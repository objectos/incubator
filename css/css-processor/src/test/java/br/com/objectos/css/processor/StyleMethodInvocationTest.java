/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

import br.com.objectos.code.java.expression.MethodInvocation;
import objectos.css.Css;
import objectos.css.property.StandardPropertyName;
import org.testng.annotations.Test;

public class StyleMethodInvocationTest {

  @Test
  public void selector() {
    StyleMethodInvocation style;
    style = new StyleMethodInvocation();

    style.addSimpleSelector(Css.a);

    test(
      style,
      "style(",
      "    a",
      ")"
    );
  }

  @Test(enabled = false)
  public void units() {
    StyleMethodInvocation style;
    style = new StyleMethodInvocation();

    style.addSimpleSelector(Css.a);

    style.addPropertyName(StandardPropertyName.MARGIN_TOP);

    style.addPropertyName(StandardPropertyName.MARGIN_RIGHT);

    style.addPropertyName(StandardPropertyName.MARGIN_BOTTOM);

    style.addPropertyName(StandardPropertyName.MARGIN_LEFT);

    test(
      style,
      "style(",
      "    a,",
      "",
      "    marginTop(px(1)),",
      "    marginRight(em(1.2)),",
      "    marginBottom(pct(10)),",
      "    marginLeft(rem(-2))",
      ")"
    );
  }

  @Test
  public void zeroValue() {
    StyleMethodInvocation style;
    style = new StyleMethodInvocation();

    style.addSimpleSelector(Css.a);

    style.addPropertyName(StandardPropertyName.MARGIN);

    style.addValue(0);

    test(
      style,
      "style(",
      "    a,",
      "",
      "    margin(zero())",
      ")"
    );
  }

  private void test(StyleMethodInvocation style, String... expected) {
    MethodInvocation invocation;
    invocation = style.build();

    Util.assertHasLines(invocation.toString(), expected);
  }

}
