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

import br.com.objectos.css.boot.property.Property;
import java.util.Map;
import java.util.TreeMap;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

final class StandardPropertyNameStep extends ThisTemplate {

  private final Map<String, Property> propertyMap = new TreeMap<>();

  public StandardPropertyNameStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addProperty(Property property) {
    var key = property.getName();

    propertyMap.put(key, property);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(property);

    autoImports();

    generatedAnnotation();
    _public();
    _enum("StandardPropertyName");
    _implements();
    t(property, "PropertyName");
    body(
      include(this::constants),

      _private(), _static(), _final(),
      t(t(property, "StandardPropertyName"), dim()),
      id("ARRAY"), t(property, "StandardPropertyName"), invoke("values"),

      _private(), _static(), _final(),
      t(t(UnmodifiableMap.class), t(String.class), t(property, "StandardPropertyName")),
      id("MAP"), invoke("buildMap"),

      _private(), _final(),
      t(String.class), id("javaName"),

      _private(), _final(),
      t(String.class), id("name"),

      _private(), constructor(
        t(String.class), id("javaName"),
        t(String.class), id("name")
      ), block(
        _this(), n("javaName"), gets(), n("javaName"),
        _this(), n("name"), gets(), n("name")
      ),

      _public(), _static(),
      t(property, "StandardPropertyName"),
      method(
        "getByCode",
        _int(), id("code")
      ),
      block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      _public(), _static(),
      t(property, "StandardPropertyName"),
      method(
        "getByName",
        t(String.class), id("name")
      ),
      block(
        _return(), n("MAP"), invoke("get", n("name"))
      ),

      _private(), _static(),
      t(t(UnmodifiableMap.class), t(String.class), t(property, "StandardPropertyName")),
      method("buildMap"),
      block(
        _var(), id("m"),
        _new(t(t(GrowableMap.class), t(String.class), t(property, "StandardPropertyName"))), end(),
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

  private void constants() {
    for (var property : propertyMap.values()) {
      var enumName = property.getEnumName();

      var methodName = property.getMethodName();

      enumConstant(enumName.name(), s(methodName.name()), s(property.name));
    }
  }

  private void mapStatements() {
    for (var property : propertyMap.values()) {
      var enumName = property.getEnumName();

      n("m");
      invoke("put", s(property.name), end(), n(enumName.name()));
      end();
    }
  }

}
