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
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.MutableHttpServer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MutableHttpServerImpl extends AbstractHttpServer implements MutableHttpServer {

  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  private Router router;

  MutableHttpServerImpl(Channel channel, Router router) {
    super(channel);
    this.router = router;
  }

  @Override
  public final void reconfigure(HttpModule module) {
    Lock writeLock = lock.writeLock();
    writeLock.lock();
    try {
      router = Router.of(module);
    } finally {
      writeLock.unlock();
    }
  }

  @Override
  final Router router() {
    Lock readLock = lock.readLock();
    readLock.lock();
    try {
      return router;
    } finally {
      readLock.unlock();
    }
  }

}
