/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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

import br.com.objectos.css.boot.keyword.KeywordName;
import org.testng.annotations.Test;

public class ValueTypeIfaceStepTest {

  @Test(description = "it should generate a Value interface for each ValueType defined")
  public void execute() {
    var template = new ValueTypeIfaceStep();

    var spec = new CssSpec() {
      @Override
      protected final void definition() {
        KeywordName auto = keyword("auto");
        KeywordName none = keyword("none");

        property(
          "clear",
          formal("", Source.MANUAL_ENTRY),
          sig(t("ClearValue", none), "value")
        );

        property(
          "top",
          formal("", Source.MANUAL_ENTRY),
          sig(t("TopValue", auto), "value")
        );
      }
    }.toSpecDsl();

    template.valueType = spec.valueType("ClearValue");

    assertEquals(
      template.toString(),

      """
      package br.com.objectos.css.type;

      import br.com.objectos.code.annotations.Generated;

      @Generated("br.com.objectos.css.boot.CssBoot")
      public interface ClearValue extends Value {}
      """
    );

    template.valueType = spec.valueType("TopValue");

    assertEquals(
      template.toString(),

      """
      package br.com.objectos.css.type;

      import br.com.objectos.code.annotations.Generated;

      @Generated("br.com.objectos.css.boot.CssBoot")
      public interface TopValue extends Value {}
      """
    );
  }

}