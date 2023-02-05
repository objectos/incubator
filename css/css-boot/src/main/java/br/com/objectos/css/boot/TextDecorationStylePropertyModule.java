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

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class TextDecorationStylePropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName dashed = keyword("dashed");
    KeywordName dotted = keyword("dotted");
    KeywordName doubleKw = keyword("double");
    KeywordName solid = keyword("solid");
    KeywordName wavy = keyword("wavy");

    property(
        "text-decoration-style",

        formal(
            Source.MDN,
            "solid | double | dotted | dashed | wavy"
        ),

        globalSig,

        sig(t("TextDecorationStyleValue", dashed, dotted, doubleKw, solid, wavy), "style")
    );
  }

}