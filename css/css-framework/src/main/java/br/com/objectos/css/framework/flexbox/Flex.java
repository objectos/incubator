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
package br.com.objectos.css.framework.flexbox;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Flex extends AbstractStyleSheet {

  public static final ClassSelector initial = Css.randomDot(5);

  public static final ClassSelector one = Css.randomDot(5);

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector none = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        initial,
        flex(l(0), l(1), Keywords.auto)
    );
    style(
        one,
        flex(l(1), l(1), pct(0))
    );
    style(
        auto,
        flex(l(1), l(1), Keywords.auto)
    );
    style(
        none,
        flex(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.initial,
            flex(l(0), l(1), Keywords.auto)
        ),

        style(
            sm.one,
            flex(l(1), l(1), pct(0))
        ),

        style(
            sm.auto,
            flex(l(1), l(1), Keywords.auto)
        ),

        style(
            sm.none,
            flex(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.initial,
            flex(l(0), l(1), Keywords.auto)
        ),

        style(
            md.one,
            flex(l(1), l(1), pct(0))
        ),

        style(
            md.auto,
            flex(l(1), l(1), Keywords.auto)
        ),

        style(
            md.none,
            flex(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.initial,
            flex(l(0), l(1), Keywords.auto)
        ),

        style(
            lg.one,
            flex(l(1), l(1), pct(0))
        ),

        style(
            lg.auto,
            flex(l(1), l(1), Keywords.auto)
        ),

        style(
            lg.none,
            flex(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.initial,
            flex(l(0), l(1), Keywords.auto)
        ),

        style(
            xl.one,
            flex(l(1), l(1), pct(0))
        ),

        style(
            xl.auto,
            flex(l(1), l(1), Keywords.auto)
        ),

        style(
            xl.none,
            flex(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector initial = Css.randomDot(5);

    ClassSelector one = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface md {

    ClassSelector initial = Css.randomDot(5);

    ClassSelector one = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector initial = Css.randomDot(5);

    ClassSelector one = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector initial = Css.randomDot(5);

    ClassSelector one = Css.randomDot(5);

    ClassSelector auto = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

}