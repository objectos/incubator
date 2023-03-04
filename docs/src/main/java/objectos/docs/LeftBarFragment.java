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
package objectos.docs;

import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.Rounded;
import br.com.objectos.css.framework.flexbox.AlignItems;
import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.interactivity.Cursor;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.sizing.Height;
import br.com.objectos.css.framework.sizing.Width;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.Padding;
import br.com.objectos.css.framework.spacing.PaddingBottom;
import br.com.objectos.css.framework.spacing.PaddingLeft;
import br.com.objectos.css.framework.spacing.PaddingTop;
import br.com.objectos.css.framework.spacing.PaddingX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.FontWeight;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextTransform;
import objectos.html.AbstractFragment;
import objectos.html.tmpl.AValue;
import objectos.html.tmpl.ElementName;

abstract class LeftBarFragment extends AbstractFragment {

  private final LeftBar injector;

  public LeftBarFragment(LeftBar injector) { this.injector = injector; }

  protected final void definition() {
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
        ArticleTemplate.CLICK_CLOSE,

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

    definitionImpl();
  }

  final ElementName a0(String key) {
    return aimpl(key, noop());
  }

  final ElementName a0(String key, String text) {
    return aimpl(key, text, noop());
  }

  final ElementName a1(String key) {
    return aimpl(key, PaddingLeft.v06);
  }

  abstract void definitionImpl();

  final ElementName h2v0(String text) {
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

  private ElementName aimpl(String key, String text, AValue level) {
    var record = injector.$record(key);

    var href = record.location().href();

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

}