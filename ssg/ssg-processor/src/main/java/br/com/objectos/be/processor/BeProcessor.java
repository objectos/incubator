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
import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.core.set.ImmutableSet;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.Processor;

@Services(Processor.class)
public class BeProcessor extends AbstractProcessingRoundProcessor {

  static final AnnotationCode GENERATED = annotation(
      Generated.class,
      l(BeProcessor.class.getCanonicalName())
  );

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(Be.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingType> types = round.getAnnotatedTypes();
    for (ProcessingType type : types) {
      process0(round, type);
    }
    return round.claimTheseAnnotations();
  }

  private void process0(ProcessingRound round, ProcessingType type) {
    try {
      BeType beType;
      beType = BeType.of(type);

      JavaFile iface;
      iface = beType.resourceIfaceGen();

      round.writeJavaFile(iface);

      if (beType.generatesIntermediateType()) {
        JavaFile intermediate;
        intermediate = beType.intermediateTypeGen();

        round.writeJavaFile(intermediate);
      }
    } catch (IOException e) {
      round.printMessageError(e);
    }
  }

}
