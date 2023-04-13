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
package objectos.docs.code.constructor;

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class ConstructorBody02 extends JavaTemplate {
  static final ClassTypeName EXCEPTION
      = ClassTypeName.of(Exception.class);
  static final ClassTypeName STRING
      = ClassTypeName.of(String.class);
  static final ClassTypeName THROWABLE
      = ClassTypeName.of(Throwable.class);

  public static void main(String[] args) {
    System.out.println(new ConstructorBody02());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("SuperInvocation"),
      extendsClause(EXCEPTION),

      constructor(
        PUBLIC,
        p(SUPER)
      ),

      constructor(
        PUBLIC,
        parameter(STRING, name("message")),
        p(SUPER, argument(n("message")))
      ),

      constructor(
        PUBLIC,
        parameter(STRING, name("message")),
        parameter(THROWABLE, name("cause")),
        p(SUPER, argument(n("message")), argument(n("cause")))
      )
    );
  }
}
