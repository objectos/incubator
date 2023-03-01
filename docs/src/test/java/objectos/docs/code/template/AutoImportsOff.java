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
package objectos.docs.code.template;

import java.nio.file.Path;
import java.time.LocalDate;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

// objectos-code/java-template.adoc
public class AutoImportsOff extends JavaTemplate {
  static final ClassTypeName LOCAL_DATE = ClassTypeName.of(LocalDate.class);

  static final ClassTypeName PATH = ClassTypeName.of(Path.class);

  static final ClassTypeName STRING = ClassTypeName.of(String.class);

  public static void main(String[] args) {
    System.out.println(new AutoImportsOff());
  }

  @Override
  protected final void definition() {
    packageDeclaration("com.example");

    classDeclaration(
      PUBLIC, name("AutoImports"),

      field(
        LOCAL_DATE, name("a")
      ),

      field(
        PATH, name("b")
      ),

      field(
        STRING, name("c")
      )
    );
  }
}