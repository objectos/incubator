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
import java.util.List;
import objectos.util.GrowableList;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

final class KeywordsClassStep extends ThisTemplate {

  private final List<KeywordName> keywordList = new GrowableList<>();

  public KeywordsClassStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addKeyword(KeywordName keyword) {
    keywordList.add(keyword);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(keyword);

    autoImports();

    generatedAnnotation();
    _public();
    _final();
    _class("Keywords");
    body(
      include(this::keywords),

      _private(), _static(), _final(),
      t(t(keyword, "StandardKeyword"), dim()),
      id("ARRAY"), ainit(include(this::constants)),

      _private(), _static(), _final(),
      t(t(UnmodifiableMap.class), t(String.class), t(keyword, "StandardKeyword")),
      id("MAP"), invoke("buildMap"),

      _private(), constructor(), block(),

      _public(), _static(),
      t(keyword, "StandardKeyword"),
      method("getByCode", _int(), id("code")),
      block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      _public(), _static(),
      t(keyword, "StandardKeyword"),
      method("getByName", t(String.class), id("name")),
      block(
        _var(), id("k"), n("MAP"), invoke("get", n("name")),
        _if(n("k"), equalTo(), _null()), block(
          _throw(), _new(t(IllegalArgumentException.class), n("name"))
        ),
        _return(), n("k")
      ),

      _public(), _static(),
      _boolean(),
      method("isKeyword", t(String.class), id("name")), block(
        _return(), n("MAP"), invoke("containsKey", n("name"))
      ),

      _private(), _static(),
      t(t(UnmodifiableMap.class), t(String.class), t(keyword, "StandardKeyword")),
      method("buildMap"), block(
        _var(), id("m"),
        _new(t(t(GrowableMap.class), t(String.class), t(keyword, "StandardKeyword"))), end(),
        include(this::mapStatements),
        _return(), n("m"), invoke("toUnmodifiableMap")
      )
    );
  }

  private void mapStatements() {
    for (var kw : keywordList) {
      n("m");

      invoke("put", s(kw.name), end(), n(kw.fieldName.name()));

      end();
    }
  }

  private void constants() {
    nl();

    for (var kw : keywordList) {
      n(kw.fieldName.name());
      end();

      nl();
    }
  }

  private void keywords() {
    for (var kw : keywordList) {
      _public();
      _static();
      _final();
      t(keyword, kw.simpleName);
      id(kw.fieldName.name());
      t(keyword, kw.simpleName);
      n("INSTANCE");
    }
  }

}
