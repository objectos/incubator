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

final class ArticlePage extends ThisTemplate {

  private final ArticleCss css = new ArticleCss();

  private final Breadcrumbs breadcrumbs = new Breadcrumbs();

  private final NextBanner nextBanner;

  private final PageSwitcher pageSwitcher = new PageSwitcher();

  private final StringBuilder sb = new StringBuilder();

  ArticlePage(NextBanner nextBanner) { this.nextBanner = nextBanner; }

  @Override
  public final void set(Pages pages) {
    super.set(pages);

    breadcrumbs.set(pages);

    pageSwitcher.set(pages);
  }

  @Override
  final void body0() {
    var href = pages.href();

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
      href.contains("/next/") ? f(nextBanner) : noop(),

      f(breadcrumbs),

      main(
        article(
          h1(raw(document.getDoctitle())),

          raw(sb.toString())
        )
      ),

      f(pageSwitcher)
    );
  }

  @Override
  final StyleSheet styleSheet() {
    return css;
  }

}