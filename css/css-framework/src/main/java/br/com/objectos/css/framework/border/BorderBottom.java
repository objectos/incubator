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
package br.com.objectos.css.framework.border;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BorderBottom extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector v1 = Css.randomDot(5);

  public static final ClassSelector v2 = Css.randomDot(5);

  public static final ClassSelector v4 = Css.randomDot(5);

  public static final ClassSelector v8 = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        v0,
        borderBottomWidth(zero())
    );
    style(
        v1,
        borderBottomWidth(px(1))
    );
    style(
        v2,
        borderBottomWidth(px(2))
    );
    style(
        v4,
        borderBottomWidth(px(4))
    );
    style(
        v8,
        borderBottomWidth(px(8))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v0,
            borderBottomWidth(zero())
        ),

        style(
            sm.v1,
            borderBottomWidth(px(1))
        ),

        style(
            sm.v2,
            borderBottomWidth(px(2))
        ),

        style(
            sm.v4,
            borderBottomWidth(px(4))
        ),

        style(
            sm.v8,
            borderBottomWidth(px(8))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            borderBottomWidth(zero())
        ),

        style(
            md.v1,
            borderBottomWidth(px(1))
        ),

        style(
            md.v2,
            borderBottomWidth(px(2))
        ),

        style(
            md.v4,
            borderBottomWidth(px(4))
        ),

        style(
            md.v8,
            borderBottomWidth(px(8))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v0,
            borderBottomWidth(zero())
        ),

        style(
            lg.v1,
            borderBottomWidth(px(1))
        ),

        style(
            lg.v2,
            borderBottomWidth(px(2))
        ),

        style(
            lg.v4,
            borderBottomWidth(px(4))
        ),

        style(
            lg.v8,
            borderBottomWidth(px(8))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v0,
            borderBottomWidth(zero())
        ),

        style(
            xl.v1,
            borderBottomWidth(px(1))
        ),

        style(
            xl.v2,
            borderBottomWidth(px(2))
        ),

        style(
            xl.v4,
            borderBottomWidth(px(4))
        ),

        style(
            xl.v8,
            borderBottomWidth(px(8))
        )
    );
  }

  public interface sm {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

}