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
package br.com.objectos.core.logging.testing;

import java.util.NoSuchElementException;
import objectos.lang.Check;
import objectos.lang.Level;
import objectos.lang.Note;
import objectos.lang.Note0;
import objectos.lang.Note1;
import objectos.lang.Note2;
import objectos.lang.Note3;
import objectos.lang.NoteSink;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

/**
 * An in-memory Logger implementation with methods that allow for inspection of
 * the logged events. Also provides for printing logged events to the standard
 * output.
 */
public final class TestableLogger implements NoteSink {

  private final MutableList<Log> logs = new MutableList<>();

  private boolean sysout;

  /**
   * Creates a new logger.
   */
  public TestableLogger() {}

  /**
   * Removes all log instances from this logger.
   */
  public final void clear() {
    logs.clear();
  }

  /**
   * Returns {@code true} if the specified event was logged at least once.
   *
   * @param event
   *        the event to check
   *
   * @return {@code true} if the specified event was logged and {@code false}
   *         otherwise
   */
  public final boolean contains(Note event) {
    Check.notNull(event, "event == null");

    for (int i = 0, size = logs.size(); i < size; i++) {
      Log log;
      log = logs.get(i);

      if (log.hasEvent(event)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns the first log instance of the specified event in this logger or
   * throws an exception if the specified event has not been logged.
   *
   * @param <T1>
   *        the type of the log value
   *
   * @param event
   *        the event to check
   *
   * @return the first instance of the specified event
   *
   * @throws NoSuchElementException
   *         if the specified event has not been logged
   */
  @SuppressWarnings("unchecked")
  public final <T1> Event1Log<T1> getFirst(Note1<T1> event) {
    Check.notNull(event, "event == null");

    for (int i = 0, size = logs.size(); i < size; i++) {
      Log log;
      log = logs.get(i);

      if (log.hasEvent(event)) {
        return (Event1Log<T1>) log;
      }
    }

    throw new NoSuchElementException();
  }

  /**
   * Returns all of the log instances in this logger.
   *
   * @return all of the log instances in this logger
   */
  public final ImmutableList<Log> getLogs() {
    return logs.toImmutableList();
  }

  /**
   * Returns all of the log instances in this logger having the specified level.
   *
   * @param level
   *        the level of the returned log instances
   *
   * @return all of the log instances in this logger having the specified level
   */
  public final ImmutableList<Log> getLogsByLevel(Level level) {
    Check.notNull(level, "level == null");

    MutableList<Log> result;
    result = new MutableList<>();

    for (int i = 0, size = logs.size(); i < size; i++) {
      Log l;
      l = logs.get(i);

      if (!l.isLevel(level)) {
        continue;
      }

      result.add(l);
    }

    return result.toImmutableList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEnabled(Note event) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final NoteSink replace(NoteSink logger) {
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void send(Note0 event) {
    if (event == null) {
      return;
    }

    Event0Log log;
    log = new Event0Log(event);

    log0(log);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T1> void send(Note1<T1> event, T1 v1) {
    if (event == null) {
      return;
    }

    Event1Log<T1> log;
    log = new Event1Log<T1>(event, v1);

    log0(log);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T1, T2> void send(Note2<T1, T2> event, T1 v1, T2 v2) {
    if (event == null) {
      return;
    }

    Event2Log<T1, T2> log;
    log = new Event2Log<T1, T2>(event, v1, v2);

    log0(log);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T1, T2, T3> void send(Note3<T1, T2, T3> event, T1 v1, T2 v2, T3 v3) {
    if (event == null) {
      return;
    }

    Event3Log<T1, T2, T3> log;
    log = new Event3Log<T1, T2, T3>(event, v1, v2, v3);

    log0(log);
  }

  /**
   * Sets if this logger should print the logged events to the standard output.
   *
   * @param value
   *        {@code true} if this logger should print the logged events to the
   *        standard output
   */
  public final void setSysout(boolean value) {
    sysout = value;
  }

  private boolean log0(Log log) {
    if (sysout) {
      log.print();
    }

    return logs.add(log);
  }

}