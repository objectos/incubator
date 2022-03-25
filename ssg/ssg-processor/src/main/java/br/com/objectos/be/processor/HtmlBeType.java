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
package br.com.objectos.be.processor;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.be.meta.MetaBeHtml;
import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.ClassCode.Builder;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.AnnotatedElementOrType;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingParameter;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;

abstract class HtmlBeType extends BeType {

  public static BeType of0(ProcessingType type) {
    MutableList<MarkdownMethod> markdownMethods;
    markdownMethods = null;

    ImmutableList<ProcessingMethod> methods;
    methods = type.getDeclaredMethods();

    for (int i = 0, size = methods.size(); i < size; i++) {
      ProcessingMethod method;
      method = methods.get(i);

      if (!method.isAbstract()) {
        continue;
      }

      ImmutableList<ProcessingParameter> parameters;
      parameters = method.getParameters();

      if (!parameters.isEmpty()) {
        continue;
      }

      PTypeMirror returnType;
      returnType = method.getReturnType();

      if (!returnType.isNoType()) {
        continue;
      }

      if (!isDirectlyAnnotatedWith(method, TypeNames.Markdown)) {
        continue;
      }

      if (markdownMethods == null) {
        markdownMethods = MutableList.create();
      }

      MarkdownMethod markdownMethod;
      markdownMethod = new MarkdownMethod(method);

      markdownMethods.add(markdownMethod);
    }

    if (markdownMethods == null) {
      return new SimpleImpl(
          type.getName()
      );
    } else {
      return new MarkdownImpl(
          type.getName(),

          markdownMethods.toImmutableList()
      );
    }
  }

  private static boolean isDirectlyAnnotatedWith(AnnotatedElementOrType subject, NamedClass name) {
    ImmutableList<ProcessingAnnotation> annotations;
    annotations = subject.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation candidate;
      candidate = annotations.get(i);

      NamedClass className;
      className = candidate.className();

      if (className.equals(name)) {
        return true;
      }
    }

    return false;
  }

  private static class MarkdownImpl extends HtmlBeType {

    private final NamedClass markdownClassName;

    private final ImmutableList<MarkdownMethod> markdownMethods;

    private final NamedClass originalClassName;

    MarkdownImpl(NamedClass originalClassName, ImmutableList<MarkdownMethod> markdownMethods) {
      this.originalClassName = originalClassName;

      this.markdownMethods = markdownMethods;

      markdownClassName = originalClassName.withPrefix("Markdown");
    }

    @Override
    public final boolean generatesIntermediateType() {
      return true;
    }

    @Override
    public final JavaFile intermediateTypeGen() {
      Builder b;
      b = ClassCode.builder();

      b.addAnnotation(BeProcessor.GENERATED);

      b.simpleName(markdownClassName);

      b.superclass(originalClassName);

      for (int i = 0, size = markdownMethods.size(); i < size; i++) {
        MarkdownMethod markdownMethod;
        markdownMethod = markdownMethods.get(i);

        MethodCode method;
        method = markdownMethod.generate();

        b.addMethod(method);
      }

      return Java.javaFile(
          originalClassName.getPackage(),

          b.build()
      );
    }

    @Override
    public final JavaFile resourceIfaceGen() {
      return Java.javaFile(
          originalClassName.getPackage(),

          _interface(
              BeProcessor.GENERATED,
              annotation(MetaBeHtml.class, l(markdownClassName)),
              _public(), originalClassName.withSuffix("Html"), _extends(TypeNames.HtmlResource)
          )
      );
    }

  }

  private static class SimpleImpl extends HtmlBeType {

    private final NamedClass className;

    SimpleImpl(NamedClass className) {
      this.className = className;
    }

    @Override
    public final boolean generatesIntermediateType() {
      return false;
    }

    @Override
    public final JavaFile intermediateTypeGen() {
      throw new UnsupportedOperationException();
    }

    @Override
    public final JavaFile resourceIfaceGen() {
      return Java.javaFile(
          className.getPackage(),

          _interface(
              BeProcessor.GENERATED,
              annotation(MetaBeHtml.class, l(className)),
              _public(), className.withSuffix("Html"), _extends(TypeNames.HtmlResource)
          )
      );
    }

  }

}