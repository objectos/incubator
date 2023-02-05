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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.lang.Constructor1;
import br.com.objectos.lexer.lang.Constructor2;
import br.com.objectos.lexer.lang.Constructor3;

abstract class SpecBuilderDriverDef {

  final SpecBuilderDriverDef defineBrick(Object value) {
    defineBrickImpl(value);
    return this;
  }

  final SpecBuilderDriverDef defineToken(Class<?> type) {
    defineTokenImpl(type);
    return this;
  }

  final SpecBuilderDriverDef defineToken(Object value) {
    defineTokenImpl(value);
    return this;
  }

  final SpecBuilderDriverDef addBrick(Object value) {
    addBrickImpl(value);
    return this;
  }

  final SpecBuilderDriverDef addChar(char c) {
    addCharImpl(c);
    return this;
  }

  final SpecBuilderDriverDef addChar(CharExpression expression) {
    addCharImpl(expression);
    return this;
  }

  final SpecBuilderDriverDef addString(String string) {
    addStringImpl(string);
    return this;
  }

  final <E, A1> SpecBuilderDriverDef andCreateWith(Constructor1<E, A1> constructor) {
    andCreateWithImpl(constructor);
    return this;
  }

  final <E, A1, A2> SpecBuilderDriverDef andCreateWith(Constructor2<E, A1, A2> constructor) {
    andCreateWithImpl(constructor);
    return this;
  }

  final <E, A1, A2, A3> SpecBuilderDriverDef andCreateWith(Constructor3<E, A1, A2, A3> constructor) {
    andCreateWithImpl(constructor);
    return this;
  }

    final SpecBuilderDriverDef andIgnore() {
    andIgnoreImpl();
    return this;
  }

  final SpecBuilderDriverDef andReturnSelf() {
    andReturnSelfImpl();
    return this;
  }

  final SpecBuilderDriverDef andReturnSelf(Object value) {
    andReturnSelfImpl(value);
    return this;
  }

  final SpecBuilderDriverDef concat() {
    concatImpl();
    return this;
  }

  final SpecBuilderDriverDef oneOrMore() {
    oneOrMoreImpl();
    return this;
  }

  final SpecBuilderDriverDef zeroOrMore() {
    zeroOrMoreImpl();
    return this;
  }

  final SpecBuilderDriverDef thenStateToString(String... expected) {
    thenStateToStringImpl(expected);
    return this;
  }

  SpecBuilderDriverDef it() {
    return this;
  }

  abstract void defineBrickImpl(Object value);

  abstract void defineTokenImpl(Class<?> type);

  abstract void defineTokenImpl(Object value);

  abstract void addBrickImpl(Object value);

  abstract void addCharImpl(char c);

  abstract void addCharImpl(CharExpression expression);

  abstract void addStringImpl(String string);

  abstract <E, A1> void andCreateWithImpl(Constructor1<E, A1> constructor);

  abstract <E, A1, A2> void andCreateWithImpl(Constructor2<E, A1, A2> constructor);

  abstract <E, A1, A2, A3> void andCreateWithImpl(Constructor3<E, A1, A2, A3> constructor);

  abstract void andIgnoreImpl();

  abstract void andReturnSelfImpl();

  abstract void andReturnSelfImpl(Object value);

  abstract void concatImpl();

  abstract void oneOrMoreImpl();

  abstract void zeroOrMoreImpl();

  abstract void thenStateToStringImpl(String[] expected);

}