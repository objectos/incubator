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

  public static final ClassSelector bottom = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector left = Css.randomDot(5);

  public static final ClassSelector leftBottom = Css.randomDot(5);

  public static final ClassSelector leftTop = Css.randomDot(5);

  public static final ClassSelector right = Css.randomDot(5);

  public static final ClassSelector rightBottom = Css.randomDot(5);

  public static final ClassSelector rightTop = Css.randomDot(5);

  public static final ClassSelector top = Css.randomDot(5);

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

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface md {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector bottom = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector left = Css.randomDot(5);

    ClassSelector leftBottom = Css.randomDot(5);

    ClassSelector leftTop = Css.randomDot(5);

    ClassSelector right = Css.randomDot(5);

    ClassSelector rightBottom = Css.randomDot(5);

    ClassSelector rightTop = Css.randomDot(5);

    ClassSelector top = Css.randomDot(5);

  }

}