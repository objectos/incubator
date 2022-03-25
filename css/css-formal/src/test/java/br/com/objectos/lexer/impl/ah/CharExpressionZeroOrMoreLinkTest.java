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
import br.com.objectos.lexer.impl.ah.model.Letter;
import org.testng.annotations.Test;

public class CharExpressionZeroOrMoreLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharExpressionOneOrMoreLink() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(
        CharExpressions._1, new CharArrayLink()
            .merge(oneLink('A', marker("a")))
            .merge(oneLink('B', marker("b"))));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(
        CharExpressions._1,
        trailingLink(Letter.C, marker("c")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "['1','i'] = ['1','i']* 'A' a", 
        "['1','i'] = ['1','i']* 'B' b", 
        "['1','i'] = ['1','i']* T:<Letter.C> )", 
        "['1','i'] ! 'A' a", 
        "['1','i'] ! 'B' b");
  }

  @Test
  public void mergeCharExpressionOneOrMoreLink_notSame() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(CharExpressions.ABC, oneLink('l', marker("letter")));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(CharExpressions._1, oneLink('d', marker("digit")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' ['1','i']* 'd' digit",
        "'a' ['a','b','c']* 'l' letter",
        "'b' ['a','b','c']* 'l' letter",
        "'c' ['a','b','c']* 'l' letter",
        "'i' ['1','i']* 'd' digit",
        "'l' letter");
  }

  @Test
  public void mergeCharExpressionZeroOrMoreLink() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(CharExpressions.NO, oneLink('a', marker("a")));
    CharExpressionZeroOrMoreLink thatLink = zeroOrMoreLink(CharExpressions.NO, oneLink('b', marker("b")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "['N','O']* 'a' a",
        "['N','O']* 'b' b");
  }
  
  @Test
  public void mergeCharSingleOneLink() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(CharExpressions.NO, oneLink('a', marker("a")));
    CharSingleOneLink thatLink = oneLink('b', oneLink('b', marker("b")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'N' ['N','O']* 'a' a",
        "'O' ['N','O']* 'a' a",
        "'a' a",
        "'b' 'b' b");
  }

  @Test
  public void mergeStringOneLink() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(ABC, stringValueLink(marker("identifier")));
    StringOneLink thatLink = oneLink("ab", stringValueLink(marker("keyword")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'ab' = ['a','b','c'] = ['a','b','c']* | identifier",
        "'ab' = ['a','b','c'] ! | keyword",
        "'ab' ! ['a','b','c']* | identifier");
  }
  
  @Test
  public void mergeStringValueLink() {
    CharExpressionZeroOrMoreLink thisLink = zeroOrMoreLink(CharExpressions.NO, oneLink('a', marker("a")));
    StringValueLink thatLink = stringValueLink(marker("sv"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "['N','O'] = ['N','O']* 'a' a",
        "['N','O'] ! 'a' = a",
        "['N','O'] ! 'a' ! | sv");
  }

}
