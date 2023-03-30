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
package objectos.docs.code.class_;

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class ClassDeclaration extends JavaTemplate {
  static final ClassTypeName EXAMPLE
      = ClassTypeName.of("com.example.annotations", "Example");

  static final ClassTypeName OBJECT
      = ClassTypeName.of(Object.class);

  static final ClassTypeName STRING
      = ClassTypeName.of(String.class);

  public static void main(String[] args) {
    System.out.println(new ClassDeclaration());
  }

  @Override
  protected final void definition() {
    packageDeclaration("com.example");

    autoImports();

    classDeclaration(
      annotation(
        EXAMPLE,
        annotationValue(s("A 'classDeclaration' kitchen-sink example."))
      ),
      PUBLIC, FINAL, name("KitchenSink"),

      field(
        PRIVATE, FINAL, OBJECT, name("value")
      ),

      constructor(
        PUBLIC,
        parameter(OBJECT, name("value")),

        p(THIS, n("value"), IS, n("value"))
      ),

      method(
        PUBLIC, FINAL, OBJECT, name("get"),
        p(RETURN, n("value"))
      ),

      method(
        annotation(Override.class),
        PUBLIC, FINAL, STRING, name("toString"),
        p(IF, condition(n("value"), EQ, NULL), block(
          p(RETURN, s("null"))
        ), ELSE, block(
          p(RETURN, n("value"), v("toString"))
        ))
      )
    );
  }
}