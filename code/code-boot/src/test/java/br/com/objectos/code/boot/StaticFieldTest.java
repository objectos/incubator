/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class StaticFieldTest extends AbstractCodeBootTest {

  @Test
  public void ofUncheckedTest() {
    assertEquals(
        StaticField.ofUnchecked(field(Fields.class, "FIELD_TEST")),
        StaticFieldFake.FIELD_TEST
    );
  }

  @Test
  public void generateTest() {
    test(
        StaticFieldFake.FIELD_TEST.generate(),
        "public static final java.lang.String FIELD_TEST = br.com.objectos.code.boot.AbstractCodeBootTest.Fields.FIELD_TEST;"
    );
  }

}
