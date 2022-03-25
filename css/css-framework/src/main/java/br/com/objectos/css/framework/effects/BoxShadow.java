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
package br.com.objectos.css.framework.effects;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BoxShadow extends AbstractStyleSheet {

  public static final ClassSelector xSmall = Css.dot("shadow-xSmall");

  public static final ClassSelector small = Css.dot("shadow-small");

  public static final ClassSelector standard = Css.dot("shadow-standard");

  public static final ClassSelector medium = Css.dot("shadow-medium");

  public static final ClassSelector large = Css.dot("shadow-large");

  public static final ClassSelector xLarge = Css.dot("shadow-xLarge");

  public static final ClassSelector xLarge2 = Css.dot("shadow-xLarge2");

  public static final ClassSelector inner = Css.dot("shadow-inner");

  public static final ClassSelector outline = Css.dot("shadow-outline");

  public static final ClassSelector none = Css.dot("shadow-none");

  @Override
  protected final void definition() {
    style(
        xSmall,
        boxShadow(zero(), zero(), zero(), px(1), rgba(0, 0, 0, 0.05))
    );
    style(
        small,
        boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.05))
    );
    style(
        standard,
        boxShadow(
            boxShadow(zero(), px(1), px(3), zero(), rgba(0, 0, 0, 0.1)),
            boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.06))
        )
    );
    style(
        medium,
        boxShadow(
            boxShadow(zero(), px(4), px(6), px(-1), rgba(0, 0, 0, 0.1)),
            boxShadow(zero(), px(2), px(4), px(-1), rgba(0, 0, 0, 0.06))
        )
    );
    style(
        large,
        boxShadow(
            boxShadow(zero(), px(10), px(15), px(-3), rgba(0, 0, 0, 0.1)),
            boxShadow(zero(), px(4), px(6), px(-2), rgba(0, 0, 0, 0.05))
        )
    );
    style(
        xLarge,
        boxShadow(
            boxShadow(zero(), px(20), px(25), px(-5), rgba(0, 0, 0, 0.1)),
            boxShadow(zero(), px(10), px(10), px(-5), rgba(0, 0, 0, 0.04))
        )
    );
    style(
        xLarge2,
        boxShadow(zero(), px(25), px(50), px(-12), rgba(0, 0, 0, 0.25))
    );
    style(
        inner,
        boxShadow(Keywords.inset, zero(), px(2), px(4), zero(), rgba(0, 0, 0, 0.06))
    );
    style(
        outline,
        boxShadow(zero(), zero(), zero(), px(3), rgba(66, 153, 225, 0.5))
    );
    style(
        none,
        boxShadow(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.xSmall,
            boxShadow(zero(), zero(), zero(), px(1), rgba(0, 0, 0, 0.05))
        ),

        style(
            sm.small,
            boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.05))
        ),

        style(
            sm.standard,
            boxShadow(
                boxShadow(zero(), px(1), px(3), zero(), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            sm.medium,
            boxShadow(
                boxShadow(zero(), px(4), px(6), px(-1), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(2), px(4), px(-1), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            sm.large,
            boxShadow(
                boxShadow(zero(), px(10), px(15), px(-3), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(4), px(6), px(-2), rgba(0, 0, 0, 0.05))
            )
        ),

        style(
            sm.xLarge,
            boxShadow(
                boxShadow(zero(), px(20), px(25), px(-5), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(10), px(10), px(-5), rgba(0, 0, 0, 0.04))
            )
        ),

        style(
            sm.xLarge2,
            boxShadow(zero(), px(25), px(50), px(-12), rgba(0, 0, 0, 0.25))
        ),

        style(
            sm.inner,
            boxShadow(Keywords.inset, zero(), px(2), px(4), zero(), rgba(0, 0, 0, 0.06))
        ),

        style(
            sm.outline,
            boxShadow(zero(), zero(), zero(), px(3), rgba(66, 153, 225, 0.5))
        ),

        style(
            sm.none,
            boxShadow(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.xSmall,
            boxShadow(zero(), zero(), zero(), px(1), rgba(0, 0, 0, 0.05))
        ),

        style(
            md.small,
            boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.05))
        ),

        style(
            md.standard,
            boxShadow(
                boxShadow(zero(), px(1), px(3), zero(), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            md.medium,
            boxShadow(
                boxShadow(zero(), px(4), px(6), px(-1), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(2), px(4), px(-1), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            md.large,
            boxShadow(
                boxShadow(zero(), px(10), px(15), px(-3), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(4), px(6), px(-2), rgba(0, 0, 0, 0.05))
            )
        ),

        style(
            md.xLarge,
            boxShadow(
                boxShadow(zero(), px(20), px(25), px(-5), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(10), px(10), px(-5), rgba(0, 0, 0, 0.04))
            )
        ),

        style(
            md.xLarge2,
            boxShadow(zero(), px(25), px(50), px(-12), rgba(0, 0, 0, 0.25))
        ),

        style(
            md.inner,
            boxShadow(Keywords.inset, zero(), px(2), px(4), zero(), rgba(0, 0, 0, 0.06))
        ),

        style(
            md.outline,
            boxShadow(zero(), zero(), zero(), px(3), rgba(66, 153, 225, 0.5))
        ),

        style(
            md.none,
            boxShadow(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.xSmall,
            boxShadow(zero(), zero(), zero(), px(1), rgba(0, 0, 0, 0.05))
        ),

        style(
            lg.small,
            boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.05))
        ),

        style(
            lg.standard,
            boxShadow(
                boxShadow(zero(), px(1), px(3), zero(), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            lg.medium,
            boxShadow(
                boxShadow(zero(), px(4), px(6), px(-1), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(2), px(4), px(-1), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            lg.large,
            boxShadow(
                boxShadow(zero(), px(10), px(15), px(-3), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(4), px(6), px(-2), rgba(0, 0, 0, 0.05))
            )
        ),

        style(
            lg.xLarge,
            boxShadow(
                boxShadow(zero(), px(20), px(25), px(-5), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(10), px(10), px(-5), rgba(0, 0, 0, 0.04))
            )
        ),

        style(
            lg.xLarge2,
            boxShadow(zero(), px(25), px(50), px(-12), rgba(0, 0, 0, 0.25))
        ),

        style(
            lg.inner,
            boxShadow(Keywords.inset, zero(), px(2), px(4), zero(), rgba(0, 0, 0, 0.06))
        ),

        style(
            lg.outline,
            boxShadow(zero(), zero(), zero(), px(3), rgba(66, 153, 225, 0.5))
        ),

        style(
            lg.none,
            boxShadow(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.xSmall,
            boxShadow(zero(), zero(), zero(), px(1), rgba(0, 0, 0, 0.05))
        ),

        style(
            xl.small,
            boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.05))
        ),

        style(
            xl.standard,
            boxShadow(
                boxShadow(zero(), px(1), px(3), zero(), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            xl.medium,
            boxShadow(
                boxShadow(zero(), px(4), px(6), px(-1), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(2), px(4), px(-1), rgba(0, 0, 0, 0.06))
            )
        ),

        style(
            xl.large,
            boxShadow(
                boxShadow(zero(), px(10), px(15), px(-3), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(4), px(6), px(-2), rgba(0, 0, 0, 0.05))
            )
        ),

        style(
            xl.xLarge,
            boxShadow(
                boxShadow(zero(), px(20), px(25), px(-5), rgba(0, 0, 0, 0.1)),
                boxShadow(zero(), px(10), px(10), px(-5), rgba(0, 0, 0, 0.04))
            )
        ),

        style(
            xl.xLarge2,
            boxShadow(zero(), px(25), px(50), px(-12), rgba(0, 0, 0, 0.25))
        ),

        style(
            xl.inner,
            boxShadow(Keywords.inset, zero(), px(2), px(4), zero(), rgba(0, 0, 0, 0.06))
        ),

        style(
            xl.outline,
            boxShadow(zero(), zero(), zero(), px(3), rgba(66, 153, 225, 0.5))
        ),

        style(
            xl.none,
            boxShadow(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector xSmall = Css.dot("sm-shadow-xSmall");

    ClassSelector small = Css.dot("sm-shadow-small");

    ClassSelector standard = Css.dot("sm-shadow-standard");

    ClassSelector medium = Css.dot("sm-shadow-medium");

    ClassSelector large = Css.dot("sm-shadow-large");

    ClassSelector xLarge = Css.dot("sm-shadow-xLarge");

    ClassSelector xLarge2 = Css.dot("sm-shadow-xLarge2");

    ClassSelector inner = Css.dot("sm-shadow-inner");

    ClassSelector outline = Css.dot("sm-shadow-outline");

    ClassSelector none = Css.dot("sm-shadow-none");

  }

  public interface md {

    ClassSelector xSmall = Css.dot("md-shadow-xSmall");

    ClassSelector small = Css.dot("md-shadow-small");

    ClassSelector standard = Css.dot("md-shadow-standard");

    ClassSelector medium = Css.dot("md-shadow-medium");

    ClassSelector large = Css.dot("md-shadow-large");

    ClassSelector xLarge = Css.dot("md-shadow-xLarge");

    ClassSelector xLarge2 = Css.dot("md-shadow-xLarge2");

    ClassSelector inner = Css.dot("md-shadow-inner");

    ClassSelector outline = Css.dot("md-shadow-outline");

    ClassSelector none = Css.dot("md-shadow-none");

  }

  public interface lg {

    ClassSelector xSmall = Css.dot("lg-shadow-xSmall");

    ClassSelector small = Css.dot("lg-shadow-small");

    ClassSelector standard = Css.dot("lg-shadow-standard");

    ClassSelector medium = Css.dot("lg-shadow-medium");

    ClassSelector large = Css.dot("lg-shadow-large");

    ClassSelector xLarge = Css.dot("lg-shadow-xLarge");

    ClassSelector xLarge2 = Css.dot("lg-shadow-xLarge2");

    ClassSelector inner = Css.dot("lg-shadow-inner");

    ClassSelector outline = Css.dot("lg-shadow-outline");

    ClassSelector none = Css.dot("lg-shadow-none");

  }

  public interface xl {

    ClassSelector xSmall = Css.dot("xl-shadow-xSmall");

    ClassSelector small = Css.dot("xl-shadow-small");

    ClassSelector standard = Css.dot("xl-shadow-standard");

    ClassSelector medium = Css.dot("xl-shadow-medium");

    ClassSelector large = Css.dot("xl-shadow-large");

    ClassSelector xLarge = Css.dot("xl-shadow-xLarge");

    ClassSelector xLarge2 = Css.dot("xl-shadow-xLarge2");

    ClassSelector inner = Css.dot("xl-shadow-inner");

    ClassSelector outline = Css.dot("xl-shadow-outline");

    ClassSelector none = Css.dot("xl-shadow-none");

  }

}