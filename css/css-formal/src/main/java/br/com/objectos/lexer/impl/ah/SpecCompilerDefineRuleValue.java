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

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleValue;
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleValueQty;

class SpecCompilerDefineRuleValue<T, B> implements LexerGrammarDslDefineRuleValue<T, B> {

  private final SpecBuilder spec;

  SpecCompilerDefineRuleValue(SpecBuilder spec) {
    this.spec = spec;
  }

  @Override
  public final LexerGrammarDslDefineRuleValueQty<T, B> addBrick(B brick) {
    spec.addBrickValue(brick);
    return new SpecCompilerDefineRuleValueQty<>(spec);
  }

  @Override
  public final LexerGrammarDslDefineRuleValueQty<T, B> addChar(char c) {
    spec.addChar(c);
    return new SpecCompilerDefineRuleValueQty<>(spec);
  }

  @Override
  public final LexerGrammarDslDefineRuleValueQty<T, B> addChar(CharExpression expression) {
    spec.addChar(expression);
    return new SpecCompilerDefineRuleValueQty<>(spec);
  }

  @Override
  public final LexerGrammarDslDefineRuleValueQty<T, B> addString(String string) {
    spec.addString(string);
    return new SpecCompilerDefineRuleValueQty<>(spec);
  }

  @Override
  public final void andIgnore() {
    spec.andIgnore();
  }

  @Override
  public final void andPushLexer(LexerGrammar<T, B> lexer) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void andReturnSelf() {
    spec.andReturnSelf();
  }

}