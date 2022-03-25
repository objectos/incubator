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

  public static final ClassSelector auto = Css.dot("bg-auto");

  public static final ClassSelector cover = Css.dot("bg-cover");

  public static final ClassSelector contain = Css.dot("bg-contain");

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

    ClassSelector auto = Css.dot("sm-bg-auto");

    ClassSelector cover = Css.dot("sm-bg-cover");

    ClassSelector contain = Css.dot("sm-bg-contain");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-bg-auto");

    ClassSelector cover = Css.dot("md-bg-cover");

    ClassSelector contain = Css.dot("md-bg-contain");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-bg-auto");

    ClassSelector cover = Css.dot("lg-bg-cover");

    ClassSelector contain = Css.dot("lg-bg-contain");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-bg-auto");

    ClassSelector cover = Css.dot("xl-bg-cover");

    ClassSelector contain = Css.dot("xl-bg-contain");

  }

}