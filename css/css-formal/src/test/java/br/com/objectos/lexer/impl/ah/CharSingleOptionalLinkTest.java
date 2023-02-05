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

import org.testng.annotations.Test;

public class CharSingleOptionalLinkTest extends AbstractLinkTest {

  @Test
  public void mergeCharSingleOneLink() {
    CharSingleOptionalLink thisLink = optionalLink('a', oneLink('b', marker("true")));
    CharSingleOneLink thatLink = oneLink('1', marker("false"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'1' false",
        "'a' 'b' true",
        "'b' true");
  }

  @Test
  public void mergeCharSingleOptionalLink() {
    CharSingleOptionalLink thisLink = optionalLink('a', oneLink('i', marker("int")));
    CharSingleOptionalLink thatLink = optionalLink('a', oneLink('d', marker("double")));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'a'? 'd' double",
        "'a'? 'i' int");
  }

  @Test
  public void mergeStringOneLink() {
    CharSingleOptionalLink thisLink = optionalLink('a', oneLink('i', marker("int")));
    StringOneLink thatLink = oneLink("px", marker("unit"));
    Link res = thisLink.merge(thatLink);
    assertToString(res,
        "'a' 'i' int",
        "'i' int",
        "'p' 'x' unit");
  }

}