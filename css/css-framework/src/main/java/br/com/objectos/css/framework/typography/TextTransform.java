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
public final class TextTransform extends AbstractStyleSheet {

  public static final ClassSelector uppercase = Css.dot("-uppercase");

  public static final ClassSelector lowercase = Css.dot("-lowercase");

  public static final ClassSelector capitalize = Css.dot("-capitalize");

  public static final ClassSelector none = Css.dot("-none");

  @Override
  protected final void definition() {
    style(
        uppercase,
        textTransform(Keywords.uppercase)
    );
    style(
        lowercase,
        textTransform(Keywords.lowercase)
    );
    style(
        capitalize,
        textTransform(Keywords.capitalize)
    );
    style(
        none,
        textTransform(Keywords.none)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.uppercase,
            textTransform(Keywords.uppercase)
        ),

        style(
            sm.lowercase,
            textTransform(Keywords.lowercase)
        ),

        style(
            sm.capitalize,
            textTransform(Keywords.capitalize)
        ),

        style(
            sm.none,
            textTransform(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.uppercase,
            textTransform(Keywords.uppercase)
        ),

        style(
            md.lowercase,
            textTransform(Keywords.lowercase)
        ),

        style(
            md.capitalize,
            textTransform(Keywords.capitalize)
        ),

        style(
            md.none,
            textTransform(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.uppercase,
            textTransform(Keywords.uppercase)
        ),

        style(
            lg.lowercase,
            textTransform(Keywords.lowercase)
        ),

        style(
            lg.capitalize,
            textTransform(Keywords.capitalize)
        ),

        style(
            lg.none,
            textTransform(Keywords.none)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.uppercase,
            textTransform(Keywords.uppercase)
        ),

        style(
            xl.lowercase,
            textTransform(Keywords.lowercase)
        ),

        style(
            xl.capitalize,
            textTransform(Keywords.capitalize)
        ),

        style(
            xl.none,
            textTransform(Keywords.none)
        )
    );
  }

  public interface sm {

    ClassSelector uppercase = Css.dot("sm--uppercase");

    ClassSelector lowercase = Css.dot("sm--lowercase");

    ClassSelector capitalize = Css.dot("sm--capitalize");

    ClassSelector none = Css.dot("sm--none");

  }

  public interface md {

    ClassSelector uppercase = Css.dot("md--uppercase");

    ClassSelector lowercase = Css.dot("md--lowercase");

    ClassSelector capitalize = Css.dot("md--capitalize");

    ClassSelector none = Css.dot("md--none");

  }

  public interface lg {

    ClassSelector uppercase = Css.dot("lg--uppercase");

    ClassSelector lowercase = Css.dot("lg--lowercase");

    ClassSelector capitalize = Css.dot("lg--capitalize");

    ClassSelector none = Css.dot("lg--none");

  }

  public interface xl {

    ClassSelector uppercase = Css.dot("xl--uppercase");

    ClassSelector lowercase = Css.dot("xl--lowercase");

    ClassSelector capitalize = Css.dot("xl--capitalize");

    ClassSelector none = Css.dot("xl--none");

  }

}