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
final class TextDecorationLinePropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName blink = keyword("blink");
    KeywordName grammarError = keyword("grammar-error");
    KeywordName lineThrough = keyword("line-through");
    KeywordName none = keyword("none");
    KeywordName overline = keyword("overline");
    KeywordName spellingError = keyword("spelling-error");
    KeywordName underline = keyword("underline");

    ValueType kind = t("TextDecorationLineKind", underline, overline, lineThrough, blink);
    ValueType value = t("TextDecorationLineValue", none, kind, spellingError, grammarError);

    property(
        "text-decoration-line",

        formal(
            Source.MDN,
            "none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error"
        ),

        globalSig,

        sig(value, "value"),
        sig(kind, "value1", kind, "value2"),
        sig(kind, "value1", kind, "value2", kind, "value3")
    );
  }

}