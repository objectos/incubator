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
public final class AlignItems extends AbstractStyleSheet {

  public static final ClassSelector stretch = Css.randomDot(5);

  public static final ClassSelector start = Css.randomDot(5);

  public static final ClassSelector center = Css.randomDot(5);

  public static final ClassSelector end = Css.randomDot(5);

  public static final ClassSelector baseline = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        stretch,
        alignItems(Keywords.stretch)
    );
    style(
        start,
        alignItems(Keywords.flexStart)
    );
    style(
        center,
        alignItems(Keywords.center)
    );
    style(
        end,
        alignItems(Keywords.flexEnd)
    );
    style(
        baseline,
        alignItems(Keywords.baseline)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            sm.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            sm.center,
            alignItems(Keywords.center)
        ),

        style(
            sm.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            sm.baseline,
            alignItems(Keywords.baseline)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            md.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            md.center,
            alignItems(Keywords.center)
        ),

        style(
            md.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            md.baseline,
            alignItems(Keywords.baseline)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            lg.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            lg.center,
            alignItems(Keywords.center)
        ),

        style(
            lg.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            lg.baseline,
            alignItems(Keywords.baseline)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.stretch,
            alignItems(Keywords.stretch)
        ),

        style(
            xl.start,
            alignItems(Keywords.flexStart)
        ),

        style(
            xl.center,
            alignItems(Keywords.center)
        ),

        style(
            xl.end,
            alignItems(Keywords.flexEnd)
        ),

        style(
            xl.baseline,
            alignItems(Keywords.baseline)
        )
    );
  }

  public interface sm {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface md {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector stretch = Css.randomDot(5);

    ClassSelector start = Css.randomDot(5);

    ClassSelector center = Css.randomDot(5);

    ClassSelector end = Css.randomDot(5);

    ClassSelector baseline = Css.randomDot(5);

  }

}