/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCode (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.code.model.element;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import br.com.objectos.code.util.AbstractCodeCoreTest;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.ReaderSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.testng.annotations.Test;

public class GetResourceTest {

  @Test
  public void getResource() throws IOException {
    Class<? extends GetResourceTest> type = getClass();

    Module module = type.getModule();

    try (
        InputStream in = module.getResourceAsStream("br/com/objectos/code/util/get-resource.txt")
    ) {
      assertNotNull(in);

      ReaderSource source;
      source = new ReaderSource() {
        @Override
        public Reader openReader(Charset charset) throws IOException {
          return new InputStreamReader(in, charset);
        }
      };

      String txt = Read.string(source, Charsets.utf8());

      assertEquals(txt, "got it!");
    }

    try (
        InputStream in = AbstractCodeCoreTest.class.getResourceAsStream("get-resource.txt")
    ) {
      assertNotNull(in);

      ReaderSource source;
      source = new ReaderSource() {
        @Override
        public Reader openReader(Charset charset) throws IOException {
          return new InputStreamReader(in, charset);
        }
      };

      String txt = Read.string(source, Charsets.utf8());

      assertEquals(txt, "got it!");
    }

    ClassLoader loader = Thread.currentThread().getContextClassLoader();

    URL url = loader.getResource("code-testing/get-resource.txt");

    assertNotNull(url);
  }

}
