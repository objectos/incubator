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

final class ArticleTemplate extends DocsTemplate {

  private final ArticleCss css = new ArticleCss();

  @SuppressWarnings("unused")
  private final Breadcrumbs breadcrumbs;

  @SuppressWarnings("unused")
  private final PageSwitcher pageSwitcher;

  private final LanguageRenderer defaultRenderer = new DefaultRenderer();

  private final LanguageRenderer javaRenderer = new JavaRenderer();

  private final LanguageRenderer xmlRenderer = new XmlRenderer();

  private LanguageRenderer languageRenderer;

  private final StringBuilder source = new StringBuilder();

  ArticleTemplate(DocsInjector injector) {
    super(injector);

    breadcrumbs = new Breadcrumbs(injector);

    pageSwitcher = new PageSwitcher(injector);
  }

  @Override
  public final void lineFeed() {
    if (languageRenderer != null) {
      source.append('\n');
    }
  }

  @Override
  public final void listingBlockEnd() {
    sourceCodeBlockEnd();
  }

  @Override
  public final void listingBlockStart() {
    sourceCodeBlockStart("default");
  }

  @Override
  public final void sourceCodeBlockEnd() {
    languageRenderer.render(source);

    languageRenderer = null;
  }

  @Override
  public final void sourceCodeBlockStart(String language) {
    source.setLength(0);

    languageRenderer = switch (language) {
      case "default", "shell" -> defaultRenderer;

      case "java" -> javaRenderer;

      case "xml" -> xmlRenderer;

      default -> throw new UnsupportedOperationException("Implement me :: lang=" + language);
    };
  }

  @Override
  public final void text(String s) {
    if (languageRenderer != null) {
      source.append(s);
    } else {
      super.text(s);
    }
  }

  @Override
  final void body0() {
    var nextBanner = injector.$nextBanner();

    body(
      nextBanner.shouldRender() ? f(nextBanner) : noop(),

      //f(breadcrumbs),

      main(
        article(
          f(this::renderDocument)
        )
      )

    //f(pageSwitcher)
    );
  }

  @Override
  final StyleSheet styleSheet() {
    return css;
  }

}