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
package br.com.objectos.logger;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import objectos.lang.Checks;
import objectos.lang.Logger;

/**
 * An in-memory logger implementation that can be used to bootstrap a
 * {@link StorageLogger}.
 *
 * @since 2
 */
public final class BootstrapLogger extends AbstractConfigurableLogger {

  private boolean bootstrap;

  private final MutableList<WriteJobLog> logs = MutableList.create();

  /**
   * Creates a new instance.
   */
  public BootstrapLogger() {}

  /**
   * Prints logged events to the standard output stream if boostrap failed; does
   * nothing otherwise.
   */
  @Override
  public final void close() {
    if (bootstrap) {
      return;
    }

    if (logs.isEmpty()) {
      return;
    }

    StandardLayout layout;
    layout = new StandardLayout();

    for (int i = 0; i < logs.size(); i++) {
      WriteJobLog log;
      log = logs.get(i);

      String s;
      s = layout.formatLog(log);

      System.out.println(s);
    }
  }

  /**
   * Writes all logged events to the specified logger if it is a
   * {@link StorageLogger}.
   *
   * @param logger
   *        a possibly storage logger instance to bootstrap
   *
   * @return the specified logger
   */
  @Override
  public final Logger replace(Logger logger) {
    Checks.checkNotNull(logger, "logger == null");

    ImmutableList<WriteJobLog> list;
    list = logs.toImmutableList();

    for (int i = 0; i < list.size(); i++) {
      WriteJobLog log;
      log = list.get(i);

      log.replay(logger);
    }

    bootstrap = true;

    return super.replace(logger);
  }

  @Override
  final void write(WriteJobLog log) {
    logs.add(log);
  }

}