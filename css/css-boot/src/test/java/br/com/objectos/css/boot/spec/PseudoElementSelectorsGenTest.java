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

import org.testng.annotations.Test;

public class PseudoElementSelectorsGenTest extends AbstractCssBootSpecTest {

  @Test
  public void generateJavaFile() {
    execute(
      new PseudoElementSelectorsGen(adapter),

      new CssSpec() {
        @Override
        protected final void definition() {
          pseudoElements(
            "after",
            "-moz-focus-inner"
          );
        }
      }
    );

    assertEquals(resultList.size(), 1);

    assertEquals(
      resultList.get(0),

      """
      package br.com.objectos.css.select;

      import br.com.objectos.code.annotations.Ignore;
      import objectos.util.GrowableMap;
      import objectos.util.UnmodifiableMap;

      public final class PseudoElementSelectors {
        public static final PseudoElementSelector AFTER = new PseudoElementSelector(0, "after");

        public static final PseudoElementSelector _MOZ_FOCUS_INNER = new PseudoElementSelector(1, "-moz-focus-inner");

        private static final PseudoElementSelector[] ARRAY = {
          AFTER,
          _MOZ_FOCUS_INNER
        };

        private static final UnmodifiableMap<String, PseudoElementSelector> MAP = buildMap();

        private PseudoElementSelectors() {}

        @Ignore
        public static PseudoElementSelector getByCode(int code) {
          return ARRAY[code];
        }

        @Ignore
        public static PseudoElementSelector getByName(String name) {
          return MAP.get(name);
        }

        private static UnmodifiableMap<String, PseudoElementSelector> buildMap() {
          var m = new GrowableMap<String, PseudoElementSelector>();
          m.put("after", AFTER);
          m.put("-moz-focus-inner", _MOZ_FOCUS_INNER);
          return m.toUnmodifiableMap();
        }
      }
      """
    );
  }

}
