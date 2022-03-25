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
package br.com.objectos.css.framework.border;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BorderStyle extends AbstractStyleSheet {

  public static final ClassSelector solid = Css.dot("border-solid");

  public static final ClassSelector dashed = Css.dot("border-dashed");

  public static final ClassSelector dotted = Css.dot("border-dotted");

  public static final ClassSelector doubleValue = Css.dot("border-double");

  public static final ClassSelector none = Css.dot("border-none");

  @Override
  protected final void definition() {
    style(
        solid,
        borderStyle(Keywords.solid)
    );
    style(
        dashed,
        borderStyle(Keywords.dashed)
    );
    style(
        dotted,
        borderStyle(Keywords.dotted)
    );
    style(
        doubleValue,
        borderStyle(Keywords.doubleKw)
    );
    style(
        none,
        borderStyle(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            sm.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            sm.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            sm.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            sm.none,
            borderStyle(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            md.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            md.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            md.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            md.none,
            borderStyle(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            lg.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            lg.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            lg.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            lg.none,
            borderStyle(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.solid,
            borderStyle(Keywords.solid)
        ),

        style(
            xl.dashed,
            borderStyle(Keywords.dashed)
        ),

        style(
            xl.dotted,
            borderStyle(Keywords.dotted)
        ),

        style(
            xl.doubleValue,
            borderStyle(Keywords.doubleKw)
        ),

        style(
            xl.none,
            borderStyle(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector solid = Css.dot("sm-border-solid");

    ClassSelector dashed = Css.dot("sm-border-dashed");

    ClassSelector dotted = Css.dot("sm-border-dotted");

    ClassSelector doubleValue = Css.dot("sm-border-double");

    ClassSelector none = Css.dot("sm-border-none");

  }

  public interface md {

    ClassSelector solid = Css.dot("md-border-solid");

    ClassSelector dashed = Css.dot("md-border-dashed");

    ClassSelector dotted = Css.dot("md-border-dotted");

    ClassSelector doubleValue = Css.dot("md-border-double");

    ClassSelector none = Css.dot("md-border-none");

  }

  public interface lg {

    ClassSelector solid = Css.dot("lg-border-solid");

    ClassSelector dashed = Css.dot("lg-border-dashed");

    ClassSelector dotted = Css.dot("lg-border-dotted");

    ClassSelector doubleValue = Css.dot("lg-border-double");

    ClassSelector none = Css.dot("lg-border-none");

  }

  public interface xl {

    ClassSelector solid = Css.dot("xl-border-solid");

    ClassSelector dashed = Css.dot("xl-border-dashed");

    ClassSelector dotted = Css.dot("xl-border-dotted");

    ClassSelector doubleValue = Css.dot("xl-border-double");

    ClassSelector none = Css.dot("xl-border-none");

  }

}