/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.git;

import br.com.objectos.concurrent.IoWorker;
import objectos.lang.NoOpNoteSink;
import objectos.lang.NoteSink;

final class GitEngineBuilder {

  private int bufferSize = Git.DEFAULT_BUFFER_SIZE;

  private final IoWorker ioWorker;

  private NoteSink logger = NoOpNoteSink.getInstance();

  GitEngineBuilder(IoWorker ioWorker) {
    this.ioWorker = ioWorker;
  }

  public final void setBufferSize(int size) {
    bufferSize = size;
  }

  public final void setLogger(NoteSink logger) {
    this.logger = logger;
  }

  final GitEngine build() {
    return new GitEngine(
      bufferSize,

      ioWorker,

      logger
    );
  }

}
