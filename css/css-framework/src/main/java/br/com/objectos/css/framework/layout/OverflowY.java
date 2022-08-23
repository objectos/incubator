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
public final class OverflowY extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector hidden = Css.randomDot(5);

  public static final ClassSelector visible = Css.randomDot(5);

  public static final ClassSelector scroll = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        auto,
        overflowY(Keywords.auto)
    );
    style(
        hidden,
        overflowY(Keywords.hidden)
    );
    style(
        visible,
        overflowY(Keywords.visible)
    );
    style(
        scroll,
        overflowY(Keywords.scroll)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            overflowY(Keywords.auto)
        ),

        style(
            sm.hidden,
            overflowY(Keywords.hidden)
        ),

        style(
            sm.visible,
            overflowY(Keywords.visible)
        ),

        style(
            sm.scroll,
            overflowY(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            overflowY(Keywords.auto)
        ),

        style(
            md.hidden,
            overflowY(Keywords.hidden)
        ),

        style(
            md.visible,
            overflowY(Keywords.visible)
        ),

        style(
            md.scroll,
            overflowY(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            overflowY(Keywords.auto)
        ),

        style(
            lg.hidden,
            overflowY(Keywords.hidden)
        ),

        style(
            lg.visible,
            overflowY(Keywords.visible)
        ),

        style(
            lg.scroll,
            overflowY(Keywords.scroll)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            overflowY(Keywords.auto)
        ),

        style(
            xl.hidden,
            overflowY(Keywords.hidden)
        ),

        style(
            xl.visible,
            overflowY(Keywords.visible)
        ),

        style(
            xl.scroll,
            overflowY(Keywords.scroll)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector visible = Css.randomDot(5);

    ClassSelector scroll = Css.randomDot(5);

  }

}