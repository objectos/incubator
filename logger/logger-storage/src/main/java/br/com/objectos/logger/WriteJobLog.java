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

import objectos.lang.Level;
import objectos.lang.Note;
import objectos.lang.NoteSink;
import objectos.lang.ToString;

abstract class WriteJobLog extends Log implements ToString.Formattable {

  WriteJobLog(Note event) {
    Object k;
    k = event.key();

    key = k.toString();

    level = event.level();

    source = event.source();

    Thread currentThread;
    currentThread = Thread.currentThread();

    thread = currentThread.getName();

    timestamp = System.currentTimeMillis();
  }

  WriteJobLog(String key, Level level, String source) {
    this.key = key;

    this.level = level;

    this.source = source;

    Thread currentThread;
    currentThread = Thread.currentThread();

    thread = currentThread.getName();

    timestamp = System.currentTimeMillis();
  }

  @Override
  public void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "", key,
      "", level,
      "", source
    );
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

  abstract byte getTypeV1();

  void replay(NoteSink logger) {}

}