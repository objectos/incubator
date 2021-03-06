/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.framework;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.background.BackgroundPosition;
import br.com.objectos.css.framework.background.BackgroundSize;
import br.com.objectos.css.framework.border.Border;
import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderLeft;
import br.com.objectos.css.framework.border.BorderRight;
import br.com.objectos.css.framework.border.BorderStyle;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.border.Rounded;
import br.com.objectos.css.framework.border.RoundedBottom;
import br.com.objectos.css.framework.border.RoundedBottomLeft;
import br.com.objectos.css.framework.border.RoundedBottomRight;
import br.com.objectos.css.framework.border.RoundedLeft;
import br.com.objectos.css.framework.border.RoundedRight;
import br.com.objectos.css.framework.border.RoundedTop;
import br.com.objectos.css.framework.border.RoundedTopLeft;
import br.com.objectos.css.framework.border.RoundedTopRight;
import br.com.objectos.css.framework.effects.BoxShadow;
import br.com.objectos.css.framework.flexbox.AlignContent;
import br.com.objectos.css.framework.flexbox.AlignItems;
import br.com.objectos.css.framework.flexbox.AlignSelf;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.layout.Bottom;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.layout.Float;
import br.com.objectos.css.framework.layout.Inset;
import br.com.objectos.css.framework.layout.InsetX;
import br.com.objectos.css.framework.layout.InsetY;
import br.com.objectos.css.framework.layout.Left;
import br.com.objectos.css.framework.layout.Overflow;
import br.com.objectos.css.framework.layout.OverflowX;
import br.com.objectos.css.framework.layout.OverflowY;
import br.com.objectos.css.framework.layout.Position;
import br.com.objectos.css.framework.layout.Right;
import br.com.objectos.css.framework.layout.Top;
import br.com.objectos.css.framework.sizing.Height;
import br.com.objectos.css.framework.sizing.MaxHeight;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.sizing.MinHeight;
import br.com.objectos.css.framework.sizing.MinWidth;
import br.com.objectos.css.framework.sizing.Width;
import br.com.objectos.css.framework.spacing.Margin;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.MarginLeft;
import br.com.objectos.css.framework.spacing.MarginRight;
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
import br.com.objectos.css.framework.typography.FontStyle;
import br.com.objectos.css.framework.typography.FontWeight;
import br.com.objectos.css.framework.typography.Leading;
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextDecoration;
import br.com.objectos.css.framework.typography.TextTransform;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
abstract class AbstractFramework extends AbstractStyleSheet {

  @Override
  protected void definition() {
    background();
    border();
    effects();
    flexbox();
    layout();
    sizing();
    spacing();
    typography();
  }

  protected void background() {
    install(new BackgroundColor());
    install(new BackgroundPosition());
    install(new BackgroundSize());
  }

  protected void border() {
    install(new BorderColor());
    install(new BorderStyle());
    install(new Border());
    install(new BorderTop());
    install(new BorderRight());
    install(new BorderBottom());
    install(new BorderLeft());
    install(new Rounded());
    install(new RoundedTop());
    install(new RoundedRight());
    install(new RoundedBottom());
    install(new RoundedLeft());
    install(new RoundedTopLeft());
    install(new RoundedTopRight());
    install(new RoundedBottomRight());
    install(new RoundedBottomLeft());
  }

  protected void effects() {
    install(new BoxShadow());
  }

  protected void flexbox() {
    install(new AlignContent());
    install(new AlignItems());
    install(new AlignSelf());
    install(new Flex());
    install(new JustifyContent());
  }

  protected void layout() {
    install(new Display());
    install(new Float());
    install(new Inset());
    install(new InsetX());
    install(new InsetY());
    install(new Top());
    install(new Right());
    install(new Bottom());
    install(new Left());
    install(new Overflow());
    install(new OverflowX());
    install(new OverflowY());
    install(new Position());
  }

  protected void sizing() {
    install(new Height());
    install(new MaxHeight());
    install(new MinHeight());
    install(new Width());
    install(new MaxWidth());
    install(new MinWidth());
  }

  protected void spacing() {
    install(new Margin());
    install(new MarginX());
    install(new MarginY());
    install(new MarginTop());
    install(new MarginRight());
    install(new MarginBottom());
    install(new MarginLeft());
    install(new Padding());
    install(new PaddingX());
    install(new PaddingY());
    install(new PaddingTop());
    install(new PaddingRight());
    install(new PaddingBottom());
    install(new PaddingLeft());
  }

  protected void typography() {
    install(new FontSize());
    install(new FontStyle());
    install(new FontWeight());
    install(new Leading());
    install(new TextAlign());
    install(new TextColor());
    install(new TextDecoration());
    install(new TextTransform());
  }

}