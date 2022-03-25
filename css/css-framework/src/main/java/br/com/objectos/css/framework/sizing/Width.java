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
public final class Width extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("w-auto");

  public static final ClassSelector px = Css.dot("w-px");

  public static final ClassSelector v0 = Css.dot("w-0");

  public static final ClassSelector v1 = Css.dot("w-1");

  public static final ClassSelector v2 = Css.dot("w-2");

  public static final ClassSelector v3 = Css.dot("w-3");

  public static final ClassSelector v4 = Css.dot("w-4");

  public static final ClassSelector v5 = Css.dot("w-5");

  public static final ClassSelector v6 = Css.dot("w-6");

  public static final ClassSelector v8 = Css.dot("w-8");

  public static final ClassSelector v10 = Css.dot("w-10");

  public static final ClassSelector v12 = Css.dot("w-12");

  public static final ClassSelector v16 = Css.dot("w-16");

  public static final ClassSelector v20 = Css.dot("w-20");

  public static final ClassSelector v24 = Css.dot("w-24");

  public static final ClassSelector v32 = Css.dot("w-32");

  public static final ClassSelector v40 = Css.dot("w-40");

  public static final ClassSelector v48 = Css.dot("w-48");

  public static final ClassSelector v56 = Css.dot("w-56");

  public static final ClassSelector v64 = Css.dot("w-64");

  public static final ClassSelector p1o2 = Css.dot("w-p1o2");

  public static final ClassSelector p1o3 = Css.dot("w-p1o3");

  public static final ClassSelector p2o3 = Css.dot("w-p2o3");

  public static final ClassSelector p1o4 = Css.dot("w-p1o4");

  public static final ClassSelector p2o4 = Css.dot("w-p2o4");

  public static final ClassSelector p3o4 = Css.dot("w-p3o4");

  public static final ClassSelector p1o5 = Css.dot("w-p1o5");

  public static final ClassSelector p2o5 = Css.dot("w-p2o5");

  public static final ClassSelector p3o5 = Css.dot("w-p3o5");

  public static final ClassSelector p4o5 = Css.dot("w-p4o5");

  public static final ClassSelector p1o6 = Css.dot("w-p1o6");

  public static final ClassSelector p2o6 = Css.dot("w-p2o6");

  public static final ClassSelector p3o6 = Css.dot("w-p3o6");

  public static final ClassSelector p4o6 = Css.dot("w-p4o6");

  public static final ClassSelector p5o6 = Css.dot("w-p5o6");

  public static final ClassSelector p1o12 = Css.dot("w-p1o12");

  public static final ClassSelector p2o12 = Css.dot("w-p2o12");

  public static final ClassSelector p3o12 = Css.dot("w-p3o12");

  public static final ClassSelector p4o12 = Css.dot("w-p4o12");

  public static final ClassSelector p5o12 = Css.dot("w-p5o12");

  public static final ClassSelector p6o12 = Css.dot("w-p6o12");

  public static final ClassSelector p7o12 = Css.dot("w-p7o12");

  public static final ClassSelector p8o12 = Css.dot("w-p8o12");

  public static final ClassSelector p9o12 = Css.dot("w-p9o12");

  public static final ClassSelector p10o12 = Css.dot("w-p10o12");

  public static final ClassSelector p11o12 = Css.dot("w-p11o12");

  public static final ClassSelector full = Css.dot("w-full");

  public static final ClassSelector screen = Css.dot("w-screen");

  @Override
  protected final void definition() {
    style(
        auto,
        width(Keywords.auto)
    );
    style(
        px,
        width(px(1))
    );
    style(
        v0,
        width(zero())
    );
    style(
        v1,
        width(rem(0.25))
    );
    style(
        v2,
        width(rem(0.5))
    );
    style(
        v3,
        width(rem(0.75))
    );
    style(
        v4,
        width(rem(1))
    );
    style(
        v5,
        width(rem(1.25))
    );
    style(
        v6,
        width(rem(1.5))
    );
    style(
        v8,
        width(rem(2))
    );
    style(
        v10,
        width(rem(2.5))
    );
    style(
        v12,
        width(rem(3))
    );
    style(
        v16,
        width(rem(4))
    );
    style(
        v20,
        width(rem(5))
    );
    style(
        v24,
        width(rem(6))
    );
    style(
        v32,
        width(rem(8))
    );
    style(
        v40,
        width(rem(10))
    );
    style(
        v48,
        width(rem(12))
    );
    style(
        v56,
        width(rem(14))
    );
    style(
        v64,
        width(rem(16))
    );
    style(
        p1o2,
        width(pct(50))
    );
    style(
        p1o3,
        width(pct(33.333333))
    );
    style(
        p2o3,
        width(pct(66.666667))
    );
    style(
        p1o4,
        width(pct(25))
    );
    style(
        p2o4,
        width(pct(50))
    );
    style(
        p3o4,
        width(pct(75))
    );
    style(
        p1o5,
        width(pct(20))
    );
    style(
        p2o5,
        width(pct(40))
    );
    style(
        p3o5,
        width(pct(60))
    );
    style(
        p4o5,
        width(pct(80))
    );
    style(
        p1o6,
        width(pct(16.666667))
    );
    style(
        p2o6,
        width(pct(33.333333))
    );
    style(
        p3o6,
        width(pct(50))
    );
    style(
        p4o6,
        width(pct(66.666667))
    );
    style(
        p5o6,
        width(pct(83.333333))
    );
    style(
        p1o12,
        width(pct(8.333333))
    );
    style(
        p2o12,
        width(pct(16.666667))
    );
    style(
        p3o12,
        width(pct(25))
    );
    style(
        p4o12,
        width(pct(33.333333))
    );
    style(
        p5o12,
        width(pct(41.666667))
    );
    style(
        p6o12,
        width(pct(50))
    );
    style(
        p7o12,
        width(pct(58.333333))
    );
    style(
        p8o12,
        width(pct(66.666667))
    );
    style(
        p9o12,
        width(pct(75))
    );
    style(
        p10o12,
        width(pct(83.333333))
    );
    style(
        p11o12,
        width(pct(91.666667))
    );
    style(
        full,
        width(pct(100))
    );
    style(
        screen,
        width(vw(100))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            width(Keywords.auto)
        ),

        style(
            sm.px,
            width(px(1))
        ),

        style(
            sm.v0,
            width(zero())
        ),

        style(
            sm.v1,
            width(rem(0.25))
        ),

        style(
            sm.v2,
            width(rem(0.5))
        ),

        style(
            sm.v3,
            width(rem(0.75))
        ),

        style(
            sm.v4,
            width(rem(1))
        ),

        style(
            sm.v5,
            width(rem(1.25))
        ),

        style(
            sm.v6,
            width(rem(1.5))
        ),

        style(
            sm.v8,
            width(rem(2))
        ),

        style(
            sm.v10,
            width(rem(2.5))
        ),

        style(
            sm.v12,
            width(rem(3))
        ),

        style(
            sm.v16,
            width(rem(4))
        ),

        style(
            sm.v20,
            width(rem(5))
        ),

        style(
            sm.v24,
            width(rem(6))
        ),

        style(
            sm.v32,
            width(rem(8))
        ),

        style(
            sm.v40,
            width(rem(10))
        ),

        style(
            sm.v48,
            width(rem(12))
        ),

        style(
            sm.v56,
            width(rem(14))
        ),

        style(
            sm.v64,
            width(rem(16))
        ),

        style(
            sm.p1o2,
            width(pct(50))
        ),

        style(
            sm.p1o3,
            width(pct(33.333333))
        ),

        style(
            sm.p2o3,
            width(pct(66.666667))
        ),

        style(
            sm.p1o4,
            width(pct(25))
        ),

        style(
            sm.p2o4,
            width(pct(50))
        ),

        style(
            sm.p3o4,
            width(pct(75))
        ),

        style(
            sm.p1o5,
            width(pct(20))
        ),

        style(
            sm.p2o5,
            width(pct(40))
        ),

        style(
            sm.p3o5,
            width(pct(60))
        ),

        style(
            sm.p4o5,
            width(pct(80))
        ),

        style(
            sm.p1o6,
            width(pct(16.666667))
        ),

        style(
            sm.p2o6,
            width(pct(33.333333))
        ),

        style(
            sm.p3o6,
            width(pct(50))
        ),

        style(
            sm.p4o6,
            width(pct(66.666667))
        ),

        style(
            sm.p5o6,
            width(pct(83.333333))
        ),

        style(
            sm.p1o12,
            width(pct(8.333333))
        ),

        style(
            sm.p2o12,
            width(pct(16.666667))
        ),

        style(
            sm.p3o12,
            width(pct(25))
        ),

        style(
            sm.p4o12,
            width(pct(33.333333))
        ),

        style(
            sm.p5o12,
            width(pct(41.666667))
        ),

        style(
            sm.p6o12,
            width(pct(50))
        ),

        style(
            sm.p7o12,
            width(pct(58.333333))
        ),

        style(
            sm.p8o12,
            width(pct(66.666667))
        ),

        style(
            sm.p9o12,
            width(pct(75))
        ),

        style(
            sm.p10o12,
            width(pct(83.333333))
        ),

        style(
            sm.p11o12,
            width(pct(91.666667))
        ),

        style(
            sm.full,
            width(pct(100))
        ),

        style(
            sm.screen,
            width(vw(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            width(Keywords.auto)
        ),

        style(
            md.px,
            width(px(1))
        ),

        style(
            md.v0,
            width(zero())
        ),

        style(
            md.v1,
            width(rem(0.25))
        ),

        style(
            md.v2,
            width(rem(0.5))
        ),

        style(
            md.v3,
            width(rem(0.75))
        ),

        style(
            md.v4,
            width(rem(1))
        ),

        style(
            md.v5,
            width(rem(1.25))
        ),

        style(
            md.v6,
            width(rem(1.5))
        ),

        style(
            md.v8,
            width(rem(2))
        ),

        style(
            md.v10,
            width(rem(2.5))
        ),

        style(
            md.v12,
            width(rem(3))
        ),

        style(
            md.v16,
            width(rem(4))
        ),

        style(
            md.v20,
            width(rem(5))
        ),

        style(
            md.v24,
            width(rem(6))
        ),

        style(
            md.v32,
            width(rem(8))
        ),

        style(
            md.v40,
            width(rem(10))
        ),

        style(
            md.v48,
            width(rem(12))
        ),

        style(
            md.v56,
            width(rem(14))
        ),

        style(
            md.v64,
            width(rem(16))
        ),

        style(
            md.p1o2,
            width(pct(50))
        ),

        style(
            md.p1o3,
            width(pct(33.333333))
        ),

        style(
            md.p2o3,
            width(pct(66.666667))
        ),

        style(
            md.p1o4,
            width(pct(25))
        ),

        style(
            md.p2o4,
            width(pct(50))
        ),

        style(
            md.p3o4,
            width(pct(75))
        ),

        style(
            md.p1o5,
            width(pct(20))
        ),

        style(
            md.p2o5,
            width(pct(40))
        ),

        style(
            md.p3o5,
            width(pct(60))
        ),

        style(
            md.p4o5,
            width(pct(80))
        ),

        style(
            md.p1o6,
            width(pct(16.666667))
        ),

        style(
            md.p2o6,
            width(pct(33.333333))
        ),

        style(
            md.p3o6,
            width(pct(50))
        ),

        style(
            md.p4o6,
            width(pct(66.666667))
        ),

        style(
            md.p5o6,
            width(pct(83.333333))
        ),

        style(
            md.p1o12,
            width(pct(8.333333))
        ),

        style(
            md.p2o12,
            width(pct(16.666667))
        ),

        style(
            md.p3o12,
            width(pct(25))
        ),

        style(
            md.p4o12,
            width(pct(33.333333))
        ),

        style(
            md.p5o12,
            width(pct(41.666667))
        ),

        style(
            md.p6o12,
            width(pct(50))
        ),

        style(
            md.p7o12,
            width(pct(58.333333))
        ),

        style(
            md.p8o12,
            width(pct(66.666667))
        ),

        style(
            md.p9o12,
            width(pct(75))
        ),

        style(
            md.p10o12,
            width(pct(83.333333))
        ),

        style(
            md.p11o12,
            width(pct(91.666667))
        ),

        style(
            md.full,
            width(pct(100))
        ),

        style(
            md.screen,
            width(vw(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            width(Keywords.auto)
        ),

        style(
            lg.px,
            width(px(1))
        ),

        style(
            lg.v0,
            width(zero())
        ),

        style(
            lg.v1,
            width(rem(0.25))
        ),

        style(
            lg.v2,
            width(rem(0.5))
        ),

        style(
            lg.v3,
            width(rem(0.75))
        ),

        style(
            lg.v4,
            width(rem(1))
        ),

        style(
            lg.v5,
            width(rem(1.25))
        ),

        style(
            lg.v6,
            width(rem(1.5))
        ),

        style(
            lg.v8,
            width(rem(2))
        ),

        style(
            lg.v10,
            width(rem(2.5))
        ),

        style(
            lg.v12,
            width(rem(3))
        ),

        style(
            lg.v16,
            width(rem(4))
        ),

        style(
            lg.v20,
            width(rem(5))
        ),

        style(
            lg.v24,
            width(rem(6))
        ),

        style(
            lg.v32,
            width(rem(8))
        ),

        style(
            lg.v40,
            width(rem(10))
        ),

        style(
            lg.v48,
            width(rem(12))
        ),

        style(
            lg.v56,
            width(rem(14))
        ),

        style(
            lg.v64,
            width(rem(16))
        ),

        style(
            lg.p1o2,
            width(pct(50))
        ),

        style(
            lg.p1o3,
            width(pct(33.333333))
        ),

        style(
            lg.p2o3,
            width(pct(66.666667))
        ),

        style(
            lg.p1o4,
            width(pct(25))
        ),

        style(
            lg.p2o4,
            width(pct(50))
        ),

        style(
            lg.p3o4,
            width(pct(75))
        ),

        style(
            lg.p1o5,
            width(pct(20))
        ),

        style(
            lg.p2o5,
            width(pct(40))
        ),

        style(
            lg.p3o5,
            width(pct(60))
        ),

        style(
            lg.p4o5,
            width(pct(80))
        ),

        style(
            lg.p1o6,
            width(pct(16.666667))
        ),

        style(
            lg.p2o6,
            width(pct(33.333333))
        ),

        style(
            lg.p3o6,
            width(pct(50))
        ),

        style(
            lg.p4o6,
            width(pct(66.666667))
        ),

        style(
            lg.p5o6,
            width(pct(83.333333))
        ),

        style(
            lg.p1o12,
            width(pct(8.333333))
        ),

        style(
            lg.p2o12,
            width(pct(16.666667))
        ),

        style(
            lg.p3o12,
            width(pct(25))
        ),

        style(
            lg.p4o12,
            width(pct(33.333333))
        ),

        style(
            lg.p5o12,
            width(pct(41.666667))
        ),

        style(
            lg.p6o12,
            width(pct(50))
        ),

        style(
            lg.p7o12,
            width(pct(58.333333))
        ),

        style(
            lg.p8o12,
            width(pct(66.666667))
        ),

        style(
            lg.p9o12,
            width(pct(75))
        ),

        style(
            lg.p10o12,
            width(pct(83.333333))
        ),

        style(
            lg.p11o12,
            width(pct(91.666667))
        ),

        style(
            lg.full,
            width(pct(100))
        ),

        style(
            lg.screen,
            width(vw(100))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            width(Keywords.auto)
        ),

        style(
            xl.px,
            width(px(1))
        ),

        style(
            xl.v0,
            width(zero())
        ),

        style(
            xl.v1,
            width(rem(0.25))
        ),

        style(
            xl.v2,
            width(rem(0.5))
        ),

        style(
            xl.v3,
            width(rem(0.75))
        ),

        style(
            xl.v4,
            width(rem(1))
        ),

        style(
            xl.v5,
            width(rem(1.25))
        ),

        style(
            xl.v6,
            width(rem(1.5))
        ),

        style(
            xl.v8,
            width(rem(2))
        ),

        style(
            xl.v10,
            width(rem(2.5))
        ),

        style(
            xl.v12,
            width(rem(3))
        ),

        style(
            xl.v16,
            width(rem(4))
        ),

        style(
            xl.v20,
            width(rem(5))
        ),

        style(
            xl.v24,
            width(rem(6))
        ),

        style(
            xl.v32,
            width(rem(8))
        ),

        style(
            xl.v40,
            width(rem(10))
        ),

        style(
            xl.v48,
            width(rem(12))
        ),

        style(
            xl.v56,
            width(rem(14))
        ),

        style(
            xl.v64,
            width(rem(16))
        ),

        style(
            xl.p1o2,
            width(pct(50))
        ),

        style(
            xl.p1o3,
            width(pct(33.333333))
        ),

        style(
            xl.p2o3,
            width(pct(66.666667))
        ),

        style(
            xl.p1o4,
            width(pct(25))
        ),

        style(
            xl.p2o4,
            width(pct(50))
        ),

        style(
            xl.p3o4,
            width(pct(75))
        ),

        style(
            xl.p1o5,
            width(pct(20))
        ),

        style(
            xl.p2o5,
            width(pct(40))
        ),

        style(
            xl.p3o5,
            width(pct(60))
        ),

        style(
            xl.p4o5,
            width(pct(80))
        ),

        style(
            xl.p1o6,
            width(pct(16.666667))
        ),

        style(
            xl.p2o6,
            width(pct(33.333333))
        ),

        style(
            xl.p3o6,
            width(pct(50))
        ),

        style(
            xl.p4o6,
            width(pct(66.666667))
        ),

        style(
            xl.p5o6,
            width(pct(83.333333))
        ),

        style(
            xl.p1o12,
            width(pct(8.333333))
        ),

        style(
            xl.p2o12,
            width(pct(16.666667))
        ),

        style(
            xl.p3o12,
            width(pct(25))
        ),

        style(
            xl.p4o12,
            width(pct(33.333333))
        ),

        style(
            xl.p5o12,
            width(pct(41.666667))
        ),

        style(
            xl.p6o12,
            width(pct(50))
        ),

        style(
            xl.p7o12,
            width(pct(58.333333))
        ),

        style(
            xl.p8o12,
            width(pct(66.666667))
        ),

        style(
            xl.p9o12,
            width(pct(75))
        ),

        style(
            xl.p10o12,
            width(pct(83.333333))
        ),

        style(
            xl.p11o12,
            width(pct(91.666667))
        ),

        style(
            xl.full,
            width(pct(100))
        ),

        style(
            xl.screen,
            width(vw(100))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-w-auto");

    ClassSelector px = Css.dot("sm-w-px");

    ClassSelector v0 = Css.dot("sm-w-0");

    ClassSelector v1 = Css.dot("sm-w-1");

    ClassSelector v2 = Css.dot("sm-w-2");

    ClassSelector v3 = Css.dot("sm-w-3");

    ClassSelector v4 = Css.dot("sm-w-4");

    ClassSelector v5 = Css.dot("sm-w-5");

    ClassSelector v6 = Css.dot("sm-w-6");

    ClassSelector v8 = Css.dot("sm-w-8");

    ClassSelector v10 = Css.dot("sm-w-10");

    ClassSelector v12 = Css.dot("sm-w-12");

    ClassSelector v16 = Css.dot("sm-w-16");

    ClassSelector v20 = Css.dot("sm-w-20");

    ClassSelector v24 = Css.dot("sm-w-24");

    ClassSelector v32 = Css.dot("sm-w-32");

    ClassSelector v40 = Css.dot("sm-w-40");

    ClassSelector v48 = Css.dot("sm-w-48");

    ClassSelector v56 = Css.dot("sm-w-56");

    ClassSelector v64 = Css.dot("sm-w-64");

    ClassSelector p1o2 = Css.dot("sm-w-p1o2");

    ClassSelector p1o3 = Css.dot("sm-w-p1o3");

    ClassSelector p2o3 = Css.dot("sm-w-p2o3");

    ClassSelector p1o4 = Css.dot("sm-w-p1o4");

    ClassSelector p2o4 = Css.dot("sm-w-p2o4");

    ClassSelector p3o4 = Css.dot("sm-w-p3o4");

    ClassSelector p1o5 = Css.dot("sm-w-p1o5");

    ClassSelector p2o5 = Css.dot("sm-w-p2o5");

    ClassSelector p3o5 = Css.dot("sm-w-p3o5");

    ClassSelector p4o5 = Css.dot("sm-w-p4o5");

    ClassSelector p1o6 = Css.dot("sm-w-p1o6");

    ClassSelector p2o6 = Css.dot("sm-w-p2o6");

    ClassSelector p3o6 = Css.dot("sm-w-p3o6");

    ClassSelector p4o6 = Css.dot("sm-w-p4o6");

    ClassSelector p5o6 = Css.dot("sm-w-p5o6");

    ClassSelector p1o12 = Css.dot("sm-w-p1o12");

    ClassSelector p2o12 = Css.dot("sm-w-p2o12");

    ClassSelector p3o12 = Css.dot("sm-w-p3o12");

    ClassSelector p4o12 = Css.dot("sm-w-p4o12");

    ClassSelector p5o12 = Css.dot("sm-w-p5o12");

    ClassSelector p6o12 = Css.dot("sm-w-p6o12");

    ClassSelector p7o12 = Css.dot("sm-w-p7o12");

    ClassSelector p8o12 = Css.dot("sm-w-p8o12");

    ClassSelector p9o12 = Css.dot("sm-w-p9o12");

    ClassSelector p10o12 = Css.dot("sm-w-p10o12");

    ClassSelector p11o12 = Css.dot("sm-w-p11o12");

    ClassSelector full = Css.dot("sm-w-full");

    ClassSelector screen = Css.dot("sm-w-screen");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-w-auto");

    ClassSelector px = Css.dot("md-w-px");

    ClassSelector v0 = Css.dot("md-w-0");

    ClassSelector v1 = Css.dot("md-w-1");

    ClassSelector v2 = Css.dot("md-w-2");

    ClassSelector v3 = Css.dot("md-w-3");

    ClassSelector v4 = Css.dot("md-w-4");

    ClassSelector v5 = Css.dot("md-w-5");

    ClassSelector v6 = Css.dot("md-w-6");

    ClassSelector v8 = Css.dot("md-w-8");

    ClassSelector v10 = Css.dot("md-w-10");

    ClassSelector v12 = Css.dot("md-w-12");

    ClassSelector v16 = Css.dot("md-w-16");

    ClassSelector v20 = Css.dot("md-w-20");

    ClassSelector v24 = Css.dot("md-w-24");

    ClassSelector v32 = Css.dot("md-w-32");

    ClassSelector v40 = Css.dot("md-w-40");

    ClassSelector v48 = Css.dot("md-w-48");

    ClassSelector v56 = Css.dot("md-w-56");

    ClassSelector v64 = Css.dot("md-w-64");

    ClassSelector p1o2 = Css.dot("md-w-p1o2");

    ClassSelector p1o3 = Css.dot("md-w-p1o3");

    ClassSelector p2o3 = Css.dot("md-w-p2o3");

    ClassSelector p1o4 = Css.dot("md-w-p1o4");

    ClassSelector p2o4 = Css.dot("md-w-p2o4");

    ClassSelector p3o4 = Css.dot("md-w-p3o4");

    ClassSelector p1o5 = Css.dot("md-w-p1o5");

    ClassSelector p2o5 = Css.dot("md-w-p2o5");

    ClassSelector p3o5 = Css.dot("md-w-p3o5");

    ClassSelector p4o5 = Css.dot("md-w-p4o5");

    ClassSelector p1o6 = Css.dot("md-w-p1o6");

    ClassSelector p2o6 = Css.dot("md-w-p2o6");

    ClassSelector p3o6 = Css.dot("md-w-p3o6");

    ClassSelector p4o6 = Css.dot("md-w-p4o6");

    ClassSelector p5o6 = Css.dot("md-w-p5o6");

    ClassSelector p1o12 = Css.dot("md-w-p1o12");

    ClassSelector p2o12 = Css.dot("md-w-p2o12");

    ClassSelector p3o12 = Css.dot("md-w-p3o12");

    ClassSelector p4o12 = Css.dot("md-w-p4o12");

    ClassSelector p5o12 = Css.dot("md-w-p5o12");

    ClassSelector p6o12 = Css.dot("md-w-p6o12");

    ClassSelector p7o12 = Css.dot("md-w-p7o12");

    ClassSelector p8o12 = Css.dot("md-w-p8o12");

    ClassSelector p9o12 = Css.dot("md-w-p9o12");

    ClassSelector p10o12 = Css.dot("md-w-p10o12");

    ClassSelector p11o12 = Css.dot("md-w-p11o12");

    ClassSelector full = Css.dot("md-w-full");

    ClassSelector screen = Css.dot("md-w-screen");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-w-auto");

    ClassSelector px = Css.dot("lg-w-px");

    ClassSelector v0 = Css.dot("lg-w-0");

    ClassSelector v1 = Css.dot("lg-w-1");

    ClassSelector v2 = Css.dot("lg-w-2");

    ClassSelector v3 = Css.dot("lg-w-3");

    ClassSelector v4 = Css.dot("lg-w-4");

    ClassSelector v5 = Css.dot("lg-w-5");

    ClassSelector v6 = Css.dot("lg-w-6");

    ClassSelector v8 = Css.dot("lg-w-8");

    ClassSelector v10 = Css.dot("lg-w-10");

    ClassSelector v12 = Css.dot("lg-w-12");

    ClassSelector v16 = Css.dot("lg-w-16");

    ClassSelector v20 = Css.dot("lg-w-20");

    ClassSelector v24 = Css.dot("lg-w-24");

    ClassSelector v32 = Css.dot("lg-w-32");

    ClassSelector v40 = Css.dot("lg-w-40");

    ClassSelector v48 = Css.dot("lg-w-48");

    ClassSelector v56 = Css.dot("lg-w-56");

    ClassSelector v64 = Css.dot("lg-w-64");

    ClassSelector p1o2 = Css.dot("lg-w-p1o2");

    ClassSelector p1o3 = Css.dot("lg-w-p1o3");

    ClassSelector p2o3 = Css.dot("lg-w-p2o3");

    ClassSelector p1o4 = Css.dot("lg-w-p1o4");

    ClassSelector p2o4 = Css.dot("lg-w-p2o4");

    ClassSelector p3o4 = Css.dot("lg-w-p3o4");

    ClassSelector p1o5 = Css.dot("lg-w-p1o5");

    ClassSelector p2o5 = Css.dot("lg-w-p2o5");

    ClassSelector p3o5 = Css.dot("lg-w-p3o5");

    ClassSelector p4o5 = Css.dot("lg-w-p4o5");

    ClassSelector p1o6 = Css.dot("lg-w-p1o6");

    ClassSelector p2o6 = Css.dot("lg-w-p2o6");

    ClassSelector p3o6 = Css.dot("lg-w-p3o6");

    ClassSelector p4o6 = Css.dot("lg-w-p4o6");

    ClassSelector p5o6 = Css.dot("lg-w-p5o6");

    ClassSelector p1o12 = Css.dot("lg-w-p1o12");

    ClassSelector p2o12 = Css.dot("lg-w-p2o12");

    ClassSelector p3o12 = Css.dot("lg-w-p3o12");

    ClassSelector p4o12 = Css.dot("lg-w-p4o12");

    ClassSelector p5o12 = Css.dot("lg-w-p5o12");

    ClassSelector p6o12 = Css.dot("lg-w-p6o12");

    ClassSelector p7o12 = Css.dot("lg-w-p7o12");

    ClassSelector p8o12 = Css.dot("lg-w-p8o12");

    ClassSelector p9o12 = Css.dot("lg-w-p9o12");

    ClassSelector p10o12 = Css.dot("lg-w-p10o12");

    ClassSelector p11o12 = Css.dot("lg-w-p11o12");

    ClassSelector full = Css.dot("lg-w-full");

    ClassSelector screen = Css.dot("lg-w-screen");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-w-auto");

    ClassSelector px = Css.dot("xl-w-px");

    ClassSelector v0 = Css.dot("xl-w-0");

    ClassSelector v1 = Css.dot("xl-w-1");

    ClassSelector v2 = Css.dot("xl-w-2");

    ClassSelector v3 = Css.dot("xl-w-3");

    ClassSelector v4 = Css.dot("xl-w-4");

    ClassSelector v5 = Css.dot("xl-w-5");

    ClassSelector v6 = Css.dot("xl-w-6");

    ClassSelector v8 = Css.dot("xl-w-8");

    ClassSelector v10 = Css.dot("xl-w-10");

    ClassSelector v12 = Css.dot("xl-w-12");

    ClassSelector v16 = Css.dot("xl-w-16");

    ClassSelector v20 = Css.dot("xl-w-20");

    ClassSelector v24 = Css.dot("xl-w-24");

    ClassSelector v32 = Css.dot("xl-w-32");

    ClassSelector v40 = Css.dot("xl-w-40");

    ClassSelector v48 = Css.dot("xl-w-48");

    ClassSelector v56 = Css.dot("xl-w-56");

    ClassSelector v64 = Css.dot("xl-w-64");

    ClassSelector p1o2 = Css.dot("xl-w-p1o2");

    ClassSelector p1o3 = Css.dot("xl-w-p1o3");

    ClassSelector p2o3 = Css.dot("xl-w-p2o3");

    ClassSelector p1o4 = Css.dot("xl-w-p1o4");

    ClassSelector p2o4 = Css.dot("xl-w-p2o4");

    ClassSelector p3o4 = Css.dot("xl-w-p3o4");

    ClassSelector p1o5 = Css.dot("xl-w-p1o5");

    ClassSelector p2o5 = Css.dot("xl-w-p2o5");

    ClassSelector p3o5 = Css.dot("xl-w-p3o5");

    ClassSelector p4o5 = Css.dot("xl-w-p4o5");

    ClassSelector p1o6 = Css.dot("xl-w-p1o6");

    ClassSelector p2o6 = Css.dot("xl-w-p2o6");

    ClassSelector p3o6 = Css.dot("xl-w-p3o6");

    ClassSelector p4o6 = Css.dot("xl-w-p4o6");

    ClassSelector p5o6 = Css.dot("xl-w-p5o6");

    ClassSelector p1o12 = Css.dot("xl-w-p1o12");

    ClassSelector p2o12 = Css.dot("xl-w-p2o12");

    ClassSelector p3o12 = Css.dot("xl-w-p3o12");

    ClassSelector p4o12 = Css.dot("xl-w-p4o12");

    ClassSelector p5o12 = Css.dot("xl-w-p5o12");

    ClassSelector p6o12 = Css.dot("xl-w-p6o12");

    ClassSelector p7o12 = Css.dot("xl-w-p7o12");

    ClassSelector p8o12 = Css.dot("xl-w-p8o12");

    ClassSelector p9o12 = Css.dot("xl-w-p9o12");

    ClassSelector p10o12 = Css.dot("xl-w-p10o12");

    ClassSelector p11o12 = Css.dot("xl-w-p11o12");

    ClassSelector full = Css.dot("xl-w-full");

    ClassSelector screen = Css.dot("xl-w-screen");

  }

}