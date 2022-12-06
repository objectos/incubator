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
package br.com.objectos.css.boot.spec;

import objectos.code.JavaModel.ArrayInitializerElement;
import objectos.util.UnmodifiableMap;

final class GeneratedColorStep extends ThisTemplate {

  CssSpecDsl spec;

  //  private final GrowableList<FieldCode> colors = new GrowableList<>();
  //  private final GrowableList<VariableInitializer> constantNames = new GrowableList<>();
  //  private final GrowableList<BlockStatement> mapStatements = new GrowableList<>();
  //
  //  @Override
  //  public final void addColorName(ColorName colorName) {
  //    colorName.assertIdentifierIsEqualToName();
  //
  //    colors.add(
  //      field(
  //        _public(), _static(), _final(), TypeNames._ColorName,
  //        init(
  //          colorName.identifier,
  //          _new(
  //            TypeNames._ColorName, getCode(), l(colorName.name)
  //          )
  //        )
  //      )
  //    );
  //
  //    constantNames.add(colorName.identifier);
  //
  //    mapStatements.add(colorName.invokePut(Ids.m));
  //  }
  //
  //  @Override
  //  public final void execute() {
  //    writeJavaFile(
  //      TypeNames.PACKAGE,
  //      classCode()
  //    );
  //  }

  @Override
  protected final void definition() {
    //    NamedClass implName;
    //    implName = TypeNames._ColorName;
    //
    //    NamedArray implArray = implName.toNamedArray();
    //
    //    UnmodifiableList<NamedClass> mapTypeArgs;
    //    mapTypeArgs = UnmodifiableList.of(Types._String, implName);
    //
    //    NamedClassOrParameterized implGrowableMap;
    //    implGrowableMap = t(Types._GrowableMap, mapTypeArgs);
    //
    //    NamedClassOrParameterized implUnmodifiableMap;
    //    implUnmodifiableMap = t(Types._UnmodifiableMap, mapTypeArgs);
    //
    _package(type);

    autoImports();

    _class(
      generatedAnnotation(),
      _abstract(), id("GeneratedColor"),

      include(this::colorFields),

      field(
        _private(), _static(), _final(), t(t(type, "ColorName"), dim()),
        id("ARRAY"), a(constantNames())
      ),

      field(
        _private(), _static(), _final(),
        t(t(UnmodifiableMap.class), t(String.class), t(type, "ColorName")),
        id("MAP"), invoke("buildMap")
      ),

      method(
        _public(), _static(), t(type, "ColorName"), id("getByCode"),
        param(_int(), id("code")),
        _return(aget(n("ARRAY"), n("code")))
      )

    //      ,
    //
    //      method(
    //        _public(), _static(), t(type, "ColorName"), id("getByName"),
    //        param(t(String.class), id("name")),
    //
    //        var("c", invoke(n("MAP"), "get", n("name"))),
    //        _if(eq(Ids.c, _null()),
    //          _throw(_new(t(IllegalArgumentException.class), Ids.name))
    //        ),
    //        _return(n("c"))
    //      ),
    //
    //      method(
    //        _public(), _static(), _boolean(), Ids.isColor,
    //        param(Types._String, Ids.name),
    //
    //        _return(invoke(Ids.MAP, "containsKey", Ids.name))
    //      ),
    //
    //      method(
    //        _private(), _static(), implUnmodifiableMap, Ids.buildMap,
    //        _var(implGrowableMap, Ids.m, Types._newGrowableMap()),
    //        statements(mapStatements),
    //        _return(invoke(Ids.m, "toUnmodifiableMap"))
    //      )
    );
  }

  private void colorFields() {

  }

  private ArrayInitializerElement[] constantNames() {
    return spec.colors()
        .stream()
        .map(name -> name.name)
        .map(this::n)
        .toArray(ArrayInitializerElement[]::new);
  }

  //  private Literal getCode() {
  //    return l(colors.size());
  //  }

}
