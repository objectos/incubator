/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.boot.spec;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.boot.type.Primitive;
import org.testng.annotations.Test;

public class StandardFunctionNameStepTest extends AbstractCssBootSpecTest {

  @Test(description = "it should generate an enum constant for each function defined")
  public void execute() {
    execute(
      new StandardFunctionNameStep(adapter),

      new CssSpec() {
        @Override
        protected final void definition() {
          function("rotate", sig(primitive(Primitive.ANGLE), "angle"));
        }
      }
    );

    assertEquals(resultList.size(), 1);

    assertEquals(
      resultList.get(0),

      """
      package br.com.objectos.css.function;

      import br.com.objectos.code.annotations.Generated;
      import objectos.util.GrowableMap;
      import objectos.util.UnmodifiableMap;

      @Generated("br.com.objectos.css.boot.CssBoot")
      public enum StandardFunctionName implements FunctionName {
        ROTATE("rotate", "rotate");

        private static final StandardFunctionName[] ARRAY = StandardFunctionName.values();

        private static final UnmodifiableMap<String, StandardFunctionName> MAP = buildMap();

        private final String javaName;

        private final String name;

        private StandardFunctionName(String javaName, String name) {
          this.javaName = javaName;
          this.name = name;
        }

        public static StandardFunctionName getByCode(int code) {
          return ARRAY[code];
        }

        public static StandardFunctionName getByName(String name) {
          return MAP.get(name);
        }

        private static UnmodifiableMap<String, StandardFunctionName> buildMap() {
          var m = new GrowableMap<String, StandardFunctionName>();
          m.put("rotate", ROTATE);
          return m.toUnmodifiableMap();
        }

        public static int size() {
          return ARRAY.length;
        }

        @Override
        public final int getCode() {
          return ordinal();
        }

        public final String getJavaName() {
          return javaName;
        }

        @Override
        public final String getName() {
          return name;
        }
      }
      """
    );
  }

}
