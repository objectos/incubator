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
public final class PaddingY extends AbstractStyleSheet {

  public static final ClassSelector px = Css.dot("py-px");

  public static final ClassSelector v0 = Css.dot("py-0");

  public static final ClassSelector v1 = Css.dot("py-1");

  public static final ClassSelector v2 = Css.dot("py-2");

  public static final ClassSelector v3 = Css.dot("py-3");

  public static final ClassSelector v4 = Css.dot("py-4");

  public static final ClassSelector v5 = Css.dot("py-5");

  public static final ClassSelector v6 = Css.dot("py-6");

  public static final ClassSelector v8 = Css.dot("py-8");

  public static final ClassSelector v10 = Css.dot("py-10");

  public static final ClassSelector v12 = Css.dot("py-12");

  public static final ClassSelector v16 = Css.dot("py-16");

  public static final ClassSelector v20 = Css.dot("py-20");

  public static final ClassSelector v24 = Css.dot("py-24");

  public static final ClassSelector v32 = Css.dot("py-32");

  public static final ClassSelector v40 = Css.dot("py-40");

  public static final ClassSelector v48 = Css.dot("py-48");

  public static final ClassSelector v56 = Css.dot("py-56");

  public static final ClassSelector v64 = Css.dot("py-64");

  @Override
  protected final void definition() {
    style(
        px,
        paddingTop(px(1)),
        paddingBottom(px(1))
    );
    style(
        v0,
        paddingTop(zero()),
        paddingBottom(zero())
    );
    style(
        v1,
        paddingTop(rem(0.25)),
        paddingBottom(rem(0.25))
    );
    style(
        v2,
        paddingTop(rem(0.5)),
        paddingBottom(rem(0.5))
    );
    style(
        v3,
        paddingTop(rem(0.75)),
        paddingBottom(rem(0.75))
    );
    style(
        v4,
        paddingTop(rem(1)),
        paddingBottom(rem(1))
    );
    style(
        v5,
        paddingTop(rem(1.25)),
        paddingBottom(rem(1.25))
    );
    style(
        v6,
        paddingTop(rem(1.5)),
        paddingBottom(rem(1.5))
    );
    style(
        v8,
        paddingTop(rem(2)),
        paddingBottom(rem(2))
    );
    style(
        v10,
        paddingTop(rem(2.5)),
        paddingBottom(rem(2.5))
    );
    style(
        v12,
        paddingTop(rem(3)),
        paddingBottom(rem(3))
    );
    style(
        v16,
        paddingTop(rem(4)),
        paddingBottom(rem(4))
    );
    style(
        v20,
        paddingTop(rem(5)),
        paddingBottom(rem(5))
    );
    style(
        v24,
        paddingTop(rem(6)),
        paddingBottom(rem(6))
    );
    style(
        v32,
        paddingTop(rem(8)),
        paddingBottom(rem(8))
    );
    style(
        v40,
        paddingTop(rem(10)),
        paddingBottom(rem(10))
    );
    style(
        v48,
        paddingTop(rem(12)),
        paddingBottom(rem(12))
    );
    style(
        v56,
        paddingTop(rem(14)),
        paddingBottom(rem(14))
    );
    style(
        v64,
        paddingTop(rem(16)),
        paddingBottom(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.px,
            paddingTop(px(1)),
            paddingBottom(px(1))
        ),

        style(
            sm.v0,
            paddingTop(zero()),
            paddingBottom(zero())
        ),

        style(
            sm.v1,
            paddingTop(rem(0.25)),
            paddingBottom(rem(0.25))
        ),

        style(
            sm.v2,
            paddingTop(rem(0.5)),
            paddingBottom(rem(0.5))
        ),

        style(
            sm.v3,
            paddingTop(rem(0.75)),
            paddingBottom(rem(0.75))
        ),

        style(
            sm.v4,
            paddingTop(rem(1)),
            paddingBottom(rem(1))
        ),

        style(
            sm.v5,
            paddingTop(rem(1.25)),
            paddingBottom(rem(1.25))
        ),

        style(
            sm.v6,
            paddingTop(rem(1.5)),
            paddingBottom(rem(1.5))
        ),

        style(
            sm.v8,
            paddingTop(rem(2)),
            paddingBottom(rem(2))
        ),

        style(
            sm.v10,
            paddingTop(rem(2.5)),
            paddingBottom(rem(2.5))
        ),

        style(
            sm.v12,
            paddingTop(rem(3)),
            paddingBottom(rem(3))
        ),

        style(
            sm.v16,
            paddingTop(rem(4)),
            paddingBottom(rem(4))
        ),

        style(
            sm.v20,
            paddingTop(rem(5)),
            paddingBottom(rem(5))
        ),

        style(
            sm.v24,
            paddingTop(rem(6)),
            paddingBottom(rem(6))
        ),

        style(
            sm.v32,
            paddingTop(rem(8)),
            paddingBottom(rem(8))
        ),

        style(
            sm.v40,
            paddingTop(rem(10)),
            paddingBottom(rem(10))
        ),

        style(
            sm.v48,
            paddingTop(rem(12)),
            paddingBottom(rem(12))
        ),

        style(
            sm.v56,
            paddingTop(rem(14)),
            paddingBottom(rem(14))
        ),

        style(
            sm.v64,
            paddingTop(rem(16)),
            paddingBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.px,
            paddingTop(px(1)),
            paddingBottom(px(1))
        ),

        style(
            md.v0,
            paddingTop(zero()),
            paddingBottom(zero())
        ),

        style(
            md.v1,
            paddingTop(rem(0.25)),
            paddingBottom(rem(0.25))
        ),

        style(
            md.v2,
            paddingTop(rem(0.5)),
            paddingBottom(rem(0.5))
        ),

        style(
            md.v3,
            paddingTop(rem(0.75)),
            paddingBottom(rem(0.75))
        ),

        style(
            md.v4,
            paddingTop(rem(1)),
            paddingBottom(rem(1))
        ),

        style(
            md.v5,
            paddingTop(rem(1.25)),
            paddingBottom(rem(1.25))
        ),

        style(
            md.v6,
            paddingTop(rem(1.5)),
            paddingBottom(rem(1.5))
        ),

        style(
            md.v8,
            paddingTop(rem(2)),
            paddingBottom(rem(2))
        ),

        style(
            md.v10,
            paddingTop(rem(2.5)),
            paddingBottom(rem(2.5))
        ),

        style(
            md.v12,
            paddingTop(rem(3)),
            paddingBottom(rem(3))
        ),

        style(
            md.v16,
            paddingTop(rem(4)),
            paddingBottom(rem(4))
        ),

        style(
            md.v20,
            paddingTop(rem(5)),
            paddingBottom(rem(5))
        ),

        style(
            md.v24,
            paddingTop(rem(6)),
            paddingBottom(rem(6))
        ),

        style(
            md.v32,
            paddingTop(rem(8)),
            paddingBottom(rem(8))
        ),

        style(
            md.v40,
            paddingTop(rem(10)),
            paddingBottom(rem(10))
        ),

        style(
            md.v48,
            paddingTop(rem(12)),
            paddingBottom(rem(12))
        ),

        style(
            md.v56,
            paddingTop(rem(14)),
            paddingBottom(rem(14))
        ),

        style(
            md.v64,
            paddingTop(rem(16)),
            paddingBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.px,
            paddingTop(px(1)),
            paddingBottom(px(1))
        ),

        style(
            lg.v0,
            paddingTop(zero()),
            paddingBottom(zero())
        ),

        style(
            lg.v1,
            paddingTop(rem(0.25)),
            paddingBottom(rem(0.25))
        ),

        style(
            lg.v2,
            paddingTop(rem(0.5)),
            paddingBottom(rem(0.5))
        ),

        style(
            lg.v3,
            paddingTop(rem(0.75)),
            paddingBottom(rem(0.75))
        ),

        style(
            lg.v4,
            paddingTop(rem(1)),
            paddingBottom(rem(1))
        ),

        style(
            lg.v5,
            paddingTop(rem(1.25)),
            paddingBottom(rem(1.25))
        ),

        style(
            lg.v6,
            paddingTop(rem(1.5)),
            paddingBottom(rem(1.5))
        ),

        style(
            lg.v8,
            paddingTop(rem(2)),
            paddingBottom(rem(2))
        ),

        style(
            lg.v10,
            paddingTop(rem(2.5)),
            paddingBottom(rem(2.5))
        ),

        style(
            lg.v12,
            paddingTop(rem(3)),
            paddingBottom(rem(3))
        ),

        style(
            lg.v16,
            paddingTop(rem(4)),
            paddingBottom(rem(4))
        ),

        style(
            lg.v20,
            paddingTop(rem(5)),
            paddingBottom(rem(5))
        ),

        style(
            lg.v24,
            paddingTop(rem(6)),
            paddingBottom(rem(6))
        ),

        style(
            lg.v32,
            paddingTop(rem(8)),
            paddingBottom(rem(8))
        ),

        style(
            lg.v40,
            paddingTop(rem(10)),
            paddingBottom(rem(10))
        ),

        style(
            lg.v48,
            paddingTop(rem(12)),
            paddingBottom(rem(12))
        ),

        style(
            lg.v56,
            paddingTop(rem(14)),
            paddingBottom(rem(14))
        ),

        style(
            lg.v64,
            paddingTop(rem(16)),
            paddingBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.px,
            paddingTop(px(1)),
            paddingBottom(px(1))
        ),

        style(
            xl.v0,
            paddingTop(zero()),
            paddingBottom(zero())
        ),

        style(
            xl.v1,
            paddingTop(rem(0.25)),
            paddingBottom(rem(0.25))
        ),

        style(
            xl.v2,
            paddingTop(rem(0.5)),
            paddingBottom(rem(0.5))
        ),

        style(
            xl.v3,
            paddingTop(rem(0.75)),
            paddingBottom(rem(0.75))
        ),

        style(
            xl.v4,
            paddingTop(rem(1)),
            paddingBottom(rem(1))
        ),

        style(
            xl.v5,
            paddingTop(rem(1.25)),
            paddingBottom(rem(1.25))
        ),

        style(
            xl.v6,
            paddingTop(rem(1.5)),
            paddingBottom(rem(1.5))
        ),

        style(
            xl.v8,
            paddingTop(rem(2)),
            paddingBottom(rem(2))
        ),

        style(
            xl.v10,
            paddingTop(rem(2.5)),
            paddingBottom(rem(2.5))
        ),

        style(
            xl.v12,
            paddingTop(rem(3)),
            paddingBottom(rem(3))
        ),

        style(
            xl.v16,
            paddingTop(rem(4)),
            paddingBottom(rem(4))
        ),

        style(
            xl.v20,
            paddingTop(rem(5)),
            paddingBottom(rem(5))
        ),

        style(
            xl.v24,
            paddingTop(rem(6)),
            paddingBottom(rem(6))
        ),

        style(
            xl.v32,
            paddingTop(rem(8)),
            paddingBottom(rem(8))
        ),

        style(
            xl.v40,
            paddingTop(rem(10)),
            paddingBottom(rem(10))
        ),

        style(
            xl.v48,
            paddingTop(rem(12)),
            paddingBottom(rem(12))
        ),

        style(
            xl.v56,
            paddingTop(rem(14)),
            paddingBottom(rem(14))
        ),

        style(
            xl.v64,
            paddingTop(rem(16)),
            paddingBottom(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector px = Css.dot("sm-py-px");

    ClassSelector v0 = Css.dot("sm-py-0");

    ClassSelector v1 = Css.dot("sm-py-1");

    ClassSelector v2 = Css.dot("sm-py-2");

    ClassSelector v3 = Css.dot("sm-py-3");

    ClassSelector v4 = Css.dot("sm-py-4");

    ClassSelector v5 = Css.dot("sm-py-5");

    ClassSelector v6 = Css.dot("sm-py-6");

    ClassSelector v8 = Css.dot("sm-py-8");

    ClassSelector v10 = Css.dot("sm-py-10");

    ClassSelector v12 = Css.dot("sm-py-12");

    ClassSelector v16 = Css.dot("sm-py-16");

    ClassSelector v20 = Css.dot("sm-py-20");

    ClassSelector v24 = Css.dot("sm-py-24");

    ClassSelector v32 = Css.dot("sm-py-32");

    ClassSelector v40 = Css.dot("sm-py-40");

    ClassSelector v48 = Css.dot("sm-py-48");

    ClassSelector v56 = Css.dot("sm-py-56");

    ClassSelector v64 = Css.dot("sm-py-64");

  }

  public interface md {

    ClassSelector px = Css.dot("md-py-px");

    ClassSelector v0 = Css.dot("md-py-0");

    ClassSelector v1 = Css.dot("md-py-1");

    ClassSelector v2 = Css.dot("md-py-2");

    ClassSelector v3 = Css.dot("md-py-3");

    ClassSelector v4 = Css.dot("md-py-4");

    ClassSelector v5 = Css.dot("md-py-5");

    ClassSelector v6 = Css.dot("md-py-6");

    ClassSelector v8 = Css.dot("md-py-8");

    ClassSelector v10 = Css.dot("md-py-10");

    ClassSelector v12 = Css.dot("md-py-12");

    ClassSelector v16 = Css.dot("md-py-16");

    ClassSelector v20 = Css.dot("md-py-20");

    ClassSelector v24 = Css.dot("md-py-24");

    ClassSelector v32 = Css.dot("md-py-32");

    ClassSelector v40 = Css.dot("md-py-40");

    ClassSelector v48 = Css.dot("md-py-48");

    ClassSelector v56 = Css.dot("md-py-56");

    ClassSelector v64 = Css.dot("md-py-64");

  }

  public interface lg {

    ClassSelector px = Css.dot("lg-py-px");

    ClassSelector v0 = Css.dot("lg-py-0");

    ClassSelector v1 = Css.dot("lg-py-1");

    ClassSelector v2 = Css.dot("lg-py-2");

    ClassSelector v3 = Css.dot("lg-py-3");

    ClassSelector v4 = Css.dot("lg-py-4");

    ClassSelector v5 = Css.dot("lg-py-5");

    ClassSelector v6 = Css.dot("lg-py-6");

    ClassSelector v8 = Css.dot("lg-py-8");

    ClassSelector v10 = Css.dot("lg-py-10");

    ClassSelector v12 = Css.dot("lg-py-12");

    ClassSelector v16 = Css.dot("lg-py-16");

    ClassSelector v20 = Css.dot("lg-py-20");

    ClassSelector v24 = Css.dot("lg-py-24");

    ClassSelector v32 = Css.dot("lg-py-32");

    ClassSelector v40 = Css.dot("lg-py-40");

    ClassSelector v48 = Css.dot("lg-py-48");

    ClassSelector v56 = Css.dot("lg-py-56");

    ClassSelector v64 = Css.dot("lg-py-64");

  }

  public interface xl {

    ClassSelector px = Css.dot("xl-py-px");

    ClassSelector v0 = Css.dot("xl-py-0");

    ClassSelector v1 = Css.dot("xl-py-1");

    ClassSelector v2 = Css.dot("xl-py-2");

    ClassSelector v3 = Css.dot("xl-py-3");

    ClassSelector v4 = Css.dot("xl-py-4");

    ClassSelector v5 = Css.dot("xl-py-5");

    ClassSelector v6 = Css.dot("xl-py-6");

    ClassSelector v8 = Css.dot("xl-py-8");

    ClassSelector v10 = Css.dot("xl-py-10");

    ClassSelector v12 = Css.dot("xl-py-12");

    ClassSelector v16 = Css.dot("xl-py-16");

    ClassSelector v20 = Css.dot("xl-py-20");

    ClassSelector v24 = Css.dot("xl-py-24");

    ClassSelector v32 = Css.dot("xl-py-32");

    ClassSelector v40 = Css.dot("xl-py-40");

    ClassSelector v48 = Css.dot("xl-py-48");

    ClassSelector v56 = Css.dot("xl-py-56");

    ClassSelector v64 = Css.dot("xl-py-64");

  }

}