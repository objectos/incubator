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
public final class AlignSelf extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.dot("self-auto");

  public static final ClassSelector start = Css.dot("self-start");

  public static final ClassSelector center = Css.dot("self-center");

  public static final ClassSelector end = Css.dot("self-end");

  public static final ClassSelector stretch = Css.dot("self-stretch");

  @Override
  protected final void definition() {
    style(
        auto,
        alignSelf(Keywords.auto)
    );
    style(
        start,
        alignSelf(Keywords.flexStart)
    );
    style(
        center,
        alignSelf(Keywords.center)
    );
    style(
        end,
        alignSelf(Keywords.flexEnd)
    );
    style(
        stretch,
        alignSelf(Keywords.stretch)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            sm.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            sm.center,
            alignSelf(Keywords.center)
        ),

        style(
            sm.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            sm.stretch,
            alignSelf(Keywords.stretch)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            md.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            md.center,
            alignSelf(Keywords.center)
        ),

        style(
            md.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            md.stretch,
            alignSelf(Keywords.stretch)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            lg.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            lg.center,
            alignSelf(Keywords.center)
        ),

        style(
            lg.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            lg.stretch,
            alignSelf(Keywords.stretch)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            alignSelf(Keywords.auto)
        ),

        style(
            xl.start,
            alignSelf(Keywords.flexStart)
        ),

        style(
            xl.center,
            alignSelf(Keywords.center)
        ),

        style(
            xl.end,
            alignSelf(Keywords.flexEnd)
        ),

        style(
            xl.stretch,
            alignSelf(Keywords.stretch)
        )
    );
  }

  public interface sm {

    ClassSelector auto = Css.dot("sm-self-auto");

    ClassSelector start = Css.dot("sm-self-start");

    ClassSelector center = Css.dot("sm-self-center");

    ClassSelector end = Css.dot("sm-self-end");

    ClassSelector stretch = Css.dot("sm-self-stretch");

  }

  public interface md {

    ClassSelector auto = Css.dot("md-self-auto");

    ClassSelector start = Css.dot("md-self-start");

    ClassSelector center = Css.dot("md-self-center");

    ClassSelector end = Css.dot("md-self-end");

    ClassSelector stretch = Css.dot("md-self-stretch");

  }

  public interface lg {

    ClassSelector auto = Css.dot("lg-self-auto");

    ClassSelector start = Css.dot("lg-self-start");

    ClassSelector center = Css.dot("lg-self-center");

    ClassSelector end = Css.dot("lg-self-end");

    ClassSelector stretch = Css.dot("lg-self-stretch");

  }

  public interface xl {

    ClassSelector auto = Css.dot("xl-self-auto");

    ClassSelector start = Css.dot("xl-self-start");

    ClassSelector center = Css.dot("xl-self-center");

    ClassSelector end = Css.dot("xl-self-end");

    ClassSelector stretch = Css.dot("xl-self-stretch");

  }

}