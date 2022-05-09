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

import java.util.List;
import objectos.logging.Event;

abstract class ReadJobLog extends Log {

  abstract void acceptValues(List<String> list);

  final boolean matchesEvent(Event event) {
    return key.equals(event.getKey())
        && level.equals(event.getLevel())
        && source.equals(getSource(event));
  }

  final boolean matchesThread(Thread thread) {
    String name;
    name = thread.getName();

    return this.thread.equals(name);
  }

  abstract void setThrowable(int index, ReadJobThrowable throwable);

  private String getSource(Event event) {
    return event.source();
  }

}