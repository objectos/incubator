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
package br.com.objectos.css.boot.keyword;

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
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.privateConstructor;
import static br.com.objectos.code.java.Java.statements;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.FieldCode;
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
import objectos.util.ImmutableList;
import objectos.util.MutableList;

public class KeywordsClassStep extends Step {

  private final MutableList<VariableInitializer> constantNames = MutableList.create();

  private final MutableList<FieldCode> keywords = MutableList.create();

  private final MutableList<BlockStatement> mapStatements = MutableList.create();

  public KeywordsClassStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addKeyword(KeywordName keyword) {
    keywords.add(
        field(
            _public(), _static(), _final(), keyword.className,
            init(keyword.fieldName, keyword.className.id(Ids.INSTANCE))
        )
    );

    constantNames.add(keyword.fieldName);

    mapStatements.add(keyword.invokePut(Ids.m));
  }

  @Override
  public final void execute() {
    writeJavaFile(
        KeywordNames.PACKAGE,
        classCode()
    );
  }

  private ClassCode classCode() {
    NamedClass implName;
    implName = KeywordNames._StandardKeyword;

    NamedArray implArray = implName.toNamedArray();

    ImmutableList<NamedClass> mapTypeArgs;
    mapTypeArgs = ImmutableList.of(Types._String, implName);

    NamedClassOrParameterized implGrowableMap;
    implGrowableMap = t(Types._MutableMap, mapTypeArgs);

    NamedClassOrParameterized implImmutableMap;
    implImmutableMap = t(Types._ImmutableMap, mapTypeArgs);

    return _class(
        CssBoot.GENERATED,
        _public(), _final(), KeywordNames._Keywords,

        fields(keywords),

        field(
            _private(), _static(), _final(), implArray,
            init(Ids.ARRAY, _new(implArray, a(constantNames)))
        ),

        field(
            _private(), _static(), _final(), implImmutableMap,
            init(Ids.MAP, invoke(Ids.buildMap.name()))
        ),

        privateConstructor(),

        method(
            _public(), _static(), implName, Ids.getByCode,
            param(_int(), Ids.code),
            _return(arrayAccess(Ids.ARRAY, Ids.code))
        ),

        method(
            _public(), _static(), KeywordNames._StandardKeyword, Ids.getByName,
            param(Types._String, Ids.name),
            _var(KeywordNames._StandardKeyword, Ids.k, invoke(Ids.MAP, "get", Ids.name)),
            _if(eq(Ids.k, _null()),
                _throw(_new(t(IllegalArgumentException.class), Ids.name))
            ),
            _return(Ids.k)
        ),

        method(
            _public(), _static(), _boolean(), Ids.isKeyword,
            param(Types._String, Ids.name),
            _return(invoke(Ids.MAP, "containsKey", Ids.name))
        ),

        method(
            _private(), _static(), implImmutableMap, Ids.buildMap,
            _var(implGrowableMap, Ids.m, Types._newMutableMap()),
            statements(mapStatements),
            _return(invoke(Ids.m, "toImmutableMap"))
        )
    );
  }

}
