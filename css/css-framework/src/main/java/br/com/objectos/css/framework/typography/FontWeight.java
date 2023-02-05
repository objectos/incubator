/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class FontWeight extends AbstractStyleSheet {

  public static final ClassSelector hairline = Css.randomDot(5);

  public static final ClassSelector thin = Css.randomDot(5);

  public static final ClassSelector light = Css.randomDot(5);

  public static final ClassSelector normal = Css.randomDot(5);

  public static final ClassSelector medium = Css.randomDot(5);

  public static final ClassSelector semibold = Css.randomDot(5);

  public static final ClassSelector bold = Css.randomDot(5);

  public static final ClassSelector extrabold = Css.randomDot(5);

  public static final ClassSelector black = Css.randomDot(5);

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
        hairline,
        fontWeight(100)
    );
    style(
        thin,
        fontWeight(200)
    );
    style(
        light,
        fontWeight(300)
    );
    style(
        normal,
        fontWeight(400)
    );
    style(
        medium,
        fontWeight(500)
    );
    style(
        semibold,
        fontWeight(600)
    );
    style(
        bold,
        fontWeight(700)
    );
    style(
        extrabold,
        fontWeight(800)
    );
    style(
        black,
        fontWeight(900)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.hairline,
            fontWeight(100)
        ),

        style(
            sm.thin,
            fontWeight(200)
        ),

        style(
            sm.light,
            fontWeight(300)
        ),

        style(
            sm.normal,
            fontWeight(400)
        ),

        style(
            sm.medium,
            fontWeight(500)
        ),

        style(
            sm.semibold,
            fontWeight(600)
        ),

        style(
            sm.bold,
            fontWeight(700)
        ),

        style(
            sm.extrabold,
            fontWeight(800)
        ),

        style(
            sm.black,
            fontWeight(900)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.hairline,
            fontWeight(100)
        ),

        style(
            md.thin,
            fontWeight(200)
        ),

        style(
            md.light,
            fontWeight(300)
        ),

        style(
            md.normal,
            fontWeight(400)
        ),

        style(
            md.medium,
            fontWeight(500)
        ),

        style(
            md.semibold,
            fontWeight(600)
        ),

        style(
            md.bold,
            fontWeight(700)
        ),

        style(
            md.extrabold,
            fontWeight(800)
        ),

        style(
            md.black,
            fontWeight(900)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.hairline,
            fontWeight(100)
        ),

        style(
            lg.thin,
            fontWeight(200)
        ),

        style(
            lg.light,
            fontWeight(300)
        ),

        style(
            lg.normal,
            fontWeight(400)
        ),

        style(
            lg.medium,
            fontWeight(500)
        ),

        style(
            lg.semibold,
            fontWeight(600)
        ),

        style(
            lg.bold,
            fontWeight(700)
        ),

        style(
            lg.extrabold,
            fontWeight(800)
        ),

        style(
            lg.black,
            fontWeight(900)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.hairline,
            fontWeight(100)
        ),

        style(
            xl.thin,
            fontWeight(200)
        ),

        style(
            xl.light,
            fontWeight(300)
        ),

        style(
            xl.normal,
            fontWeight(400)
        ),

        style(
            xl.medium,
            fontWeight(500)
        ),

        style(
            xl.semibold,
            fontWeight(600)
        ),

        style(
            xl.bold,
            fontWeight(700)
        ),

        style(
            xl.extrabold,
            fontWeight(800)
        ),

        style(
            xl.black,
            fontWeight(900)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.hairline,
            fontWeight(100)
        ),

        style(
            x2l.thin,
            fontWeight(200)
        ),

        style(
            x2l.light,
            fontWeight(300)
        ),

        style(
            x2l.normal,
            fontWeight(400)
        ),

        style(
            x2l.medium,
            fontWeight(500)
        ),

        style(
            x2l.semibold,
            fontWeight(600)
        ),

        style(
            x2l.bold,
            fontWeight(700)
        ),

        style(
            x2l.extrabold,
            fontWeight(800)
        ),

        style(
            x2l.black,
            fontWeight(900)
        )
    );
  }

  public interface sm {

    ClassSelector hairline = Css.randomDot(5);

    ClassSelector thin = Css.randomDot(5);

    ClassSelector light = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector semibold = Css.randomDot(5);

    ClassSelector bold = Css.randomDot(5);

    ClassSelector extrabold = Css.randomDot(5);

    ClassSelector black = Css.randomDot(5);

  }

  public interface md {

    ClassSelector hairline = Css.randomDot(5);

    ClassSelector thin = Css.randomDot(5);

    ClassSelector light = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector semibold = Css.randomDot(5);

    ClassSelector bold = Css.randomDot(5);

    ClassSelector extrabold = Css.randomDot(5);

    ClassSelector black = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector hairline = Css.randomDot(5);

    ClassSelector thin = Css.randomDot(5);

    ClassSelector light = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector semibold = Css.randomDot(5);

    ClassSelector bold = Css.randomDot(5);

    ClassSelector extrabold = Css.randomDot(5);

    ClassSelector black = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector hairline = Css.randomDot(5);

    ClassSelector thin = Css.randomDot(5);

    ClassSelector light = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector semibold = Css.randomDot(5);

    ClassSelector bold = Css.randomDot(5);

    ClassSelector extrabold = Css.randomDot(5);

    ClassSelector black = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector hairline = Css.randomDot(5);

    ClassSelector thin = Css.randomDot(5);

    ClassSelector light = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector semibold = Css.randomDot(5);

    ClassSelector bold = Css.randomDot(5);

    ClassSelector extrabold = Css.randomDot(5);

    ClassSelector black = Css.randomDot(5);

  }

}