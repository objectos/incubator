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
public final class InsetY extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.dot("inset-y-0");

  public static final ClassSelector auto = Css.dot("inset-y-auto");

  @Override
  protected final void definition() {
    style(
        v0,
        top(zero()),
        bottom(zero())
    );
    style(
        auto,
        top(Keywords.auto),
        bottom(Keywords.auto)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.v0,
            top(zero()),
            bottom(zero())
        ),

        style(
            sm.auto,
            top(Keywords.auto),
            bottom(Keywords.auto)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            top(zero()),
            bottom(zero())
        ),

        style(
            md.auto,
            top(Keywords.auto),
            bottom(Keywords.auto)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.v0,
            top(zero()),
            bottom(zero())
        ),

        style(
            lg.auto,
            top(Keywords.auto),
            bottom(Keywords.auto)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.v0,
            top(zero()),
            bottom(zero())
        ),

        style(
            xl.auto,
            top(Keywords.auto),
            bottom(Keywords.auto)
        )
    );
  }

  public interface sm {

    ClassSelector v0 = Css.dot("sm-inset-y-0");

    ClassSelector auto = Css.dot("sm-inset-y-auto");

  }

  public interface md {

    ClassSelector v0 = Css.dot("md-inset-y-0");

    ClassSelector auto = Css.dot("md-inset-y-auto");

  }

  public interface lg {

    ClassSelector v0 = Css.dot("lg-inset-y-0");

    ClassSelector auto = Css.dot("lg-inset-y-auto");

  }

  public interface xl {

    ClassSelector v0 = Css.dot("xl-inset-y-0");

    ClassSelector auto = Css.dot("xl-inset-y-auto");

  }

}