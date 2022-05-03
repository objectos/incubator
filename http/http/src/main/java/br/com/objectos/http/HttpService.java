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
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.object.Checks;
import br.com.objectos.core.service.AbstractService;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Random;
import objectos.logging.Logger;

/**
 * @since 4
 */
public final class HttpService extends AbstractService {

  private final SocketAddress address;

  private final Random random = new Random();

  private SelectorThread thread;

  private final HttpWorker[] workers;

  HttpService(SocketAddress address, HttpWorker[] workers) {
    this.address = address;

    this.workers = workers;
  }

  public static Option bufferSize(final int size) {
    Http.checkBufferSize(size);

    return new Option() {
      @Override
      final void acceptBuilder(HttpServiceBuilder builder) {
        builder.setBufferSize(size);
      }
    };
  }

  public static HttpService create(
      SocketAddress address, CpuArray cpuArray, IoWorker ioWorker,
      HttpProcessorProvider processorProvider, Option... options) {
    Checks.checkNotNull(address, "address == null");
    Checks.checkNotNull(cpuArray, "cpuArray == null");
    Checks.checkNotNull(ioWorker, "ioWorker == null");
    Checks.checkNotNull(processorProvider, "processorProvider == null");
    Checks.checkNotNull(options, "options == null");

    HttpServiceBuilder builder;
    builder = new HttpServiceBuilder(address, cpuArray, ioWorker, processorProvider);

    Option o;

    for (int i = 0; i < options.length; i++) {
      o = options[i];

      Checks.checkNotNull(o, "options[", i, "] == null");

      o.acceptBuilder(builder);
    }

    return builder.build();
  }

  public static Option enginesPerWorker(final int value) {
    Checks.checkArgument(value > 0, "engines/worker minimum value is 1 engine/worker");

    return new Option() {
      @Override
      final void acceptBuilder(HttpServiceBuilder builder) {
        builder.setEnginesPerWorker(value);
      }
    };
  }

  public static Option logger(Logger logger) {
    Checks.checkNotNull(logger, "logger == null");

    return new Option() {
      @Override
      final void acceptBuilder(HttpServiceBuilder builder) {
        builder.setLogger(logger);
      }
    };
  }

  @Override
  public final void startService() throws Exception {
    ThisSelectorThreadAdapter adapter;
    adapter = new ThisSelectorThreadAdapter();

    thread = SelectorThread.create(adapter, address);

    thread.start();
  }

  @Override
  public final void stopService() throws Exception {
    thread.interrupt();
  }

  /**
   * A {@code HttpService} configuration option.
   */
  public abstract static class Option {

    Option() {}

    abstract void acceptBuilder(HttpServiceBuilder builder);

  }

  private class ThisSelectorThreadAdapter implements SelectorThreadAdapter {

    @Override
    public final void acceptSocketChannel(SocketChannel socketChannel) {
      int index;
      index = random.nextInt(workers.length);

      HttpWorker w;
      w = workers[index];

      boolean accepted;
      accepted = w.offerSocketChannel(socketChannel);

      if (!accepted) {
        throw new UnsupportedOperationException("Implement me");
      }
    }

  }

}