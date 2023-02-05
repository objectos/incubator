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

import br.com.objectos.lexer.impl.ah.model.Answer;
import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import org.testng.annotations.Test;

public class CharArrayLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharExpressionZeroOrMoreLink() {
    Link res = new CharArrayLink()
        .merge(oneLink(CharExpressions.YES, marker("yes")))
        .merge(zeroOrMoreLink(CharExpressions.NO, oneLink('x', marker("x"))));
    assertToString(res,
        "'E' yes",
        "'N' ['N','O']* 'x' x",
        "'O' ['N','O']* 'x' x",
        "'S' yes",
        "'Y' yes",
        "'x' x");
  }

  @Test
  public void mergeCharExpressionZeroOrMoreLink_shouldSetFailLink() {
    Link res = new CharArrayLink()
        .merge(oneLink('a', oneLink('1', marker("1"))))
        .merge(zeroOrMoreLink(ABC, oneLink('x', marker("x"))));
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
  public void mergeCharSingleOptionalLink() {
    Link res = new CharArrayLink()
        .merge(oneLink(CharExpressions.YES, marker("yes")))
        .merge(optionalLink('z', oneLink('x', marker("x"))));
    assertToString(res,
        "'E' yes",
        "'S' yes",
        "'Y' yes",
        "'x' x",
        "'z' 'x' x");
  }

  @Test
  public void mergeStringOneLink() {
    Link res = new CharArrayLink()
        .merge(oneLink(CharExpressions.YES, marker("yes")))
        .merge(oneLink("abc", marker("x")));
    assertToString(res,
        "'E' yes",
        "'S' yes",
        "'Y' yes",
        "'a' 'bc' x");
  }

  @Test
  public void mergeTrailingLink() {
    Link res = new CharArrayLink()
        .merge(oneLink(CharExpressions.YES, marker("yes")))
        .merge(trailingLink(Answer.NO, marker("x")));
    assertToString(res,
        "'E' yes",
        "'S' yes",
        "'Y' yes",
        "T:<Answer.NO> )");
  }

}