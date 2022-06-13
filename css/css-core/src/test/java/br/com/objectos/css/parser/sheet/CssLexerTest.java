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
package br.com.objectos.css.parser.sheet;

import static br.com.objectos.css.parser.sheet.DeclarationToken.COLON;
import static br.com.objectos.css.parser.sheet.DeclarationToken.SEMI;
import static org.testng.Assert.assertEquals;

import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.UncheckedAnalyzer;
import objectos.util.ImmutableList;
import org.testng.annotations.Test;

public class CssLexerTest extends CssLexerDriver {

  @Test
  public void comment() {
    it().givenCss(""
        + "/* 1 */"
        + "body {\n"
        + "  /*2*/ margin: /*3*/ 0; /*4*/\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("body "),
            Curly.OPEN,
            new Identifier("margin"),
            DeclarationToken.COLON,
            IntValue.valueOf("0"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test(enabled = false, description = "This requires fixes in formal...")
  public void commentHttp() {
    it().givenCss(""
        + "/*\n"
        + " * //g\n"
        + " */"
        + "body {\n"
        + "  /*2*/ margin: /*3*/ 0; /*4*/\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("body "),
            Curly.OPEN,
            new Identifier("margin"),
            DeclarationToken.COLON,
            IntValue.valueOf("0"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test
  public void descendant() {
    it().givenCss("ul li {border:0;}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("ul li "),
            Curly.OPEN,
            new Identifier("border"),
            DeclarationToken.COLON,
            IntValue.valueOf("0"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test
  public void hexColor() {
    test(
        css(
            "x {",
            "  color: #fff;",
            "}"
        ),
        new SelectorToken("x "),
        Curly.OPEN,
        new Identifier("color"),
        DeclarationToken.COLON,
        new ColorValue("#fff"),
        DeclarationToken.SEMI,
        Curly.CLOSE
    );
  }

  @Test
  public void keyword() {
    it().givenCss(""
        + "hr {\n"
        + "  box-sizing: content-box;\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("hr "),
            Curly.OPEN,
            new Identifier("box-sizing"),
            DeclarationToken.COLON,
            new Identifier("content-box"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test
  public void ruleSet_many() {
    it().givenCss(""
        + "html {\n"
        + "  line-height: 1.15;\n"
        + "}\n"
        + "\n"
        + "body {\n"
        + "  margin: 0;\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("html "),
            Curly.OPEN,
            new Identifier("line-height"),
            DeclarationToken.COLON,
            DoubleValue.valueOf("1.15"),
            DeclarationToken.SEMI,
            Curly.CLOSE,
            new SelectorToken("body "),
            Curly.OPEN,
            new Identifier("margin"),
            DeclarationToken.COLON,
            IntValue.valueOf("0"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test
  public void selector() {
    it().givenCss("html {}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("html "),
            Curly.OPEN,
            Curly.CLOSE);
  }

  @Test
  public void selectorList() {
    it().givenCss(""
        + "b,\nstrong {\n"
        + "  font-weight: bolder;\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("b,\nstrong "),
            Curly.OPEN,
            new Identifier("font-weight"),
            DeclarationToken.COLON,
            new Identifier("bolder"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test
  public void stringValue() {
    it().givenCss(""
        + "x {\n"
        + "  prop: \"A\";\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("x "),
            Curly.OPEN,
            new Identifier("prop"),
            DeclarationToken.COLON,
            new StringValue("A"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  @Test
  public void units() {
    it().givenCss(""
        + ".units {\n"
        + "  margin-top: 1px;\n"
        + "  margin-right: 1.2em;\n"
        + "  margin-bottom: 10%;\n"
        + "  margin-left: -2rem;\n"
        + "}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken(".units "),
            Curly.OPEN,
            new Identifier("margin-top"), COLON, IntValue.valueOf("1"), LengthToken.px, SEMI,
            new Identifier("margin-right"), COLON, DoubleValue.valueOf("1.2"), LengthToken.em, SEMI,
            new Identifier("margin-bottom"), COLON, IntValue.valueOf("10"),
            PercentageToken.INSTANCE, SEMI,
            new Identifier("margin-left"), COLON, IntValue.valueOf("-2"), LengthToken.rem, SEMI,
            Curly.CLOSE);
  }

  @Test
  public void zeroValue() {
    it().givenCss("body {margin:0;}")
        .whenAnalyze()
        .thenTokenList(
            new SelectorToken("body "),
            Curly.OPEN,
            new Identifier("margin"),
            DeclarationToken.COLON,
            IntValue.valueOf("0"),
            DeclarationToken.SEMI,
            Curly.CLOSE);
  }

  private String css(String... css) {
    return String.join("\n", css);
  }

  private void test(String css, IsTerminal... expected) {
    Analyzer<IsTerminal> analyzer = CssLexer.get().analyze(css);
    ImmutableList<IsTerminal> tokens = ImmutableList.copyOf(new UncheckedAnalyzer<>(analyzer));
    assertEquals(tokens, ImmutableList.copyOf(expected));
  }

}