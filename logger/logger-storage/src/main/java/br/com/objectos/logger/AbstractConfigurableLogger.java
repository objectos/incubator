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

import objectos.lang.Checks;
import objectos.lang.Event;
import objectos.lang.Event0;
import objectos.lang.Event1;
import objectos.lang.Event2;
import objectos.lang.Event3;
import objectos.lang.Level;
import objectos.lang.Logger;

abstract class AbstractConfigurableLogger implements ConfigurableLogger {

  Level level = Level.TRACE;

  /**
   * Returns the current level of this logger.
   *
   * @return the current level of this logger
   */
  public final Level getLevel() {
    return level;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEnabled(Event event) {
    if (event == null) {
      return false;
    }

    return event.isEnabled(level);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void log(Event0 event) {
    if (event == null) {
      return;
    }

    if (!event.isEnabled(level)) {
      return;
    }

    write(
        new WriteJobLog0(event)
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T1> void log(Event1<T1> event, T1 v1) {
    if (event == null) {
      return;
    }

    if (!event.isEnabled(level)) {
      return;
    }

    write(
        new WriteJobLog1<T1>(event, v1)
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T1, T2> void log(Event2<T1, T2> event, T1 v1, T2 v2) {
    if (event == null) {
      return;
    }

    if (!event.isEnabled(level)) {
      return;
    }

    write(
        new WriteJobLog2<T1, T2>(event, v1, v2)
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T1, T2, T3> void log(Event3<T1, T2, T3> event, T1 v1, T2 v2, T3 v3) {
    if (event == null) {
      return;
    }

    if (!event.isEnabled(level)) {
      return;
    }

    write(
        new WriteJobLog3<T1, T2, T3>(event, v1, v2, v3)
    );
  }

  @Override
  public Logger replace(Logger logger) {
    return logger;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setLevel(Level level) {
    this.level = Checks.checkNotNull(level, "level == null");
  }

  abstract void write(WriteJobLog log);

}