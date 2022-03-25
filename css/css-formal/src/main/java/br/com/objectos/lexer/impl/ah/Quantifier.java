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

enum Quantifier {

  ONE("") {
    @Override
    public final Linker link(Consumer consumer, Linker linker) {
      return consumer.linkOne(linker);
    }
  },

  ONE_OR_MORE("+") {
    @Override
    public final Linker link(Consumer consumer, Linker linker) {
      return consumer.linkOneOrMore(linker);
    }
  },

  ZERO_OR_MORE("*") {
    @Override
    public final Linker link(Consumer consumer, Linker linker) {
      return consumer.linkZeroOrMore(linker);
    }
  },

  ZERO_OR_MORE_NON_GREEDY("*?") {
    @Override
    public final Linker link(Consumer consumer, Linker linker) {
      return consumer.linkZeroOrMoreNonGreedy(linker);
    }
  },

  OPTIONAL("?") {
    @Override
    public final Linker link(Consumer consumer, Linker linker) {
      return consumer.linkOptional(linker);
    }
  };

  final String symbol;

  private Quantifier(String symbol) {
    this.symbol = symbol;
  }

  public abstract Linker link(Consumer consumer, Linker linker);

}