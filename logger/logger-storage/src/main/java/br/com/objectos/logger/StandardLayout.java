/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import objectos.lang.Level;

/**
 * Provides a standard layout implementation.
 *
 * <p>
 * Instances of this class are not thread-safe.
 *
 * @since 2
 */
public final class StandardLayout implements Layout {

  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  /**
   * {@inheritDoc}
   */
  @Override
  public final String formatLog(Log log) {
    StringBuilder out;
    out = new StringBuilder();

    Date date;
    date = new Date(log.timestamp);

    out.append(dateFormat.format(date));

    out.append(' ');

    Level level;
    level = log.level;

    String levelName;
    levelName = level.name();

    Logging.pad(out, levelName, 5);

    out.append(" --- ");

    out.append('[');

    Logging.pad(out, log.thread, 15);

    out.append(']');

    out.append(' ');

    Logging.abbreviate(out, log.source, 40);

    out.append(' ');
    out.append(':');
    out.append(' ');

    out.append(log.key);

    for (int i = 0, size = log.size(); i < size; i++) {
      String value;
      value = log.formatValue(i);

      out.append(' ');

      out.append(value);
    }

    log.printStackTrace(out);

    return formatReturn(out);
  }

  final String format(Level level, Object source, String message) {
    StringBuilder out;
    out = new StringBuilder();

    String date;
    date = dateFormat.format(new Date());

    out.append(date);

    out.append(' ');

    String levelName;
    levelName = level.name();

    Logging.pad(out, levelName, 5);

    out.append(" --- ");

    Thread thread;
    thread = Thread.currentThread();

    String threadName;
    threadName = thread.getName();

    out.append('[');

    Logging.pad(out, threadName, 15);

    out.append(']');

    out.append(' ');

    String sourceName;
    sourceName = "";

    if (source != null) {
      Class<? extends Object> sourceType;
      sourceType = source.getClass();

      sourceName = sourceType.getCanonicalName();
    }

    Logging.abbreviate(out, sourceName, 40);

    out.append(' ');
    out.append(':');
    out.append(' ');

    out.append(message);

    out.append(System.lineSeparator());

    return out.toString();
  }

  private String formatReturn(StringBuilder out) {
    out.append(MoreLogging.LINE_SEPARATOR);

    return out.toString();
  }

}