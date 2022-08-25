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

import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderRight;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.flexbox.FlexGrow;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.sizing.MinHeight;
import br.com.objectos.css.framework.sizing.Width;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.PaddingBottom;
import br.com.objectos.css.framework.spacing.PaddingLeft;
import br.com.objectos.css.framework.spacing.PaddingRight;
import br.com.objectos.css.framework.spacing.PaddingTop;
import br.com.objectos.css.framework.spacing.PaddingX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.FontWeight;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextTransform;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.element.ElementName;
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
  public final void headingEnd(int level) {
    super.headingEnd(level); // </hx>
  }

  @Override
  public final void headingStart(int level) {
    super.headingStart(level);

    switch (level) {
      case 1 -> {
        tagStart(); // <h1>

        addValue0(
          BorderColor.gray300,
          BorderBottom.v1,
          FontSize.xLarge3,
          FontWeight.bold,
          MarginBottom.v04,
          PaddingBottom.v01
        );
      }
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
    div(
      FlexGrow.one,

      div(
        Display.flex,
        MarginX.auto,
        MaxWidth.screenX2l,
        MinHeight.full,

        nav(
          BorderColor.md.gray300,
          BorderRight.md.v1,
          Display.hidden,
          Display.md.block,
          Flex.md.none,
          FontSize.small,
          FontWeight.normal,
          PaddingLeft.md.v02,
          PaddingRight.md.v02,
          PaddingTop.v06,
          TextColor.stone800,
          Width.md.v56,
          Width.lg.v72,

          f(this::_leftBar)
        ),

        main(
          FlexGrow.one,
          PaddingX.v04,
          PaddingLeft.md.v0,
          PaddingRight.md.v06,
          PaddingTop.v06,

          article(
            f(this::renderDocument)
          )
        )
      )
    );
  }

  @Override
  final StyleSheet styleSheet() {
    return css;
  }

  private void _leftBar() {
    var version = injector.$version();

    h1(version.fullName());

    switch (version) {
      case NEXT -> {
        ul(
          li0("next/index"),

          li(
            h2("Introduction"),

            ul(
              li0("next/intro/index"),
              li0("next/intro/overview"),
              li0("next/intro/install")
            )
          ),

          li(
            h2("Objectos Lang"),

            ul(
              li0("next/objectos-lang/index"),
              li0("next/objectos-lang/Check"),
              li0("next/objectos-lang/Equals"),
              li0("next/objectos-lang/HashCode"),
              li0("next/objectos-lang/ToString"),

              li(
                h3("Note sink API"),

                li0("next/objectos-lang/note-sink-api/index"),
                li0("next/objectos-lang/note-sink-api/creating-notes"),
                li0("next/objectos-lang/note-sink-api/the-note-sink-interface"),
                li0("next/objectos-lang/note-sink-api/the-no-op-note-sink")
              )
            )
          ),

          li(
            h2("Release Notes"),

            ul(
              li0("next/relnotes/0.2.0"),
              li0("next/relnotes/0.1.0")
            )
          )
        );
      }

      case V0_2_0 -> {
        ul(
          li0("v0002/index"),

          li(
            h2("Introduction"),

            ul(
              li0("v0002/intro/index"),
              li0("v0002/intro/overview"),
              li0("v0002/intro/install")
            )
          ),

          li(
            h2("Objectos Lang"),

            ul(
              li0("v0002/objectos-lang/index"),
              li0("v0002/objectos-lang/Check"),
              li0("v0002/objectos-lang/Equals"),
              li0("v0002/objectos-lang/HashCode"),
              li0("v0002/objectos-lang/ToString"),

              li(
                h3("Note sink API"),

                li0("v0002/objectos-lang/note-sink-api/index"),
                li0("v0002/objectos-lang/note-sink-api/creating-notes"),
                li0("v0002/objectos-lang/note-sink-api/the-note-sink-interface"),
                li0("v0002/objectos-lang/note-sink-api/the-no-op-note-sink")
              )
            )
          ),

          li(
            h2("Release Notes"),

            ul(
              li0("v0002/relnotes/0.2.0"),
              li0("v0002/relnotes/0.1.0")
            )
          )
        );
      }

      case V0_1_0 -> {
        ul(
          li0("v0001/index"),

          li(
            h2v0("Introduction"),

            ul(
              li0("v0001/intro/index"),
              li0("v0001/intro/overview"),
              li0("v0001/intro/install")
            )
          ),

          li(
            h2v0("Objectos Logging"),

            ul(
              li0("v0001/logging/index"),

              li0("v0001/logging/getting-started/index"),
              li0("v0001/logging/getting-started/about-logging"),
              li0("v0001/logging/getting-started/objectos-logging"),
              li0("v0001/logging/getting-started/installing"),
              li0("v0001/logging/getting-started/quick-start"),

              li0("v0001/logging/logging-guide/index"),
              li0("v0001/logging/logging-guide/events"),
              li0("v0001/logging/logging-guide/logger"),

              li0("v0001/logging/no-op-logger/index")
            )
          ),

          li(
            h2v0("Release Notes"),

            ul(
              li0("v0001/relnotes/0.1.0")
            )
          )
        );
      }

      default -> throw new UnsupportedOperationException("Implement me :: version=" + version);
    }
  }

  private ElementName h2v0(String text) {
    return h2(
      FontWeight.semibold,
      PaddingTop.v06,
      PaddingX.v04,
      TextColor.black,
      TextTransform.uppercase,

      t(text)
    );
  }

  private ElementName li0(String key) {
    var selected = injector.$isCurrentKey(key);

    var bg = selected ? BackgroundColor.slate200 : BackgroundColor.hover.gray100;
    var fc = selected ? TextColor.black : noop();

    var href = injector.$href(key);

    var text = injector.$title(key).html();

    return li(
      a(
        bg, fc,
        PaddingX.v04,
        PaddingY.v01,
        Display.flex,

        href(href), raw(text)
      )
    );
  }

}