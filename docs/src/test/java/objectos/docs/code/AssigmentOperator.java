/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.code;

import objectos.code.JavaTemplate;

public class AssigmentOperator extends JavaTemplate {

  public static void main(String[] args) {
    System.out.println(new AssigmentOperator());
  }

  @Override
  protected final void definition() {
    _class("AssignmentOperator");
    body(
      _void(), method("overview"), block(
        n("a"), gets(), n("b"), end(),

        n("x"), gets(), n("y"), gets(), n("z"), end(),

        n("barArray"), dim(i(0)), gets(), _new(t("foo", "Bar")), end(),

        invoke("m"), n("foo"), gets(), invoke("n"), end(),

        invoke("foo", n("i"), gets(), n("j"))
      ),

      _void(), method("expressionName"), block(
        n("x"), gets(), n("value"), end(),

        n("foo"), n("y"), gets(), n("value"),

        t("com.example", "MyType"), n("field"), gets(), n("value")
      ),

      _void(), method("fieldAccess"), block(
        _this(), n("x"), gets(), n("x"),
        _this(), n("y"), gets(), n("y"), end(),

        invoke("point"), n("x"), gets(), n("valueX"), end(),
        invoke("point"), n("y"), gets(), n("valueY"), end(),

        n("a"), invoke("b"), n("x"), gets(), n("value")
      ),

      _void(), method("arrayAccess"), block(
        n("elements"), dim(i(0)), gets(), n("e0"), end(),
        n("elements"), dim(i(1)), gets(), n("e1"), end(),
        n("elements"), dim(i(2)), gets(), n("e2"), end(),

        n("multi"), dim(n("x")), dim(n("y")), gets(), n("value"), end(),

        invoke("arrayRef"), dim(invoke("index")), gets(), n("value"), end()
      ),

      _void(), method("rhs"), block(
        n("a"), gets(), n("b"), gets(), n("c"), end(),
        n("name"), gets(), invoke("foo"), n("name"), gets(), n("value"), end(),

        n("index"), gets(), i(123), end(),
        n("name"), gets(), s("Some Name"), end(),
        n("foo"), gets(), _new(t("com.example", "Foo")), end()
      )
    );
  }

}