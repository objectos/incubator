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
package br.com.objectos.code.model.element;

import br.com.objectos.code.util.SimpleElementVisitor;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

class ProcessingTypeElements {

  final ImmutableList<ProcessingConstructor> constructors;
  final ImmutableList<ProcessingField> fields;
  final ImmutableList<ProcessingMethod> methods;
  final int size;
  final ImmutableList<ProcessingType> types;

  private ProcessingTypeElements(Builder builder) {
    constructors = builder.constructors();
    fields = builder.fields();
    methods = builder.methods();
    types = builder.types();
    size = builder.size;
  }

  static ProcessingTypeElements getDeclared(ProcessingType outer) {
    return new Builder(outer, outer.getEnclosedElements()).build();
  }

  static ProcessingTypeElements getDeclaredOrInherited(ProcessingType outer) {
    return new Builder(outer, outer.getAllMembers()).build();
  }

  static class Builder extends SimpleElementVisitor<Void, Void> {

    private final MutableList<ProcessingConstructor> constructors = MutableList.create();
    private final List<? extends Element> elements;

    private final ProcessingType enclosing;

    private final MutableList<ProcessingField> fields = MutableList.create();
    private final MutableList<ProcessingMethod> methods = MutableList.create();
    private final ProcessingEnvironment processingEnv;
    private int size;

    private final MutableList<ProcessingType> types = MutableList.create();

    Builder(ProcessingType enclosing, List<? extends Element> elements) {
      this.enclosing = enclosing;
      this.elements = elements;

      processingEnv = enclosing.processingEnv();
    }

    public final ProcessingTypeElements build() {
      for (int i = 0; i < elements.size(); i++) {
        Element element;
        element = elements.get(i);

        element.accept(this, null);
      }

      return new ProcessingTypeElements(this);
    }

    @Override
    public final Void visitExecutable(ExecutableElement e, Void p) {
      switch (e.getKind()) {
        case CONSTRUCTOR:
          constructors.add(ProcessingConstructor.adaptUnchecked(processingEnv, e));
          return incrementSize();
        case METHOD:
          methods.add(ProcessingMethod.adaptUnchecked(processingEnv, e));
          return incrementSize();
        case STATIC_INIT:
          // ???
          return null;
        default:
          throw new AssertionError();
      }
    }

    @Override
    public final Void visitType(TypeElement e, Void p) {
      types.add(enclosing.newTypeElementQuery(e));
      return incrementSize();
    }

    @Override
    public final Void visitVariable(VariableElement e, Void p) {
      switch (e.getKind()) {
        case FIELD:
          fields.add(ProcessingField.adaptUnchecked(enclosing, e));
          return incrementSize();
        default:
          return null;
      }
    }

    @Override
    protected final Void defaultAction(Element e, Void p) {
      throw new UnsupportedOperationException("Implement me: kind=" + e.getKind());
    }

    final ImmutableList<ProcessingConstructor> constructors() {
      return constructors.toImmutableList();
    }

    final ImmutableList<ProcessingField> fields() {
      return fields.toImmutableList();
    }

    final ImmutableList<ProcessingMethod> methods() {
      return methods.toImmutableList();
    }

    final ImmutableList<ProcessingType> types() {
      return types.toImmutableList();
    }

    private Void incrementSize() {
      size++;
      return null;
    }

  }

}