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

import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.be.annotations.Be;
import br.com.objectos.be.annotations.Markdown;
import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.set.ImmutableSet;
import java.util.Set;
import javax.annotation.processing.Processor;

@Services(Processor.class)
public final class MarkdownMethodProcessor extends AbstractProcessingRoundProcessor {

  static final AnnotationCode GENERATED = annotation(
    Generated.class,
    l(MarkdownMethodProcessor.class.getCanonicalName())
  );

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(Markdown.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingMethod> methods;
    methods = round.getAnnotatedMethods();

    for (ProcessingMethod m : methods) {
      process0(round, m);
    }

    return round.claimTheseAnnotations();
  }

  private void process0(ProcessingRound round, ProcessingMethod m) {
    ProcessingType declaringType;
    declaringType = m.getDeclaringType();

    ImmutableList<ProcessingAnnotation> annotations;
    annotations = declaringType.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation a;
      a = annotations.get(i);

      PDeclaredType type;
      type = a.getType();

      if (type.isInstanceOf(Be.class)) {
        return;
      }
    }

    MarkdownMethod method;
    method = new MarkdownMethod(m);

    JavaFile f;
    f = method.generateJavaFile();

    try {
      round.writeJavaFile(f);
    } catch (Exception e) {
      round.printMessageError(e);
    }
  }

}
