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
package br.com.objectos.be.processor.directory;

import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java.invoke;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.statement.Statement;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingType;

final class ProcessedResourceType extends ProcessedResource {

  final BeAnnotatedType beAnnotatedType;

  private final Kind kind;

  private ProcessedResourceType(Builder builder) {
    super(builder);
    kind = builder.kind();
    beAnnotatedType = builder.beAnnotatedType();
  }

  public static ProcessedResourceType buildCss(ProcessingType type) {
    return build(Kind.CSS, type);
  }

  public static ProcessedResourceType buildHtml(ProcessingType type) {
    return build(Kind.HTML, type);
  }

  private static ProcessedResourceType build(Kind kind, ProcessingType type) {
    ProcessingTypeBuilder builder;
    builder = new ProcessingTypeBuilder(kind, type);

    return builder.build();
  }

  @Override
  public final void acceptDirectoryGenerator(DirectoryGenerator generator) {
    generator.addProcessedResourceType(this);
  }

  @Override
  public final void acceptPathGenerator(PathGenerator generator) {
    kind.acceptPathGenerator(generator, this);
  }

  public final Statement generateDirectoryConfigureStatement(NamedClass moduleName) {
    NamedClass implName;
    implName = beAnnotatedType.forDirectoryImplName(moduleName);

    return invoke(kind.moduleMethodName, _new(implName));
  }

  public final ClassCode generateDirectoryNestedImpl(DirectoryFacade facade) {
    return beAnnotatedType.generateDirectoryNestedImpl(facade);
  }

  abstract static class Builder
      extends ProcessedResource.AbstractBuilder<ProcessedResourceType> {

    Builder() {}

    public final ProcessedResourceType build() {
      return new ProcessedResourceType(this);
    }

    abstract BeAnnotatedType beAnnotatedType();

    abstract Kind kind();

  }

  private enum Kind {

    CSS("addStyleSheet") {
      @Override
      final void acceptPathGenerator(
          PathGenerator generator, ProcessedResourceType instance) {
        generator.addCssInstance(instance);
      }
    },

    HTML("addTemplate") {
      @Override
      final void acceptPathGenerator(
          PathGenerator generator, ProcessedResourceType instance) {
        generator.addHtmlInstance(instance);
      }
    };

    final String moduleMethodName;

    private Kind(String moduleMethodName) {
      this.moduleMethodName = moduleMethodName;
    }

    abstract void acceptPathGenerator(
        PathGenerator generator, ProcessedResourceType instance);

  }

  private static class ProcessingTypeBuilder extends Builder {

    private final BeAnnotatedType beAnnotatedType;
    private final Kind kind;
    private final ProcessingType type;

    ProcessingTypeBuilder(Kind kind, ProcessingType type) {
      this.kind = kind;
      this.type = type;

      beAnnotatedType = BeAnnotatedType.fromGeneratedBeResource(type);
    }

    @Override
    final BeAnnotatedType beAnnotatedType() {
      return beAnnotatedType;
    }

    @Override
    final NamedClass className() {
      return type.getName();
    }

    @Override
    final Kind kind() {
      return kind;
    }

    @Override
    final String resourceName() {
      String simpleName;
      simpleName = beAnnotatedType.className.getSimpleName();

      String lowerCase;
      lowerCase = simpleName.toLowerCase();

      String extension;
      extension = kind.name().toLowerCase();

      return lowerCase + '.' + extension;
    }

  }

}
