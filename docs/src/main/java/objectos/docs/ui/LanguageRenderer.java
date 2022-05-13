/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.ui;

import br.com.objectos.css.select.ClassSelector;
import java.util.LinkedHashMap;
import java.util.Map;
import objectos.docs.style.SyntaxCss;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.renderer.html.HtmlWriter;

abstract class LanguageRenderer {

  private final Map<String, String> attrs = new LinkedHashMap<>();

  private final HtmlWriter html;

  LanguageRenderer(HtmlWriter html) {
    this.html = html;
  }

  public final void render(FencedCodeBlock b) {
    html.line();

    attrs.clear();
    attrs.put("class", SyntaxCss._PRE.className());
    html.tag("pre", attrs);

    html.tag("code");

    String info;
    info = b.getInfo();

    String literal;
    literal = b.getLiteral();

    literal = literal.trim();

    render(info, literal);

    html.tag("/code");
    html.tag("/pre");
    html.line();
  }

  abstract void render(String info, String literal);

  final void span(ClassSelector clazz, char c) {
    span(clazz, Character.toString(c));
  }

  final void span(ClassSelector clazz, String s) {
    attrs.clear();

    attrs.put("class", clazz.className());

    html.tag("span", attrs);
    html.text(s);
    html.tag("/span");
  }

  final void text(String s) {
    html.text(s);
  }

}