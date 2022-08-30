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
package br.com.objectos.css.framework.sizing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Width extends AbstractStyleSheet {

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

  public static final ClassSelector p1o2 = Css.randomDot(5);

  public static final ClassSelector p1o3 = Css.randomDot(5);

  public static final ClassSelector p2o3 = Css.randomDot(5);

  public static final ClassSelector p1o4 = Css.randomDot(5);

  public static final ClassSelector p2o4 = Css.randomDot(5);

  public static final ClassSelector p3o4 = Css.randomDot(5);

  public static final ClassSelector p1o5 = Css.randomDot(5);

  public static final ClassSelector p2o5 = Css.randomDot(5);

  public static final ClassSelector p3o5 = Css.randomDot(5);

  public static final ClassSelector p4o5 = Css.randomDot(5);

  public static final ClassSelector p1o6 = Css.randomDot(5);

  public static final ClassSelector p2o6 = Css.randomDot(5);

  public static final ClassSelector p3o6 = Css.randomDot(5);

  public static final ClassSelector p4o6 = Css.randomDot(5);

  public static final ClassSelector p5o6 = Css.randomDot(5);

  public static final ClassSelector p1o12 = Css.randomDot(5);

  public static final ClassSelector p2o12 = Css.randomDot(5);

  public static final ClassSelector p3o12 = Css.randomDot(5);

  public static final ClassSelector p4o12 = Css.randomDot(5);

  public static final ClassSelector p5o12 = Css.randomDot(5);

  public static final ClassSelector p6o12 = Css.randomDot(5);

  public static final ClassSelector p7o12 = Css.randomDot(5);

  public static final ClassSelector p8o12 = Css.randomDot(5);

  public static final ClassSelector p9o12 = Css.randomDot(5);

  public static final ClassSelector p10o12 = Css.randomDot(5);

  public static final ClassSelector p11o12 = Css.randomDot(5);

  public static final ClassSelector full = Css.randomDot(5);

  public static final ClassSelector screen = Css.randomDot(5);

  public static final ClassSelector min = Css.randomDot(5);

  public static final ClassSelector max = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        auto,
        width(Keywords.auto)
    );
    style(
        px,
        width(px(1))
    );
    style(
        zero,
        width(zero())
    );
    style(
        v0,
        width(zero())
    );
    style(
        v00_5,
        width(rem(0.125))
    );
    style(
        v01,
        width(rem(0.25))
    );
    style(
        v01_5,
        width(rem(0.375))
    );
    style(
        v02,
        width(rem(0.5))
    );
    style(
        v02_5,
        width(rem(0.625))
    );
    style(
        v03,
        width(rem(0.75))
    );
    style(
        v03_5,
        width(rem(0.875))
    );
    style(
        v04,
        width(rem(1))
    );
    style(
        v05,
        width(rem(1.25))
    );
    style(
        v06,
        width(rem(1.5))
    );
    style(
        v07,
        width(rem(1.75))
    );
    style(
        v08,
        width(rem(2))
    );
    style(
        v09,
        width(rem(2.25))
    );
    style(
        v10,
        width(rem(2.5))
    );
    style(
        v11,
        width(rem(2.75))
    );
    style(
        v12,
        width(rem(3))
    );
    style(
        v14,
        width(rem(3.5))
    );
    style(
        v16,
        width(rem(4))
    );
    style(
        v20,
        width(rem(5))
    );
    style(
        v24,
        width(rem(6))
    );
    style(
        v28,
        width(rem(7))
    );
    style(
        v32,
        width(rem(8))
    );
    style(
        v36,
        width(rem(9))
    );
    style(
        v40,
        width(rem(10))
    );
    style(
        v44,
        width(rem(11))
    );
    style(
        v48,
        width(rem(12))
    );
    style(
        v52,
        width(rem(13))
    );
    style(
        v56,
        width(rem(14))
    );
    style(
        v60,
        width(rem(15))
    );
    style(
        v64,
        width(rem(16))
    );
    style(
        v68,
        width(rem(17))
    );
    style(
        v72,
        width(rem(18))
    );
    style(
        v80,
        width(rem(20))
    );
    style(
        v96,
        width(rem(24))
    );
    style(
        p1o2,
        width(pct(50))
    );
    style(
        p1o3,
        width(pct(33.333333))
    );
    style(
        p2o3,
        width(pct(66.666667))
    );
    style(
        p1o4,
        width(pct(25))
    );
    style(
        p2o4,
        width(pct(50))
    );
    style(
        p3o4,
        width(pct(75))
    );
    style(
        p1o5,
        width(pct(20))
    );
    style(
        p2o5,
        width(pct(40))
    );
    style(
        p3o5,
        width(pct(60))
    );
    style(
        p4o5,
        width(pct(80))
    );
    style(
        p1o6,
        width(pct(16.666667))
    );
    style(
        p2o6,
        width(pct(33.333333))
    );
    style(
        p3o6,
        width(pct(50))
    );
    style(
        p4o6,
        width(pct(66.666667))
    );
    style(
        p5o6,
        width(pct(83.333333))
    );
    style(
        p1o12,
        width(pct(8.333333))
    );
    style(
        p2o12,
        width(pct(16.666667))
    );
    style(
        p3o12,
        width(pct(25))
    );
    style(
        p4o12,
        width(pct(33.333333))
    );
    style(
        p5o12,
        width(pct(41.666667))
    );
    style(
        p6o12,
        width(pct(50))
    );
    style(
        p7o12,
        width(pct(58.333333))
    );
    style(
        p8o12,
        width(pct(66.666667))
    );
    style(
        p9o12,
        width(pct(75))
    );
    style(
        p10o12,
        width(pct(83.333333))
    );
    style(
        p11o12,
        width(pct(91.666667))
    );
    style(
        full,
        width(pct(100))
    );
    style(
        screen,
        width(vw(100))
    );
    style(
        min,
        width(Keywords.minContent)
    );
    style(
        max,
        width(Keywords.maxContent)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            width(Keywords.auto)
        ),

        style(
            sm.px,
            width(px(1))
        ),

        style(
            sm.zero,
            width(zero())
        ),

        style(
            sm.v0,
            width(zero())
        ),

        style(
            sm.v00_5,
            width(rem(0.125))
        ),

        style(
            sm.v01,
            width(rem(0.25))
        ),

        style(
            sm.v01_5,
            width(rem(0.375))
        ),

        style(
            sm.v02,
            width(rem(0.5))
        ),

        style(
            sm.v02_5,
            width(rem(0.625))
        ),

        style(
            sm.v03,
            width(rem(0.75))
        ),

        style(
            sm.v03_5,
            width(rem(0.875))
        ),

        style(
            sm.v04,
            width(rem(1))
        ),

        style(
            sm.v05,
            width(rem(1.25))
        ),

        style(
            sm.v06,
            width(rem(1.5))
        ),

        style(
            sm.v07,
            width(rem(1.75))
        ),

        style(
            sm.v08,
            width(rem(2))
        ),

        style(
            sm.v09,
            width(rem(2.25))
        ),

        style(
            sm.v10,
            width(rem(2.5))
        ),

        style(
            sm.v11,
            width(rem(2.75))
        ),

        style(
            sm.v12,
            width(rem(3))
        ),

        style(
            sm.v14,
            width(rem(3.5))
        ),

        style(
            sm.v16,
            width(rem(4))
        ),

        style(
            sm.v20,
            width(rem(5))
        ),

        style(
            sm.v24,
            width(rem(6))
        ),

        style(
            sm.v28,
            width(rem(7))
        ),

        style(
            sm.v32,
            width(rem(8))
        ),

        style(
            sm.v36,
            width(rem(9))
        ),

        style(
            sm.v40,
            width(rem(10))
        ),

        style(
            sm.v44,
            width(rem(11))
        ),

        style(
            sm.v48,
            width(rem(12))
        ),

        style(
            sm.v52,
            width(rem(13))
        ),

        style(
            sm.v56,
            width(rem(14))
        ),

        style(
            sm.v60,
            width(rem(15))
        ),

        style(
            sm.v64,
            width(rem(16))
        ),

        style(
            sm.v68,
            width(rem(17))
        ),

        style(
            sm.v72,
            width(rem(18))
        ),

        style(
            sm.v80,
            width(rem(20))
        ),

        style(
            sm.v96,
            width(rem(24))
        ),

        style(
            sm.p1o2,
            width(pct(50))
        ),

        style(
            sm.p1o3,
            width(pct(33.333333))
        ),

        style(
            sm.p2o3,
            width(pct(66.666667))
        ),

        style(
            sm.p1o4,
            width(pct(25))
        ),

        style(
            sm.p2o4,
            width(pct(50))
        ),

        style(
            sm.p3o4,
            width(pct(75))
        ),

        style(
            sm.p1o5,
            width(pct(20))
        ),

        style(
            sm.p2o5,
            width(pct(40))
        ),

        style(
            sm.p3o5,
            width(pct(60))
        ),

        style(
            sm.p4o5,
            width(pct(80))
        ),

        style(
            sm.p1o6,
            width(pct(16.666667))
        ),

        style(
            sm.p2o6,
            width(pct(33.333333))
        ),

        style(
            sm.p3o6,
            width(pct(50))
        ),

        style(
            sm.p4o6,
            width(pct(66.666667))
        ),

        style(
            sm.p5o6,
            width(pct(83.333333))
        ),

        style(
            sm.p1o12,
            width(pct(8.333333))
        ),

        style(
            sm.p2o12,
            width(pct(16.666667))
        ),

        style(
            sm.p3o12,
            width(pct(25))
        ),

        style(
            sm.p4o12,
            width(pct(33.333333))
        ),

        style(
            sm.p5o12,
            width(pct(41.666667))
        ),

        style(
            sm.p6o12,
            width(pct(50))
        ),

        style(
            sm.p7o12,
            width(pct(58.333333))
        ),

        style(
            sm.p8o12,
            width(pct(66.666667))
        ),

        style(
            sm.p9o12,
            width(pct(75))
        ),

        style(
            sm.p10o12,
            width(pct(83.333333))
        ),

        style(
            sm.p11o12,
            width(pct(91.666667))
        ),

        style(
            sm.full,
            width(pct(100))
        ),

        style(
            sm.screen,
            width(vw(100))
        ),

        style(
            sm.min,
            width(Keywords.minContent)
        ),

        style(
            sm.max,
            width(Keywords.maxContent)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            width(Keywords.auto)
        ),

        style(
            md.px,
            width(px(1))
        ),

        style(
            md.zero,
            width(zero())
        ),

        style(
            md.v0,
            width(zero())
        ),

        style(
            md.v00_5,
            width(rem(0.125))
        ),

        style(
            md.v01,
            width(rem(0.25))
        ),

        style(
            md.v01_5,
            width(rem(0.375))
        ),

        style(
            md.v02,
            width(rem(0.5))
        ),

        style(
            md.v02_5,
            width(rem(0.625))
        ),

        style(
            md.v03,
            width(rem(0.75))
        ),

        style(
            md.v03_5,
            width(rem(0.875))
        ),

        style(
            md.v04,
            width(rem(1))
        ),

        style(
            md.v05,
            width(rem(1.25))
        ),

        style(
            md.v06,
            width(rem(1.5))
        ),

        style(
            md.v07,
            width(rem(1.75))
        ),

        style(
            md.v08,
            width(rem(2))
        ),

        style(
            md.v09,
            width(rem(2.25))
        ),

        style(
            md.v10,
            width(rem(2.5))
        ),

        style(
            md.v11,
            width(rem(2.75))
        ),

        style(
            md.v12,
            width(rem(3))
        ),

        style(
            md.v14,
            width(rem(3.5))
        ),

        style(
            md.v16,
            width(rem(4))
        ),

        style(
            md.v20,
            width(rem(5))
        ),

        style(
            md.v24,
            width(rem(6))
        ),

        style(
            md.v28,
            width(rem(7))
        ),

        style(
            md.v32,
            width(rem(8))
        ),

        style(
            md.v36,
            width(rem(9))
        ),

        style(
            md.v40,
            width(rem(10))
        ),

        style(
            md.v44,
            width(rem(11))
        ),

        style(
            md.v48,
            width(rem(12))
        ),

        style(
            md.v52,
            width(rem(13))
        ),

        style(
            md.v56,
            width(rem(14))
        ),

        style(
            md.v60,
            width(rem(15))
        ),

        style(
            md.v64,
            width(rem(16))
        ),

        style(
            md.v68,
            width(rem(17))
        ),

        style(
            md.v72,
            width(rem(18))
        ),

        style(
            md.v80,
            width(rem(20))
        ),

        style(
            md.v96,
            width(rem(24))
        ),

        style(
            md.p1o2,
            width(pct(50))
        ),

        style(
            md.p1o3,
            width(pct(33.333333))
        ),

        style(
            md.p2o3,
            width(pct(66.666667))
        ),

        style(
            md.p1o4,
            width(pct(25))
        ),

        style(
            md.p2o4,
            width(pct(50))
        ),

        style(
            md.p3o4,
            width(pct(75))
        ),

        style(
            md.p1o5,
            width(pct(20))
        ),

        style(
            md.p2o5,
            width(pct(40))
        ),

        style(
            md.p3o5,
            width(pct(60))
        ),

        style(
            md.p4o5,
            width(pct(80))
        ),

        style(
            md.p1o6,
            width(pct(16.666667))
        ),

        style(
            md.p2o6,
            width(pct(33.333333))
        ),

        style(
            md.p3o6,
            width(pct(50))
        ),

        style(
            md.p4o6,
            width(pct(66.666667))
        ),

        style(
            md.p5o6,
            width(pct(83.333333))
        ),

        style(
            md.p1o12,
            width(pct(8.333333))
        ),

        style(
            md.p2o12,
            width(pct(16.666667))
        ),

        style(
            md.p3o12,
            width(pct(25))
        ),

        style(
            md.p4o12,
            width(pct(33.333333))
        ),

        style(
            md.p5o12,
            width(pct(41.666667))
        ),

        style(
            md.p6o12,
            width(pct(50))
        ),

        style(
            md.p7o12,
            width(pct(58.333333))
        ),

        style(
            md.p8o12,
            width(pct(66.666667))
        ),

        style(
            md.p9o12,
            width(pct(75))
        ),

        style(
            md.p10o12,
            width(pct(83.333333))
        ),

        style(
            md.p11o12,
            width(pct(91.666667))
        ),

        style(
            md.full,
            width(pct(100))
        ),

        style(
            md.screen,
            width(vw(100))
        ),

        style(
            md.min,
            width(Keywords.minContent)
        ),

        style(
            md.max,
            width(Keywords.maxContent)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            width(Keywords.auto)
        ),

        style(
            lg.px,
            width(px(1))
        ),

        style(
            lg.zero,
            width(zero())
        ),

        style(
            lg.v0,
            width(zero())
        ),

        style(
            lg.v00_5,
            width(rem(0.125))
        ),

        style(
            lg.v01,
            width(rem(0.25))
        ),

        style(
            lg.v01_5,
            width(rem(0.375))
        ),

        style(
            lg.v02,
            width(rem(0.5))
        ),

        style(
            lg.v02_5,
            width(rem(0.625))
        ),

        style(
            lg.v03,
            width(rem(0.75))
        ),

        style(
            lg.v03_5,
            width(rem(0.875))
        ),

        style(
            lg.v04,
            width(rem(1))
        ),

        style(
            lg.v05,
            width(rem(1.25))
        ),

        style(
            lg.v06,
            width(rem(1.5))
        ),

        style(
            lg.v07,
            width(rem(1.75))
        ),

        style(
            lg.v08,
            width(rem(2))
        ),

        style(
            lg.v09,
            width(rem(2.25))
        ),

        style(
            lg.v10,
            width(rem(2.5))
        ),

        style(
            lg.v11,
            width(rem(2.75))
        ),

        style(
            lg.v12,
            width(rem(3))
        ),

        style(
            lg.v14,
            width(rem(3.5))
        ),

        style(
            lg.v16,
            width(rem(4))
        ),

        style(
            lg.v20,
            width(rem(5))
        ),

        style(
            lg.v24,
            width(rem(6))
        ),

        style(
            lg.v28,
            width(rem(7))
        ),

        style(
            lg.v32,
            width(rem(8))
        ),

        style(
            lg.v36,
            width(rem(9))
        ),

        style(
            lg.v40,
            width(rem(10))
        ),

        style(
            lg.v44,
            width(rem(11))
        ),

        style(
            lg.v48,
            width(rem(12))
        ),

        style(
            lg.v52,
            width(rem(13))
        ),

        style(
            lg.v56,
            width(rem(14))
        ),

        style(
            lg.v60,
            width(rem(15))
        ),

        style(
            lg.v64,
            width(rem(16))
        ),

        style(
            lg.v68,
            width(rem(17))
        ),

        style(
            lg.v72,
            width(rem(18))
        ),

        style(
            lg.v80,
            width(rem(20))
        ),

        style(
            lg.v96,
            width(rem(24))
        ),

        style(
            lg.p1o2,
            width(pct(50))
        ),

        style(
            lg.p1o3,
            width(pct(33.333333))
        ),

        style(
            lg.p2o3,
            width(pct(66.666667))
        ),

        style(
            lg.p1o4,
            width(pct(25))
        ),

        style(
            lg.p2o4,
            width(pct(50))
        ),

        style(
            lg.p3o4,
            width(pct(75))
        ),

        style(
            lg.p1o5,
            width(pct(20))
        ),

        style(
            lg.p2o5,
            width(pct(40))
        ),

        style(
            lg.p3o5,
            width(pct(60))
        ),

        style(
            lg.p4o5,
            width(pct(80))
        ),

        style(
            lg.p1o6,
            width(pct(16.666667))
        ),

        style(
            lg.p2o6,
            width(pct(33.333333))
        ),

        style(
            lg.p3o6,
            width(pct(50))
        ),

        style(
            lg.p4o6,
            width(pct(66.666667))
        ),

        style(
            lg.p5o6,
            width(pct(83.333333))
        ),

        style(
            lg.p1o12,
            width(pct(8.333333))
        ),

        style(
            lg.p2o12,
            width(pct(16.666667))
        ),

        style(
            lg.p3o12,
            width(pct(25))
        ),

        style(
            lg.p4o12,
            width(pct(33.333333))
        ),

        style(
            lg.p5o12,
            width(pct(41.666667))
        ),

        style(
            lg.p6o12,
            width(pct(50))
        ),

        style(
            lg.p7o12,
            width(pct(58.333333))
        ),

        style(
            lg.p8o12,
            width(pct(66.666667))
        ),

        style(
            lg.p9o12,
            width(pct(75))
        ),

        style(
            lg.p10o12,
            width(pct(83.333333))
        ),

        style(
            lg.p11o12,
            width(pct(91.666667))
        ),

        style(
            lg.full,
            width(pct(100))
        ),

        style(
            lg.screen,
            width(vw(100))
        ),

        style(
            lg.min,
            width(Keywords.minContent)
        ),

        style(
            lg.max,
            width(Keywords.maxContent)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            width(Keywords.auto)
        ),

        style(
            xl.px,
            width(px(1))
        ),

        style(
            xl.zero,
            width(zero())
        ),

        style(
            xl.v0,
            width(zero())
        ),

        style(
            xl.v00_5,
            width(rem(0.125))
        ),

        style(
            xl.v01,
            width(rem(0.25))
        ),

        style(
            xl.v01_5,
            width(rem(0.375))
        ),

        style(
            xl.v02,
            width(rem(0.5))
        ),

        style(
            xl.v02_5,
            width(rem(0.625))
        ),

        style(
            xl.v03,
            width(rem(0.75))
        ),

        style(
            xl.v03_5,
            width(rem(0.875))
        ),

        style(
            xl.v04,
            width(rem(1))
        ),

        style(
            xl.v05,
            width(rem(1.25))
        ),

        style(
            xl.v06,
            width(rem(1.5))
        ),

        style(
            xl.v07,
            width(rem(1.75))
        ),

        style(
            xl.v08,
            width(rem(2))
        ),

        style(
            xl.v09,
            width(rem(2.25))
        ),

        style(
            xl.v10,
            width(rem(2.5))
        ),

        style(
            xl.v11,
            width(rem(2.75))
        ),

        style(
            xl.v12,
            width(rem(3))
        ),

        style(
            xl.v14,
            width(rem(3.5))
        ),

        style(
            xl.v16,
            width(rem(4))
        ),

        style(
            xl.v20,
            width(rem(5))
        ),

        style(
            xl.v24,
            width(rem(6))
        ),

        style(
            xl.v28,
            width(rem(7))
        ),

        style(
            xl.v32,
            width(rem(8))
        ),

        style(
            xl.v36,
            width(rem(9))
        ),

        style(
            xl.v40,
            width(rem(10))
        ),

        style(
            xl.v44,
            width(rem(11))
        ),

        style(
            xl.v48,
            width(rem(12))
        ),

        style(
            xl.v52,
            width(rem(13))
        ),

        style(
            xl.v56,
            width(rem(14))
        ),

        style(
            xl.v60,
            width(rem(15))
        ),

        style(
            xl.v64,
            width(rem(16))
        ),

        style(
            xl.v68,
            width(rem(17))
        ),

        style(
            xl.v72,
            width(rem(18))
        ),

        style(
            xl.v80,
            width(rem(20))
        ),

        style(
            xl.v96,
            width(rem(24))
        ),

        style(
            xl.p1o2,
            width(pct(50))
        ),

        style(
            xl.p1o3,
            width(pct(33.333333))
        ),

        style(
            xl.p2o3,
            width(pct(66.666667))
        ),

        style(
            xl.p1o4,
            width(pct(25))
        ),

        style(
            xl.p2o4,
            width(pct(50))
        ),

        style(
            xl.p3o4,
            width(pct(75))
        ),

        style(
            xl.p1o5,
            width(pct(20))
        ),

        style(
            xl.p2o5,
            width(pct(40))
        ),

        style(
            xl.p3o5,
            width(pct(60))
        ),

        style(
            xl.p4o5,
            width(pct(80))
        ),

        style(
            xl.p1o6,
            width(pct(16.666667))
        ),

        style(
            xl.p2o6,
            width(pct(33.333333))
        ),

        style(
            xl.p3o6,
            width(pct(50))
        ),

        style(
            xl.p4o6,
            width(pct(66.666667))
        ),

        style(
            xl.p5o6,
            width(pct(83.333333))
        ),

        style(
            xl.p1o12,
            width(pct(8.333333))
        ),

        style(
            xl.p2o12,
            width(pct(16.666667))
        ),

        style(
            xl.p3o12,
            width(pct(25))
        ),

        style(
            xl.p4o12,
            width(pct(33.333333))
        ),

        style(
            xl.p5o12,
            width(pct(41.666667))
        ),

        style(
            xl.p6o12,
            width(pct(50))
        ),

        style(
            xl.p7o12,
            width(pct(58.333333))
        ),

        style(
            xl.p8o12,
            width(pct(66.666667))
        ),

        style(
            xl.p9o12,
            width(pct(75))
        ),

        style(
            xl.p10o12,
            width(pct(83.333333))
        ),

        style(
            xl.p11o12,
            width(pct(91.666667))
        ),

        style(
            xl.full,
            width(pct(100))
        ),

        style(
            xl.screen,
            width(vw(100))
        ),

        style(
            xl.min,
            width(Keywords.minContent)
        ),

        style(
            xl.max,
            width(Keywords.maxContent)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.auto,
            width(Keywords.auto)
        ),

        style(
            x2l.px,
            width(px(1))
        ),

        style(
            x2l.zero,
            width(zero())
        ),

        style(
            x2l.v0,
            width(zero())
        ),

        style(
            x2l.v00_5,
            width(rem(0.125))
        ),

        style(
            x2l.v01,
            width(rem(0.25))
        ),

        style(
            x2l.v01_5,
            width(rem(0.375))
        ),

        style(
            x2l.v02,
            width(rem(0.5))
        ),

        style(
            x2l.v02_5,
            width(rem(0.625))
        ),

        style(
            x2l.v03,
            width(rem(0.75))
        ),

        style(
            x2l.v03_5,
            width(rem(0.875))
        ),

        style(
            x2l.v04,
            width(rem(1))
        ),

        style(
            x2l.v05,
            width(rem(1.25))
        ),

        style(
            x2l.v06,
            width(rem(1.5))
        ),

        style(
            x2l.v07,
            width(rem(1.75))
        ),

        style(
            x2l.v08,
            width(rem(2))
        ),

        style(
            x2l.v09,
            width(rem(2.25))
        ),

        style(
            x2l.v10,
            width(rem(2.5))
        ),

        style(
            x2l.v11,
            width(rem(2.75))
        ),

        style(
            x2l.v12,
            width(rem(3))
        ),

        style(
            x2l.v14,
            width(rem(3.5))
        ),

        style(
            x2l.v16,
            width(rem(4))
        ),

        style(
            x2l.v20,
            width(rem(5))
        ),

        style(
            x2l.v24,
            width(rem(6))
        ),

        style(
            x2l.v28,
            width(rem(7))
        ),

        style(
            x2l.v32,
            width(rem(8))
        ),

        style(
            x2l.v36,
            width(rem(9))
        ),

        style(
            x2l.v40,
            width(rem(10))
        ),

        style(
            x2l.v44,
            width(rem(11))
        ),

        style(
            x2l.v48,
            width(rem(12))
        ),

        style(
            x2l.v52,
            width(rem(13))
        ),

        style(
            x2l.v56,
            width(rem(14))
        ),

        style(
            x2l.v60,
            width(rem(15))
        ),

        style(
            x2l.v64,
            width(rem(16))
        ),

        style(
            x2l.v68,
            width(rem(17))
        ),

        style(
            x2l.v72,
            width(rem(18))
        ),

        style(
            x2l.v80,
            width(rem(20))
        ),

        style(
            x2l.v96,
            width(rem(24))
        ),

        style(
            x2l.p1o2,
            width(pct(50))
        ),

        style(
            x2l.p1o3,
            width(pct(33.333333))
        ),

        style(
            x2l.p2o3,
            width(pct(66.666667))
        ),

        style(
            x2l.p1o4,
            width(pct(25))
        ),

        style(
            x2l.p2o4,
            width(pct(50))
        ),

        style(
            x2l.p3o4,
            width(pct(75))
        ),

        style(
            x2l.p1o5,
            width(pct(20))
        ),

        style(
            x2l.p2o5,
            width(pct(40))
        ),

        style(
            x2l.p3o5,
            width(pct(60))
        ),

        style(
            x2l.p4o5,
            width(pct(80))
        ),

        style(
            x2l.p1o6,
            width(pct(16.666667))
        ),

        style(
            x2l.p2o6,
            width(pct(33.333333))
        ),

        style(
            x2l.p3o6,
            width(pct(50))
        ),

        style(
            x2l.p4o6,
            width(pct(66.666667))
        ),

        style(
            x2l.p5o6,
            width(pct(83.333333))
        ),

        style(
            x2l.p1o12,
            width(pct(8.333333))
        ),

        style(
            x2l.p2o12,
            width(pct(16.666667))
        ),

        style(
            x2l.p3o12,
            width(pct(25))
        ),

        style(
            x2l.p4o12,
            width(pct(33.333333))
        ),

        style(
            x2l.p5o12,
            width(pct(41.666667))
        ),

        style(
            x2l.p6o12,
            width(pct(50))
        ),

        style(
            x2l.p7o12,
            width(pct(58.333333))
        ),

        style(
            x2l.p8o12,
            width(pct(66.666667))
        ),

        style(
            x2l.p9o12,
            width(pct(75))
        ),

        style(
            x2l.p10o12,
            width(pct(83.333333))
        ),

        style(
            x2l.p11o12,
            width(pct(91.666667))
        ),

        style(
            x2l.full,
            width(pct(100))
        ),

        style(
            x2l.screen,
            width(vw(100))
        ),

        style(
            x2l.min,
            width(Keywords.minContent)
        ),

        style(
            x2l.max,
            width(Keywords.maxContent)
        )
    );
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

    ClassSelector p1o2 = Css.randomDot(5);

    ClassSelector p1o3 = Css.randomDot(5);

    ClassSelector p2o3 = Css.randomDot(5);

    ClassSelector p1o4 = Css.randomDot(5);

    ClassSelector p2o4 = Css.randomDot(5);

    ClassSelector p3o4 = Css.randomDot(5);

    ClassSelector p1o5 = Css.randomDot(5);

    ClassSelector p2o5 = Css.randomDot(5);

    ClassSelector p3o5 = Css.randomDot(5);

    ClassSelector p4o5 = Css.randomDot(5);

    ClassSelector p1o6 = Css.randomDot(5);

    ClassSelector p2o6 = Css.randomDot(5);

    ClassSelector p3o6 = Css.randomDot(5);

    ClassSelector p4o6 = Css.randomDot(5);

    ClassSelector p5o6 = Css.randomDot(5);

    ClassSelector p1o12 = Css.randomDot(5);

    ClassSelector p2o12 = Css.randomDot(5);

    ClassSelector p3o12 = Css.randomDot(5);

    ClassSelector p4o12 = Css.randomDot(5);

    ClassSelector p5o12 = Css.randomDot(5);

    ClassSelector p6o12 = Css.randomDot(5);

    ClassSelector p7o12 = Css.randomDot(5);

    ClassSelector p8o12 = Css.randomDot(5);

    ClassSelector p9o12 = Css.randomDot(5);

    ClassSelector p10o12 = Css.randomDot(5);

    ClassSelector p11o12 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screen = Css.randomDot(5);

    ClassSelector min = Css.randomDot(5);

    ClassSelector max = Css.randomDot(5);

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

    ClassSelector p1o2 = Css.randomDot(5);

    ClassSelector p1o3 = Css.randomDot(5);

    ClassSelector p2o3 = Css.randomDot(5);

    ClassSelector p1o4 = Css.randomDot(5);

    ClassSelector p2o4 = Css.randomDot(5);

    ClassSelector p3o4 = Css.randomDot(5);

    ClassSelector p1o5 = Css.randomDot(5);

    ClassSelector p2o5 = Css.randomDot(5);

    ClassSelector p3o5 = Css.randomDot(5);

    ClassSelector p4o5 = Css.randomDot(5);

    ClassSelector p1o6 = Css.randomDot(5);

    ClassSelector p2o6 = Css.randomDot(5);

    ClassSelector p3o6 = Css.randomDot(5);

    ClassSelector p4o6 = Css.randomDot(5);

    ClassSelector p5o6 = Css.randomDot(5);

    ClassSelector p1o12 = Css.randomDot(5);

    ClassSelector p2o12 = Css.randomDot(5);

    ClassSelector p3o12 = Css.randomDot(5);

    ClassSelector p4o12 = Css.randomDot(5);

    ClassSelector p5o12 = Css.randomDot(5);

    ClassSelector p6o12 = Css.randomDot(5);

    ClassSelector p7o12 = Css.randomDot(5);

    ClassSelector p8o12 = Css.randomDot(5);

    ClassSelector p9o12 = Css.randomDot(5);

    ClassSelector p10o12 = Css.randomDot(5);

    ClassSelector p11o12 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screen = Css.randomDot(5);

    ClassSelector min = Css.randomDot(5);

    ClassSelector max = Css.randomDot(5);

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

    ClassSelector p1o2 = Css.randomDot(5);

    ClassSelector p1o3 = Css.randomDot(5);

    ClassSelector p2o3 = Css.randomDot(5);

    ClassSelector p1o4 = Css.randomDot(5);

    ClassSelector p2o4 = Css.randomDot(5);

    ClassSelector p3o4 = Css.randomDot(5);

    ClassSelector p1o5 = Css.randomDot(5);

    ClassSelector p2o5 = Css.randomDot(5);

    ClassSelector p3o5 = Css.randomDot(5);

    ClassSelector p4o5 = Css.randomDot(5);

    ClassSelector p1o6 = Css.randomDot(5);

    ClassSelector p2o6 = Css.randomDot(5);

    ClassSelector p3o6 = Css.randomDot(5);

    ClassSelector p4o6 = Css.randomDot(5);

    ClassSelector p5o6 = Css.randomDot(5);

    ClassSelector p1o12 = Css.randomDot(5);

    ClassSelector p2o12 = Css.randomDot(5);

    ClassSelector p3o12 = Css.randomDot(5);

    ClassSelector p4o12 = Css.randomDot(5);

    ClassSelector p5o12 = Css.randomDot(5);

    ClassSelector p6o12 = Css.randomDot(5);

    ClassSelector p7o12 = Css.randomDot(5);

    ClassSelector p8o12 = Css.randomDot(5);

    ClassSelector p9o12 = Css.randomDot(5);

    ClassSelector p10o12 = Css.randomDot(5);

    ClassSelector p11o12 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screen = Css.randomDot(5);

    ClassSelector min = Css.randomDot(5);

    ClassSelector max = Css.randomDot(5);

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

    ClassSelector p1o2 = Css.randomDot(5);

    ClassSelector p1o3 = Css.randomDot(5);

    ClassSelector p2o3 = Css.randomDot(5);

    ClassSelector p1o4 = Css.randomDot(5);

    ClassSelector p2o4 = Css.randomDot(5);

    ClassSelector p3o4 = Css.randomDot(5);

    ClassSelector p1o5 = Css.randomDot(5);

    ClassSelector p2o5 = Css.randomDot(5);

    ClassSelector p3o5 = Css.randomDot(5);

    ClassSelector p4o5 = Css.randomDot(5);

    ClassSelector p1o6 = Css.randomDot(5);

    ClassSelector p2o6 = Css.randomDot(5);

    ClassSelector p3o6 = Css.randomDot(5);

    ClassSelector p4o6 = Css.randomDot(5);

    ClassSelector p5o6 = Css.randomDot(5);

    ClassSelector p1o12 = Css.randomDot(5);

    ClassSelector p2o12 = Css.randomDot(5);

    ClassSelector p3o12 = Css.randomDot(5);

    ClassSelector p4o12 = Css.randomDot(5);

    ClassSelector p5o12 = Css.randomDot(5);

    ClassSelector p6o12 = Css.randomDot(5);

    ClassSelector p7o12 = Css.randomDot(5);

    ClassSelector p8o12 = Css.randomDot(5);

    ClassSelector p9o12 = Css.randomDot(5);

    ClassSelector p10o12 = Css.randomDot(5);

    ClassSelector p11o12 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screen = Css.randomDot(5);

    ClassSelector min = Css.randomDot(5);

    ClassSelector max = Css.randomDot(5);

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

    ClassSelector p1o2 = Css.randomDot(5);

    ClassSelector p1o3 = Css.randomDot(5);

    ClassSelector p2o3 = Css.randomDot(5);

    ClassSelector p1o4 = Css.randomDot(5);

    ClassSelector p2o4 = Css.randomDot(5);

    ClassSelector p3o4 = Css.randomDot(5);

    ClassSelector p1o5 = Css.randomDot(5);

    ClassSelector p2o5 = Css.randomDot(5);

    ClassSelector p3o5 = Css.randomDot(5);

    ClassSelector p4o5 = Css.randomDot(5);

    ClassSelector p1o6 = Css.randomDot(5);

    ClassSelector p2o6 = Css.randomDot(5);

    ClassSelector p3o6 = Css.randomDot(5);

    ClassSelector p4o6 = Css.randomDot(5);

    ClassSelector p5o6 = Css.randomDot(5);

    ClassSelector p1o12 = Css.randomDot(5);

    ClassSelector p2o12 = Css.randomDot(5);

    ClassSelector p3o12 = Css.randomDot(5);

    ClassSelector p4o12 = Css.randomDot(5);

    ClassSelector p5o12 = Css.randomDot(5);

    ClassSelector p6o12 = Css.randomDot(5);

    ClassSelector p7o12 = Css.randomDot(5);

    ClassSelector p8o12 = Css.randomDot(5);

    ClassSelector p9o12 = Css.randomDot(5);

    ClassSelector p10o12 = Css.randomDot(5);

    ClassSelector p11o12 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screen = Css.randomDot(5);

    ClassSelector min = Css.randomDot(5);

    ClassSelector max = Css.randomDot(5);

  }

}