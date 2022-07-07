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

final class ArticlePage extends BaseTemplate {

  private final ArticleCss css = new ArticleCss();

  private final Breadcrumbs breadcrumbs;

  private final PageSwitcher pageSwitcher;

  private final DocsInjector injector;

  ArticlePage(DocsInjector injector) {
    this.injector = injector;

    breadcrumbs = new Breadcrumbs(injector);

    pageSwitcher = new PageSwitcher(injector);
  }

  @Override
  final void body0() {
    var nextBanner = injector.$nextBanner();

    body(
      nextBanner.shouldRender() ? f(nextBanner) : noop(),

      f(breadcrumbs),

      main(
        article(
          h1(raw(injector.$doctitle())),

          raw(injector.$contents())
        )
      ),

      f(pageSwitcher)
    );
  }

  @Override
  final StyleSheet styleSheet() {
    return css;
  }

  @Override
  final String thisTitle() {
    return injector.$doctitle();
  }

}