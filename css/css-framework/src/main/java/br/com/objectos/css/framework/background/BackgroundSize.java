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
package br.com.objectos.css.framework.background;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BackgroundSize extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector cover = Css.randomDot(5);

  public static final ClassSelector contain = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        auto,
        backgroundSize(Keywords.auto)
    );
    style(
        cover,
        backgroundSize(Keywords.cover)
    );
    style(
        contain,
        backgroundSize(Keywords.contain)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            backgroundSize(Keywords.auto)
        ),

        style(
            sm.cover,
            backgroundSize(Keywords.cover)
        ),

        style(
            sm.contain,
            backgroundSize(Keywords.contain)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            backgroundSize(Keywords.auto)
        ),

        style(
            md.cover,
            backgroundSize(Keywords.cover)
        ),

        style(
            md.contain,
            backgroundSize(Keywords.contain)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            backgroundSize(Keywords.auto)
        ),

        style(
            lg.cover,
            backgroundSize(Keywords.cover)
        ),

        style(
            lg.contain,
            backgroundSize(Keywords.contain)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            backgroundSize(Keywords.auto)
        ),

        style(
            xl.cover,
            backgroundSize(Keywords.cover)
        ),

        style(
            xl.contain,
            backgroundSize(Keywords.contain)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector contain = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector contain = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector contain = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector cover = Css.randomDot(5);

    ClassSelector contain = Css.randomDot(5);

  }

}