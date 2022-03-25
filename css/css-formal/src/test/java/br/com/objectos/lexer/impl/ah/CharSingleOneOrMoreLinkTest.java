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

import static br.com.objectos.lexer.impl.ah.model.CharExpressions.ABC;

import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import org.testng.annotations.Test;

public class CharSingleOneOrMoreLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharExpressionZeroOrMoreLink() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(ABC, oneLink('x', marker("x")));
    CharSingleOneLink thatLink = oneLink('a', oneLink('1', marker("1")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'a' '1' 1",
        "'a' 'a' ['a','b','c']* 'x' x",
        "'a' 'b' ['a','b','c']* 'x' x",
        "'a' 'c' ['a','b','c']* 'x' x",
        "'a' 'x' x",
        "'b' ['a','b','c']* 'x' x",
        "'c' ['a','b','c']* 'x' x",
        "'x' x");
  }

  @Test
  public void mergeCharExpressionOneOrMoreLink() {
    CharSingleOneOrMoreLink thisLink = oneOrMoreLink('x', marker("space"));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(CharExpressions._1, marker("one"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' ['1','i']* one",
        "'i' ['1','i']* one",
        "'x' 'x'* space");
  }

}