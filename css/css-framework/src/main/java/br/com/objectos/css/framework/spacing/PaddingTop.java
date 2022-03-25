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
public final class PaddingTop extends AbstractStyleSheet {

  public static final ClassSelector px = Css.dot("pt-px");

  public static final ClassSelector v0 = Css.dot("pt-0");

  public static final ClassSelector v1 = Css.dot("pt-1");

  public static final ClassSelector v2 = Css.dot("pt-2");

  public static final ClassSelector v3 = Css.dot("pt-3");

  public static final ClassSelector v4 = Css.dot("pt-4");

  public static final ClassSelector v5 = Css.dot("pt-5");

  public static final ClassSelector v6 = Css.dot("pt-6");

  public static final ClassSelector v8 = Css.dot("pt-8");

  public static final ClassSelector v10 = Css.dot("pt-10");

  public static final ClassSelector v12 = Css.dot("pt-12");

  public static final ClassSelector v16 = Css.dot("pt-16");

  public static final ClassSelector v20 = Css.dot("pt-20");

  public static final ClassSelector v24 = Css.dot("pt-24");

  public static final ClassSelector v32 = Css.dot("pt-32");

  public static final ClassSelector v40 = Css.dot("pt-40");

  public static final ClassSelector v48 = Css.dot("pt-48");

  public static final ClassSelector v56 = Css.dot("pt-56");

  public static final ClassSelector v64 = Css.dot("pt-64");

  @Override
  protected final void definition() {
    style(
        px,
        paddingTop(px(1))
    );
    style(
        v0,
        paddingTop(zero())
    );
    style(
        v1,
        paddingTop(rem(0.25))
    );
    style(
        v2,
        paddingTop(rem(0.5))
    );
    style(
        v3,
        paddingTop(rem(0.75))
    );
    style(
        v4,
        paddingTop(rem(1))
    );
    style(
        v5,
        paddingTop(rem(1.25))
    );
    style(
        v6,
        paddingTop(rem(1.5))
    );
    style(
        v8,
        paddingTop(rem(2))
    );
    style(
        v10,
        paddingTop(rem(2.5))
    );
    style(
        v12,
        paddingTop(rem(3))
    );
    style(
        v16,
        paddingTop(rem(4))
    );
    style(
        v20,
        paddingTop(rem(5))
    );
    style(
        v24,
        paddingTop(rem(6))
    );
    style(
        v32,
        paddingTop(rem(8))
    );
    style(
        v40,
        paddingTop(rem(10))
    );
    style(
        v48,
        paddingTop(rem(12))
    );
    style(
        v56,
        paddingTop(rem(14))
    );
    style(
        v64,
        paddingTop(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.px,
            paddingTop(px(1))
        ),

        style(
            sm.v0,
            paddingTop(zero())
        ),

        style(
            sm.v1,
            paddingTop(rem(0.25))
        ),

        style(
            sm.v2,
            paddingTop(rem(0.5))
        ),

        style(
            sm.v3,
            paddingTop(rem(0.75))
        ),

        style(
            sm.v4,
            paddingTop(rem(1))
        ),

        style(
            sm.v5,
            paddingTop(rem(1.25))
        ),

        style(
            sm.v6,
            paddingTop(rem(1.5))
        ),

        style(
            sm.v8,
            paddingTop(rem(2))
        ),

        style(
            sm.v10,
            paddingTop(rem(2.5))
        ),

        style(
            sm.v12,
            paddingTop(rem(3))
        ),

        style(
            sm.v16,
            paddingTop(rem(4))
        ),

        style(
            sm.v20,
            paddingTop(rem(5))
        ),

        style(
            sm.v24,
            paddingTop(rem(6))
        ),

        style(
            sm.v32,
            paddingTop(rem(8))
        ),

        style(
            sm.v40,
            paddingTop(rem(10))
        ),

        style(
            sm.v48,
            paddingTop(rem(12))
        ),

        style(
            sm.v56,
            paddingTop(rem(14))
        ),

        style(
            sm.v64,
            paddingTop(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.px,
            paddingTop(px(1))
        ),

        style(
            md.v0,
            paddingTop(zero())
        ),

        style(
            md.v1,
            paddingTop(rem(0.25))
        ),

        style(
            md.v2,
            paddingTop(rem(0.5))
        ),

        style(
            md.v3,
            paddingTop(rem(0.75))
        ),

        style(
            md.v4,
            paddingTop(rem(1))
        ),

        style(
            md.v5,
            paddingTop(rem(1.25))
        ),

        style(
            md.v6,
            paddingTop(rem(1.5))
        ),

        style(
            md.v8,
            paddingTop(rem(2))
        ),

        style(
            md.v10,
            paddingTop(rem(2.5))
        ),

        style(
            md.v12,
            paddingTop(rem(3))
        ),

        style(
            md.v16,
            paddingTop(rem(4))
        ),

        style(
            md.v20,
            paddingTop(rem(5))
        ),

        style(
            md.v24,
            paddingTop(rem(6))
        ),

        style(
            md.v32,
            paddingTop(rem(8))
        ),

        style(
            md.v40,
            paddingTop(rem(10))
        ),

        style(
            md.v48,
            paddingTop(rem(12))
        ),

        style(
            md.v56,
            paddingTop(rem(14))
        ),

        style(
            md.v64,
            paddingTop(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.px,
            paddingTop(px(1))
        ),

        style(
            lg.v0,
            paddingTop(zero())
        ),

        style(
            lg.v1,
            paddingTop(rem(0.25))
        ),

        style(
            lg.v2,
            paddingTop(rem(0.5))
        ),

        style(
            lg.v3,
            paddingTop(rem(0.75))
        ),

        style(
            lg.v4,
            paddingTop(rem(1))
        ),

        style(
            lg.v5,
            paddingTop(rem(1.25))
        ),

        style(
            lg.v6,
            paddingTop(rem(1.5))
        ),

        style(
            lg.v8,
            paddingTop(rem(2))
        ),

        style(
            lg.v10,
            paddingTop(rem(2.5))
        ),

        style(
            lg.v12,
            paddingTop(rem(3))
        ),

        style(
            lg.v16,
            paddingTop(rem(4))
        ),

        style(
            lg.v20,
            paddingTop(rem(5))
        ),

        style(
            lg.v24,
            paddingTop(rem(6))
        ),

        style(
            lg.v32,
            paddingTop(rem(8))
        ),

        style(
            lg.v40,
            paddingTop(rem(10))
        ),

        style(
            lg.v48,
            paddingTop(rem(12))
        ),

        style(
            lg.v56,
            paddingTop(rem(14))
        ),

        style(
            lg.v64,
            paddingTop(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.px,
            paddingTop(px(1))
        ),

        style(
            xl.v0,
            paddingTop(zero())
        ),

        style(
            xl.v1,
            paddingTop(rem(0.25))
        ),

        style(
            xl.v2,
            paddingTop(rem(0.5))
        ),

        style(
            xl.v3,
            paddingTop(rem(0.75))
        ),

        style(
            xl.v4,
            paddingTop(rem(1))
        ),

        style(
            xl.v5,
            paddingTop(rem(1.25))
        ),

        style(
            xl.v6,
            paddingTop(rem(1.5))
        ),

        style(
            xl.v8,
            paddingTop(rem(2))
        ),

        style(
            xl.v10,
            paddingTop(rem(2.5))
        ),

        style(
            xl.v12,
            paddingTop(rem(3))
        ),

        style(
            xl.v16,
            paddingTop(rem(4))
        ),

        style(
            xl.v20,
            paddingTop(rem(5))
        ),

        style(
            xl.v24,
            paddingTop(rem(6))
        ),

        style(
            xl.v32,
            paddingTop(rem(8))
        ),

        style(
            xl.v40,
            paddingTop(rem(10))
        ),

        style(
            xl.v48,
            paddingTop(rem(12))
        ),

        style(
            xl.v56,
            paddingTop(rem(14))
        ),

        style(
            xl.v64,
            paddingTop(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector px = Css.dot("sm-pt-px");

    ClassSelector v0 = Css.dot("sm-pt-0");

    ClassSelector v1 = Css.dot("sm-pt-1");

    ClassSelector v2 = Css.dot("sm-pt-2");

    ClassSelector v3 = Css.dot("sm-pt-3");

    ClassSelector v4 = Css.dot("sm-pt-4");

    ClassSelector v5 = Css.dot("sm-pt-5");

    ClassSelector v6 = Css.dot("sm-pt-6");

    ClassSelector v8 = Css.dot("sm-pt-8");

    ClassSelector v10 = Css.dot("sm-pt-10");

    ClassSelector v12 = Css.dot("sm-pt-12");

    ClassSelector v16 = Css.dot("sm-pt-16");

    ClassSelector v20 = Css.dot("sm-pt-20");

    ClassSelector v24 = Css.dot("sm-pt-24");

    ClassSelector v32 = Css.dot("sm-pt-32");

    ClassSelector v40 = Css.dot("sm-pt-40");

    ClassSelector v48 = Css.dot("sm-pt-48");

    ClassSelector v56 = Css.dot("sm-pt-56");

    ClassSelector v64 = Css.dot("sm-pt-64");

  }

  public interface md {

    ClassSelector px = Css.dot("md-pt-px");

    ClassSelector v0 = Css.dot("md-pt-0");

    ClassSelector v1 = Css.dot("md-pt-1");

    ClassSelector v2 = Css.dot("md-pt-2");

    ClassSelector v3 = Css.dot("md-pt-3");

    ClassSelector v4 = Css.dot("md-pt-4");

    ClassSelector v5 = Css.dot("md-pt-5");

    ClassSelector v6 = Css.dot("md-pt-6");

    ClassSelector v8 = Css.dot("md-pt-8");

    ClassSelector v10 = Css.dot("md-pt-10");

    ClassSelector v12 = Css.dot("md-pt-12");

    ClassSelector v16 = Css.dot("md-pt-16");

    ClassSelector v20 = Css.dot("md-pt-20");

    ClassSelector v24 = Css.dot("md-pt-24");

    ClassSelector v32 = Css.dot("md-pt-32");

    ClassSelector v40 = Css.dot("md-pt-40");

    ClassSelector v48 = Css.dot("md-pt-48");

    ClassSelector v56 = Css.dot("md-pt-56");

    ClassSelector v64 = Css.dot("md-pt-64");

  }

  public interface lg {

    ClassSelector px = Css.dot("lg-pt-px");

    ClassSelector v0 = Css.dot("lg-pt-0");

    ClassSelector v1 = Css.dot("lg-pt-1");

    ClassSelector v2 = Css.dot("lg-pt-2");

    ClassSelector v3 = Css.dot("lg-pt-3");

    ClassSelector v4 = Css.dot("lg-pt-4");

    ClassSelector v5 = Css.dot("lg-pt-5");

    ClassSelector v6 = Css.dot("lg-pt-6");

    ClassSelector v8 = Css.dot("lg-pt-8");

    ClassSelector v10 = Css.dot("lg-pt-10");

    ClassSelector v12 = Css.dot("lg-pt-12");

    ClassSelector v16 = Css.dot("lg-pt-16");

    ClassSelector v20 = Css.dot("lg-pt-20");

    ClassSelector v24 = Css.dot("lg-pt-24");

    ClassSelector v32 = Css.dot("lg-pt-32");

    ClassSelector v40 = Css.dot("lg-pt-40");

    ClassSelector v48 = Css.dot("lg-pt-48");

    ClassSelector v56 = Css.dot("lg-pt-56");

    ClassSelector v64 = Css.dot("lg-pt-64");

  }

  public interface xl {

    ClassSelector px = Css.dot("xl-pt-px");

    ClassSelector v0 = Css.dot("xl-pt-0");

    ClassSelector v1 = Css.dot("xl-pt-1");

    ClassSelector v2 = Css.dot("xl-pt-2");

    ClassSelector v3 = Css.dot("xl-pt-3");

    ClassSelector v4 = Css.dot("xl-pt-4");

    ClassSelector v5 = Css.dot("xl-pt-5");

    ClassSelector v6 = Css.dot("xl-pt-6");

    ClassSelector v8 = Css.dot("xl-pt-8");

    ClassSelector v10 = Css.dot("xl-pt-10");

    ClassSelector v12 = Css.dot("xl-pt-12");

    ClassSelector v16 = Css.dot("xl-pt-16");

    ClassSelector v20 = Css.dot("xl-pt-20");

    ClassSelector v24 = Css.dot("xl-pt-24");

    ClassSelector v32 = Css.dot("xl-pt-32");

    ClassSelector v40 = Css.dot("xl-pt-40");

    ClassSelector v48 = Css.dot("xl-pt-48");

    ClassSelector v56 = Css.dot("xl-pt-56");

    ClassSelector v64 = Css.dot("xl-pt-64");

  }

}