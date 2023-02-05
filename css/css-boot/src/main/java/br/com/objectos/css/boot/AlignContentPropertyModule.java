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
import br.com.objectos.css.boot.spec.Source;
import br.com.objectos.css.boot.type.ValueType;

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class AlignContentPropertyModule extends AbstractAlignOrJustifyPropertyModule {

  @Override
  final void propertyDefinitionImpl() {
    ValueType alignContent = t(
        "AlignContentValue",
        normal, baseline, contentDistribution, contentPosition
    );

    property(
        "align-content",

        formal(
            Source.MDN,
            "normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position>",

            "<baseline-position> = [ first | last ]? baseline",
            "<content-distribution> = space-between | space-around | space-evenly | stretch",
            "<overflow-position> = unsafe | safe",
            "<content-position> = center | start | end | flex-start | flex-end"
        ),

        globalSig,

        sig(alignContent, "value"),
        sig(baselinePosition, "firstOrLast", baseline, "baseline"),
        sig(overflowPosition, "safeOrUnsafe", contentPosition, "position")
    );
  }

}