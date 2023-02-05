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

import static br.com.objectos.lexer.impl.ah.model.CharExpressions.ABC;

import org.testng.annotations.Test;

public class CharSingleOneLinkTest extends AbstractLinkTest {
  
  @Test
  public void mergeCharExpressionOneOrMoreLink() {
    CharSingleOneLink thisLink = oneLink('1', marker("digit"));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(ABC, marker("letter"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' digit",
        "'a' ['a','b','c']* letter",
        "'b' ['a','b','c']* letter",
        "'c' ['a','b','c']* letter");
  }

  @Test
  public void mergeCharExpressionZeroOrMoreLink() {
    CharSingleOneLink thisLink = oneLink('a', oneLink('1', marker("1")));
    CharExpressionZeroOrMoreLink thatLink = zeroOrMoreLink(ABC, oneLink('x', marker("x")));
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
  
}