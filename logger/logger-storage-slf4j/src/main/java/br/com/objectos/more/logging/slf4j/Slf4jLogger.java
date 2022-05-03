/*
 * Copyright (C) 2021 Objectos Software LTDA.
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
package br.com.objectos.more.logging.slf4j;

import br.com.objectos.logger.StorageLogger;
import objectos.logging.Level;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

final class Slf4jLogger implements org.slf4j.Logger {

  private final StorageLogger logger;

  private final String name;

  Slf4jLogger(String name, StorageLogger logger) {
    this.name = name;
    this.logger = logger;
  }

  @Override
  public void debug(Marker marker, String msg) {}

  @Override
  public void debug(Marker marker, String format, Object arg) {}

  @Override
  public void debug(Marker marker, String format, Object... arguments) {}

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {}

  @Override
  public void debug(Marker marker, String msg, Throwable t) {}

  @Override
  public final void debug(String msg) {
    log0(Level.DEBUG, msg);
  }

  @Override
  public final void debug(String format, Object arg) {
    log1(Level.DEBUG, format, arg);
  }

  @Override
  public final void debug(String format, Object... arguments) {
    logN(Level.DEBUG, format, arguments);
  }

  @Override
  public final void debug(String format, Object arg1, Object arg2) {
    log2(Level.DEBUG, format, arg1, arg2);
  }

  @Override
  public final void debug(String msg, Throwable t) {
    logStackTrace(Level.DEBUG, msg, t);
  }

  @Override
  public void error(Marker marker, String msg) {}

  @Override
  public void error(Marker marker, String format, Object arg) {}

  @Override
  public void error(Marker marker, String format, Object... arguments) {}

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {}

  @Override
  public void error(Marker marker, String msg, Throwable t) {}

  @Override
  public final void error(String msg) {
    log0(Level.ERROR, msg);
  }

  @Override
  public final void error(String format, Object arg) {
    log1(Level.ERROR, format, arg);
  }

  @Override
  public final void error(String format, Object... arguments) {
    logN(Level.ERROR, format, arguments);
  }

  @Override
  public final void error(String format, Object arg1, Object arg2) {
    log2(Level.ERROR, format, arg1, arg2);
  }

  @Override
  public final void error(String msg, Throwable t) {
    logStackTrace(Level.ERROR, msg, t);
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public void info(Marker marker, String msg) {}

  @Override
  public void info(Marker marker, String format, Object arg) {}

  @Override
  public void info(Marker marker, String format, Object... arguments) {}

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {}

  @Override
  public void info(Marker marker, String msg, Throwable t) {}

  @Override
  public final void info(String msg) {
    log0(Level.INFO, msg);
  }

  @Override
  public final void info(String format, Object arg) {
    log1(Level.INFO, format, arg);
  }

  @Override
  public final void info(String format, Object... arguments) {
    logN(Level.INFO, format, arguments);
  }

  @Override
  public final void info(String format, Object arg1, Object arg2) {
    log2(Level.INFO, format, arg1, arg2);
  }

  @Override
  public final void info(String msg, Throwable t) {
    logStackTrace(Level.INFO, msg, t);
  }

  @Override
  public final boolean isDebugEnabled() {
    return isEnabled(Level.DEBUG);
  }

  @Override
  public final boolean isDebugEnabled(Marker marker) {
    return isDebugEnabled();
  }

  @Override
  public final boolean isErrorEnabled() {
    return isEnabled(Level.ERROR);
  }

  @Override
  public final boolean isErrorEnabled(Marker marker) {
    return isErrorEnabled();
  }

  @Override
  public final boolean isInfoEnabled() {
    return isEnabled(Level.INFO);
  }

  @Override
  public final boolean isInfoEnabled(Marker marker) {
    return isInfoEnabled();
  }

  @Override
  public final boolean isTraceEnabled() {
    return isEnabled(Level.TRACE);
  }

  @Override
  public final boolean isTraceEnabled(Marker marker) {
    return isTraceEnabled();
  }

  @Override
  public final boolean isWarnEnabled() {
    return isEnabled(Level.WARN);
  }

  @Override
  public final boolean isWarnEnabled(Marker marker) {
    return isWarnEnabled();
  }

  @Override
  public void trace(Marker marker, String msg) {}

  @Override
  public void trace(Marker marker, String format, Object arg) {}

  @Override
  public void trace(Marker marker, String format, Object... argArray) {}

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {}

  @Override
  public void trace(Marker marker, String msg, Throwable t) {}

  @Override
  public final void trace(String msg) {
    log0(Level.TRACE, msg);
  }

  @Override
  public final void trace(String format, Object arg) {
    log1(Level.TRACE, format, arg);
  }

  @Override
  public final void trace(String format, Object... arguments) {
    logN(Level.TRACE, format, arguments);
  }

  @Override
  public final void trace(String format, Object arg1, Object arg2) {
    log2(Level.TRACE, format, arg1, arg2);
  }

  @Override
  public final void trace(String msg, Throwable t) {
    logStackTrace(Level.TRACE, msg, t);
  }

  @Override
  public void warn(Marker marker, String msg) {}

  @Override
  public void warn(Marker marker, String format, Object arg) {}

  @Override
  public void warn(Marker marker, String format, Object... arguments) {}

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {}

  @Override
  public void warn(Marker marker, String msg, Throwable t) {}

  @Override
  public final void warn(String msg) {
    log0(Level.WARN, msg);
  }

  @Override
  public final void warn(String format, Object arg) {
    log1(Level.WARN, format, arg);
  }

  @Override
  public final void warn(String format, Object... arguments) {
    logN(Level.WARN, format, arguments);
  }

  @Override
  public final void warn(String format, Object arg1, Object arg2) {
    log2(Level.WARN, format, arg1, arg2);
  }

  @Override
  public final void warn(String msg, Throwable t) {
    logStackTrace(Level.WARN, msg, t);
  }

  private boolean isEnabled(Level requested) {
    Level actual;
    actual = logger.getLevel();

    return requested.compareTo(actual) >= 0;
  }

  private void log0(Level level, String msg) {
    logger.slf4j(name, level, msg);
  }

  private void log1(Level level, String format, Object arg) {
    FormattingTuple tuple;
    tuple = MessageFormatter.format(format, arg);

    logFormattingTuple(level, tuple);
  }

  private void log2(Level level, String format, Object arg1, Object arg2) {
    FormattingTuple tuple;
    tuple = MessageFormatter.format(format, arg1, arg2);

    logFormattingTuple(level, tuple);
  }

  private void logFormattingTuple(Level level, FormattingTuple tuple) {
    String message;
    message = tuple.getMessage();

    Throwable throwable;
    throwable = tuple.getThrowable();

    if (throwable != null) {
      logStackTrace(level, message, throwable);
    } else {
      log0(level, message);
    }
  }

  private void logN(Level level, String format, Object[] arguments) {
    FormattingTuple tuple;
    tuple = MessageFormatter.arrayFormat(format, arguments);

    logFormattingTuple(level, tuple);
  }

  private void logStackTrace(Level level, String msg, Throwable t) {
    logger.slf4j(name, level, msg, t);
  }

}