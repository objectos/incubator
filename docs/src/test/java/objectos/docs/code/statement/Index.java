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

import objectos.code.JavaTemplate;

public class Index extends JavaTemplate {
  static final ClassTypeName SB = classType(StringBuilder.class);

  public static void main(String[] args) {
    System.out.println(new Index());
  }

  @Override
  protected final void definition() {
    _class("Index");
    body(
      method(this::example)
    );
  }

  private void example() {
    p(VAR, name("sb"), NEW, SB);
    p(n("sb"), v("append"), arg(s("Objectos Code")));
    p(RETURN, n("sb"), v("toString"));
  }
}