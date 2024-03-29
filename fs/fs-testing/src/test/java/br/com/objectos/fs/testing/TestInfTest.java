/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
package br.com.objectos.fs.testing;

import static org.testng.Assert.assertEquals;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import java.io.IOException;
import org.testng.annotations.Test;

public class TestInfTest {

  @Test
  public void get() throws IOException {
    Directory testInf;
    testInf = TestInf.get();

    RegularFile result;
    result = testInf.getRegularFile("hello.txt");

    assertEquals(
        Read.string(result, Charsets.utf8()),

        "Hello world!"
    );
  }

}
