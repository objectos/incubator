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

class MatcherBlock extends Block {

  private final Quantifier quantifier;
  private final Consumer consumer;

  MatcherBlock(Block block, Quantifier quantifier, Consumer consumer) {
    super(block);
    this.quantifier = quantifier;
    this.consumer = consumer;
  }

  public final State getState() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final Linker link(Linker linker) {
    return block.link(quantifier.link(consumer, linker));
  }

  @Override
  public final Block oneOrMore() {
    return new MatcherBlock(block, Quantifier.ONE_OR_MORE, consumer);
  }

  @Override
  public final Block optional() {
    return new MatcherBlock(block, Quantifier.OPTIONAL, consumer);
  }

  @Override
  public final StartingBlock reverse(Block next) {
    return block.reverse(new MatcherBlock(next, quantifier, consumer));
  }

  @Override
  public final Block zeroOrMore() {
    return new MatcherBlock(block, Quantifier.ZERO_OR_MORE, consumer);
  }

  @Override
  public final Block zeroOrMoreNonGreedy() {
    return new MatcherBlock(block, Quantifier.ZERO_OR_MORE_NON_GREEDY, consumer);
  }

}