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
import br.com.objectos.css.framework.border.Rounded;
import br.com.objectos.css.framework.flexbox.AlignItems;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.flexbox.FlexGrow;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.layout.OverflowX;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.sizing.MinHeight;
import br.com.objectos.css.framework.sizing.MinWidth;
import br.com.objectos.css.framework.sizing.Width;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.MarginTop;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.MarginY;
import br.com.objectos.css.framework.spacing.Padding;
import br.com.objectos.css.framework.spacing.PaddingBottom;
import br.com.objectos.css.framework.spacing.PaddingLeft;
import br.com.objectos.css.framework.spacing.PaddingRight;
import br.com.objectos.css.framework.spacing.PaddingTop;
import br.com.objectos.css.framework.spacing.PaddingX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.FontWeight;
import br.com.objectos.css.framework.typography.LetterSpacing;
import br.com.objectos.css.framework.typography.ListStyleType;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextTransform;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.element.StandardElementName;
import br.com.objectos.html.spi.type.AValue;
import objectos.asciidoc.DocumentAttributes;

final class ArticleTemplate extends DocsTemplate implements LanguageRenderer.Output {

  private static final ClassSelector MY_DEFAULT = MarginY.v03;

  private final LanguageRenderer defaultRenderer = new DefaultRenderer();

  private final LanguageRenderer javaRenderer = new JavaRenderer();

  private final LanguageRenderer xmlRenderer = new XmlRenderer();

  private LanguageRenderer languageRenderer;

  private final StringBuilder source = new StringBuilder();

  private boolean containerStarted;

  private boolean heading;

  ArticleTemplate(DocsInjector injector) { super(injector); }

  @Override
  public final void documentEnd() {
    if (containerStarted) {
      tagEnd(StandardElementName.DIV); // contents

      tagEnd(StandardElementName.DIV); // container
    }
  }

  @Override
  public final void documentStart(DocumentAttributes attributes) {
    super.documentStart(attributes);

    containerStarted = false;
  }

  @Override
  public final void headingEnd(int level) {
    switch (level) {
      case 1 -> {
        tagEnd(StandardElementName.H1);

        tagEnd(StandardElementName.HEADER);

        containerStarted = true;

        // container

        tagStart();

        addValue0(
          FlexDirection.rowReverse,
          Display.flex,
          MaxWidth.full
        );

        // TOC

        tagStart();

        addValue0(
          Display.hidden,
          Display.xl.block,
          Flex.xl.none,
          Width.xl.v64
        );

        addValue0(raw("&nbsp;"));

        tagEnd(StandardElementName.DIV);

        // contents

        tagStart();

        addValue0(
          FlexGrow.one,
          MinWidth.v0,
          PaddingRight.xl.v10
        );
      }

      default -> super.headingEnd(level);
    }

    heading = false;
  }

  @Override
  public final void headingStart(int level) {
    heading = true;

    switch (level) {
      case 1 -> {
        tagStart(); // <header>

        addValue0(
          BorderColor.slate400,
          BorderBottom.v1,
          MarginBottom.v10,
          PaddingBottom.v08
        );

        tagStart(); // <h1>

        addValue0(
          FontSize.xLarge3,
          LetterSpacing.tight
        );
      }

      case 2 -> {
        tagStart(); // <h2>

        addValue0(
          FontSize.xLarge2,
          MarginTop.v10
        );
      }

      case 3 -> {
        tagStart(); // <h3>

        addValue0(
          BorderBottom.v1,
          BorderColor.slate300,
          FontSize.large,
          MarginTop.v06,
          MY_DEFAULT
        );
      }

      default -> super.headingStart(level);
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
  public final void listItemStart() {
    super.listItemStart();

    addValue0(
      MY_DEFAULT
    );
  }

  @Override
  public final void monospaceStart() {
    super.monospaceStart();

    if (!heading) {
      addValue0(
        BackgroundColor.gray100,
        FontSize.small,
        PaddingX.v01,
        PaddingY.v00_5
      );
    }
  }

  @Override
  public final void paragraphStart() {
    super.paragraphStart();

    addValue0(
      MY_DEFAULT
    );
  }

  @Override
  public final void sourceCodeBlockEnd() {
    var literal = source.toString();

    tagStart();
    addValue0(
      BackgroundColor.gray100,
      FontSize.small,
      OverflowX.auto,
      MY_DEFAULT,
      Padding.v03
    );
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
  public final void unorderedListStart() {
    super.unorderedListStart();

    addValue0(
      ListStyleType.disc,
      PaddingLeft.v08
    );
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
          BorderColor.lg.slate200,
          BorderRight.lg.v1,
          Display.hidden,
          Display.lg.block,
          Flex.lg.none,
          FontSize.small,
          FontWeight.normal,
          PaddingLeft.lg.v02,
          PaddingRight.lg.v02,
          PaddingTop.v06,
          TextColor.stone800,
          Width.lg.v56,
          DocsCss.XL_WIDTH_70,

          f(this::_leftBar)
        ),

        main(
          FlexGrow.one,
          MinWidth.v0,
          PaddingX.v04,
          PaddingTop.v06,

          PaddingTop.xl.v08,
          PaddingLeft.xl.v10,

          article(
            f(this::renderDocument)
          )
        )
      )
    );
  }

  private void _leftBar() {
    var version = injector.$version();

    div(
      AlignItems.center,
      Display.flex,
      JustifyContent.between,
      MarginBottom.v04,
      PaddingX.v03,

      div("Version " + version.name),

      a(
        BackgroundColor.gray400,
        BackgroundColor.hover.gray500,
        PaddingX.v02,
        PaddingY.v01,
        Rounded.standard,
        TextColor.white,

        href(injector.$href("versions")), t("Change")
      )
    );

    switch (version) {
      case NEXT -> {
        ul(
          li(a0("next/index")),

          li(
            h2v0("Introduction"),

            ul(
              li(a0("next/intro/overview")),
              li(a0("next/intro/install"))
            )
          ),

          li(
            h2v0("Objectos Lang"),

            ul(
              li(a0("next/objectos-lang/index")),
              li(a0("next/objectos-lang/Check")),
              li(a0("next/objectos-lang/Equals")),
              li(a0("next/objectos-lang/HashCode")),
              li(a0("next/objectos-lang/ToString")),

              li(a0("next/objectos-lang/note-sink-api/index"),
                ul(
                  li(a1("next/objectos-lang/note-sink-api/creating-notes")),
                  li(a1("next/objectos-lang/note-sink-api/the-note-sink-interface")),
                  li(a1("next/objectos-lang/note-sink-api/the-no-op-note-sink"))
                )
              )
            )
          ),

          li(
            h2v0("Release Notes"),

            ul(
              li(a0("next/relnotes/0.2.0")),
              li(a0("next/relnotes/0.1.0"))
            )
          )
        );
      }

      case V0_2_0 -> {
        ul(
          li(a0("v0002/index")),

          li(
            h2v0("Introduction"),

            ul(
              li(a0("v0002/intro/overview")),
              li(a0("v0002/intro/install"))
            )
          ),

          li(
            h2v0("Objectos Lang"),

            ul(
              li(a0("v0002/objectos-lang/index")),
              li(a0("v0002/objectos-lang/Check")),
              li(a0("v0002/objectos-lang/Equals")),
              li(a0("v0002/objectos-lang/HashCode")),
              li(a0("v0002/objectos-lang/ToString")),

              li(a0("v0002/objectos-lang/note-sink-api/index"),

                ul(
                  li(a1("v0002/objectos-lang/note-sink-api/creating-notes")),
                  li(a1("v0002/objectos-lang/note-sink-api/the-note-sink-interface")),
                  li(a1("v0002/objectos-lang/note-sink-api/the-no-op-note-sink"))
                )
              )
            )
          ),

          li(
            h2v0("Release Notes"),

            ul(
              li(a0("v0002/relnotes/0.2.0")),
              li(a0("v0002/relnotes/0.1.0"))
            )
          )
        );
      }

      case V0_1_0 -> {
        ul(
          li(a0("v0001/index")),

          li(
            h2v0("Introduction"),

            ul(
              li(a0("v0001/intro/overview")),
              li(a0("v0001/intro/install"))
            )
          ),

          li(
            h2v0("Objectos Logging"),

            ul(
              li(a0("v0001/logging/index")),

              li(a0("v0001/logging/getting-started/index"),
                ul(
                  li(a1("v0001/logging/getting-started/about-logging")),
                  li(a1("v0001/logging/getting-started/objectos-logging")),
                  li(a1("v0001/logging/getting-started/installing")),
                  li(a1("v0001/logging/getting-started/quick-start"))
                )
              ),

              li(a0("v0001/logging/logging-guide/index"),
                ul(
                  li(a1("v0001/logging/logging-guide/events")),
                  li(a1("v0001/logging/logging-guide/logger"))
                )
              ),

              li(a0("v0001/logging/no-op-logger/index"))
            )
          ),

          li(
            h2v0("Release Notes"),

            ul(
              li(a0("v0001/relnotes/0.1.0"))
            )
          )
        );
      }

      default -> throw new UnsupportedOperationException("Implement me :: version=" + version);
    }
  }

  private ElementName a0(String key) {
    return aimpl(key, noop());
  }

  private ElementName a1(String key) {
    return aimpl(key, PaddingLeft.v06);
  }

  private ElementName aimpl(String key, AValue level) {
    var selected = injector.$isCurrentKey(key);

    var bg = selected ? BackgroundColor.slate200 : BackgroundColor.hover.gray100;
    var fc = selected ? TextColor.black : noop();

    var href = injector.$href(key);

    var text = injector.$title(key).toc();

    return a(
      bg, fc, level,
      PaddingX.v03,
      PaddingY.v01,
      Display.block,

      href(href), raw(text)
    );
  }

  private ElementName h2v0(String text) {
    return h2(
      FontWeight.semibold,
      PaddingBottom.v01,
      PaddingTop.v06,
      PaddingX.v03,
      TextColor.black,
      TextTransform.uppercase,

      t(text)
    );
  }

}