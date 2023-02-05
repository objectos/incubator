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
package br.com.objectos.lexer.charexp;

class PredicateCharExpression extends CharExpression {

  private final CharPredicate predicate;

  PredicateCharExpression(String description, CharPredicate predicate) {
    super(description);
    this.predicate = predicate;
  }

  @Override
  public final boolean matches(char next) {
    return predicate.test(next);
  }

  @Override
  public final char[] toCharArray() {
    StringBuilder sb = new StringBuilder();

    for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
      if (predicate.test(c)) {
        sb.append(c);
      }
    }

    return sb.toString().toCharArray();
  }

}