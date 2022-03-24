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

import br.com.objectos.concurrent.CpuWorker;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.logging.Logger;
import java.nio.channels.SocketChannel;

/**
 * @since 4
 */
final class HttpWorker {

  private final CpuWorker cpuWorker;

  private final HttpEngine[] engines;

  @SuppressWarnings("unused")
  private final Logger logger;

  HttpWorker(CpuWorker cpuWorker, Logger logger, HttpEngine[] engines) {
    this.cpuWorker = cpuWorker;

    this.logger = logger;

    this.engines = engines;
  }

  public static HttpWorker create(
      int bufferSize,
      CpuWorker cpuWorker,
      IoWorker ioWorker,
      Logger logger,
      int enginesPerWorker,
      HttpProcessorProvider processorProvider) {
    HttpEngine[] engines;
    engines = new HttpEngine[enginesPerWorker];

    StringDeduplicator stringDeduplicator;
    stringDeduplicator = new HashMapStringDeduplicator();

    for (int i = 0; i < enginesPerWorker; i++) {
      HttpProcessor processor;
      processor = processorProvider.create();

      HttpEngine engine;
      engine = new HttpEngine(bufferSize, ioWorker, logger, processor, stringDeduplicator);

      engines[i] = engine;
    }

    return new HttpWorker(cpuWorker, logger, engines);
  }

  public final boolean offerSocketChannel(SocketChannel socketChannel) {
    for (HttpEngine engine : engines) {
      if (engine.isActive()) {
        continue;
      }

      engine.setInput(socketChannel);

      if (cpuWorker.offer(engine)) {
        return true;
      }

      engine.unset();
    }

    return false;
  }

}