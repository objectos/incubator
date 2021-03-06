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
final class ResizePropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName block = keyword("block");
    KeywordName both = keyword("both");
    KeywordName horizontal = keyword("horizontal");
    KeywordName inline = keyword("inline");
    KeywordName none = keyword("none");
    KeywordName vertical = keyword("vertical");

    property(
        "resize",

        formal(
            Source.MDN,
            "none | both | horizontal | vertical | block | inline"
        ),

        globalSig,

        sig(t("ResizeValue", block, both, horizontal, inline, none, vertical), "value")
    );
  }

}