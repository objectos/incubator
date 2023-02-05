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
package br.com.objectos.css.framework.typography;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class FontStyle extends AbstractStyleSheet {

  public static final ClassSelector italic = Css.randomDot(5);

  public static final ClassSelector normal = Css.randomDot(5);

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
        italic,
        fontStyle(Keywords.italic)
    );
    style(
        normal,
        fontStyle(Keywords.normal)
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            sm.normal,
            fontStyle(Keywords.normal)
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            md.normal,
            fontStyle(Keywords.normal)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            lg.normal,
            fontStyle(Keywords.normal)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            xl.normal,
            fontStyle(Keywords.normal)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            x2l.normal,
            fontStyle(Keywords.normal)
        )
    );
  }

  public interface sm {

    ClassSelector italic = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

  }

  public interface md {

    ClassSelector italic = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector italic = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector italic = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector italic = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

  }

}