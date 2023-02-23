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
package objectos.docs.code.statement;

public class If extends StatementExample {
  public static void main(String[] args) {
    System.out.println(new If());
  }

  @Override
  final void example() {
    p(VAR, name("value"), v("get"));
    p(IF, arg(v("isType"), arg(n("value"))), block(
      p(v("executeType"), arg(n("value")))
    ), ELSE, IF, arg(n("value"), EQ, n("VAR")), block(
      p(v("executeVar"), arg(n("value")))
    ), ELSE, block(
      p(v("executeError"), arg(n("value")))
    ));

    p(IF, arg(n("active")), block(
      p(v("execute"))
    ));

    p(IF, arg(v("compute"), v("list"), v("size"), EQ, i(0)), block(
      p(v("executeWhenEmpty"))
    ));

    p(IF, arg(n("active")), block(
      p(v("stepOne")),
      p(v("stepTwo")),
      p(v("stepThree"))
    ));

    p(IF, arg(n("size"), EQ, i(0)), block(
      p(RETURN, s("[]"))
    ), ELSE, block(
      p(RETURN, v("makeToString"))
    ));

    p(
      IF, arg(n("o"), EQ, NULL), n("s"), IS, s("null"),
      ELSE, n("s"), IS, n("o"), v("toString")
    );
  }
}