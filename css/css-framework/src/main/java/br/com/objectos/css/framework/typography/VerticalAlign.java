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
package br.com.objectos.css.framework.typography;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class VerticalAlign extends AbstractStyleSheet {

  public static final ClassSelector baseline = Css.randomDot(5);

  public static final ClassSelector top = Css.randomDot(5);

  public static final ClassSelector middle = Css.randomDot(5);

  public static final ClassSelector bottom = Css.randomDot(5);

  public static final ClassSelector textTop = Css.randomDot(5);

  public static final ClassSelector textBottom = Css.randomDot(5);

  public static final ClassSelector subAlign = Css.randomDot(5);

  public static final ClassSelector superAlign = Css.randomDot(5);

  @Override
  protected final void definition() {
    definition0();
    definition1();
    definition2();
    definition3();
    definition4();
    definition5();
  }

  private void definition0() {
    style(
        baseline,
        verticalAlign(Keywords.baseline)
    );
    style(
        top,
        verticalAlign(Keywords.top)
    );
    style(
        middle,
        verticalAlign(Keywords.middle)
    );
    style(
        bottom,
        verticalAlign(Keywords.bottom)
    );
    style(
        textTop,
        verticalAlign(Keywords.textTop)
    );
    style(
        textBottom,
        verticalAlign(Keywords.textBottom)
    );
    style(
        subAlign,
        verticalAlign(Keywords.subKw)
    );
    style(
        superAlign,
        verticalAlign(Keywords.superKw)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.baseline,
            verticalAlign(Keywords.baseline)
        ),

        style(
            sm.top,
            verticalAlign(Keywords.top)
        ),

        style(
            sm.middle,
            verticalAlign(Keywords.middle)
        ),

        style(
            sm.bottom,
            verticalAlign(Keywords.bottom)
        ),

        style(
            sm.textTop,
            verticalAlign(Keywords.textTop)
        ),

        style(
            sm.textBottom,
            verticalAlign(Keywords.textBottom)
        ),

        style(
            sm.subAlign,
            verticalAlign(Keywords.subKw)
        ),

        style(
            sm.superAlign,
            verticalAlign(Keywords.superKw)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.baseline,
            verticalAlign(Keywords.baseline)
        ),

        style(
            md.top,
            verticalAlign(Keywords.top)
        ),

        style(
            md.middle,
            verticalAlign(Keywords.middle)
        ),

        style(
            md.bottom,
            verticalAlign(Keywords.bottom)
        ),

        style(
            md.textTop,
            verticalAlign(Keywords.textTop)
        ),

        style(
            md.textBottom,
            verticalAlign(Keywords.textBottom)
        ),

        style(
            md.subAlign,
            verticalAlign(Keywords.subKw)
        ),

        style(
            md.superAlign,
            verticalAlign(Keywords.superKw)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.baseline,
            verticalAlign(Keywords.baseline)
        ),

        style(
            lg.top,
            verticalAlign(Keywords.top)
        ),

        style(
            lg.middle,
            verticalAlign(Keywords.middle)
        ),

        style(
            lg.bottom,
            verticalAlign(Keywords.bottom)
        ),

        style(
            lg.textTop,
            verticalAlign(Keywords.textTop)
        ),

        style(
            lg.textBottom,
            verticalAlign(Keywords.textBottom)
        ),

        style(
            lg.subAlign,
            verticalAlign(Keywords.subKw)
        ),

        style(
            lg.superAlign,
            verticalAlign(Keywords.superKw)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.baseline,
            verticalAlign(Keywords.baseline)
        ),

        style(
            xl.top,
            verticalAlign(Keywords.top)
        ),

        style(
            xl.middle,
            verticalAlign(Keywords.middle)
        ),

        style(
            xl.bottom,
            verticalAlign(Keywords.bottom)
        ),

        style(
            xl.textTop,
            verticalAlign(Keywords.textTop)
        ),

        style(
            xl.textBottom,
            verticalAlign(Keywords.textBottom)
        ),

        style(
            xl.subAlign,
            verticalAlign(Keywords.subKw)
        ),

        style(
            xl.superAlign,
            verticalAlign(Keywords.superKw)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.baseline,
            verticalAlign(Keywords.baseline)
        ),

        style(
            x2l.top,
            verticalAlign(Keywords.top)
        ),

        style(
            x2l.middle,
            verticalAlign(Keywords.middle)
        ),

        style(
            x2l.bottom,
            verticalAlign(Keywords.bottom)
        ),

        style(
            x2l.textTop,
            verticalAlign(Keywords.textTop)
        ),

        style(
            x2l.textBottom,
            verticalAlign(Keywords.textBottom)
        ),

        style(
            x2l.subAlign,
            verticalAlign(Keywords.subKw)
        ),

        style(
            x2l.superAlign,
            verticalAlign(Keywords.superKw)
        )
    );
  }

  public interface sm {

    ClassSelector baseline = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

    ClassSelector middle = Css.randomDot(5);

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector textTop = Css.randomDot(5);

    ClassSelector textBottom = Css.randomDot(5);

    ClassSelector subAlign = Css.randomDot(5);

    ClassSelector superAlign = Css.randomDot(5);

  }

  public interface md {

    ClassSelector baseline = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

    ClassSelector middle = Css.randomDot(5);

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector textTop = Css.randomDot(5);

    ClassSelector textBottom = Css.randomDot(5);

    ClassSelector subAlign = Css.randomDot(5);

    ClassSelector superAlign = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector baseline = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

    ClassSelector middle = Css.randomDot(5);

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector textTop = Css.randomDot(5);

    ClassSelector textBottom = Css.randomDot(5);

    ClassSelector subAlign = Css.randomDot(5);

    ClassSelector superAlign = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector baseline = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

    ClassSelector middle = Css.randomDot(5);

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector textTop = Css.randomDot(5);

    ClassSelector textBottom = Css.randomDot(5);

    ClassSelector subAlign = Css.randomDot(5);

    ClassSelector superAlign = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector baseline = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

    ClassSelector middle = Css.randomDot(5);

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector textTop = Css.randomDot(5);

    ClassSelector textBottom = Css.randomDot(5);

    ClassSelector subAlign = Css.randomDot(5);

    ClassSelector superAlign = Css.randomDot(5);

  }

}