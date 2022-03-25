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
public final class RoundedTopRight extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-tr-none");

  public static final ClassSelector small = Css.dot("rounded-tr-small");

  public static final ClassSelector standard = Css.dot("rounded-tr-standard");

  public static final ClassSelector medium = Css.dot("rounded-tr-medium");

  public static final ClassSelector large = Css.dot("rounded-tr-large");

  public static final ClassSelector full = Css.dot("rounded-tr-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderTopRightRadius(zero())
    );
    style(
        small,
        borderTopRightRadius(em(0.125))
    );
    style(
        standard,
        borderTopRightRadius(em(0.25))
    );
    style(
        medium,
        borderTopRightRadius(em(0.375))
    );
    style(
        large,
        borderTopRightRadius(em(0.5))
    );
    style(
        full,
        borderTopRightRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderTopRightRadius(zero())
        ),

        style(
            sm.small,
            borderTopRightRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderTopRightRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderTopRightRadius(em(0.375))
        ),

        style(
            sm.large,
            borderTopRightRadius(em(0.5))
        ),

        style(
            sm.full,
            borderTopRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderTopRightRadius(zero())
        ),

        style(
            md.small,
            borderTopRightRadius(em(0.125))
        ),

        style(
            md.standard,
            borderTopRightRadius(em(0.25))
        ),

        style(
            md.medium,
            borderTopRightRadius(em(0.375))
        ),

        style(
            md.large,
            borderTopRightRadius(em(0.5))
        ),

        style(
            md.full,
            borderTopRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderTopRightRadius(zero())
        ),

        style(
            lg.small,
            borderTopRightRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderTopRightRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderTopRightRadius(em(0.375))
        ),

        style(
            lg.large,
            borderTopRightRadius(em(0.5))
        ),

        style(
            lg.full,
            borderTopRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderTopRightRadius(zero())
        ),

        style(
            xl.small,
            borderTopRightRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderTopRightRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderTopRightRadius(em(0.375))
        ),

        style(
            xl.large,
            borderTopRightRadius(em(0.5))
        ),

        style(
            xl.full,
            borderTopRightRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-tr-none");

    ClassSelector small = Css.dot("sm-rounded-tr-small");

    ClassSelector standard = Css.dot("sm-rounded-tr-standard");

    ClassSelector medium = Css.dot("sm-rounded-tr-medium");

    ClassSelector large = Css.dot("sm-rounded-tr-large");

    ClassSelector full = Css.dot("sm-rounded-tr-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-tr-none");

    ClassSelector small = Css.dot("md-rounded-tr-small");

    ClassSelector standard = Css.dot("md-rounded-tr-standard");

    ClassSelector medium = Css.dot("md-rounded-tr-medium");

    ClassSelector large = Css.dot("md-rounded-tr-large");

    ClassSelector full = Css.dot("md-rounded-tr-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-tr-none");

    ClassSelector small = Css.dot("lg-rounded-tr-small");

    ClassSelector standard = Css.dot("lg-rounded-tr-standard");

    ClassSelector medium = Css.dot("lg-rounded-tr-medium");

    ClassSelector large = Css.dot("lg-rounded-tr-large");

    ClassSelector full = Css.dot("lg-rounded-tr-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-tr-none");

    ClassSelector small = Css.dot("xl-rounded-tr-small");

    ClassSelector standard = Css.dot("xl-rounded-tr-standard");

    ClassSelector medium = Css.dot("xl-rounded-tr-medium");

    ClassSelector large = Css.dot("xl-rounded-tr-large");

    ClassSelector full = Css.dot("xl-rounded-tr-full");

  }

}