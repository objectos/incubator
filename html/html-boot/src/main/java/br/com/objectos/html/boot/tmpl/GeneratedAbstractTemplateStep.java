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
package br.com.objectos.html.boot.tmpl;

import static br.com.objectos.code.java.Java._abstract;
import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java.a;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.methods;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.tvar;
import static br.com.objectos.code.java.Java.typeParam;
import static br.com.objectos.html.boot.attribute.AttributeNames.StandardAttributeName;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.ParameterCode;
import br.com.objectos.code.java.declaration.VarArgs;
import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedTypeParameter;
import br.com.objectos.code.java.type.NamedTypeVariable;
import br.com.objectos.html.boot.HtmlBoot;
import br.com.objectos.html.boot.element.ElementNames;
import br.com.objectos.html.boot.spec.AbstractJavaFileStep;
import br.com.objectos.html.boot.spec.AttributeKind;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spec.ElementSpec;
import br.com.objectos.html.boot.spec.TemplateSpec;
import br.com.objectos.html.boot.spi.type.SpiType;
import br.com.objectos.html.boot.util.Ids;
import br.com.objectos.html.boot.util.Names;
import java.util.function.Consumer;
import objectos.util.GrowableList;

public class GeneratedAbstractTemplateStep extends AbstractJavaFileStep {

  private final GrowableList<MethodCode> attrMethods = new GrowableList<>();
  private final GrowableList<MethodCode> elementMethods = new GrowableList<>();

  private TemplateSpec templateSpec;

  public GeneratedAbstractTemplateStep(Consumer<JavaFile> javaFileWriter) {
    super(javaFileWriter);
  }

  @Override
  public final void attributeSpec(AttributeSpec attributeSpec) {
    Iterable<String> names = attributeSpec.methodNames();

    for (String name : names) {
      if (templateSpec.shouldIncludeAttribute(name)) {
        MethodCode method = generateAttrFunction(attributeSpec, name);

        attrMethods.add(method);
      }
    }
  }

  @Override
  public final void elementSpec(ElementSpec elementSpec) {
    Identifier methodName = id(elementSpec.methodName());

    elementMethods.add(elementMethodValue(elementSpec, methodName));

    if (templateSpec.shouldIncludeText(elementSpec)) {
      elementMethods.add(elementMethodString(elementSpec, methodName));
    }
  }

  @Override
  public final void execute() {
    generateJavaFile(
        TmplNames.PACKAGE,
        classCode()
    );
  }

  @Override
  public final void templateSpec(TemplateSpec template) {
    templateSpec = template;
  }

  private ClassCode classCode() {
    NamedTypeParameter nExtends = typeParam("N", StandardAttributeName);
    NamedTypeVariable n = tvar("N");
    return _class(
        HtmlBoot.GENERATED,
        _abstract(), TmplNames.GeneratedAbstractTemplate,

        methods(elementMethods),

        methods(attrMethods),

        method(
            _abstract(), nExtends, n, id("addStandardAttribute"),
            param(n, Ids.name)
        ),

        method(
            _abstract(), nExtends, n, id("addStandardAttribute"),
            param(n, Ids.name),
            param(Names.String, Ids.value)
        ),

        method(
            _abstract(), ElementNames.ElementName, id("addStandardElement"),
            param(ElementNames.StandardElementName, Ids.name),
            param(Names.String, Ids.text)
        ),

        method(
            _abstract(), ElementNames.ElementName, id("addStandardElement"),
            param(ElementNames.StandardElementName, Ids.name),
            param(SpiType.ValueArray, Ids.values)
        )
    );
  }

  private MethodCode elementMethod0(
      ElementSpec element, Identifier methodName, ParameterCode parameter) {
    return method(
        _public(), _final(), ElementNames.ElementName, methodName,
        parameter,
        _return(
            invoke(
                "addStandardElement",
                ElementNames.StandardElementName.id(element.constantName()),
                parameter.name()
            )
        )
    );
  }

  private MethodCode elementMethodString(ElementSpec elementSpec, Identifier methodName) {
    return elementMethod0(
        elementSpec,
        methodName,
        param(Names.String, Ids.text)
    );
  }

  private MethodCode elementMethodValue(ElementSpec elementSpec, Identifier methodName) {
    return elementMethod0(
        elementSpec,
        methodName,
        param(VarArgs.of(a(elementSpec.classNameValue())), Ids.values)
    );
  }

  private MethodCode generateAttrFunction(AttributeSpec attributeSpec, String methodName) {
    MethodCode.Builder b = MethodCode.builder();
    b.addModifier(Modifiers.PUBLIC, Modifiers.FINAL);
    b.returnType(attributeSpec.className());
    b.name(methodName);

    GrowableList<Argument> args = new GrowableList<>();
    args.add(StandardAttributeName.id(attributeSpec.constantName()));

    AttributeKind kind = attributeSpec.kind();
    if (kind.isString()) {
      b.addParameter(param(Names.String, Ids.value));
      args.add(Ids.value);
    }

    b.addStatement(
        _return(
            invoke("addStandardAttribute", args)
        )
    );

    return b.build();
  }

}
