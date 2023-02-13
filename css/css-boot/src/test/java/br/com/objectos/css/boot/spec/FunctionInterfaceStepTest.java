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

public class FunctionInterfaceStepTest extends AbstractCssBootSpecTest {

  @Test
  public void execute() {
    execute(
      new FunctionInterfaceStep(adapter),

      new CssSpec() {
        @Override
        protected final void definition() {
          var rotate = function("rotate", sig(primitive(Primitive.ANGLE), "angle"));

          var transformValue = t("TransformValue", rotate);

          property(
            "transform",
            formal("", Source.MANUAL_ENTRY),
            sig(transformValue, "function")
          );
        }
      }
    );

    assertEquals(resultList.size(), 1);

    assertEquals(
      resultList.get(0),

      """
      package br.com.objectos.css.function;

      import br.com.objectos.css.type.TransformValue;

      public interface RotateFunction extends TransformValue {}
      """
    );
  }

}