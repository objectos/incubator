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
package br.com.objectos.css.io;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrettyCssWriterTest {

  private PrettyCssWriter w;

  private StringBuilder sb;

  @BeforeClass
  public void _beforeClass() {
    w = new PrettyCssWriter();

    sb = new StringBuilder();

    w.out(sb);
  }

  @BeforeMethod
  public void _beforeMethod() {
    sb.setLength(0);
  }

  @Test
  public void writeIndentation() throws IOException {
    w.writeIndentation();
    w.write("#foo {");
    w.writeNewLine();
    w.indent();
    w.writeIndentation();
    w.write("margin: 0;");
    w.writeNewLine();
    w.unindent();
    w.writeIndentation();
    w.write('}');

    assertEquals(
      sb.toString(),

      """
      #foo {
        margin: 0;
      }"""
    );
  }

  @Test
  public void writeUrl() throws IOException {
    w.writeUrl("abc");

    assertEquals(
      sb.toString(),

      """
      url("abc")"""
    );
  }

}
