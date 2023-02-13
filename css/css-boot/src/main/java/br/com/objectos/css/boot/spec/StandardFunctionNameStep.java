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

import java.util.Map;
import java.util.TreeMap;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

final class StandardFunctionNameStep extends ThisTemplate {

  private final Map<String, FunctionName> nameMap = new TreeMap<>();

  public StandardFunctionNameStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addFunction(FunctionName function) {
    var key = function.getName();

    nameMap.put(key, function);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(function);

    autoImports();

    generatedAnnotation();

    _public();
    _enum("StandardFunctionName");
    _implements();
    t(function, "FunctionName");
    body(
      include(this::enumConstants),

      _private(), _static(), _final(),
      t(t(function, "StandardFunctionName"), dim()),
      id("ARRAY"), t(function, "StandardFunctionName"), invoke("values"),

      _private(), _static(), _final(),
      t(t(UnmodifiableMap.class), t(String.class), t(function, "StandardFunctionName")),
      id("MAP"), invoke("buildMap"),

      _private(), _final(),
      t(String.class),
      id("javaName"),

      _private(), _final(),
      t(String.class),
      id("name"),

      _private(), constructor(
        t(String.class), id("javaName"),
        t(String.class), id("name")
      ), block(
        _this(), n("javaName"), gets(), n("javaName"),
        _this(), n("name"), gets(), n("name")
      ),

      _public(), _static(),
      t(function, "StandardFunctionName"),
      method("getByCode", _int(), id("code")),
      block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      _public(), _static(),
      t(function, "StandardFunctionName"),
      method("getByName", t(String.class), id("name")),
      block(
        _return(), n("MAP"), invoke("get", n("name"))
      ),

      _private(), _static(),
      t(t(UnmodifiableMap.class), t(String.class), t(function, "StandardFunctionName")),
      method("buildMap"),
      block(
        _var(), id("m"),
        _new(t(t(GrowableMap.class), t(String.class), t(function, "StandardFunctionName"))), end(),
        include(this::mapStatements),
        _return(), n("m"), invoke("toUnmodifiableMap")
      ),

      _public(), _static(), _int(), method("size"),
      block(
        _return(), n("ARRAY"), n("length")
      ),

      at(t(Override.class)),
      _public(), _final(), _int(), method("getCode"),
      block(
        _return(), invoke("ordinal")
      ),

      _public(), _final(), t(String.class), method("getJavaName"),
      block(
        _return(), n("javaName")
      ),

      at(t(Override.class)),
      _public(), _final(), t(String.class), method("getName"),
      block(
        _return(), n("name")
      )
    );
  }

  private void enumConstants() {
    for (var name : nameMap.values()) {
      enumConstant(name.enumName(), s(name.methodName()), s(name.getName()));
    }
  }

  private void mapStatements() {
    for (var name : nameMap.values()) {
      n("m");
      invoke("put", s(name.getName()), end(), n(name.enumName()));
      end();
    }
  }

}
