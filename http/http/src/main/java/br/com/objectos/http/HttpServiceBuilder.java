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

import br.com.objectos.concurrent.CpuArray;
import br.com.objectos.concurrent.CpuWorker;
import br.com.objectos.concurrent.IoWorker;
import java.net.SocketAddress;
import objectos.logging.Logger;
import objectos.logging.NoOpLogger;

final class HttpServiceBuilder {

  private final SocketAddress address;

  private int bufferSize = Http.DEFAULT_BUFFER_SIZE;

  private final CpuArray cpuArray;

  private int enginesPerWorker = 1;

  private final IoWorker ioWorker;

  private Logger logger = NoOpLogger.getInstance();

  private final HttpProcessorProvider processorProvider;

  HttpServiceBuilder(SocketAddress address,
                     CpuArray cpuArray,
                     IoWorker ioWorker,
                     HttpProcessorProvider processorProvider) {
    this.address = address;

    this.cpuArray = cpuArray;

    this.ioWorker = ioWorker;

    this.processorProvider = processorProvider;
  }

  public final HttpService build() {
    int arraySize;
    arraySize = cpuArray.size();

    HttpWorker[] workers;
    workers = new HttpWorker[arraySize];

    for (int i = 0; i < arraySize; i++) {
      CpuWorker cpuWorker;
      cpuWorker = cpuArray.get(i);

      workers[i] = HttpWorker.create(
          bufferSize, cpuWorker, ioWorker, logger, enginesPerWorker, processorProvider);
    }

    return new HttpService(address, workers);
  }

  final void setBufferSize(int size) {
    this.bufferSize = size;
  }

  final void setEnginesPerWorker(int value) {
    enginesPerWorker = value;
  }

  final void setLogger(Logger logger) {
    this.logger = logger;
  }

}