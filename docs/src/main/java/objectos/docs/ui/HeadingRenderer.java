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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

final class HeadingRenderer implements NodeRenderer {

  private final HtmlNodeRendererContext context;

  private String id;

  private final HtmlWriter html;

  HeadingRenderer(HtmlNodeRendererContext context) {
    this.context = context;

    html = context.getWriter();
  }

  @Override
  public final Set<Class<? extends Node>> getNodeTypes() {
    return Collections.singleton(Heading.class);
  }

  @Override
  public final void render(Node node) {
    var h = (Heading) node;

    preProcess(h);

    var htag = "h" + h.getLevel();

    html.line();

    if (id != null) {
      var attrs = Map.of("id", id);

      html.tag(htag, attrs);
    } else {
      html.tag(htag);
    }

    var child = h.getFirstChild();

    while (child != null) {
      context.render(child);

      child = child.getNext();
    }

    html.tag('/' + htag);

    html.line();

    id = null;
  }

  private void preProcess(Heading h) {
    var child = h.getFirstChild();

    while (child != null) {
      if (child instanceof Text t) {
        visitText(t);
      }

      child = child.getNext();
    }
  }

  private void visitText(Text t) {
    String text;
    text = t.getLiteral();

    if (text.isEmpty()) {
      return;
    }

    int length;
    length = text.length();

    char c;
    c = '\0';

    int index;
    index = length - 1;

    int closingBrace;
    closingBrace = -1;

    while (index >= 0) {
      c = text.charAt(index);

      index--;

      if (c == ' ') {
        continue;
      }

      if (c == '}') {
        closingBrace = index + 1;
      }

      break;
    }

    if (closingBrace < 0) {
      return;
    }

    int openingBrace;
    openingBrace = -1;

    char previous;
    previous = c;

    while (index >= 0) {
      c = text.charAt(index);

      index--;

      if (c == '{' && previous == '#') {
        openingBrace = index + 1;

        break;
      }

      previous = c;
    }

    if (openingBrace < 0) {
      return;
    }

    id = text.substring(openingBrace + 2, closingBrace);

    var trimmed = text.substring(0, openingBrace);

    t.setLiteral(trimmed);
  }

}