/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.processing.type;

import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedClassOrParameterized;
import br.com.objectos.code.java.type.NamedParameterized;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

public final class PDeclaredType extends PTypeMirror {

  private NamedClassOrParameterized name;

  private ProcessingType processingType;

  private final DeclaredType type;

  private ImmutableList<PTypeMirror> typeParameters;

  PDeclaredType(ProcessingEnvironment processingEnv, DeclaredType type) {
    super(processingEnv);
    this.type = type;
  }

  public static PDeclaredType adapt(ProcessingEnvironment processingEnv, DeclaredType type) {
    Checks.checkNotNull(processingEnv, "processingEnv == null");
    Checks.checkNotNull(type, "type == null");
    Checks.checkArgument(type.getKind() != TypeKind.ERROR, "type.getKind() == TypeKind.ERROR");

    return new PDeclaredType(processingEnv, type);
  }

  @Override
  public final NamedClassOrParameterized getName() {
    if (name == null) {
      name = getName0();
    }

    return name;
  }

  public final ImmutableList<PTypeMirror> getTypeArguments() {
    if (typeParameters == null) {
      typeParameters = getTypeArguments0();
    }

    return typeParameters;
  }

  @Override
  public final boolean isDeclaredType() {
    return true;
  }

  @Override
  public final PDeclaredType toDeclaredType() {
    return this;
  }

  public final ProcessingType toProcessingType() {
    if (processingType == null) {
      processingType = toProcessingType0();
    }

    return processingType;
  }

  @Override
  final DeclaredType getType() {
    return type;
  }

  @Override
  final NamedArray toNamedArray() {
    NamedClassOrParameterized typeName;
    typeName = getName();

    return typeName.toNamedArray();
  }

  private NamedClassOrParameterized getName0() {
    Element element;
    element = type.asElement();

    TypeElement typeElement;
    typeElement = TypeElement.class.cast(element);

    NamedClass rawName;
    rawName = NamedClass.of(typeElement);

    ImmutableList<PTypeMirror> typeArguments;
    typeArguments = getTypeArguments();

    int size;
    size = typeArguments.size();

    switch (size) {
      case 0:
        return rawName;
      default:
        MutableList<NamedType> argumentNames;
        argumentNames = MutableList.create();

        for (int i = 0; i < size; i++) {
          PTypeMirror typeArgument;
          typeArgument = typeArguments.get(i);

          NamedType argumentName;
          argumentName = typeArgument.getName();

          argumentNames.add(argumentName);
        }

        return NamedParameterized.of(rawName, argumentNames);
    }
  }

  private ImmutableList<PTypeMirror> getTypeArguments0() {
    List<? extends TypeMirror> typeArguments;
    typeArguments = type.getTypeArguments();

    int size;
    size = typeArguments.size();

    switch (size) {
      case 0:
        return ImmutableList.of();
      default:
        MutableList<PTypeMirror> result;
        result = MutableList.create();

        for (int i = 0; i < size; i++) {
          TypeMirror typeArgument;
          typeArgument = typeArguments.get(i);

          PTypeMirror adapted;
          adapted = PTypeMirror.adapt(processingEnv, typeArgument);

          result.add(adapted);
        }

        return result.toImmutableList();
    }
  }

  private ProcessingType toProcessingType0() {
    Element element;
    element = type.asElement();

    TypeElement typeElement;
    typeElement = TypeElement.class.cast(element);

    return ProcessingType.adapt(processingEnv, typeElement);
  }

}
