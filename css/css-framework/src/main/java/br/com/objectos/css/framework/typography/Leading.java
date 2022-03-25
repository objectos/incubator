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

  public static final ClassSelector none = Css.dot("leading-none");

  public static final ClassSelector tight = Css.dot("leading-tight");

  public static final ClassSelector snug = Css.dot("leading-snug");

  public static final ClassSelector normal = Css.dot("leading-normal");

  public static final ClassSelector relaxed = Css.dot("leading-relaxed");

  public static final ClassSelector loose = Css.dot("leading-loose");

  public static final ClassSelector v3 = Css.dot("leading-3");

  public static final ClassSelector v4 = Css.dot("leading-4");

  public static final ClassSelector v5 = Css.dot("leading-5");

  public static final ClassSelector v6 = Css.dot("leading-6");

  public static final ClassSelector v7 = Css.dot("leading-7");

  public static final ClassSelector v8 = Css.dot("leading-8");

  public static final ClassSelector v9 = Css.dot("leading-9");

  public static final ClassSelector v10 = Css.dot("leading-10");

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

    ClassSelector none = Css.dot("sm-leading-none");

    ClassSelector tight = Css.dot("sm-leading-tight");

    ClassSelector snug = Css.dot("sm-leading-snug");

    ClassSelector normal = Css.dot("sm-leading-normal");

    ClassSelector relaxed = Css.dot("sm-leading-relaxed");

    ClassSelector loose = Css.dot("sm-leading-loose");

    ClassSelector v3 = Css.dot("sm-leading-3");

    ClassSelector v4 = Css.dot("sm-leading-4");

    ClassSelector v5 = Css.dot("sm-leading-5");

    ClassSelector v6 = Css.dot("sm-leading-6");

    ClassSelector v7 = Css.dot("sm-leading-7");

    ClassSelector v8 = Css.dot("sm-leading-8");

    ClassSelector v9 = Css.dot("sm-leading-9");

    ClassSelector v10 = Css.dot("sm-leading-10");

  }

  public interface md {

    ClassSelector none = Css.dot("md-leading-none");

    ClassSelector tight = Css.dot("md-leading-tight");

    ClassSelector snug = Css.dot("md-leading-snug");

    ClassSelector normal = Css.dot("md-leading-normal");

    ClassSelector relaxed = Css.dot("md-leading-relaxed");

    ClassSelector loose = Css.dot("md-leading-loose");

    ClassSelector v3 = Css.dot("md-leading-3");

    ClassSelector v4 = Css.dot("md-leading-4");

    ClassSelector v5 = Css.dot("md-leading-5");

    ClassSelector v6 = Css.dot("md-leading-6");

    ClassSelector v7 = Css.dot("md-leading-7");

    ClassSelector v8 = Css.dot("md-leading-8");

    ClassSelector v9 = Css.dot("md-leading-9");

    ClassSelector v10 = Css.dot("md-leading-10");

  }

  public interface lg {

    ClassSelector none = Css.dot("lg-leading-none");

    ClassSelector tight = Css.dot("lg-leading-tight");

    ClassSelector snug = Css.dot("lg-leading-snug");

    ClassSelector normal = Css.dot("lg-leading-normal");

    ClassSelector relaxed = Css.dot("lg-leading-relaxed");

    ClassSelector loose = Css.dot("lg-leading-loose");

    ClassSelector v3 = Css.dot("lg-leading-3");

    ClassSelector v4 = Css.dot("lg-leading-4");

    ClassSelector v5 = Css.dot("lg-leading-5");

    ClassSelector v6 = Css.dot("lg-leading-6");

    ClassSelector v7 = Css.dot("lg-leading-7");

    ClassSelector v8 = Css.dot("lg-leading-8");

    ClassSelector v9 = Css.dot("lg-leading-9");

    ClassSelector v10 = Css.dot("lg-leading-10");

  }

  public interface xl {

    ClassSelector none = Css.dot("xl-leading-none");

    ClassSelector tight = Css.dot("xl-leading-tight");

    ClassSelector snug = Css.dot("xl-leading-snug");

    ClassSelector normal = Css.dot("xl-leading-normal");

    ClassSelector relaxed = Css.dot("xl-leading-relaxed");

    ClassSelector loose = Css.dot("xl-leading-loose");

    ClassSelector v3 = Css.dot("xl-leading-3");

    ClassSelector v4 = Css.dot("xl-leading-4");

    ClassSelector v5 = Css.dot("xl-leading-5");

    ClassSelector v6 = Css.dot("xl-leading-6");

    ClassSelector v7 = Css.dot("xl-leading-7");

    ClassSelector v8 = Css.dot("xl-leading-8");

    ClassSelector v9 = Css.dot("xl-leading-9");

    ClassSelector v10 = Css.dot("xl-leading-10");

  }

}