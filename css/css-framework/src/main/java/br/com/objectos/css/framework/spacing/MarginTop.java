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
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class MarginTop extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("mt-auto");

  public static final ClassSelector px = Css.dot("mt-px");

  public static final ClassSelector v0 = Css.dot("mt-0");

  public static final ClassSelector v1 = Css.dot("mt-1");

  public static final ClassSelector v2 = Css.dot("mt-2");

  public static final ClassSelector v3 = Css.dot("mt-3");

  public static final ClassSelector v4 = Css.dot("mt-4");

  public static final ClassSelector v5 = Css.dot("mt-5");

  public static final ClassSelector v6 = Css.dot("mt-6");

  public static final ClassSelector v8 = Css.dot("mt-8");

  public static final ClassSelector v10 = Css.dot("mt-10");

  public static final ClassSelector v12 = Css.dot("mt-12");

  public static final ClassSelector v16 = Css.dot("mt-16");

  public static final ClassSelector v20 = Css.dot("mt-20");

  public static final ClassSelector v24 = Css.dot("mt-24");

  public static final ClassSelector v32 = Css.dot("mt-32");

  public static final ClassSelector v40 = Css.dot("mt-40");

  public static final ClassSelector v48 = Css.dot("mt-48");

  public static final ClassSelector v56 = Css.dot("mt-56");

  public static final ClassSelector v64 = Css.dot("mt-64");

  @Override
  protected final void definition() {
    style(
        auto,
        marginTop(Keywords.auto)
    );
    style(
        px,
        marginTop(px(1))
    );
    style(
        v0,
        marginTop(zero())
    );
    style(
        v1,
        marginTop(rem(0.25))
    );
    style(
        v2,
        marginTop(rem(0.5))
    );
    style(
        v3,
        marginTop(rem(0.75))
    );
    style(
        v4,
        marginTop(rem(1))
    );
    style(
        v5,
        marginTop(rem(1.25))
    );
    style(
        v6,
        marginTop(rem(1.5))
    );
    style(
        v8,
        marginTop(rem(2))
    );
    style(
        v10,
        marginTop(rem(2.5))
    );
    style(
        v12,
        marginTop(rem(3))
    );
    style(
        v16,
        marginTop(rem(4))
    );
    style(
        v20,
        marginTop(rem(5))
    );
    style(
        v24,
        marginTop(rem(6))
    );
    style(
        v32,
        marginTop(rem(8))
    );
    style(
        v40,
        marginTop(rem(10))
    );
    style(
        v48,
        marginTop(rem(12))
    );
    style(
        v56,
        marginTop(rem(14))
    );
    style(
        v64,
        marginTop(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            marginTop(Keywords.auto)
        ),

        style(
            sm.px,
            marginTop(px(1))
        ),

        style(
            sm.v0,
            marginTop(zero())
        ),

        style(
            sm.v1,
            marginTop(rem(0.25))
        ),

        style(
            sm.v2,
            marginTop(rem(0.5))
        ),

        style(
            sm.v3,
            marginTop(rem(0.75))
        ),

        style(
            sm.v4,
            marginTop(rem(1))
        ),

        style(
            sm.v5,
            marginTop(rem(1.25))
        ),

        style(
            sm.v6,
            marginTop(rem(1.5))
        ),

        style(
            sm.v8,
            marginTop(rem(2))
        ),

        style(
            sm.v10,
            marginTop(rem(2.5))
        ),

        style(
            sm.v12,
            marginTop(rem(3))
        ),

        style(
            sm.v16,
            marginTop(rem(4))
        ),

        style(
            sm.v20,
            marginTop(rem(5))
        ),

        style(
            sm.v24,
            marginTop(rem(6))
        ),

        style(
            sm.v32,
            marginTop(rem(8))
        ),

        style(
            sm.v40,
            marginTop(rem(10))
        ),

        style(
            sm.v48,
            marginTop(rem(12))
        ),

        style(
            sm.v56,
            marginTop(rem(14))
        ),

        style(
            sm.v64,
            marginTop(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            marginTop(Keywords.auto)
        ),

        style(
            md.px,
            marginTop(px(1))
        ),

        style(
            md.v0,
            marginTop(zero())
        ),

        style(
            md.v1,
            marginTop(rem(0.25))
        ),

        style(
            md.v2,
            marginTop(rem(0.5))
        ),

        style(
            md.v3,
            marginTop(rem(0.75))
        ),

        style(
            md.v4,
            marginTop(rem(1))
        ),

        style(
            md.v5,
            marginTop(rem(1.25))
        ),

        style(
            md.v6,
            marginTop(rem(1.5))
        ),

        style(
            md.v8,
            marginTop(rem(2))
        ),

        style(
            md.v10,
            marginTop(rem(2.5))
        ),

        style(
            md.v12,
            marginTop(rem(3))
        ),

        style(
            md.v16,
            marginTop(rem(4))
        ),

        style(
            md.v20,
            marginTop(rem(5))
        ),

        style(
            md.v24,
            marginTop(rem(6))
        ),

        style(
            md.v32,
            marginTop(rem(8))
        ),

        style(
            md.v40,
            marginTop(rem(10))
        ),

        style(
            md.v48,
            marginTop(rem(12))
        ),

        style(
            md.v56,
            marginTop(rem(14))
        ),

        style(
            md.v64,
            marginTop(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            marginTop(Keywords.auto)
        ),

        style(
            lg.px,
            marginTop(px(1))
        ),

        style(
            lg.v0,
            marginTop(zero())
        ),

        style(
            lg.v1,
            marginTop(rem(0.25))
        ),

        style(
            lg.v2,
            marginTop(rem(0.5))
        ),

        style(
            lg.v3,
            marginTop(rem(0.75))
        ),

        style(
            lg.v4,
            marginTop(rem(1))
        ),

        style(
            lg.v5,
            marginTop(rem(1.25))
        ),

        style(
            lg.v6,
            marginTop(rem(1.5))
        ),

        style(
            lg.v8,
            marginTop(rem(2))
        ),

        style(
            lg.v10,
            marginTop(rem(2.5))
        ),

        style(
            lg.v12,
            marginTop(rem(3))
        ),

        style(
            lg.v16,
            marginTop(rem(4))
        ),

        style(
            lg.v20,
            marginTop(rem(5))
        ),

        style(
            lg.v24,
            marginTop(rem(6))
        ),

        style(
            lg.v32,
            marginTop(rem(8))
        ),

        style(
            lg.v40,
            marginTop(rem(10))
        ),

        style(
            lg.v48,
            marginTop(rem(12))
        ),

        style(
            lg.v56,
            marginTop(rem(14))
        ),

        style(
            lg.v64,
            marginTop(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            marginTop(Keywords.auto)
        ),

        style(
            xl.px,
            marginTop(px(1))
        ),

        style(
            xl.v0,
            marginTop(zero())
        ),

        style(
            xl.v1,
            marginTop(rem(0.25))
        ),

        style(
            xl.v2,
            marginTop(rem(0.5))
        ),

        style(
            xl.v3,
            marginTop(rem(0.75))
        ),

        style(
            xl.v4,
            marginTop(rem(1))
        ),

        style(
            xl.v5,
            marginTop(rem(1.25))
        ),

        style(
            xl.v6,
            marginTop(rem(1.5))
        ),

        style(
            xl.v8,
            marginTop(rem(2))
        ),

        style(
            xl.v10,
            marginTop(rem(2.5))
        ),

        style(
            xl.v12,
            marginTop(rem(3))
        ),

        style(
            xl.v16,
            marginTop(rem(4))
        ),

        style(
            xl.v20,
            marginTop(rem(5))
        ),

        style(
            xl.v24,
            marginTop(rem(6))
        ),

        style(
            xl.v32,
            marginTop(rem(8))
        ),

        style(
            xl.v40,
            marginTop(rem(10))
        ),

        style(
            xl.v48,
            marginTop(rem(12))
        ),

        style(
            xl.v56,
            marginTop(rem(14))
        ),

        style(
            xl.v64,
            marginTop(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-mt-auto");

    ClassSelector px = Css.dot("sm-mt-px");

    ClassSelector v0 = Css.dot("sm-mt-0");

    ClassSelector v1 = Css.dot("sm-mt-1");

    ClassSelector v2 = Css.dot("sm-mt-2");

    ClassSelector v3 = Css.dot("sm-mt-3");

    ClassSelector v4 = Css.dot("sm-mt-4");

    ClassSelector v5 = Css.dot("sm-mt-5");

    ClassSelector v6 = Css.dot("sm-mt-6");

    ClassSelector v8 = Css.dot("sm-mt-8");

    ClassSelector v10 = Css.dot("sm-mt-10");

    ClassSelector v12 = Css.dot("sm-mt-12");

    ClassSelector v16 = Css.dot("sm-mt-16");

    ClassSelector v20 = Css.dot("sm-mt-20");

    ClassSelector v24 = Css.dot("sm-mt-24");

    ClassSelector v32 = Css.dot("sm-mt-32");

    ClassSelector v40 = Css.dot("sm-mt-40");

    ClassSelector v48 = Css.dot("sm-mt-48");

    ClassSelector v56 = Css.dot("sm-mt-56");

    ClassSelector v64 = Css.dot("sm-mt-64");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-mt-auto");

    ClassSelector px = Css.dot("md-mt-px");

    ClassSelector v0 = Css.dot("md-mt-0");

    ClassSelector v1 = Css.dot("md-mt-1");

    ClassSelector v2 = Css.dot("md-mt-2");

    ClassSelector v3 = Css.dot("md-mt-3");

    ClassSelector v4 = Css.dot("md-mt-4");

    ClassSelector v5 = Css.dot("md-mt-5");

    ClassSelector v6 = Css.dot("md-mt-6");

    ClassSelector v8 = Css.dot("md-mt-8");

    ClassSelector v10 = Css.dot("md-mt-10");

    ClassSelector v12 = Css.dot("md-mt-12");

    ClassSelector v16 = Css.dot("md-mt-16");

    ClassSelector v20 = Css.dot("md-mt-20");

    ClassSelector v24 = Css.dot("md-mt-24");

    ClassSelector v32 = Css.dot("md-mt-32");

    ClassSelector v40 = Css.dot("md-mt-40");

    ClassSelector v48 = Css.dot("md-mt-48");

    ClassSelector v56 = Css.dot("md-mt-56");

    ClassSelector v64 = Css.dot("md-mt-64");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-mt-auto");

    ClassSelector px = Css.dot("lg-mt-px");

    ClassSelector v0 = Css.dot("lg-mt-0");

    ClassSelector v1 = Css.dot("lg-mt-1");

    ClassSelector v2 = Css.dot("lg-mt-2");

    ClassSelector v3 = Css.dot("lg-mt-3");

    ClassSelector v4 = Css.dot("lg-mt-4");

    ClassSelector v5 = Css.dot("lg-mt-5");

    ClassSelector v6 = Css.dot("lg-mt-6");

    ClassSelector v8 = Css.dot("lg-mt-8");

    ClassSelector v10 = Css.dot("lg-mt-10");

    ClassSelector v12 = Css.dot("lg-mt-12");

    ClassSelector v16 = Css.dot("lg-mt-16");

    ClassSelector v20 = Css.dot("lg-mt-20");

    ClassSelector v24 = Css.dot("lg-mt-24");

    ClassSelector v32 = Css.dot("lg-mt-32");

    ClassSelector v40 = Css.dot("lg-mt-40");

    ClassSelector v48 = Css.dot("lg-mt-48");

    ClassSelector v56 = Css.dot("lg-mt-56");

    ClassSelector v64 = Css.dot("lg-mt-64");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-mt-auto");

    ClassSelector px = Css.dot("xl-mt-px");

    ClassSelector v0 = Css.dot("xl-mt-0");

    ClassSelector v1 = Css.dot("xl-mt-1");

    ClassSelector v2 = Css.dot("xl-mt-2");

    ClassSelector v3 = Css.dot("xl-mt-3");

    ClassSelector v4 = Css.dot("xl-mt-4");

    ClassSelector v5 = Css.dot("xl-mt-5");

    ClassSelector v6 = Css.dot("xl-mt-6");

    ClassSelector v8 = Css.dot("xl-mt-8");

    ClassSelector v10 = Css.dot("xl-mt-10");

    ClassSelector v12 = Css.dot("xl-mt-12");

    ClassSelector v16 = Css.dot("xl-mt-16");

    ClassSelector v20 = Css.dot("xl-mt-20");

    ClassSelector v24 = Css.dot("xl-mt-24");

    ClassSelector v32 = Css.dot("xl-mt-32");

    ClassSelector v40 = Css.dot("xl-mt-40");

    ClassSelector v48 = Css.dot("xl-mt-48");

    ClassSelector v56 = Css.dot("xl-mt-56");

    ClassSelector v64 = Css.dot("xl-mt-64");

  }

}