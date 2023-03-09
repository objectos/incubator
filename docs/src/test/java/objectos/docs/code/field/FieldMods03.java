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
package objectos.docs.code.field;

import java.util.concurrent.ThreadLocalRandom;
import objectos.code.JavaTemplate;

public class FieldMods03 extends JavaTemplate {
  public static void main(String[] args) {
    System.out.println(new FieldMods03());
  }

  @Override
  protected final void definition() {
    classDeclaration(
      name("Programatically"),

      field(
        include(this::modifiers), INT, name("a"), i(1234)
      )
    );
  }

  private void modifiers() {
    if (shouldBePublic()) {
      code(PUBLIC);
    }

    code(STATIC, FINAL);
  }

  private boolean shouldBePublic() {
    return ThreadLocalRandom.current().nextBoolean();
  }
}
