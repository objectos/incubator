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
import java.util.Set;
import java.util.function.Function;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

final class SyntaxRenderer implements NodeRenderer {

  private final HtmlNodeRendererContext context;

  SyntaxRenderer(HtmlNodeRendererContext context) {
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

    if (info.startsWith("java")) {
      render(b, JavaRenderer::new);
    }

    else if (info.startsWith("xml")) {
      render(b, XmlRenderer::new);
    }

    else {
      render(b, DefaultRenderer::new);
    }
  }

  private void render(FencedCodeBlock b, Function<HtmlWriter, LanguageRenderer> constructor) {
    HtmlWriter html;
    html = context.getWriter();

    LanguageRenderer renderer;
    renderer = constructor.apply(html);

    renderer.render(b);
  }

  private static class DefaultRenderer extends LanguageRenderer {

    DefaultRenderer(HtmlWriter html) {
      super(html);
    }

    @Override
    final void render(String info, String literal) {
      text(literal);
    }

  }

}