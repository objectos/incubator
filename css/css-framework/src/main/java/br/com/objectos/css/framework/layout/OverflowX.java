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
public final class OverflowX extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("overflow-x-auto");

  public static final ClassSelector hidden = Css.dot("overflow-x-hidden");

  public static final ClassSelector visible = Css.dot("overflow-x-visible");

  public static final ClassSelector scroll = Css.dot("overflow-x-scroll");

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
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-overflow-x-auto");

    ClassSelector hidden = Css.dot("sm-overflow-x-hidden");

    ClassSelector visible = Css.dot("sm-overflow-x-visible");

    ClassSelector scroll = Css.dot("sm-overflow-x-scroll");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-overflow-x-auto");

    ClassSelector hidden = Css.dot("md-overflow-x-hidden");

    ClassSelector visible = Css.dot("md-overflow-x-visible");

    ClassSelector scroll = Css.dot("md-overflow-x-scroll");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-overflow-x-auto");

    ClassSelector hidden = Css.dot("lg-overflow-x-hidden");

    ClassSelector visible = Css.dot("lg-overflow-x-visible");

    ClassSelector scroll = Css.dot("lg-overflow-x-scroll");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-overflow-x-auto");

    ClassSelector hidden = Css.dot("xl-overflow-x-hidden");

    ClassSelector visible = Css.dot("xl-overflow-x-visible");

    ClassSelector scroll = Css.dot("xl-overflow-x-scroll");

  }

}