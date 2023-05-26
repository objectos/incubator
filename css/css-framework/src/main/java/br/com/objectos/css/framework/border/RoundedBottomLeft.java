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
package br.com.objectos.css.framework.border;

import br.com.objectos.code.annotations.Generated;
import objectos.css.Css;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class RoundedBottomLeft extends AbstractStyleSheet {

  public static final ClassSelector none = Css.randomDot(5);

  public static final ClassSelector small = Css.randomDot(5);

  public static final ClassSelector standard = Css.randomDot(5);

  public static final ClassSelector medium = Css.randomDot(5);

  public static final ClassSelector large = Css.randomDot(5);

  public static final ClassSelector full = Css.randomDot(5);

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
        none,
        borderBottomLeftRadius(zero())
    );
    style(
        small,
        borderBottomLeftRadius(em(0.125))
    );
    style(
        standard,
        borderBottomLeftRadius(em(0.25))
    );
    style(
        medium,
        borderBottomLeftRadius(em(0.375))
    );
    style(
        large,
        borderBottomLeftRadius(em(0.5))
    );
    style(
        full,
        borderBottomLeftRadius(px(9999))
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            sm.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            sm.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            sm.full,
            borderBottomLeftRadius(px(9999))
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            md.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            md.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            md.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            md.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            md.full,
            borderBottomLeftRadius(px(9999))
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            lg.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            lg.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            lg.full,
            borderBottomLeftRadius(px(9999))
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            xl.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            xl.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            xl.full,
            borderBottomLeftRadius(px(9999))
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            x2l.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            x2l.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            x2l.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            x2l.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            x2l.full,
            borderBottomLeftRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface md {

    ClassSelector none = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector none = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector none = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector none = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

}