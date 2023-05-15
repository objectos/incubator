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
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextTransform;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import objectos.docs.internal.Navigation.Element;
import objectos.docs.internal.Navigation.Link;
import objectos.docs.internal.Navigation.LinkList;
import objectos.docs.internal.Navigation.LinkTitle;
import objectos.docs.internal.Navigation.Section;
import objectos.shared.LanguageRenderer;

public final class ArticleTemplate2 extends DocsTemplate2 implements LanguageRenderer.Output {

  private static final IdSelector BACKDROP = Css.randomHash(3);

  static final IdSelector CLICK_CLOSE = Css.randomHash(3);

  private static final IdSelector CLICK_OPEN = Css.randomHash(3);

  private static final IdSelector NAV = Css.randomHash(3);

  @SuppressWarnings("unused")
  private static final ClassSelector MY_DEFAULT = MarginY.v03;

  private Navigation navigation;

  ArticleTemplate2(DocsInjector injector) {
    super(injector);
  }

  public static void initArticleTemplate() {
  }

  @Override
  public void languageSpan(ClassSelector clazz, String contents) {}

  @Override
  public void languageText(String text) {}

  @Override
  final void head0() {
    super.head0();

    script("""
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
        .replace("{leftPanel}", NAV.id()));
  }

  @Override
  final void main0() {
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

          f(this::leftBar)
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

  private void leftBar() {
    div(
      AlignItems.center,
      BorderColor.slate200,
      BorderBottom.v1,
      Display.flex,
      Display.lg.hidden,
      FlexDirection.rowReverse,
      Height.v16,

      svg(
        ArticleTemplate2.CLICK_CLOSE,

        Cursor.hover.pointer,
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

        pathTo("/versions.html"), t("Change")
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

      pathTo("/" + version.directory + "/api/index.html"),

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

    navigation = version.getNavigation(key);

    ul(f(this::leftBar0));
  }

  private void leftBar0() {
    for (var element : navigation.elements) {
      leftBar1(element);
    }
  }

  private void leftBar1(Element element) {
    if (element instanceof Link link) {
      li(
        leftBarLink(link.iref(), null, noop())
      );
    } else if (element instanceof LinkList linkList) {
      li(
        leftBarLink(linkList.iref(), null, noop()),

        f(() -> {
          for (var child : linkList.elements()) {
            leftBar2(child);
          }
        })
      );
    } else if (element instanceof LinkTitle linkTitle) {
      li(
        leftBarLink(linkTitle.iref(), linkTitle.title(), noop())
      );
    } else if (element instanceof Section section) {
      li(
        leftBarHeading2(section.name()),

        f(() -> {
          for (var child : section.elements()) {
            leftBar1(child);
          }
        })
      );
    }
  }

  private void leftBar2(Element element) {
    if (element instanceof Link link) {
      li(
        leftBarLink(link.iref(), null, PaddingLeft.v06)
      );
    } else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  private ElementContents leftBarHeading2(String text) {
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

  private ElementContents leftBarLink(String iref, String text, AnchorInstruction level) {
    var href = version.leftBarLink(iref);

    if (text == null) {
      var record = injector.$record(href);

      text = record.title().toc();
    }

    var selected = key.equals(href);

    var bg = selected ? BackgroundColor.slate200 : BackgroundColor.hover.gray100;
    var fc = selected ? TextColor.black : noop();

    return a(
      bg, fc, level,
      PaddingX.v03,
      PaddingY.v01,
      Display.block,

      pathTo(href), raw(text)
    );
  }

}