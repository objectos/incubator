/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.boot.spec;

import br.com.objectos.css.boot.function.FunctionName;

final class FunctionInterfaceStep extends ThisTemplate {

  private FunctionName functionName;

  public FunctionInterfaceStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addFunction(FunctionName function) {
    functionName = function;

    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(function);

    autoImports();

    _public();
    _interface(functionName.singleDeclarationSimpleName());
    superInterfaces();
    body();
  }

  private void superInterfaces() {
    var interfaceSet = functionName.interfaceSet;

    if (interfaceSet.isEmpty()) {
      return;
    }

    _extends();

    for (var name : interfaceSet) {
      t(type, name);
    }
  }

}
