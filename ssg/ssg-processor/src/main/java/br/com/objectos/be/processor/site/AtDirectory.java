/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor.site;

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._var;
import static br.com.objectos.code.java.Java._void;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.nl;
import static br.com.objectos.code.java.Java.param;

import br.com.objectos.be.processor.Ids;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.expression.Arguments;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.statement.BlockStatement;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.ErrorTypeException;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;

final class AtDirectory {

  final NamedClass directoryName;
  final Identifier identifier;
  final ImmutableList<PathParameter> parameters;
  final String path;
  final NamedClass pathName;

  private AtDirectory(Builder builder) {
    directoryName = builder.getDirectoryName();
    identifier = builder.getIdentifier();
    parameters = builder.getParameters();
    path = builder.getPath();
    pathName = builder.getPathName();
  }

  public static ImmutableList<AtDirectory> fromBeSiteAnnotation(
      ProcessingAnnotation beSiteAnnotation)
      throws ErrorTypeException {
    MutableList<AtDirectory> result = MutableList.create();

    ProcessingAnnotationValue directoriesAnnotationValue;
    directoriesAnnotationValue = beSiteAnnotation.getDeclaredOrDefaultValue("directories");

    ImmutableList<ProcessingAnnotation> atAnnotations;
    atAnnotations = directoriesAnnotationValue.getAnnotationArray();

    for (int i = 0; i < atAnnotations.size(); i++) {
      ProcessingAnnotation atAnnotation;
      atAnnotation = atAnnotations.get(i);

      AtDirectory atDirectory;
      atDirectory = fromAtAnnotation(atAnnotation);

      result.add(atDirectory);
    }

    return result.toImmutableList();
  }

  private static AtDirectory fromAtAnnotation(
      ProcessingAnnotation annotation)
      throws ErrorTypeException {
    return new ProcessingAnnotationBuilder(annotation).build();
  }

  public final BlockStatement generateConfigureStatement() {
    return invoke(identifier.name(), Ids.helper);
  }

  public final FieldCode generateHelperField() {
    return field(
        _final(), TypeNames.BaseUrl, init(identifier, invoke("getBaseUrl", l(path)))
    );
  }

  public final MethodCode generateHelperMethod() {
    return method(
        _final(), pathName, identifier,
        param(TypeNames.BaseUrl, Ids.from),
        _return(_new(pathName, invoke(identifier, "resolve", Ids.from)))
    );
  }

  public final MethodCode generateSiteMethod(NamedClass helperName) {
    return method(
        _private(), _void(), identifier,
        param(helperName, Ids.helper),

        _var(TypeNames.BaseUrl, Ids.from, Ids.helper.id(identifier)),
        invoke(
            "install", nl(),
            Ids.from, nl(),
            _new(
                directoryName,
                newDirectoryArguments()
            ), nl()
        )
    );
  }

  private Arguments newDirectoryArguments() {
    switch (parameters.size()) {
      case 0:
        return Arguments.empty();
      default:
        return newDirectoryArguments0();
    }
  }

  private Arguments newDirectoryArguments0() {
    Arguments.Builder arguments;
    arguments = Arguments.builder();

    arguments.addNewLine();

    for (int i = 0; i < parameters.size(); i++) {
      PathParameter parameter;
      parameter = parameters.get(i);

      parameter.acceptDirectoryArgumentsBuilder(arguments);
    }

    return arguments.build();
  }

  abstract static class Builder {

    public final AtDirectory build() {
      return new AtDirectory(this);
    }

    abstract NamedClass getDirectoryName();

    abstract Identifier getIdentifier();

    abstract ImmutableList<PathParameter> getParameters();

    abstract String getPath();

    abstract NamedClass getPathName();

  }

  private static class ProcessingAnnotationBuilder extends Builder {

    private final ProcessingAnnotation annotation;
    private final ProcessingType type;

    ProcessingAnnotationBuilder(ProcessingAnnotation annotation) throws ErrorTypeException {
      this.annotation = annotation;

      ProcessingAnnotationValue typeValue;
      typeValue = annotation.getDeclaredValue("type");

      PTypeMirror typeDeclaredValue;
      typeDeclaredValue = typeValue.getType();

      typeDeclaredValue.throwErrorTypeExceptionIfPossible();

      PDeclaredType typeClass;
      typeClass = typeDeclaredValue.toDeclaredType();

      type = typeClass.toProcessingType();
    }

    @Override
    final NamedClass getDirectoryName() {
      return type.getName();
    }

    @Override
    final Identifier getIdentifier() {
      NamedClass className;
      className = type.getName();

      String simpleName;
      simpleName = className.getSimpleName();

      String prefix;
      prefix = simpleName.replace("Directory", "");

      return Java.id(JavaNames.toValidMethodName(prefix));
    }

    @Override
    final ImmutableList<PathParameter> getParameters() {
      return PathParameter.fromDirectory(type);
    }

    @Override
    final String getPath() {
      ProcessingAnnotationValue pathValue;
      pathValue = annotation.getDeclaredValue("path");

      return pathValue.getString();
    }

    @Override
    final NamedClass getPathName() {
      NamedClass className;
      className = type.getName();

      String simpleName;
      simpleName = className.getSimpleName();

      String pathSimpleName;
      pathSimpleName = simpleName.replace("Directory", "Path");

      return className.getPackage().nestedClass(pathSimpleName);
    }

  }

}
