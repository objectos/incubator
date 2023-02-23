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
package objectos.docs.code.expression;

import objectos.code.JavaTemplate;

public class AssigmentOperator extends JavaTemplate {

  static final ClassTypeName FOO = classType("com.example", "Foo");

  static final ClassTypeName MYTYPE = classType("com.example", "MyType");

  public static void main(String[] args) {
    System.out.println(new AssigmentOperator());
  }

  @Override
  protected final void definition() {
    _class("AssignmentOperator");
    body(
      method(
        name("overview"),

        p(n("a"), IS, n("b")),

        p(n("x"), IS, n("y"), IS, n("z")),

        p(n("barArray"), dim(i(0)), IS, NEW, classType("com.example", "Bar")),

        p(v("m"), n("foo"), IS, v("n")),

        p(v("foo"), arg(n("i"), IS, n("j")))
      ),

      method(
        name("expressionName"),

        p(n("x"), IS, n("value")),

        p(n("foo"), n("y"), IS, n("value")),

        p(MYTYPE, n("field"), IS, n("value"))
      ),

      method(
        name("fieldAccess"),

        p(THIS, n("x"), IS, n("x")),
        p(THIS, n("y"), IS, n("y")),

        p(v("point"), n("x"), IS, n("valueX")),
        p(v("point"), n("y"), IS, n("valueY")),

        p(n("a"), v("b"), n("x"), IS, n("value"))
      ),

      method(
        name("arrayAccess"),

        p(n("elements"), dim(i(0)), IS, n("e0")),
        p(n("elements"), dim(i(1)), IS, n("e1")),
        p(n("elements"), dim(i(2)), IS, n("e2")),

        p(n("multi"), dim(n("x")), dim(n("y")), IS, n("value")),

        p(v("arrayRef"), dim(v("index")), IS, n("value"))
      ),

      method(
        name("rhs"),

        p(n("a"), IS, n("b"), IS, n("c")),
        p(n("name"), IS, v("foo"), n("name"), IS, n("value")),

        p(n("index"), IS, i(123)),
        p(n("name"), IS, s("Some Name")),
        p(n("foo"), IS, NEW, FOO)
      )
    );
  }

}