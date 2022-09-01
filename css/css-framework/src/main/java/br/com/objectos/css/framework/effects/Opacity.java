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
package br.com.objectos.css.framework.effects;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Opacity extends AbstractStyleSheet {

  public static final ClassSelector v000 = Css.randomDot(5);

  public static final ClassSelector v005 = Css.randomDot(5);

  public static final ClassSelector v010 = Css.randomDot(5);

  public static final ClassSelector v020 = Css.randomDot(5);

  public static final ClassSelector v030 = Css.randomDot(5);

  public static final ClassSelector v040 = Css.randomDot(5);

  public static final ClassSelector v050 = Css.randomDot(5);

  public static final ClassSelector v060 = Css.randomDot(5);

  public static final ClassSelector v070 = Css.randomDot(5);

  public static final ClassSelector v080 = Css.randomDot(5);

  public static final ClassSelector v090 = Css.randomDot(5);

  public static final ClassSelector v095 = Css.randomDot(5);

  public static final ClassSelector v100 = Css.randomDot(5);

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
        v000,
        opacity(zero())
    );
    style(
        v005,
        opacity(0.05)
    );
    style(
        v010,
        opacity(0.1)
    );
    style(
        v020,
        opacity(0.2)
    );
    style(
        v030,
        opacity(0.3)
    );
    style(
        v040,
        opacity(0.4)
    );
    style(
        v050,
        opacity(0.5)
    );
    style(
        v060,
        opacity(0.6)
    );
    style(
        v070,
        opacity(0.7)
    );
    style(
        v080,
        opacity(0.8)
    );
    style(
        v090,
        opacity(0.9)
    );
    style(
        v095,
        opacity(0.95)
    );
    style(
        v100,
        opacity(1)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v000,
            opacity(zero())
        ),

        style(
            sm.v005,
            opacity(0.05)
        ),

        style(
            sm.v010,
            opacity(0.1)
        ),

        style(
            sm.v020,
            opacity(0.2)
        ),

        style(
            sm.v030,
            opacity(0.3)
        ),

        style(
            sm.v040,
            opacity(0.4)
        ),

        style(
            sm.v050,
            opacity(0.5)
        ),

        style(
            sm.v060,
            opacity(0.6)
        ),

        style(
            sm.v070,
            opacity(0.7)
        ),

        style(
            sm.v080,
            opacity(0.8)
        ),

        style(
            sm.v090,
            opacity(0.9)
        ),

        style(
            sm.v095,
            opacity(0.95)
        ),

        style(
            sm.v100,
            opacity(1)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v000,
            opacity(zero())
        ),

        style(
            md.v005,
            opacity(0.05)
        ),

        style(
            md.v010,
            opacity(0.1)
        ),

        style(
            md.v020,
            opacity(0.2)
        ),

        style(
            md.v030,
            opacity(0.3)
        ),

        style(
            md.v040,
            opacity(0.4)
        ),

        style(
            md.v050,
            opacity(0.5)
        ),

        style(
            md.v060,
            opacity(0.6)
        ),

        style(
            md.v070,
            opacity(0.7)
        ),

        style(
            md.v080,
            opacity(0.8)
        ),

        style(
            md.v090,
            opacity(0.9)
        ),

        style(
            md.v095,
            opacity(0.95)
        ),

        style(
            md.v100,
            opacity(1)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v000,
            opacity(zero())
        ),

        style(
            lg.v005,
            opacity(0.05)
        ),

        style(
            lg.v010,
            opacity(0.1)
        ),

        style(
            lg.v020,
            opacity(0.2)
        ),

        style(
            lg.v030,
            opacity(0.3)
        ),

        style(
            lg.v040,
            opacity(0.4)
        ),

        style(
            lg.v050,
            opacity(0.5)
        ),

        style(
            lg.v060,
            opacity(0.6)
        ),

        style(
            lg.v070,
            opacity(0.7)
        ),

        style(
            lg.v080,
            opacity(0.8)
        ),

        style(
            lg.v090,
            opacity(0.9)
        ),

        style(
            lg.v095,
            opacity(0.95)
        ),

        style(
            lg.v100,
            opacity(1)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v000,
            opacity(zero())
        ),

        style(
            xl.v005,
            opacity(0.05)
        ),

        style(
            xl.v010,
            opacity(0.1)
        ),

        style(
            xl.v020,
            opacity(0.2)
        ),

        style(
            xl.v030,
            opacity(0.3)
        ),

        style(
            xl.v040,
            opacity(0.4)
        ),

        style(
            xl.v050,
            opacity(0.5)
        ),

        style(
            xl.v060,
            opacity(0.6)
        ),

        style(
            xl.v070,
            opacity(0.7)
        ),

        style(
            xl.v080,
            opacity(0.8)
        ),

        style(
            xl.v090,
            opacity(0.9)
        ),

        style(
            xl.v095,
            opacity(0.95)
        ),

        style(
            xl.v100,
            opacity(1)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.v000,
            opacity(zero())
        ),

        style(
            x2l.v005,
            opacity(0.05)
        ),

        style(
            x2l.v010,
            opacity(0.1)
        ),

        style(
            x2l.v020,
            opacity(0.2)
        ),

        style(
            x2l.v030,
            opacity(0.3)
        ),

        style(
            x2l.v040,
            opacity(0.4)
        ),

        style(
            x2l.v050,
            opacity(0.5)
        ),

        style(
            x2l.v060,
            opacity(0.6)
        ),

        style(
            x2l.v070,
            opacity(0.7)
        ),

        style(
            x2l.v080,
            opacity(0.8)
        ),

        style(
            x2l.v090,
            opacity(0.9)
        ),

        style(
            x2l.v095,
            opacity(0.95)
        ),

        style(
            x2l.v100,
            opacity(1)
        )
    );
  }

  public interface sm {

    ClassSelector v000 = Css.randomDot(5);

    ClassSelector v005 = Css.randomDot(5);

    ClassSelector v010 = Css.randomDot(5);

    ClassSelector v020 = Css.randomDot(5);

    ClassSelector v030 = Css.randomDot(5);

    ClassSelector v040 = Css.randomDot(5);

    ClassSelector v050 = Css.randomDot(5);

    ClassSelector v060 = Css.randomDot(5);

    ClassSelector v070 = Css.randomDot(5);

    ClassSelector v080 = Css.randomDot(5);

    ClassSelector v090 = Css.randomDot(5);

    ClassSelector v095 = Css.randomDot(5);

    ClassSelector v100 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector v000 = Css.randomDot(5);

    ClassSelector v005 = Css.randomDot(5);

    ClassSelector v010 = Css.randomDot(5);

    ClassSelector v020 = Css.randomDot(5);

    ClassSelector v030 = Css.randomDot(5);

    ClassSelector v040 = Css.randomDot(5);

    ClassSelector v050 = Css.randomDot(5);

    ClassSelector v060 = Css.randomDot(5);

    ClassSelector v070 = Css.randomDot(5);

    ClassSelector v080 = Css.randomDot(5);

    ClassSelector v090 = Css.randomDot(5);

    ClassSelector v095 = Css.randomDot(5);

    ClassSelector v100 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector v000 = Css.randomDot(5);

    ClassSelector v005 = Css.randomDot(5);

    ClassSelector v010 = Css.randomDot(5);

    ClassSelector v020 = Css.randomDot(5);

    ClassSelector v030 = Css.randomDot(5);

    ClassSelector v040 = Css.randomDot(5);

    ClassSelector v050 = Css.randomDot(5);

    ClassSelector v060 = Css.randomDot(5);

    ClassSelector v070 = Css.randomDot(5);

    ClassSelector v080 = Css.randomDot(5);

    ClassSelector v090 = Css.randomDot(5);

    ClassSelector v095 = Css.randomDot(5);

    ClassSelector v100 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector v000 = Css.randomDot(5);

    ClassSelector v005 = Css.randomDot(5);

    ClassSelector v010 = Css.randomDot(5);

    ClassSelector v020 = Css.randomDot(5);

    ClassSelector v030 = Css.randomDot(5);

    ClassSelector v040 = Css.randomDot(5);

    ClassSelector v050 = Css.randomDot(5);

    ClassSelector v060 = Css.randomDot(5);

    ClassSelector v070 = Css.randomDot(5);

    ClassSelector v080 = Css.randomDot(5);

    ClassSelector v090 = Css.randomDot(5);

    ClassSelector v095 = Css.randomDot(5);

    ClassSelector v100 = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector v000 = Css.randomDot(5);

    ClassSelector v005 = Css.randomDot(5);

    ClassSelector v010 = Css.randomDot(5);

    ClassSelector v020 = Css.randomDot(5);

    ClassSelector v030 = Css.randomDot(5);

    ClassSelector v040 = Css.randomDot(5);

    ClassSelector v050 = Css.randomDot(5);

    ClassSelector v060 = Css.randomDot(5);

    ClassSelector v070 = Css.randomDot(5);

    ClassSelector v080 = Css.randomDot(5);

    ClassSelector v090 = Css.randomDot(5);

    ClassSelector v095 = Css.randomDot(5);

    ClassSelector v100 = Css.randomDot(5);

  }

}