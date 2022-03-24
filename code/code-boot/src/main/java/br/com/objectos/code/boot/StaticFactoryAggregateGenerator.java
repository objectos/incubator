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
package br.com.objectos.code.boot;

import br.com.objectos.code.annotations.GenerateStaticFactoryAggregate;
import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.core.set.ImmutableSet;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.Processor;

@Services(Processor.class)
public class StaticFactoryAggregateGenerator extends AbstractProcessingRoundProcessor {

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(GenerateStaticFactoryAggregate.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingPackage> packages = round.getAnnotatedPackages();
    for (ProcessingPackage pkg : packages) {
      process0(round, pkg);
    }
    return round.claimTheseAnnotations();
  }

  private JavaFile generate(ProcessingPackage pkg) {
    GenerateStaticFactoryAggregateAnnotation annotation;
    annotation = GenerateStaticFactoryAggregateAnnotation.of(pkg);
    return annotation.generate();
  }

  private void process0(ProcessingRound round, ProcessingPackage pkg) {
    try {
      JavaFile generated = generate(pkg);
      round.writeJavaFile(generated);
    } catch (IOException e) {
      round.printMessageError(e);
    }
  }

}
