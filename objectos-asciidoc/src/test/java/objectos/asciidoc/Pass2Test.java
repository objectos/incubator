/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package objectos.asciidoc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Pass2Test extends AsciiDocTest {

  private Pass2 pass2;

  @BeforeClass
  @Override
  public void _beforeClass() {
    pass2 = new Pass2();
  }

  @Test(enabled = false)
  public void _enableCodeMinings() {
  }

  @Override
  final void test(
      String source, int[] expected0, int[] expected1, int[][] expected2, String expectedHtml) {
    var s = new ArrayPass2Source(expected0);

    var index = 0;

    for (int i = 0; i < expected1.length; i++) {
      var code = expected1[i];

      if (code != Code.TOKENS) {
        continue;
      }

      var first = expected1[i + 1];
      var last = expected1[i + 2];

      pass2.execute(s, first, last);

      var result = pass2.toText();

      testArrays(result, expected2[index], "Pass (2) assertion failed at index=" + index);

      index++;
    }

    assertEquals(expected2.length, index);
  }

}