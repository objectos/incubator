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
public final class MarginBottom extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector px = Css.randomDot(5);

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector v1 = Css.randomDot(5);

  public static final ClassSelector v2 = Css.randomDot(5);

  public static final ClassSelector v3 = Css.randomDot(5);

  public static final ClassSelector v4 = Css.randomDot(5);

  public static final ClassSelector v5 = Css.randomDot(5);

  public static final ClassSelector v6 = Css.randomDot(5);

  public static final ClassSelector v8 = Css.randomDot(5);

  public static final ClassSelector v10 = Css.randomDot(5);

  public static final ClassSelector v12 = Css.randomDot(5);

  public static final ClassSelector v16 = Css.randomDot(5);

  public static final ClassSelector v20 = Css.randomDot(5);

  public static final ClassSelector v24 = Css.randomDot(5);

  public static final ClassSelector v32 = Css.randomDot(5);

  public static final ClassSelector v40 = Css.randomDot(5);

  public static final ClassSelector v48 = Css.randomDot(5);

  public static final ClassSelector v56 = Css.randomDot(5);

  public static final ClassSelector v64 = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        auto,
        marginBottom(Keywords.auto)
    );
    style(
        px,
        marginBottom(px(1))
    );
    style(
        v0,
        marginBottom(zero())
    );
    style(
        v1,
        marginBottom(rem(0.25))
    );
    style(
        v2,
        marginBottom(rem(0.5))
    );
    style(
        v3,
        marginBottom(rem(0.75))
    );
    style(
        v4,
        marginBottom(rem(1))
    );
    style(
        v5,
        marginBottom(rem(1.25))
    );
    style(
        v6,
        marginBottom(rem(1.5))
    );
    style(
        v8,
        marginBottom(rem(2))
    );
    style(
        v10,
        marginBottom(rem(2.5))
    );
    style(
        v12,
        marginBottom(rem(3))
    );
    style(
        v16,
        marginBottom(rem(4))
    );
    style(
        v20,
        marginBottom(rem(5))
    );
    style(
        v24,
        marginBottom(rem(6))
    );
    style(
        v32,
        marginBottom(rem(8))
    );
    style(
        v40,
        marginBottom(rem(10))
    );
    style(
        v48,
        marginBottom(rem(12))
    );
    style(
        v56,
        marginBottom(rem(14))
    );
    style(
        v64,
        marginBottom(rem(16))
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            marginBottom(Keywords.auto)
        ),

        style(
            sm.px,
            marginBottom(px(1))
        ),

        style(
            sm.v0,
            marginBottom(zero())
        ),

        style(
            sm.v1,
            marginBottom(rem(0.25))
        ),

        style(
            sm.v2,
            marginBottom(rem(0.5))
        ),

        style(
            sm.v3,
            marginBottom(rem(0.75))
        ),

        style(
            sm.v4,
            marginBottom(rem(1))
        ),

        style(
            sm.v5,
            marginBottom(rem(1.25))
        ),

        style(
            sm.v6,
            marginBottom(rem(1.5))
        ),

        style(
            sm.v8,
            marginBottom(rem(2))
        ),

        style(
            sm.v10,
            marginBottom(rem(2.5))
        ),

        style(
            sm.v12,
            marginBottom(rem(3))
        ),

        style(
            sm.v16,
            marginBottom(rem(4))
        ),

        style(
            sm.v20,
            marginBottom(rem(5))
        ),

        style(
            sm.v24,
            marginBottom(rem(6))
        ),

        style(
            sm.v32,
            marginBottom(rem(8))
        ),

        style(
            sm.v40,
            marginBottom(rem(10))
        ),

        style(
            sm.v48,
            marginBottom(rem(12))
        ),

        style(
            sm.v56,
            marginBottom(rem(14))
        ),

        style(
            sm.v64,
            marginBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            marginBottom(Keywords.auto)
        ),

        style(
            md.px,
            marginBottom(px(1))
        ),

        style(
            md.v0,
            marginBottom(zero())
        ),

        style(
            md.v1,
            marginBottom(rem(0.25))
        ),

        style(
            md.v2,
            marginBottom(rem(0.5))
        ),

        style(
            md.v3,
            marginBottom(rem(0.75))
        ),

        style(
            md.v4,
            marginBottom(rem(1))
        ),

        style(
            md.v5,
            marginBottom(rem(1.25))
        ),

        style(
            md.v6,
            marginBottom(rem(1.5))
        ),

        style(
            md.v8,
            marginBottom(rem(2))
        ),

        style(
            md.v10,
            marginBottom(rem(2.5))
        ),

        style(
            md.v12,
            marginBottom(rem(3))
        ),

        style(
            md.v16,
            marginBottom(rem(4))
        ),

        style(
            md.v20,
            marginBottom(rem(5))
        ),

        style(
            md.v24,
            marginBottom(rem(6))
        ),

        style(
            md.v32,
            marginBottom(rem(8))
        ),

        style(
            md.v40,
            marginBottom(rem(10))
        ),

        style(
            md.v48,
            marginBottom(rem(12))
        ),

        style(
            md.v56,
            marginBottom(rem(14))
        ),

        style(
            md.v64,
            marginBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            marginBottom(Keywords.auto)
        ),

        style(
            lg.px,
            marginBottom(px(1))
        ),

        style(
            lg.v0,
            marginBottom(zero())
        ),

        style(
            lg.v1,
            marginBottom(rem(0.25))
        ),

        style(
            lg.v2,
            marginBottom(rem(0.5))
        ),

        style(
            lg.v3,
            marginBottom(rem(0.75))
        ),

        style(
            lg.v4,
            marginBottom(rem(1))
        ),

        style(
            lg.v5,
            marginBottom(rem(1.25))
        ),

        style(
            lg.v6,
            marginBottom(rem(1.5))
        ),

        style(
            lg.v8,
            marginBottom(rem(2))
        ),

        style(
            lg.v10,
            marginBottom(rem(2.5))
        ),

        style(
            lg.v12,
            marginBottom(rem(3))
        ),

        style(
            lg.v16,
            marginBottom(rem(4))
        ),

        style(
            lg.v20,
            marginBottom(rem(5))
        ),

        style(
            lg.v24,
            marginBottom(rem(6))
        ),

        style(
            lg.v32,
            marginBottom(rem(8))
        ),

        style(
            lg.v40,
            marginBottom(rem(10))
        ),

        style(
            lg.v48,
            marginBottom(rem(12))
        ),

        style(
            lg.v56,
            marginBottom(rem(14))
        ),

        style(
            lg.v64,
            marginBottom(rem(16))
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            marginBottom(Keywords.auto)
        ),

        style(
            xl.px,
            marginBottom(px(1))
        ),

        style(
            xl.v0,
            marginBottom(zero())
        ),

        style(
            xl.v1,
            marginBottom(rem(0.25))
        ),

        style(
            xl.v2,
            marginBottom(rem(0.5))
        ),

        style(
            xl.v3,
            marginBottom(rem(0.75))
        ),

        style(
            xl.v4,
            marginBottom(rem(1))
        ),

        style(
            xl.v5,
            marginBottom(rem(1.25))
        ),

        style(
            xl.v6,
            marginBottom(rem(1.5))
        ),

        style(
            xl.v8,
            marginBottom(rem(2))
        ),

        style(
            xl.v10,
            marginBottom(rem(2.5))
        ),

        style(
            xl.v12,
            marginBottom(rem(3))
        ),

        style(
            xl.v16,
            marginBottom(rem(4))
        ),

        style(
            xl.v20,
            marginBottom(rem(5))
        ),

        style(
            xl.v24,
            marginBottom(rem(6))
        ),

        style(
            xl.v32,
            marginBottom(rem(8))
        ),

        style(
            xl.v40,
            marginBottom(rem(10))
        ),

        style(
            xl.v48,
            marginBottom(rem(12))
        ),

        style(
            xl.v56,
            marginBottom(rem(14))
        ),

        style(
            xl.v64,
            marginBottom(rem(16))
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector px = Css.randomDot(5);

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v1 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v3 = Css.randomDot(5);

    ClassSelector v4 = Css.randomDot(5);

    ClassSelector v5 = Css.randomDot(5);

    ClassSelector v6 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

    ClassSelector v10 = Css.randomDot(5);

    ClassSelector v12 = Css.randomDot(5);

    ClassSelector v16 = Css.randomDot(5);

    ClassSelector v20 = Css.randomDot(5);

    ClassSelector v24 = Css.randomDot(5);

    ClassSelector v32 = Css.randomDot(5);

    ClassSelector v40 = Css.randomDot(5);

    ClassSelector v48 = Css.randomDot(5);

    ClassSelector v56 = Css.randomDot(5);

    ClassSelector v64 = Css.randomDot(5);

  }

}