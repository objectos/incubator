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

import java.util.Objects;
import objectos.util.ImmutableList;

public abstract class NonTerminal extends AbstractSymbol implements Repeatable {

  NonTerminal() {}

  public static NonTerminal get(Class<?> type) {
    return new One(type);
  }

  @Override
  public final <R, P> R acceptSymbolVisitor(SymbolVisitor<R, P> visitor, P p) {
    return visitor.visitNonTerminal(this, p);
  }

  public final Production toGoalProduction() {
    return new GoalProduction(this);
  }

  abstract boolean isSuperType(Class<?> subType);

  final Expression toGoalExpression() {
    ImmutableList<Symbol> list = ImmutableList.of(this, Symbol.eof());
    return new Expression(list);
  }

  ValueList valueList() {
    return new ValueList();
  }

  private static class One extends NonTerminal {

    private final Class<?> type;

    One(Class<?> type) {
      this.type = Objects.requireNonNull(type);
    }

    @Override
    public final void acceptStage01Builder(Stage01Builder builder) {
      builder.addNonTerminalSet(this);
    }

    @Override
    public final void acceptStage02Builder(Stage02Builder builder, NonTerminal superType) {
      if (superType.isSuperType(type)) {
        builder.addProduction(new PolymorphicProduction(superType, this));
      }
    }

    @Override
    public final int hashCode() {
      return type.hashCode();
    }

    @Override
    public final String toString() {
      return "<" + type.getSimpleName() + ">";
    }

    @Override
    final boolean equalsImpl(AbstractSymbol o) {
      One that = (One) o;
      return type.equals(that.type);
    }

    @Override
    final boolean isSuperType(Class<?> subType) {
      return !type.equals(subType)
          && type.isAssignableFrom(subType);
    }

  }

}