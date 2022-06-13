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

import br.com.objectos.mojo.BuildLog.Event;
import br.com.objectos.mojo.BuildLog.Message;
import objectos.util.ImmutableList;
import objectos.util.MutableList;
import org.codehaus.plexus.logging.Logger;
import org.slf4j.event.Level;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

final class BuildLogger extends AbstractLogger {

  private final Level level = Level.INFO;

  private final MutableList<Message> messages = MutableList.create();

  BuildLogger() {}

  public final BuildLog build() {
    BuildLog log;
    log = new BuildLog(this);

    messages.clear();

    return log;
  }

  @Override
  public final void debug(String msg) {
    add(Level.DEBUG, msg);
  }

  @Override
  public final void error(String msg) {
    add(Level.ERROR, msg);
  }

  @Override
  public final Logger getLoggerForComponent(String role) {
    return this;
  }

  @Override
  public final Logger getLoggerForComponent(String role, String hint) {
    String name = role;

    if (hint != null) {
      name = role + '.' + hint;
    }

    return getLoggerForComponent(name);
  }

  @Override
  public final void info(String msg) {
    add(Level.INFO, msg);
  }

  @Override
  public final void info(String format, Object arg) {
    info(MessageFormatter.format(format, arg));
  }

  @Override
  public final void info(String format, Object... arguments) {
    info(MessageFormatter.format(format, arguments));
  }

  @Override
  public final void info(String format, Object arg1, Object arg2) {
    info(MessageFormatter.format(format, arg1, arg2));
  }

  @Override
  public final boolean isDebugEnabled() {
    return isLevelEnabled(Level.DEBUG);
  }

  @Override
  public final boolean isErrorEnabled() {
    return isLevelEnabled(Level.ERROR);
  }

  @Override
  public final boolean isInfoEnabled() {
    return isLevelEnabled(Level.INFO);
  }

  @Override
  public final boolean isWarnEnabled() {
    return isLevelEnabled(Level.WARN);
  }

  @Override
  public final void warn(String msg) {
    add(Level.WARN, msg);
  }

  final ImmutableList<Message> messages() {
    return messages.toImmutableList();
  }

  @Override
  final void writeMessage(String msg) {
    String value;

    if (msg != null) {
      value = msg;
    } else {
      value = "null";
    }

    Message message;
    message = new Message(value);

    messages.add(message);
  }

  private void add(Level level, String msg) {
    if (isLevelEnabled(level)) {
      synchronized (messages) {
        Event event;
        event = new Event(level, msg);

        messages.add(event);
      }
    }
  }

  private void info(FormattingTuple format) {
    info(format.getMessage());
  }

  private boolean isLevelEnabled(Level test) {
    int result;
    result = level.compareTo(test);

    return result >= 0;
  }

}