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
import br.com.objectos.css.boot.type.ValueType;

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class BorderStylePropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName dashed = keyword("dashed");
    KeywordName dotted = keyword("dotted");
    KeywordName doubleKw = keyword("double");
    KeywordName groove = keyword("groove");
    KeywordName hidden = keyword("hidden");
    KeywordName inset = keyword("inset");
    KeywordName none = keyword("none");
    KeywordName outset = keyword("outset");
    KeywordName ridge = keyword("ridge");
    KeywordName solid = keyword("solid");

    ValueType lineStyle = t(
        "LineStyleValue",
        none, hidden, dotted, dashed, solid, doubleKw, groove, ridge, inset, outset
    );

    property(
        "border-style",

        formal(
            Source.MDN,
            "<line-style>{1,4}",

            "<line-style> = none | hidden | dotted | dashed | solid | double | groove | ridge | inset | outset"
        ),

        globalSig,

        sigXY1(lineStyle),
        sigXY2(lineStyle),
        sigXY3(lineStyle),
        sigXY4(lineStyle)
    );

    property(
        names("border-top-style", "border-right-style", "border-bottom-style", "border-left-style"),

        formal(
            Source.MDN,
            "<line-style>",

            "<line-style> = none | hidden | dotted | dashed | solid | double | groove | ridge | inset | outset"
        ),

        globalSig,

        sig(lineStyle, "style")
    );
  }

}