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
package br.com.objectos.fs.watch;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import br.com.objectos.fs.watch.Watch.Event;
import br.com.objectos.fs.watch.Watch.Option;
import br.com.objectos.fs.watch.WatchServiceJava7.Builder;
import br.com.objectos.latest.Concrete;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.Set;
import objectos.lang.Check;

@Concrete.Bridge
class FactoryJava7 extends FactoryJavaAny {

  private static final WatchEvent.Kind<?>[] EVENTS = new WatchEvent.Kind[] {
      ENTRY_CREATE,
      ENTRY_DELETE,
      ENTRY_MODIFY
  };

  FactoryJava7() {}

  static WatchEvent.Kind<?>[] getEvents(Set<Event> set) {
    WatchEvent.Kind<?>[] events;
    events = new WatchEvent.Kind<?>[set.size()];

    int i = 0;

    for (Event event : set) {
      int ordinal;
      ordinal = event.ordinal();

      events[i++] = EVENTS[ordinal];
    }

    return events;
  }

  @Override
  final Watch.Service create(Option[] options) throws IOException {
    Check.notNull(options, "options == null");

    Builder builder;
    builder = new WatchServiceJava7.Builder();

    for (int i = 0, length = options.length; i < length; i++) {
      Option o;
      o = options[i];

      if (o == null) {
        throw new NullPointerException("options[" + i + "] == null");
      }

      o.acceptWatchServiceBuilder(builder);
    }

    return builder.build();
  }

}
