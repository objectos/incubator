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

import java.util.Objects;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

public class Expression {

  private static final Expression EMPTY = of(Symbol.empty());

  private final UnmodifiableList<Symbol> list;

  Expression(UnmodifiableList<Symbol> list) {
    this.list = list;
  }

  static Expression empty() {
    return EMPTY;
  }

  static Expression of(Symbol... symbols) {
    Objects.requireNonNull(symbols);
    GrowableList<Symbol> list = new GrowableList<>();

    for (Symbol symbol : symbols) {
      list.add(Objects.requireNonNull(symbol));
    }

    return new Expression(list.toUnmodifiableList());
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Expression)) {
      return false;
    }
    Expression that = (Expression) obj;
    return list.equals(that.list);
  }

  public final Symbol get(int index) {
    return list.get(index);
  }

  @Override
  public final int hashCode() {
    return list.hashCode();
  }

  public final boolean hasNext(int index) {
    return index < list.size();
  }

  public final int size() {
    return list.size();
  }

  @Override
  public final String toString() {
    return list.join(" ");
  }

  public final String toString(int index) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      if (i == index) {
        sb.append("\u2022");
        sb.append(' ');
      }
      Symbol symbol = list.get(i);
      sb.append(symbol);
      sb.append(' ');
    }
    if (index >= list.size()) {
      sb.append("\u2022");
    }
    return sb.toString();
  }

  final void acceptStage01Builder(Stage01Builder builder) {
    for (Symbol symbol : list) {
      symbol.acceptStage01Builder(builder);
    }
  }

}