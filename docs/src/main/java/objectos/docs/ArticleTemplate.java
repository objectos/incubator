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

import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.sizing.Width;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.PaddingBottom;
import br.com.objectos.css.framework.spacing.PaddingLeft;
import br.com.objectos.css.framework.spacing.PaddingRight;
import br.com.objectos.css.framework.spacing.PaddingX;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.FontWeight;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.element.StandardElementName;
import objectos.docs.style.SyntaxCss;

final class ArticleTemplate extends DocsTemplate implements LanguageRenderer.Output {

  private final ArticleCss css = new ArticleCss();

  private final LanguageRenderer defaultRenderer = new DefaultRenderer();

  private final LanguageRenderer javaRenderer = new JavaRenderer();

  private final LanguageRenderer xmlRenderer = new XmlRenderer();

  private LanguageRenderer languageRenderer;

  private final StringBuilder source = new StringBuilder();

  ArticleTemplate(DocsInjector injector) { super(injector); }

  @Override
  public final void headingStart(int level) {
    super.headingStart(level);

    switch (level) {
      case 1 -> addValue0(
        BorderColor.gray300,
        BorderBottom.v1,
        FontSize.xLarge3,
        FontWeight.bold,
        MarginBottom.v04,
        PaddingBottom.v01
      );
    }
  }

  @Override
  public final void languageSpan(ClassSelector clazz, String contents) {
    addValue0(span(clazz, t(contents)));
  }

  @Override
  public final void languageText(String text) {
    addValue0(t(text));
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
  public final void paragraphStart() {
    super.paragraphStart();

    addValue0(
      MarginBottom.v04
    );
  }

  @Override
  public final void sourceCodeBlockEnd() {
    var literal = source.toString();

    tagStart();
    addValue0(SyntaxCss._PRE);
    tagStart();
    languageRenderer.render(this, literal);
    tagEnd(StandardElementName.CODE);
    tagEnd(StandardElementName.PRE);

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
  final void main0() {
    //    var nextBanner = injector.$nextBanner();
    div(
      //    nextBanner.shouldRender() ? f(nextBanner) : noop(),
      Display.flex,
      MarginX.auto,
      MaxWidth.screenXl,

      nav(
        Display.hidden,
        Display.md.block,
        Flex.md.none,
        PaddingX.md.v06,
        Width.md.v56,
        Width.lg.v72,

        t("Objectos v0.2.0")
      ),

      main(
        PaddingX.v04,
        PaddingLeft.md.v0,
        PaddingRight.md.v06,

        article(
          f(this::renderDocument)
        )
      )
    );
  }

  @Override
  final StyleSheet styleSheet() {
    return css;
  }

}