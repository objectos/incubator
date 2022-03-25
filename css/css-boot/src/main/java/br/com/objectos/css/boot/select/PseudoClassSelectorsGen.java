/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.boot.select;

import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.boot.spec.StepAdapter;

public class PseudoClassSelectorsGen extends AbstractSelectorStep {

  public PseudoClassSelectorsGen(StepAdapter adapter) {
    super(adapter);
  }

  public static FieldCode styleSheetField(String name) {
    return SelectNames.styleSheetField(
        SelectNames.PseudoClassSelector, toFieldName(name)
    );
  }

  @Override
  public final void addPseudoClass(String name) {
    addPseudo(name, toFieldName(name));
  }

  @Override
  final NamedClass getGeneratedName() {
    return SelectNames.PseudoClassSelectors;
  }

  @Override
  final NamedClass getImplName() {
    return SelectNames.PseudoClassSelector;
  }

}
