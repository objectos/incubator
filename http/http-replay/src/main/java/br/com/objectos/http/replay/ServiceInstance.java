/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

import br.com.objectos.core.io.InputStreamSource;
import java.io.IOException;

final class ServiceInstance implements ReplayService, Runnable {

  private final ByteSourceJob byteSourceJob;

  private Thread thread;

  private final Object toggle = new Object();

  ServiceInstance(ByteSourceJob byteSourceJob) {
    this.byteSourceJob = byteSourceJob;
  }

  @Override
  public final void close() throws IOException {
    if (thread == null) {
      return;
    }

    if (thread.isAlive()) {
      thread.interrupt();
    }
  }

  @Override
  public final void replay(InputStreamSource source) {
    synchronized (toggle) {
      byteSourceJob.setInput(source);

      toggle.notify();
    }
  }

  @Override
  public final void run() {
    while (true) {
      run0();
    }
  }

  @Override
  public final void start() {
    thread = new Thread(this);

    thread.setDaemon(true);

    thread.start();
  }

  @Override
  public final String toString() {
    return byteSourceJob.toString();
  }

  private void run0() {
    synchronized (toggle) {
      try {
        toggle.wait();

        byteSourceJob.run();
      } catch (InterruptedException e) {
        Thread.interrupted();
        return;
      }
    }
  }

}
