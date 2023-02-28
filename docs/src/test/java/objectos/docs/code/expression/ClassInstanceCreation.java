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

import java.io.UncheckedIOException;
import java.util.HashMap;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;
import objectos.code.ParameterizedTypeName;

public class ClassInstanceCreation extends JavaTemplate {
  static final ParameterizedTypeName HASHMAP = ParameterizedTypeName.of(
    ClassTypeName.of(HashMap.class),
    ClassTypeName.of(Integer.class),
    ClassTypeName.of(String.class)
  );

  static final ClassTypeName SB = ClassTypeName.of(StringBuilder.class);

  static final ClassTypeName UIOE = ClassTypeName.of(UncheckedIOException.class);

  public static void main(String[] args) {
    System.out.println(new ClassInstanceCreation());
  }

  @Override
  protected final void definition() {
    _package("com.example");

    autoImports();

    _class("ClassInstanceCreation");
    body(
      method(
        name("example"),
        p(NEW, SB),
        p(NEW, SB, arg(s("Initial value"))),

        p(NEW, HASHMAP),

        p(NEW, UIOE,
          arg(s("Failed to open %s"), v("formatted"), arg(n("file"))),
          arg(n("cause")))
      )
    );
  }
}