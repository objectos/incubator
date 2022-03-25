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
package br.com.objectos.css.framework.layout;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Float extends AbstractStyleSheet {

  public static final ClassSelector left = Css.dot("float-left");

  public static final ClassSelector right = Css.dot("float-right");

  public static final ClassSelector none = Css.dot("float-none");

  @Override
  protected final void definition() {
    style(
        left,
        floatTo(Keywords.left)
    );
    style(
        right,
        floatTo(Keywords.right)
    );
    style(
        none,
        floatTo(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.left,
            floatTo(Keywords.left)
        ),

        style(
            sm.right,
            floatTo(Keywords.right)
        ),

        style(
            sm.none,
            floatTo(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.left,
            floatTo(Keywords.left)
        ),

        style(
            md.right,
            floatTo(Keywords.right)
        ),

        style(
            md.none,
            floatTo(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.left,
            floatTo(Keywords.left)
        ),

        style(
            lg.right,
            floatTo(Keywords.right)
        ),

        style(
            lg.none,
            floatTo(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.left,
            floatTo(Keywords.left)
        ),

        style(
            xl.right,
            floatTo(Keywords.right)
        ),

        style(
            xl.none,
            floatTo(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector left = Css.dot("sm-float-left");

    ClassSelector right = Css.dot("sm-float-right");

    ClassSelector none = Css.dot("sm-float-none");

  }

  public interface md {

    ClassSelector left = Css.dot("md-float-left");

    ClassSelector right = Css.dot("md-float-right");

    ClassSelector none = Css.dot("md-float-none");

  }

  public interface lg {

    ClassSelector left = Css.dot("lg-float-left");

    ClassSelector right = Css.dot("lg-float-right");

    ClassSelector none = Css.dot("lg-float-none");

  }

  public interface xl {

    ClassSelector left = Css.dot("xl-float-left");

    ClassSelector right = Css.dot("xl-float-right");

    ClassSelector none = Css.dot("xl-float-none");

  }

}