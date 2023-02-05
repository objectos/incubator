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

class Linker {

  private Link currentLink = StartingLink.INSTANCE;
  private Link link = UndefinedTokenLink.INSTANCE;

  public final Linker brickActionLink(SpecAction action) {
    BrickActionLink trailing = new BrickActionLink(action, currentLink);
    Link reversed = trailing.reverse();
    link = link.merge(reversed);
    currentLink = StartingLink.INSTANCE;
    return this;
  }

  public final Linker brickValueOneLink(Object value) {
    currentLink = new BrickValueOneLink(value, currentLink);
    return this;
  }

  public final Linker charExpressionOne(CharExpression expression) {
    currentLink = new CharExpressionOneLink(expression, currentLink);
    return stringValueLink();
  }

  public final Linker charExpressionOneOrMore(CharExpression expression) {
    currentLink = new CharExpressionOneOrMoreLink(expression, currentLink);
    return stringValueLink();
  }

  public final Linker charExpressionZeroOrMore(CharExpression expression) {
    currentLink = new CharExpressionZeroOrMoreLink(expression, currentLink);
    return stringValueLink();
  }

  public final Linker charExpressionZeroOrMoreNonGreedy(CharExpression expression) {
    currentLink = new CharExpressionZeroOrMoreNonGreedyLink(expression, currentLink);
    return stringValueLink();
  }

  public final Linker charExpressionOptional(CharExpression expression) {
    currentLink = new CharExpressionOptionalLink(expression, currentLink);
    return stringValueLink();
  }

  public final Linker charSingleOne(char value) {
    currentLink = new CharSingleOneLink(value, currentLink);
    return stringValueLink();
  }

  public final Linker charSingleOneOrMore(char value) {
    currentLink = new CharSingleOneOrMoreLink(value, currentLink);
    return stringValueLink();
  }

  public final Linker charSingleZeroOrMore(char value) {
    currentLink = new CharSingleZeroOrMoreLink(value, currentLink);
    return stringValueLink();
  }

  public final Linker charSingleOptional(char value) {
    currentLink = new CharSingleOptionalLink(value, currentLink);
    return stringValueLink();
  }

  public final Linker concat() {
    currentLink.concat();
    return this;
  }

  public final Linker stringOneLink(String value) {
    currentLink = new StringOneLink(value, currentLink);
    return stringValueLink();
  }

  public final Link toLink() {
    return link.toLink();
  }

  public final State toState(BrickMap brickMap) {
    link = link.acceptBrickMap(brickMap);
    return link.toState();
  }

  @Override
  public final String toString() {
    return link.toString();
  }

  public final Linker trailingLink(SpecAction action) {
    TrailingLink trailing = new TrailingLink(action, currentLink);
    Link reversed = trailing.reverse();
    link = link.merge(reversed);
    currentLink = StartingLink.INSTANCE;
    return this;
  }

  private Linker stringValueLink() {
    currentLink = new StringValueLink(currentLink);
    return this;
  }

}