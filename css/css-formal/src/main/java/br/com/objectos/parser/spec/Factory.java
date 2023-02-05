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

import br.com.objectos.parser.grammar.Constructor1;
import br.com.objectos.parser.grammar.Constructor2;
import br.com.objectos.parser.grammar.Constructor3;
import br.com.objectos.parser.grammar.Constructor4;
import br.com.objectos.parser.grammar.Constructor5;
import br.com.objectos.parser.grammar.Constructor6;
import br.com.objectos.parser.grammar.Constructor7;
import java.util.Iterator;

public interface Factory {

  static <E, A1> Factory1<E, A1> factoryOf(Constructor1<E, A1> constructor) {
    return new Factory1<>(constructor);
  }

  static <E, A1, A2> Factory2<E, A1, A2> factoryOf(Constructor2<E, A1, A2> constructor) {
    return new Factory2<>(constructor);
  }

  static <E, A1, A2, A3> Factory3<E, A1, A2, A3> factoryOf(Constructor3<E, A1, A2, A3> constructor) {
    return new Factory3<>(constructor);
  }

  static <E, A1, A2, A3, A4> Factory4<E, A1, A2, A3, A4> factoryOf(
      Constructor4<E, A1, A2, A3, A4> constructor) {
    return new Factory4<>(constructor);
  }

  static <E, A1, A2, A3, A4, A5> Factory5<E, A1, A2, A3, A4, A5> factoryOf(
      Constructor5<E, A1, A2, A3, A4, A5> constructor) {
    return new Factory5<>(constructor);
  }

  static <E, A1, A2, A3, A4, A5, A6> Factory6<E, A1, A2, A3, A4, A5, A6> factoryOf(
      Constructor6<E, A1, A2, A3, A4, A5, A6> constructor) {
    return new Factory6<>(constructor);
  }

  static <E, A1, A2, A3, A4, A5, A6, A7> Factory7<E, A1, A2, A3, A4, A5, A6, A7> factoryOf(
      Constructor7<E, A1, A2, A3, A4, A5, A6, A7> constructor) {
    return new Factory7<>(constructor);
  }

  Object get(Iterator<?> iterator);

  default Production production(NonTerminal symbol, Symbol... expression) {
    return new FactoryProduction(symbol, Expression.of(expression), this);
  }

}