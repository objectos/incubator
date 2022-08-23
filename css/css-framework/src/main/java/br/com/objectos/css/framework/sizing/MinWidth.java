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
public final class MinWidth extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector full = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        v0,
        minWidth(zero())
    );
    style(
        full,
        minWidth(pct(100))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v0,
            minWidth(zero())
        ),

        style(
            sm.full,
            minWidth(pct(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            minWidth(zero())
        ),

        style(
            md.full,
            minWidth(pct(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v0,
            minWidth(zero())
        ),

        style(
            lg.full,
            minWidth(pct(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v0,
            minWidth(zero())
        ),

        style(
            xl.full,
            minWidth(pct(100))
        )
    );
  }

  public interface sm {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface md {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector full = Css.randomDot(5);

  }

}