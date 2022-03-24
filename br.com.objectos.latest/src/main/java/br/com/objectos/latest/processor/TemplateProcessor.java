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

import br.com.objectos.latest.Template;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Process {@link Template} annotated interfaces.
 */
public final class TemplateProcessor extends AbstractLatestProcessor {

  @Override
  final Class<? extends Annotation> getAnnotationType() {
    return Template.class;
  }

  @Override
  final List<? extends TypeMirror> getSuperTypes(TypeElement candidate) {
    if (!isInterface(candidate)) {
      return EMPTY_SUPER;
    }

    Template.Bridge bridge;
    bridge = candidate.getAnnotation(Template.Bridge.class);

    if (bridge == null) {
      return EMPTY_SUPER;
    }

    return candidate.getInterfaces();
  }

  @Override
  final void processElement(Element element) {
    if (!isInterface(element)) {
      notifyUser(element);

      return;
    }

    TypeElement templateType;
    templateType = asTypeElement(element);

    LatestEntry latestEntry;
    latestEntry = findLatestEntry(templateType);

    TypeElement bridgeType;
    bridgeType = latestEntry.element;

    Template templateAnnotation;
    templateAnnotation = templateType.getAnnotation(Template.class);

    String simpleName;
    simpleName = templateAnnotation.simpleName();

    List<TemplateMethod> templateMethods;
    templateMethods = new ArrayList<TemplateMethod>();

    TemplateMethod.createAll(templateType, templateMethods);

    TemplateMethod.createAll(bridgeType, templateMethods);

    // writing

    LatestWriter w;
    w = new LatestWriter(processingEnv);

    w.writePackageName(templateType);

    w.writeImports();

    w.writeJavadoc("", bridgeType, templateType);

    w.writeGenerated(TemplateProcessor.class);

    w.write("public interface ");

    w.write(simpleName);

    w.writeTypeParameters(templateType);

    String extendsClause;
    extendsClause = templateAnnotation.extendsClause();

    if (!extendsClause.isEmpty()) {
      w.write(" extends ");

      w.write(extendsClause);
    }

    w.write(" {");

    for (int i = 0, size = templateMethods.size(); i < size; i++) {
      TemplateMethod method;
      method = templateMethods.get(i);

      w.newLine();

      w.newLine();

      method.write(w);
    }

    w.newLine();

    w.newLine();

    w.write('}');

    w.flush(templateType, simpleName);
  }

}
