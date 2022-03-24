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
package br.com.objectos.http.server.jetty;

import br.com.objectos.http.path.Router;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.RequestProto;
import br.com.objectos.http.server.Resolution;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.ResponseWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MutableRouterHandler extends AbstractRouterHandler {

  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  private Router router;

  MutableRouterHandler(Router router) {
    this.router = router;
  }

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
  final void handleImpl(RequestProto request, ResponseWriter writer) throws HttpException {
    Lock readLock = lock.readLock();
    readLock.lock();
    try {
      Resolution resolution = router.resolve(request);
      Response response = resolution.execute();
      response.writeTo(writer);
    } finally {
      readLock.unlock();
    }
  }

}
