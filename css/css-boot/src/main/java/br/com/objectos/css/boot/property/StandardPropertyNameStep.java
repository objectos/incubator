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
package br.com.objectos.css.boot.property;

import static br.com.objectos.code.java.Java._enum;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._implements;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._this;
import static br.com.objectos.code.java.Java._var;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.args;
import static br.com.objectos.code.java.Java.arrayAccess;
import static br.com.objectos.code.java.Java.assign;
import static br.com.objectos.code.java.Java.constructor;
import static br.com.objectos.code.java.Java.enumConstant;
import static br.com.objectos.code.java.Java.enumConstants;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.fieldAccess;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.statements;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.EnumCode;
import br.com.objectos.code.java.declaration.EnumConstantCode;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.Literal;
import br.com.objectos.code.java.statement.BlockStatement;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedClassOrParameterized;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.spec.Ids;
import br.com.objectos.css.boot.spec.AbstractStep;
import br.com.objectos.css.boot.spec.StepAdapter;
import br.com.objectos.css.boot.spec.Types;
import java.util.Map;
import java.util.TreeMap;
import objectos.util.UnmodifiableList;

public class StandardPropertyNameStep extends AbstractStep {

  private final Map<String, EnumConstantCode> constants = new TreeMap<>();

  private final Map<String, BlockStatement> mapStatements = new TreeMap<>();

  public StandardPropertyNameStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addProperty(Property property) {
    String key;
    key = property.getName();

    Identifier enumName;
    enumName = property.getEnumName();

    Identifier methodName;
    methodName = property.getMethodName();

    Literal javaName;
    javaName = l(methodName.name());

    Literal nameLiteral;
    nameLiteral = property.getNameLiteral();

    constants.put(
      key,
      enumConstant(enumName, args(javaName, nameLiteral))
    );

    mapStatements.put(
      key,
      invoke(Ids.m, "put", property.getNameLiteral(), enumName)
    );
  }

  @Override
  public final void execute() {
    writeJavaFile(
      PropertyNames.PACKAGE,
      enumCode()
    );
  }

  private EnumCode enumCode() {
    NamedClass implName;
    implName = PropertyNames.StandardPropertyName;

    NamedArray implArray;
    implArray = implName.toNamedArray();

    UnmodifiableList<NamedClass> mapTypeArgs;
    mapTypeArgs = UnmodifiableList.of(Types._String, implName);

    NamedClassOrParameterized implGrowableMap;
    implGrowableMap = t(Types._GrowableMap, mapTypeArgs);

    NamedClassOrParameterized implUnmodifiableMap;
    implUnmodifiableMap = t(Types._UnmodifiableMap, mapTypeArgs);

    return _enum(
      CssBoot.GENERATED,
      _public(), PropertyNames.StandardPropertyName, _implements(PropertyNames.PropertyName),

      enumConstants(constants.values()),

      field(
        _private(), _static(), _final(),
        implArray,
        init(Ids.ARRAY, PropertyNames.StandardPropertyName.invoke("values"))
      ),

      field(
        _private(), _static(), _final(), implUnmodifiableMap,
        init(Ids.MAP, invoke(Ids.buildMap.name()))
      ),

      field(_private(), _final(), Types._String, Ids.javaName),

      field(_private(), _final(), Types._String, Ids.name),

      constructor(
        _private(),
        param(Types._String, Ids.javaName),
        param(Types._String, Ids.name),
        assign(fieldAccess(_this(), Ids.javaName), Ids.javaName),
        assign(fieldAccess(_this(), Ids.name), Ids.name)
      ),

      method(
        _public(), _static(), PropertyNames.StandardPropertyName, Ids.getByCode,
        param(_int(), Ids.code),
        _return(arrayAccess(Ids.ARRAY, Ids.code))
      ),

      method(
        _public(), _static(), implName, Ids.getByName,
        param(Types._String, Ids.name),
        _return(invoke(Ids.MAP, "get", Ids.name))
      ),

      method(
        _private(), _static(), implUnmodifiableMap, Ids.buildMap,
        _var(implGrowableMap, Ids.m, Types._newGrowableMap()),
        statements(mapStatements.values()),
        _return(invoke(Ids.m, "toUnmodifiableMap"))
      ),

      method(
        _public(), _static(), _int(), Ids.size,
        _return(Ids.ARRAY.id(Ids.length))
      ),

      method(
        annotation(Override.class),
        _public(), _final(), _int(), Ids.getCode,
        _return(invoke("ordinal"))
      ),

      method(
        _public(), _final(), Types._String, Ids.getJavaName,
        _return(Ids.javaName)
      ),

      method(
        annotation(Override.class),
        _public(), _final(), Types._String, Ids.getName,
        _return(Ids.name)
      )
    );
  }

}
