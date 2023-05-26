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
package br.com.objectos.css.framework.background;

import br.com.objectos.code.annotations.Generated;
import objectos.css.Css;
import objectos.css.keyword.Keywords;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BackgroundPosition extends AbstractStyleSheet {

  public static final ClassSelector bottom = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector left = Css.randomDot(5);

  public static final ClassSelector leftBottom = Css.randomDot(5);

  public static final ClassSelector leftTop = Css.randomDot(5);

  public static final ClassSelector right = Css.randomDot(5);

  public static final ClassSelector rightBottom = Css.randomDot(5);

  public static final ClassSelector rightTop = Css.randomDot(5);

  public static final ClassSelector top = Css.randomDot(5);

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
        bottom,
        backgroundPosition(Keywords.bottom)
    );
    style(
        center,
        backgroundPosition(Keywords.center)
    );
    style(
        left,
        backgroundPosition(Keywords.left)
    );
    style(
        leftBottom,
        backgroundPosition(Keywords.left, Keywords.bottom)
    );
    style(
        leftTop,
        backgroundPosition(Keywords.left, Keywords.top)
    );
    style(
        right,
        backgroundPosition(Keywords.right)
    );
    style(
        rightBottom,
        backgroundPosition(Keywords.right, Keywords.bottom)
    );
    style(
        rightTop,
        backgroundPosition(Keywords.right, Keywords.top)
    );
    style(
        top,
        backgroundPosition(Keywords.top)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            sm.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            sm.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            sm.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            sm.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            sm.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            sm.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            sm.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            sm.top,
            backgroundPosition(Keywords.top)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            md.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            md.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            md.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            md.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            md.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            md.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            md.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            md.top,
            backgroundPosition(Keywords.top)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            lg.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            lg.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            lg.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            lg.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            lg.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            lg.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            lg.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            lg.top,
            backgroundPosition(Keywords.top)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            xl.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            xl.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            xl.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            xl.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            xl.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            xl.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            xl.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            xl.top,
            backgroundPosition(Keywords.top)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            x2l.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            x2l.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            x2l.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            x2l.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            x2l.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            x2l.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            x2l.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            x2l.top,
            backgroundPosition(Keywords.top)
        )
    );
  }

  public interface sm {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface md {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

}