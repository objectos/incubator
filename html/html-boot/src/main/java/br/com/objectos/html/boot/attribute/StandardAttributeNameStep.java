/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot.attribute;

import static br.com.objectos.code.java.Java._abstract;
import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._implements;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._super;
import static br.com.objectos.code.java.Java._this;
import static br.com.objectos.code.java.Java.a;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.arrayAccess;
import static br.com.objectos.code.java.Java.constructor;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.fields;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.types;
import static br.com.objectos.html.boot.attribute.AttributeNames.AttributeName;
import static br.com.objectos.html.boot.attribute.AttributeNames.StandardAttributeName;
import static br.com.objectos.html.boot.attribute.AttributeNames.StandardAttributeNameArray;
import static br.com.objectos.html.boot.spi.type.SpiType.Value;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.expression.Callee;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.statement.VariableInitializer;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.html.boot.HtmlBoot;
import br.com.objectos.html.boot.spec.AbstractJavaFileStep;
import br.com.objectos.html.boot.spec.AttributeKind;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spi.tmpl.SpiTmpl;
import br.com.objectos.html.boot.util.Ids;
import br.com.objectos.html.boot.util.Names;
import java.util.function.Consumer;
import objectos.util.MutableList;

public class StandardAttributeNameStep extends AbstractJavaFileStep {

  private final MutableList<VariableInitializer> constantNames = new MutableList<>();
  private final MutableList<FieldCode> constants = new MutableList<>();
  private Callee currentCallee;

  private final MutableList<ClassCode> nameTypes = new MutableList<>();

  public StandardAttributeNameStep(Consumer<JavaFile> javaFileWriter) {
    super(javaFileWriter);

    currentCallee = _new(AttributeNames.NamesBuilder);
  }

  @Override
  public final void attributeSpec(AttributeSpec attributeSpec) {
    Identifier constantName = attributeSpec.constantName();
    constantNames.add(constantName);

    NamedClass className = attributeSpec.className();
    constants.add(
        field(
            _public(), _static(), _final(), className,
            init(constantName, _new(className))
        )
    );

    AttributeKind kind = attributeSpec.kind();

    nameTypes.add(
        _class(
            _public(), _static(), className, _extends(StandardAttributeName),
            _implements(attributeSpec.interfaceSet()),
            constructor(
                _private(),
                _super(
                    l(nameTypes.size()),
                    kind.asAttributeNameFieldAccess(),
                    l(attributeSpec.name())
                )
            )
        )
    );

    currentCallee = invoke(currentCallee, "put", l(attributeSpec.name()), constantName);
  }

  @Override
  public final void execute() {
    generateJavaFile(
        AttributeNames._PACKAGE,
        classCode()
    );
  }

  private ClassCode classCode() {
    return _class(
        HtmlBoot.GENERATED,
        _public(), _abstract(), StandardAttributeName, _implements(AttributeName, Value),

        fields(constants),

        field(
            _private(), _static(), _final(), StandardAttributeNameArray,
            init(Ids.ARRAY, _new(StandardAttributeNameArray, a(constantNames)))
        ),

        field(
            _private(), _static(), _final(), AttributeNames.namesMapTypeName,
            init(Ids.MAP, invoke(currentCallee, "build"))
        ),

        field(_private(), _final(), _int(), Ids.code),

        field(_private(), _final(), AttributeNames.AttributeKind, Ids.kind),

        field(_private(), _final(), Names.String, Ids.name),

        constructor(
            param(_int(), Ids.code),
            param(AttributeNames.AttributeKind, Ids.kind),
            param(Names.String, Ids.name),
            _this().id(Ids.code).receive(Ids.code),
            _this().id(Ids.kind).receive(Ids.kind),
            _this().id(Ids.name).receive(Ids.name)
        ),

        method(
            _public(), _static(), StandardAttributeName, Ids.getByCode,
            param(_int(), Ids.code),
            _return(arrayAccess(Ids.ARRAY, Ids.code))
        ),

        method(
            _public(), _static(), StandardAttributeName, Ids.getByName,
            param(Names.String, Ids.name),
            _return(invoke(Ids.MAP, "get", Ids.name))
        ),

        method(
            _public(), _static(), _int(), Ids.size,
            _return(Ids.ARRAY.id(Ids.length))
        ),

        method(
            annotation(Override.class),
            _public(), _final(), _int(), Ids.getCode,
            _return(Ids.code)
        ),

        method(
            annotation(Override.class),
            _public(), _final(), AttributeNames.AttributeKind, Ids.getKind,
            _return(Ids.kind)
        ),

        method(
            annotation(Override.class),
            _public(), _final(), Names.String, Ids.getName,
            _return(Ids.name)
        ),

        SpiTmpl.markMethod("markAttribute"),

        SpiTmpl.renderMethod(),

        types(nameTypes)
    );
  }

}
