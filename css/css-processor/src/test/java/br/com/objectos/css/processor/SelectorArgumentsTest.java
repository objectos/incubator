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
package br.com.objectos.css.processor;

import static br.com.objectos.css.Css.attr;
import static br.com.objectos.css.Css.cn;
import static br.com.objectos.css.Css.eq;
import static br.com.objectos.css.Css.id;
import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.SimpleSelector;
import org.testng.annotations.Test;

public class SelectorArgumentsTest {

  @Test
  public void attributeSelector() {
    test(attr("type"), "attr(\"type\")");
  }

  @Test
  public void attributeValueSelector() {
    test(attr("type", eq("text")), "attr(\"type\", eq(\"text\"))");
  }

  @Test
  public void classSelector() {
    test(cn("units"), "cn(\"units\")");
    test(cn("overload"), "cn(\"overload\")");
  }

  @Test
  public void idSelector() {
    test(id("x"), "id(\"x\")");
  }

  @Test
  public void pseudoClassSelector() {
    test(Css.ACTIVE, "ACTIVE");
  }

  @Test
  public void pseudoElementSelector() {
    test(Css._WEBKIT_FILE_UPLOAD_BUTTON, "_WEBKIT_FILE_UPLOAD_BUTTON");
  }

  @Test
  public void typeSelector() {
    test(Css.html, "html");
    test(Css.body, "body");
    test(Css.b, "b");
    test(Css.hr, "hr");
    test(Css.p, "p");
    test(Css.strong, "strong");
  }

  private void test(SimpleSelector selector, String expected) {
    Argument argument;
    argument = SelectorArguments.of(selector);

    assertEquals(argument.toString(), expected);
  }

}
