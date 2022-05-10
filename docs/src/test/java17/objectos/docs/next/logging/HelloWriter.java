/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.next.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import objectos.logging.Event0;
import objectos.logging.Event1;
import objectos.logging.Logger;

class HelloWriter extends Thread {
  static final Event0 DONE = Event0.info();

  static final Event1<IOException> IO_ERROR = Event1.error();

  private final Logger logger;
  private final int quantity;

  private Path file;

  HelloWriter(Logger logger, int quantity) {
    this.logger = logger;
    this.quantity = quantity;
  }

  @Override
  public void run() {
    try {
      run0();

      logger.log(DONE);
    } catch (IOException e) {
      logger.log(IO_ERROR, e);
    }
  }

  final List<String> readLines() throws IOException {
    return Files.readAllLines(file);
  }

  private void run0() throws IOException {
    file = Files.createTempFile("hello-writer", ".txt");

    try (var writer = Files.newBufferedWriter(file)) {
      for (int i = 0; i < quantity; i++) {
        writer.write("hello!");

        writer.newLine();
      }
    }
  }
}