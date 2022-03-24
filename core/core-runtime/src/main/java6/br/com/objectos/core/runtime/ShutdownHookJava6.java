/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.core.runtime;

import br.com.objectos.core.object.Checks;
import br.com.objectos.latest.Concrete;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Concrete.Bridge
class ShutdownHookJava6 extends AbstractShutdownHook {

  private List<Closeable> closeables;

  @Concrete.Constructor
  ShutdownHookJava6() {}

  /**
   * Registers the specified {@link Closeable} instance to the
   * facility provided by this class.
   *
   * <p>
   * During the JVM shutdown process, the facility will invoke the
   * {@link Closeable#close()} method on supplied instance.
   *
   * @param closeable
   *        a {@link Closeable} instance to be registered
   */
  public static void register(Closeable closeable) {
    ShutdownHook hook;
    hook = getHook();

    hook.addCloseable(closeable);
  }

  @Override
  final void addCloseable(Closeable closeable) {
    Checks.checkNotNull(closeable, "closeable == null");

    if (closeables == null) {
      synchronized (this) {
        if (closeables == null) {
          closeables = new ArrayList<Closeable>();
        }
      }
    }

    synchronized (closeables) {
      closeables.add(closeable);
    }
  }

  @Override
  final void run0() {
    if (closeables != null) {
      doCloseables();
    }
  }

  private void doCloseables() {
    for (int i = 0; i < closeables.size(); i++) {
      Closeable c;
      c = closeables.get(i);

      try {
        c.close();
      } catch (IOException e) {
        log(e);
      }
    }
  }

}
