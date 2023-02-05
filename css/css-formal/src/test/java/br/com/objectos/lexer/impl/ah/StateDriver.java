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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.impl.ah.model.IsBrick;
import br.com.objectos.lexer.impl.ah.model.IsToken;
import java.util.Arrays;

class StateDriver extends StateDriverDef implements StateSubject {

  private boolean ignore;
  private LexemeSubject lexemeSubject;

  private Source source;
  private State startingState;
  private State state;
  private ValueList valueList;

  @Override
  public void clear() {}

  @Override
  public void ignore() {
    ignore = true;
  }

  @Override
  public LexemeSubject lexemeSubject() {
    return lexemeSubject;
  }

  @Override
  public void popLexer(int count) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void pushLexer(Spec spec) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public ValueList valueList() {
    return valueList;
  }

  @Override
  void givenGrammarImpl(LexerGrammar<IsToken, IsBrick> grammar) {
    startingState = new SpecCompiler<>(grammar).compile().startingState();
  }

  @Override
  void givenInputImpl(String string) {
    source = new StringSource(string);

    ignore = false;
    state = startingState;
    valueList = new ValueList();
    lexemeSubject = new LexemeSubject(source, valueList);
  }

  @Override
  void thenCharExpressionStateImpl() {
    assertInstanceOf(state, AbstractCharExpressionState.class);
  }

  @Override
  void thenEndStateImpl() {
    assertInstanceOf(state, EndState.class);
  }

  @Override
  void thenHasNextImpl(boolean expected) {
    assertEquals(state.hasNext(), expected);
  }

  @Override
  void thenIgnoreImpl(boolean expected) {
    assertEquals(ignore, expected);
  }

  @Override
  void thenLexemeSubjectImpl(String expected) {
    assertEquals(lexemeSubject.toString(), expected);
  }

  @Override
  void thenMarkerStateImpl(MarkerState expected) {
    assertEquals(state, expected);
  }

  @Override
  void thenReturnEnumStateImpl() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  void thenStateImpl(Class<?> expectedType) {
    assertInstanceOf(state, expectedType);
  }

  @Override
  void thenTrailingStateImpl(Class<?> actionType) {
    assertTrue(state instanceof TrailingState);
    TrailingState trailing = (TrailingState) state;
    assertInstanceOf(trailing.action, actionType);
  }

  @Override
  void thenValueListImpl(Object[] expected) {
    assertEquals(valueList.toList(), Arrays.asList(expected));
  }

  @Override
  void whenNextImpl() {
    state = state.next(this);
  }

  private void assertInstanceOf(Object subject, Class<?> expected) {
    assertTrue(expected.isInstance(subject), "actual class=" + subject.getClass());
  }

}