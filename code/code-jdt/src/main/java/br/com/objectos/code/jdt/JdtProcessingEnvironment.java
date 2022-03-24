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

import java.util.Collections;
import java.util.Locale;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.apt.dispatch.BaseProcessingEnvImpl;

class JdtProcessingEnvironment extends BaseProcessingEnvImpl {

  public JdtProcessingEnvironment(Compiler compiler) {
    _compiler = compiler;
    _filer = new JdtFiler();
    _messager = new JdtMessager();
    _processorOptions = Collections.emptyMap();
  }

  @Override
  public Locale getLocale() {
    return Locale.getDefault();
  }

}