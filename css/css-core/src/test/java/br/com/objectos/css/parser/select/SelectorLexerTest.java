/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.parser.select;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.lexer.Analyzer;
import org.testng.annotations.Test;

public class SelectorLexerTest {

  @Test
  public void singles() {
    test("#div0", SelectorToken.HASH, IdentToken.of("div0"));
    test(".active", SelectorToken.DOT, IdentToken.of("active"));
    test("ul", IdentToken.of("ul"));
  }

  @Test
  public void attribute() {
    test("[foo]", SelectorToken.ATTR_OPEN, IdentToken.of("foo"), SelectorToken.ATTR_CLOSE);
  }

  @Test
  public void attribute_valueUnquoted() {
    test("[foo=bar]",
        SelectorToken.ATTR_OPEN,
        IdentToken.of("foo"), AttributeValueOperator.EQUALS, IdentToken.of("bar"),
        SelectorToken.ATTR_CLOSE);
  }

  @Test
  public void attribute_valueQuoted() {
    test("[foo='bar']",
        SelectorToken.ATTR_OPEN,
        IdentToken.of("foo"), AttributeValueOperator.EQUALS, new SingleQuotedString("bar"),
        SelectorToken.ATTR_CLOSE);
  }

  @Test
  public void pseudoClass() {
    test(":hover",
        PseudoClassToken.of("hover"));
    test(":first-of-type",
        PseudoClassToken.of("first-of-type"));
  }

  @Test
  public void pseudoElement() {
    test("::after",
        PseudoElementToken.of("after"));
  }

  @Test
  public void subclassSequence() {
    test("#div0.active",
        SelectorToken.HASH, IdentToken.of("div0"),
        SelectorToken.DOT, IdentToken.of("active"));
  }

  @Test
  public void combinator() {
    IdentToken img = IdentToken.of("img");
    IdentToken p = IdentToken.of("p");
    test("img p", img, Combinator.DESCENDANT, p);
    test("img  p", img, Combinator.DESCENDANT, p);
    test("img > p", img, Combinator.CHILD, p);
    test("img   >      p", img, Combinator.CHILD, p);
  }

  @Test
  public void complexSelector_descendant() {
    test("div#div0 p",
        IdentToken.of("div"),
        SelectorToken.HASH, IdentToken.of("div0"),
        Combinator.DESCENDANT,
        IdentToken.of("p"));
  }
  
  @Test
  public void list() {
    test("b,\nstrong",
        IdentToken.of("b"),
        Separator.COMMA,
        IdentToken.of("strong"));
  }

  private void test(String string, IsTerminal... expected) {
    Analyzer<IsTerminal> analyzer = SelectorLexer.get().analyze(string);
    for (IsTerminal token : expected) {
      assertTrue(analyzer.hasNext());
      assertEquals(analyzer.next(), token);
    }
    assertFalse(analyzer.hasNext());
  }

}