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

import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.layout.Display;
import objectos.asciidoc.InlineMacroAttributes;
import objectos.html.tmpl.StandardElementName;
import objectos.shared.SharedTemplate;

abstract class DocsTemplate extends SharedTemplate {

  final DocsInjector injector;

  private String rawStyle;

  String key;

  Version version;

  DocsTemplate(DocsInjector injector) { this.injector = injector; }

  @Override
  public void inlineMacro(
      String name, String target, InlineMacroAttributes attributes) {
    switch (name) {
      case "elink" -> {
        var href = injector.$elink(target);

        tagStart();
        linkPathTo(href);
        attributes.render("1");
        tagEnd(StandardElementName.A);
      }

      case "ilink" -> {
        var href = injector.$ilink(target);

        tagStart();
        linkPathTo(href);
        attributes.render("1");
        tagEnd(StandardElementName.A);
      }

      case "issue" -> {
        tagStart();
        linkValues("https://github.com/objectos/objectos/issues/" + target);
        text("#" + target);
        tagEnd(StandardElementName.A);
      }

      default -> throw new UnsupportedOperationException("Implement me :: name=" + name);
    }
  }

  @Override
  protected final void definition() {
    pathName(key);

    var topBar = injector.$topBar();

    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        BODY,
        Display.flex,
        FlexDirection.column,

        topBar.toFragment(),

        f(this::main0),

        injector.$bottomBar()
      )
    );
  }

  void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type("vnd.microsoft.icon"), href("/favicon.ico"));

    var title = injector.$title();

    title(title.plain());

    if (rawStyle != null) {
      style(rawStyle);
    }

    var topBar = injector.$topBar();

    var topBarJs = topBar.javaScript();

    if (!topBarJs.isBlank()) {
      script(topBarJs);
    }
  }

  abstract void main0();

  final void rawStyle(String rawStyle) {
    this.rawStyle = rawStyle;
  }

  final void renderDocument() {
    var document = injector.$document();

    document.process(this);
  }

}