/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
final class JustifyItemsPropertyModule extends AbstractAlignOrJustifyPropertyModule {

  @Override
  final void propertyDefinitionImpl() {
    KeywordName legacy = keyword("legacy");

    ValueType justifyItems = t(
        "JustifyItemsValue",
        normal, stretch, baseline, selfPositionOrLeftOrRight, legacy
    );

    ValueType justifyLegacy = t(
        "JustifyLegacyValue",
        left, right, center
    );

    property(
        "justify-items",

        formal(
            Source.MDN,
            "normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ] | legacy | legacy && [ left | right | center ]",

            "<baseline-position> = [ first | last ]? baseline",
            "<overflow-position> = unsafe | safe",
            "<self-position> = center | start | end | self-start | self-end | flex-start | flex-end"
        ),

        globalSig,

        sig(justifyItems, "value"),
        sig(baselinePosition, "firstOrLast", baseline, "baseline"),
        sig(overflowPosition, "safeOrUnsafe", selfPosition, "position"),
        sig(legacy, "legacy", justifyLegacy, "value")
    );
  }

}