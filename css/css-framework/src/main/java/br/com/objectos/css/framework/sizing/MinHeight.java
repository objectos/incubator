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
package br.com.objectos.css.framework.sizing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class MinHeight extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.dot("min-h-0");

  public static final ClassSelector full = Css.dot("min-h-full");

  public static final ClassSelector screen = Css.dot("min-h-screen");

  @Override
  protected final void definition() {
    style(
        v0,
        minHeight(zero())
    );
    style(
        full,
        minHeight(pct(100))
    );
    style(
        screen,
        minHeight(vh(100))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v0,
            minHeight(zero())
        ),

        style(
            sm.full,
            minHeight(pct(100))
        ),

        style(
            sm.screen,
            minHeight(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            minHeight(zero())
        ),

        style(
            md.full,
            minHeight(pct(100))
        ),

        style(
            md.screen,
            minHeight(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v0,
            minHeight(zero())
        ),

        style(
            lg.full,
            minHeight(pct(100))
        ),

        style(
            lg.screen,
            minHeight(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v0,
            minHeight(zero())
        ),

        style(
            xl.full,
            minHeight(pct(100))
        ),

        style(
            xl.screen,
            minHeight(vh(100))
        )
    );
  }

  public interface sm {

    ClassSelector v0 = Css.dot("sm-min-h-0");

    ClassSelector full = Css.dot("sm-min-h-full");

    ClassSelector screen = Css.dot("sm-min-h-screen");

  }

  public interface md {

    ClassSelector v0 = Css.dot("md-min-h-0");

    ClassSelector full = Css.dot("md-min-h-full");

    ClassSelector screen = Css.dot("md-min-h-screen");

  }

  public interface lg {

    ClassSelector v0 = Css.dot("lg-min-h-0");

    ClassSelector full = Css.dot("lg-min-h-full");

    ClassSelector screen = Css.dot("lg-min-h-screen");

  }

  public interface xl {

    ClassSelector v0 = Css.dot("xl-min-h-0");

    ClassSelector full = Css.dot("xl-min-h-full");

    ClassSelector screen = Css.dot("xl-min-h-screen");

  }

}