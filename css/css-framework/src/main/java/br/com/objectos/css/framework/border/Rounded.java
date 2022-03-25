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
public final class Rounded extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-none");

  public static final ClassSelector small = Css.dot("rounded-small");

  public static final ClassSelector standard = Css.dot("rounded-standard");

  public static final ClassSelector medium = Css.dot("rounded-medium");

  public static final ClassSelector large = Css.dot("rounded-large");

  public static final ClassSelector full = Css.dot("rounded-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderRadius(zero())
    );
    style(
        small,
        borderRadius(em(0.125))
    );
    style(
        standard,
        borderRadius(em(0.25))
    );
    style(
        medium,
        borderRadius(em(0.375))
    );
    style(
        large,
        borderRadius(em(0.5))
    );
    style(
        full,
        borderRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderRadius(zero())
        ),

        style(
            sm.small,
            borderRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderRadius(em(0.375))
        ),

        style(
            sm.large,
            borderRadius(em(0.5))
        ),

        style(
            sm.full,
            borderRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderRadius(zero())
        ),

        style(
            md.small,
            borderRadius(em(0.125))
        ),

        style(
            md.standard,
            borderRadius(em(0.25))
        ),

        style(
            md.medium,
            borderRadius(em(0.375))
        ),

        style(
            md.large,
            borderRadius(em(0.5))
        ),

        style(
            md.full,
            borderRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderRadius(zero())
        ),

        style(
            lg.small,
            borderRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderRadius(em(0.375))
        ),

        style(
            lg.large,
            borderRadius(em(0.5))
        ),

        style(
            lg.full,
            borderRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderRadius(zero())
        ),

        style(
            xl.small,
            borderRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderRadius(em(0.375))
        ),

        style(
            xl.large,
            borderRadius(em(0.5))
        ),

        style(
            xl.full,
            borderRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-none");

    ClassSelector small = Css.dot("sm-rounded-small");

    ClassSelector standard = Css.dot("sm-rounded-standard");

    ClassSelector medium = Css.dot("sm-rounded-medium");

    ClassSelector large = Css.dot("sm-rounded-large");

    ClassSelector full = Css.dot("sm-rounded-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-none");

    ClassSelector small = Css.dot("md-rounded-small");

    ClassSelector standard = Css.dot("md-rounded-standard");

    ClassSelector medium = Css.dot("md-rounded-medium");

    ClassSelector large = Css.dot("md-rounded-large");

    ClassSelector full = Css.dot("md-rounded-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-none");

    ClassSelector small = Css.dot("lg-rounded-small");

    ClassSelector standard = Css.dot("lg-rounded-standard");

    ClassSelector medium = Css.dot("lg-rounded-medium");

    ClassSelector large = Css.dot("lg-rounded-large");

    ClassSelector full = Css.dot("lg-rounded-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-none");

    ClassSelector small = Css.dot("xl-rounded-small");

    ClassSelector standard = Css.dot("xl-rounded-standard");

    ClassSelector medium = Css.dot("xl-rounded-medium");

    ClassSelector large = Css.dot("xl-rounded-large");

    ClassSelector full = Css.dot("xl-rounded-full");

  }

}