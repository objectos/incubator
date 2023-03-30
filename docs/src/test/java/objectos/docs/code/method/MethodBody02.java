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
package objectos.docs.code.method;

import java.util.ArrayList;
import java.util.List;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;
import objectos.code.ParameterizedTypeName;

public class MethodBody02 extends JavaTemplate {
  static final ClassTypeName ARRAY_LIST
      = ClassTypeName.of(ArrayList.class);
  static final ClassTypeName LIST
      = ClassTypeName.of(List.class);
  static final ClassTypeName STRING
      = ClassTypeName.of(String.class);
  static final ParameterizedTypeName LIST_STRING
      = ParameterizedTypeName.of(LIST, STRING);

  public static void main(String[] args) {
    System.out.println(new MethodBody02());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("Programmatically"),

      method(
        PUBLIC, LIST_STRING, name("example"),

        p(VAR, name("list"), NEW, ARRAY_LIST),

        include(this::elements),

        p(RETURN, LIST, v("copyOf"), argument(n("list")))
      )
    );
  }

  private void elements() {
    var elements = List.of("A", "B", "C", "D");

    for (var element : elements) {
      p(n("list"), v("add"), argument(s(element)));
    }
  }
}
