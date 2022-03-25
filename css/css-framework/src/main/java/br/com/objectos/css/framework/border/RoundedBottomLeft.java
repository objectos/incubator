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
public final class RoundedBottomLeft extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-bl-none");

  public static final ClassSelector small = Css.dot("rounded-bl-small");

  public static final ClassSelector standard = Css.dot("rounded-bl-standard");

  public static final ClassSelector medium = Css.dot("rounded-bl-medium");

  public static final ClassSelector large = Css.dot("rounded-bl-large");

  public static final ClassSelector full = Css.dot("rounded-bl-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderBottomLeftRadius(zero())
    );
    style(
        small,
        borderBottomLeftRadius(em(0.125))
    );
    style(
        standard,
        borderBottomLeftRadius(em(0.25))
    );
    style(
        medium,
        borderBottomLeftRadius(em(0.375))
    );
    style(
        large,
        borderBottomLeftRadius(em(0.5))
    );
    style(
        full,
        borderBottomLeftRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            sm.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            sm.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            sm.full,
            borderBottomLeftRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            md.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            md.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            md.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            md.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            md.full,
            borderBottomLeftRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            lg.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            lg.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            lg.full,
            borderBottomLeftRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderBottomLeftRadius(zero())
        ),

        style(
            xl.small,
            borderBottomLeftRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderBottomLeftRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderBottomLeftRadius(em(0.375))
        ),

        style(
            xl.large,
            borderBottomLeftRadius(em(0.5))
        ),

        style(
            xl.full,
            borderBottomLeftRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-bl-none");

    ClassSelector small = Css.dot("sm-rounded-bl-small");

    ClassSelector standard = Css.dot("sm-rounded-bl-standard");

    ClassSelector medium = Css.dot("sm-rounded-bl-medium");

    ClassSelector large = Css.dot("sm-rounded-bl-large");

    ClassSelector full = Css.dot("sm-rounded-bl-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-bl-none");

    ClassSelector small = Css.dot("md-rounded-bl-small");

    ClassSelector standard = Css.dot("md-rounded-bl-standard");

    ClassSelector medium = Css.dot("md-rounded-bl-medium");

    ClassSelector large = Css.dot("md-rounded-bl-large");

    ClassSelector full = Css.dot("md-rounded-bl-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-bl-none");

    ClassSelector small = Css.dot("lg-rounded-bl-small");

    ClassSelector standard = Css.dot("lg-rounded-bl-standard");

    ClassSelector medium = Css.dot("lg-rounded-bl-medium");

    ClassSelector large = Css.dot("lg-rounded-bl-large");

    ClassSelector full = Css.dot("lg-rounded-bl-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-bl-none");

    ClassSelector small = Css.dot("xl-rounded-bl-small");

    ClassSelector standard = Css.dot("xl-rounded-bl-standard");

    ClassSelector medium = Css.dot("xl-rounded-bl-medium");

    ClassSelector large = Css.dot("xl-rounded-bl-large");

    ClassSelector full = Css.dot("xl-rounded-bl-full");

  }

}