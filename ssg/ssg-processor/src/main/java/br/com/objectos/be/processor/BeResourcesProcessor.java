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

import br.com.objectos.be.annotations.BeResources;
import br.com.objectos.be.processor.resources.BeResourcesAnnotatedPackage;
import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.core.set.ImmutableSet;
import java.util.Set;
import javax.annotation.processing.Processor;

@Services(Processor.class)
public class BeResourcesProcessor extends AbstractProcessingRoundProcessor {

  public static final AnnotationCode GENERATED = annotation(
      Generated.class,
      l(BeResourcesProcessor.class.getCanonicalName())
  );

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(BeResources.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingPackage> packages;
    packages = round.getAnnotatedPackages();

    for (ProcessingPackage pkg : packages) {
      BeResourcesAnnotatedPackage.process(round, pkg);
    }

    return round.claimTheseAnnotations();
  }

}
