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

import br.com.objectos.be.meta.ResourceKind;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import java.util.NoSuchElementException;

class BeAnnotatedType {

  final NamedClass className;
  final ImmutableList<BeAnnotatedTypeMethod> methods;

  private NamedClass moduleImplName;

  private BeAnnotatedType(Builder builder) {
    className = builder.className();
    methods = builder.methods();
  }

  static BeAnnotatedType fromGeneratedBeResource(ProcessingType generatedBeResource) {
    ProcessingAnnotation beTypeAnnotation;
    beTypeAnnotation = getBeTypeAnnotation(generatedBeResource);

    ProcessingAnnotationValue beTypeClassValue;
    beTypeClassValue = beTypeAnnotation.getDeclaredValue("value");

    PTypeMirror beModelType;
    beModelType = beTypeClassValue.getType();

    if (!beModelType.isDeclaredType()) {
      throw new UnsupportedOperationException("Implement me");
    }

    PDeclaredType beClass;
    beClass = beModelType.toDeclaredType();

    ProcessingType beAnnotatedProcessingType;
    beAnnotatedProcessingType = beClass.toProcessingType();

    ProcessingTypeBuilder builder;
    builder = new ProcessingTypeBuilder(beAnnotatedProcessingType);

    return builder.build();
  }

  private static ProcessingAnnotation getBeTypeAnnotation(ProcessingType processingType) {
    ResourceKind resourceKind;
    resourceKind = ResourceKindEnum.getFromGeneratedType(processingType);

    NamedClass metaAnnotationClassName;
    metaAnnotationClassName = getMetaAnnotationClassName(resourceKind);

    ImmutableList<ProcessingAnnotation> annotations;
    annotations = processingType.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation candidate;
      candidate = annotations.get(i);

      NamedClass candidateClassName;
      candidateClassName = candidate.className();

      if (candidateClassName.equals(metaAnnotationClassName)) {
        return candidate;
      }
    }

    throw new NoSuchElementException(metaAnnotationClassName.getCanonicalName());
  }

  private static NamedClass getMetaAnnotationClassName(ResourceKind resourceKind) {
    switch (resourceKind) {
      case CSS:
        return TypeNames.MetaBeCss;
      case HTML:
        return TypeNames.MetaBeHtml;
      case IMAGE:
        return TypeNames.MetaBeImage;
      default:
        throw new AssertionError(resourceKind);
    }
  }

  public final NamedClass forDirectoryImplName(NamedClass moduleName) {
    if (moduleImplName == null) {
      moduleImplName = forDirectoryImplName0(moduleName);
    }

    return moduleImplName;
  }

  public final ClassCode generateDirectoryNestedImpl(DirectoryFacade facade) {
    ClassCode.Builder builder;
    builder = ClassCode.builder();

    builder.addModifier(Modifiers.PRIVATE);

    String simpleName;
    simpleName = forDirectoryImplSimpleName();

    builder.simpleName(simpleName);

    builder.superclass(className);

    for (int i = 0; i < methods.size(); i++) {
      BeAnnotatedTypeMethod method;
      method = methods.get(i);

      MethodCode generatedMethod;
      generatedMethod = method.generateDirectoryNestedImplMethod(facade);

      builder.addMethod(generatedMethod);
    }

    return builder.build();
  }

  private NamedClass forDirectoryImplName0(NamedClass moduleName) {
    String simpleName;
    simpleName = forDirectoryImplSimpleName();

    return moduleName.nestedClass(simpleName);
  }

  private String forDirectoryImplSimpleName() {
    NamedClass implClassName;
    implClassName = className.withSuffix("Impl");

    return implClassName.getSimpleName();
  }

  abstract static class Builder {

    Builder() {}

    public final BeAnnotatedType build() {
      return new BeAnnotatedType(this);
    }

    abstract NamedClass className();

    abstract ImmutableList<BeAnnotatedTypeMethod> methods();

  }

  private static class ProcessingTypeBuilder extends Builder {

    private final ProcessingType processingType;

    ProcessingTypeBuilder(ProcessingType processingType) {
      this.processingType = processingType;
    }

    @Override
    final NamedClass className() {
      return processingType.getName();
    }

    @Override
    final ImmutableList<BeAnnotatedTypeMethod> methods() {
      return BeAnnotatedTypeMethod.fromBeAnnotatedProcessingType(processingType);
    }

  }

}
