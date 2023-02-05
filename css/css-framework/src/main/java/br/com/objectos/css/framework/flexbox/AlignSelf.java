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
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class AlignSelf extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector start = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector end = Css.randomDot(5);

  public static final ClassSelector stretch = Css.randomDot(5);

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
        alignSelf(Keywords.auto)
    );
    style(
        start,
        alignSelf(Keywords.flexStart)
    );
    style(
        center,
        alignSelf(Keywords.center)
    );
    style(
        end,
        alignSelf(Keywords.flexEnd)
    );
    style(
        stretch,
        alignSelf(Keywords.stretch)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            sm.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            sm.center,
            alignSelf(Keywords.center)
        ),

        style(
            sm.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            sm.stretch,
            alignSelf(Keywords.stretch)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            md.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            md.center,
            alignSelf(Keywords.center)
        ),

        style(
            md.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            md.stretch,
            alignSelf(Keywords.stretch)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            lg.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            lg.center,
            alignSelf(Keywords.center)
        ),

        style(
            lg.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            lg.stretch,
            alignSelf(Keywords.stretch)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            xl.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            xl.center,
            alignSelf(Keywords.center)
        ),

        style(
            xl.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            xl.stretch,
            alignSelf(Keywords.stretch)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            x2l.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            x2l.center,
            alignSelf(Keywords.center)
        ),

        style(
            x2l.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            x2l.stretch,
            alignSelf(Keywords.stretch)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector stretch = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector stretch = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector stretch = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector stretch = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector stretch = Css.randomDot(5);

  }

}