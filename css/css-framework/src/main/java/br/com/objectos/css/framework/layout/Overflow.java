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
public final class Overflow extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("overflow-auto");

  public static final ClassSelector hidden = Css.dot("overflow-hidden");

  public static final ClassSelector visible = Css.dot("overflow-visible");

  public static final ClassSelector scroll = Css.dot("overflow-scroll");

  @Override
  protected final void definition() {
    style(
        auto,
        overflow(Keywords.auto)
    );
    style(
        hidden,
        overflow(Keywords.hidden)
    );
    style(
        visible,
        overflow(Keywords.visible)
    );
    style(
        scroll,
        overflow(Keywords.scroll)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            overflow(Keywords.auto)
        ),

        style(
            sm.hidden,
            overflow(Keywords.hidden)
        ),

        style(
            sm.visible,
            overflow(Keywords.visible)
        ),

        style(
            sm.scroll,
            overflow(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            overflow(Keywords.auto)
        ),

        style(
            md.hidden,
            overflow(Keywords.hidden)
        ),

        style(
            md.visible,
            overflow(Keywords.visible)
        ),

        style(
            md.scroll,
            overflow(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            overflow(Keywords.auto)
        ),

        style(
            lg.hidden,
            overflow(Keywords.hidden)
        ),

        style(
            lg.visible,
            overflow(Keywords.visible)
        ),

        style(
            lg.scroll,
            overflow(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            overflow(Keywords.auto)
        ),

        style(
            xl.hidden,
            overflow(Keywords.hidden)
        ),

        style(
            xl.visible,
            overflow(Keywords.visible)
        ),

        style(
            xl.scroll,
            overflow(Keywords.scroll)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-overflow-auto");

    ClassSelector hidden = Css.dot("sm-overflow-hidden");

    ClassSelector visible = Css.dot("sm-overflow-visible");

    ClassSelector scroll = Css.dot("sm-overflow-scroll");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-overflow-auto");

    ClassSelector hidden = Css.dot("md-overflow-hidden");

    ClassSelector visible = Css.dot("md-overflow-visible");

    ClassSelector scroll = Css.dot("md-overflow-scroll");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-overflow-auto");

    ClassSelector hidden = Css.dot("lg-overflow-hidden");

    ClassSelector visible = Css.dot("lg-overflow-visible");

    ClassSelector scroll = Css.dot("lg-overflow-scroll");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-overflow-auto");

    ClassSelector hidden = Css.dot("xl-overflow-hidden");

    ClassSelector visible = Css.dot("xl-overflow-visible");

    ClassSelector scroll = Css.dot("xl-overflow-scroll");

  }

}