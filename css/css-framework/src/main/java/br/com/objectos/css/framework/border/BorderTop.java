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
 */
package br.com.objectos.css.framework.border;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BorderTop extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector v1 = Css.randomDot(5);

  public static final ClassSelector v2 = Css.randomDot(5);

  public static final ClassSelector v4 = Css.randomDot(5);

  public static final ClassSelector v8 = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        v0,
        borderTopWidth(zero())
    );
    style(
        v1,
        borderTopWidth(px(1))
    );
    style(
        v2,
        borderTopWidth(px(2))
    );
    style(
        v4,
        borderTopWidth(px(4))
    );
    style(
        v8,
        borderTopWidth(px(8))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v0,
            borderTopWidth(zero())
        ),

        style(
            sm.v1,
            borderTopWidth(px(1))
        ),

        style(
            sm.v2,
            borderTopWidth(px(2))
        ),

        style(
            sm.v4,
            borderTopWidth(px(4))
        ),

        style(
            sm.v8,
            borderTopWidth(px(8))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            borderTopWidth(zero())
        ),

        style(
            md.v1,
            borderTopWidth(px(1))
        ),

        style(
            md.v2,
            borderTopWidth(px(2))
        ),

        style(
            md.v4,
            borderTopWidth(px(4))
        ),

        style(
            md.v8,
            borderTopWidth(px(8))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v0,
            borderTopWidth(zero())
        ),

        style(
            lg.v1,
            borderTopWidth(px(1))
        ),

        style(
            lg.v2,
            borderTopWidth(px(2))
        ),

        style(
            lg.v4,
            borderTopWidth(px(4))
        ),

        style(
            lg.v8,
            borderTopWidth(px(8))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v0,
            borderTopWidth(zero())
        ),

        style(
            xl.v1,
            borderTopWidth(px(1))
        ),

        style(
            xl.v2,
            borderTopWidth(px(2))
        ),

        style(
            xl.v4,
            borderTopWidth(px(4))
        ),

        style(
            xl.v8,
            borderTopWidth(px(8))
        )
    );
  }

  public interface sm {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

}