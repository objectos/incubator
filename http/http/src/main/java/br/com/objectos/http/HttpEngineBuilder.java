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
package br.com.objectos.http;

import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.logging.Logger;
import br.com.objectos.logging.NoopLogger;

final class HttpEngineBuilder {

  private int bufferSize = Http.DEFAULT_BUFFER_SIZE;

  private final IoWorker ioWorker;

  private Logger logger = NoopLogger.getInstance();

  private final HttpProcessor processor;

  private final StringDeduplicator stringDeduplicator;

  HttpEngineBuilder(IoWorker ioWorker,
                    HttpProcessor processor,
                    StringDeduplicator stringDeduplicator) {
    this.ioWorker = ioWorker;

    this.processor = processor;

    this.stringDeduplicator = stringDeduplicator;
  }

  public final void setBufferSize(int size) {
    bufferSize = size;
  }

  public final void setLogger(Logger logger) {
    this.logger = logger;
  }

  final HttpEngine build() {
    return new HttpEngine(
        bufferSize,

        ioWorker,

        logger,

        processor,

        stringDeduplicator
    );
  }

}
