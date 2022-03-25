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
public final class Height extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("h-auto");

  public static final ClassSelector px = Css.dot("h-px");

  public static final ClassSelector v0 = Css.dot("h-0");

  public static final ClassSelector v1 = Css.dot("h-1");

  public static final ClassSelector v2 = Css.dot("h-2");

  public static final ClassSelector v3 = Css.dot("h-3");

  public static final ClassSelector v4 = Css.dot("h-4");

  public static final ClassSelector v5 = Css.dot("h-5");

  public static final ClassSelector v6 = Css.dot("h-6");

  public static final ClassSelector v8 = Css.dot("h-8");

  public static final ClassSelector v10 = Css.dot("h-10");

  public static final ClassSelector v12 = Css.dot("h-12");

  public static final ClassSelector v16 = Css.dot("h-16");

  public static final ClassSelector v20 = Css.dot("h-20");

  public static final ClassSelector v24 = Css.dot("h-24");

  public static final ClassSelector v32 = Css.dot("h-32");

  public static final ClassSelector v40 = Css.dot("h-40");

  public static final ClassSelector v48 = Css.dot("h-48");

  public static final ClassSelector v56 = Css.dot("h-56");

  public static final ClassSelector v64 = Css.dot("h-64");

  public static final ClassSelector full = Css.dot("h-full");

  public static final ClassSelector screen = Css.dot("h-screen");

  @Override
  protected final void definition() {
    style(
        auto,
        height(Keywords.auto)
    );
    style(
        px,
        height(px(1))
    );
    style(
        v0,
        height(zero())
    );
    style(
        v1,
        height(rem(0.25))
    );
    style(
        v2,
        height(rem(0.5))
    );
    style(
        v3,
        height(rem(0.75))
    );
    style(
        v4,
        height(rem(1))
    );
    style(
        v5,
        height(rem(1.25))
    );
    style(
        v6,
        height(rem(1.5))
    );
    style(
        v8,
        height(rem(2))
    );
    style(
        v10,
        height(rem(2.5))
    );
    style(
        v12,
        height(rem(3))
    );
    style(
        v16,
        height(rem(4))
    );
    style(
        v20,
        height(rem(5))
    );
    style(
        v24,
        height(rem(6))
    );
    style(
        v32,
        height(rem(8))
    );
    style(
        v40,
        height(rem(10))
    );
    style(
        v48,
        height(rem(12))
    );
    style(
        v56,
        height(rem(14))
    );
    style(
        v64,
        height(rem(16))
    );
    style(
        full,
        height(pct(100))
    );
    style(
        screen,
        height(vh(100))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            height(Keywords.auto)
        ),

        style(
            sm.px,
            height(px(1))
        ),

        style(
            sm.v0,
            height(zero())
        ),

        style(
            sm.v1,
            height(rem(0.25))
        ),

        style(
            sm.v2,
            height(rem(0.5))
        ),

        style(
            sm.v3,
            height(rem(0.75))
        ),

        style(
            sm.v4,
            height(rem(1))
        ),

        style(
            sm.v5,
            height(rem(1.25))
        ),

        style(
            sm.v6,
            height(rem(1.5))
        ),

        style(
            sm.v8,
            height(rem(2))
        ),

        style(
            sm.v10,
            height(rem(2.5))
        ),

        style(
            sm.v12,
            height(rem(3))
        ),

        style(
            sm.v16,
            height(rem(4))
        ),

        style(
            sm.v20,
            height(rem(5))
        ),

        style(
            sm.v24,
            height(rem(6))
        ),

        style(
            sm.v32,
            height(rem(8))
        ),

        style(
            sm.v40,
            height(rem(10))
        ),

        style(
            sm.v48,
            height(rem(12))
        ),

        style(
            sm.v56,
            height(rem(14))
        ),

        style(
            sm.v64,
            height(rem(16))
        ),

        style(
            sm.full,
            height(pct(100))
        ),

        style(
            sm.screen,
            height(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            height(Keywords.auto)
        ),

        style(
            md.px,
            height(px(1))
        ),

        style(
            md.v0,
            height(zero())
        ),

        style(
            md.v1,
            height(rem(0.25))
        ),

        style(
            md.v2,
            height(rem(0.5))
        ),

        style(
            md.v3,
            height(rem(0.75))
        ),

        style(
            md.v4,
            height(rem(1))
        ),

        style(
            md.v5,
            height(rem(1.25))
        ),

        style(
            md.v6,
            height(rem(1.5))
        ),

        style(
            md.v8,
            height(rem(2))
        ),

        style(
            md.v10,
            height(rem(2.5))
        ),

        style(
            md.v12,
            height(rem(3))
        ),

        style(
            md.v16,
            height(rem(4))
        ),

        style(
            md.v20,
            height(rem(5))
        ),

        style(
            md.v24,
            height(rem(6))
        ),

        style(
            md.v32,
            height(rem(8))
        ),

        style(
            md.v40,
            height(rem(10))
        ),

        style(
            md.v48,
            height(rem(12))
        ),

        style(
            md.v56,
            height(rem(14))
        ),

        style(
            md.v64,
            height(rem(16))
        ),

        style(
            md.full,
            height(pct(100))
        ),

        style(
            md.screen,
            height(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            height(Keywords.auto)
        ),

        style(
            lg.px,
            height(px(1))
        ),

        style(
            lg.v0,
            height(zero())
        ),

        style(
            lg.v1,
            height(rem(0.25))
        ),

        style(
            lg.v2,
            height(rem(0.5))
        ),

        style(
            lg.v3,
            height(rem(0.75))
        ),

        style(
            lg.v4,
            height(rem(1))
        ),

        style(
            lg.v5,
            height(rem(1.25))
        ),

        style(
            lg.v6,
            height(rem(1.5))
        ),

        style(
            lg.v8,
            height(rem(2))
        ),

        style(
            lg.v10,
            height(rem(2.5))
        ),

        style(
            lg.v12,
            height(rem(3))
        ),

        style(
            lg.v16,
            height(rem(4))
        ),

        style(
            lg.v20,
            height(rem(5))
        ),

        style(
            lg.v24,
            height(rem(6))
        ),

        style(
            lg.v32,
            height(rem(8))
        ),

        style(
            lg.v40,
            height(rem(10))
        ),

        style(
            lg.v48,
            height(rem(12))
        ),

        style(
            lg.v56,
            height(rem(14))
        ),

        style(
            lg.v64,
            height(rem(16))
        ),

        style(
            lg.full,
            height(pct(100))
        ),

        style(
            lg.screen,
            height(vh(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            height(Keywords.auto)
        ),

        style(
            xl.px,
            height(px(1))
        ),

        style(
            xl.v0,
            height(zero())
        ),

        style(
            xl.v1,
            height(rem(0.25))
        ),

        style(
            xl.v2,
            height(rem(0.5))
        ),

        style(
            xl.v3,
            height(rem(0.75))
        ),

        style(
            xl.v4,
            height(rem(1))
        ),

        style(
            xl.v5,
            height(rem(1.25))
        ),

        style(
            xl.v6,
            height(rem(1.5))
        ),

        style(
            xl.v8,
            height(rem(2))
        ),

        style(
            xl.v10,
            height(rem(2.5))
        ),

        style(
            xl.v12,
            height(rem(3))
        ),

        style(
            xl.v16,
            height(rem(4))
        ),

        style(
            xl.v20,
            height(rem(5))
        ),

        style(
            xl.v24,
            height(rem(6))
        ),

        style(
            xl.v32,
            height(rem(8))
        ),

        style(
            xl.v40,
            height(rem(10))
        ),

        style(
            xl.v48,
            height(rem(12))
        ),

        style(
            xl.v56,
            height(rem(14))
        ),

        style(
            xl.v64,
            height(rem(16))
        ),

        style(
            xl.full,
            height(pct(100))
        ),

        style(
            xl.screen,
            height(vh(100))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-h-auto");

    ClassSelector px = Css.dot("sm-h-px");

    ClassSelector v0 = Css.dot("sm-h-0");

    ClassSelector v1 = Css.dot("sm-h-1");

    ClassSelector v2 = Css.dot("sm-h-2");

    ClassSelector v3 = Css.dot("sm-h-3");

    ClassSelector v4 = Css.dot("sm-h-4");

    ClassSelector v5 = Css.dot("sm-h-5");

    ClassSelector v6 = Css.dot("sm-h-6");

    ClassSelector v8 = Css.dot("sm-h-8");

    ClassSelector v10 = Css.dot("sm-h-10");

    ClassSelector v12 = Css.dot("sm-h-12");

    ClassSelector v16 = Css.dot("sm-h-16");

    ClassSelector v20 = Css.dot("sm-h-20");

    ClassSelector v24 = Css.dot("sm-h-24");

    ClassSelector v32 = Css.dot("sm-h-32");

    ClassSelector v40 = Css.dot("sm-h-40");

    ClassSelector v48 = Css.dot("sm-h-48");

    ClassSelector v56 = Css.dot("sm-h-56");

    ClassSelector v64 = Css.dot("sm-h-64");

    ClassSelector full = Css.dot("sm-h-full");

    ClassSelector screen = Css.dot("sm-h-screen");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-h-auto");

    ClassSelector px = Css.dot("md-h-px");

    ClassSelector v0 = Css.dot("md-h-0");

    ClassSelector v1 = Css.dot("md-h-1");

    ClassSelector v2 = Css.dot("md-h-2");

    ClassSelector v3 = Css.dot("md-h-3");

    ClassSelector v4 = Css.dot("md-h-4");

    ClassSelector v5 = Css.dot("md-h-5");

    ClassSelector v6 = Css.dot("md-h-6");

    ClassSelector v8 = Css.dot("md-h-8");

    ClassSelector v10 = Css.dot("md-h-10");

    ClassSelector v12 = Css.dot("md-h-12");

    ClassSelector v16 = Css.dot("md-h-16");

    ClassSelector v20 = Css.dot("md-h-20");

    ClassSelector v24 = Css.dot("md-h-24");

    ClassSelector v32 = Css.dot("md-h-32");

    ClassSelector v40 = Css.dot("md-h-40");

    ClassSelector v48 = Css.dot("md-h-48");

    ClassSelector v56 = Css.dot("md-h-56");

    ClassSelector v64 = Css.dot("md-h-64");

    ClassSelector full = Css.dot("md-h-full");

    ClassSelector screen = Css.dot("md-h-screen");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-h-auto");

    ClassSelector px = Css.dot("lg-h-px");

    ClassSelector v0 = Css.dot("lg-h-0");

    ClassSelector v1 = Css.dot("lg-h-1");

    ClassSelector v2 = Css.dot("lg-h-2");

    ClassSelector v3 = Css.dot("lg-h-3");

    ClassSelector v4 = Css.dot("lg-h-4");

    ClassSelector v5 = Css.dot("lg-h-5");

    ClassSelector v6 = Css.dot("lg-h-6");

    ClassSelector v8 = Css.dot("lg-h-8");

    ClassSelector v10 = Css.dot("lg-h-10");

    ClassSelector v12 = Css.dot("lg-h-12");

    ClassSelector v16 = Css.dot("lg-h-16");

    ClassSelector v20 = Css.dot("lg-h-20");

    ClassSelector v24 = Css.dot("lg-h-24");

    ClassSelector v32 = Css.dot("lg-h-32");

    ClassSelector v40 = Css.dot("lg-h-40");

    ClassSelector v48 = Css.dot("lg-h-48");

    ClassSelector v56 = Css.dot("lg-h-56");

    ClassSelector v64 = Css.dot("lg-h-64");

    ClassSelector full = Css.dot("lg-h-full");

    ClassSelector screen = Css.dot("lg-h-screen");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-h-auto");

    ClassSelector px = Css.dot("xl-h-px");

    ClassSelector v0 = Css.dot("xl-h-0");

    ClassSelector v1 = Css.dot("xl-h-1");

    ClassSelector v2 = Css.dot("xl-h-2");

    ClassSelector v3 = Css.dot("xl-h-3");

    ClassSelector v4 = Css.dot("xl-h-4");

    ClassSelector v5 = Css.dot("xl-h-5");

    ClassSelector v6 = Css.dot("xl-h-6");

    ClassSelector v8 = Css.dot("xl-h-8");

    ClassSelector v10 = Css.dot("xl-h-10");

    ClassSelector v12 = Css.dot("xl-h-12");

    ClassSelector v16 = Css.dot("xl-h-16");

    ClassSelector v20 = Css.dot("xl-h-20");

    ClassSelector v24 = Css.dot("xl-h-24");

    ClassSelector v32 = Css.dot("xl-h-32");

    ClassSelector v40 = Css.dot("xl-h-40");

    ClassSelector v48 = Css.dot("xl-h-48");

    ClassSelector v56 = Css.dot("xl-h-56");

    ClassSelector v64 = Css.dot("xl-h-64");

    ClassSelector full = Css.dot("xl-h-full");

    ClassSelector screen = Css.dot("xl-h-screen");

  }

}