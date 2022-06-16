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

import static br.com.objectos.code.java.Java._abstract;
import static br.com.objectos.code.java.Java._boolean;
import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._if;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java._null;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._throw;
import static br.com.objectos.code.java.Java._var;
import static br.com.objectos.code.java.Java.a;
import static br.com.objectos.code.java.Java.arrayAccess;
import static br.com.objectos.code.java.Java.eq;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.fields;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.statements;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.expression.Literal;
import br.com.objectos.code.java.statement.BlockStatement;
import br.com.objectos.code.java.statement.VariableInitializer;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedClassOrParameterized;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.spec.Ids;
import br.com.objectos.css.boot.spec.Step;
import br.com.objectos.css.boot.spec.StepAdapter;
import br.com.objectos.css.boot.spec.Types;
import objectos.util.UnmodifiableList;
import objectos.util.MutableList;

public class GeneratedColorStep extends Step {

  private final MutableList<FieldCode> colors = new MutableList<>();
  private final MutableList<VariableInitializer> constantNames = new MutableList<>();
  private final MutableList<BlockStatement> mapStatements = new MutableList<>();

  public GeneratedColorStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addColorName(ColorName colorName) {
    colorName.assertIdentifierIsEqualToName();

    colors.add(
        field(
            _public(), _static(), _final(), TypeNames._ColorName,
            init(
                colorName.identifier,
                _new(
                    TypeNames._ColorName, getCode(), l(colorName.name)
                )
            )
        )
    );

    constantNames.add(colorName.identifier);

    mapStatements.add(colorName.invokePut(Ids.m));
  }

  @Override
  public final void execute() {
    writeJavaFile(
        TypeNames.PACKAGE,
        classCode()
    );
  }

  private ClassCode classCode() {
    NamedClass implName;
    implName = TypeNames._ColorName;

    NamedArray implArray = implName.toNamedArray();

    UnmodifiableList<NamedClass> mapTypeArgs;
    mapTypeArgs = UnmodifiableList.of(Types._String, implName);

    NamedClassOrParameterized implGrowableMap;
    implGrowableMap = t(Types._MutableMap, mapTypeArgs);

    NamedClassOrParameterized implUnmodifiableMap;
    implUnmodifiableMap = t(Types._UnmodifiableMap, mapTypeArgs);

    return _class(
        CssBoot.GENERATED,
        _abstract(), TypeNames._GeneratedColor,

        fields(colors),

        field(
            _private(), _static(), _final(), implArray,
            init(Ids.ARRAY, _new(implArray, a(constantNames)))
        ),

        field(
            _private(), _static(), _final(), implUnmodifiableMap,
            init(Ids.MAP, invoke(Ids.buildMap.name()))
        ),

        method(
            _public(), _static(), implName, Ids.getByCode,
            param(_int(), Ids.code),
            _return(arrayAccess(Ids.ARRAY, Ids.code))
        ),

        method(
            _public(), _static(), implName, Ids.getByName,
            param(Types._String, Ids.name),

            _var(implName, Ids.c, invoke(Ids.MAP, "get", Ids.name)),
            _if(eq(Ids.c, _null()),
                _throw(_new(t(IllegalArgumentException.class), Ids.name))
            ),
            _return(Ids.c)
        ),

        method(
            _public(), _static(), _boolean(), Ids.isColor,
            param(Types._String, Ids.name),

            _return(invoke(Ids.MAP, "containsKey", Ids.name))
        ),

        method(
            _private(), _static(), implUnmodifiableMap, Ids.buildMap,
            _var(implGrowableMap, Ids.m, Types._newMutableMap()),
            statements(mapStatements),
            _return(invoke(Ids.m, "toUnmodifiableMap"))
        )
    );
  }

  private Literal getCode() {
    return l(colors.size());
  }

}
