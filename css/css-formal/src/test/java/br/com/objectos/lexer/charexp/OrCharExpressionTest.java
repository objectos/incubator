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

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import org.testng.annotations.Test;

public class OrCharExpressionTest {

  @Test
  public void toCharArray() {
    CharExpression exp = CharExpression.or(
        CharExpression.is('c', '0'),
        CharExpression.is('b', '7'),
        CharExpression.is('a', '3'));
    char[] res = exp.toCharArray();
    assertEquals(Arrays.toString(res), "[0, 3, 7, a, b, c]");
  }

}