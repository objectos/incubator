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
package br.com.objectos.css.framework.flexbox;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class JustifyContent extends AbstractStyleSheet {

  public static final ClassSelector start = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector end = Css.randomDot(5);

  public static final ClassSelector between = Css.randomDot(5);

  public static final ClassSelector around = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        start,
        justifyContent(Keywords.flexStart)
    );
    style(
        center,
        justifyContent(Keywords.center)
    );
    style(
        end,
        justifyContent(Keywords.flexEnd)
    );
    style(
        between,
        justifyContent(Keywords.spaceBetween)
    );
    style(
        around,
        justifyContent(Keywords.spaceAround)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.start,
            justifyContent(Keywords.flexStart)
        ),

        style(
            sm.center,
            justifyContent(Keywords.center)
        ),

        style(
            sm.end,
            justifyContent(Keywords.flexEnd)
        ),

        style(
            sm.between,
            justifyContent(Keywords.spaceBetween)
        ),

        style(
            sm.around,
            justifyContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.start,
            justifyContent(Keywords.flexStart)
        ),

        style(
            md.center,
            justifyContent(Keywords.center)
        ),

        style(
            md.end,
            justifyContent(Keywords.flexEnd)
        ),

        style(
            md.between,
            justifyContent(Keywords.spaceBetween)
        ),

        style(
            md.around,
            justifyContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.start,
            justifyContent(Keywords.flexStart)
        ),

        style(
            lg.center,
            justifyContent(Keywords.center)
        ),

        style(
            lg.end,
            justifyContent(Keywords.flexEnd)
        ),

        style(
            lg.between,
            justifyContent(Keywords.spaceBetween)
        ),

        style(
            lg.around,
            justifyContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.start,
            justifyContent(Keywords.flexStart)
        ),

        style(
            xl.center,
            justifyContent(Keywords.center)
        ),

        style(
            xl.end,
            justifyContent(Keywords.flexEnd)
        ),

        style(
            xl.between,
            justifyContent(Keywords.spaceBetween)
        ),

        style(
            xl.around,
            justifyContent(Keywords.spaceAround)
        )
    );
  }

  public interface sm {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface md {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector between = Css.randomDot(5);

    ClassSelector around = Css.randomDot(5);

  }

}