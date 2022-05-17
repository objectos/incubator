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
package br.com.objectos.code.jdt;

import br.com.objectos.core.list.Lists;
import java.util.List;
import javax.annotation.processing.Processor;
import objectos.lang.Checks;
import org.eclipse.jdt.internal.compiler.AbstractAnnotationProcessorManager;
import org.eclipse.jdt.internal.compiler.Compiler;

class AnnotationProcessorManagerBuilder {

  private final List<Processor> list = Lists.newArrayList();

  public void add(Processor processor) {
    Checks.checkNotNull(processor, "processor == null");
    list.add(processor);
  }

  public void set(Compiler compiler) {
    if (!list.isEmpty()) {
      set0(compiler);
    }
  }

  private AbstractAnnotationProcessorManager annotationProcessorManager(Compiler compiler) {
    JdtProcessingEnvironment processingEnv = new JdtProcessingEnvironment(compiler);
    return new JdtAnnotationProcessorManager(list, processingEnv);
  }

  private void set0(Compiler compiler) {
    compiler.annotationProcessorManager = annotationProcessorManager(compiler);
    compiler.options.processAnnotations = true;
    compiler.options.storeAnnotations = true; // ???
  }

}