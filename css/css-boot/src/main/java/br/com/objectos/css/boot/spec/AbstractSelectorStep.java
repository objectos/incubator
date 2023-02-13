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

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.css.boot.util.JavaNames;
import java.util.List;
import objectos.util.GrowableList;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

abstract class AbstractSelectorStep extends ThisTemplate {

  private record Element(String name, String fieldName) {}

  private final List<Element> elementList = new GrowableList<>();

  AbstractSelectorStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(select);

    autoImports();

    generatedAnnotation();
    _public();
    _final();
    _class(getGeneratedName());
    body(
      include(this::fields),

      _private(), _static(), _final(),
      t(t(select, getImplName()), dim()),
      id("ARRAY"), ainit(include(this::constantNames)),

      _private(), _static(), _final(),
      t(t(UnmodifiableMap.class), t(String.class), t(select, getImplName())),
      id("MAP"), invoke("buildMap"),

      _private(),
      constructor(),
      block(),

      at(t(Ignore.class)),
      _public(), _static(),
      t(select, getImplName()),
      method(
        "getByCode",
        _int(), id("code")
      ),
      block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      at(t(Ignore.class)),
      _public(), _static(),
      t(select, getImplName()),
      method(
        "getByName",
        t(String.class), id("name")
      ),
      block(
        _return(), n("MAP"), invoke("get", n("name"))
      ),

      _private(), _static(),
      t(t(UnmodifiableMap.class), t(String.class), t(select, getImplName())),
      method("buildMap"),
      block(
        _var(), id("m"), _new(t(t(GrowableMap.class), t(String.class), t(select, getImplName()))),
        end(),
        include(this::mapStatements),
        _return(), n("m"), invoke("toUnmodifiableMap")
      )
    );
  }

  final void addPseudo(String name, String fieldName) {
    var element = new Element(name, fieldName);

    elementList.add(element);
  }

  abstract String getGeneratedName();

  abstract String getImplName();

  final String toFieldName(String simpleName) {
    String fieldName;
    fieldName = simpleName.replace('-', '_').toUpperCase();

    return JavaNames.toIdentifier(fieldName);
  }

  private void constantNames() {
    nl();

    for (var element : elementList) {
      n(element.fieldName);
      end();

      nl();
    }
  }

  private void fields() {
    int code = 0;

    for (var element : elementList) {
      _public();
      _static();
      _final();
      t(select, getImplName());
      id(element.fieldName);
      _new(t(select, getImplName()), i(code++), s(element.name));
    }
  }

  private void mapStatements() {
    for (var element : elementList) {
      n("m");
      invoke("put", s(element.name), end(), n(element.fieldName));
      end();
    }
  }

}
