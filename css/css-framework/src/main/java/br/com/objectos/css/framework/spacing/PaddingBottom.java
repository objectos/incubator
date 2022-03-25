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
package br.com.objectos.css.framework.spacing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class PaddingBottom extends AbstractStyleSheet {

  public static final ClassSelector px = Css.dot("pb-px");

  public static final ClassSelector v0 = Css.dot("pb-0");

  public static final ClassSelector v1 = Css.dot("pb-1");

  public static final ClassSelector v2 = Css.dot("pb-2");

  public static final ClassSelector v3 = Css.dot("pb-3");

  public static final ClassSelector v4 = Css.dot("pb-4");

  public static final ClassSelector v5 = Css.dot("pb-5");

  public static final ClassSelector v6 = Css.dot("pb-6");

  public static final ClassSelector v8 = Css.dot("pb-8");

  public static final ClassSelector v10 = Css.dot("pb-10");

  public static final ClassSelector v12 = Css.dot("pb-12");

  public static final ClassSelector v16 = Css.dot("pb-16");

  public static final ClassSelector v20 = Css.dot("pb-20");

  public static final ClassSelector v24 = Css.dot("pb-24");

  public static final ClassSelector v32 = Css.dot("pb-32");

  public static final ClassSelector v40 = Css.dot("pb-40");

  public static final ClassSelector v48 = Css.dot("pb-48");

  public static final ClassSelector v56 = Css.dot("pb-56");

  public static final ClassSelector v64 = Css.dot("pb-64");

  @Override
  protected final void definition() {
    style(
        px,
        paddingBottom(px(1))
    );
    style(
        v0,
        paddingBottom(zero())
    );
    style(
        v1,
        paddingBottom(rem(0.25))
    );
    style(
        v2,
        paddingBottom(rem(0.5))
    );
    style(
        v3,
        paddingBottom(rem(0.75))
    );
    style(
        v4,
        paddingBottom(rem(1))
    );
    style(
        v5,
        paddingBottom(rem(1.25))
    );
    style(
        v6,
        paddingBottom(rem(1.5))
    );
    style(
        v8,
        paddingBottom(rem(2))
    );
    style(
        v10,
        paddingBottom(rem(2.5))
    );
    style(
        v12,
        paddingBottom(rem(3))
    );
    style(
        v16,
        paddingBottom(rem(4))
    );
    style(
        v20,
        paddingBottom(rem(5))
    );
    style(
        v24,
        paddingBottom(rem(6))
    );
    style(
        v32,
        paddingBottom(rem(8))
    );
    style(
        v40,
        paddingBottom(rem(10))
    );
    style(
        v48,
        paddingBottom(rem(12))
    );
    style(
        v56,
        paddingBottom(rem(14))
    );
    style(
        v64,
        paddingBottom(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.px,
            paddingBottom(px(1))
        ),

        style(
            sm.v0,
            paddingBottom(zero())
        ),

        style(
            sm.v1,
            paddingBottom(rem(0.25))
        ),

        style(
            sm.v2,
            paddingBottom(rem(0.5))
        ),

        style(
            sm.v3,
            paddingBottom(rem(0.75))
        ),

        style(
            sm.v4,
            paddingBottom(rem(1))
        ),

        style(
            sm.v5,
            paddingBottom(rem(1.25))
        ),

        style(
            sm.v6,
            paddingBottom(rem(1.5))
        ),

        style(
            sm.v8,
            paddingBottom(rem(2))
        ),

        style(
            sm.v10,
            paddingBottom(rem(2.5))
        ),

        style(
            sm.v12,
            paddingBottom(rem(3))
        ),

        style(
            sm.v16,
            paddingBottom(rem(4))
        ),

        style(
            sm.v20,
            paddingBottom(rem(5))
        ),

        style(
            sm.v24,
            paddingBottom(rem(6))
        ),

        style(
            sm.v32,
            paddingBottom(rem(8))
        ),

        style(
            sm.v40,
            paddingBottom(rem(10))
        ),

        style(
            sm.v48,
            paddingBottom(rem(12))
        ),

        style(
            sm.v56,
            paddingBottom(rem(14))
        ),

        style(
            sm.v64,
            paddingBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.px,
            paddingBottom(px(1))
        ),

        style(
            md.v0,
            paddingBottom(zero())
        ),

        style(
            md.v1,
            paddingBottom(rem(0.25))
        ),

        style(
            md.v2,
            paddingBottom(rem(0.5))
        ),

        style(
            md.v3,
            paddingBottom(rem(0.75))
        ),

        style(
            md.v4,
            paddingBottom(rem(1))
        ),

        style(
            md.v5,
            paddingBottom(rem(1.25))
        ),

        style(
            md.v6,
            paddingBottom(rem(1.5))
        ),

        style(
            md.v8,
            paddingBottom(rem(2))
        ),

        style(
            md.v10,
            paddingBottom(rem(2.5))
        ),

        style(
            md.v12,
            paddingBottom(rem(3))
        ),

        style(
            md.v16,
            paddingBottom(rem(4))
        ),

        style(
            md.v20,
            paddingBottom(rem(5))
        ),

        style(
            md.v24,
            paddingBottom(rem(6))
        ),

        style(
            md.v32,
            paddingBottom(rem(8))
        ),

        style(
            md.v40,
            paddingBottom(rem(10))
        ),

        style(
            md.v48,
            paddingBottom(rem(12))
        ),

        style(
            md.v56,
            paddingBottom(rem(14))
        ),

        style(
            md.v64,
            paddingBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.px,
            paddingBottom(px(1))
        ),

        style(
            lg.v0,
            paddingBottom(zero())
        ),

        style(
            lg.v1,
            paddingBottom(rem(0.25))
        ),

        style(
            lg.v2,
            paddingBottom(rem(0.5))
        ),

        style(
            lg.v3,
            paddingBottom(rem(0.75))
        ),

        style(
            lg.v4,
            paddingBottom(rem(1))
        ),

        style(
            lg.v5,
            paddingBottom(rem(1.25))
        ),

        style(
            lg.v6,
            paddingBottom(rem(1.5))
        ),

        style(
            lg.v8,
            paddingBottom(rem(2))
        ),

        style(
            lg.v10,
            paddingBottom(rem(2.5))
        ),

        style(
            lg.v12,
            paddingBottom(rem(3))
        ),

        style(
            lg.v16,
            paddingBottom(rem(4))
        ),

        style(
            lg.v20,
            paddingBottom(rem(5))
        ),

        style(
            lg.v24,
            paddingBottom(rem(6))
        ),

        style(
            lg.v32,
            paddingBottom(rem(8))
        ),

        style(
            lg.v40,
            paddingBottom(rem(10))
        ),

        style(
            lg.v48,
            paddingBottom(rem(12))
        ),

        style(
            lg.v56,
            paddingBottom(rem(14))
        ),

        style(
            lg.v64,
            paddingBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.px,
            paddingBottom(px(1))
        ),

        style(
            xl.v0,
            paddingBottom(zero())
        ),

        style(
            xl.v1,
            paddingBottom(rem(0.25))
        ),

        style(
            xl.v2,
            paddingBottom(rem(0.5))
        ),

        style(
            xl.v3,
            paddingBottom(rem(0.75))
        ),

        style(
            xl.v4,
            paddingBottom(rem(1))
        ),

        style(
            xl.v5,
            paddingBottom(rem(1.25))
        ),

        style(
            xl.v6,
            paddingBottom(rem(1.5))
        ),

        style(
            xl.v8,
            paddingBottom(rem(2))
        ),

        style(
            xl.v10,
            paddingBottom(rem(2.5))
        ),

        style(
            xl.v12,
            paddingBottom(rem(3))
        ),

        style(
            xl.v16,
            paddingBottom(rem(4))
        ),

        style(
            xl.v20,
            paddingBottom(rem(5))
        ),

        style(
            xl.v24,
            paddingBottom(rem(6))
        ),

        style(
            xl.v32,
            paddingBottom(rem(8))
        ),

        style(
            xl.v40,
            paddingBottom(rem(10))
        ),

        style(
            xl.v48,
            paddingBottom(rem(12))
        ),

        style(
            xl.v56,
            paddingBottom(rem(14))
        ),

        style(
            xl.v64,
            paddingBottom(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector px = Css.dot("sm-pb-px");

    ClassSelector v0 = Css.dot("sm-pb-0");

    ClassSelector v1 = Css.dot("sm-pb-1");

    ClassSelector v2 = Css.dot("sm-pb-2");

    ClassSelector v3 = Css.dot("sm-pb-3");

    ClassSelector v4 = Css.dot("sm-pb-4");

    ClassSelector v5 = Css.dot("sm-pb-5");

    ClassSelector v6 = Css.dot("sm-pb-6");

    ClassSelector v8 = Css.dot("sm-pb-8");

    ClassSelector v10 = Css.dot("sm-pb-10");

    ClassSelector v12 = Css.dot("sm-pb-12");

    ClassSelector v16 = Css.dot("sm-pb-16");

    ClassSelector v20 = Css.dot("sm-pb-20");

    ClassSelector v24 = Css.dot("sm-pb-24");

    ClassSelector v32 = Css.dot("sm-pb-32");

    ClassSelector v40 = Css.dot("sm-pb-40");

    ClassSelector v48 = Css.dot("sm-pb-48");

    ClassSelector v56 = Css.dot("sm-pb-56");

    ClassSelector v64 = Css.dot("sm-pb-64");

  }

  public interface md {

    ClassSelector px = Css.dot("md-pb-px");

    ClassSelector v0 = Css.dot("md-pb-0");

    ClassSelector v1 = Css.dot("md-pb-1");

    ClassSelector v2 = Css.dot("md-pb-2");

    ClassSelector v3 = Css.dot("md-pb-3");

    ClassSelector v4 = Css.dot("md-pb-4");

    ClassSelector v5 = Css.dot("md-pb-5");

    ClassSelector v6 = Css.dot("md-pb-6");

    ClassSelector v8 = Css.dot("md-pb-8");

    ClassSelector v10 = Css.dot("md-pb-10");

    ClassSelector v12 = Css.dot("md-pb-12");

    ClassSelector v16 = Css.dot("md-pb-16");

    ClassSelector v20 = Css.dot("md-pb-20");

    ClassSelector v24 = Css.dot("md-pb-24");

    ClassSelector v32 = Css.dot("md-pb-32");

    ClassSelector v40 = Css.dot("md-pb-40");

    ClassSelector v48 = Css.dot("md-pb-48");

    ClassSelector v56 = Css.dot("md-pb-56");

    ClassSelector v64 = Css.dot("md-pb-64");

  }

  public interface lg {

    ClassSelector px = Css.dot("lg-pb-px");

    ClassSelector v0 = Css.dot("lg-pb-0");

    ClassSelector v1 = Css.dot("lg-pb-1");

    ClassSelector v2 = Css.dot("lg-pb-2");

    ClassSelector v3 = Css.dot("lg-pb-3");

    ClassSelector v4 = Css.dot("lg-pb-4");

    ClassSelector v5 = Css.dot("lg-pb-5");

    ClassSelector v6 = Css.dot("lg-pb-6");

    ClassSelector v8 = Css.dot("lg-pb-8");

    ClassSelector v10 = Css.dot("lg-pb-10");

    ClassSelector v12 = Css.dot("lg-pb-12");

    ClassSelector v16 = Css.dot("lg-pb-16");

    ClassSelector v20 = Css.dot("lg-pb-20");

    ClassSelector v24 = Css.dot("lg-pb-24");

    ClassSelector v32 = Css.dot("lg-pb-32");

    ClassSelector v40 = Css.dot("lg-pb-40");

    ClassSelector v48 = Css.dot("lg-pb-48");

    ClassSelector v56 = Css.dot("lg-pb-56");

    ClassSelector v64 = Css.dot("lg-pb-64");

  }

  public interface xl {

    ClassSelector px = Css.dot("xl-pb-px");

    ClassSelector v0 = Css.dot("xl-pb-0");

    ClassSelector v1 = Css.dot("xl-pb-1");

    ClassSelector v2 = Css.dot("xl-pb-2");

    ClassSelector v3 = Css.dot("xl-pb-3");

    ClassSelector v4 = Css.dot("xl-pb-4");

    ClassSelector v5 = Css.dot("xl-pb-5");

    ClassSelector v6 = Css.dot("xl-pb-6");

    ClassSelector v8 = Css.dot("xl-pb-8");

    ClassSelector v10 = Css.dot("xl-pb-10");

    ClassSelector v12 = Css.dot("xl-pb-12");

    ClassSelector v16 = Css.dot("xl-pb-16");

    ClassSelector v20 = Css.dot("xl-pb-20");

    ClassSelector v24 = Css.dot("xl-pb-24");

    ClassSelector v32 = Css.dot("xl-pb-32");

    ClassSelector v40 = Css.dot("xl-pb-40");

    ClassSelector v48 = Css.dot("xl-pb-48");

    ClassSelector v56 = Css.dot("xl-pb-56");

    ClassSelector v64 = Css.dot("xl-pb-64");

  }

}