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

import br.com.objectos.css.boot.type.ColorName;
import java.util.Set;
import java.util.TreeSet;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

final class GeneratedColorStep extends ThisTemplate {

  private final Set<ColorName> colorNames = new TreeSet<>();

  GeneratedColorStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addColorName(ColorName colorName) {
    colorName.assertIdentifierIsEqualToName();

    colorNames.add(colorName);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(type);

    autoImports();

    generatedAnnotation();
    _abstract();
    _class("GeneratedColor");
    body(
      include(this::colorFields),

      _private(), _static(), _final(),
      t(t(type, "ColorName"), dim()),
      id("ARRAY"), ainit(include(this::constantNames)),

      _private(), _static(), _final(),
      t(t(UnmodifiableMap.class), t(String.class), t(type, "ColorName")),
      id("MAP"), invoke("buildMap"),

      _public(), _static(),
      t(type, "ColorName"),
      method("getByCode", _int(), id("code")),
      block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      _public(), _static(),
      t(type, "ColorName"),
      method("getByName", t(String.class), id("name")),
      block(
        _var(), id("c"), n("MAP"), invoke("get", n("name")),
        _if(n("c"), equalTo(), _null()), block(
          _throw(), _new(t(IllegalArgumentException.class), n("name"))
        ),
        _return(), n("c")
      ),

      _public(), _static(),
      _boolean(),
      method("isColor", t(String.class), id("name")),
      block(
        _return(), n("MAP"), invoke("containsKey", n("name"))
      ),

      _private(), _static(),
      t(t(UnmodifiableMap.class), t(String.class), t(type, "ColorName")),
      method("buildMap"),
      block(
        _var(), id("m"), _new(t(t(GrowableMap.class), t(String.class), t(type, "ColorName"))),
        end(),
        include(this::mapStatements),
        _return(), n("m"), invoke("toUnmodifiableMap")
      )
    );
  }

  private void colorFields() {
    int code = 0;

    for (var color : colorNames) {
      _public();
      _static();
      _final();
      t(type, "ColorName");
      id(color.identifier);
      _new(t(type, "ColorName"), i(code++), s(color.name));
    }
  }

  private void constantNames() {
    nl();

    for (var color : colorNames) {
      n(color.name);
      end();

      nl();
    }
  }

  private void mapStatements() {
    for (var color : colorNames) {
      code(n("m"), invoke("put", s(color.name), end(), n(color.identifier)), end());
    }
  }

}