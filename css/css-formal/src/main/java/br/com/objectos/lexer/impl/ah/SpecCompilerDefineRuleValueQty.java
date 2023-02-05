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
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleValue;
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleValueQty;

class SpecCompilerDefineRuleValueQty<T, B> implements LexerGrammarDslDefineRuleValueQty<T, B> {

  private final SpecBuilder spec;

  SpecCompilerDefineRuleValueQty(SpecBuilder spec) {
    this.spec = spec;
  }

  @Override
  public LexerGrammarDslDefineRuleValue<T, B> optional() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public LexerGrammarDslDefineRuleValue<T, B> oneOrMore() {
    spec.oneOrMore();
    return new SpecCompilerDefineRuleValue<>(spec);
  }

  @Override
  public final LexerGrammarDslDefineRuleValue<T, B> zeroOrMore() {
    spec.zeroOrMore();
    return new SpecCompilerDefineRuleValue<>(spec);
  }

  @Override
  public LexerGrammarDslDefineRuleValue<T, B> zeroOrMoreNonGreedy() {
    throw new UnsupportedOperationException("Implement me");
  }

  //

  @Override
  public final SpecCompilerDefineRuleValueQty<T, B> addBrick(B brick) {
    spec.addBrickValue(brick);
    return this;
  }

  @Override
  public final LexerGrammarDslDefineRuleValueQty<T, B> addChar(char c) {
    spec.addChar(c);
    return this;
  }

  @Override
  public final SpecCompilerDefineRuleValueQty<T, B> addChar(CharExpression expression) {
    spec.addChar(expression);
    return this;
  }
  
  @Override
  public final LexerGrammarDslDefineRuleValueQty<T, B> addString(String string) {
    spec.addString(string);
    return this;
  }

  @Override
  public final void andPopLexer() {
    spec.andPopLexer();
  }

  @Override
  public final void andPopLexer(int count) {
    spec.andPopLexer(count);
  }

  @Override
  public final void andPushLexer(LexerGrammar<T, B> lexer) {
    spec.andPushLexer(lexer);
  }

  @Override
  public final void andReturnSelf() {
    spec.andReturnSelf();
  }

}