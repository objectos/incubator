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
package br.com.objectos.fs.watch;

import br.com.objectos.fs.RegularFile;
import java.io.IOException;

class RegularFileWrapper {

  final RegularFile file;

  long lastModified;

  long size;

  RegularFileWrapper(RegularFile file) {
    this.file = file;

    long lastModified = 0;
    long size = 0;

    try {
      lastModified = file.getLastModifiedMillis();

      size = file.size();
    } catch (IOException e) {
      // lastModified && size do not throw IOException for java6
    }

    this.lastModified = lastModified;
    this.size = size;
  }

  final boolean modified(RegularFile that) throws IOException {
    boolean result;
    result = false;

    long thatLastModified;
    thatLastModified = that.getLastModifiedMillis();

    if (lastModified != thatLastModified) {
      lastModified = thatLastModified;

      result = true;
    }

    long thatSize;
    thatSize = that.size();

    if (size != thatSize) {
      size = thatSize;

      result = true;
    }

    return result;
  }

}