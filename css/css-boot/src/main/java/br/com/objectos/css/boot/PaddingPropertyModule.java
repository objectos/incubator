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
import br.com.objectos.css.boot.spec.Source;
import br.com.objectos.css.boot.type.ValueType;

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class PaddingPropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    ValueType lengthOrPercentage = t("LengthOrPercentageValue", length, percentage);

    property(
        "padding",

        formal(
            Source.MDN,
            "[ <length> | <percentage> ]{1,4}"
        ),

        globalSig,

        sigXY1(lengthOrPercentage),
        sigXY2(lengthOrPercentage),
        sigXY3(lengthOrPercentage),
        sigXY4(lengthOrPercentage)
    );

    property(
        names("padding-top", "padding-right", "padding-bottom", "padding-left"),

        formal(
            Source.MDN,
            "<length> | <percentage>"
        ),

        globalSig,

        sig(lengthOrPercentage, "value")
    );
  }

}