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
package br.com.objectos.css.framework.background;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BackgroundPosition extends AbstractStyleSheet {

  public static final ClassSelector bottom = Css.dot("bg-bottom");

  public static final ClassSelector center = Css.dot("bg-center");

  public static final ClassSelector left = Css.dot("bg-left");

  public static final ClassSelector leftBottom = Css.dot("bg-left-bottom");

  public static final ClassSelector leftTop = Css.dot("bg-left-top");

  public static final ClassSelector right = Css.dot("bg-right");

  public static final ClassSelector rightBottom = Css.dot("bg-right-bottom");

  public static final ClassSelector rightTop = Css.dot("bg-right-top");

  public static final ClassSelector top = Css.dot("bg-top");

  @Override
  protected final void definition() {
    style(
        bottom,
        backgroundPosition(Keywords.bottom)
    );
    style(
        center,
        backgroundPosition(Keywords.center)
    );
    style(
        left,
        backgroundPosition(Keywords.left)
    );
    style(
        leftBottom,
        backgroundPosition(Keywords.left, Keywords.bottom)
    );
    style(
        leftTop,
        backgroundPosition(Keywords.left, Keywords.top)
    );
    style(
        right,
        backgroundPosition(Keywords.right)
    );
    style(
        rightBottom,
        backgroundPosition(Keywords.right, Keywords.bottom)
    );
    style(
        rightTop,
        backgroundPosition(Keywords.right, Keywords.top)
    );
    style(
        top,
        backgroundPosition(Keywords.top)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            sm.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            sm.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            sm.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            sm.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            sm.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            sm.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            sm.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            sm.top,
            backgroundPosition(Keywords.top)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            md.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            md.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            md.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            md.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            md.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            md.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            md.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            md.top,
            backgroundPosition(Keywords.top)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            lg.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            lg.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            lg.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            lg.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            lg.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            lg.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            lg.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            lg.top,
            backgroundPosition(Keywords.top)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.bottom,
            backgroundPosition(Keywords.bottom)
        ),

        style(
            xl.center,
            backgroundPosition(Keywords.center)
        ),

        style(
            xl.left,
            backgroundPosition(Keywords.left)
        ),

        style(
            xl.leftBottom,
            backgroundPosition(Keywords.left, Keywords.bottom)
        ),

        style(
            xl.leftTop,
            backgroundPosition(Keywords.left, Keywords.top)
        ),

        style(
            xl.right,
            backgroundPosition(Keywords.right)
        ),

        style(
            xl.rightBottom,
            backgroundPosition(Keywords.right, Keywords.bottom)
        ),

        style(
            xl.rightTop,
            backgroundPosition(Keywords.right, Keywords.top)
        ),

        style(
            xl.top,
            backgroundPosition(Keywords.top)
        )
    );
  }

  public interface sm {

    ClassSelector bottom = Css.dot("sm-bg-bottom");

    ClassSelector center = Css.dot("sm-bg-center");

    ClassSelector left = Css.dot("sm-bg-left");

    ClassSelector leftBottom = Css.dot("sm-bg-left-bottom");

    ClassSelector leftTop = Css.dot("sm-bg-left-top");

    ClassSelector right = Css.dot("sm-bg-right");

    ClassSelector rightBottom = Css.dot("sm-bg-right-bottom");

    ClassSelector rightTop = Css.dot("sm-bg-right-top");

    ClassSelector top = Css.dot("sm-bg-top");

  }

  public interface md {

    ClassSelector bottom = Css.dot("md-bg-bottom");

    ClassSelector center = Css.dot("md-bg-center");

    ClassSelector left = Css.dot("md-bg-left");

    ClassSelector leftBottom = Css.dot("md-bg-left-bottom");

    ClassSelector leftTop = Css.dot("md-bg-left-top");

    ClassSelector right = Css.dot("md-bg-right");

    ClassSelector rightBottom = Css.dot("md-bg-right-bottom");

    ClassSelector rightTop = Css.dot("md-bg-right-top");

    ClassSelector top = Css.dot("md-bg-top");

  }

  public interface lg {

    ClassSelector bottom = Css.dot("lg-bg-bottom");

    ClassSelector center = Css.dot("lg-bg-center");

    ClassSelector left = Css.dot("lg-bg-left");

    ClassSelector leftBottom = Css.dot("lg-bg-left-bottom");

    ClassSelector leftTop = Css.dot("lg-bg-left-top");

    ClassSelector right = Css.dot("lg-bg-right");

    ClassSelector rightBottom = Css.dot("lg-bg-right-bottom");

    ClassSelector rightTop = Css.dot("lg-bg-right-top");

    ClassSelector top = Css.dot("lg-bg-top");

  }

  public interface xl {

    ClassSelector bottom = Css.dot("xl-bg-bottom");

    ClassSelector center = Css.dot("xl-bg-center");

    ClassSelector left = Css.dot("xl-bg-left");

    ClassSelector leftBottom = Css.dot("xl-bg-left-bottom");

    ClassSelector leftTop = Css.dot("xl-bg-left-top");

    ClassSelector right = Css.dot("xl-bg-right");

    ClassSelector rightBottom = Css.dot("xl-bg-right-bottom");

    ClassSelector rightTop = Css.dot("xl-bg-right-top");

    ClassSelector top = Css.dot("xl-bg-top");

  }

}