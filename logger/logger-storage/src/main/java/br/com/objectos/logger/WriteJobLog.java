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

import br.com.objectos.core.object.ToString;
import objectos.logging.Event;
import objectos.logging.Level;
import objectos.logging.Logger;

abstract class WriteJobLog extends Log {

  WriteJobLog(Event event) {
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
  public String toString() {
    return ToString.toString(
      this,
      "", key,
      "", level,
      "", source
    );
  }

  abstract byte getTypeV1();

  void replay(Logger logger) {}

}