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
package br.com.objectos.css.boot.type;

import static br.com.objectos.code.java.Java._enum;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._this;
import static br.com.objectos.code.java.Java.a;
import static br.com.objectos.code.java.Java.arrayAccess;
import static br.com.objectos.code.java.Java.assign;
import static br.com.objectos.code.java.Java.chain;
import static br.com.objectos.code.java.Java.constructor;
import static br.com.objectos.code.java.Java.enumConstant;
import static br.com.objectos.code.java.Java.enumConstants;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.fieldAccess;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.EnumCode;
import br.com.objectos.code.java.declaration.EnumConstantCode;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.spec.Ids;
import br.com.objectos.css.boot.spec.Step;
import br.com.objectos.css.boot.spec.StepAdapter;
import br.com.objectos.css.boot.spec.Types;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class LengthUnitStep extends Step {

  private final Map<String, EnumConstantCode> constants = new TreeMap<>();

  public LengthUnitStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addLengthUnit(String unit) {
    String simpleName;
    simpleName = unit.toUpperCase();

    Identifier enumName;
    enumName = id(simpleName);

    constants.put(
      unit,
      enumConstant(enumName)
    );
  }

  @Override
  public final void execute() {
    writeJavaFile(
      TypeNames.PACKAGE,
      enumCode()
    );
  }

  private EnumCode enumCode() {
    return _enum(
      CssBoot.GENERATED,
      _public(), TypeNames._LengthUnit,

      enumConstants(constants.values()),

      field(
        _private(), _static(), _final(),
        a(TypeNames._LengthUnit),
        init(Ids.ARRAY, TypeNames._LengthUnit.invoke("values"))
      ),

      field(_private(), _final(), Types._String, Ids.name),

      constructor(
        _private(),
        assign(
          fieldAccess(_this(), Ids.name),
          chain(
            invoke("name"),
            invoke("toLowerCase", t(Locale.class).id("US"))
          )
        )
      ),

      method(
        _public(), _static(), TypeNames._LengthUnit, Ids.getByCode,
        param(_int(), Ids.code),
        _return(arrayAccess(Ids.ARRAY, Ids.code))
      ),

      method(
        _public(), _static(), _int(), Ids.size,
        _return(Ids.ARRAY.id(Ids.length))
      ),

      method(
        _public(), _final(), _int(), Ids.getCode,
        _return(invoke("ordinal"))
      ),

      method(
        _public(), _final(), Types._String, Ids.getName,
        _return(Ids.name)
      )
    );
  }

}
