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

import br.com.objectos.css.sheet.StyleSheet;

final class IndexPage extends ThisTemplate {

  private final IndexCss css = new IndexCss();

  private final StringBuilder sb = new StringBuilder();

  private final TableOfContents toc;

  IndexPage(TableOfContents toc) {
    this.toc = toc;
  }

  @Override
  public final void set(Pages pages) {
    super.set(pages);

    toc.set(pages);
  }

  @Override
  final void body0() {
    sb.setLength(0);

    for (var node : document.getBlocks()) {
      var c = node.getContent();

      if (c instanceof String s) {
        sb.append(s);
      } else {
        throw new RuntimeException("Unexpected content: " + c.getClass());
      }
    }

    body(
      main(
        article(
          h1(document.getDoctitle()),

          raw(sb.toString()),

          section(
            h2("Table of contents"),

            f(toc)
          )
        )
      )
    );
  }

  @Override
  final StyleSheet styleSheet() {
    return css;
  }

}