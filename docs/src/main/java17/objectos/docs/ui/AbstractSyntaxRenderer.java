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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import objectos.docs.style.SyntaxCss;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.CoreHtmlNodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

abstract class AbstractSyntaxRenderer implements NodeRenderer {

  private final Map<String, String> attrs = new LinkedHashMap<>();

  private final HtmlNodeRendererContext context;

  private HtmlWriter html;

  AbstractSyntaxRenderer(HtmlNodeRendererContext context) {
    this.context = context;
  }

  @Override
  public final Set<Class<? extends Node>> getNodeTypes() {
    return Collections.singleton(FencedCodeBlock.class);
  }

  @Override
  public final void render(Node node) {
    FencedCodeBlock b;
    b = (FencedCodeBlock) node;

    String info;
    info = b.getInfo();

    if (shouldRender(info)) {
      html = context.getWriter();

      html.line();

      attrs.clear();
      attrs.put("class", SyntaxCss._PRE.className());
      html.tag("pre", attrs);

      html.tag("code");

      String literal;
      literal = b.getLiteral();

      literal = literal.trim();

      render(literal);

      html.tag("/code");
      html.tag("/pre");
      html.line();
    }

    else {
      CoreHtmlNodeRenderer core;
      core = new CoreHtmlNodeRenderer(context);

      core.render(node);
    }
  }

  protected final void span(ClassSelector clazz, char c) {
    span(clazz, Character.toString(c));
  }

  protected final void span(ClassSelector clazz, String s) {
    attrs.clear();

    attrs.put("class", clazz.className());

    html.tag("span", attrs);
    html.text(s);
    html.tag("/span");
  }

  protected final void text(String s) {
    html.text(s);
  }

  abstract void render(String literal);

  abstract boolean shouldRender(String info);

}