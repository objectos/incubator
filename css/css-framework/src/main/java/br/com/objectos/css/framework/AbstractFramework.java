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
 *
 * Based on Tailwind CSS
 *
 * MIT License
 *
 * Copyright (c) Tailwind Labs, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * Footer
 */
package br.com.objectos.css.framework;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.background.BackgroundPosition;
import br.com.objectos.css.framework.background.BackgroundSize;
import br.com.objectos.css.framework.border.Border;
import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderBottomColor;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderLeft;
import br.com.objectos.css.framework.border.BorderLeftColor;
import br.com.objectos.css.framework.border.BorderRight;
import br.com.objectos.css.framework.border.BorderRightColor;
import br.com.objectos.css.framework.border.BorderStyle;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.border.BorderTopColor;
import br.com.objectos.css.framework.border.BorderX;
import br.com.objectos.css.framework.border.BorderXColor;
import br.com.objectos.css.framework.border.BorderY;
import br.com.objectos.css.framework.border.BorderYColor;
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
import br.com.objectos.css.framework.effects.Opacity;
import br.com.objectos.css.framework.flexbox.AlignContent;
import br.com.objectos.css.framework.flexbox.AlignItems;
import br.com.objectos.css.framework.flexbox.AlignSelf;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.flexbox.FlexGrow;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.interactivity.Cursor;
import br.com.objectos.css.framework.layout.Bottom;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.layout.Float;
import br.com.objectos.css.framework.layout.Inset;
import br.com.objectos.css.framework.layout.InsetX;
import br.com.objectos.css.framework.layout.InsetY;
import br.com.objectos.css.framework.layout.Left;
import br.com.objectos.css.framework.layout.ObjectFit;
import br.com.objectos.css.framework.layout.Overflow;
import br.com.objectos.css.framework.layout.OverflowX;
import br.com.objectos.css.framework.layout.OverflowY;
import br.com.objectos.css.framework.layout.Position;
import br.com.objectos.css.framework.layout.Right;
import br.com.objectos.css.framework.layout.Top;
import br.com.objectos.css.framework.layout.ZIndex;
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
import br.com.objectos.css.framework.typography.LetterSpacing;
import br.com.objectos.css.framework.typography.ListStylePosition;
import br.com.objectos.css.framework.typography.ListStyleType;
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextDecoration;
import br.com.objectos.css.framework.typography.TextTransform;
import br.com.objectos.css.framework.typography.VerticalAlign;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
abstract class AbstractFramework extends AbstractStyleSheet {

  @Override
  protected void definition() {
    background();
    border();
    effects();
    flexbox();
    interactivity();
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
    install(new BorderXColor());
    install(new BorderYColor());
    install(new BorderTopColor());
    install(new BorderRightColor());
    install(new BorderBottomColor());
    install(new BorderLeftColor());
    install(new BorderStyle());
    install(new BorderStyle());
    install(new Border());
    install(new BorderX());
    install(new BorderY());
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
    install(new Opacity());
  }

  protected void flexbox() {
    install(new AlignContent());
    install(new AlignItems());
    install(new AlignSelf());
    install(new Flex());
    install(new FlexDirection());
    install(new FlexGrow());
    install(new JustifyContent());
  }

  protected void interactivity() {
    install(new Cursor());
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
    install(new ObjectFit());
    install(new Overflow());
    install(new OverflowX());
    install(new OverflowY());
    install(new Position());
    install(new ZIndex());
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
    install(new LetterSpacing());
    install(new ListStylePosition());
    install(new ListStyleType());
    install(new TextAlign());
    install(new TextColor());
    install(new TextDecoration());
    install(new TextTransform());
    install(new VerticalAlign());
  }

}