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
import java.util.Map;
import java.util.NoSuchElementException;
import objectos.asciidoc.Document;
import objectos.docs.Docs.BottomBar;
import objectos.docs.Docs.TopBar;
import objectos.html.HtmlSink;
import objectos.html.HtmlTemplate;
import objectos.shared.StyleClassSet;
import objectos.util.GrowableMap;

public class Step3Generate extends Step2Scan {

  private final Map<String, DocsTemplate> templates;

  private BottomBar bottomBar = new DocsBottomBar();

  private String currentKey;

  private DocumentRecord currentRecord;

  private Version currentVersion;

  private TopBar topBar = new DocsTopBar();

  public Step3Generate() {
    var injector = new ThisInjector();

    templates = _templates(
      new ArticleTemplate(injector),

      new VersionsTemplate(injector)
    );
  }

  public final void bottomBar(BottomBar bottomBar) {
    this.bottomBar = bottomBar;
  }

  public final void topBar(TopBar topBar) {
    this.topBar = topBar;
  }

  public final void executeGenerate() throws IOException {
    System.out.println("Resource target path: " + targetDirectory);

    var htmlSink = new HtmlSink();

    var styleClassSet = new StyleClassSet();

    var styleSheet = new DocsCss();

    var styleSheetWriter = StyleSheetWriter.ofPretty();

    for (var entry : documents.entrySet()) {
      currentKey = entry.getKey();

      currentRecord = entry.getValue();

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
  }

  private DocsTemplate _template(String templateName) {
    var tmpl = templates.get(templateName);

    if (tmpl == null) {
      throw new NoSuchElementException(templateName);
    }

    return tmpl;
  }

  private Map<String, DocsTemplate> _templates(DocsTemplate... templates) {
    var map = new GrowableMap<String, DocsTemplate>();

    for (var template : templates) {
      var clazz = template.getClass();

      var key = clazz.getSimpleName();

      map.put(key, template);
    }

    return map.toUnmodifiableMap();
  }

  private class ThisInjector extends DocsInjector {
    @Override
    final HtmlTemplate $bottomBar() { return bottomBar.toFragment(); }

    @Override
    final Document $document() { return currentRecord.document(); }

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
    final DocumentTitle $title() { return currentRecord.title(); }

    @Override
    final TopBar $topBar() { return topBar; }

    @Override
    final Iterable<Version> $versions() { return versions.values(); }
  }

}