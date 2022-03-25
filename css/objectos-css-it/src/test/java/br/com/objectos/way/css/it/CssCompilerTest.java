/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCss it (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.way.css.it;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CssCompilerTest {

  @Test
  public void descendant() {
    assertEquals(new DescendantStyleSheet().toString(), ""
        + "ul li {\n"
        + "  border: 0;\n"
        + "}");
  }

  @Test(enabled = false)
  public void fontFamily_comma() {
    // assertEquals(new FontFamilyCommaStyleSheet().toString(), ""
    // + "p {\n"
    // + " font-family: \"Arial\", sans-serif;\n"
    // + "}");
  }

  @Test
  public void keyword() {
    assertEquals(new KeywordStyleSheet().toString(), ""
        + "hr {\n"
        + "  box-sizing: content-box;\n"
        + "}");
  }

  @Test
  public void lineHeight_doubleValue() {
    assertEquals(new LineHeightDoubleValueStyleSheet().toString(), ""
        + "html {\n"
        + "  line-height: 1.15;\n"
        + "}");
  }

  @Test
  public void marginOverload() {
    assertEquals(new MarginOverloadStyleSheet().toString(), ""
        + ".overload {\n"
        + "  margin: 0;\n"
        + "  margin: 1px 2.1em;\n"
        + "  margin: 10% -3em 47rem;\n"
        + "  margin: 10px 3.47em 15px 1.56rem;\n"
        + "}");
  }

  @Test
  public void normalizeV8() {
    assertEquals(new NormalizeV8StyleSheet().toString(), ""
        + "html {\n" +
        "  line-height: 1.15;\n" +
        "  -webkit-text-size-adjust: 100%;\n" +
        "}\n" +
        "\n" +
        "body {\n" +
        "  margin: 0;\n" +
        "}\n" +
        "\n" +
        "h1 {\n" +
        "  font-size: 2em;\n" +
        "  margin: 0.67em 0;\n" +
        "}\n" +
        "\n" +
        "hr {\n" +
        "  box-sizing: content-box;\n" +
        "  height: 0;\n" +
        "  overflow: visible;\n" +
        "}\n" +
        "\n" +
        "pre {\n" +
        "  font-family: monospace, monospace;\n" +
        "  font-size: 1em;\n" +
        "}\n" +
        "\n" +
        "a {\n" +
        "  background-color: transparent;\n" +
        "}\n" +
        "\n" +
        "abbr[title] {\n" +
        "  border-bottom: none;\n" +
        "  text-decoration: underline;\n" +
        "  text-decoration: underline dotted;\n" +
        "}\n" +
        "\n" +
        "b, strong {\n" +
        "  font-weight: bolder;\n" +
        "}\n" +
        "\n" +
        "code, kbd, samp {\n" +
        "  font-family: monospace, monospace;\n" +
        "  font-size: 1em;\n" +
        "}\n" +
        "\n" +
        "small {\n" +
        "  font-size: 80%;\n" +
        "}\n" +
        "\n" +
        "sub, sup {\n" +
        "  font-size: 75%;\n" +
        "  line-height: 0;\n" +
        "  position: relative;\n" +
        "  vertical-align: baseline;\n" +
        "}\n" +
        "\n" +
        "sub {\n" +
        "  bottom: -0.25em;\n" +
        "}\n" +
        "\n" +
        "sup {\n" +
        "  top: -0.5em;\n" +
        "}\n" +
        "\n" +
        "img {\n" +
        "  border-style: none;\n" +
        "}\n" +
        "\n" +
        "button, input, optgroup, select, textarea {\n" +
        "  font-family: inherit;\n" +
        "  font-size: 100%;\n" +
        "  line-height: 1.15;\n" +
        "  margin: 0;\n" +
        "}\n" +
        "\n" +
        "button, input {\n" +
        "  overflow: visible;\n" +
        "}\n" +
        "\n" +
        "button, select {\n" +
        "  text-transform: none;\n" +
        "}\n" +
        "\n" +
        "button, [type=\"button\"], [type=\"reset\"], [type=\"submit\"] {\n" +
        "  -webkit-appearance: button;\n" +
        "}\n" +
        "\n" +
        "button::-moz-focus-inner, [type=\"button\"]::-moz-focus-inner, [type=\"reset\"]::-moz-focus-inner, [type=\"submit\"]::-moz-focus-inner {\n"
        +
        "  border-style: none;\n" +
        "  padding: 0;\n" +
        "}\n" +
        "\n" +
        "button:-moz-focusring, [type=\"button\"]:-moz-focusring, [type=\"reset\"]:-moz-focusring, [type=\"submit\"]:-moz-focusring {\n"
        +
        "  outline: 1px dotted ButtonText;\n" +
        "}\n" +
        "\n" +
        "fieldset {\n" +
        "  padding: 0.35em 0.75em 0.625em;\n" +
        "}\n" +
        "\n" +
        "legend {\n" +
        "  box-sizing: border-box;\n" +
        "  color: inherit;\n" +
        "  display: table;\n" +
        "  max-width: 100%;\n" +
        "  padding: 0;\n" +
        "  white-space: normal;\n" +
        "}\n" +
        "\n" +
        "progress {\n" +
        "  vertical-align: baseline;\n" +
        "}\n" +
        "\n" +
        "textarea {\n" +
        "  overflow: auto;\n" +
        "}\n" +
        "\n" +
        "[type=\"checkbox\"], [type=\"radio\"] {\n" +
        "  box-sizing: border-box;\n" +
        "  padding: 0;\n" +
        "}\n" +
        "\n" +
        "[type=\"number\"]::-webkit-inner-spin-button, [type=\"number\"]::-webkit-outer-spin-button {\n"
        +
        "  height: auto;\n" +
        "}\n" +
        "\n" +
        "[type=\"search\"] {\n" +
        "  -webkit-appearance: textfield;\n" +
        "  outline-offset: -2px;\n" +
        "}\n" +
        "\n" +
        "[type=\"search\"]::-webkit-search-decoration {\n" +
        "  -webkit-appearance: none;\n" +
        "}\n" +
        "\n" +
        "::-webkit-file-upload-button {\n" +
        "  -webkit-appearance: button;\n" +
        "  font: inherit;\n" +
        "}\n" +
        "\n" +
        "details {\n" +
        "  display: block;\n" +
        "}\n" +
        "\n" +
        "summary {\n" +
        "  display: list-item;\n" +
        "}\n" +
        "\n" +
        "template {\n" +
        "  display: none;\n" +
        "}\n" +
        "\n" +
        "[hidden] {\n" +
        "  display: none;\n" +
        "}");
  }

  @Test
  public void ruleSet_many() {
    assertEquals(new RuleSetManyStyleSheet().toString(), ""
        + "html {\n"
        + "  line-height: 1.15;\n"
        + "}\n"
        + "\n"
        + "body {\n"
        + "  margin: 0;\n"
        + "}");
  }

  @Test
  public void selector() {
    assertEquals(new SelectorStyleSheet().toString(), ""
        + "html {}");
  }

  @Test
  public void units() {
    assertEquals(new UnitsStyleSheet().toString(), ""
        + ".units {\n"
        + "  margin-top: 1px;\n"
        + "  margin-right: 1.2em;\n"
        + "  margin-bottom: 10%;\n"
        + "  margin-left: -2rem;\n"
        + "}");
  }

  @Test
  public void zeroValue() {
    assertEquals(new ZeroValueStyleSheet().toString(), ""
        + "body {\n"
        + "  margin: 0;\n"
        + "}");
  }

}
