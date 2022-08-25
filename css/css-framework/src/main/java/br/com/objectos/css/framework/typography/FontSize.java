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
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class FontSize extends AbstractStyleSheet {

  public static final ClassSelector xSmall = Css.randomDot(5);

  public static final ClassSelector small = Css.randomDot(5);

  public static final ClassSelector base = Css.randomDot(5);

  public static final ClassSelector large = Css.randomDot(5);

  public static final ClassSelector xLarge = Css.randomDot(5);

  public static final ClassSelector xLarge2 = Css.randomDot(5);

  public static final ClassSelector xLarge3 = Css.randomDot(5);

  public static final ClassSelector xLarge4 = Css.randomDot(5);

  public static final ClassSelector xLarge5 = Css.randomDot(5);

  public static final ClassSelector xLarge6 = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        xSmall,
        fontSize(rem(0.75))
    );
    style(
        small,
        fontSize(rem(0.875))
    );
    style(
        base,
        fontSize(rem(1))
    );
    style(
        large,
        fontSize(rem(1.125))
    );
    style(
        xLarge,
        fontSize(rem(1.25))
    );
    style(
        xLarge2,
        fontSize(rem(1.5))
    );
    style(
        xLarge3,
        fontSize(rem(1.875))
    );
    style(
        xLarge4,
        fontSize(rem(2.25))
    );
    style(
        xLarge5,
        fontSize(rem(3))
    );
    style(
        xLarge6,
        fontSize(rem(4))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            sm.small,
            fontSize(rem(0.875))
        ),

        style(
            sm.base,
            fontSize(rem(1))
        ),

        style(
            sm.large,
            fontSize(rem(1.125))
        ),

        style(
            sm.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            sm.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            sm.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            sm.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            sm.xLarge5,
            fontSize(rem(3))
        ),

        style(
            sm.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            md.small,
            fontSize(rem(0.875))
        ),

        style(
            md.base,
            fontSize(rem(1))
        ),

        style(
            md.large,
            fontSize(rem(1.125))
        ),

        style(
            md.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            md.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            md.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            md.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            md.xLarge5,
            fontSize(rem(3))
        ),

        style(
            md.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            lg.small,
            fontSize(rem(0.875))
        ),

        style(
            lg.base,
            fontSize(rem(1))
        ),

        style(
            lg.large,
            fontSize(rem(1.125))
        ),

        style(
            lg.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            lg.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            lg.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            lg.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            lg.xLarge5,
            fontSize(rem(3))
        ),

        style(
            lg.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            xl.small,
            fontSize(rem(0.875))
        ),

        style(
            xl.base,
            fontSize(rem(1))
        ),

        style(
            xl.large,
            fontSize(rem(1.125))
        ),

        style(
            xl.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            xl.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            xl.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            xl.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            xl.xLarge5,
            fontSize(rem(3))
        ),

        style(
            xl.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            x2l.small,
            fontSize(rem(0.875))
        ),

        style(
            x2l.base,
            fontSize(rem(1))
        ),

        style(
            x2l.large,
            fontSize(rem(1.125))
        ),

        style(
            x2l.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            x2l.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            x2l.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            x2l.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            x2l.xLarge5,
            fontSize(rem(3))
        ),

        style(
            x2l.xLarge6,
            fontSize(rem(4))
        )
    );
  }

  public interface sm {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector base = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector base = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector base = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector base = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector base = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector xLarge3 = Css.randomDot(5);

    ClassSelector xLarge4 = Css.randomDot(5);

    ClassSelector xLarge5 = Css.randomDot(5);

    ClassSelector xLarge6 = Css.randomDot(5);

  }

}