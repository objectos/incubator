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
package br.com.objectos.css.framework.typography;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class FontSize extends AbstractStyleSheet {

  public static final ClassSelector xSmall = Css.dot("fs-x-small");

  public static final ClassSelector small = Css.dot("fs-small");

  public static final ClassSelector base = Css.dot("fs-base");

  public static final ClassSelector large = Css.dot("fs-large");

  public static final ClassSelector xLarge = Css.dot("fs-x-large");

  public static final ClassSelector xLarge2 = Css.dot("fs-x-large-2");

  public static final ClassSelector xLarge3 = Css.dot("fs-x-large-3");

  public static final ClassSelector xLarge4 = Css.dot("fs-x-large-4");

  public static final ClassSelector xLarge5 = Css.dot("fs-x-large-5");

  public static final ClassSelector xLarge6 = Css.dot("fs-x-large-6");

  @Override
  protected final void definition() {
    style(
        xSmall,
        fontSize(rem(0.75))
    );
    style(
        small,
        fontSize(rem(0.875))
    );
    style(
        base,
        fontSize(rem(1))
    );
    style(
        large,
        fontSize(rem(1.125))
    );
    style(
        xLarge,
        fontSize(rem(1.25))
    );
    style(
        xLarge2,
        fontSize(rem(1.5))
    );
    style(
        xLarge3,
        fontSize(rem(1.875))
    );
    style(
        xLarge4,
        fontSize(rem(2.25))
    );
    style(
        xLarge5,
        fontSize(rem(3))
    );
    style(
        xLarge6,
        fontSize(rem(4))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            sm.small,
            fontSize(rem(0.875))
        ),

        style(
            sm.base,
            fontSize(rem(1))
        ),

        style(
            sm.large,
            fontSize(rem(1.125))
        ),

        style(
            sm.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            sm.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            sm.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            sm.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            sm.xLarge5,
            fontSize(rem(3))
        ),

        style(
            sm.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            md.small,
            fontSize(rem(0.875))
        ),

        style(
            md.base,
            fontSize(rem(1))
        ),

        style(
            md.large,
            fontSize(rem(1.125))
        ),

        style(
            md.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            md.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            md.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            md.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            md.xLarge5,
            fontSize(rem(3))
        ),

        style(
            md.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            lg.small,
            fontSize(rem(0.875))
        ),

        style(
            lg.base,
            fontSize(rem(1))
        ),

        style(
            lg.large,
            fontSize(rem(1.125))
        ),

        style(
            lg.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            lg.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            lg.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            lg.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            lg.xLarge5,
            fontSize(rem(3))
        ),

        style(
            lg.xLarge6,
            fontSize(rem(4))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.xSmall,
            fontSize(rem(0.75))
        ),

        style(
            xl.small,
            fontSize(rem(0.875))
        ),

        style(
            xl.base,
            fontSize(rem(1))
        ),

        style(
            xl.large,
            fontSize(rem(1.125))
        ),

        style(
            xl.xLarge,
            fontSize(rem(1.25))
        ),

        style(
            xl.xLarge2,
            fontSize(rem(1.5))
        ),

        style(
            xl.xLarge3,
            fontSize(rem(1.875))
        ),

        style(
            xl.xLarge4,
            fontSize(rem(2.25))
        ),

        style(
            xl.xLarge5,
            fontSize(rem(3))
        ),

        style(
            xl.xLarge6,
            fontSize(rem(4))
        )
    );
  }

  public interface sm {

    ClassSelector xSmall = Css.dot("sm-fs-x-small");

    ClassSelector small = Css.dot("sm-fs-small");

    ClassSelector base = Css.dot("sm-fs-base");

    ClassSelector large = Css.dot("sm-fs-large");

    ClassSelector xLarge = Css.dot("sm-fs-x-large");

    ClassSelector xLarge2 = Css.dot("sm-fs-x-large-2");

    ClassSelector xLarge3 = Css.dot("sm-fs-x-large-3");

    ClassSelector xLarge4 = Css.dot("sm-fs-x-large-4");

    ClassSelector xLarge5 = Css.dot("sm-fs-x-large-5");

    ClassSelector xLarge6 = Css.dot("sm-fs-x-large-6");

  }

  public interface md {

    ClassSelector xSmall = Css.dot("md-fs-x-small");

    ClassSelector small = Css.dot("md-fs-small");

    ClassSelector base = Css.dot("md-fs-base");

    ClassSelector large = Css.dot("md-fs-large");

    ClassSelector xLarge = Css.dot("md-fs-x-large");

    ClassSelector xLarge2 = Css.dot("md-fs-x-large-2");

    ClassSelector xLarge3 = Css.dot("md-fs-x-large-3");

    ClassSelector xLarge4 = Css.dot("md-fs-x-large-4");

    ClassSelector xLarge5 = Css.dot("md-fs-x-large-5");

    ClassSelector xLarge6 = Css.dot("md-fs-x-large-6");

  }

  public interface lg {

    ClassSelector xSmall = Css.dot("lg-fs-x-small");

    ClassSelector small = Css.dot("lg-fs-small");

    ClassSelector base = Css.dot("lg-fs-base");

    ClassSelector large = Css.dot("lg-fs-large");

    ClassSelector xLarge = Css.dot("lg-fs-x-large");

    ClassSelector xLarge2 = Css.dot("lg-fs-x-large-2");

    ClassSelector xLarge3 = Css.dot("lg-fs-x-large-3");

    ClassSelector xLarge4 = Css.dot("lg-fs-x-large-4");

    ClassSelector xLarge5 = Css.dot("lg-fs-x-large-5");

    ClassSelector xLarge6 = Css.dot("lg-fs-x-large-6");

  }

  public interface xl {

    ClassSelector xSmall = Css.dot("xl-fs-x-small");

    ClassSelector small = Css.dot("xl-fs-small");

    ClassSelector base = Css.dot("xl-fs-base");

    ClassSelector large = Css.dot("xl-fs-large");

    ClassSelector xLarge = Css.dot("xl-fs-x-large");

    ClassSelector xLarge2 = Css.dot("xl-fs-x-large-2");

    ClassSelector xLarge3 = Css.dot("xl-fs-x-large-3");

    ClassSelector xLarge4 = Css.dot("xl-fs-x-large-4");

    ClassSelector xLarge5 = Css.dot("xl-fs-x-large-5");

    ClassSelector xLarge6 = Css.dot("xl-fs-x-large-6");

  }

}