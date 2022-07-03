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

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.css.sheet.StyleSheetWriter;
import org.testng.annotations.Test;

public class CssParserTest {

  @Test
  public void descendant() {
    test(
      css(
        "ul li {border:0;}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            ul, sp(), li,

            border(zero())
          );
        }
      }
    );
  }

  @Test
  public void fontFamilyTest() {
    test(
      css(
        "textarea {",
        "  font-family: inherit; /* 1 */",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            textarea,
            fontFamily(inherit)
          );
        }
      }
    );
    test(
      css(
        "p {",
        "  font-family: Arial, Helvetica;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            p,
            fontFamily(
              fontFamily("Arial"),
              fontFamily("Helvetica")
            )
          );
        }
      }
    );
    test(
      css(
        "p {",
        "  font-family: sans-serif, \"Foo Sans\", Arial;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            p,
            fontFamily(
              fontFamily(sansSerif),
              fontFamily("Foo Sans"),
              fontFamily("Arial")
            )
          );
        }
      }
    );
    test(
      css(
        "html {",
        "  font-family: system-ui, -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto; /* 1 */",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            html,
            fontFamily(
              fontFamily(keyword("system-ui")),
              fontFamily(keyword("-apple-system")),
              fontFamily(keyword("BlinkMacSystemFont")),
              fontFamily("Segoe UI"),
              fontFamily(keyword("Roboto"))
            )
          );
        }
      }
    );
  }

  @Test
  public void hexColorValue() {
    test(
      css(
        "a {",
        "  color: #fff;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            a,

            color(hex("#fff"))
          );
        }
      }
    );
  }

  @Test
  public void keyword() {
    test(
      css(
        "hr {",
        "  box-sizing: content-box;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            hr,

            boxSizing(contentBox)
          );
        }
      }
    );
  }

  @Test
  public void lineHeight_doubleValue() {
    test(
      css(
        "html {",
        "  line-height: 1.15;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            html,

            lineHeight(1.15)
          );
        }
      }
    );
  }

  @Test(enabled = false)
  public void marginOverload() {
    test(
      css(
        ".overload {",
        "  margin: 0;",
        "  margin: 1px 2.1em;",
        "  margin: 10% -3em 47rem;",
        "  margin: 10px 3.47em 15px 1.56rem;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            cn("overload"),

            margin(zero()),
            margin(px(1), em(2.1)),
            margin(pct(10), em(-3), rem(47)),
            margin(px(10), em(3.47), px(15), rem(1.56))
          );
        }
      }
    );
  }

  @Test
  public void marginOverload_withZero() {
    test(
      css(
        ".overload-zero {",
        "  margin: 0.67em 0;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            cn("overload-zero"),

            margin(em(0.67), zero())
          );
        }
      }
    );
  }

  @Test
  public void ruleSet_many() {
    test(
      css(
        "html {",
        "  line-height: 1.15;",
        "}",
        "",
        "body {",
        "  margin: 0;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            html,

            lineHeight(1.15)
          );
          style(
            body,

            margin(zero())
          );
        }
      }
    );
  }

  @Test
  public void selector() {
    test(
      css("html {}"),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(html);
        }
      }
    );
  }

  @Test
  public void selectorList() {
    test(
      css(
        "b,\nstrong {",
        "  font-weight: bolder;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            b, or(), strong,
            fontWeight(bolder)
          );
        }
      }
    );
  }

  @Test
  public void systemColor() {
    test(
      css(
        ".system-color {",
        "  outline: 1px dotted ButtonText;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            cn("system-color"),

            outline(px(1), dotted, ButtonText)
          );
        }
      }
    );
  }

  @Test
  public void units() {
    test(
      css(
        ".units {",
        "  margin-top: 1px;",
        "  margin-right: 1.2em;",
        "  margin-bottom: 10%;",
        "  margin-left: -2rem;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            cn("units"),

            marginTop(px(1)),
            marginRight(em(1.2)),
            marginBottom(pct(10)),
            marginLeft(rem(-2))
          );
        }
      }
    );
  }

  @Test
  public void universalSelector() {
    test(
      css(
        "*,::before {",
        "  width: auto;",
        "}"
      ),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            any(), or(), BEFORE,

            width(auto)
          );
        }
      }
    );
  }

  @Test
  public void zeroValue() {
    test(
      css("body {margin:0;}"),
      new AbstractStyleSheet() {
        @Override
        protected final void definition() {
          style(
            body,
            margin(zero())
          );
        }
      }
    );
  }

  private String css(String... css) {
    return String.join("\n", css);
  }

  private void test(String css, StyleSheet expected) {
    StyleSheet sheet;
    sheet = CssParser.parse(css);

    assertEquals(
      StyleSheetWriter.toMinifiedString(sheet),
      StyleSheetWriter.toMinifiedString(expected)
    );
  }

}
