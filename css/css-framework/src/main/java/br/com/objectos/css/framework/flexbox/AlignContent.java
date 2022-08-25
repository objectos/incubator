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
package br.com.objectos.css.framework.flexbox;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class AlignContent extends AbstractStyleSheet {

  public static final ClassSelector start = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector end = Css.randomDot(5);

  public static final ClassSelector between = Css.randomDot(5);

  public static final ClassSelector around = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        start,
        alignContent(Keywords.flexStart)
    );
    style(
        center,
        alignContent(Keywords.center)
    );
    style(
        end,
        alignContent(Keywords.flexEnd)
    );
    style(
        between,
        alignContent(Keywords.spaceBetween)
    );
    style(
        around,
        alignContent(Keywords.spaceAround)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            sm.center,
            alignContent(Keywords.center)
        ),

        style(
            sm.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            sm.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            sm.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            md.center,
            alignContent(Keywords.center)
        ),

        style(
            md.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            md.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            md.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            lg.center,
            alignContent(Keywords.center)
        ),

        style(
            lg.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            lg.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            lg.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            xl.center,
            alignContent(Keywords.center)
        ),

        style(
            xl.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            xl.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            xl.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            x2l.center,
            alignContent(Keywords.center)
        ),

        style(
            x2l.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            x2l.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            x2l.around,
            alignContent(Keywords.spaceAround)
        )
    );
  }

  public interface sm {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface md {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

}