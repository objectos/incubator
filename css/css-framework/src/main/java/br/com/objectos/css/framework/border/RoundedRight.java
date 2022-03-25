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
public final class RoundedRight extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-r-none");

  public static final ClassSelector small = Css.dot("rounded-r-small");

  public static final ClassSelector standard = Css.dot("rounded-r-standard");

  public static final ClassSelector medium = Css.dot("rounded-r-medium");

  public static final ClassSelector large = Css.dot("rounded-r-large");

  public static final ClassSelector full = Css.dot("rounded-r-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderTopRightRadius(zero()),
        borderBottomRightRadius(zero())
    );
    style(
        small,
        borderTopRightRadius(em(0.125)),
        borderBottomRightRadius(em(0.125))
    );
    style(
        standard,
        borderTopRightRadius(em(0.25)),
        borderBottomRightRadius(em(0.25))
    );
    style(
        medium,
        borderTopRightRadius(em(0.375)),
        borderBottomRightRadius(em(0.375))
    );
    style(
        large,
        borderTopRightRadius(em(0.5)),
        borderBottomRightRadius(em(0.5))
    );
    style(
        full,
        borderTopRightRadius(px(9999)),
        borderBottomRightRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderTopRightRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            sm.small,
            borderTopRightRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderTopRightRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderTopRightRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            sm.large,
            borderTopRightRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            sm.full,
            borderTopRightRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderTopRightRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            md.small,
            borderTopRightRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            md.standard,
            borderTopRightRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            md.medium,
            borderTopRightRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            md.large,
            borderTopRightRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            md.full,
            borderTopRightRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderTopRightRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            lg.small,
            borderTopRightRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderTopRightRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderTopRightRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            lg.large,
            borderTopRightRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            lg.full,
            borderTopRightRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderTopRightRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            xl.small,
            borderTopRightRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderTopRightRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderTopRightRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            xl.large,
            borderTopRightRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            xl.full,
            borderTopRightRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-r-none");

    ClassSelector small = Css.dot("sm-rounded-r-small");

    ClassSelector standard = Css.dot("sm-rounded-r-standard");

    ClassSelector medium = Css.dot("sm-rounded-r-medium");

    ClassSelector large = Css.dot("sm-rounded-r-large");

    ClassSelector full = Css.dot("sm-rounded-r-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-r-none");

    ClassSelector small = Css.dot("md-rounded-r-small");

    ClassSelector standard = Css.dot("md-rounded-r-standard");

    ClassSelector medium = Css.dot("md-rounded-r-medium");

    ClassSelector large = Css.dot("md-rounded-r-large");

    ClassSelector full = Css.dot("md-rounded-r-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-r-none");

    ClassSelector small = Css.dot("lg-rounded-r-small");

    ClassSelector standard = Css.dot("lg-rounded-r-standard");

    ClassSelector medium = Css.dot("lg-rounded-r-medium");

    ClassSelector large = Css.dot("lg-rounded-r-large");

    ClassSelector full = Css.dot("lg-rounded-r-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-r-none");

    ClassSelector small = Css.dot("xl-rounded-r-small");

    ClassSelector standard = Css.dot("xl-rounded-r-standard");

    ClassSelector medium = Css.dot("xl-rounded-r-medium");

    ClassSelector large = Css.dot("xl-rounded-r-large");

    ClassSelector full = Css.dot("xl-rounded-r-full");

  }

}