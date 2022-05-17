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

import br.com.objectos.core.io.Copy;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import objectos.lang.Try;
import org.testng.annotations.Test;

public class ZipFileTest {

  private final byte[] responseBuffer = new byte[128];

  @Test(enabled = false)
  public void listEntries() throws IOException {
    Throwable rethrow;
    rethrow = Try.begin();

    ZipFile zip;
    zip = null;

    try {
      File file;
      file = new File("/tmp/replay.zip");

      zip = new ZipFile(file);

      Enumeration<? extends ZipEntry> entries;
      entries = zip.entries();

      while (entries.hasMoreElements()) {
        ZipEntry request;
        request = entries.nextElement();

        if (request.getName().endsWith("archive")) {
          continue;
        }

        if (!isRequest(request)) {
          throw new IOException("Not a request: ");
        }

        if (!entries.hasMoreElements()) {
          throw new IOException("No more elements...");
        }

        ZipEntry response;
        response = entries.nextElement();

        if (!isResponse(response)) {
          throw new IOException("Not a response");
        }

        listEntry(zip, request, response);
      }
    } catch (IOException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, zip);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  private boolean isRequest(ZipEntry entry) {
    String name;
    name = entry.getName();

    return name.endsWith(".request");
  }

  private boolean isResponse(ZipEntry entry) {
    String name;
    name = entry.getName();

    return name.endsWith(".response");
  }

  private void listEntry(ZipFile tar, ZipEntry request, ZipEntry response) throws IOException {
    Throwable rethrow;
    rethrow = Try.begin();

    GZIPInputStream gzip;
    gzip = null;

    try {
      InputStream in;
      in = tar.getInputStream(request);

      gzip = new GZIPInputStream(in);

      String name;
      name = request.getName();

      System.out.println(name);

      long time;
      time = request.getTime();

      Date lastModifiedDate;
      lastModifiedDate = new Date(time);

      System.out.println(lastModifiedDate.toString());

      ByteArrayOutputStream out;
      out = new ByteArrayOutputStream();

      Copy.streams(in, out, responseBuffer);

      byte[] bytes;
      bytes = out.toByteArray();

      String s;
      s = new String(bytes);

      System.out.println(s);
    } catch (IOException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, gzip);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);

    try {
      InputStream in;
      in = tar.getInputStream(response);

      gzip = new GZIPInputStream(in);

      String name;
      name = response.getName();

      System.out.println(name);

      int read;
      read = gzip.read(responseBuffer);

      if (read > 0) {
        String s;
        s = new String(responseBuffer, 0, read);

        System.out.println(s);
      }
    } catch (IOException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, gzip);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

}