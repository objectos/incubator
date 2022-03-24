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

import static org.testng.Assert.assertTrue;

import br.com.objectos.core.throwable.Try;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import org.testng.annotations.Test;

public class SelectorThreadTest extends AbstractHttpTest implements SelectorThreadAdapter {

  private volatile boolean accepted;

  @Override
  public final void acceptSocketChannel(SocketChannel socketChannel) {
    synchronized (this) {
      try {
        accepted = true;

        socketChannel.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      } finally {
        notifyAll();
      }
    }
  }

  @Test(description = TestCase0001.DESCRIPTION)
  public void testCase01() throws IOException {
    InetSocketAddress loopback;
    loopback = nextLoopbackSocketAddress();

    SelectorThread thread;
    thread = null;

    Throwable rethrow;
    rethrow = Try.begin();

    SocketChannel open;
    open = null;

    try {
      thread = SelectorThread.create(this, loopback);

      thread.start();

      assertTrue(thread.isAlive());

      assertTrue(thread.isOpen());

      Thread.sleep(2);

      open = SocketChannel.open(loopback);

      synchronized (this) {
        wait();
      }
    } catch (IOException e) {
      rethrow = e;
    } catch (InterruptedException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, open);

      thread.interrupt();
    }

    Try.rethrowIfPossible(rethrow, IOException.class);

    assertTrue(accepted);
  }

}