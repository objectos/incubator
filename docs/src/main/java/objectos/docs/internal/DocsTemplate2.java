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
import objectos.asciidoc.pseudom.Node.Monospaced;
import objectos.shared.SharedTemplate2;

abstract class DocsTemplate2 extends SharedTemplate2 {

  final DocsInjector injector;

  private String rawStyle;

  String key;

  Version version;

  DocsTemplate2(DocsInjector injector) { this.injector = injector; }

  protected final void container(ContainerNode container) {
    for (var node : container.nodes()) {
      node(node);
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

  void monospaced(Monospaced monospaced) {
    code(f(() -> container(monospaced)));
  }

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

  abstract void node(Node node);

}