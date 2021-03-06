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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Pass0Test extends AsciiDocTest {

  private Pass0 pass0;

  @BeforeClass
  @Override
  public void _beforeClass() {
    pass0 = new Pass0();
  }

  @Test(enabled = false)
  public void _enableCodeMinings() {
  }

  @Override
  final void test(
      String source, int[] expected0, int[] expected1, int[][] expected2, String expectedHtml) {
    pass0.execute(source);

    int[] result = pass0.toToken();

    testArrays(result, expected0, "Process (0) assertion failed");
  }

}