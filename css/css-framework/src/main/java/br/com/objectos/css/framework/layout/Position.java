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
public final class Position extends AbstractStyleSheet {

  public static final ClassSelector staticPosition = Css.dot("static-position");

  public static final ClassSelector fixed = Css.dot("fixed");

  public static final ClassSelector absolute = Css.dot("absolute");

  public static final ClassSelector relative = Css.dot("relative");

  public static final ClassSelector sticky = Css.dot("sticky");

  @Override
  protected final void definition() {
    style(
        staticPosition,
        position(Keywords.staticKw)
    );
    style(
        fixed,
        position(Keywords.fixed)
    );
    style(
        absolute,
        position(Keywords.absolute)
    );
    style(
        relative,
        position(Keywords.relative)
    );
    style(
        sticky,
        position(Keywords.sticky)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.staticPosition,
            position(Keywords.staticKw)
        ),

        style(
            sm.fixed,
            position(Keywords.fixed)
        ),

        style(
            sm.absolute,
            position(Keywords.absolute)
        ),

        style(
            sm.relative,
            position(Keywords.relative)
        ),

        style(
            sm.sticky,
            position(Keywords.sticky)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.staticPosition,
            position(Keywords.staticKw)
        ),

        style(
            md.fixed,
            position(Keywords.fixed)
        ),

        style(
            md.absolute,
            position(Keywords.absolute)
        ),

        style(
            md.relative,
            position(Keywords.relative)
        ),

        style(
            md.sticky,
            position(Keywords.sticky)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.staticPosition,
            position(Keywords.staticKw)
        ),

        style(
            lg.fixed,
            position(Keywords.fixed)
        ),

        style(
            lg.absolute,
            position(Keywords.absolute)
        ),

        style(
            lg.relative,
            position(Keywords.relative)
        ),

        style(
            lg.sticky,
            position(Keywords.sticky)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.staticPosition,
            position(Keywords.staticKw)
        ),

        style(
            xl.fixed,
            position(Keywords.fixed)
        ),

        style(
            xl.absolute,
            position(Keywords.absolute)
        ),

        style(
            xl.relative,
            position(Keywords.relative)
        ),

        style(
            xl.sticky,
            position(Keywords.sticky)
        )
    );
  }

  public interface sm {

    ClassSelector staticPosition = Css.dot("sm-static-position");

    ClassSelector fixed = Css.dot("sm-fixed");

    ClassSelector absolute = Css.dot("sm-absolute");

    ClassSelector relative = Css.dot("sm-relative");

    ClassSelector sticky = Css.dot("sm-sticky");

  }

  public interface md {

    ClassSelector staticPosition = Css.dot("md-static-position");

    ClassSelector fixed = Css.dot("md-fixed");

    ClassSelector absolute = Css.dot("md-absolute");

    ClassSelector relative = Css.dot("md-relative");

    ClassSelector sticky = Css.dot("md-sticky");

  }

  public interface lg {

    ClassSelector staticPosition = Css.dot("lg-static-position");

    ClassSelector fixed = Css.dot("lg-fixed");

    ClassSelector absolute = Css.dot("lg-absolute");

    ClassSelector relative = Css.dot("lg-relative");

    ClassSelector sticky = Css.dot("lg-sticky");

  }

  public interface xl {

    ClassSelector staticPosition = Css.dot("xl-static-position");

    ClassSelector fixed = Css.dot("xl-fixed");

    ClassSelector absolute = Css.dot("xl-absolute");

    ClassSelector relative = Css.dot("xl-relative");

    ClassSelector sticky = Css.dot("xl-sticky");

  }

}