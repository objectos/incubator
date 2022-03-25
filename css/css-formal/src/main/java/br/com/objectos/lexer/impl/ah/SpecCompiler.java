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

import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.grammar.LexerGrammarDsl;
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleType;
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleValue;
import java.util.Objects;

public class SpecCompiler<T, B> extends SpecBuilder implements LexerGrammarDsl<T, B> {

  private final LexerGrammar<T, B> grammar;

  public SpecCompiler(LexerGrammar<T, B> grammar) {
    this.grammar = Objects.requireNonNull(grammar);
  }

  public final Spec compile() {
    grammar.acceptLexerGrammarDsl(this);
    return compileImpl();
  }

  @Override
  public final <E extends B> LexerGrammarDslDefineRuleType<E, T, B> defineBrick(Class<E> brickType) {
    Objects.requireNonNull(brickType);
    return new SpecCompilerDefineRuleType<>(brickType(brickType));
  }

  @Override
  public final LexerGrammarDslDefineRuleValue<T, B> defineBrick(B brickValue) {
    Objects.requireNonNull(brickValue);
    return new SpecCompilerDefineRuleValue<>(brickValue(brickValue));
  }

  @Override
  public final <E extends T> LexerGrammarDslDefineRuleType<E, T, B> defineToken(Class<E> tokenType) {
    Objects.requireNonNull(tokenType);
    return new SpecCompilerDefineRuleType<>(tokenType(tokenType));
  }

  @Override
  public final LexerGrammarDslDefineRuleValue<T, B> defineToken(T tokenValue) {
    Objects.requireNonNull(tokenValue);
    return new SpecCompilerDefineRuleValue<>(tokenValue(tokenValue));
  }

}