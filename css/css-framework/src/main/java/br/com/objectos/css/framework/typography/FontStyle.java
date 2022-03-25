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
public final class FontStyle extends AbstractStyleSheet {

  public static final ClassSelector italic = Css.dot("fs-italic");

  public static final ClassSelector normal = Css.dot("fs-normal");

  @Override
  protected final void definition() {
    style(
        italic,
        fontStyle(Keywords.italic)
    );
    style(
        normal,
        fontStyle(Keywords.normal)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            sm.normal,
            fontStyle(Keywords.normal)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            md.normal,
            fontStyle(Keywords.normal)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            lg.normal,
            fontStyle(Keywords.normal)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.italic,
            fontStyle(Keywords.italic)
        ),

        style(
            xl.normal,
            fontStyle(Keywords.normal)
        )
    );
  }

  public interface sm {

    ClassSelector italic = Css.dot("sm-fs-italic");

    ClassSelector normal = Css.dot("sm-fs-normal");

  }

  public interface md {

    ClassSelector italic = Css.dot("md-fs-italic");

    ClassSelector normal = Css.dot("md-fs-normal");

  }

  public interface lg {

    ClassSelector italic = Css.dot("lg-fs-italic");

    ClassSelector normal = Css.dot("lg-fs-normal");

  }

  public interface xl {

    ClassSelector italic = Css.dot("xl-fs-italic");

    ClassSelector normal = Css.dot("xl-fs-normal");

  }

}