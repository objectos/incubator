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
import java.util.Objects;
import objectos.asciidoc.Document;
import objectos.docs.Docs.BottomBar;
import objectos.docs.Docs.TopBar;
import objectos.html.HtmlSink;
import objectos.html.HtmlTemplate;
import objectos.lang.Check;
import objectos.shared.StyleClassSet;
import objectos.util.GrowableMap;

public class Step3Generate extends Step2Scan {

  private final LeftBar leftBar;

  private final Map<String, DocsTemplate> templates;

  private BottomBar bottomBar = new DocsBottomBar();

  private String currentKey;

  private HtmlTemplate currentLeftBar;

  private DocumentRecord currentRecord;

  private Version currentVersion;

  private TopBar topBar = new DocsTopBar();

  public Step3Generate() {
    var injector = new ThisInjector();

    leftBar = new LeftBar(injector);

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

      currentVersion = Version.parseCurrentKey(currentKey);

      if (currentVersion != null) {
        currentLeftBar = leftBar.get(currentKey, currentVersion);
      } else {
        currentLeftBar = null;
      }

      var templateName = currentRecord.templateName();

      var template = _template(templateName);

      template.rawStyle(null);

      htmlSink.toVisitor(template, styleClassSet);

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

  private String toKey(String target) {
    Check.state(currentVersion != null, "currentVersion is not set");

    return currentVersion.directory + "/" + target;
  }

  private class ThisInjector extends DocsInjector {
    @Override
    final HtmlTemplate $bottomBar() { return bottomBar.toFragment(); }

    @Override
    final Document $document() { return currentRecord.document(); }

    @Override
    final String $elink(String target) {
      var first = target.indexOf('/');

      var versionKey = target.substring(0, first);

      var version = Version.parse(versionKey);

      var key = target.substring(first + 1);

      return baseHref + "/" + version.slug + "/" + key + ".html";
    }

    @Override
    final String $href() {
      var location = currentRecord.location();

      return location.href();
    }

    @Override
    final String $href(String key) {
      var record = documents.get(key);

      if (record != null) {
        var location = record.location();

        return location.href();
      }

      if (!production) {
        return "";
      }

      throw new NoSuchElementException(key);
    }

    @Override
    final String $ilink(String target) {
      var key = toKey(target);

      return $href(key);
    }

    @Override
    final boolean $isCurrentKey(String key) { return Objects.equals(currentKey, key); }

    @Override
    final boolean $isNext() { return currentKey.startsWith("next/"); }

    @Override
    final HtmlTemplate $leftBar() { return currentLeftBar; }

    @Override
    final String $pathName() {
      var version = Version.parseCurrentKey(currentKey);

      if (version != null) {
        return version.pathName(currentKey);
      } else {
        return "/" + currentKey + ".html";
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
    final Version $version() { return currentVersion; }
  }

}