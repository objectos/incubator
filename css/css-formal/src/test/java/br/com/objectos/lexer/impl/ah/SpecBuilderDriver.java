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
package br.com.objectos.lexer.impl.ah;

import static org.testng.Assert.assertEquals;

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.lang.Constructor1;
import br.com.objectos.lexer.lang.Constructor2;
import br.com.objectos.lexer.lang.Constructor3;
import java.util.Arrays;
import java.util.List;

class SpecBuilderDriver extends SpecBuilderDriverDef {

  private SpecBuilder spec;

  @Override
  void defineBrickImpl(Object value) {
    spec.brickValue(value);
  }

  @Override
  void defineTokenImpl(Class<?> type) {
    spec.tokenType(type);
  }

  @Override
  void defineTokenImpl(Object value) {
    spec.tokenValue(value);
  }

  @Override
  void addBrickImpl(Object value) {
    spec.addBrickValue(value);
  }

  @Override
  void addCharImpl(char c) {
    spec.addChar(c);
  }

  @Override
  void addCharImpl(CharExpression expression) {
    spec.addChar(expression);
  }

  @Override
  void addStringImpl(String string) {
    spec.addString(string);
  }

  @Override
  <E, A1> void andCreateWithImpl(Constructor1<E, A1> constructor) {
    spec.andCreateWith(constructor);
  }

  @Override
  <E, A1, A2> void andCreateWithImpl(Constructor2<E, A1, A2> constructor) {
    spec.andCreateWith(constructor);
  }

  @Override
  <E, A1, A2, A3> void andCreateWithImpl(Constructor3<E, A1, A2, A3> constructor) {
    spec.andCreateWith(constructor);
  }

  @Override
  void andIgnoreImpl() {
    spec.andIgnore();
  }

  @Override
  void andReturnSelfImpl() {
    spec.andReturnSelf();
  }

  @Override
  void andReturnSelfImpl(Object value) {
    spec.andReturnSelf(value, 1);
  }

  @Override
  void concatImpl() {
    spec.concat();
  }

  @Override
  void oneOrMoreImpl() {
    spec.oneOrMore();
  }

  @Override
  void zeroOrMoreImpl() {
    spec.zeroOrMore();
  }

  @Override
  void thenStateToStringImpl(String[] expected) {
    State state = spec.state();
    List<String> toStringList = Arrays.asList(state.toString().split("\n"));
    assertEquals(toStringList, Arrays.asList(expected));
  }

  @Override
  SpecBuilderDriverDef it() {
    spec = new SpecBuilder();
    return super.it();
  }

}