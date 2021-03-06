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

final class IndexPage extends BaseTemplate {

  private final IndexCss css = new IndexCss();

  private final DocsInjector injector;

  IndexPage(DocsInjector injector) {
    this.injector = injector;
  }

  @Override
  final void body0() {
    var nextBanner = injector.$nextBanner();

    var version = injector.$version();

    body(
      nextBanner.shouldRender() ? f(nextBanner) : noop(),

      main(
        article(
          header(
            IndexCss.HD,

            h1("Documentation for Objectos developers"),

            div(
              IndexCss.HDV,

              div(
                t("Version", version.name()), t(" ["), a(href("../versions.html"), t("change")),
                t("]")
              ),

              div(
                t("API reference: "), a(href("api/index.html"), t("Javadocs"))
              )
            )
          ),

          raw(injector.$contents()),

          section(
            h2("Table of contents"),

            f(injector.$tableOfContents())
          )
        )
      )
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