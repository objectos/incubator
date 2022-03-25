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

interface Link {

  static String toString(Link self) {
    return self.toString(new StateWriter()).toString();
  }

  Link acceptBrickMap(BrickMap brickMap);

  default Link acceptLinkMerger(LinkMerger merger) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default State acceptCharExpressionZeroOrMoreNonGreedyStateBuilder(
      CharExpressionZeroOrMoreNonGreedyStateBuilder builder) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  Link addLast(Link last);

  default void concat() {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link concatLink() {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default boolean isConcat() {
    return false;
  }

  default Link merge(Link nextLink) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  Link reverse(Link next);

  default Link toLink() {
    return this;
  }

  default State toState() {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  StateWriter toString(StateWriter w);

}
