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

import java.util.Iterator;
import java.util.Objects;
import objectos.util.GrowableList;

abstract class Repetition extends NonTerminal {

  final Symbol value;
  final CollectionKind kind;

  Repetition(Symbol value, CollectionKind kind) {
    this.value = value;
    this.kind = kind;
  }

  static Repetition oneOrMore(Symbol symbol, CollectionKind kind) {
    Objects.requireNonNull(symbol);
    Objects.requireNonNull(kind);
    return new OneOrMore(symbol, kind);
  }

  static Repetition zeroOrMore(Symbol symbol, CollectionKind kind) {
    Objects.requireNonNull(symbol);
    Objects.requireNonNull(kind);
    return new ZeroOrMore(symbol, kind);
  }

  @Override
  public final void acceptStage01Builder(Stage01Builder builder) {
    value.acceptStage01Builder(builder);
  }

  @Override
  public final void acceptStage02Builder(Stage02Builder builder, NonTerminal superType) {
    value.acceptStage02Builder(builder, superType);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(value, kind);
  }

  @Override
  public final String toString() {
    return value.toString() + symbol();
  }

  @Override
  final boolean equalsImpl(AbstractSymbol o) {
    Repetition that = (Repetition) o;
    return value.equals(that.value)
        && kind.equals(that.kind);
  }

  abstract void forTopDown(GrowableList<Production> list);

  final Object get(Iterator<?> iterator) {
    return kind.get(iterator);
  }

  @Override
  final boolean isSuperType(Class<?> subType) {
    return false;
  }

  abstract char symbol();

  @Override
  final ValueList valueList() {
    return kind.valueList();
  }

  static class OneOrMore extends Repetition {

    OneOrMore(Symbol value, CollectionKind kind) {
      super(value, kind);
    }

    @Override
    final void forTopDown(GrowableList<Production> list) {
      list.add(new RepetitionProduction(this, Expression.of(value, this)));
      list.add(new RepetitionProduction(this, Expression.of(value)));
    }

    @Override
    final char symbol() {
      return '+';
    }

  }
  
  static class ZeroOrMore extends Repetition {

    ZeroOrMore(Symbol value, CollectionKind kind) {
      super(value, kind);
    }

    @Override
    final void forTopDown(GrowableList<Production> list) {
      list.add(new RepetitionProduction(this, Expression.of(value, this)));
      list.add(new RepetitionProduction(this, Expression.empty()));
    }

    @Override
    final char symbol() {
      return '*';
    }

  }
  

}