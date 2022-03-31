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
package br.com.objectos.css.sheet;

import br.com.objectos.css.property.StandardPropertyName;

class ContextSelector<E extends Exception> extends ContextVisitSelector<E> {

  private static final ContextSelector<Exception> INSTANCE = new ContextSelector<>();

  private ContextSelector() {}

  @SuppressWarnings("unchecked")
  public static <E extends Exception> Context<E> get() {
    return (Context<E>) INSTANCE;
  }

  @Override
  final Context<E> visitDeclarationStart(Adapter<E> a, StandardPropertyName name) throws E {
    a.visitBlockStart();
    a.visitDeclarationStart(name);
    return toDeclarationStart();
  }

  @Override
  final Context<E> visitRuleEnd(Adapter<E> a) throws E {
    a.visitEmptyBlock();
    return toMediaOrSheetBody(a);
  }

}