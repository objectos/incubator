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
package br.com.objectos.css.sheet;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.TypeSelectors;
import br.com.objectos.css.sheet.ex.TestCase00;
import br.com.objectos.css.sheet.ex.TestCase01;
import br.com.objectos.css.sheet.ex.TestCase08;
import br.com.objectos.css.sheet.ex.TestCase09;
import br.com.objectos.css.sheet.ex.TestCase17;
import br.com.objectos.css.sheet.ex.TestCase25;
import br.com.objectos.css.sheet.ex.TestCase27;
import br.com.objectos.css.sheet.ex.TestCase32;
import java.util.Arrays;
import org.testng.annotations.Test;

public class StyleSheetDslTest {

  @Test
  public void testCase00() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase00());

    testProtos(
        dsl,
        ByteProto.RULE_END,
        TypeSelectors.body.getCode(),
        ByteProto.SELECTOR_TYPE_OBJ,
        ByteProto.RULE_START
    );
  }

  @Test
  public void testCase01() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase01());

    testChars(
        dsl,
        "myid"
    );

    testProtos(
        dsl,
        4, 0,
        ByteProto.SELECTOR_ID,
        ByteProto.RULE_END,
        ByteProto.SELECTOR_ID_MARK,
        ByteProto.RULE_START
    );
  }

  @Test
  public void testCase08() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase08());

    testProtos(
        dsl,
        ByteProto.DECLARATION_END,
        Keywords.block.getCode(),
        ByteProto.VALUE_KEYWORD,
        StandardPropertyName.DISPLAY.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_UNIVERSAL_OBJ,
        ByteProto.RULE_START
    );
  }

  @Test
  public void testCase09() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase09());

    testProtos(
        dsl,
        ByteProto.DECLARATION_END,
        -300,
        ByteProto.VALUE_INT,
        StandardPropertyName.Z_INDEX.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_UNIVERSAL_OBJ,
        ByteProto.RULE_START
    );
  }

  @Test
  public void testCase17() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase17());

    testProtos(
        dsl,
        2, ByteProto.VALUE_INT_DSL,
        0, ByteProto.VALUE_DOUBLE_DSL,

        ByteProto.DECLARATION_END,
        ByteProto.VALUE_DOUBLE_MARK,
        ByteProto.VALUE_INT_MARK,
        StandardPropertyName.FLEX.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_UNIVERSAL_OBJ,
        ByteProto.RULE_START
    );
  }

  @Test
  public void testCase25() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase25());

    testProtos(
        dsl,
        ByteProto.DECLARATION_END,
        Keywords.sansSerif.getCode(),
        ByteProto.VALUE_KEYWORD,
        StandardPropertyName.FONT_FAMILY.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.DECLARATION_END,
        5, 0,
        ByteProto.VALUE_STRING,
        StandardPropertyName.FONT_FAMILY.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.DECLARATION_MULTI_END,
        ByteProto.DECLARATION_MULTI_ELEMENT_MARK,
        ByteProto.DECLARATION_MULTI_ELEMENT_MARK,
        StandardPropertyName.FONT_FAMILY.getCode(),
        ByteProto.DECLARATION_MULTI_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_UNIVERSAL_OBJ,
        ByteProto.RULE_START
    );
  }

  @Test
  public void testCase27() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase27());

    testProtos(
        dsl,
        100,
        ByteProto.VALUE_PERCENTAGE_INT,

        ByteProto.DECLARATION_END,
        ByteProto.VALUE_PERCENTAGE_INT_MARK,
        StandardPropertyName.WIDTH.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        TypeSelectors.section.getCode(),
        ByteProto.SELECTOR_TYPE_OBJ,
        ByteProto.RULE_START,

        ByteProto.AT_MEDIA_END,
        ByteProto.RULE_MARK,
        MediaType.SCREEN.getCode(),
        ByteProto.MEDIA_TYPE,
        ByteProto.AT_MEDIA_START
    );
  }

  @Test
  public void testCase32() {
    StyleSheetDsl dsl;
    dsl = dsl(new TestCase32());

    testProtos(
        dsl,
        4, 0,
        ByteProto.SELECTOR_CLASS,
        ByteProto.DECLARATION_END,
        0,
        ByteProto.VALUE_INT,
        StandardPropertyName.Z_INDEX.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_CLASS_MARK,
        ByteProto.RULE_START,

        3, 4,
        ByteProto.SELECTOR_CLASS,
        ByteProto.DECLARATION_END,
        1,
        ByteProto.VALUE_INT,
        StandardPropertyName.Z_INDEX.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_CLASS_MARK,
        ByteProto.RULE_START,

        3, 7,
        ByteProto.SELECTOR_CLASS,
        ByteProto.DECLARATION_END,
        2,
        ByteProto.VALUE_INT,
        StandardPropertyName.Z_INDEX.getCode(),
        ByteProto.DECLARATION_START,

        ByteProto.RULE_END,
        ByteProto.DECLARATION_MARK,
        ByteProto.SELECTOR_CLASS_MARK,
        ByteProto.RULE_START
    );
  }

  private StyleSheetDsl dsl(StyleSheet sheet) {
    StyleSheetDsl dsl;
    dsl = new StyleSheetDsl();

    sheet.acceptStyleSheetDsl(dsl);

    return dsl;
  }

  private void testChars(StyleSheetDsl dsl, String expected) {
    assertEquals(dsl.charsToString(), expected);
  }

  private void testProtos(StyleSheetDsl dsl, int... expected) {
    int[] result;
    result = dsl.getProtos();

    try {
      assertEquals(result, expected);
    } catch (AssertionError e) {
      System.err.println(Arrays.toString(result));
      System.err.println(Arrays.toString(expected));
      throw e;
    }
  }

}