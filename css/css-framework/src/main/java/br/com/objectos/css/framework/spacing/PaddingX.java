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
public final class PaddingX extends AbstractStyleSheet {

  public static final ClassSelector px = Css.dot("px-px");

  public static final ClassSelector v0 = Css.dot("px-0");

  public static final ClassSelector v1 = Css.dot("px-1");

  public static final ClassSelector v2 = Css.dot("px-2");

  public static final ClassSelector v3 = Css.dot("px-3");

  public static final ClassSelector v4 = Css.dot("px-4");

  public static final ClassSelector v5 = Css.dot("px-5");

  public static final ClassSelector v6 = Css.dot("px-6");

  public static final ClassSelector v8 = Css.dot("px-8");

  public static final ClassSelector v10 = Css.dot("px-10");

  public static final ClassSelector v12 = Css.dot("px-12");

  public static final ClassSelector v16 = Css.dot("px-16");

  public static final ClassSelector v20 = Css.dot("px-20");

  public static final ClassSelector v24 = Css.dot("px-24");

  public static final ClassSelector v32 = Css.dot("px-32");

  public static final ClassSelector v40 = Css.dot("px-40");

  public static final ClassSelector v48 = Css.dot("px-48");

  public static final ClassSelector v56 = Css.dot("px-56");

  public static final ClassSelector v64 = Css.dot("px-64");

  @Override
  protected final void definition() {
    style(
        px,
        paddingRight(px(1)),
        paddingLeft(px(1))
    );
    style(
        v0,
        paddingRight(zero()),
        paddingLeft(zero())
    );
    style(
        v1,
        paddingRight(rem(0.25)),
        paddingLeft(rem(0.25))
    );
    style(
        v2,
        paddingRight(rem(0.5)),
        paddingLeft(rem(0.5))
    );
    style(
        v3,
        paddingRight(rem(0.75)),
        paddingLeft(rem(0.75))
    );
    style(
        v4,
        paddingRight(rem(1)),
        paddingLeft(rem(1))
    );
    style(
        v5,
        paddingRight(rem(1.25)),
        paddingLeft(rem(1.25))
    );
    style(
        v6,
        paddingRight(rem(1.5)),
        paddingLeft(rem(1.5))
    );
    style(
        v8,
        paddingRight(rem(2)),
        paddingLeft(rem(2))
    );
    style(
        v10,
        paddingRight(rem(2.5)),
        paddingLeft(rem(2.5))
    );
    style(
        v12,
        paddingRight(rem(3)),
        paddingLeft(rem(3))
    );
    style(
        v16,
        paddingRight(rem(4)),
        paddingLeft(rem(4))
    );
    style(
        v20,
        paddingRight(rem(5)),
        paddingLeft(rem(5))
    );
    style(
        v24,
        paddingRight(rem(6)),
        paddingLeft(rem(6))
    );
    style(
        v32,
        paddingRight(rem(8)),
        paddingLeft(rem(8))
    );
    style(
        v40,
        paddingRight(rem(10)),
        paddingLeft(rem(10))
    );
    style(
        v48,
        paddingRight(rem(12)),
        paddingLeft(rem(12))
    );
    style(
        v56,
        paddingRight(rem(14)),
        paddingLeft(rem(14))
    );
    style(
        v64,
        paddingRight(rem(16)),
        paddingLeft(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.px,
            paddingRight(px(1)),
            paddingLeft(px(1))
        ),

        style(
            sm.v0,
            paddingRight(zero()),
            paddingLeft(zero())
        ),

        style(
            sm.v1,
            paddingRight(rem(0.25)),
            paddingLeft(rem(0.25))
        ),

        style(
            sm.v2,
            paddingRight(rem(0.5)),
            paddingLeft(rem(0.5))
        ),

        style(
            sm.v3,
            paddingRight(rem(0.75)),
            paddingLeft(rem(0.75))
        ),

        style(
            sm.v4,
            paddingRight(rem(1)),
            paddingLeft(rem(1))
        ),

        style(
            sm.v5,
            paddingRight(rem(1.25)),
            paddingLeft(rem(1.25))
        ),

        style(
            sm.v6,
            paddingRight(rem(1.5)),
            paddingLeft(rem(1.5))
        ),

        style(
            sm.v8,
            paddingRight(rem(2)),
            paddingLeft(rem(2))
        ),

        style(
            sm.v10,
            paddingRight(rem(2.5)),
            paddingLeft(rem(2.5))
        ),

        style(
            sm.v12,
            paddingRight(rem(3)),
            paddingLeft(rem(3))
        ),

        style(
            sm.v16,
            paddingRight(rem(4)),
            paddingLeft(rem(4))
        ),

        style(
            sm.v20,
            paddingRight(rem(5)),
            paddingLeft(rem(5))
        ),

        style(
            sm.v24,
            paddingRight(rem(6)),
            paddingLeft(rem(6))
        ),

        style(
            sm.v32,
            paddingRight(rem(8)),
            paddingLeft(rem(8))
        ),

        style(
            sm.v40,
            paddingRight(rem(10)),
            paddingLeft(rem(10))
        ),

        style(
            sm.v48,
            paddingRight(rem(12)),
            paddingLeft(rem(12))
        ),

        style(
            sm.v56,
            paddingRight(rem(14)),
            paddingLeft(rem(14))
        ),

        style(
            sm.v64,
            paddingRight(rem(16)),
            paddingLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.px,
            paddingRight(px(1)),
            paddingLeft(px(1))
        ),

        style(
            md.v0,
            paddingRight(zero()),
            paddingLeft(zero())
        ),

        style(
            md.v1,
            paddingRight(rem(0.25)),
            paddingLeft(rem(0.25))
        ),

        style(
            md.v2,
            paddingRight(rem(0.5)),
            paddingLeft(rem(0.5))
        ),

        style(
            md.v3,
            paddingRight(rem(0.75)),
            paddingLeft(rem(0.75))
        ),

        style(
            md.v4,
            paddingRight(rem(1)),
            paddingLeft(rem(1))
        ),

        style(
            md.v5,
            paddingRight(rem(1.25)),
            paddingLeft(rem(1.25))
        ),

        style(
            md.v6,
            paddingRight(rem(1.5)),
            paddingLeft(rem(1.5))
        ),

        style(
            md.v8,
            paddingRight(rem(2)),
            paddingLeft(rem(2))
        ),

        style(
            md.v10,
            paddingRight(rem(2.5)),
            paddingLeft(rem(2.5))
        ),

        style(
            md.v12,
            paddingRight(rem(3)),
            paddingLeft(rem(3))
        ),

        style(
            md.v16,
            paddingRight(rem(4)),
            paddingLeft(rem(4))
        ),

        style(
            md.v20,
            paddingRight(rem(5)),
            paddingLeft(rem(5))
        ),

        style(
            md.v24,
            paddingRight(rem(6)),
            paddingLeft(rem(6))
        ),

        style(
            md.v32,
            paddingRight(rem(8)),
            paddingLeft(rem(8))
        ),

        style(
            md.v40,
            paddingRight(rem(10)),
            paddingLeft(rem(10))
        ),

        style(
            md.v48,
            paddingRight(rem(12)),
            paddingLeft(rem(12))
        ),

        style(
            md.v56,
            paddingRight(rem(14)),
            paddingLeft(rem(14))
        ),

        style(
            md.v64,
            paddingRight(rem(16)),
            paddingLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.px,
            paddingRight(px(1)),
            paddingLeft(px(1))
        ),

        style(
            lg.v0,
            paddingRight(zero()),
            paddingLeft(zero())
        ),

        style(
            lg.v1,
            paddingRight(rem(0.25)),
            paddingLeft(rem(0.25))
        ),

        style(
            lg.v2,
            paddingRight(rem(0.5)),
            paddingLeft(rem(0.5))
        ),

        style(
            lg.v3,
            paddingRight(rem(0.75)),
            paddingLeft(rem(0.75))
        ),

        style(
            lg.v4,
            paddingRight(rem(1)),
            paddingLeft(rem(1))
        ),

        style(
            lg.v5,
            paddingRight(rem(1.25)),
            paddingLeft(rem(1.25))
        ),

        style(
            lg.v6,
            paddingRight(rem(1.5)),
            paddingLeft(rem(1.5))
        ),

        style(
            lg.v8,
            paddingRight(rem(2)),
            paddingLeft(rem(2))
        ),

        style(
            lg.v10,
            paddingRight(rem(2.5)),
            paddingLeft(rem(2.5))
        ),

        style(
            lg.v12,
            paddingRight(rem(3)),
            paddingLeft(rem(3))
        ),

        style(
            lg.v16,
            paddingRight(rem(4)),
            paddingLeft(rem(4))
        ),

        style(
            lg.v20,
            paddingRight(rem(5)),
            paddingLeft(rem(5))
        ),

        style(
            lg.v24,
            paddingRight(rem(6)),
            paddingLeft(rem(6))
        ),

        style(
            lg.v32,
            paddingRight(rem(8)),
            paddingLeft(rem(8))
        ),

        style(
            lg.v40,
            paddingRight(rem(10)),
            paddingLeft(rem(10))
        ),

        style(
            lg.v48,
            paddingRight(rem(12)),
            paddingLeft(rem(12))
        ),

        style(
            lg.v56,
            paddingRight(rem(14)),
            paddingLeft(rem(14))
        ),

        style(
            lg.v64,
            paddingRight(rem(16)),
            paddingLeft(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.px,
            paddingRight(px(1)),
            paddingLeft(px(1))
        ),

        style(
            xl.v0,
            paddingRight(zero()),
            paddingLeft(zero())
        ),

        style(
            xl.v1,
            paddingRight(rem(0.25)),
            paddingLeft(rem(0.25))
        ),

        style(
            xl.v2,
            paddingRight(rem(0.5)),
            paddingLeft(rem(0.5))
        ),

        style(
            xl.v3,
            paddingRight(rem(0.75)),
            paddingLeft(rem(0.75))
        ),

        style(
            xl.v4,
            paddingRight(rem(1)),
            paddingLeft(rem(1))
        ),

        style(
            xl.v5,
            paddingRight(rem(1.25)),
            paddingLeft(rem(1.25))
        ),

        style(
            xl.v6,
            paddingRight(rem(1.5)),
            paddingLeft(rem(1.5))
        ),

        style(
            xl.v8,
            paddingRight(rem(2)),
            paddingLeft(rem(2))
        ),

        style(
            xl.v10,
            paddingRight(rem(2.5)),
            paddingLeft(rem(2.5))
        ),

        style(
            xl.v12,
            paddingRight(rem(3)),
            paddingLeft(rem(3))
        ),

        style(
            xl.v16,
            paddingRight(rem(4)),
            paddingLeft(rem(4))
        ),

        style(
            xl.v20,
            paddingRight(rem(5)),
            paddingLeft(rem(5))
        ),

        style(
            xl.v24,
            paddingRight(rem(6)),
            paddingLeft(rem(6))
        ),

        style(
            xl.v32,
            paddingRight(rem(8)),
            paddingLeft(rem(8))
        ),

        style(
            xl.v40,
            paddingRight(rem(10)),
            paddingLeft(rem(10))
        ),

        style(
            xl.v48,
            paddingRight(rem(12)),
            paddingLeft(rem(12))
        ),

        style(
            xl.v56,
            paddingRight(rem(14)),
            paddingLeft(rem(14))
        ),

        style(
            xl.v64,
            paddingRight(rem(16)),
            paddingLeft(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector px = Css.dot("sm-px-px");

    ClassSelector v0 = Css.dot("sm-px-0");

    ClassSelector v1 = Css.dot("sm-px-1");

    ClassSelector v2 = Css.dot("sm-px-2");

    ClassSelector v3 = Css.dot("sm-px-3");

    ClassSelector v4 = Css.dot("sm-px-4");

    ClassSelector v5 = Css.dot("sm-px-5");

    ClassSelector v6 = Css.dot("sm-px-6");

    ClassSelector v8 = Css.dot("sm-px-8");

    ClassSelector v10 = Css.dot("sm-px-10");

    ClassSelector v12 = Css.dot("sm-px-12");

    ClassSelector v16 = Css.dot("sm-px-16");

    ClassSelector v20 = Css.dot("sm-px-20");

    ClassSelector v24 = Css.dot("sm-px-24");

    ClassSelector v32 = Css.dot("sm-px-32");

    ClassSelector v40 = Css.dot("sm-px-40");

    ClassSelector v48 = Css.dot("sm-px-48");

    ClassSelector v56 = Css.dot("sm-px-56");

    ClassSelector v64 = Css.dot("sm-px-64");

  }

  public interface md {

    ClassSelector px = Css.dot("md-px-px");

    ClassSelector v0 = Css.dot("md-px-0");

    ClassSelector v1 = Css.dot("md-px-1");

    ClassSelector v2 = Css.dot("md-px-2");

    ClassSelector v3 = Css.dot("md-px-3");

    ClassSelector v4 = Css.dot("md-px-4");

    ClassSelector v5 = Css.dot("md-px-5");

    ClassSelector v6 = Css.dot("md-px-6");

    ClassSelector v8 = Css.dot("md-px-8");

    ClassSelector v10 = Css.dot("md-px-10");

    ClassSelector v12 = Css.dot("md-px-12");

    ClassSelector v16 = Css.dot("md-px-16");

    ClassSelector v20 = Css.dot("md-px-20");

    ClassSelector v24 = Css.dot("md-px-24");

    ClassSelector v32 = Css.dot("md-px-32");

    ClassSelector v40 = Css.dot("md-px-40");

    ClassSelector v48 = Css.dot("md-px-48");

    ClassSelector v56 = Css.dot("md-px-56");

    ClassSelector v64 = Css.dot("md-px-64");

  }

  public interface lg {

    ClassSelector px = Css.dot("lg-px-px");

    ClassSelector v0 = Css.dot("lg-px-0");

    ClassSelector v1 = Css.dot("lg-px-1");

    ClassSelector v2 = Css.dot("lg-px-2");

    ClassSelector v3 = Css.dot("lg-px-3");

    ClassSelector v4 = Css.dot("lg-px-4");

    ClassSelector v5 = Css.dot("lg-px-5");

    ClassSelector v6 = Css.dot("lg-px-6");

    ClassSelector v8 = Css.dot("lg-px-8");

    ClassSelector v10 = Css.dot("lg-px-10");

    ClassSelector v12 = Css.dot("lg-px-12");

    ClassSelector v16 = Css.dot("lg-px-16");

    ClassSelector v20 = Css.dot("lg-px-20");

    ClassSelector v24 = Css.dot("lg-px-24");

    ClassSelector v32 = Css.dot("lg-px-32");

    ClassSelector v40 = Css.dot("lg-px-40");

    ClassSelector v48 = Css.dot("lg-px-48");

    ClassSelector v56 = Css.dot("lg-px-56");

    ClassSelector v64 = Css.dot("lg-px-64");

  }

  public interface xl {

    ClassSelector px = Css.dot("xl-px-px");

    ClassSelector v0 = Css.dot("xl-px-0");

    ClassSelector v1 = Css.dot("xl-px-1");

    ClassSelector v2 = Css.dot("xl-px-2");

    ClassSelector v3 = Css.dot("xl-px-3");

    ClassSelector v4 = Css.dot("xl-px-4");

    ClassSelector v5 = Css.dot("xl-px-5");

    ClassSelector v6 = Css.dot("xl-px-6");

    ClassSelector v8 = Css.dot("xl-px-8");

    ClassSelector v10 = Css.dot("xl-px-10");

    ClassSelector v12 = Css.dot("xl-px-12");

    ClassSelector v16 = Css.dot("xl-px-16");

    ClassSelector v20 = Css.dot("xl-px-20");

    ClassSelector v24 = Css.dot("xl-px-24");

    ClassSelector v32 = Css.dot("xl-px-32");

    ClassSelector v40 = Css.dot("xl-px-40");

    ClassSelector v48 = Css.dot("xl-px-48");

    ClassSelector v56 = Css.dot("xl-px-56");

    ClassSelector v64 = Css.dot("xl-px-64");

  }

}