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

class ContextDeclarationStart<E extends Exception> extends ContextVisitValue<E> {

  private static final ContextDeclarationStart<Exception> INSTANCE
      = new ContextDeclarationStart<>();

  private ContextDeclarationStart() {}

  @SuppressWarnings("unchecked")
  public static <E extends Exception> Context<E> get() {
    return (Context<E>) INSTANCE;
  }

  @Override
  final Context<E> toNextContext(Adapter<E> a) {
    return toDeclarationValues();
  }

  @Override
  final void visitBeforeValueImpl(Adapter<E> a) throws E {
    // noop
  }

  @Override
  final Context<E> visitMultiDeclarationSeparator(Adapter<E> a) throws E {
    return toDeclarationStart();
  }

}