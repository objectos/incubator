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
public final class TextAlign extends AbstractStyleSheet {

  public static final ClassSelector left = Css.dot("text-left");

  public static final ClassSelector center = Css.dot("text-center");

  public static final ClassSelector right = Css.dot("text-right");

  public static final ClassSelector justify = Css.dot("text-justify");

  @Override
  protected final void definition() {
    style(
        left,
        textAlign(Keywords.left)
    );
    style(
        center,
        textAlign(Keywords.center)
    );
    style(
        right,
        textAlign(Keywords.right)
    );
    style(
        justify,
        textAlign(Keywords.justify)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.left,
            textAlign(Keywords.left)
        ),

        style(
            sm.center,
            textAlign(Keywords.center)
        ),

        style(
            sm.right,
            textAlign(Keywords.right)
        ),

        style(
            sm.justify,
            textAlign(Keywords.justify)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.left,
            textAlign(Keywords.left)
        ),

        style(
            md.center,
            textAlign(Keywords.center)
        ),

        style(
            md.right,
            textAlign(Keywords.right)
        ),

        style(
            md.justify,
            textAlign(Keywords.justify)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.left,
            textAlign(Keywords.left)
        ),

        style(
            lg.center,
            textAlign(Keywords.center)
        ),

        style(
            lg.right,
            textAlign(Keywords.right)
        ),

        style(
            lg.justify,
            textAlign(Keywords.justify)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.left,
            textAlign(Keywords.left)
        ),

        style(
            xl.center,
            textAlign(Keywords.center)
        ),

        style(
            xl.right,
            textAlign(Keywords.right)
        ),

        style(
            xl.justify,
            textAlign(Keywords.justify)
        )
    );
  }

  public interface sm {

    ClassSelector left = Css.dot("sm-text-left");

    ClassSelector center = Css.dot("sm-text-center");

    ClassSelector right = Css.dot("sm-text-right");

    ClassSelector justify = Css.dot("sm-text-justify");

  }

  public interface md {

    ClassSelector left = Css.dot("md-text-left");

    ClassSelector center = Css.dot("md-text-center");

    ClassSelector right = Css.dot("md-text-right");

    ClassSelector justify = Css.dot("md-text-justify");

  }

  public interface lg {

    ClassSelector left = Css.dot("lg-text-left");

    ClassSelector center = Css.dot("lg-text-center");

    ClassSelector right = Css.dot("lg-text-right");

    ClassSelector justify = Css.dot("lg-text-justify");

  }

  public interface xl {

    ClassSelector left = Css.dot("xl-text-left");

    ClassSelector center = Css.dot("xl-text-center");

    ClassSelector right = Css.dot("xl-text-right");

    ClassSelector justify = Css.dot("xl-text-justify");

  }

}