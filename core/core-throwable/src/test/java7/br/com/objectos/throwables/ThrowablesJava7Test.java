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
package br.com.objectos.throwables;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import br.com.objectos.core.throwable.Throwables;
import java.io.IOException;
import org.testng.annotations.Test;

public class ThrowablesJava7Test {

  @Test
  public void addSuppressed() {
    Exception original;
    original = new InterruptedException();

    Exception suppressed;
    suppressed = new IOException();

    Throwable result;
    result = Throwables.addSuppressed(original, suppressed);

    assertSame(result, original);

    Throwable[] suppressedArray;
    suppressedArray = result.getSuppressed();

    assertEquals(suppressedArray.length, 1);

    assertSame(suppressedArray[0], suppressed);
  }

  @Test
  public void getSuppresed() {
    IOException suppressed;
    suppressed = new IOException("suppressed");

    try {
      IOException e;
      e = new IOException();

      e.addSuppressed(suppressed);

      throw e;
    } catch (Exception e) {
      Throwable[] result;
      result = Throwables.getSuppressed(e);

      assertEquals(result.length, 1);

      assertSame(result[0], suppressed);
    }
  }

}