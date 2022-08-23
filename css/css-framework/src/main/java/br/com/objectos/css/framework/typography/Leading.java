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
public final class Leading extends AbstractStyleSheet {

  public static final ClassSelector none = Css.randomDot(5);

  public static final ClassSelector tight = Css.randomDot(5);

  public static final ClassSelector snug = Css.randomDot(5);

  public static final ClassSelector normal = Css.randomDot(5);

  public static final ClassSelector relaxed = Css.randomDot(5);

  public static final ClassSelector loose = Css.randomDot(5);

  public static final ClassSelector v3 = Css.randomDot(5);

  public static final ClassSelector v4 = Css.randomDot(5);

  public static final ClassSelector v5 = Css.randomDot(5);

  public static final ClassSelector v6 = Css.randomDot(5);

  public static final ClassSelector v7 = Css.randomDot(5);

  public static final ClassSelector v8 = Css.randomDot(5);

  public static final ClassSelector v9 = Css.randomDot(5);

  public static final ClassSelector v10 = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        none,
        lineHeight(1)
    );
    style(
        tight,
        lineHeight(1.25)
    );
    style(
        snug,
        lineHeight(1.375)
    );
    style(
        normal,
        lineHeight(1.5)
    );
    style(
        relaxed,
        lineHeight(1.625)
    );
    style(
        loose,
        lineHeight(2)
    );
    style(
        v3,
        lineHeight(rem(0.75))
    );
    style(
        v4,
        lineHeight(rem(1))
    );
    style(
        v5,
        lineHeight(rem(1.25))
    );
    style(
        v6,
        lineHeight(rem(1.5))
    );
    style(
        v7,
        lineHeight(rem(1.75))
    );
    style(
        v8,
        lineHeight(rem(2))
    );
    style(
        v9,
        lineHeight(rem(2.25))
    );
    style(
        v10,
        lineHeight(rem(2.5))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.none,
            lineHeight(1)
        ),

        style(
            sm.tight,
            lineHeight(1.25)
        ),

        style(
            sm.snug,
            lineHeight(1.375)
        ),

        style(
            sm.normal,
            lineHeight(1.5)
        ),

        style(
            sm.relaxed,
            lineHeight(1.625)
        ),

        style(
            sm.loose,
            lineHeight(2)
        ),

        style(
            sm.v3,
            lineHeight(rem(0.75))
        ),

        style(
            sm.v4,
            lineHeight(rem(1))
        ),

        style(
            sm.v5,
            lineHeight(rem(1.25))
        ),

        style(
            sm.v6,
            lineHeight(rem(1.5))
        ),

        style(
            sm.v7,
            lineHeight(rem(1.75))
        ),

        style(
            sm.v8,
            lineHeight(rem(2))
        ),

        style(
            sm.v9,
            lineHeight(rem(2.25))
        ),

        style(
            sm.v10,
            lineHeight(rem(2.5))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.none,
            lineHeight(1)
        ),

        style(
            md.tight,
            lineHeight(1.25)
        ),

        style(
            md.snug,
            lineHeight(1.375)
        ),

        style(
            md.normal,
            lineHeight(1.5)
        ),

        style(
            md.relaxed,
            lineHeight(1.625)
        ),

        style(
            md.loose,
            lineHeight(2)
        ),

        style(
            md.v3,
            lineHeight(rem(0.75))
        ),

        style(
            md.v4,
            lineHeight(rem(1))
        ),

        style(
            md.v5,
            lineHeight(rem(1.25))
        ),

        style(
            md.v6,
            lineHeight(rem(1.5))
        ),

        style(
            md.v7,
            lineHeight(rem(1.75))
        ),

        style(
            md.v8,
            lineHeight(rem(2))
        ),

        style(
            md.v9,
            lineHeight(rem(2.25))
        ),

        style(
            md.v10,
            lineHeight(rem(2.5))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.none,
            lineHeight(1)
        ),

        style(
            lg.tight,
            lineHeight(1.25)
        ),

        style(
            lg.snug,
            lineHeight(1.375)
        ),

        style(
            lg.normal,
            lineHeight(1.5)
        ),

        style(
            lg.relaxed,
            lineHeight(1.625)
        ),

        style(
            lg.loose,
            lineHeight(2)
        ),

        style(
            lg.v3,
            lineHeight(rem(0.75))
        ),

        style(
            lg.v4,
            lineHeight(rem(1))
        ),

        style(
            lg.v5,
            lineHeight(rem(1.25))
        ),

        style(
            lg.v6,
            lineHeight(rem(1.5))
        ),

        style(
            lg.v7,
            lineHeight(rem(1.75))
        ),

        style(
            lg.v8,
            lineHeight(rem(2))
        ),

        style(
            lg.v9,
            lineHeight(rem(2.25))
        ),

        style(
            lg.v10,
            lineHeight(rem(2.5))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.none,
            lineHeight(1)
        ),

        style(
            xl.tight,
            lineHeight(1.25)
        ),

        style(
            xl.snug,
            lineHeight(1.375)
        ),

        style(
            xl.normal,
            lineHeight(1.5)
        ),

        style(
            xl.relaxed,
            lineHeight(1.625)
        ),

        style(
            xl.loose,
            lineHeight(2)
        ),

        style(
            xl.v3,
            lineHeight(rem(0.75))
        ),

        style(
            xl.v4,
            lineHeight(rem(1))
        ),

        style(
            xl.v5,
            lineHeight(rem(1.25))
        ),

        style(
            xl.v6,
            lineHeight(rem(1.5))
        ),

        style(
            xl.v7,
            lineHeight(rem(1.75))
        ),

        style(
            xl.v8,
            lineHeight(rem(2))
        ),

        style(
            xl.v9,
            lineHeight(rem(2.25))
        ),

        style(
            xl.v10,
            lineHeight(rem(2.5))
        )
    );
  }

  public interface sm {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector none = Css.randomDot(5);

    ClassSelector tight = Css.randomDot(5);

    ClassSelector snug = Css.randomDot(5);

    ClassSelector normal = Css.randomDot(5);

    ClassSelector relaxed = Css.randomDot(5);

    ClassSelector loose = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v7 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v9 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

  }

}