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

class CharCharExpression extends CharExpression {

  private final char value;

  CharCharExpression(char value) {
    super(Character.toString(value));
    this.value = value;
  }

  @Override
  public final boolean matches(char next) {
    return value == next;
  }

  @Override
  public final char[] toCharArray() {
    return new char[] { value };
  }

}