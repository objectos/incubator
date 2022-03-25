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
public final class MarginLeft extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("ml-auto");

  public static final ClassSelector px = Css.dot("ml-px");

  public static final ClassSelector v0 = Css.dot("ml-0");

  public static final ClassSelector v1 = Css.dot("ml-1");

  public static final ClassSelector v2 = Css.dot("ml-2");

  public static final ClassSelector v3 = Css.dot("ml-3");

  public static final ClassSelector v4 = Css.dot("ml-4");

  public static final ClassSelector v5 = Css.dot("ml-5");

  public static final ClassSelector v6 = Css.dot("ml-6");

  public static final ClassSelector v8 = Css.dot("ml-8");

  public static final ClassSelector v10 = Css.dot("ml-10");

  public static final ClassSelector v12 = Css.dot("ml-12");

  public static final ClassSelector v16 = Css.dot("ml-16");

  public static final ClassSelector v20 = Css.dot("ml-20");

  public static final ClassSelector v24 = Css.dot("ml-24");

  public static final ClassSelector v32 = Css.dot("ml-32");

  public static final ClassSelector v40 = Css.dot("ml-40");

  public static final ClassSelector v48 = Css.dot("ml-48");

  public static final ClassSelector v56 = Css.dot("ml-56");

  public static final ClassSelector v64 = Css.dot("ml-64");

  @Override
  protected final void definition() {
    style(
        auto,
        marginLeft(Keywords.auto)
    );
    style(
        px,
        marginLeft(px(1))
    );
    style(
        v0,
        marginLeft(zero())
    );
    style(
        v1,
        marginLeft(rem(0.25))
    );
    style(
        v2,
        marginLeft(rem(0.5))
    );
    style(
        v3,
        marginLeft(rem(0.75))
    );
    style(
        v4,
        marginLeft(rem(1))
    );
    style(
        v5,
        marginLeft(rem(1.25))
    );
    style(
        v6,
        marginLeft(rem(1.5))
    );
    style(
        v8,
        marginLeft(rem(2))
    );
    style(
        v10,
        marginLeft(rem(2.5))
    );
    style(
        v12,
        marginLeft(rem(3))
    );
    style(
        v16,
        marginLeft(rem(4))
    );
    style(
        v20,
        marginLeft(rem(5))
    );
    style(
        v24,
        marginLeft(rem(6))
    );
    style(
        v32,
        marginLeft(rem(8))
    );
    style(
        v40,
        marginLeft(rem(10))
    );
    style(
        v48,
        marginLeft(rem(12))
    );
    style(
        v56,
        marginLeft(rem(14))
    );
    style(
        v64,
        marginLeft(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            marginLeft(Keywords.auto)
        ),

        style(
            sm.px,
            marginLeft(px(1))
        ),

        style(
            sm.v0,
            marginLeft(zero())
        ),

        style(
            sm.v1,
            marginLeft(rem(0.25))
        ),

        style(
            sm.v2,
            marginLeft(rem(0.5))
        ),

        style(
            sm.v3,
            marginLeft(rem(0.75))
        ),

        style(
            sm.v4,
            marginLeft(rem(1))
        ),

        style(
            sm.v5,
            marginLeft(rem(1.25))
        ),

        style(
            sm.v6,
            marginLeft(rem(1.5))
        ),

        style(
            sm.v8,
            marginLeft(rem(2))
        ),

        style(
            sm.v10,
            marginLeft(rem(2.5))
        ),

        style(
            sm.v12,
            marginLeft(rem(3))
        ),

        style(
            sm.v16,
            marginLeft(rem(4))
        ),

        style(
            sm.v20,
            marginLeft(rem(5))
        ),

        style(
            sm.v24,
            marginLeft(rem(6))
        ),

        style(
            sm.v32,
            marginLeft(rem(8))
        ),

        style(
            sm.v40,
            marginLeft(rem(10))
        ),

        style(
            sm.v48,
            marginLeft(rem(12))
        ),

        style(
            sm.v56,
            marginLeft(rem(14))
        ),

        style(
            sm.v64,
            marginLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            marginLeft(Keywords.auto)
        ),

        style(
            md.px,
            marginLeft(px(1))
        ),

        style(
            md.v0,
            marginLeft(zero())
        ),

        style(
            md.v1,
            marginLeft(rem(0.25))
        ),

        style(
            md.v2,
            marginLeft(rem(0.5))
        ),

        style(
            md.v3,
            marginLeft(rem(0.75))
        ),

        style(
            md.v4,
            marginLeft(rem(1))
        ),

        style(
            md.v5,
            marginLeft(rem(1.25))
        ),

        style(
            md.v6,
            marginLeft(rem(1.5))
        ),

        style(
            md.v8,
            marginLeft(rem(2))
        ),

        style(
            md.v10,
            marginLeft(rem(2.5))
        ),

        style(
            md.v12,
            marginLeft(rem(3))
        ),

        style(
            md.v16,
            marginLeft(rem(4))
        ),

        style(
            md.v20,
            marginLeft(rem(5))
        ),

        style(
            md.v24,
            marginLeft(rem(6))
        ),

        style(
            md.v32,
            marginLeft(rem(8))
        ),

        style(
            md.v40,
            marginLeft(rem(10))
        ),

        style(
            md.v48,
            marginLeft(rem(12))
        ),

        style(
            md.v56,
            marginLeft(rem(14))
        ),

        style(
            md.v64,
            marginLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            marginLeft(Keywords.auto)
        ),

        style(
            lg.px,
            marginLeft(px(1))
        ),

        style(
            lg.v0,
            marginLeft(zero())
        ),

        style(
            lg.v1,
            marginLeft(rem(0.25))
        ),

        style(
            lg.v2,
            marginLeft(rem(0.5))
        ),

        style(
            lg.v3,
            marginLeft(rem(0.75))
        ),

        style(
            lg.v4,
            marginLeft(rem(1))
        ),

        style(
            lg.v5,
            marginLeft(rem(1.25))
        ),

        style(
            lg.v6,
            marginLeft(rem(1.5))
        ),

        style(
            lg.v8,
            marginLeft(rem(2))
        ),

        style(
            lg.v10,
            marginLeft(rem(2.5))
        ),

        style(
            lg.v12,
            marginLeft(rem(3))
        ),

        style(
            lg.v16,
            marginLeft(rem(4))
        ),

        style(
            lg.v20,
            marginLeft(rem(5))
        ),

        style(
            lg.v24,
            marginLeft(rem(6))
        ),

        style(
            lg.v32,
            marginLeft(rem(8))
        ),

        style(
            lg.v40,
            marginLeft(rem(10))
        ),

        style(
            lg.v48,
            marginLeft(rem(12))
        ),

        style(
            lg.v56,
            marginLeft(rem(14))
        ),

        style(
            lg.v64,
            marginLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            marginLeft(Keywords.auto)
        ),

        style(
            xl.px,
            marginLeft(px(1))
        ),

        style(
            xl.v0,
            marginLeft(zero())
        ),

        style(
            xl.v1,
            marginLeft(rem(0.25))
        ),

        style(
            xl.v2,
            marginLeft(rem(0.5))
        ),

        style(
            xl.v3,
            marginLeft(rem(0.75))
        ),

        style(
            xl.v4,
            marginLeft(rem(1))
        ),

        style(
            xl.v5,
            marginLeft(rem(1.25))
        ),

        style(
            xl.v6,
            marginLeft(rem(1.5))
        ),

        style(
            xl.v8,
            marginLeft(rem(2))
        ),

        style(
            xl.v10,
            marginLeft(rem(2.5))
        ),

        style(
            xl.v12,
            marginLeft(rem(3))
        ),

        style(
            xl.v16,
            marginLeft(rem(4))
        ),

        style(
            xl.v20,
            marginLeft(rem(5))
        ),

        style(
            xl.v24,
            marginLeft(rem(6))
        ),

        style(
            xl.v32,
            marginLeft(rem(8))
        ),

        style(
            xl.v40,
            marginLeft(rem(10))
        ),

        style(
            xl.v48,
            marginLeft(rem(12))
        ),

        style(
            xl.v56,
            marginLeft(rem(14))
        ),

        style(
            xl.v64,
            marginLeft(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-ml-auto");

    ClassSelector px = Css.dot("sm-ml-px");

    ClassSelector v0 = Css.dot("sm-ml-0");

    ClassSelector v1 = Css.dot("sm-ml-1");

    ClassSelector v2 = Css.dot("sm-ml-2");

    ClassSelector v3 = Css.dot("sm-ml-3");

    ClassSelector v4 = Css.dot("sm-ml-4");

    ClassSelector v5 = Css.dot("sm-ml-5");

    ClassSelector v6 = Css.dot("sm-ml-6");

    ClassSelector v8 = Css.dot("sm-ml-8");

    ClassSelector v10 = Css.dot("sm-ml-10");

    ClassSelector v12 = Css.dot("sm-ml-12");

    ClassSelector v16 = Css.dot("sm-ml-16");

    ClassSelector v20 = Css.dot("sm-ml-20");

    ClassSelector v24 = Css.dot("sm-ml-24");

    ClassSelector v32 = Css.dot("sm-ml-32");

    ClassSelector v40 = Css.dot("sm-ml-40");

    ClassSelector v48 = Css.dot("sm-ml-48");

    ClassSelector v56 = Css.dot("sm-ml-56");

    ClassSelector v64 = Css.dot("sm-ml-64");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-ml-auto");

    ClassSelector px = Css.dot("md-ml-px");

    ClassSelector v0 = Css.dot("md-ml-0");

    ClassSelector v1 = Css.dot("md-ml-1");

    ClassSelector v2 = Css.dot("md-ml-2");

    ClassSelector v3 = Css.dot("md-ml-3");

    ClassSelector v4 = Css.dot("md-ml-4");

    ClassSelector v5 = Css.dot("md-ml-5");

    ClassSelector v6 = Css.dot("md-ml-6");

    ClassSelector v8 = Css.dot("md-ml-8");

    ClassSelector v10 = Css.dot("md-ml-10");

    ClassSelector v12 = Css.dot("md-ml-12");

    ClassSelector v16 = Css.dot("md-ml-16");

    ClassSelector v20 = Css.dot("md-ml-20");

    ClassSelector v24 = Css.dot("md-ml-24");

    ClassSelector v32 = Css.dot("md-ml-32");

    ClassSelector v40 = Css.dot("md-ml-40");

    ClassSelector v48 = Css.dot("md-ml-48");

    ClassSelector v56 = Css.dot("md-ml-56");

    ClassSelector v64 = Css.dot("md-ml-64");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-ml-auto");

    ClassSelector px = Css.dot("lg-ml-px");

    ClassSelector v0 = Css.dot("lg-ml-0");

    ClassSelector v1 = Css.dot("lg-ml-1");

    ClassSelector v2 = Css.dot("lg-ml-2");

    ClassSelector v3 = Css.dot("lg-ml-3");

    ClassSelector v4 = Css.dot("lg-ml-4");

    ClassSelector v5 = Css.dot("lg-ml-5");

    ClassSelector v6 = Css.dot("lg-ml-6");

    ClassSelector v8 = Css.dot("lg-ml-8");

    ClassSelector v10 = Css.dot("lg-ml-10");

    ClassSelector v12 = Css.dot("lg-ml-12");

    ClassSelector v16 = Css.dot("lg-ml-16");

    ClassSelector v20 = Css.dot("lg-ml-20");

    ClassSelector v24 = Css.dot("lg-ml-24");

    ClassSelector v32 = Css.dot("lg-ml-32");

    ClassSelector v40 = Css.dot("lg-ml-40");

    ClassSelector v48 = Css.dot("lg-ml-48");

    ClassSelector v56 = Css.dot("lg-ml-56");

    ClassSelector v64 = Css.dot("lg-ml-64");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-ml-auto");

    ClassSelector px = Css.dot("xl-ml-px");

    ClassSelector v0 = Css.dot("xl-ml-0");

    ClassSelector v1 = Css.dot("xl-ml-1");

    ClassSelector v2 = Css.dot("xl-ml-2");

    ClassSelector v3 = Css.dot("xl-ml-3");

    ClassSelector v4 = Css.dot("xl-ml-4");

    ClassSelector v5 = Css.dot("xl-ml-5");

    ClassSelector v6 = Css.dot("xl-ml-6");

    ClassSelector v8 = Css.dot("xl-ml-8");

    ClassSelector v10 = Css.dot("xl-ml-10");

    ClassSelector v12 = Css.dot("xl-ml-12");

    ClassSelector v16 = Css.dot("xl-ml-16");

    ClassSelector v20 = Css.dot("xl-ml-20");

    ClassSelector v24 = Css.dot("xl-ml-24");

    ClassSelector v32 = Css.dot("xl-ml-32");

    ClassSelector v40 = Css.dot("xl-ml-40");

    ClassSelector v48 = Css.dot("xl-ml-48");

    ClassSelector v56 = Css.dot("xl-ml-56");

    ClassSelector v64 = Css.dot("xl-ml-64");

  }

}