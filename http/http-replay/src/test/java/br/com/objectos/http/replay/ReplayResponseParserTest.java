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
package br.com.objectos.http.replay;

import static org.testng.Assert.assertEquals;

import br.com.objectos.concurrent.DirectIoWorker;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.Resource;
import br.com.objectos.http.Header.ContentType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import objectos.lang.Try;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReplayResponseParserTest implements ReplayResponseParserAdapter {

  private String expectedBody;

  private ContentType expectedContentType;

  private String expectedLocation;

  private ReplayResponseParser parser;

  @BeforeClass
  public void _setUp() {
    ByteBuffer byteBuffer;
    byteBuffer = ByteBuffer.allocate(1024);

    CharBuffer charBuffer;
    charBuffer = CharBuffer.allocate(1024);

    IoWorker ioWorker;
    ioWorker = DirectIoWorker.get();

    parser = new ReplayResponseParser(this, byteBuffer, charBuffer, ioWorker);
  }

  @BeforeMethod
  public void _setUpTest() {
    expectedBody = null;

    expectedContentType = null;

    expectedLocation = null;
  }

  @Override
  public final void expectRedirect(String location) {
    expectedLocation = location;
  }

  @Override
  public final void expectTextResponse(ContentType contentType, String body) {
    expectedBody = body;

    expectedContentType = contentType;
  }

  @Test
  public void testCase01() throws IOException {
    Resource resource;
    resource = Resource.getResource("TEST-INF/test-case-01.zip");

    Throwable rethrow;
    rethrow = Try.begin();

    InputStream in;
    in = null;

    try {
      in = resource.openInputStream();

      GZIPInputStream gzip;
      gzip = skipTo(in, "000000000001.response.gz");

      parser.setInput(gzip);

      while (parser.shouldExecute()) {
        parser.executeOne();
      }

      assertEquals(expectedLocation, "/test/login");
    } catch (IOException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, in);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  @Test
  public void testCase02() throws IOException {
    Resource resource;
    resource = Resource.getResource("TEST-INF/test-case-02.zip");

    Throwable rethrow;
    rethrow = Try.begin();

    InputStream in;
    in = null;

    try {
      in = resource.openInputStream();

      GZIPInputStream gzip;
      gzip = skipTo(in, "000000000002.response.gz");

      parser.setInput(gzip);

      while (parser.shouldExecute()) {
        parser.executeOne();
      }

      assertEquals(
        expectedBody,
        String.join(
          System.lineSeparator(),

          "<!doctype html>",
          "<html>",
          "<head>",
          "<title>Login</title>",
          "</head>",
          "<body>",
          "</body>",
          "</html>",
          ""
        )
      );

      assertEquals(
        expectedContentType.toString(),
        "Content-Type: text/html; charset=utf-8"
      );
    } catch (IOException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, in);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  @Test
  public void testCase02ContentLength() {
    String lines;
    lines = String.join(
      System.lineSeparator(),

      "<!doctype html>",
      "<html>",
      "<head>",
      "<title>Login</title>",
      "</head>",
      "<body>",
      "</body>",
      "</html>",
      ""
    );

    assertEquals(lines.length(), 82);
  }

  private GZIPInputStream skipTo(InputStream in, String fileName) throws IOException {
    ZipInputStream zip;
    zip = new ZipInputStream(in);

    ZipEntry entry;
    entry = zip.getNextEntry();

    while (entry != null) {
      String entryName;
      entryName = entry.getName();

      if (entryName.endsWith(fileName)) {
        return new GZIPInputStream(zip);
      }

      entry = zip.getNextEntry();
    }

    throw new IOException(fileName + " was not found in the zip file");
  }

}