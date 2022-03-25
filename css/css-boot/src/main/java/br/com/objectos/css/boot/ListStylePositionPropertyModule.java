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

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class ListStylePositionPropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName inside = keyword("inside");
    KeywordName outside = keyword("outside");

    property(
        "list-style-position",

        formal(
            Source.MDN,
            "inside | outside"
        ),

        globalSig,

        sig(t("ListStylePositionValue", inside, outside), "value")
    );
  }

}