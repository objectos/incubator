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
package br.com.objectos.css.boot.type;

import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;

import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.spec.Step;
import br.com.objectos.css.boot.spec.StepAdapter;

public class PrimitiveTypeStep extends Step {

  public PrimitiveTypeStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addPrimitiveType(PrimitiveType type) {
    writeJavaFile(
        TypeNames.PACKAGE,
        ifaceCode(type)
    );
  }

  private InterfaceCode ifaceCode(PrimitiveType type) {
    return _interface(
        CssBoot.GENERATED,
        _public(), type.typeClassName(), type.extendsClause()
    );
  }
}
