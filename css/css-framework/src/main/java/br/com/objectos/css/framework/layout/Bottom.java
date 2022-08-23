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
package br.com.objectos.css.framework.layout;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Bottom extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector auto = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        v0,
        bottom(zero())
    );
    style(
        auto,
        bottom(Keywords.auto)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v0,
            bottom(zero())
        ),

        style(
            sm.auto,
            bottom(Keywords.auto)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            bottom(zero())
        ),

        style(
            md.auto,
            bottom(Keywords.auto)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v0,
            bottom(zero())
        ),

        style(
            lg.auto,
            bottom(Keywords.auto)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v0,
            bottom(zero())
        ),

        style(
            xl.auto,
            bottom(Keywords.auto)
        )
    );
  }

  public interface sm {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

  }

  public interface md {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

  }

}