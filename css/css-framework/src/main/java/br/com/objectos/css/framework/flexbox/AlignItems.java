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
package br.com.objectos.css.framework.flexbox;

import br.com.objectos.code.annotations.Generated;
import objectos.css.Css;
import objectos.css.keyword.Keywords;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class AlignItems extends AbstractStyleSheet {

  public static final ClassSelector stretch = Css.randomDot(5);

  public static final ClassSelector start = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector end = Css.randomDot(5);

  public static final ClassSelector baseline = Css.randomDot(5);

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
        stretch,
        alignItems(Keywords.stretch)
    );
    style(
        start,
        alignItems(Keywords.flexStart)
    );
    style(
        center,
        alignItems(Keywords.center)
    );
    style(
        end,
        alignItems(Keywords.flexEnd)
    );
    style(
        baseline,
        alignItems(Keywords.baseline)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            sm.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            sm.center,
            alignItems(Keywords.center)
        ),

        style(
            sm.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            sm.baseline,
            alignItems(Keywords.baseline)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            md.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            md.center,
            alignItems(Keywords.center)
        ),

        style(
            md.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            md.baseline,
            alignItems(Keywords.baseline)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            lg.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            lg.center,
            alignItems(Keywords.center)
        ),

        style(
            lg.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            lg.baseline,
            alignItems(Keywords.baseline)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            xl.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            xl.center,
            alignItems(Keywords.center)
        ),

        style(
            xl.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            xl.baseline,
            alignItems(Keywords.baseline)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            x2l.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            x2l.center,
            alignItems(Keywords.center)
        ),

        style(
            x2l.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            x2l.baseline,
            alignItems(Keywords.baseline)
        )
    );
  }

  public interface sm {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface md {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

}