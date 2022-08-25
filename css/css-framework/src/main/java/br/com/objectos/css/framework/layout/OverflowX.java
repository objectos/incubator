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
package br.com.objectos.css.framework.layout;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class OverflowX extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector hidden = Css.randomDot(5);

  public static final ClassSelector visible = Css.randomDot(5);

  public static final ClassSelector scroll = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        auto,
        overflowX(Keywords.auto)
    );
    style(
        hidden,
        overflowX(Keywords.hidden)
    );
    style(
        visible,
        overflowX(Keywords.visible)
    );
    style(
        scroll,
        overflowX(Keywords.scroll)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            overflowX(Keywords.auto)
        ),

        style(
            sm.hidden,
            overflowX(Keywords.hidden)
        ),

        style(
            sm.visible,
            overflowX(Keywords.visible)
        ),

        style(
            sm.scroll,
            overflowX(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            overflowX(Keywords.auto)
        ),

        style(
            md.hidden,
            overflowX(Keywords.hidden)
        ),

        style(
            md.visible,
            overflowX(Keywords.visible)
        ),

        style(
            md.scroll,
            overflowX(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            overflowX(Keywords.auto)
        ),

        style(
            lg.hidden,
            overflowX(Keywords.hidden)
        ),

        style(
            lg.visible,
            overflowX(Keywords.visible)
        ),

        style(
            lg.scroll,
            overflowX(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            overflowX(Keywords.auto)
        ),

        style(
            xl.hidden,
            overflowX(Keywords.hidden)
        ),

        style(
            xl.visible,
            overflowX(Keywords.visible)
        ),

        style(
            xl.scroll,
            overflowX(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.auto,
            overflowX(Keywords.auto)
        ),

        style(
            x2l.hidden,
            overflowX(Keywords.hidden)
        ),

        style(
            x2l.visible,
            overflowX(Keywords.visible)
        ),

        style(
            x2l.scroll,
            overflowX(Keywords.scroll)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

}