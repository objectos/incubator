/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.office.writer;

import static org.testng.Assert.assertEquals;

import br.com.objectos.fs.RegularFile;
import br.com.objectos.office.AbstractOfficeTest;
import br.com.objectos.office.OfficeException;
import java.io.IOException;
import org.testng.annotations.Test;

public class WriterScriptTest extends AbstractOfficeTest {

  @Test
  public void createWriterDocument() throws IOException, OfficeException {
    class ThisScript extends AbstractWriterScript {
      @Override
      protected final void definition() {
        p(
            "First paragraph",
            "",
            "Third paragraph"
        );
      }
    }

    WriterService service;
    service = getWriterService();

    ThisScript script;
    script = new ThisScript();

    RegularFile documentFile;
    documentFile = service.createWriterDocument(script, Format.ODT);

    String text;
    text = service.extractText(documentFile);

    assertHasLines(
        text.trim(),
        "First paragraph",
        "",
        "Third paragraph"
    );
  }

  @Test
  public void editWriterTemplate() throws IOException, OfficeException {
    class ThisScript extends AbstractWriterScript {
      @Override
      protected final void definition() {
        replace("world", "ObjectosOffice");

        replace("text", "thing");
      }
    }

    WriterService service;
    service = getWriterService();

    RegularFile helloFile;
    helloFile = WriterResources.HELLO;

    ThisScript script;
    script = new ThisScript();

    RegularFile documentFile;
    documentFile = service.editWriterTemplate(helloFile, script, Format.ODT);

    String text;
    text = service.extractText(documentFile);

    assertHasLines(
        text.trim(),
        "Hello ObjectosOffice!",
        "",
        "Some thing. Some more thing."
    );
  }

  @Test
  public void extractText() throws OfficeException, IOException {
    WriterService service;
    service = getWriterService();

    RegularFile helloFile;
    helloFile = WriterResources.HELLO;

    String result;
    result = service.extractText(helloFile);

    assertHasLines(
        result.trim(),
        "Hello world!",
        "",
        "Some text. Some more text."
    );
  }

  private void assertHasLines(String s, String... expected) {
    String[] split = s.split("\n");

    assertEquals(split, expected);
  }

}