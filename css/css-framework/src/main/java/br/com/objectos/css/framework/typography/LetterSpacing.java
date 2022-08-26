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
public final class LetterSpacing extends AbstractStyleSheet {

  public static final ClassSelector tighter = Css.randomDot(5);

  public static final ClassSelector tight = Css.randomDot(5);

  public static final ClassSelector normal = Css.randomDot(5);

  public static final ClassSelector wide = Css.randomDot(5);

  public static final ClassSelector wider = Css.randomDot(5);

  public static final ClassSelector widest = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        tighter,
        letterSpacing(rem(-0.05))
    );
    style(
        tight,
        letterSpacing(rem(-0.025))
    );
    style(
        normal,
        letterSpacing(rem(0))
    );
    style(
        wide,
        letterSpacing(rem(0.025))
    );
    style(
        wider,
        letterSpacing(rem(0.05))
    );
    style(
        widest,
        letterSpacing(rem(0.1))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.tighter,
            letterSpacing(rem(-0.05))
        ),

        style(
            sm.tight,
            letterSpacing(rem(-0.025))
        ),

        style(
            sm.normal,
            letterSpacing(rem(0))
        ),

        style(
            sm.wide,
            letterSpacing(rem(0.025))
        ),

        style(
            sm.wider,
            letterSpacing(rem(0.05))
        ),

        style(
            sm.widest,
            letterSpacing(rem(0.1))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.tighter,
            letterSpacing(rem(-0.05))
        ),

        style(
            md.tight,
            letterSpacing(rem(-0.025))
        ),

        style(
            md.normal,
            letterSpacing(rem(0))
        ),

        style(
            md.wide,
            letterSpacing(rem(0.025))
        ),

        style(
            md.wider,
            letterSpacing(rem(0.05))
        ),

        style(
            md.widest,
            letterSpacing(rem(0.1))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.tighter,
            letterSpacing(rem(-0.05))
        ),

        style(
            lg.tight,
            letterSpacing(rem(-0.025))
        ),

        style(
            lg.normal,
            letterSpacing(rem(0))
        ),

        style(
            lg.wide,
            letterSpacing(rem(0.025))
        ),

        style(
            lg.wider,
            letterSpacing(rem(0.05))
        ),

        style(
            lg.widest,
            letterSpacing(rem(0.1))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.tighter,
            letterSpacing(rem(-0.05))
        ),

        style(
            xl.tight,
            letterSpacing(rem(-0.025))
        ),

        style(
            xl.normal,
            letterSpacing(rem(0))
        ),

        style(
            xl.wide,
            letterSpacing(rem(0.025))
        ),

        style(
            xl.wider,
            letterSpacing(rem(0.05))
        ),

        style(
            xl.widest,
            letterSpacing(rem(0.1))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.tighter,
            letterSpacing(rem(-0.05))
        ),

        style(
            x2l.tight,
            letterSpacing(rem(-0.025))
        ),

        style(
            x2l.normal,
            letterSpacing(rem(0))
        ),

        style(
            x2l.wide,
            letterSpacing(rem(0.025))
        ),

        style(
            x2l.wider,
            letterSpacing(rem(0.05))
        ),

        style(
            x2l.widest,
            letterSpacing(rem(0.1))
        )
    );
  }

  public interface sm {

    ClassSelector tighter = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector wide = Css.randomDot(5);

    ClassSelector wider = Css.randomDot(5);

    ClassSelector widest = Css.randomDot(5);

  }

  public interface md {

    ClassSelector tighter = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector wide = Css.randomDot(5);

    ClassSelector wider = Css.randomDot(5);

    ClassSelector widest = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector tighter = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector wide = Css.randomDot(5);

    ClassSelector wider = Css.randomDot(5);

    ClassSelector widest = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector tighter = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector wide = Css.randomDot(5);

    ClassSelector wider = Css.randomDot(5);

    ClassSelector widest = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector tighter = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector wide = Css.randomDot(5);

    ClassSelector wider = Css.randomDot(5);

    ClassSelector widest = Css.randomDot(5);

  }

}