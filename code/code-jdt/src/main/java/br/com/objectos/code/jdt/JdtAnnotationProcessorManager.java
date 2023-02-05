/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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

import java.util.Iterator;
import java.util.List;
import javax.annotation.processing.Processor;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.apt.dispatch.BaseAnnotationProcessorManager;
import org.eclipse.jdt.internal.compiler.apt.dispatch.ProcessorInfo;

class JdtAnnotationProcessorManager extends BaseAnnotationProcessorManager {

  private final Iterator<Processor> processorIter;

  public JdtAnnotationProcessorManager(List<Processor> processorList,
                                       JdtProcessingEnvironment processingEnv) {
    processorIter = processorList.iterator();
    _processingEnv = processingEnv;
  }

  public void configureFromPlatform(
      Compiler compiler, Object compilationUnitLocator, Object javaProject) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProcessorInfo discoverNextProcessor() {
    if (processorIter.hasNext()) {
      Processor processor = processorIter.next();
      processor.init(_processingEnv);
      ProcessorInfo procecssorInfo = new ProcessorInfo(processor);
      _processors.add(procecssorInfo);
      return procecssorInfo;
    }
    return null;
  }

  @Override
  public void reportProcessorException(Processor p, Exception e) {}

}