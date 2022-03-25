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
package br.com.objectos.lexer.impl.ah.model;

import br.com.objectos.lexer.charexp.CharExpression;

public class CharExpressions {

  public static final CharExpression _1 = CharExpression.is('1', 'i');
  public static final CharExpression _123 = CharExpression.is('1', '2', '3');

  public static final CharExpression ABC = CharExpression.is('a', 'b', 'c');
  public static final CharExpression NO = CharExpression.is('N', 'O');
  public static final CharExpression YES = CharExpression.is('Y', 'E', 'S');

  public static final CharExpression WS = CharExpression.is('\n', '\r', '\t', ' ');

  private CharExpressions() {
  }

}