/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import static br.com.objectos.code.java.declaration.ConstructorCode.constructor;

import br.com.objectos.code.annotations.GenerateStaticFactoryAggregate;
import br.com.objectos.code.java.declaration.AccessLevel;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import objectos.lang.HashCode;
import objectos.lang.ToString;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

final class GenerateStaticFactoryAggregateAnnotation implements ToString.Formattable {

  private final UnmodifiableList<FactoryType> factoryTypes;

  private final PackageName packageName;

  private final String simpleName;

  private GenerateStaticFactoryAggregateAnnotation(Builder builder) {
    packageName = builder.packageName();
    simpleName = builder.simpleName();
    factoryTypes = builder.factoryTypes();
  }

  static GenerateStaticFactoryAggregateAnnotation of(ProcessingPackage pkg) {
    return new PackageElementQueryBuilder(pkg).build();
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof GenerateStaticFactoryAggregateAnnotation)) {
      return false;
    }
    GenerateStaticFactoryAggregateAnnotation that
        = (GenerateStaticFactoryAggregateAnnotation) obj;
    return packageName.equals(that.packageName)
        && simpleName.equals(that.simpleName)
        && factoryTypes.equals(that.factoryTypes);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "packageName", packageName,
      "simpleName", simpleName,
      "factoryTypes", factoryTypes
    );
  }

  public final JavaFile generate() {
    return generateClass().toJavaFile(packageName);
  }

  public final ClassCode generateClass() {
    ClassCode.Builder b = ClassCode.builder();

    b.addModifier(Modifiers.PUBLIC, Modifiers.FINAL);

    b.simpleName(simpleName);

    b.addConstructor(
      constructor(AccessLevel.PRIVATE)
    );

    for (int i = 0; i < factoryTypes.size(); i++) {
      FactoryType factoryType;
      factoryType = factoryTypes.get(i);

      factoryType.generateFields(b);

      factoryType.generateMethods(b);
    }

    return b.build();
  }

  @Override
  public final int hashCode() {
    return HashCode.of(packageName, simpleName, factoryTypes);
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

  static abstract class Builder {

    public final GenerateStaticFactoryAggregateAnnotation build() {
      return new GenerateStaticFactoryAggregateAnnotation(this);
    }

    abstract UnmodifiableList<FactoryType> factoryTypes();

    abstract PackageName packageName();

    abstract String simpleName();

  }

  private static class PackageElementQueryBuilder extends Builder {
    private final ProcessingAnnotation annotation;
    private final PackageName packageName;

    PackageElementQueryBuilder(ProcessingPackage pkg) {
      packageName = pkg.toNamedPackage();
      annotation = pkg.getDirectlyPresentAnnotation(GenerateStaticFactoryAggregate.class);
    }

    @Override
    final UnmodifiableList<FactoryType> factoryTypes() {
      GrowableList<FactoryType> result = new GrowableList<>();

      ProcessingAnnotationValue factoriesValue;
      factoriesValue = annotation.getDeclaredOrDefaultValue("factories");

      UnmodifiableList<PTypeMirror> factories;
      factories = factoriesValue.getTypeArray();

      for (int i = 0; i < factories.size(); i++) {
        PTypeMirror factory = factories.get(i);

        if (!factory.isDeclaredType()) {
          continue;
        }

        PDeclaredType factoryModelClass;
        factoryModelClass = factory.toDeclaredType();

        ProcessingType factoryProcessingType;
        factoryProcessingType = factoryModelClass.toProcessingType();

        FactoryType factoryType;
        factoryType = FactoryType.of(factoryProcessingType);

        result.add(factoryType);
      }

      return result.toUnmodifiableList();
    }

    @Override
    final PackageName packageName() {
      return packageName;
    }

    @Override
    final String simpleName() {
      ProcessingAnnotationValue simpleName;
      simpleName = annotation.getDeclaredValue("simpleName");

      return simpleName.getString();
    }
  }

}
