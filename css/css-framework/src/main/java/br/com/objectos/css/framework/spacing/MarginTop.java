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
package br.com.objectos.css.framework.spacing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class MarginTop extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector px = Css.randomDot(5);

  public static final ClassSelector zero = Css.randomDot(5);

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector v00_5 = Css.randomDot(5);

  public static final ClassSelector v01 = Css.randomDot(5);

  public static final ClassSelector v01_5 = Css.randomDot(5);

  public static final ClassSelector v02 = Css.randomDot(5);

  public static final ClassSelector v02_5 = Css.randomDot(5);

  public static final ClassSelector v03 = Css.randomDot(5);

  public static final ClassSelector v03_5 = Css.randomDot(5);

  public static final ClassSelector v04 = Css.randomDot(5);

  public static final ClassSelector v05 = Css.randomDot(5);

  public static final ClassSelector v06 = Css.randomDot(5);

  public static final ClassSelector v07 = Css.randomDot(5);

  public static final ClassSelector v08 = Css.randomDot(5);

  public static final ClassSelector v09 = Css.randomDot(5);

  public static final ClassSelector v10 = Css.randomDot(5);

  public static final ClassSelector v11 = Css.randomDot(5);

  public static final ClassSelector v12 = Css.randomDot(5);

  public static final ClassSelector v14 = Css.randomDot(5);

  public static final ClassSelector v16 = Css.randomDot(5);

  public static final ClassSelector v20 = Css.randomDot(5);

  public static final ClassSelector v24 = Css.randomDot(5);

  public static final ClassSelector v28 = Css.randomDot(5);

  public static final ClassSelector v32 = Css.randomDot(5);

  public static final ClassSelector v36 = Css.randomDot(5);

  public static final ClassSelector v40 = Css.randomDot(5);

  public static final ClassSelector v44 = Css.randomDot(5);

  public static final ClassSelector v48 = Css.randomDot(5);

  public static final ClassSelector v52 = Css.randomDot(5);

  public static final ClassSelector v56 = Css.randomDot(5);

  public static final ClassSelector v60 = Css.randomDot(5);

  public static final ClassSelector v64 = Css.randomDot(5);

  public static final ClassSelector v68 = Css.randomDot(5);

  public static final ClassSelector v72 = Css.randomDot(5);

  public static final ClassSelector v80 = Css.randomDot(5);

  public static final ClassSelector v96 = Css.randomDot(5);

  @Override
  protected final void definition() {
    definition0();
    definition1();
    definition2();
    definition3();
    definition4();
    definition5();
    definition6();
  }

  private void definition0() {
    style(
        auto,
        marginTop(Keywords.auto)
    );
    style(
        px,
        marginTop(px(1))
    );
    style(
        zero,
        marginTop(zero())
    );
    style(
        v0,
        marginTop(zero())
    );
    style(
        v00_5,
        marginTop(rem(0.125))
    );
    style(
        v01,
        marginTop(rem(0.25))
    );
    style(
        v01_5,
        marginTop(rem(0.375))
    );
    style(
        v02,
        marginTop(rem(0.5))
    );
    style(
        v02_5,
        marginTop(rem(0.625))
    );
    style(
        v03,
        marginTop(rem(0.75))
    );
    style(
        v03_5,
        marginTop(rem(0.875))
    );
    style(
        v04,
        marginTop(rem(1))
    );
    style(
        v05,
        marginTop(rem(1.25))
    );
    style(
        v06,
        marginTop(rem(1.5))
    );
    style(
        v07,
        marginTop(rem(1.75))
    );
    style(
        v08,
        marginTop(rem(2))
    );
    style(
        v09,
        marginTop(rem(2.25))
    );
    style(
        v10,
        marginTop(rem(2.5))
    );
    style(
        v11,
        marginTop(rem(2.75))
    );
    style(
        v12,
        marginTop(rem(3))
    );
    style(
        v14,
        marginTop(rem(3.5))
    );
    style(
        v16,
        marginTop(rem(4))
    );
    style(
        v20,
        marginTop(rem(5))
    );
    style(
        v24,
        marginTop(rem(6))
    );
    style(
        v28,
        marginTop(rem(7))
    );
    style(
        v32,
        marginTop(rem(8))
    );
    style(
        v36,
        marginTop(rem(9))
    );
    style(
        v40,
        marginTop(rem(10))
    );
    style(
        v44,
        marginTop(rem(11))
    );
    style(
        v48,
        marginTop(rem(12))
    );
    style(
        v52,
        marginTop(rem(13))
    );
    style(
        v56,
        marginTop(rem(14))
    );
    style(
        v60,
        marginTop(rem(15))
    );
    style(
        v64,
        marginTop(rem(16))
    );
    style(
        v68,
        marginTop(rem(17))
    );
    style(
        v72,
        marginTop(rem(18))
    );
    style(
        v80,
        marginTop(rem(20))
    );
    style(
        v96,
        marginTop(rem(24))
    );
  }

  private void definition1() {
    style(
        firstChild.auto, FIRST_CHILD,
        marginTop(Keywords.auto)
    );
    style(
        firstChild.px, FIRST_CHILD,
        marginTop(px(1))
    );
    style(
        firstChild.zero, FIRST_CHILD,
        marginTop(zero())
    );
    style(
        firstChild.v0, FIRST_CHILD,
        marginTop(zero())
    );
    style(
        firstChild.v00_5, FIRST_CHILD,
        marginTop(rem(0.125))
    );
    style(
        firstChild.v01, FIRST_CHILD,
        marginTop(rem(0.25))
    );
    style(
        firstChild.v01_5, FIRST_CHILD,
        marginTop(rem(0.375))
    );
    style(
        firstChild.v02, FIRST_CHILD,
        marginTop(rem(0.5))
    );
    style(
        firstChild.v02_5, FIRST_CHILD,
        marginTop(rem(0.625))
    );
    style(
        firstChild.v03, FIRST_CHILD,
        marginTop(rem(0.75))
    );
    style(
        firstChild.v03_5, FIRST_CHILD,
        marginTop(rem(0.875))
    );
    style(
        firstChild.v04, FIRST_CHILD,
        marginTop(rem(1))
    );
    style(
        firstChild.v05, FIRST_CHILD,
        marginTop(rem(1.25))
    );
    style(
        firstChild.v06, FIRST_CHILD,
        marginTop(rem(1.5))
    );
    style(
        firstChild.v07, FIRST_CHILD,
        marginTop(rem(1.75))
    );
    style(
        firstChild.v08, FIRST_CHILD,
        marginTop(rem(2))
    );
    style(
        firstChild.v09, FIRST_CHILD,
        marginTop(rem(2.25))
    );
    style(
        firstChild.v10, FIRST_CHILD,
        marginTop(rem(2.5))
    );
    style(
        firstChild.v11, FIRST_CHILD,
        marginTop(rem(2.75))
    );
    style(
        firstChild.v12, FIRST_CHILD,
        marginTop(rem(3))
    );
    style(
        firstChild.v14, FIRST_CHILD,
        marginTop(rem(3.5))
    );
    style(
        firstChild.v16, FIRST_CHILD,
        marginTop(rem(4))
    );
    style(
        firstChild.v20, FIRST_CHILD,
        marginTop(rem(5))
    );
    style(
        firstChild.v24, FIRST_CHILD,
        marginTop(rem(6))
    );
    style(
        firstChild.v28, FIRST_CHILD,
        marginTop(rem(7))
    );
    style(
        firstChild.v32, FIRST_CHILD,
        marginTop(rem(8))
    );
    style(
        firstChild.v36, FIRST_CHILD,
        marginTop(rem(9))
    );
    style(
        firstChild.v40, FIRST_CHILD,
        marginTop(rem(10))
    );
    style(
        firstChild.v44, FIRST_CHILD,
        marginTop(rem(11))
    );
    style(
        firstChild.v48, FIRST_CHILD,
        marginTop(rem(12))
    );
    style(
        firstChild.v52, FIRST_CHILD,
        marginTop(rem(13))
    );
    style(
        firstChild.v56, FIRST_CHILD,
        marginTop(rem(14))
    );
    style(
        firstChild.v60, FIRST_CHILD,
        marginTop(rem(15))
    );
    style(
        firstChild.v64, FIRST_CHILD,
        marginTop(rem(16))
    );
    style(
        firstChild.v68, FIRST_CHILD,
        marginTop(rem(17))
    );
    style(
        firstChild.v72, FIRST_CHILD,
        marginTop(rem(18))
    );
    style(
        firstChild.v80, FIRST_CHILD,
        marginTop(rem(20))
    );
    style(
        firstChild.v96, FIRST_CHILD,
        marginTop(rem(24))
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            marginTop(Keywords.auto)
        ),

        style(
            sm.px,
            marginTop(px(1))
        ),

        style(
            sm.zero,
            marginTop(zero())
        ),

        style(
            sm.v0,
            marginTop(zero())
        ),

        style(
            sm.v00_5,
            marginTop(rem(0.125))
        ),

        style(
            sm.v01,
            marginTop(rem(0.25))
        ),

        style(
            sm.v01_5,
            marginTop(rem(0.375))
        ),

        style(
            sm.v02,
            marginTop(rem(0.5))
        ),

        style(
            sm.v02_5,
            marginTop(rem(0.625))
        ),

        style(
            sm.v03,
            marginTop(rem(0.75))
        ),

        style(
            sm.v03_5,
            marginTop(rem(0.875))
        ),

        style(
            sm.v04,
            marginTop(rem(1))
        ),

        style(
            sm.v05,
            marginTop(rem(1.25))
        ),

        style(
            sm.v06,
            marginTop(rem(1.5))
        ),

        style(
            sm.v07,
            marginTop(rem(1.75))
        ),

        style(
            sm.v08,
            marginTop(rem(2))
        ),

        style(
            sm.v09,
            marginTop(rem(2.25))
        ),

        style(
            sm.v10,
            marginTop(rem(2.5))
        ),

        style(
            sm.v11,
            marginTop(rem(2.75))
        ),

        style(
            sm.v12,
            marginTop(rem(3))
        ),

        style(
            sm.v14,
            marginTop(rem(3.5))
        ),

        style(
            sm.v16,
            marginTop(rem(4))
        ),

        style(
            sm.v20,
            marginTop(rem(5))
        ),

        style(
            sm.v24,
            marginTop(rem(6))
        ),

        style(
            sm.v28,
            marginTop(rem(7))
        ),

        style(
            sm.v32,
            marginTop(rem(8))
        ),

        style(
            sm.v36,
            marginTop(rem(9))
        ),

        style(
            sm.v40,
            marginTop(rem(10))
        ),

        style(
            sm.v44,
            marginTop(rem(11))
        ),

        style(
            sm.v48,
            marginTop(rem(12))
        ),

        style(
            sm.v52,
            marginTop(rem(13))
        ),

        style(
            sm.v56,
            marginTop(rem(14))
        ),

        style(
            sm.v60,
            marginTop(rem(15))
        ),

        style(
            sm.v64,
            marginTop(rem(16))
        ),

        style(
            sm.v68,
            marginTop(rem(17))
        ),

        style(
            sm.v72,
            marginTop(rem(18))
        ),

        style(
            sm.v80,
            marginTop(rem(20))
        ),

        style(
            sm.v96,
            marginTop(rem(24))
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            marginTop(Keywords.auto)
        ),

        style(
            md.px,
            marginTop(px(1))
        ),

        style(
            md.zero,
            marginTop(zero())
        ),

        style(
            md.v0,
            marginTop(zero())
        ),

        style(
            md.v00_5,
            marginTop(rem(0.125))
        ),

        style(
            md.v01,
            marginTop(rem(0.25))
        ),

        style(
            md.v01_5,
            marginTop(rem(0.375))
        ),

        style(
            md.v02,
            marginTop(rem(0.5))
        ),

        style(
            md.v02_5,
            marginTop(rem(0.625))
        ),

        style(
            md.v03,
            marginTop(rem(0.75))
        ),

        style(
            md.v03_5,
            marginTop(rem(0.875))
        ),

        style(
            md.v04,
            marginTop(rem(1))
        ),

        style(
            md.v05,
            marginTop(rem(1.25))
        ),

        style(
            md.v06,
            marginTop(rem(1.5))
        ),

        style(
            md.v07,
            marginTop(rem(1.75))
        ),

        style(
            md.v08,
            marginTop(rem(2))
        ),

        style(
            md.v09,
            marginTop(rem(2.25))
        ),

        style(
            md.v10,
            marginTop(rem(2.5))
        ),

        style(
            md.v11,
            marginTop(rem(2.75))
        ),

        style(
            md.v12,
            marginTop(rem(3))
        ),

        style(
            md.v14,
            marginTop(rem(3.5))
        ),

        style(
            md.v16,
            marginTop(rem(4))
        ),

        style(
            md.v20,
            marginTop(rem(5))
        ),

        style(
            md.v24,
            marginTop(rem(6))
        ),

        style(
            md.v28,
            marginTop(rem(7))
        ),

        style(
            md.v32,
            marginTop(rem(8))
        ),

        style(
            md.v36,
            marginTop(rem(9))
        ),

        style(
            md.v40,
            marginTop(rem(10))
        ),

        style(
            md.v44,
            marginTop(rem(11))
        ),

        style(
            md.v48,
            marginTop(rem(12))
        ),

        style(
            md.v52,
            marginTop(rem(13))
        ),

        style(
            md.v56,
            marginTop(rem(14))
        ),

        style(
            md.v60,
            marginTop(rem(15))
        ),

        style(
            md.v64,
            marginTop(rem(16))
        ),

        style(
            md.v68,
            marginTop(rem(17))
        ),

        style(
            md.v72,
            marginTop(rem(18))
        ),

        style(
            md.v80,
            marginTop(rem(20))
        ),

        style(
            md.v96,
            marginTop(rem(24))
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            marginTop(Keywords.auto)
        ),

        style(
            lg.px,
            marginTop(px(1))
        ),

        style(
            lg.zero,
            marginTop(zero())
        ),

        style(
            lg.v0,
            marginTop(zero())
        ),

        style(
            lg.v00_5,
            marginTop(rem(0.125))
        ),

        style(
            lg.v01,
            marginTop(rem(0.25))
        ),

        style(
            lg.v01_5,
            marginTop(rem(0.375))
        ),

        style(
            lg.v02,
            marginTop(rem(0.5))
        ),

        style(
            lg.v02_5,
            marginTop(rem(0.625))
        ),

        style(
            lg.v03,
            marginTop(rem(0.75))
        ),

        style(
            lg.v03_5,
            marginTop(rem(0.875))
        ),

        style(
            lg.v04,
            marginTop(rem(1))
        ),

        style(
            lg.v05,
            marginTop(rem(1.25))
        ),

        style(
            lg.v06,
            marginTop(rem(1.5))
        ),

        style(
            lg.v07,
            marginTop(rem(1.75))
        ),

        style(
            lg.v08,
            marginTop(rem(2))
        ),

        style(
            lg.v09,
            marginTop(rem(2.25))
        ),

        style(
            lg.v10,
            marginTop(rem(2.5))
        ),

        style(
            lg.v11,
            marginTop(rem(2.75))
        ),

        style(
            lg.v12,
            marginTop(rem(3))
        ),

        style(
            lg.v14,
            marginTop(rem(3.5))
        ),

        style(
            lg.v16,
            marginTop(rem(4))
        ),

        style(
            lg.v20,
            marginTop(rem(5))
        ),

        style(
            lg.v24,
            marginTop(rem(6))
        ),

        style(
            lg.v28,
            marginTop(rem(7))
        ),

        style(
            lg.v32,
            marginTop(rem(8))
        ),

        style(
            lg.v36,
            marginTop(rem(9))
        ),

        style(
            lg.v40,
            marginTop(rem(10))
        ),

        style(
            lg.v44,
            marginTop(rem(11))
        ),

        style(
            lg.v48,
            marginTop(rem(12))
        ),

        style(
            lg.v52,
            marginTop(rem(13))
        ),

        style(
            lg.v56,
            marginTop(rem(14))
        ),

        style(
            lg.v60,
            marginTop(rem(15))
        ),

        style(
            lg.v64,
            marginTop(rem(16))
        ),

        style(
            lg.v68,
            marginTop(rem(17))
        ),

        style(
            lg.v72,
            marginTop(rem(18))
        ),

        style(
            lg.v80,
            marginTop(rem(20))
        ),

        style(
            lg.v96,
            marginTop(rem(24))
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            marginTop(Keywords.auto)
        ),

        style(
            xl.px,
            marginTop(px(1))
        ),

        style(
            xl.zero,
            marginTop(zero())
        ),

        style(
            xl.v0,
            marginTop(zero())
        ),

        style(
            xl.v00_5,
            marginTop(rem(0.125))
        ),

        style(
            xl.v01,
            marginTop(rem(0.25))
        ),

        style(
            xl.v01_5,
            marginTop(rem(0.375))
        ),

        style(
            xl.v02,
            marginTop(rem(0.5))
        ),

        style(
            xl.v02_5,
            marginTop(rem(0.625))
        ),

        style(
            xl.v03,
            marginTop(rem(0.75))
        ),

        style(
            xl.v03_5,
            marginTop(rem(0.875))
        ),

        style(
            xl.v04,
            marginTop(rem(1))
        ),

        style(
            xl.v05,
            marginTop(rem(1.25))
        ),

        style(
            xl.v06,
            marginTop(rem(1.5))
        ),

        style(
            xl.v07,
            marginTop(rem(1.75))
        ),

        style(
            xl.v08,
            marginTop(rem(2))
        ),

        style(
            xl.v09,
            marginTop(rem(2.25))
        ),

        style(
            xl.v10,
            marginTop(rem(2.5))
        ),

        style(
            xl.v11,
            marginTop(rem(2.75))
        ),

        style(
            xl.v12,
            marginTop(rem(3))
        ),

        style(
            xl.v14,
            marginTop(rem(3.5))
        ),

        style(
            xl.v16,
            marginTop(rem(4))
        ),

        style(
            xl.v20,
            marginTop(rem(5))
        ),

        style(
            xl.v24,
            marginTop(rem(6))
        ),

        style(
            xl.v28,
            marginTop(rem(7))
        ),

        style(
            xl.v32,
            marginTop(rem(8))
        ),

        style(
            xl.v36,
            marginTop(rem(9))
        ),

        style(
            xl.v40,
            marginTop(rem(10))
        ),

        style(
            xl.v44,
            marginTop(rem(11))
        ),

        style(
            xl.v48,
            marginTop(rem(12))
        ),

        style(
            xl.v52,
            marginTop(rem(13))
        ),

        style(
            xl.v56,
            marginTop(rem(14))
        ),

        style(
            xl.v60,
            marginTop(rem(15))
        ),

        style(
            xl.v64,
            marginTop(rem(16))
        ),

        style(
            xl.v68,
            marginTop(rem(17))
        ),

        style(
            xl.v72,
            marginTop(rem(18))
        ),

        style(
            xl.v80,
            marginTop(rem(20))
        ),

        style(
            xl.v96,
            marginTop(rem(24))
        )
    );
  }

  private void definition6() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.auto,
            marginTop(Keywords.auto)
        ),

        style(
            x2l.px,
            marginTop(px(1))
        ),

        style(
            x2l.zero,
            marginTop(zero())
        ),

        style(
            x2l.v0,
            marginTop(zero())
        ),

        style(
            x2l.v00_5,
            marginTop(rem(0.125))
        ),

        style(
            x2l.v01,
            marginTop(rem(0.25))
        ),

        style(
            x2l.v01_5,
            marginTop(rem(0.375))
        ),

        style(
            x2l.v02,
            marginTop(rem(0.5))
        ),

        style(
            x2l.v02_5,
            marginTop(rem(0.625))
        ),

        style(
            x2l.v03,
            marginTop(rem(0.75))
        ),

        style(
            x2l.v03_5,
            marginTop(rem(0.875))
        ),

        style(
            x2l.v04,
            marginTop(rem(1))
        ),

        style(
            x2l.v05,
            marginTop(rem(1.25))
        ),

        style(
            x2l.v06,
            marginTop(rem(1.5))
        ),

        style(
            x2l.v07,
            marginTop(rem(1.75))
        ),

        style(
            x2l.v08,
            marginTop(rem(2))
        ),

        style(
            x2l.v09,
            marginTop(rem(2.25))
        ),

        style(
            x2l.v10,
            marginTop(rem(2.5))
        ),

        style(
            x2l.v11,
            marginTop(rem(2.75))
        ),

        style(
            x2l.v12,
            marginTop(rem(3))
        ),

        style(
            x2l.v14,
            marginTop(rem(3.5))
        ),

        style(
            x2l.v16,
            marginTop(rem(4))
        ),

        style(
            x2l.v20,
            marginTop(rem(5))
        ),

        style(
            x2l.v24,
            marginTop(rem(6))
        ),

        style(
            x2l.v28,
            marginTop(rem(7))
        ),

        style(
            x2l.v32,
            marginTop(rem(8))
        ),

        style(
            x2l.v36,
            marginTop(rem(9))
        ),

        style(
            x2l.v40,
            marginTop(rem(10))
        ),

        style(
            x2l.v44,
            marginTop(rem(11))
        ),

        style(
            x2l.v48,
            marginTop(rem(12))
        ),

        style(
            x2l.v52,
            marginTop(rem(13))
        ),

        style(
            x2l.v56,
            marginTop(rem(14))
        ),

        style(
            x2l.v60,
            marginTop(rem(15))
        ),

        style(
            x2l.v64,
            marginTop(rem(16))
        ),

        style(
            x2l.v68,
            marginTop(rem(17))
        ),

        style(
            x2l.v72,
            marginTop(rem(18))
        ),

        style(
            x2l.v80,
            marginTop(rem(20))
        ),

        style(
            x2l.v96,
            marginTop(rem(24))
        )
    );
  }

  public interface firstChild {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v00_5 = Css.randomDot(5);

    ClassSelector v01 = Css.randomDot(5);

    ClassSelector v01_5 = Css.randomDot(5);

    ClassSelector v02 = Css.randomDot(5);

    ClassSelector v02_5 = Css.randomDot(5);

    ClassSelector v03 = Css.randomDot(5);

    ClassSelector v03_5 = Css.randomDot(5);

    ClassSelector v04 = Css.randomDot(5);

    ClassSelector v05 = Css.randomDot(5);

    ClassSelector v06 = Css.randomDot(5);

    ClassSelector v07 = Css.randomDot(5);

    ClassSelector v08 = Css.randomDot(5);

    ClassSelector v09 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v11 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v14 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v28 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v36 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v44 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v52 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v60 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

    ClassSelector v68 = Css.randomDot(5);

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v00_5 = Css.randomDot(5);

    ClassSelector v01 = Css.randomDot(5);

    ClassSelector v01_5 = Css.randomDot(5);

    ClassSelector v02 = Css.randomDot(5);

    ClassSelector v02_5 = Css.randomDot(5);

    ClassSelector v03 = Css.randomDot(5);

    ClassSelector v03_5 = Css.randomDot(5);

    ClassSelector v04 = Css.randomDot(5);

    ClassSelector v05 = Css.randomDot(5);

    ClassSelector v06 = Css.randomDot(5);

    ClassSelector v07 = Css.randomDot(5);

    ClassSelector v08 = Css.randomDot(5);

    ClassSelector v09 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v11 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v14 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v28 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v36 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v44 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v52 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v60 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

    ClassSelector v68 = Css.randomDot(5);

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v00_5 = Css.randomDot(5);

    ClassSelector v01 = Css.randomDot(5);

    ClassSelector v01_5 = Css.randomDot(5);

    ClassSelector v02 = Css.randomDot(5);

    ClassSelector v02_5 = Css.randomDot(5);

    ClassSelector v03 = Css.randomDot(5);

    ClassSelector v03_5 = Css.randomDot(5);

    ClassSelector v04 = Css.randomDot(5);

    ClassSelector v05 = Css.randomDot(5);

    ClassSelector v06 = Css.randomDot(5);

    ClassSelector v07 = Css.randomDot(5);

    ClassSelector v08 = Css.randomDot(5);

    ClassSelector v09 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v11 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v14 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v28 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v36 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v44 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v52 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v60 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

    ClassSelector v68 = Css.randomDot(5);

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v00_5 = Css.randomDot(5);

    ClassSelector v01 = Css.randomDot(5);

    ClassSelector v01_5 = Css.randomDot(5);

    ClassSelector v02 = Css.randomDot(5);

    ClassSelector v02_5 = Css.randomDot(5);

    ClassSelector v03 = Css.randomDot(5);

    ClassSelector v03_5 = Css.randomDot(5);

    ClassSelector v04 = Css.randomDot(5);

    ClassSelector v05 = Css.randomDot(5);

    ClassSelector v06 = Css.randomDot(5);

    ClassSelector v07 = Css.randomDot(5);

    ClassSelector v08 = Css.randomDot(5);

    ClassSelector v09 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v11 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v14 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v28 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v36 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v44 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v52 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v60 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

    ClassSelector v68 = Css.randomDot(5);

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v00_5 = Css.randomDot(5);

    ClassSelector v01 = Css.randomDot(5);

    ClassSelector v01_5 = Css.randomDot(5);

    ClassSelector v02 = Css.randomDot(5);

    ClassSelector v02_5 = Css.randomDot(5);

    ClassSelector v03 = Css.randomDot(5);

    ClassSelector v03_5 = Css.randomDot(5);

    ClassSelector v04 = Css.randomDot(5);

    ClassSelector v05 = Css.randomDot(5);

    ClassSelector v06 = Css.randomDot(5);

    ClassSelector v07 = Css.randomDot(5);

    ClassSelector v08 = Css.randomDot(5);

    ClassSelector v09 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v11 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v14 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v28 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v36 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v44 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v52 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v60 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

    ClassSelector v68 = Css.randomDot(5);

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v00_5 = Css.randomDot(5);

    ClassSelector v01 = Css.randomDot(5);

    ClassSelector v01_5 = Css.randomDot(5);

    ClassSelector v02 = Css.randomDot(5);

    ClassSelector v02_5 = Css.randomDot(5);

    ClassSelector v03 = Css.randomDot(5);

    ClassSelector v03_5 = Css.randomDot(5);

    ClassSelector v04 = Css.randomDot(5);

    ClassSelector v05 = Css.randomDot(5);

    ClassSelector v06 = Css.randomDot(5);

    ClassSelector v07 = Css.randomDot(5);

    ClassSelector v08 = Css.randomDot(5);

    ClassSelector v09 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v11 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v14 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v28 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v36 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v44 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v52 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v60 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

    ClassSelector v68 = Css.randomDot(5);

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

}