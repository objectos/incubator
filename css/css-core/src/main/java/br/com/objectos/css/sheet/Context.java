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

abstract class Context<E extends Exception> {

  interface Adapter<E extends Exception> extends CompiledStyleSheetVisitor<E> {

    Body peekBody();

    Body popBody();

    void pushBody(Body body);

  }

  Context() {}

  public static <E extends Exception> Context<E> getStart() {
    return ContextStart.get();
  }

  final Context<E> toDeclarationStart() {
    return ContextDeclarationStart.get();
  }

  final Context<E> toDeclarationValues() {
    return ContextDeclarationValues.get();
  }

  final Context<E> toFunctionStart() {
    return ContextFunctionStart.get();
  }

  final Context<E> toFunctionValues() {
    return ContextFunctionValues.get();
  }

  final Context<E> toMediaQuery() {
    return ContextMediaQuery.get();
  }

  final Context<E> toMediaQueryDeclaration() {
    return ContextMediaQueryDeclaration.get();
  }

  final Context<E> toMediaStart() {
    return ContextAtMediaStart.get();
  }

  final Context<E> toRuleStart() {
    return ContextRuleStart.get();
  }

  final Context<E> toSelector() {
    return ContextSelector.get();
  }

}