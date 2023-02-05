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
import static br.com.objectos.lexer.impl.ah.model.CharExpressions.NO;
import static br.com.objectos.lexer.impl.ah.model.CharExpressions._1;

import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import org.testng.annotations.Test;

public class CharExpressionOneOrMoreLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharExpressionOneOrMoreLink() {
    CharExpressionOneOrMoreLink thisLink = oneOrMoreLink(NO, oneLink('i', marker("int")));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(NO, oneLink('d', marker("double")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "['N','O']+ 'd' double",
        "['N','O']+ 'i' int");
  }

  @Test
  public void mergeCharExpressionOneOrMoreLink_notSame() {
    CharExpressionOneOrMoreLink thisLink = oneOrMoreLink(ABC, oneLink('l', marker("letter")));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(_1, oneLink('d', marker("digit")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' ['1','i']* 'd' digit",
        "'a' ['a','b','c']* 'l' letter",
        "'b' ['a','b','c']* 'l' letter",
        "'c' ['a','b','c']* 'l' letter",
        "'i' ['1','i']* 'd' digit");
  }

  @Test
  public void mergeCharSingleOneLink() {
    CharExpressionOneOrMoreLink thisLink = oneOrMoreLink(
        CharExpressions.ABC,
        marker("abc"));
    CharSingleOneLink thatLink = oneLink('0', marker("zero"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'0' zero",
        "'a' ['a','b','c']* abc",
        "'b' ['a','b','c']* abc",
        "'c' ['a','b','c']* abc");
  }

  @Test
  public void mergeCharSingleOptionalLink() {
    CharExpressionOneOrMoreLink thisLink = oneOrMoreLink(ABC, oneLink('x', marker("id")));
    CharSingleOptionalLink thatLink = optionalLink('a', oneLink('1', marker("digit")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' digit",
        "'a' '1' digit",
        "'a' 'a' ['a','b','c']* 'x' id",
        "'a' 'b' ['a','b','c']* 'x' id",
        "'a' 'c' ['a','b','c']* 'x' id",
        "'a' 'x' id",
        "'b' ['a','b','c']* 'x' id",
        "'c' ['a','b','c']* 'x' id");
  }

}
