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
package br.com.objectos.css.framework.spacing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class MarginX extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector px = Css.randomDot(5);

  public static final ClassSelector zero = Css.randomDot(5);

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector v0_5 = Css.randomDot(5);

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

  public static final ClassSelector v72 = Css.randomDot(5);

  public static final ClassSelector v80 = Css.randomDot(5);

  public static final ClassSelector v96 = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        auto,
        marginRight(Keywords.auto),
        marginLeft(Keywords.auto)
    );
    style(
        px,
        marginRight(px(1)),
        marginLeft(px(1))
    );
    style(
        zero,
        marginRight(zero()),
        marginLeft(zero())
    );
    style(
        v0,
        marginRight(zero()),
        marginLeft(zero())
    );
    style(
        v0_5,
        marginRight(rem(0.125)),
        marginLeft(rem(0.125))
    );
    style(
        v01,
        marginRight(rem(0.25)),
        marginLeft(rem(0.25))
    );
    style(
        v01_5,
        marginRight(rem(0.375)),
        marginLeft(rem(0.375))
    );
    style(
        v02,
        marginRight(rem(0.5)),
        marginLeft(rem(0.5))
    );
    style(
        v02_5,
        marginRight(rem(0.625)),
        marginLeft(rem(0.625))
    );
    style(
        v03,
        marginRight(rem(0.75)),
        marginLeft(rem(0.75))
    );
    style(
        v03_5,
        marginRight(rem(0.875)),
        marginLeft(rem(0.875))
    );
    style(
        v04,
        marginRight(rem(1)),
        marginLeft(rem(1))
    );
    style(
        v05,
        marginRight(rem(1.25)),
        marginLeft(rem(1.25))
    );
    style(
        v06,
        marginRight(rem(1.5)),
        marginLeft(rem(1.5))
    );
    style(
        v07,
        marginRight(rem(1.75)),
        marginLeft(rem(1.75))
    );
    style(
        v08,
        marginRight(rem(2)),
        marginLeft(rem(2))
    );
    style(
        v09,
        marginRight(rem(2.25)),
        marginLeft(rem(2.25))
    );
    style(
        v10,
        marginRight(rem(2.5)),
        marginLeft(rem(2.5))
    );
    style(
        v11,
        marginRight(rem(2.75)),
        marginLeft(rem(2.75))
    );
    style(
        v12,
        marginRight(rem(3)),
        marginLeft(rem(3))
    );
    style(
        v14,
        marginRight(rem(3.5)),
        marginLeft(rem(3.5))
    );
    style(
        v16,
        marginRight(rem(4)),
        marginLeft(rem(4))
    );
    style(
        v20,
        marginRight(rem(5)),
        marginLeft(rem(5))
    );
    style(
        v24,
        marginRight(rem(6)),
        marginLeft(rem(6))
    );
    style(
        v28,
        marginRight(rem(7)),
        marginLeft(rem(7))
    );
    style(
        v32,
        marginRight(rem(8)),
        marginLeft(rem(8))
    );
    style(
        v36,
        marginRight(rem(9)),
        marginLeft(rem(9))
    );
    style(
        v40,
        marginRight(rem(10)),
        marginLeft(rem(10))
    );
    style(
        v44,
        marginRight(rem(11)),
        marginLeft(rem(11))
    );
    style(
        v48,
        marginRight(rem(12)),
        marginLeft(rem(12))
    );
    style(
        v52,
        marginRight(rem(13)),
        marginLeft(rem(13))
    );
    style(
        v56,
        marginRight(rem(14)),
        marginLeft(rem(14))
    );
    style(
        v60,
        marginRight(rem(15)),
        marginLeft(rem(15))
    );
    style(
        v64,
        marginRight(rem(16)),
        marginLeft(rem(16))
    );
    style(
        v72,
        marginRight(rem(18)),
        marginLeft(rem(18))
    );
    style(
        v80,
        marginRight(rem(20)),
        marginLeft(rem(20))
    );
    style(
        v96,
        marginRight(rem(24)),
        marginLeft(rem(24))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            marginRight(Keywords.auto),
            marginLeft(Keywords.auto)
        ),

        style(
            sm.px,
            marginRight(px(1)),
            marginLeft(px(1))
        ),

        style(
            sm.zero,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            sm.v0,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            sm.v0_5,
            marginRight(rem(0.125)),
            marginLeft(rem(0.125))
        ),

        style(
            sm.v01,
            marginRight(rem(0.25)),
            marginLeft(rem(0.25))
        ),

        style(
            sm.v01_5,
            marginRight(rem(0.375)),
            marginLeft(rem(0.375))
        ),

        style(
            sm.v02,
            marginRight(rem(0.5)),
            marginLeft(rem(0.5))
        ),

        style(
            sm.v02_5,
            marginRight(rem(0.625)),
            marginLeft(rem(0.625))
        ),

        style(
            sm.v03,
            marginRight(rem(0.75)),
            marginLeft(rem(0.75))
        ),

        style(
            sm.v03_5,
            marginRight(rem(0.875)),
            marginLeft(rem(0.875))
        ),

        style(
            sm.v04,
            marginRight(rem(1)),
            marginLeft(rem(1))
        ),

        style(
            sm.v05,
            marginRight(rem(1.25)),
            marginLeft(rem(1.25))
        ),

        style(
            sm.v06,
            marginRight(rem(1.5)),
            marginLeft(rem(1.5))
        ),

        style(
            sm.v07,
            marginRight(rem(1.75)),
            marginLeft(rem(1.75))
        ),

        style(
            sm.v08,
            marginRight(rem(2)),
            marginLeft(rem(2))
        ),

        style(
            sm.v09,
            marginRight(rem(2.25)),
            marginLeft(rem(2.25))
        ),

        style(
            sm.v10,
            marginRight(rem(2.5)),
            marginLeft(rem(2.5))
        ),

        style(
            sm.v11,
            marginRight(rem(2.75)),
            marginLeft(rem(2.75))
        ),

        style(
            sm.v12,
            marginRight(rem(3)),
            marginLeft(rem(3))
        ),

        style(
            sm.v14,
            marginRight(rem(3.5)),
            marginLeft(rem(3.5))
        ),

        style(
            sm.v16,
            marginRight(rem(4)),
            marginLeft(rem(4))
        ),

        style(
            sm.v20,
            marginRight(rem(5)),
            marginLeft(rem(5))
        ),

        style(
            sm.v24,
            marginRight(rem(6)),
            marginLeft(rem(6))
        ),

        style(
            sm.v28,
            marginRight(rem(7)),
            marginLeft(rem(7))
        ),

        style(
            sm.v32,
            marginRight(rem(8)),
            marginLeft(rem(8))
        ),

        style(
            sm.v36,
            marginRight(rem(9)),
            marginLeft(rem(9))
        ),

        style(
            sm.v40,
            marginRight(rem(10)),
            marginLeft(rem(10))
        ),

        style(
            sm.v44,
            marginRight(rem(11)),
            marginLeft(rem(11))
        ),

        style(
            sm.v48,
            marginRight(rem(12)),
            marginLeft(rem(12))
        ),

        style(
            sm.v52,
            marginRight(rem(13)),
            marginLeft(rem(13))
        ),

        style(
            sm.v56,
            marginRight(rem(14)),
            marginLeft(rem(14))
        ),

        style(
            sm.v60,
            marginRight(rem(15)),
            marginLeft(rem(15))
        ),

        style(
            sm.v64,
            marginRight(rem(16)),
            marginLeft(rem(16))
        ),

        style(
            sm.v72,
            marginRight(rem(18)),
            marginLeft(rem(18))
        ),

        style(
            sm.v80,
            marginRight(rem(20)),
            marginLeft(rem(20))
        ),

        style(
            sm.v96,
            marginRight(rem(24)),
            marginLeft(rem(24))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            marginRight(Keywords.auto),
            marginLeft(Keywords.auto)
        ),

        style(
            md.px,
            marginRight(px(1)),
            marginLeft(px(1))
        ),

        style(
            md.zero,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            md.v0,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            md.v0_5,
            marginRight(rem(0.125)),
            marginLeft(rem(0.125))
        ),

        style(
            md.v01,
            marginRight(rem(0.25)),
            marginLeft(rem(0.25))
        ),

        style(
            md.v01_5,
            marginRight(rem(0.375)),
            marginLeft(rem(0.375))
        ),

        style(
            md.v02,
            marginRight(rem(0.5)),
            marginLeft(rem(0.5))
        ),

        style(
            md.v02_5,
            marginRight(rem(0.625)),
            marginLeft(rem(0.625))
        ),

        style(
            md.v03,
            marginRight(rem(0.75)),
            marginLeft(rem(0.75))
        ),

        style(
            md.v03_5,
            marginRight(rem(0.875)),
            marginLeft(rem(0.875))
        ),

        style(
            md.v04,
            marginRight(rem(1)),
            marginLeft(rem(1))
        ),

        style(
            md.v05,
            marginRight(rem(1.25)),
            marginLeft(rem(1.25))
        ),

        style(
            md.v06,
            marginRight(rem(1.5)),
            marginLeft(rem(1.5))
        ),

        style(
            md.v07,
            marginRight(rem(1.75)),
            marginLeft(rem(1.75))
        ),

        style(
            md.v08,
            marginRight(rem(2)),
            marginLeft(rem(2))
        ),

        style(
            md.v09,
            marginRight(rem(2.25)),
            marginLeft(rem(2.25))
        ),

        style(
            md.v10,
            marginRight(rem(2.5)),
            marginLeft(rem(2.5))
        ),

        style(
            md.v11,
            marginRight(rem(2.75)),
            marginLeft(rem(2.75))
        ),

        style(
            md.v12,
            marginRight(rem(3)),
            marginLeft(rem(3))
        ),

        style(
            md.v14,
            marginRight(rem(3.5)),
            marginLeft(rem(3.5))
        ),

        style(
            md.v16,
            marginRight(rem(4)),
            marginLeft(rem(4))
        ),

        style(
            md.v20,
            marginRight(rem(5)),
            marginLeft(rem(5))
        ),

        style(
            md.v24,
            marginRight(rem(6)),
            marginLeft(rem(6))
        ),

        style(
            md.v28,
            marginRight(rem(7)),
            marginLeft(rem(7))
        ),

        style(
            md.v32,
            marginRight(rem(8)),
            marginLeft(rem(8))
        ),

        style(
            md.v36,
            marginRight(rem(9)),
            marginLeft(rem(9))
        ),

        style(
            md.v40,
            marginRight(rem(10)),
            marginLeft(rem(10))
        ),

        style(
            md.v44,
            marginRight(rem(11)),
            marginLeft(rem(11))
        ),

        style(
            md.v48,
            marginRight(rem(12)),
            marginLeft(rem(12))
        ),

        style(
            md.v52,
            marginRight(rem(13)),
            marginLeft(rem(13))
        ),

        style(
            md.v56,
            marginRight(rem(14)),
            marginLeft(rem(14))
        ),

        style(
            md.v60,
            marginRight(rem(15)),
            marginLeft(rem(15))
        ),

        style(
            md.v64,
            marginRight(rem(16)),
            marginLeft(rem(16))
        ),

        style(
            md.v72,
            marginRight(rem(18)),
            marginLeft(rem(18))
        ),

        style(
            md.v80,
            marginRight(rem(20)),
            marginLeft(rem(20))
        ),

        style(
            md.v96,
            marginRight(rem(24)),
            marginLeft(rem(24))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            marginRight(Keywords.auto),
            marginLeft(Keywords.auto)
        ),

        style(
            lg.px,
            marginRight(px(1)),
            marginLeft(px(1))
        ),

        style(
            lg.zero,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            lg.v0,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            lg.v0_5,
            marginRight(rem(0.125)),
            marginLeft(rem(0.125))
        ),

        style(
            lg.v01,
            marginRight(rem(0.25)),
            marginLeft(rem(0.25))
        ),

        style(
            lg.v01_5,
            marginRight(rem(0.375)),
            marginLeft(rem(0.375))
        ),

        style(
            lg.v02,
            marginRight(rem(0.5)),
            marginLeft(rem(0.5))
        ),

        style(
            lg.v02_5,
            marginRight(rem(0.625)),
            marginLeft(rem(0.625))
        ),

        style(
            lg.v03,
            marginRight(rem(0.75)),
            marginLeft(rem(0.75))
        ),

        style(
            lg.v03_5,
            marginRight(rem(0.875)),
            marginLeft(rem(0.875))
        ),

        style(
            lg.v04,
            marginRight(rem(1)),
            marginLeft(rem(1))
        ),

        style(
            lg.v05,
            marginRight(rem(1.25)),
            marginLeft(rem(1.25))
        ),

        style(
            lg.v06,
            marginRight(rem(1.5)),
            marginLeft(rem(1.5))
        ),

        style(
            lg.v07,
            marginRight(rem(1.75)),
            marginLeft(rem(1.75))
        ),

        style(
            lg.v08,
            marginRight(rem(2)),
            marginLeft(rem(2))
        ),

        style(
            lg.v09,
            marginRight(rem(2.25)),
            marginLeft(rem(2.25))
        ),

        style(
            lg.v10,
            marginRight(rem(2.5)),
            marginLeft(rem(2.5))
        ),

        style(
            lg.v11,
            marginRight(rem(2.75)),
            marginLeft(rem(2.75))
        ),

        style(
            lg.v12,
            marginRight(rem(3)),
            marginLeft(rem(3))
        ),

        style(
            lg.v14,
            marginRight(rem(3.5)),
            marginLeft(rem(3.5))
        ),

        style(
            lg.v16,
            marginRight(rem(4)),
            marginLeft(rem(4))
        ),

        style(
            lg.v20,
            marginRight(rem(5)),
            marginLeft(rem(5))
        ),

        style(
            lg.v24,
            marginRight(rem(6)),
            marginLeft(rem(6))
        ),

        style(
            lg.v28,
            marginRight(rem(7)),
            marginLeft(rem(7))
        ),

        style(
            lg.v32,
            marginRight(rem(8)),
            marginLeft(rem(8))
        ),

        style(
            lg.v36,
            marginRight(rem(9)),
            marginLeft(rem(9))
        ),

        style(
            lg.v40,
            marginRight(rem(10)),
            marginLeft(rem(10))
        ),

        style(
            lg.v44,
            marginRight(rem(11)),
            marginLeft(rem(11))
        ),

        style(
            lg.v48,
            marginRight(rem(12)),
            marginLeft(rem(12))
        ),

        style(
            lg.v52,
            marginRight(rem(13)),
            marginLeft(rem(13))
        ),

        style(
            lg.v56,
            marginRight(rem(14)),
            marginLeft(rem(14))
        ),

        style(
            lg.v60,
            marginRight(rem(15)),
            marginLeft(rem(15))
        ),

        style(
            lg.v64,
            marginRight(rem(16)),
            marginLeft(rem(16))
        ),

        style(
            lg.v72,
            marginRight(rem(18)),
            marginLeft(rem(18))
        ),

        style(
            lg.v80,
            marginRight(rem(20)),
            marginLeft(rem(20))
        ),

        style(
            lg.v96,
            marginRight(rem(24)),
            marginLeft(rem(24))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            marginRight(Keywords.auto),
            marginLeft(Keywords.auto)
        ),

        style(
            xl.px,
            marginRight(px(1)),
            marginLeft(px(1))
        ),

        style(
            xl.zero,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            xl.v0,
            marginRight(zero()),
            marginLeft(zero())
        ),

        style(
            xl.v0_5,
            marginRight(rem(0.125)),
            marginLeft(rem(0.125))
        ),

        style(
            xl.v01,
            marginRight(rem(0.25)),
            marginLeft(rem(0.25))
        ),

        style(
            xl.v01_5,
            marginRight(rem(0.375)),
            marginLeft(rem(0.375))
        ),

        style(
            xl.v02,
            marginRight(rem(0.5)),
            marginLeft(rem(0.5))
        ),

        style(
            xl.v02_5,
            marginRight(rem(0.625)),
            marginLeft(rem(0.625))
        ),

        style(
            xl.v03,
            marginRight(rem(0.75)),
            marginLeft(rem(0.75))
        ),

        style(
            xl.v03_5,
            marginRight(rem(0.875)),
            marginLeft(rem(0.875))
        ),

        style(
            xl.v04,
            marginRight(rem(1)),
            marginLeft(rem(1))
        ),

        style(
            xl.v05,
            marginRight(rem(1.25)),
            marginLeft(rem(1.25))
        ),

        style(
            xl.v06,
            marginRight(rem(1.5)),
            marginLeft(rem(1.5))
        ),

        style(
            xl.v07,
            marginRight(rem(1.75)),
            marginLeft(rem(1.75))
        ),

        style(
            xl.v08,
            marginRight(rem(2)),
            marginLeft(rem(2))
        ),

        style(
            xl.v09,
            marginRight(rem(2.25)),
            marginLeft(rem(2.25))
        ),

        style(
            xl.v10,
            marginRight(rem(2.5)),
            marginLeft(rem(2.5))
        ),

        style(
            xl.v11,
            marginRight(rem(2.75)),
            marginLeft(rem(2.75))
        ),

        style(
            xl.v12,
            marginRight(rem(3)),
            marginLeft(rem(3))
        ),

        style(
            xl.v14,
            marginRight(rem(3.5)),
            marginLeft(rem(3.5))
        ),

        style(
            xl.v16,
            marginRight(rem(4)),
            marginLeft(rem(4))
        ),

        style(
            xl.v20,
            marginRight(rem(5)),
            marginLeft(rem(5))
        ),

        style(
            xl.v24,
            marginRight(rem(6)),
            marginLeft(rem(6))
        ),

        style(
            xl.v28,
            marginRight(rem(7)),
            marginLeft(rem(7))
        ),

        style(
            xl.v32,
            marginRight(rem(8)),
            marginLeft(rem(8))
        ),

        style(
            xl.v36,
            marginRight(rem(9)),
            marginLeft(rem(9))
        ),

        style(
            xl.v40,
            marginRight(rem(10)),
            marginLeft(rem(10))
        ),

        style(
            xl.v44,
            marginRight(rem(11)),
            marginLeft(rem(11))
        ),

        style(
            xl.v48,
            marginRight(rem(12)),
            marginLeft(rem(12))
        ),

        style(
            xl.v52,
            marginRight(rem(13)),
            marginLeft(rem(13))
        ),

        style(
            xl.v56,
            marginRight(rem(14)),
            marginLeft(rem(14))
        ),

        style(
            xl.v60,
            marginRight(rem(15)),
            marginLeft(rem(15))
        ),

        style(
            xl.v64,
            marginRight(rem(16)),
            marginLeft(rem(16))
        ),

        style(
            xl.v72,
            marginRight(rem(18)),
            marginLeft(rem(18))
        ),

        style(
            xl.v80,
            marginRight(rem(20)),
            marginLeft(rem(20))
        ),

        style(
            xl.v96,
            marginRight(rem(24)),
            marginLeft(rem(24))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v0_5 = Css.randomDot(5);

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

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v0_5 = Css.randomDot(5);

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

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v0_5 = Css.randomDot(5);

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

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector zero = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v0_5 = Css.randomDot(5);

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

    ClassSelector v72 = Css.randomDot(5);

    ClassSelector v80 = Css.randomDot(5);

    ClassSelector v96 = Css.randomDot(5);

  }

}