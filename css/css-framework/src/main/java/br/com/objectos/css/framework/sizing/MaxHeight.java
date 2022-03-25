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
public final class MaxHeight extends AbstractStyleSheet {

  public static final ClassSelector full = Css.dot("max-h-full");

  public static final ClassSelector screen = Css.dot("max-h-screen");

  @Override
  protected final void definition() {
    style(
        full,
        maxHeight(pct(100))
    );
    style(
        screen,
        maxHeight(vh(100))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.full,
            maxHeight(pct(100))
        ),

        style(
            sm.screen,
            maxHeight(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.full,
            maxHeight(pct(100))
        ),

        style(
            md.screen,
            maxHeight(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.full,
            maxHeight(pct(100))
        ),

        style(
            lg.screen,
            maxHeight(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.full,
            maxHeight(pct(100))
        ),

        style(
            xl.screen,
            maxHeight(vh(100))
        )
    );
  }

  public interface sm {

    ClassSelector full = Css.dot("sm-max-h-full");

    ClassSelector screen = Css.dot("sm-max-h-screen");

  }

  public interface md {

    ClassSelector full = Css.dot("md-max-h-full");

    ClassSelector screen = Css.dot("md-max-h-screen");

  }

  public interface lg {

    ClassSelector full = Css.dot("lg-max-h-full");

    ClassSelector screen = Css.dot("lg-max-h-screen");

  }

  public interface xl {

    ClassSelector full = Css.dot("xl-max-h-full");

    ClassSelector screen = Css.dot("xl-max-h-screen");

  }

}