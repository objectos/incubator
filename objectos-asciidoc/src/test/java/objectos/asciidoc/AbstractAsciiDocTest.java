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

public abstract class AbstractAsciiDocTest {

  private final AsciiDocTest outer;

  AbstractAsciiDocTest(AsciiDocTest outer) {
    this.outer = outer;
  }

  @BeforeClass
  public void _beforeClass() {
    outer._beforeClass();
  }

  final int[] p0(int... values) { return values; }

  final int[] p1(int... values) { return values; }

  final int[][] p2(int[]... values) { return values; }

  final int[] t(int... values) { return values; }

  final void test(
      String source, int[] expected0, int[] expected1, int[][] expected2, String expectedHtml) {
    outer.test(source, expected0, expected1, expected2, expectedHtml);
  }

}