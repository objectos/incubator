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

import static org.testng.Assert.assertEquals;

import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import org.testng.annotations.Test;

public class StringSourceTest {

  @Test
  public void advance() {
    Source source = source("YES");
    source.advance("YE");
    assertEquals(source.nextChar(), 'S');
  }

  @Test
  public void matchesChar() {
    Source source = source("YES");
    assertEquals(source.matchesChar(CharExpressions.YES), true);
    assertEquals(source.matchesChar(CharExpressions.WS), false);
  }

  @Test
  public void matchesChar_eof() {
    Source source = source("");
    assertEquals(source.matchesChar(CharExpressions.YES), false);
    assertEquals(source.matchesChar(CharExpressions.WS), false);
  }

  @Test
  public void matchesString() {
    Source source = source("abc");
    assertEquals(source.matchesString("a"), true);
    assertEquals(source.matchesString("ab"), true);
    assertEquals(source.matchesString("abc"), true);
    assertEquals(source.matchesString("abcd"), false);
  }

  @Test
  public void peekChar() {
    Source s = source("YES");
    assertEquals(s.peekChar(), 'Y');
    s.advance();
    assertEquals(s.peekChar(), 'E');
    s.advance();
    assertEquals(s.peekChar(), 'S');
  }

  @Test
  public void peekString_charExpression() {
    assertEquals(source("YES").peekString(CharExpressions.YES), "YES");
    assertEquals(source("YESES").peekString(CharExpressions.YES), "YESES");
    assertEquals(source("YESNO").peekString(CharExpressions.YES), "YES");
    assertEquals(source("X").peekString(CharExpressions.YES), "");
  }

  @Test
  public void peekString_string() {
    Source source = source("abc*/xyz");
    assertEquals(source.peekString("*/").get(), "abc");
    assertEquals(source.peekString("*/!").isPresent(), false);
    assertEquals(source.peekString("abc").get(), "");
    assertEquals(source.peekString("%").isPresent(), false);
  }

  @Test
  public void peekString_charSingle() {
    assertEquals(source("Yes").peekString('Y'), "Y");
    assertEquals(source("YYYes").peekString('Y'), "YYY");
    assertEquals(source("X").peekString('Y'), "");
  }

  private Source source(String string) {
    return new StringSource(string);
  }

}