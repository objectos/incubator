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
import objectos.css.Css;
import objectos.css.keyword.Keywords;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class ObjectFit extends AbstractStyleSheet {

  public static final ClassSelector contain = Css.randomDot(5);

  public static final ClassSelector cover = Css.randomDot(5);

  public static final ClassSelector fill = Css.randomDot(5);

  public static final ClassSelector none = Css.randomDot(5);

  public static final ClassSelector scaleDown = Css.randomDot(5);

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
        contain,
        objectFit(Keywords.contain)
    );
    style(
        cover,
        objectFit(Keywords.cover)
    );
    style(
        fill,
        objectFit(Keywords.fill)
    );
    style(
        none,
        objectFit(Keywords.none)
    );
    style(
        scaleDown,
        objectFit(Keywords.scaleDown)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.contain,
            objectFit(Keywords.contain)
        ),

        style(
            sm.cover,
            objectFit(Keywords.cover)
        ),

        style(
            sm.fill,
            objectFit(Keywords.fill)
        ),

        style(
            sm.none,
            objectFit(Keywords.none)
        ),

        style(
            sm.scaleDown,
            objectFit(Keywords.scaleDown)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.contain,
            objectFit(Keywords.contain)
        ),

        style(
            md.cover,
            objectFit(Keywords.cover)
        ),

        style(
            md.fill,
            objectFit(Keywords.fill)
        ),

        style(
            md.none,
            objectFit(Keywords.none)
        ),

        style(
            md.scaleDown,
            objectFit(Keywords.scaleDown)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.contain,
            objectFit(Keywords.contain)
        ),

        style(
            lg.cover,
            objectFit(Keywords.cover)
        ),

        style(
            lg.fill,
            objectFit(Keywords.fill)
        ),

        style(
            lg.none,
            objectFit(Keywords.none)
        ),

        style(
            lg.scaleDown,
            objectFit(Keywords.scaleDown)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.contain,
            objectFit(Keywords.contain)
        ),

        style(
            xl.cover,
            objectFit(Keywords.cover)
        ),

        style(
            xl.fill,
            objectFit(Keywords.fill)
        ),

        style(
            xl.none,
            objectFit(Keywords.none)
        ),

        style(
            xl.scaleDown,
            objectFit(Keywords.scaleDown)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.contain,
            objectFit(Keywords.contain)
        ),

        style(
            x2l.cover,
            objectFit(Keywords.cover)
        ),

        style(
            x2l.fill,
            objectFit(Keywords.fill)
        ),

        style(
            x2l.none,
            objectFit(Keywords.none)
        ),

        style(
            x2l.scaleDown,
            objectFit(Keywords.scaleDown)
        )
    );
  }

  public interface sm {

    ClassSelector contain = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector fill = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector scaleDown = Css.randomDot(5);

  }

  public interface md {

    ClassSelector contain = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector fill = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector scaleDown = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector contain = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector fill = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector scaleDown = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector contain = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector fill = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector scaleDown = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector contain = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector fill = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector scaleDown = Css.randomDot(5);

  }

}