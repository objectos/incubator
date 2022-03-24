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
package br.com.objectos.fs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;

class ComunsIoJava6 {

  private ComunsIoJava6() {}

  public static URI toUri(URI source) {
    String scheme;
    scheme = source.getScheme();

    String path;
    path = source.getPath();

    try {
      return new URI(scheme, "", path, null);
    } catch (URISyntaxException e) {
      throw new AssertionError(e);
    }
  }

  @SuppressWarnings("resource")
  static FileChannel openReadAndWriteChannel(File delegate) throws IOException {
    RandomAccessFile file;
    file = new RandomAccessFile(delegate, "rw");

    FileChannel channel;
    channel = file.getChannel();

    channel.position(file.length());

    return channel;
  }

  @SuppressWarnings("resource")
  static FileChannel openWriteChannel(File delegate) throws IOException {
    FileOutputStream stream;
    stream = new FileOutputStream(delegate, true);

    return stream.getChannel();
  }

}
