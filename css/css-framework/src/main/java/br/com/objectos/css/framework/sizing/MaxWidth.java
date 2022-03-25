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
package br.com.objectos.css.framework.sizing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class MaxWidth extends AbstractStyleSheet {

  public static final ClassSelector none = Css.dot("max-w-none");

  public static final ClassSelector xSmall = Css.dot("max-w-x-small");

  public static final ClassSelector small = Css.dot("max-w-small");

  public static final ClassSelector medium = Css.dot("max-w-medium");

  public static final ClassSelector large = Css.dot("max-w-large");

  public static final ClassSelector xLarge = Css.dot("max-w-x-large");

  public static final ClassSelector xLarge2 = Css.dot("max-w-x-large-2");

  public static final ClassSelector xLarge3 = Css.dot("max-w-x-large-3");

  public static final ClassSelector xLarge4 = Css.dot("max-w-x-large-4");

  public static final ClassSelector xLarge5 = Css.dot("max-w-x-large-5");

  public static final ClassSelector xLarge6 = Css.dot("max-w-x-large-6");

  public static final ClassSelector full = Css.dot("max-w-full");

  public static final ClassSelector screenSm = Css.dot("max-w-screen-sm");

  public static final ClassSelector screenMd = Css.dot("max-w-screen-md");

  public static final ClassSelector screenLg = Css.dot("max-w-screen-lg");

  public static final ClassSelector screenXl = Css.dot("max-w-screen-xl");

  @Override
  protected final void definition() {
    style(
        none,
        maxWidth(Keywords.none)
    );
    style(
        xSmall,
        maxWidth(rem(20))
    );
    style(
        small,
        maxWidth(rem(24))
    );
    style(
        medium,
        maxWidth(rem(28))
    );
    style(
        large,
        maxWidth(rem(32))
    );
    style(
        xLarge,
        maxWidth(rem(36))
    );
    style(
        xLarge2,
        maxWidth(rem(42))
    );
    style(
        xLarge3,
        maxWidth(rem(48))
    );
    style(
        xLarge4,
        maxWidth(rem(56))
    );
    style(
        xLarge5,
        maxWidth(rem(64))
    );
    style(
        xLarge6,
        maxWidth(rem(72))
    );
    style(
        full,
        maxWidth(pct(100))
    );
    style(
        screenSm,
        maxWidth(px(640))
    );
    style(
        screenMd,
        maxWidth(px(768))
    );
    style(
        screenLg,
        maxWidth(px(1024))
    );
    style(
        screenXl,
        maxWidth(px(1280))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            maxWidth(Keywords.none)
        ),

        style(
            sm.xSmall,
            maxWidth(rem(20))
        ),

        style(
            sm.small,
            maxWidth(rem(24))
        ),

        style(
            sm.medium,
            maxWidth(rem(28))
        ),

        style(
            sm.large,
            maxWidth(rem(32))
        ),

        style(
            sm.xLarge,
            maxWidth(rem(36))
        ),

        style(
            sm.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            sm.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            sm.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            sm.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            sm.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            sm.full,
            maxWidth(pct(100))
        ),

        style(
            sm.screenSm,
            maxWidth(px(640))
        ),

        style(
            sm.screenMd,
            maxWidth(px(768))
        ),

        style(
            sm.screenLg,
            maxWidth(px(1024))
        ),

        style(
            sm.screenXl,
            maxWidth(px(1280))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            maxWidth(Keywords.none)
        ),

        style(
            md.xSmall,
            maxWidth(rem(20))
        ),

        style(
            md.small,
            maxWidth(rem(24))
        ),

        style(
            md.medium,
            maxWidth(rem(28))
        ),

        style(
            md.large,
            maxWidth(rem(32))
        ),

        style(
            md.xLarge,
            maxWidth(rem(36))
        ),

        style(
            md.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            md.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            md.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            md.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            md.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            md.full,
            maxWidth(pct(100))
        ),

        style(
            md.screenSm,
            maxWidth(px(640))
        ),

        style(
            md.screenMd,
            maxWidth(px(768))
        ),

        style(
            md.screenLg,
            maxWidth(px(1024))
        ),

        style(
            md.screenXl,
            maxWidth(px(1280))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            maxWidth(Keywords.none)
        ),

        style(
            lg.xSmall,
            maxWidth(rem(20))
        ),

        style(
            lg.small,
            maxWidth(rem(24))
        ),

        style(
            lg.medium,
            maxWidth(rem(28))
        ),

        style(
            lg.large,
            maxWidth(rem(32))
        ),

        style(
            lg.xLarge,
            maxWidth(rem(36))
        ),

        style(
            lg.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            lg.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            lg.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            lg.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            lg.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            lg.full,
            maxWidth(pct(100))
        ),

        style(
            lg.screenSm,
            maxWidth(px(640))
        ),

        style(
            lg.screenMd,
            maxWidth(px(768))
        ),

        style(
            lg.screenLg,
            maxWidth(px(1024))
        ),

        style(
            lg.screenXl,
            maxWidth(px(1280))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            maxWidth(Keywords.none)
        ),

        style(
            xl.xSmall,
            maxWidth(rem(20))
        ),

        style(
            xl.small,
            maxWidth(rem(24))
        ),

        style(
            xl.medium,
            maxWidth(rem(28))
        ),

        style(
            xl.large,
            maxWidth(rem(32))
        ),

        style(
            xl.xLarge,
            maxWidth(rem(36))
        ),

        style(
            xl.xLarge2,
            maxWidth(rem(42))
        ),

        style(
            xl.xLarge3,
            maxWidth(rem(48))
        ),

        style(
            xl.xLarge4,
            maxWidth(rem(56))
        ),

        style(
            xl.xLarge5,
            maxWidth(rem(64))
        ),

        style(
            xl.xLarge6,
            maxWidth(rem(72))
        ),

        style(
            xl.full,
            maxWidth(pct(100))
        ),

        style(
            xl.screenSm,
            maxWidth(px(640))
        ),

        style(
            xl.screenMd,
            maxWidth(px(768))
        ),

        style(
            xl.screenLg,
            maxWidth(px(1024))
        ),

        style(
            xl.screenXl,
            maxWidth(px(1280))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.dot("sm-max-w-none");

    ClassSelector xSmall = Css.dot("sm-max-w-x-small");

    ClassSelector small = Css.dot("sm-max-w-small");

    ClassSelector medium = Css.dot("sm-max-w-medium");

    ClassSelector large = Css.dot("sm-max-w-large");

    ClassSelector xLarge = Css.dot("sm-max-w-x-large");

    ClassSelector xLarge2 = Css.dot("sm-max-w-x-large-2");

    ClassSelector xLarge3 = Css.dot("sm-max-w-x-large-3");

    ClassSelector xLarge4 = Css.dot("sm-max-w-x-large-4");

    ClassSelector xLarge5 = Css.dot("sm-max-w-x-large-5");

    ClassSelector xLarge6 = Css.dot("sm-max-w-x-large-6");

    ClassSelector full = Css.dot("sm-max-w-full");

    ClassSelector screenSm = Css.dot("sm-max-w-screen-sm");

    ClassSelector screenMd = Css.dot("sm-max-w-screen-md");

    ClassSelector screenLg = Css.dot("sm-max-w-screen-lg");

    ClassSelector screenXl = Css.dot("sm-max-w-screen-xl");

  }

  public interface md {

    ClassSelector none = Css.dot("md-max-w-none");

    ClassSelector xSmall = Css.dot("md-max-w-x-small");

    ClassSelector small = Css.dot("md-max-w-small");

    ClassSelector medium = Css.dot("md-max-w-medium");

    ClassSelector large = Css.dot("md-max-w-large");

    ClassSelector xLarge = Css.dot("md-max-w-x-large");

    ClassSelector xLarge2 = Css.dot("md-max-w-x-large-2");

    ClassSelector xLarge3 = Css.dot("md-max-w-x-large-3");

    ClassSelector xLarge4 = Css.dot("md-max-w-x-large-4");

    ClassSelector xLarge5 = Css.dot("md-max-w-x-large-5");

    ClassSelector xLarge6 = Css.dot("md-max-w-x-large-6");

    ClassSelector full = Css.dot("md-max-w-full");

    ClassSelector screenSm = Css.dot("md-max-w-screen-sm");

    ClassSelector screenMd = Css.dot("md-max-w-screen-md");

    ClassSelector screenLg = Css.dot("md-max-w-screen-lg");

    ClassSelector screenXl = Css.dot("md-max-w-screen-xl");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-max-w-none");

    ClassSelector xSmall = Css.dot("lg-max-w-x-small");

    ClassSelector small = Css.dot("lg-max-w-small");

    ClassSelector medium = Css.dot("lg-max-w-medium");

    ClassSelector large = Css.dot("lg-max-w-large");

    ClassSelector xLarge = Css.dot("lg-max-w-x-large");

    ClassSelector xLarge2 = Css.dot("lg-max-w-x-large-2");

    ClassSelector xLarge3 = Css.dot("lg-max-w-x-large-3");

    ClassSelector xLarge4 = Css.dot("lg-max-w-x-large-4");

    ClassSelector xLarge5 = Css.dot("lg-max-w-x-large-5");

    ClassSelector xLarge6 = Css.dot("lg-max-w-x-large-6");

    ClassSelector full = Css.dot("lg-max-w-full");

    ClassSelector screenSm = Css.dot("lg-max-w-screen-sm");

    ClassSelector screenMd = Css.dot("lg-max-w-screen-md");

    ClassSelector screenLg = Css.dot("lg-max-w-screen-lg");

    ClassSelector screenXl = Css.dot("lg-max-w-screen-xl");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-max-w-none");

    ClassSelector xSmall = Css.dot("xl-max-w-x-small");

    ClassSelector small = Css.dot("xl-max-w-small");

    ClassSelector medium = Css.dot("xl-max-w-medium");

    ClassSelector large = Css.dot("xl-max-w-large");

    ClassSelector xLarge = Css.dot("xl-max-w-x-large");

    ClassSelector xLarge2 = Css.dot("xl-max-w-x-large-2");

    ClassSelector xLarge3 = Css.dot("xl-max-w-x-large-3");

    ClassSelector xLarge4 = Css.dot("xl-max-w-x-large-4");

    ClassSelector xLarge5 = Css.dot("xl-max-w-x-large-5");

    ClassSelector xLarge6 = Css.dot("xl-max-w-x-large-6");

    ClassSelector full = Css.dot("xl-max-w-full");

    ClassSelector screenSm = Css.dot("xl-max-w-screen-sm");

    ClassSelector screenMd = Css.dot("xl-max-w-screen-md");

    ClassSelector screenLg = Css.dot("xl-max-w-screen-lg");

    ClassSelector screenXl = Css.dot("xl-max-w-screen-xl");

  }

}