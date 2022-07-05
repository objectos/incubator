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
package objectos.docs;

import br.com.objectos.html.tmpl.AbstractTemplate;
import br.com.objectos.http.media.ImageType;
import objectos.lang.Check;
import org.asciidoctor.ast.Document;

final class ArticlePage extends AbstractTemplate {

  private final ArticleCss css = new ArticleCss();

  private final NextBanner nextBanner = new NextBanner();

  private Document document;

  private boolean next;

  public final void set(Document document) {
    this.document = Check.notNull(document, "document == null");
  }

  public final void setNext(boolean next) {
    this.next = next;
  }

  @Override
  protected final void definition() {
    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      f(this::body0)
    );
  }

  private void body0() {
    body(
      next ? f(nextBanner) : noop(),

      main(
        f(this::main0)
      )
    );
  }

  private void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));
    title(document.getDoctitle());
    style(
      raw(css.toString())
    );
  }

  private void main0() {
    article(
      f(() -> {
        h1(document.getDoctitle());

        for (var node : document.getBlocks()) {
          var c = node.getContent();

          if (c instanceof String s) {
            raw(s);
          } else {
            throw new RuntimeException("Unexpected content: " + c.getClass());
          }
        }
      })
    );
  }

}