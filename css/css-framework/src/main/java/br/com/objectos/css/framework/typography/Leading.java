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
import objectos.css.Css;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Leading extends AbstractStyleSheet {

  public static final ClassSelector none = Css.randomDot(5);

  public static final ClassSelector tight = Css.randomDot(5);

  public static final ClassSelector snug = Css.randomDot(5);

  public static final ClassSelector normal = Css.randomDot(5);

  public static final ClassSelector relaxed = Css.randomDot(5);

  public static final ClassSelector loose = Css.randomDot(5);

  public static final ClassSelector v3 = Css.randomDot(5);

  public static final ClassSelector v4 = Css.randomDot(5);

  public static final ClassSelector v5 = Css.randomDot(5);

  public static final ClassSelector v6 = Css.randomDot(5);

  public static final ClassSelector v7 = Css.randomDot(5);

  public static final ClassSelector v8 = Css.randomDot(5);

  public static final ClassSelector v9 = Css.randomDot(5);

  public static final ClassSelector v10 = Css.randomDot(5);

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
        none,
        lineHeight(1)
    );
    style(
        tight,
        lineHeight(1.25)
    );
    style(
        snug,
        lineHeight(1.375)
    );
    style(
        normal,
        lineHeight(1.5)
    );
    style(
        relaxed,
        lineHeight(1.625)
    );
    style(
        loose,
        lineHeight(2)
    );
    style(
        v3,
        lineHeight(rem(0.75))
    );
    style(
        v4,
        lineHeight(rem(1))
    );
    style(
        v5,
        lineHeight(rem(1.25))
    );
    style(
        v6,
        lineHeight(rem(1.5))
    );
    style(
        v7,
        lineHeight(rem(1.75))
    );
    style(
        v8,
        lineHeight(rem(2))
    );
    style(
        v9,
        lineHeight(rem(2.25))
    );
    style(
        v10,
        lineHeight(rem(2.5))
    );
  }

  private void definition1() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            lineHeight(1)
        ),

        style(
            sm.tight,
            lineHeight(1.25)
        ),

        style(
            sm.snug,
            lineHeight(1.375)
        ),

        style(
            sm.normal,
            lineHeight(1.5)
        ),

        style(
            sm.relaxed,
            lineHeight(1.625)
        ),

        style(
            sm.loose,
            lineHeight(2)
        ),

        style(
            sm.v3,
            lineHeight(rem(0.75))
        ),

        style(
            sm.v4,
            lineHeight(rem(1))
        ),

        style(
            sm.v5,
            lineHeight(rem(1.25))
        ),

        style(
            sm.v6,
            lineHeight(rem(1.5))
        ),

        style(
            sm.v7,
            lineHeight(rem(1.75))
        ),

        style(
            sm.v8,
            lineHeight(rem(2))
        ),

        style(
            sm.v9,
            lineHeight(rem(2.25))
        ),

        style(
            sm.v10,
            lineHeight(rem(2.5))
        )
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            lineHeight(1)
        ),

        style(
            md.tight,
            lineHeight(1.25)
        ),

        style(
            md.snug,
            lineHeight(1.375)
        ),

        style(
            md.normal,
            lineHeight(1.5)
        ),

        style(
            md.relaxed,
            lineHeight(1.625)
        ),

        style(
            md.loose,
            lineHeight(2)
        ),

        style(
            md.v3,
            lineHeight(rem(0.75))
        ),

        style(
            md.v4,
            lineHeight(rem(1))
        ),

        style(
            md.v5,
            lineHeight(rem(1.25))
        ),

        style(
            md.v6,
            lineHeight(rem(1.5))
        ),

        style(
            md.v7,
            lineHeight(rem(1.75))
        ),

        style(
            md.v8,
            lineHeight(rem(2))
        ),

        style(
            md.v9,
            lineHeight(rem(2.25))
        ),

        style(
            md.v10,
            lineHeight(rem(2.5))
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            lineHeight(1)
        ),

        style(
            lg.tight,
            lineHeight(1.25)
        ),

        style(
            lg.snug,
            lineHeight(1.375)
        ),

        style(
            lg.normal,
            lineHeight(1.5)
        ),

        style(
            lg.relaxed,
            lineHeight(1.625)
        ),

        style(
            lg.loose,
            lineHeight(2)
        ),

        style(
            lg.v3,
            lineHeight(rem(0.75))
        ),

        style(
            lg.v4,
            lineHeight(rem(1))
        ),

        style(
            lg.v5,
            lineHeight(rem(1.25))
        ),

        style(
            lg.v6,
            lineHeight(rem(1.5))
        ),

        style(
            lg.v7,
            lineHeight(rem(1.75))
        ),

        style(
            lg.v8,
            lineHeight(rem(2))
        ),

        style(
            lg.v9,
            lineHeight(rem(2.25))
        ),

        style(
            lg.v10,
            lineHeight(rem(2.5))
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            lineHeight(1)
        ),

        style(
            xl.tight,
            lineHeight(1.25)
        ),

        style(
            xl.snug,
            lineHeight(1.375)
        ),

        style(
            xl.normal,
            lineHeight(1.5)
        ),

        style(
            xl.relaxed,
            lineHeight(1.625)
        ),

        style(
            xl.loose,
            lineHeight(2)
        ),

        style(
            xl.v3,
            lineHeight(rem(0.75))
        ),

        style(
            xl.v4,
            lineHeight(rem(1))
        ),

        style(
            xl.v5,
            lineHeight(rem(1.25))
        ),

        style(
            xl.v6,
            lineHeight(rem(1.5))
        ),

        style(
            xl.v7,
            lineHeight(rem(1.75))
        ),

        style(
            xl.v8,
            lineHeight(rem(2))
        ),

        style(
            xl.v9,
            lineHeight(rem(2.25))
        ),

        style(
            xl.v10,
            lineHeight(rem(2.5))
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.none,
            lineHeight(1)
        ),

        style(
            x2l.tight,
            lineHeight(1.25)
        ),

        style(
            x2l.snug,
            lineHeight(1.375)
        ),

        style(
            x2l.normal,
            lineHeight(1.5)
        ),

        style(
            x2l.relaxed,
            lineHeight(1.625)
        ),

        style(
            x2l.loose,
            lineHeight(2)
        ),

        style(
            x2l.v3,
            lineHeight(rem(0.75))
        ),

        style(
            x2l.v4,
            lineHeight(rem(1))
        ),

        style(
            x2l.v5,
            lineHeight(rem(1.25))
        ),

        style(
            x2l.v6,
            lineHeight(rem(1.5))
        ),

        style(
            x2l.v7,
            lineHeight(rem(1.75))
        ),

        style(
            x2l.v8,
            lineHeight(rem(2))
        ),

        style(
            x2l.v9,
            lineHeight(rem(2.25))
        ),

        style(
            x2l.v10,
            lineHeight(rem(2.5))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

}