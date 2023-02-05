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
import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.lang.Constructor1;
import br.com.objectos.lexer.lang.Constructor2;
import br.com.objectos.lexer.lang.Constructor3;
import java.util.EnumSet;
import java.util.Objects;
import objectos.lang.Check;

class SpecBuilder {

  private Block block;
  private final BrickMap brickMap = new BrickMap();

  private Kind kind;

  private final Linker linker = new Linker();
  private int rank;
  private Class<?> type;
  private Object value;

  SpecBuilder() {}

  public final void addBrickValue(Object value) {
    block = block.addBrickValue(value);
  }

  public final void addChar(char c) {
    block = block.addChar(c);
  }

  public final void addChar(CharExpression expression) {
    block = block.addChar(expression);
  }

  public final void addString(String string) {
    block = block.addString(string);
  }

  public final <E, A1> void andCreateWith(Constructor1<E, A1> constructor) {
    Objects.requireNonNull(constructor);
    CreateWith createWith = CreateWith.of(constructor);
    CreateWithAction action = new CreateWithAction(rank(), type, createWith);
    onSpecAction(action);
  }

  public final <E, A1, A2> void andCreateWith(Constructor2<E, A1, A2> constructor) {
    Objects.requireNonNull(constructor);
    CreateWith createWith = CreateWith.of(constructor);
    CreateWithAction action = new CreateWithAction(rank(), type, createWith);
    onSpecAction(action);
  }

  public final <E, A1, A2, A3> void andCreateWith(Constructor3<E, A1, A2, A3> constructor) {
    Objects.requireNonNull(constructor);
    CreateWith createWith = CreateWith.of(constructor);
    CreateWithAction action = new CreateWithAction(rank(), type, createWith);
    onSpecAction(action);
  }

  public final void andIgnore() {
    IgnoreAction action = IgnoreAction.INSTANCE;
    onSpecAction(action);
  }

  public final void andPopLexer() {
    andPopLexer(1);
  }

  public final void andPopLexer(int count) {
    ReturnSelfAction delegate = new ReturnSelfAction(rank(), value);
    PopLexerAction action = new PopLexerAction(delegate, count);
    onSpecAction(action);
  }

  public final <T, B> void andPushLexer(LexerGrammar<T, B> lexer) {
    ReturnSelfAction delegate = new ReturnSelfAction(rank(), value);
    Spec spec = new SpecCompiler<>(lexer).compile();
    PushLexerAction action = new PushLexerAction(delegate, spec);
    onSpecAction(action);
  }

  public final void andReturnSelf() {
    ReturnSelfAction action = new ReturnSelfAction(rank(), value);
    onSpecAction(action);
  }

  public final void andReturnSelf(Object value, int arity) {
    ReturnSelfAction action = new ReturnSelfAction(rank(), value);
    onSpecAction(action);
    block = new StartingBlock();
  }

  public final SpecBuilder brickType(Class<?> type) {
    return kindType(Kind.BRICK, type);
  }

  public final SpecBuilder brickValue(Object value) {
    return kindValue(Kind.BRICK, value);
  }

  public final void concat() {
    block = block.concat();
  }

  public final void oneOrMore() {
    block = block.oneOrMore();
  }

  public final void optional() {
    block = block.optional();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public final EnumSet<?> toEnumSet() {
    Check.argument(type.isEnum(), type, " is not an enum type.");
    Class<? extends Enum> enumType = (Class<? extends Enum>) type;
    return EnumSet.allOf(enumType);
  }

  public final SpecBuilder tokenType(Class<?> type) {
    return kindType(Kind.TOKEN, type);
  }

  public final SpecBuilder tokenValue(Object value) {
    return kindValue(Kind.TOKEN, value);
  }

  public final void zeroOrMore() {
    block = block.zeroOrMore();
  }

  public final void zeroOrMoreNonGreedy() {
    block = block.zeroOrMoreNonGreedy();
  }

  final Spec compileImpl() {
    return new SingleStateSpec(state());
  }

  final void onSpecActionBrick(SpecAction action) {
    BrickActionBlock brickBlock = block.brickActionBlock(action);
    Link link = brickBlock.toLink();
    brickBlock.onSpecActionBrick(brickMap, link);
  }

  final void onSpecActionToken(SpecAction action) {
    TrailingBlock trailingBlock = block.trailingBlock(action);
    trailingBlock.acceptLinker(linker);
  }

  final State state() {
    return linker.toState(brickMap);
  }

  private SpecBuilder kindType(Kind kind, Class<?> type) {
    reset();
    this.kind = kind;
    this.type = Objects.requireNonNull(type);
    return this;
  }

  private SpecBuilder kindValue(Kind kind, Object value) {
    reset();
    this.kind = kind;
    this.value = Objects.requireNonNull(value);
    return this;
  }

  private void onSpecAction(SpecAction action) {
    kind.onSpecAction(this, action);
  }

  private int rank() {
    return rank++;
  }

  private void reset() {
    block = new StartingBlock();
    kind = null;
    type = null;
    value = null;
  }

  private enum Kind {

    BRICK {
      @Override
      final void onSpecAction(SpecBuilder outer, SpecAction action) {
        outer.onSpecActionBrick(action);
      }
    },

    TOKEN {
      @Override
      final void onSpecAction(SpecBuilder outer, SpecAction action) {
        outer.onSpecActionToken(action);
      }
    };

    abstract void onSpecAction(SpecBuilder outer, SpecAction action);

  }

}