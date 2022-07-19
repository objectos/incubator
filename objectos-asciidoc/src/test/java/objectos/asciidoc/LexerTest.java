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

public class LexerTest {

  private Lexer lexer;

  @BeforeClass
  public void _beforeClass() {
    lexer = new Lexer();
  }

  @Test(enabled = false)
  public void notWordNotBigSIntersection() {
    int count = 0;

    for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
      if (Character.isLowSurrogate(c)) {
        continue;
      }

      if (Character.isHighSurrogate(c)) {
        continue;
      }

      if (!lexer.isBigS(c) && !lexer.isWord(c)) {
        count++;

        System.out.print(c);
        System.out.print(' ');

        if ((count % 20) == 0) {
          System.out.println();
        }
      }
    }

    System.out.println();
    System.out.println("Total count=" + count);
  }

}