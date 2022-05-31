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
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

final class FootnoteRenderer implements NodeRenderer {

  private final HtmlWriter html;

  FootnoteRenderer(HtmlNodeRendererContext context) {
    html = context.getWriter();
  }

  @Override
  public final Set<Class<? extends Node>> getNodeTypes() {
    return Collections.singleton(Footnote.class);
  }

  @Override
  public final void render(Node node) {
    Footnote fn;
    fn = (Footnote) node;

    Text t;
    t = (Text) fn.getFirstChild();

    String l;
    l = t.getLiteral();

    String id;
    id = "fn" + l;

    html.tag(
      "a",

      Map.of(
        "id", id + "-ret",
        "href", "#" + id
      )
    );

    html.tag("sup");

    html.text('[' + l + ']');

    html.tag("/sup");

    html.tag("/a");
  }

}