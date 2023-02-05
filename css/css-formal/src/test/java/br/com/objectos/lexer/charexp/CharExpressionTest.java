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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class CharExpressionTest {

  @Test
  public void range() {
    CharExpression zeroToFour = CharExpression.range('0', '4');
    assertMatches(zeroToFour, '0', '1', '2', '3', '4');
    assertNotMatches(zeroToFour, '5', 'a', 'X');
  }

  @Test
  public void notExpression() {
    CharExpression zeroToFour = CharExpression.range('0', '4');
    CharExpression exp = CharExpression.not(zeroToFour);
    assertNotMatches(exp, '0', '1', '2', '3', '4');
    assertMatches(exp, '5', 'a', 'X');
  }

  @Test
  public void or() {
    CharExpression zeroToFour = CharExpression.range('0', '4');
    CharExpression fiveToNine = CharExpression.range('5', '9');
    CharExpression zeroToNine = CharExpression.or(zeroToFour, fiveToNine);
    assertMatches(zeroToNine, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    assertNotMatches(zeroToNine, '~', 'a', 'X');
  }

  @Test
  public void and() {
    CharExpression notSpace = CharExpression.not(' ');
    CharExpression isWhitespace = CharExpression.isWhitespace();
    CharExpression wsExceptSpace = CharExpression.and(isWhitespace, notSpace);
    assertMatches(wsExceptSpace, '\t', '\n');
    assertNotMatches(wsExceptSpace, ' ', 'a', 'X');
  }

  private void assertMatches(CharExpression expression, char... values) {
    for (char c : values) {
      assertTrue(expression.matches(c));
    }
  }

  private void assertNotMatches(CharExpression expression, char... values) {
    for (char c : values) {
      assertFalse(expression.matches(c));
    }
  }

}