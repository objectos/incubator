/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import org.apache.maven.wagon.events.TransferEvent;
import org.apache.maven.wagon.events.TransferListener;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.logging.LoggerManager;
import org.slf4j.Marker;

abstract class AbstractLogger
    extends AbstractPrintStream
    implements
    org.codehaus.plexus.logging.Logger,
    org.slf4j.Logger,
    LoggerManager,
    TransferListener {

  @Override
  public void debug(Marker marker, String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(Marker marker, String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(Marker marker, String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(Marker marker, String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void debug(String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(Marker marker, String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(Marker marker, String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(Marker marker, String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(Marker marker, String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void error(String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void fatalError(String message) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void fatalError(String message, Throwable throwable) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final int getActiveLoggerCount() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public Logger getChildLogger(String name) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public Logger getLoggerForComponent(String role) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public Logger getLoggerForComponent(String role, String hint) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final int getThreshold() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(Marker marker, String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(Marker marker, String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(Marker marker, String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(Marker marker, String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void info(String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isDebugEnabled() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isErrorEnabled() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isFatalErrorEnabled() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isInfoEnabled() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isTraceEnabled() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isWarnEnabled() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void returnComponentLogger(String role) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void returnComponentLogger(String role, String hint) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void setThreshold(int threshold) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void setThresholds(int threshold) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(Marker marker, String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(Marker marker, String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(Marker marker, String format, Object... argArray) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(Marker marker, String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void trace(String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void transferCompleted(TransferEvent transferEvent) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void transferError(TransferEvent transferEvent) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void transferInitiated(TransferEvent transferEvent) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void transferProgress(TransferEvent transferEvent, byte[] buffer, int length) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void transferStarted(TransferEvent transferEvent) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(Marker marker, String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(Marker marker, String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(Marker marker, String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(Marker marker, String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(String msg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(String format, Object arg) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(String format, Object... arguments) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void warn(String msg, Throwable t) {
    throw new UnsupportedOperationException("Implement me");
  }

}
