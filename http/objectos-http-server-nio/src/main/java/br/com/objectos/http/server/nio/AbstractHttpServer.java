/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import br.com.objectos.http.path.Router;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.HttpServer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class AbstractHttpServer implements HttpServer {

  private final Channel channel;
  private final ExecutorService executor;

  AbstractHttpServer(Channel channel) {
    this.channel = channel;
    executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }

  @Override
  public final void join() throws InterruptedException {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void start() {
    int size = Runtime.getRuntime().availableProcessors();
    for (int i = 0; i < size; i++) {
      executor.submit(new Lifecycle());
    }
  }

  @Override
  public final void stop() {
    try {
      channel.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }
  }

  abstract Router router();

  private void accept(ByteBuffer buffer) throws IOException {
    try (Socket socket = channel.accept(buffer)) {
      socket.parse()
          .resolve(router())
          .execute()
          .writeTo(new NioResponseWriter(socket));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (HttpException e) {
      e.printStackTrace();
    }
  }

  private class Lifecycle implements Runnable {

    private final ByteBuffer buffer = ByteBuffer.allocate(8192);

    @Override
    public void run() {
      while (!executor.isShutdown()) {
        try {
          accept(buffer);
        } catch (IOException e) {
          e.printStackTrace();
          stop();
        }
      }
    }

  }

}