/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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

import br.com.objectos.core.list.MutableList;
import java.util.Objects;

class Optional extends NonTerminal {

  final Symbol value;

  Optional(Symbol value) {
    this.value = value;
  }

  public static Optional get(Symbol value) {
    Objects.requireNonNull(value);
    return new Optional(value);
  }

  @Override
  public final void acceptStage01Builder(Stage01Builder builder) {
    value.acceptStage01Builder(builder);
  }

  @Override
  public final void acceptStage02Builder(Stage02Builder builder, NonTerminal superType) {
    value.acceptStage02Builder(builder, superType);
  }

  public final void acceptProductionList(MutableList<Production> list) {
    list.add(present());
    list.add(absent());
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String toString() {
    return value.toString() + '?';
  }

  @Override
  final boolean equalsImpl(AbstractSymbol o) {
    Optional that = (Optional) o;
    return value.equals(that.value);
  }

  @Override
  final boolean isSuperType(Class<?> subType) {
    return false;
  }

  private Production absent() {
    return new OptionalProduction(this, Expression.empty());
  }

  private Production present() {
    return new OptionalProduction(this, Expression.of(value));
  }

}