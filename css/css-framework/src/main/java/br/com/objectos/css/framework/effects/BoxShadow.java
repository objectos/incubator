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

  public static final ClassSelector xSmall = Css.randomDot(5);

  public static final ClassSelector small = Css.randomDot(5);

  public static final ClassSelector standard = Css.randomDot(5);

  public static final ClassSelector medium = Css.randomDot(5);

  public static final ClassSelector large = Css.randomDot(5);

  public static final ClassSelector xLarge = Css.randomDot(5);

  public static final ClassSelector xLarge2 = Css.randomDot(5);

  public static final ClassSelector inner = Css.randomDot(5);

  public static final ClassSelector outline = Css.randomDot(5);

  public static final ClassSelector none = Css.randomDot(5);

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

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector inner = Css.randomDot(5);

    ClassSelector outline = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface md {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector inner = Css.randomDot(5);

    ClassSelector outline = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector inner = Css.randomDot(5);

    ClassSelector outline = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector xSmall = Css.randomDot(5);

    ClassSelector small = Css.randomDot(5);

    ClassSelector standard = Css.randomDot(5);

    ClassSelector medium = Css.randomDot(5);

    ClassSelector large = Css.randomDot(5);

    ClassSelector xLarge = Css.randomDot(5);

    ClassSelector xLarge2 = Css.randomDot(5);

    ClassSelector inner = Css.randomDot(5);

    ClassSelector outline = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

  }

}