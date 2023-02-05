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
package br.com.objectos.css.framework.layout;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class ZIndex extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector v00 = Css.randomDot(5);

  public static final ClassSelector v10 = Css.randomDot(5);

  public static final ClassSelector v20 = Css.randomDot(5);

  public static final ClassSelector v30 = Css.randomDot(5);

  public static final ClassSelector v40 = Css.randomDot(5);

  public static final ClassSelector v50 = Css.randomDot(5);

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
        auto,
        zIndex(Keywords.auto)
    );
    style(
        v00,
        zIndex(zero())
    );
    style(
        v10,
        zIndex(10)
    );
    style(
        v20,
        zIndex(20)
    );
    style(
        v30,
        zIndex(30)
    );
    style(
        v40,
        zIndex(40)
    );
    style(
        v50,
        zIndex(50)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            zIndex(Keywords.auto)
        ),

        style(
            sm.v00,
            zIndex(zero())
        ),

        style(
            sm.v10,
            zIndex(10)
        ),

        style(
            sm.v20,
            zIndex(20)
        ),

        style(
            sm.v30,
            zIndex(30)
        ),

        style(
            sm.v40,
            zIndex(40)
        ),

        style(
            sm.v50,
            zIndex(50)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            zIndex(Keywords.auto)
        ),

        style(
            md.v00,
            zIndex(zero())
        ),

        style(
            md.v10,
            zIndex(10)
        ),

        style(
            md.v20,
            zIndex(20)
        ),

        style(
            md.v30,
            zIndex(30)
        ),

        style(
            md.v40,
            zIndex(40)
        ),

        style(
            md.v50,
            zIndex(50)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            zIndex(Keywords.auto)
        ),

        style(
            lg.v00,
            zIndex(zero())
        ),

        style(
            lg.v10,
            zIndex(10)
        ),

        style(
            lg.v20,
            zIndex(20)
        ),

        style(
            lg.v30,
            zIndex(30)
        ),

        style(
            lg.v40,
            zIndex(40)
        ),

        style(
            lg.v50,
            zIndex(50)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            zIndex(Keywords.auto)
        ),

        style(
            xl.v00,
            zIndex(zero())
        ),

        style(
            xl.v10,
            zIndex(10)
        ),

        style(
            xl.v20,
            zIndex(20)
        ),

        style(
            xl.v30,
            zIndex(30)
        ),

        style(
            xl.v40,
            zIndex(40)
        ),

        style(
            xl.v50,
            zIndex(50)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.auto,
            zIndex(Keywords.auto)
        ),

        style(
            x2l.v00,
            zIndex(zero())
        ),

        style(
            x2l.v10,
            zIndex(10)
        ),

        style(
            x2l.v20,
            zIndex(20)
        ),

        style(
            x2l.v30,
            zIndex(30)
        ),

        style(
            x2l.v40,
            zIndex(40)
        ),

        style(
            x2l.v50,
            zIndex(50)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector v00 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v30 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v50 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector v00 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v30 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v50 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector v00 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v30 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v50 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector v00 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v30 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v50 = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector v00 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v30 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v50 = Css.randomDot(5);

  }

}