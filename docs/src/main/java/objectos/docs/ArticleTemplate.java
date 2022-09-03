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

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderRight;
import br.com.objectos.css.framework.border.Rounded;
import br.com.objectos.css.framework.effects.Opacity;
import br.com.objectos.css.framework.flexbox.AlignItems;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.flexbox.FlexGrow;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.interactivity.Cursor;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.layout.Left;
import br.com.objectos.css.framework.layout.OverflowX;
import br.com.objectos.css.framework.layout.OverflowY;
import br.com.objectos.css.framework.layout.Position;
import br.com.objectos.css.framework.layout.Top;
import br.com.objectos.css.framework.layout.ZIndex;
import br.com.objectos.css.framework.sizing.Height;
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
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextTransform;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.element.StandardElementName;
import br.com.objectos.html.spi.type.AValue;
import objectos.asciidoc.DocumentAttributes;
import objectos.shared.DefaultRenderer;
import objectos.shared.JavaRenderer;
import objectos.shared.LanguageRenderer;
import objectos.shared.XmlRenderer;

final class ArticleTemplate extends DocsTemplate implements LanguageRenderer.Output {

  private static final IdSelector BACKDROP = Css.randomHash(3);

  private static final IdSelector CLICK_CLOSE = Css.randomHash(3);

  private static final IdSelector CLICK_OPEN = Css.randomHash(3);

  private static final IdSelector NAV = Css.randomHash(3);

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
  final void head0() {
    super.head0();

    script(raw("""
    function onClick(id, listener) {
      const el = document.getElementById(id);

      el.addEventListener("click", listener);
    }

    function setStyle(id, propName, value) {
      const el = document.getElementById(id);

      el.style[propName] = value;
    }

    function menuCloseClicked(event) {
      setStyle("{backdrop}", "display", null);
      setStyle("{body}", "overflow", null);
      setStyle("{menuClose}", "display", null);
      setStyle("{menuOpen}", "display", null);
      setStyle("{leftPanel}", "display", null);
    }

    function menuOpenClicked(event) {
      setStyle("{backdrop}", "display", "block");
      setStyle("{body}", "overflow", "hidden");
      setStyle("{menuClose}", "display", "block");
      setStyle("{menuOpen}", "display", "none");
      setStyle("{leftPanel}", "display", "block");
    }

    function domLoaded() {
      onClick("{backdrop}", menuCloseClicked);
      onClick("{menuClose}", menuCloseClicked);
      onClick("{menuOpen}", menuOpenClicked);
    }

    window.addEventListener('DOMContentLoaded', domLoaded);
    """
        .replace("{backdrop}", BACKDROP.id())
        .replace("{body}", BODY.id())
        .replace("{menuClose}", CLICK_CLOSE.id())
        .replace("{menuOpen}", CLICK_OPEN.id())
        .replace("{leftPanel}", NAV.id())));
  }

  @Override
  final void main0() {
    var version = injector.$version();

    if (version.status == Status.DEVELOPMENT) {
      div(
        BackgroundColor.orange100,
        PaddingY.v02,

        div(
          FontSize.small,
          MarginX.auto,
          MaxWidth.screenX2l,
          PaddingX.v04,
          TextAlign.center,

          p("""
          You are reading the documentation for the unreleased development version of Objectos.

          Please note that this is a work in progress. It might be incomplete and may
          change without notice.""")
        )
      );
    }

    div(
      div(
        BACKDROP,

        BackgroundColor.black,
        Display.hidden,
        Opacity.v050,
        Position.fixed,
        Top.v0,

        Height.screen,
        Width.screen,
        ZIndex.v10
      ),

      span(
        CLICK_OPEN,

        Cursor.hover.pointer,
        Display.inlineBlock,
        Display.lg.hidden,
        Padding.v01,
        Position.absolute,

        Left.v03,
        Top.v04,

        svg(
          Display.inlineBlock,
          Height.v06,
          Width.v06,

          xmlns("http://www.w3.org/2000/svg"),
          width("16"),
          height("16"),
          fill("currentColor"),
          viewBox("0 0 16 16"),
          path(
            fillRule("evenodd"),
            d("M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z")
          )
        )
      )
    );

    div(
      FlexGrow.one,

      div(
        AlignItems.stretch,
        Display.flex,
        MarginX.auto,
        MaxWidth.screenX2l,
        MinHeight.full,

        nav(
          NAV,

          // hidden
          BackgroundColor.white,
          Display.hidden,
          Height.screen,
          OverflowY.auto,
          Position.fixed,
          Top.v0,
          Width.v72,
          ZIndex.v20,

          // shown
          Display.lg.block,
          Height.lg.auto,
          Position.lg.staticPosition,

          // common
          BorderColor.lg.slate200,
          BorderRight.lg.v1,
          Flex.lg.none,
          FontSize.small,
          FontWeight.normal,
          PaddingBottom.v24, // to offset bottombar
          PaddingLeft.lg.v02,
          PaddingRight.lg.v02,
          TextColor.stone800,
          Width.lg.v56,
          DocsCss.XL_WIDTH_70,

          f(this::_leftBar)
        ),

        main(
          FlexGrow.one,
          MinWidth.v0,
          PaddingBottom.v24, // to offset bottombar
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
      BorderColor.slate200,
      BorderBottom.v1,
      Display.flex,
      Display.lg.hidden,
      FlexDirection.rowReverse,
      Height.v16,

      svg(
        CLICK_CLOSE,
        Display.block,
        Padding.v02,
        Height.v12,
        Width.v12,

        xmlns("http://www.w3.org/2000/svg"),
        width("16"),
        height("16"),
        fill("currentColor"),
        viewBox("0 0 16 16"),
        path(
          d("M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z")
        )
      )
    );

    div(
      AlignItems.center,
      Display.flex,
      JustifyContent.between,
      MarginBottom.v01,
      PaddingX.v03,
      PaddingTop.v06,

      div("Version " + version.name),

      a(
        BackgroundColor.gray400,
        BackgroundColor.hover.gray500,
        FontSize.xSmall,
        PaddingX.v02,
        PaddingY.v01,
        Rounded.standard,
        TextColor.white,

        href(injector.$record("versions").location().href()), t("Change")
      )
    );

    a(
      BackgroundColor.hover.gray100,
      Display.flex,
      AlignItems.center,
      JustifyContent.between,
      MarginBottom.v04,
      PaddingX.v03,
      PaddingY.v01,

      href(injector.$elink(version.directory + "/api/index")),

      span(
        t("Javadocs")
      ),

      svg(
        xmlns("http://www.w3.org/2000/svg"),
        width("16"),
        height("16"),
        fill("currentColor"),
        viewBox("0 0 16 16"),

        path(fillRule("evenodd"),
          d("M8.636 3.5a.5.5 0 0 0-.5-.5H1.5A1.5 1.5 0 0 0 0 4.5v10A1.5 1.5 0 0 0 1.5 16h10a1.5 1.5 0 0 0 1.5-1.5V7.864a.5.5 0 0 0-1 0V14.5a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5v-10a.5.5 0 0 1 .5-.5h6.636a.5.5 0 0 0 .5-.5z")),
        path(fillRule("evenodd"),
          d("M16 .5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h3.793L6.146 9.146a.5.5 0 1 0 .708.708L15 1.707V5.5a.5.5 0 0 0 1 0v-5z"))
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
    var record = injector.$record(key);

    var href = record.location().href();

    var text = record.title().toc();

    var selected = injector.$isCurrentKey(key);

    var bg = selected ? BackgroundColor.slate200 : BackgroundColor.hover.gray100;
    var fc = selected ? TextColor.black : noop();

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