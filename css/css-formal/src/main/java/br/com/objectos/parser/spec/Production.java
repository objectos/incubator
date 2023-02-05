/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.parser.spec;

import java.util.Iterator;
import java.util.Objects;

public abstract class Production {

  public final NonTerminal symbol;
  public final Expression expression;

  Production(NonTerminal symbol, Expression expression) {
    this.symbol = symbol;
    this.expression = Objects.requireNonNull(expression);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Production)) {
      return false;
    }
    Production that = (Production) obj;
    return getClass().equals(that.getClass())
        && symbol.equals(that.symbol)
        && expression.equals(that.expression);
  }

  public abstract Object get(Iterator<?> iterator);

  @Override
  public final int hashCode() {
    return Objects.hash(symbol, expression);
  }

  public final boolean isEmpty() {
    return expression.size() == 1
        && expression.get(0).equals(Empty.INSTANCE);
  }

  public boolean isOptional() {
    return false;
  }

  @Override
  public final String toString() {
    return new StringBuilder()
        .append(toStringSymbol())
        .append(" -> ")
        .append(expression)
        .toString();
  }

  public final String toString(int index) {
    return new StringBuilder()
        .append(toStringSymbol())
        .append(" -> ")
        .append(expression.toString(index))
        .toString();
  }

  public final ValueList valueList() {
    return symbol.valueList();
  }

  abstract void acceptSpecBuilder(Spec.Builder builder);

  final void acceptStage01Builder(Stage01Builder builder) {
    expression.acceptStage01Builder(builder);
  }

  abstract void acceptStage02Builder(Stage02Builder builder, NonTerminal superType);

  abstract String toStringSymbol();

}