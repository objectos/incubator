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

import br.com.objectos.core.object.Checks;
import br.com.objectos.lexer.charexp.CharExpression;
import java.util.Objects;

abstract class Block {

  final Block block;

  Block(Block block) {
    this.block = block;
  }

  public final Block addBrickValue(Object value) {
    return matcherBlock(Quantifier.ONE, new BrickValueConsumer(value));
  }

  public final Block addChar(char c) {
    return matcherBlock(Quantifier.ONE, new CharSingleConsumer(c));
  }

  public final Block addChar(CharExpression expression) {
    Objects.requireNonNull(expression);
    return matcherBlock(Quantifier.ONE, new CharExpressionConsumer(expression));
  }

  public final Block addString(String string) {
    Objects.requireNonNull(string);
    Checks.checkArgument(!string.isEmpty(), "string cannot be empty");
    return string.length() == 1
        ? addChar(string.charAt(0))
        : matcherBlock(Quantifier.ONE, new StringConsumer(string));
  }

  public final BrickActionBlock brickActionBlock(SpecAction action) {
    return new BrickActionBlock(this, action);
  }

  public final Block concat() {
    return new ConcatBlock(this);
  }

  public abstract Linker link(Linker linker);

  public Block oneOrMore() {
    throw new UnsupportedOperationException(
        getClass().getSimpleName() + ".oneOrMore() not supported");
  }

  public Block optional() {
    throw new UnsupportedOperationException(
        getClass().getSimpleName() + ".optional() not supported");
  }

  public abstract StartingBlock reverse(Block next);

  public final TrailingBlock trailingBlock(SpecAction action) {
    return new TrailingBlock(this, action);
  }

  public Block zeroOrMore() {
    throw new UnsupportedOperationException(
        getClass().getSimpleName() + ".zeroOrMore() not supported");
  }

  public Block zeroOrMoreNonGreedy() {
    throw new UnsupportedOperationException(
        getClass().getSimpleName() + ".zeroOrMore() not supported");
  }

  private Block matcherBlock(Quantifier quantifier, Consumer consumer) {
    return new MatcherBlock(this, quantifier, consumer);
  }

}