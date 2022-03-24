/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

abstract class AbstractLatestProcessor extends AbstractProcessor {

  static final List<? extends TypeMirror> EMPTY_SUPER = Collections.<TypeMirror> emptyList();

  private boolean jdt;

  private Set<String> typesToReprocess;

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    Class<? extends Annotation> annotationType;
    annotationType = getAnnotationType();

    String canonicalName;
    canonicalName = annotationType.getCanonicalName();

    return Collections.singleton(canonicalName);
  }

  @Override
  public final SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public final synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    Class<? extends ProcessingEnvironment> processingEnvClass;
    processingEnvClass = processingEnv.getClass();

    String canonicalName;
    canonicalName = processingEnvClass.getCanonicalName();

    if (canonicalName.startsWith("org.eclipse.jdt")) {
      jdt = true;
    }
  }

  @Override
  public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (typesToReprocess != null) {
      Elements elements;
      elements = processingEnv.getElementUtils();

      for (String typeName : typesToReprocess) {
        TypeElement element;
        element = elements.getTypeElement(typeName);

        processElement(element);
      }

      typesToReprocess.clear();
    }

    for (TypeElement annotation : annotations) {
      Set<? extends Element> elements;
      elements = roundEnv.getElementsAnnotatedWith(annotation);

      process0(elements);
    }

    return false;
  }

  final TypeElement asTypeElement(Element e) {
    return (TypeElement) e;
  }

  final boolean containsErrorType(List<? extends TypeMirror> types) {
    for (int i = 0, size = types.size(); i < size; i++) {
      TypeMirror maybe;
      maybe = types.get(i);

      TypeKind kind;
      kind = maybe.getKind();

      if (kind == TypeKind.ERROR) {
        return true;
      }
    }

    return false;
  }

  final LatestEntry findLatestEntry(TypeElement element) {
    NavigableSet<LatestEntry> implementations;
    implementations = new TreeSet<LatestEntry>();

    TypeMirror superclass;
    superclass = element.asType();

    TypeMirror rawSuperclass;
    rawSuperclass = getRawType(superclass);

    PackageElement packageElement;
    packageElement = Util.getPackageElement(element);

    List<? extends Element> topLevels;
    topLevels = packageElement.getEnclosedElements();

    for (int i = 0; i < topLevels.size(); i++) {
      Element topLevel;
      topLevel = topLevels.get(i);

      TypeElement candidate;
      candidate = TypeElement.class.cast(topLevel);

      List<? extends TypeMirror> superTypes;
      superTypes = getSuperTypes(candidate);

      for (int j = 0, size = superTypes.size(); j < size; j++) {
        TypeMirror superType;
        superType = superTypes.get(j);

        findLatestEntry0(implementations, rawSuperclass, candidate, superType);
      }
    }

    LatestEntry last;
    last = implementations.last();

    if (last == null) {
      throw new JdtMaybeIncrementalCompilationException("latestEntry == null");
    }

    return last;
  }

  abstract Class<? extends Annotation> getAnnotationType();

  abstract List<? extends TypeMirror> getSuperTypes(TypeElement candidate);

  final boolean isClass(Element element) {
    ElementKind kind;
    kind = element.getKind();

    return kind == ElementKind.CLASS;
  }

  final boolean isInterface(Element element) {
    ElementKind kind;
    kind = element.getKind();

    return kind == ElementKind.INTERFACE;
  }

  final void notifyUser(Element element) {

  }

  abstract void processElement(Element element);

  final void reprocessType(TypeElement element) {
    if (typesToReprocess == null) {
      typesToReprocess = new HashSet<String>();
    }

    Name name;
    name = element.getQualifiedName();

    typesToReprocess.add(name.toString());
  }

  private void findLatestEntry0(
      NavigableSet<LatestEntry> implementations,
      TypeMirror rawSuperclass, TypeElement candidate, TypeMirror superType) {
    TypeMirror rawCandidateSuper;
    rawCandidateSuper = getRawType(superType);

    Types types;
    types = processingEnv.getTypeUtils();

    if (types.isSubtype(rawCandidateSuper, rawSuperclass)) {
      Name simpleName;
      simpleName = candidate.getSimpleName();

      String key;
      key = simpleName.toString();

      LatestEntry entry;
      entry = new LatestEntry(key, candidate);

      implementations.add(entry);
    }
  }

  private TypeMirror getRawType(TypeMirror type) {
    Types types;
    types = processingEnv.getTypeUtils();

    return types.erasure(type);
  }

  private void process0(Set<? extends Element> elements) {
    for (Element element : elements) {
      try {
        processElement(element);
      } catch (JdtMaybeIncrementalCompilationException e) {
        processJdtException(e);
      }
    }
  }

  private void processJdtException(JdtMaybeIncrementalCompilationException e) {
    if (!jdt) {
      Messager messager;
      messager = processingEnv.getMessager();

      messager.printMessage(Kind.ERROR, e.getMessage());
    }
  }

}
