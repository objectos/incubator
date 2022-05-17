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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import objectos.lang.Checks;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.core.builder.ProblemFactory;

class JdtCompilerBuilderPojo
    implements
    JdtCompilerBuilder,
    JdtCompilerBuilder.SourceVersionStep,
    JdtCompilerBuilder.CompilerRequestorStep,
    JdtCompilerBuilder.AddAnnotationProcessorStep {

  private final AnnotationProcessorManagerBuilder annotationProcessorManager
      = new AnnotationProcessorManagerBuilder();
  private final Map<String, String> optionMap = new HashMap<String, String>();
  private ICompilerRequestor requestor;

  @Override
  public JdtCompiler build() {
    NameEnvironment environment = new NameEnvironment();
    IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.exitAfterAllProblems();
    CompilerOptions options = new CompilerOptions();
    options.set(optionMap);
    IProblemFactory problemFactory = ProblemFactory.getProblemFactory(Locale.getDefault());
    Compiler delegate = new Compiler(environment, policy, options, requestor, problemFactory);
    annotationProcessorManager.set(delegate);
    return new JdtCompiler(delegate);
  }

  @Override
  public final AddAnnotationProcessorStep withAnnotationProcessor(Processor processor) {
    annotationProcessorManager.add(processor);
    return this;
  }

  @Override
  public final CompilerRequestorStep withCompilerRequestor(ICompilerRequestor requestor) {
    this.requestor = Checks.checkNotNull(requestor, "requestor == null");
    return this;
  }

  @Override
  public final SourceVersionStep withSourceVersion(SourceVersion sourceVersion) {
    Checks.checkNotNull(sourceVersion, "sourceVersion == null");
    JdtSourceVersion jdtVersion = JdtSourceVersion.valueOf(sourceVersion.name());
    jdtVersion.set(optionMap);
    return this;
  }

}