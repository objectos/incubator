/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
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
package br.com.objectos.html.boot.element;

import static br.com.objectos.code.java.Java._enum;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._implements;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._this;
import static br.com.objectos.code.java.Java.a;
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

import br.com.objectos.code.java.declaration.EnumCode;
import br.com.objectos.code.java.declaration.EnumConstantCode;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.HtmlBoot;
import br.com.objectos.html.boot.spec.AbstractJavaFileStep;
import br.com.objectos.html.boot.spec.ElementSpec;
import br.com.objectos.html.boot.spi.tmpl.SpiTmpl;
import br.com.objectos.html.boot.util.Ids;
import br.com.objectos.html.boot.util.Names;
import java.util.function.Consumer;
import objectos.util.GrowableList;

public class StandardElementNameStep extends AbstractJavaFileStep {

  private static final ExpressionName NORMAL = ElementNames.ElementKind.id("NORMAL");
  private static final ExpressionName VOID = ElementNames.ElementKind.id("VOID");

  private final GrowableList<EnumConstantCode> constants = new GrowableList<>();

  public StandardElementNameStep(Consumer<JavaFile> javaFileWriter) {
    super(javaFileWriter);
  }

  @Override
  public final void elementSpec(ElementSpec elementSpec) {
    constants.add(cte(elementSpec));
  }

  @Override
  public final void execute() {
    generateJavaFile(
        ElementNames.PACKAGE,
        enumCode()
    );
  }

  private EnumConstantCode cte(ElementSpec elementSpec) {
    return enumConstant(
        elementSpec.constantName(),
        args(
            elementSpec.hasEndTag() ? NORMAL : VOID,
            l(elementSpec.name())
        )
    );
  }

  private EnumCode enumCode() {
    return _enum(
        HtmlBoot.GENERATED,
        _public(), ElementNames.StandardElementName, _implements(ElementNames.ElementName),

        enumConstants(constants),

        field(
            _private(), _static(), _final(),
            a(ElementNames.StandardElementName),
            init(Ids.ARRAY, ElementNames.StandardElementName.invoke("values"))
        ),

        field(_private(), _final(), ElementNames.ElementKind, Ids.kind),
        field(_private(), _final(), Names.String, Ids.name),

        constructor(
            _private(),
            param(ElementNames.ElementKind, Ids.kind),
            param(Names.String, Ids.name),
            assign(fieldAccess(_this(), Ids.kind), Ids.kind),
            assign(fieldAccess(_this(), Ids.name), Ids.name)
        ),

        method(
            _public(), _static(), ElementNames.StandardElementName, Ids.getByCode,
            param(_int(), Ids.code),
            _return(arrayAccess(Ids.ARRAY, Ids.code))
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
            annotation(Override.class),
            _public(), _final(), ElementNames.ElementKind, Ids.getKind,
            _return(Ids.kind)
        ),

        method(
            annotation(Override.class),
            _public(), _final(), Names.String, Ids.getName,
            _return(Ids.name)
        ),

        SpiTmpl.markMethod("markElement"),

        SpiTmpl.renderMethod()
    );
  }

}
