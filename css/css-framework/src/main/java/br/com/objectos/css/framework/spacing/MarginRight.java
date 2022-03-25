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
public final class MarginRight extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("mr-auto");

  public static final ClassSelector px = Css.dot("mr-px");

  public static final ClassSelector v0 = Css.dot("mr-0");

  public static final ClassSelector v1 = Css.dot("mr-1");

  public static final ClassSelector v2 = Css.dot("mr-2");

  public static final ClassSelector v3 = Css.dot("mr-3");

  public static final ClassSelector v4 = Css.dot("mr-4");

  public static final ClassSelector v5 = Css.dot("mr-5");

  public static final ClassSelector v6 = Css.dot("mr-6");

  public static final ClassSelector v8 = Css.dot("mr-8");

  public static final ClassSelector v10 = Css.dot("mr-10");

  public static final ClassSelector v12 = Css.dot("mr-12");

  public static final ClassSelector v16 = Css.dot("mr-16");

  public static final ClassSelector v20 = Css.dot("mr-20");

  public static final ClassSelector v24 = Css.dot("mr-24");

  public static final ClassSelector v32 = Css.dot("mr-32");

  public static final ClassSelector v40 = Css.dot("mr-40");

  public static final ClassSelector v48 = Css.dot("mr-48");

  public static final ClassSelector v56 = Css.dot("mr-56");

  public static final ClassSelector v64 = Css.dot("mr-64");

  @Override
  protected final void definition() {
    style(
        auto,
        marginRight(Keywords.auto)
    );
    style(
        px,
        marginRight(px(1))
    );
    style(
        v0,
        marginRight(zero())
    );
    style(
        v1,
        marginRight(rem(0.25))
    );
    style(
        v2,
        marginRight(rem(0.5))
    );
    style(
        v3,
        marginRight(rem(0.75))
    );
    style(
        v4,
        marginRight(rem(1))
    );
    style(
        v5,
        marginRight(rem(1.25))
    );
    style(
        v6,
        marginRight(rem(1.5))
    );
    style(
        v8,
        marginRight(rem(2))
    );
    style(
        v10,
        marginRight(rem(2.5))
    );
    style(
        v12,
        marginRight(rem(3))
    );
    style(
        v16,
        marginRight(rem(4))
    );
    style(
        v20,
        marginRight(rem(5))
    );
    style(
        v24,
        marginRight(rem(6))
    );
    style(
        v32,
        marginRight(rem(8))
    );
    style(
        v40,
        marginRight(rem(10))
    );
    style(
        v48,
        marginRight(rem(12))
    );
    style(
        v56,
        marginRight(rem(14))
    );
    style(
        v64,
        marginRight(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            marginRight(Keywords.auto)
        ),

        style(
            sm.px,
            marginRight(px(1))
        ),

        style(
            sm.v0,
            marginRight(zero())
        ),

        style(
            sm.v1,
            marginRight(rem(0.25))
        ),

        style(
            sm.v2,
            marginRight(rem(0.5))
        ),

        style(
            sm.v3,
            marginRight(rem(0.75))
        ),

        style(
            sm.v4,
            marginRight(rem(1))
        ),

        style(
            sm.v5,
            marginRight(rem(1.25))
        ),

        style(
            sm.v6,
            marginRight(rem(1.5))
        ),

        style(
            sm.v8,
            marginRight(rem(2))
        ),

        style(
            sm.v10,
            marginRight(rem(2.5))
        ),

        style(
            sm.v12,
            marginRight(rem(3))
        ),

        style(
            sm.v16,
            marginRight(rem(4))
        ),

        style(
            sm.v20,
            marginRight(rem(5))
        ),

        style(
            sm.v24,
            marginRight(rem(6))
        ),

        style(
            sm.v32,
            marginRight(rem(8))
        ),

        style(
            sm.v40,
            marginRight(rem(10))
        ),

        style(
            sm.v48,
            marginRight(rem(12))
        ),

        style(
            sm.v56,
            marginRight(rem(14))
        ),

        style(
            sm.v64,
            marginRight(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            marginRight(Keywords.auto)
        ),

        style(
            md.px,
            marginRight(px(1))
        ),

        style(
            md.v0,
            marginRight(zero())
        ),

        style(
            md.v1,
            marginRight(rem(0.25))
        ),

        style(
            md.v2,
            marginRight(rem(0.5))
        ),

        style(
            md.v3,
            marginRight(rem(0.75))
        ),

        style(
            md.v4,
            marginRight(rem(1))
        ),

        style(
            md.v5,
            marginRight(rem(1.25))
        ),

        style(
            md.v6,
            marginRight(rem(1.5))
        ),

        style(
            md.v8,
            marginRight(rem(2))
        ),

        style(
            md.v10,
            marginRight(rem(2.5))
        ),

        style(
            md.v12,
            marginRight(rem(3))
        ),

        style(
            md.v16,
            marginRight(rem(4))
        ),

        style(
            md.v20,
            marginRight(rem(5))
        ),

        style(
            md.v24,
            marginRight(rem(6))
        ),

        style(
            md.v32,
            marginRight(rem(8))
        ),

        style(
            md.v40,
            marginRight(rem(10))
        ),

        style(
            md.v48,
            marginRight(rem(12))
        ),

        style(
            md.v56,
            marginRight(rem(14))
        ),

        style(
            md.v64,
            marginRight(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            marginRight(Keywords.auto)
        ),

        style(
            lg.px,
            marginRight(px(1))
        ),

        style(
            lg.v0,
            marginRight(zero())
        ),

        style(
            lg.v1,
            marginRight(rem(0.25))
        ),

        style(
            lg.v2,
            marginRight(rem(0.5))
        ),

        style(
            lg.v3,
            marginRight(rem(0.75))
        ),

        style(
            lg.v4,
            marginRight(rem(1))
        ),

        style(
            lg.v5,
            marginRight(rem(1.25))
        ),

        style(
            lg.v6,
            marginRight(rem(1.5))
        ),

        style(
            lg.v8,
            marginRight(rem(2))
        ),

        style(
            lg.v10,
            marginRight(rem(2.5))
        ),

        style(
            lg.v12,
            marginRight(rem(3))
        ),

        style(
            lg.v16,
            marginRight(rem(4))
        ),

        style(
            lg.v20,
            marginRight(rem(5))
        ),

        style(
            lg.v24,
            marginRight(rem(6))
        ),

        style(
            lg.v32,
            marginRight(rem(8))
        ),

        style(
            lg.v40,
            marginRight(rem(10))
        ),

        style(
            lg.v48,
            marginRight(rem(12))
        ),

        style(
            lg.v56,
            marginRight(rem(14))
        ),

        style(
            lg.v64,
            marginRight(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            marginRight(Keywords.auto)
        ),

        style(
            xl.px,
            marginRight(px(1))
        ),

        style(
            xl.v0,
            marginRight(zero())
        ),

        style(
            xl.v1,
            marginRight(rem(0.25))
        ),

        style(
            xl.v2,
            marginRight(rem(0.5))
        ),

        style(
            xl.v3,
            marginRight(rem(0.75))
        ),

        style(
            xl.v4,
            marginRight(rem(1))
        ),

        style(
            xl.v5,
            marginRight(rem(1.25))
        ),

        style(
            xl.v6,
            marginRight(rem(1.5))
        ),

        style(
            xl.v8,
            marginRight(rem(2))
        ),

        style(
            xl.v10,
            marginRight(rem(2.5))
        ),

        style(
            xl.v12,
            marginRight(rem(3))
        ),

        style(
            xl.v16,
            marginRight(rem(4))
        ),

        style(
            xl.v20,
            marginRight(rem(5))
        ),

        style(
            xl.v24,
            marginRight(rem(6))
        ),

        style(
            xl.v32,
            marginRight(rem(8))
        ),

        style(
            xl.v40,
            marginRight(rem(10))
        ),

        style(
            xl.v48,
            marginRight(rem(12))
        ),

        style(
            xl.v56,
            marginRight(rem(14))
        ),

        style(
            xl.v64,
            marginRight(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-mr-auto");

    ClassSelector px = Css.dot("sm-mr-px");

    ClassSelector v0 = Css.dot("sm-mr-0");

    ClassSelector v1 = Css.dot("sm-mr-1");

    ClassSelector v2 = Css.dot("sm-mr-2");

    ClassSelector v3 = Css.dot("sm-mr-3");

    ClassSelector v4 = Css.dot("sm-mr-4");

    ClassSelector v5 = Css.dot("sm-mr-5");

    ClassSelector v6 = Css.dot("sm-mr-6");

    ClassSelector v8 = Css.dot("sm-mr-8");

    ClassSelector v10 = Css.dot("sm-mr-10");

    ClassSelector v12 = Css.dot("sm-mr-12");

    ClassSelector v16 = Css.dot("sm-mr-16");

    ClassSelector v20 = Css.dot("sm-mr-20");

    ClassSelector v24 = Css.dot("sm-mr-24");

    ClassSelector v32 = Css.dot("sm-mr-32");

    ClassSelector v40 = Css.dot("sm-mr-40");

    ClassSelector v48 = Css.dot("sm-mr-48");

    ClassSelector v56 = Css.dot("sm-mr-56");

    ClassSelector v64 = Css.dot("sm-mr-64");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-mr-auto");

    ClassSelector px = Css.dot("md-mr-px");

    ClassSelector v0 = Css.dot("md-mr-0");

    ClassSelector v1 = Css.dot("md-mr-1");

    ClassSelector v2 = Css.dot("md-mr-2");

    ClassSelector v3 = Css.dot("md-mr-3");

    ClassSelector v4 = Css.dot("md-mr-4");

    ClassSelector v5 = Css.dot("md-mr-5");

    ClassSelector v6 = Css.dot("md-mr-6");

    ClassSelector v8 = Css.dot("md-mr-8");

    ClassSelector v10 = Css.dot("md-mr-10");

    ClassSelector v12 = Css.dot("md-mr-12");

    ClassSelector v16 = Css.dot("md-mr-16");

    ClassSelector v20 = Css.dot("md-mr-20");

    ClassSelector v24 = Css.dot("md-mr-24");

    ClassSelector v32 = Css.dot("md-mr-32");

    ClassSelector v40 = Css.dot("md-mr-40");

    ClassSelector v48 = Css.dot("md-mr-48");

    ClassSelector v56 = Css.dot("md-mr-56");

    ClassSelector v64 = Css.dot("md-mr-64");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-mr-auto");

    ClassSelector px = Css.dot("lg-mr-px");

    ClassSelector v0 = Css.dot("lg-mr-0");

    ClassSelector v1 = Css.dot("lg-mr-1");

    ClassSelector v2 = Css.dot("lg-mr-2");

    ClassSelector v3 = Css.dot("lg-mr-3");

    ClassSelector v4 = Css.dot("lg-mr-4");

    ClassSelector v5 = Css.dot("lg-mr-5");

    ClassSelector v6 = Css.dot("lg-mr-6");

    ClassSelector v8 = Css.dot("lg-mr-8");

    ClassSelector v10 = Css.dot("lg-mr-10");

    ClassSelector v12 = Css.dot("lg-mr-12");

    ClassSelector v16 = Css.dot("lg-mr-16");

    ClassSelector v20 = Css.dot("lg-mr-20");

    ClassSelector v24 = Css.dot("lg-mr-24");

    ClassSelector v32 = Css.dot("lg-mr-32");

    ClassSelector v40 = Css.dot("lg-mr-40");

    ClassSelector v48 = Css.dot("lg-mr-48");

    ClassSelector v56 = Css.dot("lg-mr-56");

    ClassSelector v64 = Css.dot("lg-mr-64");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-mr-auto");

    ClassSelector px = Css.dot("xl-mr-px");

    ClassSelector v0 = Css.dot("xl-mr-0");

    ClassSelector v1 = Css.dot("xl-mr-1");

    ClassSelector v2 = Css.dot("xl-mr-2");

    ClassSelector v3 = Css.dot("xl-mr-3");

    ClassSelector v4 = Css.dot("xl-mr-4");

    ClassSelector v5 = Css.dot("xl-mr-5");

    ClassSelector v6 = Css.dot("xl-mr-6");

    ClassSelector v8 = Css.dot("xl-mr-8");

    ClassSelector v10 = Css.dot("xl-mr-10");

    ClassSelector v12 = Css.dot("xl-mr-12");

    ClassSelector v16 = Css.dot("xl-mr-16");

    ClassSelector v20 = Css.dot("xl-mr-20");

    ClassSelector v24 = Css.dot("xl-mr-24");

    ClassSelector v32 = Css.dot("xl-mr-32");

    ClassSelector v40 = Css.dot("xl-mr-40");

    ClassSelector v48 = Css.dot("xl-mr-48");

    ClassSelector v56 = Css.dot("xl-mr-56");

    ClassSelector v64 = Css.dot("xl-mr-64");

  }

}