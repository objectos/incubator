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

import br.com.objectos.be.annotations.BeSite;
import br.com.objectos.be.processor.site.BeSiteAnnotatedPackage;
import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.code.processing.type.ErrorTypeException;
import br.com.objectos.core.set.ImmutableSet;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.Processor;

@Services(Processor.class)
public class BeSiteProcessor extends AbstractProcessingRoundProcessor {

  public static final AnnotationCode GENERATED = annotation(
      Generated.class,
      l(BeSiteProcessor.class.getCanonicalName())
  );

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(BeSite.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingPackage> annotatedPackages;
    annotatedPackages = round.getAnnotatedPackages();

    for (ProcessingPackage annotatedPackage : annotatedPackages) {
      process0(round, annotatedPackage);
    }

    return round.claimTheseAnnotations();
  }

  private void process0(ProcessingRound round, ProcessingPackage annotatedPackage) {
    try {
      BeSiteAnnotatedPackage.process(round, annotatedPackage);
    } catch (ErrorTypeException e) {
      round.reprocessPackage(annotatedPackage);
    } catch (IOException e) {
      round.printMessageError(e);
    }
  }

}
