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
package br.com.objectos.css.boot.spec;

import br.com.objectos.css.boot.keyword.KeywordName;

final class KeywordClassStep extends ThisTemplate {

  private int code;

  private KeywordName keywordName;

  public KeywordClassStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addKeyword(KeywordName keyword) {
    keywordName = keyword;

    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(keyword);

    autoImports();

    generatedAnnotation();
    _public();
    _final();
    _class(keywordName.simpleName);
    _extends();
    t(keyword, "StandardKeyword");
    superInterfaces();
    body(
      _static(), _final(), t(keyword, keywordName.simpleName),
      id("INSTANCE"), _new(t(keyword, keywordName.simpleName)),

      _private(), constructor(), block(
        _super(i(code++), s(keywordName.fieldName), s(keywordName.name))
      )
    );
  }

  private void superInterfaces() {
    var names = keywordName.interfaceSet;

    if (names.isEmpty()) {
      return;
    }

    _implements();

    for (var name : names) {
      t(type, name);
    }
  }

}
