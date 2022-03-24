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
package br.com.objectos.fs.zip;

import br.com.objectos.core.io.Copy;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.core.throwable.Try;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.PathNameVisitor;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @since 2
 */
public final class Unzip {

  private final byte[] buffer = new byte[8192];

  private final Enumeration<? extends ZipEntry> entries;

  private Throwable ioException;

  private final Directory workingDirectory;

  private final ZipFile zip;

  Unzip(Directory workingDirectory,
        ZipFile zip) {
    this.workingDirectory = workingDirectory;

    this.zip = zip;

    entries = zip.entries();
  }

  public static void unzip(
      Directory workingDirectory, RegularFile zipFile)
      throws IOException {
    Checks.checkNotNull(workingDirectory, "workingDirectory == null");
    Checks.checkNotNull(zipFile, "zipFile == null");

    File file;
    file = zipFile.toFile();

    ZipFile zip;
    zip = new ZipFile(file);

    Unzip unzip;
    unzip = new Unzip(workingDirectory, zip);

    unzip.execute();
  }

  public final void execute() throws IOException {
    try {
      execute0();
    } catch (Throwable e) {
      ioException = e;
    } finally {
      ioException = Try.close(ioException, zip);
    }

    Try.rethrowIfPossible(ioException, IOException.class);
  }

  private void execute0() throws IOException {
    if (!entries.hasMoreElements()) {
      return;
    }

    StringBuilder namePart;
    namePart = new StringBuilder();

    MutableList<String> nameParts;
    nameParts = MutableList.create();

    ThisVisitor thisVisitor;
    thisVisitor = new ThisVisitor();

    while (entries.hasMoreElements()) {
      ZipEntry entry;
      entry = entries.nextElement();

      String entryName;
      entryName = entry.getName();

      if (entryName.isEmpty()) {
        ioException = new IOException("Cannot unzip, entry pathname is empty");

        break;
      }

      nameParts.clear();

      char[] entryArray;
      entryArray = entryName.toCharArray();

      char c;
      c = entryArray[0];

      if (c == '/') {
        ioException = new IOException("Cannot unzip, entry pathname is absolute: " + entryName);

        break;
      }

      namePart.setLength(0);

      namePart.append(c);

      for (int i = 1, length = entryArray.length; i < length; i++) {
        c = entryArray[i];

        switch (c) {
          case '/':
          case '\\':
            String s;
            s = namePart.toString();

            nameParts.add(s);

            namePart.setLength(0);

            break;
          default:
            namePart.append(c);

            break;
        }
      }

      if (namePart.length() > 0) {
        String s;
        s = namePart.toString();

        nameParts.add(s);

        namePart.setLength(0);
      }

      if (nameParts.isEmpty()) {
        ioException = new IOException("Cannot unzip, entry pathname is empty");

        break;
      }

      String firstName;
      firstName = nameParts.get(0);

      String[] moreNames;
      moreNames = new String[nameParts.size() - 1];

      for (int i = 1, size = nameParts.size(); i < size; i++) {
        moreNames[i - 1] = nameParts.get(i);
      }

      ResolvedPath resolved;
      resolved = workingDirectory.resolve(firstName, moreNames);

      Throwable throwable;
      throwable = resolved.acceptPathNameVisitor(thisVisitor, entry);

      Try.rethrowIfPossible(throwable, IOException.class);
    }
  }

  private class ThisVisitor implements PathNameVisitor<Throwable, ZipEntry> {
    @Override
    public final Throwable visitDirectory(Directory directory, ZipEntry p) {
      return new IOException("Cannot unzip, destination exists: " + directory.getPath());
    }

    @Override
    public final Throwable visitNotFound(ResolvedPath notFound, ZipEntry entry) {
      try {
        notFound.createParents();
      } catch (IOException e) {
        return e;
      }

      if (entry.isDirectory()) {
        try {
          notFound.createDirectory();

          return null;
        } catch (IOException e) {
          return e;
        }
      }

      InputStream in;

      try {
        in = zip.getInputStream(entry);
      } catch (IOException e) {
        return e;
      }

      try {
        Copy.streamToSource(in, notFound, buffer);

        return null;
      } catch (IOException e) {
        return e;
      }
    }

    @Override
    public final Throwable visitRegularFile(RegularFile file, ZipEntry p) {
      return new IOException("Cannot unzip, destination exists: " + file.getPath());
    }
  }

}