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

import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

public class JdtCompiler {

  private final Compiler compiler;

  JdtCompiler(Compiler compiler) {
    this.compiler = compiler;
  }

  public static JdtCompilerBuilder builder() {
    return new JdtCompilerBuilderPojo();
  }

  public final void compile(ICompilationUnit... units) {
    compiler.compile(units);
  }

  public final Compiler unwrap() {
    return compiler;
  }

}