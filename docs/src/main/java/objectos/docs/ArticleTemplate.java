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
import br.com.objectos.html.spi.type.LiValue;
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

        f(this::leftBar)
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

  private void leftBar() {
    var version = injector.$version();

    h1(version.fullName());

    switch (version) {
      case NEXT -> {
        ul(
          li(leftLink("next/index")),

          li(
            h2("Introduction"),

            ul(
              li(leftLink("next/intro/index")),
              li(leftLink("next/intro/overview")),
              li(leftLink("next/intro/install"))
            )
          ),

          li(
            h2("Objectos Lang"),

            ul(
              li(leftLink("next/objectos-lang/index", "Introduction")),
              li(leftLink("next/objectos-lang/Check")),
              li(leftLink("next/objectos-lang/Equals")),
              li(leftLink("next/objectos-lang/HashCode")),
              li(leftLink("next/objectos-lang/ToString")),

              li(
                h3("Note sink API"),

                li(leftLink("next/objectos-lang/note-sink-api/index")),
                li(leftLink("next/objectos-lang/note-sink-api/creating-notes")),
                li(leftLink("next/objectos-lang/note-sink-api/the-note-sink-interface")),
                li(leftLink("next/objectos-lang/note-sink-api/the-no-op-note-sink"))
              )
            )
          ),

          li(
            h2("Release Notes"),

            ul(
              li(leftLink("next/relnotes/0.2.0", "Objectos 0.2.0")),
              li(leftLink("next/relnotes/0.1.0", "Objectos 0.1.0"))
            )
          )
        );
      }

      case V0_2_0 -> {
        ul(
          li(leftLink("v0002/index")),

          li(
            h2("Introduction"),

            ul(
              li(leftLink("v0002/intro/index")),
              li(leftLink("v0002/intro/overview")),
              li(leftLink("v0002/intro/install"))
            )
          ),

          li(
            h2("Objectos Lang"),

            ul(
              li(leftLink("v0002/objectos-lang/index", "Introduction")),
              li(leftLink("v0002/objectos-lang/Check")),
              li(leftLink("v0002/objectos-lang/Equals")),
              li(leftLink("v0002/objectos-lang/HashCode")),
              li(leftLink("v0002/objectos-lang/ToString")),

              li(
                h3("Note sink API"),

                li(leftLink("v0002/objectos-lang/note-sink-api/index")),
                li(leftLink("v0002/objectos-lang/note-sink-api/creating-notes")),
                li(leftLink("v0002/objectos-lang/note-sink-api/the-note-sink-interface")),
                li(leftLink("v0002/objectos-lang/note-sink-api/the-no-op-note-sink"))
              )
            )
          ),

          li(
            h2("Release Notes"),

            ul(
              li(leftLink("v0002/relnotes/0.2.0", "Objectos 0.2.0")),
              li(leftLink("v0002/relnotes/0.1.0", "Objectos 0.1.0"))
            )
          )
        );
      }

      case V0_1_0 -> {
        ul(
          li(leftLink("v0001/index")),

          li(
            h2("Introduction"),

            ul(
              li(leftLink("v0001/intro/index")),
              li(leftLink("v0001/intro/overview")),
              li(leftLink("v0001/intro/install"))
            )
          ),

          li(
            h2("Objectos Logging"),

            ul(
              li(leftLink("v0001/logging/index")),

              li(leftLink("v0001/logging/getting-started/index")),
              li(leftLink("v0001/logging/getting-started/about-logging")),
              li(leftLink("v0001/logging/getting-started/objectos-logging")),
              li(leftLink("v0001/logging/getting-started/installing")),
              li(leftLink("v0001/logging/getting-started/quick-start")),

              li(leftLink("v0001/logging/logging-guide/index")),
              li(leftLink("v0001/logging/logging-guide/events")),
              li(leftLink("v0001/logging/logging-guide/logger")),

              li(leftLink("v0001/logging/no-op-logger/index"))
            )
          ),

          li(
            h2("Release Notes"),

            ul(
              li(leftLink("v0001/relnotes/0.1.0", "Objectos 0.1.0"))
            )
          )
        );
      }

      default -> throw new UnsupportedOperationException("Implement me :: version=" + version);
    }
  }

  private LiValue leftLink(String key) {
    return a(
      href(injector.$href(key)),
      raw(injector.$title(key).html())
    );
  }

  private LiValue leftLink(String key, String text) {
    return a(href(injector.$href(key)), t(text));
  }

}