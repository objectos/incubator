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
package br.com.objectos.css.framework.border;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class RoundedBottomRight extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-br-none");

  public static final ClassSelector small = Css.dot("rounded-br-small");

  public static final ClassSelector standard = Css.dot("rounded-br-standard");

  public static final ClassSelector medium = Css.dot("rounded-br-medium");

  public static final ClassSelector large = Css.dot("rounded-br-large");

  public static final ClassSelector full = Css.dot("rounded-br-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderBottomRightRadius(zero())
    );
    style(
        small,
        borderBottomRightRadius(em(0.125))
    );
    style(
        standard,
        borderBottomRightRadius(em(0.25))
    );
    style(
        medium,
        borderBottomRightRadius(em(0.375))
    );
    style(
        large,
        borderBottomRightRadius(em(0.5))
    );
    style(
        full,
        borderBottomRightRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderBottomRightRadius(zero())
        ),

        style(
            sm.small,
            borderBottomRightRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderBottomRightRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderBottomRightRadius(em(0.375))
        ),

        style(
            sm.large,
            borderBottomRightRadius(em(0.5))
        ),

        style(
            sm.full,
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderBottomRightRadius(zero())
        ),

        style(
            md.small,
            borderBottomRightRadius(em(0.125))
        ),

        style(
            md.standard,
            borderBottomRightRadius(em(0.25))
        ),

        style(
            md.medium,
            borderBottomRightRadius(em(0.375))
        ),

        style(
            md.large,
            borderBottomRightRadius(em(0.5))
        ),

        style(
            md.full,
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderBottomRightRadius(zero())
        ),

        style(
            lg.small,
            borderBottomRightRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderBottomRightRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderBottomRightRadius(em(0.375))
        ),

        style(
            lg.large,
            borderBottomRightRadius(em(0.5))
        ),

        style(
            lg.full,
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderBottomRightRadius(zero())
        ),

        style(
            xl.small,
            borderBottomRightRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderBottomRightRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderBottomRightRadius(em(0.375))
        ),

        style(
            xl.large,
            borderBottomRightRadius(em(0.5))
        ),

        style(
            xl.full,
            borderBottomRightRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-br-none");

    ClassSelector small = Css.dot("sm-rounded-br-small");

    ClassSelector standard = Css.dot("sm-rounded-br-standard");

    ClassSelector medium = Css.dot("sm-rounded-br-medium");

    ClassSelector large = Css.dot("sm-rounded-br-large");

    ClassSelector full = Css.dot("sm-rounded-br-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-br-none");

    ClassSelector small = Css.dot("md-rounded-br-small");

    ClassSelector standard = Css.dot("md-rounded-br-standard");

    ClassSelector medium = Css.dot("md-rounded-br-medium");

    ClassSelector large = Css.dot("md-rounded-br-large");

    ClassSelector full = Css.dot("md-rounded-br-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-br-none");

    ClassSelector small = Css.dot("lg-rounded-br-small");

    ClassSelector standard = Css.dot("lg-rounded-br-standard");

    ClassSelector medium = Css.dot("lg-rounded-br-medium");

    ClassSelector large = Css.dot("lg-rounded-br-large");

    ClassSelector full = Css.dot("lg-rounded-br-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-br-none");

    ClassSelector small = Css.dot("xl-rounded-br-small");

    ClassSelector standard = Css.dot("xl-rounded-br-standard");

    ClassSelector medium = Css.dot("xl-rounded-br-medium");

    ClassSelector large = Css.dot("xl-rounded-br-large");

    ClassSelector full = Css.dot("xl-rounded-br-full");

  }

}