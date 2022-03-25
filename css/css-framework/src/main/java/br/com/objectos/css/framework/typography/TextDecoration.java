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
package br.com.objectos.css.framework.typography;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class TextDecoration extends AbstractStyleSheet {

  public static final ClassSelector underline = Css.dot("-underline");

  public static final ClassSelector lineThrough = Css.dot("-line-through");

  public static final ClassSelector strike = Css.dot("-strike");

  public static final ClassSelector none = Css.dot("-none");

  @Override
  protected final void definition() {
    style(
        underline,
        textDecoration(Keywords.underline)
    );
    style(
        lineThrough,
        textDecoration(Keywords.lineThrough)
    );
    style(
        strike,
        textDecoration(Keywords.lineThrough)
    );
    style(
        none,
        textDecoration(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.underline,
            textDecoration(Keywords.underline)
        ),

        style(
            sm.lineThrough,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            sm.strike,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            sm.none,
            textDecoration(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.underline,
            textDecoration(Keywords.underline)
        ),

        style(
            md.lineThrough,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            md.strike,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            md.none,
            textDecoration(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.underline,
            textDecoration(Keywords.underline)
        ),

        style(
            lg.lineThrough,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            lg.strike,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            lg.none,
            textDecoration(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.underline,
            textDecoration(Keywords.underline)
        ),

        style(
            xl.lineThrough,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            xl.strike,
            textDecoration(Keywords.lineThrough)
        ),

        style(
            xl.none,
            textDecoration(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector underline = Css.dot("sm--underline");

    ClassSelector lineThrough = Css.dot("sm--line-through");

    ClassSelector strike = Css.dot("sm--strike");

    ClassSelector none = Css.dot("sm--none");

  }

  public interface md {

    ClassSelector underline = Css.dot("md--underline");

    ClassSelector lineThrough = Css.dot("md--line-through");

    ClassSelector strike = Css.dot("md--strike");

    ClassSelector none = Css.dot("md--none");

  }

  public interface lg {

    ClassSelector underline = Css.dot("lg--underline");

    ClassSelector lineThrough = Css.dot("lg--line-through");

    ClassSelector strike = Css.dot("lg--strike");

    ClassSelector none = Css.dot("lg--none");

  }

  public interface xl {

    ClassSelector underline = Css.dot("xl--underline");

    ClassSelector lineThrough = Css.dot("xl--line-through");

    ClassSelector strike = Css.dot("xl--strike");

    ClassSelector none = Css.dot("xl--none");

  }

}