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
package br.com.objectos.css.boot;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.spec.Source;
import br.com.objectos.css.boot.type.JavaType;
import br.com.objectos.css.boot.type.ValueType;

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class FontFamilyPropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName cursive = keyword("cursive");
    KeywordName fantasy = keyword("fantasy");
    KeywordName monospace = keyword("monospace");
    KeywordName sansSerif = keyword("sans-serif");
    KeywordName serif = keyword("serif");

    ValueType fontFamily = t(
        "FontFamilyValue",
        serif, sansSerif, cursive, fantasy, monospace,
        string
    );

    propertyHash(
        "font-family",

        formal(
            Source.MDN,
            "[ <family-name> | <generic-family> ]#",

            "<family-name> = <string> | <custom-ident>+",
            "<generic-family> = serif | sans-serif | cursive | fantasy | monospace"
        ),

        globalSig,

        sig(fontFamily, "family"),

        sig(JavaType.STRING, "family"),

        sigHash()
    );
  }

}