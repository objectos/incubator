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
public final class RoundedBottom extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("rounded-b-none");

  public static final ClassSelector small = Css.dot("rounded-b-small");

  public static final ClassSelector standard = Css.dot("rounded-b-standard");

  public static final ClassSelector medium = Css.dot("rounded-b-medium");

  public static final ClassSelector large = Css.dot("rounded-b-large");

  public static final ClassSelector full = Css.dot("rounded-b-full");

  @Override
  protected final void definition() {
    style(
        none,
        borderBottomLeftRadius(zero()),
        borderBottomRightRadius(zero())
    );
    style(
        small,
        borderBottomLeftRadius(em(0.125)),
        borderBottomRightRadius(em(0.125))
    );
    style(
        standard,
        borderBottomLeftRadius(em(0.25)),
        borderBottomRightRadius(em(0.25))
    );
    style(
        medium,
        borderBottomLeftRadius(em(0.375)),
        borderBottomRightRadius(em(0.375))
    );
    style(
        large,
        borderBottomLeftRadius(em(0.5)),
        borderBottomRightRadius(em(0.5))
    );
    style(
        full,
        borderBottomLeftRadius(px(9999)),
        borderBottomRightRadius(px(9999))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            borderBottomLeftRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            sm.small,
            borderBottomLeftRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            sm.standard,
            borderBottomLeftRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            sm.medium,
            borderBottomLeftRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            sm.large,
            borderBottomLeftRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            sm.full,
            borderBottomLeftRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            borderBottomLeftRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            md.small,
            borderBottomLeftRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            md.standard,
            borderBottomLeftRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            md.medium,
            borderBottomLeftRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            md.large,
            borderBottomLeftRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            md.full,
            borderBottomLeftRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            borderBottomLeftRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            lg.small,
            borderBottomLeftRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            lg.standard,
            borderBottomLeftRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            lg.medium,
            borderBottomLeftRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            lg.large,
            borderBottomLeftRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            lg.full,
            borderBottomLeftRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            borderBottomLeftRadius(zero()),
            borderBottomRightRadius(zero())
        ),

        style(
            xl.small,
            borderBottomLeftRadius(em(0.125)),
            borderBottomRightRadius(em(0.125))
        ),

        style(
            xl.standard,
            borderBottomLeftRadius(em(0.25)),
            borderBottomRightRadius(em(0.25))
        ),

        style(
            xl.medium,
            borderBottomLeftRadius(em(0.375)),
            borderBottomRightRadius(em(0.375))
        ),

        style(
            xl.large,
            borderBottomLeftRadius(em(0.5)),
            borderBottomRightRadius(em(0.5))
        ),

        style(
            xl.full,
            borderBottomLeftRadius(px(9999)),
            borderBottomRightRadius(px(9999))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-rounded-b-none");

    ClassSelector small = Css.dot("sm-rounded-b-small");

    ClassSelector standard = Css.dot("sm-rounded-b-standard");

    ClassSelector medium = Css.dot("sm-rounded-b-medium");

    ClassSelector large = Css.dot("sm-rounded-b-large");

    ClassSelector full = Css.dot("sm-rounded-b-full");

  }

  public interface md {

    ClassSelector none = Css.dot("md-rounded-b-none");

    ClassSelector small = Css.dot("md-rounded-b-small");

    ClassSelector standard = Css.dot("md-rounded-b-standard");

    ClassSelector medium = Css.dot("md-rounded-b-medium");

    ClassSelector large = Css.dot("md-rounded-b-large");

    ClassSelector full = Css.dot("md-rounded-b-full");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-rounded-b-none");

    ClassSelector small = Css.dot("lg-rounded-b-small");

    ClassSelector standard = Css.dot("lg-rounded-b-standard");

    ClassSelector medium = Css.dot("lg-rounded-b-medium");

    ClassSelector large = Css.dot("lg-rounded-b-large");

    ClassSelector full = Css.dot("lg-rounded-b-full");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-rounded-b-none");

    ClassSelector small = Css.dot("xl-rounded-b-small");

    ClassSelector standard = Css.dot("xl-rounded-b-standard");

    ClassSelector medium = Css.dot("xl-rounded-b-medium");

    ClassSelector large = Css.dot("xl-rounded-b-large");

    ClassSelector full = Css.dot("xl-rounded-b-full");

  }

}