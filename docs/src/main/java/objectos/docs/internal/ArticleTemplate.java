/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.internal;

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderRight;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.effects.Opacity;
import br.com.objectos.css.framework.flexbox.AlignItems;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.flexbox.FlexGrow;
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
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import objectos.asciidoc.DocumentAttributes;
import objectos.html.tmpl.StandardElementName;
import objectos.shared.DefaultRenderer;
import objectos.shared.JavaRenderer;
import objectos.shared.LanguageRenderer;
import objectos.shared.XmlRenderer;

public final class ArticleTemplate extends DocsTemplate implements LanguageRenderer.Output {

  private static final IdSelector BACKDROP = Css.randomHash(3);

  static final IdSelector CLICK_CLOSE = Css.randomHash(3);

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

  public static void initArticleTemplate() {
  }

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

      case "html", "xml" -> xmlRenderer;

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
    (function() {
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
    })();
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
        BorderColor.slate200,
        BorderTop.v1,
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

    if (version.status == Status.UNSUPPORTED) {
      div(
        BackgroundColor.red100,
        BorderColor.slate200,
        BorderTop.v1,
        PaddingY.v02,

        div(
          FontSize.small,
          MarginX.auto,
          MaxWidth.screenX2l,
          PaddingX.v04,
          TextAlign.center,

          p("""
          You are reading the documentation of an old and unsupported version of Objectos.""")
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
      BorderColor.slate200,
      BorderTop.v1,
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

          injector.$leftBar()
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

}