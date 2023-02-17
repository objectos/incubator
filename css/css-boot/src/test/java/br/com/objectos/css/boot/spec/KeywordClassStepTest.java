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

public class KeywordClassStepTest extends AbstractCssBootSpecTest {

  @Test(description = """
  keyword class must implement the PropertyValue interface
  if part of that value set
  """)
  public void execute() {
    execute(
      new KeywordClassStep(adapter),

      new CssSpec() {
        @Override
        protected final void definition() {
          KeywordName auto = keyword("auto");
          keyword("double");

          property(
            "top",
            formal("", Source.MANUAL_ENTRY),
            sig(t("TopValue", auto), "value")
          );
        }
      }
    );

    assertEquals(resultList.size(), 2);

    assertEquals(
      resultList.get(0),

      """
      package br.com.objectos.css.keyword;

      import br.com.objectos.code.annotations.Generated;
      import br.com.objectos.css.type.TopValue;

      @Generated("br.com.objectos.css.boot.CssBoot")
      public final class AutoKeyword extends StandardKeyword implements TopValue {
        static final AutoKeyword INSTANCE = new AutoKeyword();

        private AutoKeyword() {
          super(0, "auto", "auto");
        }
      }
      """
    );

    assertEquals(
      resultList.get(1),

      """
      package br.com.objectos.css.keyword;

      import br.com.objectos.code.annotations.Generated;

      @Generated("br.com.objectos.css.boot.CssBoot")
      public final class DoubleKeyword extends StandardKeyword {
        static final DoubleKeyword INSTANCE = new DoubleKeyword();

        private DoubleKeyword() {
          super(1, "doubleKw", "double");
        }
      }
      """
    );
  }

}