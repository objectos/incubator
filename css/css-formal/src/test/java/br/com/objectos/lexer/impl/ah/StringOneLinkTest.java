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

import br.com.objectos.lexer.impl.ah.model.Bit;
import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import br.com.objectos.lexer.impl.ah.model.TokenString;
import org.testng.annotations.Test;

public class StringOneLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharExpressionOneOrMoreLink() {
    StringOneLink thisLink = oneLink("ox", marker("comment"));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(CharExpressions.ABC, marker("x"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'a' ['a','b','c']* x",
        "'b' ['a','b','c']* x",
        "'c' ['a','b','c']* x",
        "'o' 'x' comment");
  }

  @Test
  public void mergeCharExpressionZeroOrMoreLink_stringMatchesExpression() {
    StringOneLink thisLink = oneLink("ab", stringValueLink(trailingLink(Bit.ONE, null)));
    CharExpressionZeroOrMoreLink thatLink = zeroOrMoreLink(
        ABC,
        stringValueLink(trailingLink(TokenString.class, TokenString::new, null)));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'ab' = ['a','b','c'] = ['a','b','c']* | T:<TokenString> )",
        "'ab' = ['a','b','c'] ! | T:<Bit.ONE> )",
        "'ab' ! ['a','b','c']* | T:<TokenString> )");
  }

}