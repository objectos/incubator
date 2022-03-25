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
public final class AlignContent extends AbstractStyleSheet {

  public static final ClassSelector start = Css.dot("content-start");

  public static final ClassSelector center = Css.dot("content-center");

  public static final ClassSelector end = Css.dot("content-end");

  public static final ClassSelector between = Css.dot("content-between");

  public static final ClassSelector around = Css.dot("content-around");

  @Override
  protected final void definition() {
    style(
        start,
        alignContent(Keywords.flexStart)
    );
    style(
        center,
        alignContent(Keywords.center)
    );
    style(
        end,
        alignContent(Keywords.flexEnd)
    );
    style(
        between,
        alignContent(Keywords.spaceBetween)
    );
    style(
        around,
        alignContent(Keywords.spaceAround)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            sm.center,
            alignContent(Keywords.center)
        ),

        style(
            sm.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            sm.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            sm.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            md.center,
            alignContent(Keywords.center)
        ),

        style(
            md.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            md.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            md.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            lg.center,
            alignContent(Keywords.center)
        ),

        style(
            lg.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            lg.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            lg.around,
            alignContent(Keywords.spaceAround)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.start,
            alignContent(Keywords.flexStart)
        ),

        style(
            xl.center,
            alignContent(Keywords.center)
        ),

        style(
            xl.end,
            alignContent(Keywords.flexEnd)
        ),

        style(
            xl.between,
            alignContent(Keywords.spaceBetween)
        ),

        style(
            xl.around,
            alignContent(Keywords.spaceAround)
        )
    );
  }

  public interface sm {

    ClassSelector start = Css.dot("sm-content-start");

    ClassSelector center = Css.dot("sm-content-center");

    ClassSelector end = Css.dot("sm-content-end");

    ClassSelector between = Css.dot("sm-content-between");

    ClassSelector around = Css.dot("sm-content-around");

  }

  public interface md {

    ClassSelector start = Css.dot("md-content-start");

    ClassSelector center = Css.dot("md-content-center");

    ClassSelector end = Css.dot("md-content-end");

    ClassSelector between = Css.dot("md-content-between");

    ClassSelector around = Css.dot("md-content-around");

  }

  public interface lg {

    ClassSelector start = Css.dot("lg-content-start");

    ClassSelector center = Css.dot("lg-content-center");

    ClassSelector end = Css.dot("lg-content-end");

    ClassSelector between = Css.dot("lg-content-between");

    ClassSelector around = Css.dot("lg-content-around");

  }

  public interface xl {

    ClassSelector start = Css.dot("xl-content-start");

    ClassSelector center = Css.dot("xl-content-center");

    ClassSelector end = Css.dot("xl-content-end");

    ClassSelector between = Css.dot("xl-content-between");

    ClassSelector around = Css.dot("xl-content-around");

  }

}