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
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BorderStyle extends AbstractStyleSheet {

  public static final ClassSelector solid = Css.randomDot(5);

  public static final ClassSelector dashed = Css.randomDot(5);

  public static final ClassSelector dotted = Css.randomDot(5);

  public static final ClassSelector doubleValue = Css.randomDot(5);

  public static final ClassSelector none = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        solid,
        borderStyle(Keywords.solid)
    );
    style(
        dashed,
        borderStyle(Keywords.dashed)
    );
    style(
        dotted,
        borderStyle(Keywords.dotted)
    );
    style(
        doubleValue,
        borderStyle(Keywords.doubleKw)
    );
    style(
        none,
        borderStyle(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            sm.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            sm.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            sm.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            sm.none,
            borderStyle(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            md.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            md.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            md.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            md.none,
            borderStyle(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            lg.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            lg.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            lg.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            lg.none,
            borderStyle(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            xl.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            xl.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            xl.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            xl.none,
            borderStyle(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector solid = Css.randomDot(5);

    ClassSelector dashed = Css.randomDot(5);

    ClassSelector dotted = Css.randomDot(5);

    ClassSelector doubleValue = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface md {

    ClassSelector solid = Css.randomDot(5);

    ClassSelector dashed = Css.randomDot(5);

    ClassSelector dotted = Css.randomDot(5);

    ClassSelector doubleValue = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector solid = Css.randomDot(5);

    ClassSelector dashed = Css.randomDot(5);

    ClassSelector dotted = Css.randomDot(5);

    ClassSelector doubleValue = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector solid = Css.randomDot(5);

    ClassSelector dashed = Css.randomDot(5);

    ClassSelector dotted = Css.randomDot(5);

    ClassSelector doubleValue = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

}