/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package objectos.docs.internal;

import br.com.objectos.css.sheet.StyleSheetWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import objectos.asciidoc.Document;
import objectos.docs.Docs.BottomBar;
import objectos.docs.Docs.TopBar;
import objectos.html.HtmlSink;
import objectos.html.HtmlTemplate;
import objectos.shared.StyleClassSet;

public class Step3Generate extends Step2Scan {

  private class Task implements Runnable {
    private final String key;
    private final DocumentRecord record;

    public Task(String key, DocumentRecord record) {
      this.key = key;
      this.record = record;
    }

    @Override
    public final void run() {
      ThisInjector injector;

      try {
        injector = injectors.take();
      } catch (InterruptedException e) {
        if (rethrow != null) {
          rethrow.addSuppressed(e);
        } else {
          rethrow = new IOException(e);
        }

        return;
      }

      try {
        injector.generate(key, record);
      } catch (IOException e) {
        if (rethrow != null) {
          rethrow.addSuppressed(e);
        } else {
          rethrow = e;
        }
      } finally {
        injectors.add(injector);
      }
    }
  }

  private class ThisInjector extends DocsInjector {
    private final HtmlSink htmlSink = new HtmlSink();

    private final StyleClassSet styleClassSet = new StyleClassSet();

    private final DocsCss styleSheet = new DocsCss();

    private final StyleSheetWriter styleSheetWriter = StyleSheetWriter.ofPretty();

    private final ArticleTemplate articleTemplate = new ArticleTemplate(this);

    private final VersionsTemplate versionsTemplate = new VersionsTemplate(this);

    private final BottomBar bottomBar = bottomBarFactory.get();

    private final TopBar topBar = topBarFactory.get();

    private String currentKey;

    private DocumentRecord currentRecord;

    private Version currentVersion;

    public final void generate(String key, DocumentRecord record) throws IOException {
      currentKey = key;

      currentRecord = record;

      currentVersion = currentRecord.version();

      var templateName = currentRecord.templateName();

      var template = _template(templateName);

      template.key = currentKey;

      template.version = currentVersion;

      template.rawStyle(null);

      htmlSink.toProcessor(template, styleClassSet);

      styleSheetWriter.filterClassSelectorsByName(styleClassSet);

      template.rawStyle(styleSheetWriter.toString(styleSheet));

      htmlSink.toDirectory(template, targetDirectory);
    }

    @Override
    final HtmlTemplate $bottomBar() {
      return bottomBar.toFragment();
    }

    @Override
    final Document $document() {
      return currentRecord.document();
    }

    @Override
    final String $elink(String target) {
      char first = target.charAt(0);

      if (first != '/') {
        var index = target.indexOf('/');

        if (index == -1) {
          throw new UnsupportedOperationException("Implement me");
        }

        var key = target.substring(0, index);

        var prefix = switch (key) {
          case "v000502" -> "/0.5.2";
          case "v000501" -> "/0.5.1";
          case "v000500" -> "/0.5.0";
          case "v0004" -> "/0.4";
          case "v0003" -> "/0.3";
          case "v0002" -> "/0.2";
          case "v0001" -> "/0.1";
          default -> throw new UnsupportedOperationException("Implement me :: key=" + key);
        };

        return prefix + target.substring(index) + ".html";
      } else {
        throw new UnsupportedOperationException("Implement me");
      }
    }

    @Override
    final String $ilink(String target) {
      char first = target.charAt(0);

      if (first != '/') {
        return "/" + currentVersion.directory + "/" + target + ".html";
      } else {
        throw new UnsupportedOperationException("Implement me");
      }
    }

    @Override
    final DocumentRecord $record(String key) {
      var record = documents.get(key);

      if (record == null) {
        throw new NoSuchElementException(key);
      }

      return record;
    }

    @Override
    final DocumentTitle $title() {
      return currentRecord.title();
    }

    @Override
    final TopBar $topBar() {
      return topBar;
    }

    @Override
    final Iterable<Version> $versions() {
      return versions.values();
    }

    private DocsTemplate _template(String templateName) {
      return switch (templateName) {
        case "ArticleTemplate" -> articleTemplate;

        case "VersionsTemplate" -> versionsTemplate;

        default -> throw new NoSuchElementException(templateName);
      };
    }
  }

  private final int capacity;

  private final BlockingQueue<ThisInjector> injectors;

  private Supplier<BottomBar> bottomBarFactory = DocsBottomBar::new;

  private IOException rethrow;

  private Supplier<TopBar> topBarFactory = DocsTopBar::new;

  public Step3Generate() {
    capacity = Runtime.getRuntime().availableProcessors();

    injectors = new ArrayBlockingQueue<>(capacity);

    for (int i = 0; i < capacity; i++) {
      injectors.add(new ThisInjector());
    }
  }

  public final void bottomBar(Supplier<BottomBar> bottomBarFactory) {
    this.bottomBarFactory = bottomBarFactory;
  }

  public final void executeGenerate() throws IOException {
    long startTime = System.currentTimeMillis();

    System.out.println("Resource target path: " + targetDirectory);

    try (var executor = Executors.newFixedThreadPool(capacity)) {
      for (var entry : documents.entrySet()) {
        var key = entry.getKey();
        var value = entry.getValue();
        var task = new Task(key, value);
        executor.submit(task);
      }
    }

    long totalTime = System.currentTimeMillis() - startTime;

    System.out.println("Step 3: " + totalTime + " ms");

    if (rethrow != null) {
      throw rethrow;
    }
  }

  public final void topBar(Supplier<TopBar> topBarFactory) {
    this.topBarFactory = topBarFactory;
  }

}