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
public final class PaddingLeft extends AbstractStyleSheet {

  public static final ClassSelector px = Css.dot("pl-px");

  public static final ClassSelector v0 = Css.dot("pl-0");

  public static final ClassSelector v1 = Css.dot("pl-1");

  public static final ClassSelector v2 = Css.dot("pl-2");

  public static final ClassSelector v3 = Css.dot("pl-3");

  public static final ClassSelector v4 = Css.dot("pl-4");

  public static final ClassSelector v5 = Css.dot("pl-5");

  public static final ClassSelector v6 = Css.dot("pl-6");

  public static final ClassSelector v8 = Css.dot("pl-8");

  public static final ClassSelector v10 = Css.dot("pl-10");

  public static final ClassSelector v12 = Css.dot("pl-12");

  public static final ClassSelector v16 = Css.dot("pl-16");

  public static final ClassSelector v20 = Css.dot("pl-20");

  public static final ClassSelector v24 = Css.dot("pl-24");

  public static final ClassSelector v32 = Css.dot("pl-32");

  public static final ClassSelector v40 = Css.dot("pl-40");

  public static final ClassSelector v48 = Css.dot("pl-48");

  public static final ClassSelector v56 = Css.dot("pl-56");

  public static final ClassSelector v64 = Css.dot("pl-64");

  @Override
  protected final void definition() {
    style(
        px,
        paddingLeft(px(1))
    );
    style(
        v0,
        paddingLeft(zero())
    );
    style(
        v1,
        paddingLeft(rem(0.25))
    );
    style(
        v2,
        paddingLeft(rem(0.5))
    );
    style(
        v3,
        paddingLeft(rem(0.75))
    );
    style(
        v4,
        paddingLeft(rem(1))
    );
    style(
        v5,
        paddingLeft(rem(1.25))
    );
    style(
        v6,
        paddingLeft(rem(1.5))
    );
    style(
        v8,
        paddingLeft(rem(2))
    );
    style(
        v10,
        paddingLeft(rem(2.5))
    );
    style(
        v12,
        paddingLeft(rem(3))
    );
    style(
        v16,
        paddingLeft(rem(4))
    );
    style(
        v20,
        paddingLeft(rem(5))
    );
    style(
        v24,
        paddingLeft(rem(6))
    );
    style(
        v32,
        paddingLeft(rem(8))
    );
    style(
        v40,
        paddingLeft(rem(10))
    );
    style(
        v48,
        paddingLeft(rem(12))
    );
    style(
        v56,
        paddingLeft(rem(14))
    );
    style(
        v64,
        paddingLeft(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.px,
            paddingLeft(px(1))
        ),

        style(
            sm.v0,
            paddingLeft(zero())
        ),

        style(
            sm.v1,
            paddingLeft(rem(0.25))
        ),

        style(
            sm.v2,
            paddingLeft(rem(0.5))
        ),

        style(
            sm.v3,
            paddingLeft(rem(0.75))
        ),

        style(
            sm.v4,
            paddingLeft(rem(1))
        ),

        style(
            sm.v5,
            paddingLeft(rem(1.25))
        ),

        style(
            sm.v6,
            paddingLeft(rem(1.5))
        ),

        style(
            sm.v8,
            paddingLeft(rem(2))
        ),

        style(
            sm.v10,
            paddingLeft(rem(2.5))
        ),

        style(
            sm.v12,
            paddingLeft(rem(3))
        ),

        style(
            sm.v16,
            paddingLeft(rem(4))
        ),

        style(
            sm.v20,
            paddingLeft(rem(5))
        ),

        style(
            sm.v24,
            paddingLeft(rem(6))
        ),

        style(
            sm.v32,
            paddingLeft(rem(8))
        ),

        style(
            sm.v40,
            paddingLeft(rem(10))
        ),

        style(
            sm.v48,
            paddingLeft(rem(12))
        ),

        style(
            sm.v56,
            paddingLeft(rem(14))
        ),

        style(
            sm.v64,
            paddingLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.px,
            paddingLeft(px(1))
        ),

        style(
            md.v0,
            paddingLeft(zero())
        ),

        style(
            md.v1,
            paddingLeft(rem(0.25))
        ),

        style(
            md.v2,
            paddingLeft(rem(0.5))
        ),

        style(
            md.v3,
            paddingLeft(rem(0.75))
        ),

        style(
            md.v4,
            paddingLeft(rem(1))
        ),

        style(
            md.v5,
            paddingLeft(rem(1.25))
        ),

        style(
            md.v6,
            paddingLeft(rem(1.5))
        ),

        style(
            md.v8,
            paddingLeft(rem(2))
        ),

        style(
            md.v10,
            paddingLeft(rem(2.5))
        ),

        style(
            md.v12,
            paddingLeft(rem(3))
        ),

        style(
            md.v16,
            paddingLeft(rem(4))
        ),

        style(
            md.v20,
            paddingLeft(rem(5))
        ),

        style(
            md.v24,
            paddingLeft(rem(6))
        ),

        style(
            md.v32,
            paddingLeft(rem(8))
        ),

        style(
            md.v40,
            paddingLeft(rem(10))
        ),

        style(
            md.v48,
            paddingLeft(rem(12))
        ),

        style(
            md.v56,
            paddingLeft(rem(14))
        ),

        style(
            md.v64,
            paddingLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.px,
            paddingLeft(px(1))
        ),

        style(
            lg.v0,
            paddingLeft(zero())
        ),

        style(
            lg.v1,
            paddingLeft(rem(0.25))
        ),

        style(
            lg.v2,
            paddingLeft(rem(0.5))
        ),

        style(
            lg.v3,
            paddingLeft(rem(0.75))
        ),

        style(
            lg.v4,
            paddingLeft(rem(1))
        ),

        style(
            lg.v5,
            paddingLeft(rem(1.25))
        ),

        style(
            lg.v6,
            paddingLeft(rem(1.5))
        ),

        style(
            lg.v8,
            paddingLeft(rem(2))
        ),

        style(
            lg.v10,
            paddingLeft(rem(2.5))
        ),

        style(
            lg.v12,
            paddingLeft(rem(3))
        ),

        style(
            lg.v16,
            paddingLeft(rem(4))
        ),

        style(
            lg.v20,
            paddingLeft(rem(5))
        ),

        style(
            lg.v24,
            paddingLeft(rem(6))
        ),

        style(
            lg.v32,
            paddingLeft(rem(8))
        ),

        style(
            lg.v40,
            paddingLeft(rem(10))
        ),

        style(
            lg.v48,
            paddingLeft(rem(12))
        ),

        style(
            lg.v56,
            paddingLeft(rem(14))
        ),

        style(
            lg.v64,
            paddingLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.px,
            paddingLeft(px(1))
        ),

        style(
            xl.v0,
            paddingLeft(zero())
        ),

        style(
            xl.v1,
            paddingLeft(rem(0.25))
        ),

        style(
            xl.v2,
            paddingLeft(rem(0.5))
        ),

        style(
            xl.v3,
            paddingLeft(rem(0.75))
        ),

        style(
            xl.v4,
            paddingLeft(rem(1))
        ),

        style(
            xl.v5,
            paddingLeft(rem(1.25))
        ),

        style(
            xl.v6,
            paddingLeft(rem(1.5))
        ),

        style(
            xl.v8,
            paddingLeft(rem(2))
        ),

        style(
            xl.v10,
            paddingLeft(rem(2.5))
        ),

        style(
            xl.v12,
            paddingLeft(rem(3))
        ),

        style(
            xl.v16,
            paddingLeft(rem(4))
        ),

        style(
            xl.v20,
            paddingLeft(rem(5))
        ),

        style(
            xl.v24,
            paddingLeft(rem(6))
        ),

        style(
            xl.v32,
            paddingLeft(rem(8))
        ),

        style(
            xl.v40,
            paddingLeft(rem(10))
        ),

        style(
            xl.v48,
            paddingLeft(rem(12))
        ),

        style(
            xl.v56,
            paddingLeft(rem(14))
        ),

        style(
            xl.v64,
            paddingLeft(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector px = Css.dot("sm-pl-px");

    ClassSelector v0 = Css.dot("sm-pl-0");

    ClassSelector v1 = Css.dot("sm-pl-1");

    ClassSelector v2 = Css.dot("sm-pl-2");

    ClassSelector v3 = Css.dot("sm-pl-3");

    ClassSelector v4 = Css.dot("sm-pl-4");

    ClassSelector v5 = Css.dot("sm-pl-5");

    ClassSelector v6 = Css.dot("sm-pl-6");

    ClassSelector v8 = Css.dot("sm-pl-8");

    ClassSelector v10 = Css.dot("sm-pl-10");

    ClassSelector v12 = Css.dot("sm-pl-12");

    ClassSelector v16 = Css.dot("sm-pl-16");

    ClassSelector v20 = Css.dot("sm-pl-20");

    ClassSelector v24 = Css.dot("sm-pl-24");

    ClassSelector v32 = Css.dot("sm-pl-32");

    ClassSelector v40 = Css.dot("sm-pl-40");

    ClassSelector v48 = Css.dot("sm-pl-48");

    ClassSelector v56 = Css.dot("sm-pl-56");

    ClassSelector v64 = Css.dot("sm-pl-64");

  }

  public interface md {

    ClassSelector px = Css.dot("md-pl-px");

    ClassSelector v0 = Css.dot("md-pl-0");

    ClassSelector v1 = Css.dot("md-pl-1");

    ClassSelector v2 = Css.dot("md-pl-2");

    ClassSelector v3 = Css.dot("md-pl-3");

    ClassSelector v4 = Css.dot("md-pl-4");

    ClassSelector v5 = Css.dot("md-pl-5");

    ClassSelector v6 = Css.dot("md-pl-6");

    ClassSelector v8 = Css.dot("md-pl-8");

    ClassSelector v10 = Css.dot("md-pl-10");

    ClassSelector v12 = Css.dot("md-pl-12");

    ClassSelector v16 = Css.dot("md-pl-16");

    ClassSelector v20 = Css.dot("md-pl-20");

    ClassSelector v24 = Css.dot("md-pl-24");

    ClassSelector v32 = Css.dot("md-pl-32");

    ClassSelector v40 = Css.dot("md-pl-40");

    ClassSelector v48 = Css.dot("md-pl-48");

    ClassSelector v56 = Css.dot("md-pl-56");

    ClassSelector v64 = Css.dot("md-pl-64");

  }

  public interface lg {

    ClassSelector px = Css.dot("lg-pl-px");

    ClassSelector v0 = Css.dot("lg-pl-0");

    ClassSelector v1 = Css.dot("lg-pl-1");

    ClassSelector v2 = Css.dot("lg-pl-2");

    ClassSelector v3 = Css.dot("lg-pl-3");

    ClassSelector v4 = Css.dot("lg-pl-4");

    ClassSelector v5 = Css.dot("lg-pl-5");

    ClassSelector v6 = Css.dot("lg-pl-6");

    ClassSelector v8 = Css.dot("lg-pl-8");

    ClassSelector v10 = Css.dot("lg-pl-10");

    ClassSelector v12 = Css.dot("lg-pl-12");

    ClassSelector v16 = Css.dot("lg-pl-16");

    ClassSelector v20 = Css.dot("lg-pl-20");

    ClassSelector v24 = Css.dot("lg-pl-24");

    ClassSelector v32 = Css.dot("lg-pl-32");

    ClassSelector v40 = Css.dot("lg-pl-40");

    ClassSelector v48 = Css.dot("lg-pl-48");

    ClassSelector v56 = Css.dot("lg-pl-56");

    ClassSelector v64 = Css.dot("lg-pl-64");

  }

  public interface xl {

    ClassSelector px = Css.dot("xl-pl-px");

    ClassSelector v0 = Css.dot("xl-pl-0");

    ClassSelector v1 = Css.dot("xl-pl-1");

    ClassSelector v2 = Css.dot("xl-pl-2");

    ClassSelector v3 = Css.dot("xl-pl-3");

    ClassSelector v4 = Css.dot("xl-pl-4");

    ClassSelector v5 = Css.dot("xl-pl-5");

    ClassSelector v6 = Css.dot("xl-pl-6");

    ClassSelector v8 = Css.dot("xl-pl-8");

    ClassSelector v10 = Css.dot("xl-pl-10");

    ClassSelector v12 = Css.dot("xl-pl-12");

    ClassSelector v16 = Css.dot("xl-pl-16");

    ClassSelector v20 = Css.dot("xl-pl-20");

    ClassSelector v24 = Css.dot("xl-pl-24");

    ClassSelector v32 = Css.dot("xl-pl-32");

    ClassSelector v40 = Css.dot("xl-pl-40");

    ClassSelector v48 = Css.dot("xl-pl-48");

    ClassSelector v56 = Css.dot("xl-pl-56");

    ClassSelector v64 = Css.dot("xl-pl-64");

  }

}