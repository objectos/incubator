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

public class Process1Test extends AsciiDocTest {

  private Process1 process1;

  @BeforeClass
  @Override
  public void _beforeClass() {
    process1 = new Process1();
  }

  @Test(enabled = false)
  public void _enableCodeMinings() {
  }

  @Override
  final String convert(String source) {
    return null;
  }

  @Override
  final void test(String source, int[] expected0, int[] expected1, String expectedHtml) {
    var s = new ArrayProcess1Source(expected0);

    process1.execute(s);

    int[] result = process1.toCode();

    testArrays(result, expected1, "Process (1) assertion failed");
  }

}