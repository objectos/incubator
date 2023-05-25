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

import br.com.objectos.css.boot.spec.KeywordName;
import br.com.objectos.css.boot.spec.Source;

@DoNotOverwrite
final class WhiteSpacePropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName breakSpaces = keyword("break-spaces");
    KeywordName normal = keyword("normal");
    KeywordName nowrap = keyword("nowrap");
    KeywordName pre = keyword("pre");
    KeywordName preLine = keyword("pre-line");
    KeywordName preWrap = keyword("pre-wrap");

    property(
      "white-space",

      formal(
        Source.MDN,
        "normal | pre | nowrap | pre-wrap | pre-line | break-spaces"
      ),

      globalSig,

      sig(t("WhiteSpaceValue", normal, pre, nowrap, preWrap, preLine, breakSpaces), "value")
    );
  }

}