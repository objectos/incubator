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

import br.com.objectos.lexer.grammar.LexerGrammarDslQuantifierDsl;

abstract class AbstractSpecCompilerQuantifier<QTY, T, B>
    implements
    LexerGrammarDslQuantifierDsl<QTY> {

  final SpecBuilder spec;

  AbstractSpecCompilerQuantifier(SpecBuilder spec) {
    this.spec = spec;
  }

  public final void andIgnore() {
    spec.andIgnore();
  }

  @Override
  public final QTY zeroOrMore() {
    spec.zeroOrMore();
    return qself();
  }

  @Override
  public final QTY zeroOrMoreNonGreedy() {
    spec.zeroOrMoreNonGreedy();
    return qself();
  }

  @Override
  public final QTY oneOrMore() {
    spec.oneOrMore();
    return qself();
  }

  abstract QTY qself();

}