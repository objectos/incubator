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
public final class RoundedTop extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-t-none");

  public static final ClassSelector small = Css.dot("rounded-t-small");

  public static final ClassSelector standard = Css.dot("rounded-t-standard");

  public static final ClassSelector medium = Css.dot("rounded-t-medium");

  public static final ClassSelector large = Css.dot("rounded-t-large");

  public static final ClassSelector full = Css.dot("rounded-t-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderTopLeftRadius(zero()),
        borderTopRightRadius(zero())
    );
    style(
        small,
        borderTopLeftRadius(em(0.125)),
        borderTopRightRadius(em(0.125))
    );
    style(
        standard,
        borderTopLeftRadius(em(0.25)),
        borderTopRightRadius(em(0.25))
    );
    style(
        medium,
        borderTopLeftRadius(em(0.375)),
        borderTopRightRadius(em(0.375))
    );
    style(
        large,
        borderTopLeftRadius(em(0.5)),
        borderTopRightRadius(em(0.5))
    );
    style(
        full,
        borderTopLeftRadius(px(9999)),
        borderTopRightRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderTopLeftRadius(zero()),
            borderTopRightRadius(zero())
        ),

        style(
            sm.small,
            borderTopLeftRadius(em(0.125)),
            borderTopRightRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderTopLeftRadius(em(0.25)),
            borderTopRightRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderTopLeftRadius(em(0.375)),
            borderTopRightRadius(em(0.375))
        ),

        style(
            sm.large,
            borderTopLeftRadius(em(0.5)),
            borderTopRightRadius(em(0.5))
        ),

        style(
            sm.full,
            borderTopLeftRadius(px(9999)),
            borderTopRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderTopLeftRadius(zero()),
            borderTopRightRadius(zero())
        ),

        style(
            md.small,
            borderTopLeftRadius(em(0.125)),
            borderTopRightRadius(em(0.125))
        ),

        style(
            md.standard,
            borderTopLeftRadius(em(0.25)),
            borderTopRightRadius(em(0.25))
        ),

        style(
            md.medium,
            borderTopLeftRadius(em(0.375)),
            borderTopRightRadius(em(0.375))
        ),

        style(
            md.large,
            borderTopLeftRadius(em(0.5)),
            borderTopRightRadius(em(0.5))
        ),

        style(
            md.full,
            borderTopLeftRadius(px(9999)),
            borderTopRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderTopLeftRadius(zero()),
            borderTopRightRadius(zero())
        ),

        style(
            lg.small,
            borderTopLeftRadius(em(0.125)),
            borderTopRightRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderTopLeftRadius(em(0.25)),
            borderTopRightRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderTopLeftRadius(em(0.375)),
            borderTopRightRadius(em(0.375))
        ),

        style(
            lg.large,
            borderTopLeftRadius(em(0.5)),
            borderTopRightRadius(em(0.5))
        ),

        style(
            lg.full,
            borderTopLeftRadius(px(9999)),
            borderTopRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderTopLeftRadius(zero()),
            borderTopRightRadius(zero())
        ),

        style(
            xl.small,
            borderTopLeftRadius(em(0.125)),
            borderTopRightRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderTopLeftRadius(em(0.25)),
            borderTopRightRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderTopLeftRadius(em(0.375)),
            borderTopRightRadius(em(0.375))
        ),

        style(
            xl.large,
            borderTopLeftRadius(em(0.5)),
            borderTopRightRadius(em(0.5))
        ),

        style(
            xl.full,
            borderTopLeftRadius(px(9999)),
            borderTopRightRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-t-none");

    ClassSelector small = Css.dot("sm-rounded-t-small");

    ClassSelector standard = Css.dot("sm-rounded-t-standard");

    ClassSelector medium = Css.dot("sm-rounded-t-medium");

    ClassSelector large = Css.dot("sm-rounded-t-large");

    ClassSelector full = Css.dot("sm-rounded-t-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-t-none");

    ClassSelector small = Css.dot("md-rounded-t-small");

    ClassSelector standard = Css.dot("md-rounded-t-standard");

    ClassSelector medium = Css.dot("md-rounded-t-medium");

    ClassSelector large = Css.dot("md-rounded-t-large");

    ClassSelector full = Css.dot("md-rounded-t-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-t-none");

    ClassSelector small = Css.dot("lg-rounded-t-small");

    ClassSelector standard = Css.dot("lg-rounded-t-standard");

    ClassSelector medium = Css.dot("lg-rounded-t-medium");

    ClassSelector large = Css.dot("lg-rounded-t-large");

    ClassSelector full = Css.dot("lg-rounded-t-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-t-none");

    ClassSelector small = Css.dot("xl-rounded-t-small");

    ClassSelector standard = Css.dot("xl-rounded-t-standard");

    ClassSelector medium = Css.dot("xl-rounded-t-medium");

    ClassSelector large = Css.dot("xl-rounded-t-large");

    ClassSelector full = Css.dot("xl-rounded-t-full");

  }

}