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
import java.io.IOException;
import java.io.UncheckedIOException;
import objectos.asciidoc.pseudom.Node;
import objectos.asciidoc.pseudom.Node.ContainerNode;
import objectos.asciidoc.pseudom.Node.Emphasis;
import objectos.asciidoc.pseudom.Node.Header;
import objectos.asciidoc.pseudom.Node.Monospaced;
import objectos.asciidoc.pseudom.Node.Strong;
import objectos.asciidoc.pseudom.Node.Text;
import objectos.asciidoc.pseudom.Node.Title;
import objectos.shared.SharedTemplate2;

abstract class DocsTemplate2 extends SharedTemplate2 {

  final DocsInjector injector;

  private String rawStyle;

  String key;

  Version version;

  DocsTemplate2(DocsInjector injector) { this.injector = injector; }

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
    try (var document = injector.$document2()) {
      for (var node : document.nodes()) {
        node(node);
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void node(Node node) {
    if (node instanceof Emphasis emphasis) {
      em(f(() -> container(emphasis)));
    } else if (node instanceof Header header) {
      container(header);
    } else if (node instanceof Monospaced monospaced) {
      code(f(() -> container(monospaced)));
    } else if (node instanceof Strong strong) {
      strong(f(() -> container(strong)));
    } else if (node instanceof Text text) {
      t(text.value());
    } else if (node instanceof Title title) {
      int level = title.level();

      switch (level) {
        case 0 -> h1(f(() -> container(title)));

        case 1 -> h2(f(() -> container(title)));

        case 2 -> h3(f(() -> container(title)));

        default -> throw new UnsupportedOperationException(
          "Implement me :: level=" + level
        );
      }
    } else {
      throw new UnsupportedOperationException(
        "Implement me :: type=" + node.getClass().getSimpleName()
      );
    }
  }

  private void container(ContainerNode container) {
    for (var node : container.nodes()) {
      node(node);
    }
  }

}