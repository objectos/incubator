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
public final class Padding extends AbstractStyleSheet {

  public static final ClassSelector px = Css.dot("p-px");

  public static final ClassSelector v0 = Css.dot("p-0");

  public static final ClassSelector v1 = Css.dot("p-1");

  public static final ClassSelector v2 = Css.dot("p-2");

  public static final ClassSelector v3 = Css.dot("p-3");

  public static final ClassSelector v4 = Css.dot("p-4");

  public static final ClassSelector v5 = Css.dot("p-5");

  public static final ClassSelector v6 = Css.dot("p-6");

  public static final ClassSelector v8 = Css.dot("p-8");

  public static final ClassSelector v10 = Css.dot("p-10");

  public static final ClassSelector v12 = Css.dot("p-12");

  public static final ClassSelector v16 = Css.dot("p-16");

  public static final ClassSelector v20 = Css.dot("p-20");

  public static final ClassSelector v24 = Css.dot("p-24");

  public static final ClassSelector v32 = Css.dot("p-32");

  public static final ClassSelector v40 = Css.dot("p-40");

  public static final ClassSelector v48 = Css.dot("p-48");

  public static final ClassSelector v56 = Css.dot("p-56");

  public static final ClassSelector v64 = Css.dot("p-64");

  @Override
  protected final void definition() {
    style(
        px,
        padding(px(1))
    );
    style(
        v0,
        padding(zero())
    );
    style(
        v1,
        padding(rem(0.25))
    );
    style(
        v2,
        padding(rem(0.5))
    );
    style(
        v3,
        padding(rem(0.75))
    );
    style(
        v4,
        padding(rem(1))
    );
    style(
        v5,
        padding(rem(1.25))
    );
    style(
        v6,
        padding(rem(1.5))
    );
    style(
        v8,
        padding(rem(2))
    );
    style(
        v10,
        padding(rem(2.5))
    );
    style(
        v12,
        padding(rem(3))
    );
    style(
        v16,
        padding(rem(4))
    );
    style(
        v20,
        padding(rem(5))
    );
    style(
        v24,
        padding(rem(6))
    );
    style(
        v32,
        padding(rem(8))
    );
    style(
        v40,
        padding(rem(10))
    );
    style(
        v48,
        padding(rem(12))
    );
    style(
        v56,
        padding(rem(14))
    );
    style(
        v64,
        padding(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.px,
            padding(px(1))
        ),

        style(
            sm.v0,
            padding(zero())
        ),

        style(
            sm.v1,
            padding(rem(0.25))
        ),

        style(
            sm.v2,
            padding(rem(0.5))
        ),

        style(
            sm.v3,
            padding(rem(0.75))
        ),

        style(
            sm.v4,
            padding(rem(1))
        ),

        style(
            sm.v5,
            padding(rem(1.25))
        ),

        style(
            sm.v6,
            padding(rem(1.5))
        ),

        style(
            sm.v8,
            padding(rem(2))
        ),

        style(
            sm.v10,
            padding(rem(2.5))
        ),

        style(
            sm.v12,
            padding(rem(3))
        ),

        style(
            sm.v16,
            padding(rem(4))
        ),

        style(
            sm.v20,
            padding(rem(5))
        ),

        style(
            sm.v24,
            padding(rem(6))
        ),

        style(
            sm.v32,
            padding(rem(8))
        ),

        style(
            sm.v40,
            padding(rem(10))
        ),

        style(
            sm.v48,
            padding(rem(12))
        ),

        style(
            sm.v56,
            padding(rem(14))
        ),

        style(
            sm.v64,
            padding(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.px,
            padding(px(1))
        ),

        style(
            md.v0,
            padding(zero())
        ),

        style(
            md.v1,
            padding(rem(0.25))
        ),

        style(
            md.v2,
            padding(rem(0.5))
        ),

        style(
            md.v3,
            padding(rem(0.75))
        ),

        style(
            md.v4,
            padding(rem(1))
        ),

        style(
            md.v5,
            padding(rem(1.25))
        ),

        style(
            md.v6,
            padding(rem(1.5))
        ),

        style(
            md.v8,
            padding(rem(2))
        ),

        style(
            md.v10,
            padding(rem(2.5))
        ),

        style(
            md.v12,
            padding(rem(3))
        ),

        style(
            md.v16,
            padding(rem(4))
        ),

        style(
            md.v20,
            padding(rem(5))
        ),

        style(
            md.v24,
            padding(rem(6))
        ),

        style(
            md.v32,
            padding(rem(8))
        ),

        style(
            md.v40,
            padding(rem(10))
        ),

        style(
            md.v48,
            padding(rem(12))
        ),

        style(
            md.v56,
            padding(rem(14))
        ),

        style(
            md.v64,
            padding(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.px,
            padding(px(1))
        ),

        style(
            lg.v0,
            padding(zero())
        ),

        style(
            lg.v1,
            padding(rem(0.25))
        ),

        style(
            lg.v2,
            padding(rem(0.5))
        ),

        style(
            lg.v3,
            padding(rem(0.75))
        ),

        style(
            lg.v4,
            padding(rem(1))
        ),

        style(
            lg.v5,
            padding(rem(1.25))
        ),

        style(
            lg.v6,
            padding(rem(1.5))
        ),

        style(
            lg.v8,
            padding(rem(2))
        ),

        style(
            lg.v10,
            padding(rem(2.5))
        ),

        style(
            lg.v12,
            padding(rem(3))
        ),

        style(
            lg.v16,
            padding(rem(4))
        ),

        style(
            lg.v20,
            padding(rem(5))
        ),

        style(
            lg.v24,
            padding(rem(6))
        ),

        style(
            lg.v32,
            padding(rem(8))
        ),

        style(
            lg.v40,
            padding(rem(10))
        ),

        style(
            lg.v48,
            padding(rem(12))
        ),

        style(
            lg.v56,
            padding(rem(14))
        ),

        style(
            lg.v64,
            padding(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.px,
            padding(px(1))
        ),

        style(
            xl.v0,
            padding(zero())
        ),

        style(
            xl.v1,
            padding(rem(0.25))
        ),

        style(
            xl.v2,
            padding(rem(0.5))
        ),

        style(
            xl.v3,
            padding(rem(0.75))
        ),

        style(
            xl.v4,
            padding(rem(1))
        ),

        style(
            xl.v5,
            padding(rem(1.25))
        ),

        style(
            xl.v6,
            padding(rem(1.5))
        ),

        style(
            xl.v8,
            padding(rem(2))
        ),

        style(
            xl.v10,
            padding(rem(2.5))
        ),

        style(
            xl.v12,
            padding(rem(3))
        ),

        style(
            xl.v16,
            padding(rem(4))
        ),

        style(
            xl.v20,
            padding(rem(5))
        ),

        style(
            xl.v24,
            padding(rem(6))
        ),

        style(
            xl.v32,
            padding(rem(8))
        ),

        style(
            xl.v40,
            padding(rem(10))
        ),

        style(
            xl.v48,
            padding(rem(12))
        ),

        style(
            xl.v56,
            padding(rem(14))
        ),

        style(
            xl.v64,
            padding(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector px = Css.dot("sm-p-px");

    ClassSelector v0 = Css.dot("sm-p-0");

    ClassSelector v1 = Css.dot("sm-p-1");

    ClassSelector v2 = Css.dot("sm-p-2");

    ClassSelector v3 = Css.dot("sm-p-3");

    ClassSelector v4 = Css.dot("sm-p-4");

    ClassSelector v5 = Css.dot("sm-p-5");

    ClassSelector v6 = Css.dot("sm-p-6");

    ClassSelector v8 = Css.dot("sm-p-8");

    ClassSelector v10 = Css.dot("sm-p-10");

    ClassSelector v12 = Css.dot("sm-p-12");

    ClassSelector v16 = Css.dot("sm-p-16");

    ClassSelector v20 = Css.dot("sm-p-20");

    ClassSelector v24 = Css.dot("sm-p-24");

    ClassSelector v32 = Css.dot("sm-p-32");

    ClassSelector v40 = Css.dot("sm-p-40");

    ClassSelector v48 = Css.dot("sm-p-48");

    ClassSelector v56 = Css.dot("sm-p-56");

    ClassSelector v64 = Css.dot("sm-p-64");

  }

  public interface md {

    ClassSelector px = Css.dot("md-p-px");

    ClassSelector v0 = Css.dot("md-p-0");

    ClassSelector v1 = Css.dot("md-p-1");

    ClassSelector v2 = Css.dot("md-p-2");

    ClassSelector v3 = Css.dot("md-p-3");

    ClassSelector v4 = Css.dot("md-p-4");

    ClassSelector v5 = Css.dot("md-p-5");

    ClassSelector v6 = Css.dot("md-p-6");

    ClassSelector v8 = Css.dot("md-p-8");

    ClassSelector v10 = Css.dot("md-p-10");

    ClassSelector v12 = Css.dot("md-p-12");

    ClassSelector v16 = Css.dot("md-p-16");

    ClassSelector v20 = Css.dot("md-p-20");

    ClassSelector v24 = Css.dot("md-p-24");

    ClassSelector v32 = Css.dot("md-p-32");

    ClassSelector v40 = Css.dot("md-p-40");

    ClassSelector v48 = Css.dot("md-p-48");

    ClassSelector v56 = Css.dot("md-p-56");

    ClassSelector v64 = Css.dot("md-p-64");

  }

  public interface lg {

    ClassSelector px = Css.dot("lg-p-px");

    ClassSelector v0 = Css.dot("lg-p-0");

    ClassSelector v1 = Css.dot("lg-p-1");

    ClassSelector v2 = Css.dot("lg-p-2");

    ClassSelector v3 = Css.dot("lg-p-3");

    ClassSelector v4 = Css.dot("lg-p-4");

    ClassSelector v5 = Css.dot("lg-p-5");

    ClassSelector v6 = Css.dot("lg-p-6");

    ClassSelector v8 = Css.dot("lg-p-8");

    ClassSelector v10 = Css.dot("lg-p-10");

    ClassSelector v12 = Css.dot("lg-p-12");

    ClassSelector v16 = Css.dot("lg-p-16");

    ClassSelector v20 = Css.dot("lg-p-20");

    ClassSelector v24 = Css.dot("lg-p-24");

    ClassSelector v32 = Css.dot("lg-p-32");

    ClassSelector v40 = Css.dot("lg-p-40");

    ClassSelector v48 = Css.dot("lg-p-48");

    ClassSelector v56 = Css.dot("lg-p-56");

    ClassSelector v64 = Css.dot("lg-p-64");

  }

  public interface xl {

    ClassSelector px = Css.dot("xl-p-px");

    ClassSelector v0 = Css.dot("xl-p-0");

    ClassSelector v1 = Css.dot("xl-p-1");

    ClassSelector v2 = Css.dot("xl-p-2");

    ClassSelector v3 = Css.dot("xl-p-3");

    ClassSelector v4 = Css.dot("xl-p-4");

    ClassSelector v5 = Css.dot("xl-p-5");

    ClassSelector v6 = Css.dot("xl-p-6");

    ClassSelector v8 = Css.dot("xl-p-8");

    ClassSelector v10 = Css.dot("xl-p-10");

    ClassSelector v12 = Css.dot("xl-p-12");

    ClassSelector v16 = Css.dot("xl-p-16");

    ClassSelector v20 = Css.dot("xl-p-20");

    ClassSelector v24 = Css.dot("xl-p-24");

    ClassSelector v32 = Css.dot("xl-p-32");

    ClassSelector v40 = Css.dot("xl-p-40");

    ClassSelector v48 = Css.dot("xl-p-48");

    ClassSelector v56 = Css.dot("xl-p-56");

    ClassSelector v64 = Css.dot("xl-p-64");

  }

}