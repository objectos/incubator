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
import static br.com.objectos.lexer.impl.ah.model.CharExpressions._123;

import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import org.testng.annotations.Test;

public class CharExpressionOneLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharExpressionOneOrMoreLink() {
    CharExpressionOneLink thisLink = oneLink(ABC, marker("x"));
    CharExpressionOneOrMoreLink thatLink = oneOrMoreLink(_123, marker("0"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' ['1','2','3']* 0",
        "'2' ['1','2','3']* 0",
        "'3' ['1','2','3']* 0",
        "'a' x",
        "'b' x",
        "'c' x");
  }
  
  @Test
  public void mergeCharSingleOneLink() {
    CharExpressionOneLink thisLink = oneLink(CharExpressions.YES, marker("yes"));
    CharSingleOneLink thatLink = oneLink('x', marker("x"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'E' yes",
        "'S' yes",
        "'Y' yes",
        "'x' x");
  }

}