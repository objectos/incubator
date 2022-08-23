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
public final class MaxWidth extends AbstractStyleSheet {

  public static final ClassSelector none = Css.randomDot(5);

  public static final ClassSelector xSmall = Css.randomDot(5);

  public static final ClassSelector small = Css.randomDot(5);

  public static final ClassSelector medium = Css.randomDot(5);

  public static final ClassSelector large = Css.randomDot(5);

  public static final ClassSelector xLarge = Css.randomDot(5);

  public static final ClassSelector xLarge2 = Css.randomDot(5);

  public static final ClassSelector xLarge3 = Css.randomDot(5);

  public static final ClassSelector xLarge4 = Css.randomDot(5);

  public static final ClassSelector xLarge5 = Css.randomDot(5);

  public static final ClassSelector xLarge6 = Css.randomDot(5);

  public static final ClassSelector full = Css.randomDot(5);

  public static final ClassSelector screenSm = Css.randomDot(5);

  public static final ClassSelector screenMd = Css.randomDot(5);

  public static final ClassSelector screenLg = Css.randomDot(5);

  public static final ClassSelector screenXl = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        none,
        maxWidth(Keywords.none)
    );
    style(
        xSmall,
        maxWidth(rem(20))
    );
    style(
        small,
        maxWidth(rem(24))
    );
    style(
        medium,
        maxWidth(rem(28))
    );
    style(
        large,
        maxWidth(rem(32))
    );
    style(
        xLarge,
        maxWidth(rem(36))
    );
    style(
        xLarge2,
        maxWidth(rem(42))
    );
    style(
        xLarge3,
        maxWidth(rem(48))
    );
    style(
        xLarge4,
        maxWidth(rem(56))
    );
    style(
        xLarge5,
        maxWidth(rem(64))
    );
    style(
        xLarge6,
        maxWidth(rem(72))
    );
    style(
        full,
        maxWidth(pct(100))
    );
    style(
        screenSm,
        maxWidth(px(640))
    );
    style(
        screenMd,
        maxWidth(px(768))
    );
    style(
        screenLg,
        maxWidth(px(1024))
    );
    style(
        screenXl,
        maxWidth(px(1280))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            maxWidth(Keywords.none)
        ),

        style(
            sm.xSmall,
            maxWidth(rem(20))
        ),

        style(
            sm.small,
            maxWidth(rem(24))
        ),

        style(
            sm.medium,
            maxWidth(rem(28))
        ),

        style(
            sm.large,
            maxWidth(rem(32))
        ),

        style(
            sm.xLarge,
            maxWidth(rem(36))
        ),

        style(
            sm.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            sm.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            sm.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            sm.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            sm.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            sm.full,
            maxWidth(pct(100))
        ),

        style(
            sm.screenSm,
            maxWidth(px(640))
        ),

        style(
            sm.screenMd,
            maxWidth(px(768))
        ),

        style(
            sm.screenLg,
            maxWidth(px(1024))
        ),

        style(
            sm.screenXl,
            maxWidth(px(1280))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            maxWidth(Keywords.none)
        ),

        style(
            md.xSmall,
            maxWidth(rem(20))
        ),

        style(
            md.small,
            maxWidth(rem(24))
        ),

        style(
            md.medium,
            maxWidth(rem(28))
        ),

        style(
            md.large,
            maxWidth(rem(32))
        ),

        style(
            md.xLarge,
            maxWidth(rem(36))
        ),

        style(
            md.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            md.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            md.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            md.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            md.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            md.full,
            maxWidth(pct(100))
        ),

        style(
            md.screenSm,
            maxWidth(px(640))
        ),

        style(
            md.screenMd,
            maxWidth(px(768))
        ),

        style(
            md.screenLg,
            maxWidth(px(1024))
        ),

        style(
            md.screenXl,
            maxWidth(px(1280))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            maxWidth(Keywords.none)
        ),

        style(
            lg.xSmall,
            maxWidth(rem(20))
        ),

        style(
            lg.small,
            maxWidth(rem(24))
        ),

        style(
            lg.medium,
            maxWidth(rem(28))
        ),

        style(
            lg.large,
            maxWidth(rem(32))
        ),

        style(
            lg.xLarge,
            maxWidth(rem(36))
        ),

        style(
            lg.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            lg.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            lg.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            lg.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            lg.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            lg.full,
            maxWidth(pct(100))
        ),

        style(
            lg.screenSm,
            maxWidth(px(640))
        ),

        style(
            lg.screenMd,
            maxWidth(px(768))
        ),

        style(
            lg.screenLg,
            maxWidth(px(1024))
        ),

        style(
            lg.screenXl,
            maxWidth(px(1280))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            maxWidth(Keywords.none)
        ),

        style(
            xl.xSmall,
            maxWidth(rem(20))
        ),

        style(
            xl.small,
            maxWidth(rem(24))
        ),

        style(
            xl.medium,
            maxWidth(rem(28))
        ),

        style(
            xl.large,
            maxWidth(rem(32))
        ),

        style(
            xl.xLarge,
            maxWidth(rem(36))
        ),

        style(
            xl.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            xl.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            xl.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            xl.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            xl.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            xl.full,
            maxWidth(pct(100))
        ),

        style(
            xl.screenSm,
            maxWidth(px(640))
        ),

        style(
            xl.screenMd,
            maxWidth(px(768))
        ),

        style(
            xl.screenLg,
            maxWidth(px(1024))
        ),

        style(
            xl.screenXl,
            maxWidth(px(1280))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.randomDot(5);

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screenSm = Css.randomDot(5);

    ClassSelector screenMd = Css.randomDot(5);

    ClassSelector screenLg = Css.randomDot(5);

    ClassSelector screenXl = Css.randomDot(5);

  }

  public interface md {

    ClassSelector none = Css.randomDot(5);

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screenSm = Css.randomDot(5);

    ClassSelector screenMd = Css.randomDot(5);

    ClassSelector screenLg = Css.randomDot(5);

    ClassSelector screenXl = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector none = Css.randomDot(5);

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screenSm = Css.randomDot(5);

    ClassSelector screenMd = Css.randomDot(5);

    ClassSelector screenLg = Css.randomDot(5);

    ClassSelector screenXl = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector none = Css.randomDot(5);

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

    ClassSelector screenSm = Css.randomDot(5);

    ClassSelector screenMd = Css.randomDot(5);

    ClassSelector screenLg = Css.randomDot(5);

    ClassSelector screenXl = Css.randomDot(5);

  }

}