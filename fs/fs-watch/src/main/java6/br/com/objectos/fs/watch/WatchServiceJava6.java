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
package br.com.objectos.fs.watch;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.watch.Watch.Event;
import br.com.objectos.fs.watch.Watch.Listener;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import objectos.lang.Check;
import objectos.util.ImmutableSet;
import objectos.util.MutableMap;

final class WatchServiceJava6 extends Thread implements Watch.Service {

  private final ImmutableSet<WatchDirectoryJava6> directories;

  private final int timeout = 1 * 1000;

  WatchServiceJava6(ImmutableSet<WatchDirectoryJava6> directories) {
    super("WatchService");

    this.directories = directories;
  }

  @Override
  public final void run() {
    while (!Thread.interrupted()) {
      try {
        Thread.sleep(timeout);

        for (WatchDirectoryJava6 dir : directories) {
          dir.checkForEvents();
        }
      } catch (InterruptedException e) {
        return;
      }
    }
  }

  @Override
  public final void startService() {
    start();
  }

  @Override
  public final void stopService() {
    interrupt();
  }

  static final class Builder implements Watch.ServiceBuilder {

    private final Map<Directory, WatchDirectoryJava6> directories = new MutableMap<>();

    public final Watch.Service build() {
      Collection<WatchDirectoryJava6> values;
      values = directories.values();

      ImmutableSet<WatchDirectoryJava6> set;
      set = ImmutableSet.copyOf(values);

      return new WatchServiceJava6(set);
    }

    @Override
    public final void watchDirectory(
        Directory directory, Listener listener, Event event) {
      Check.notNull(directory, "directory == null");

      if (directories.containsKey(directory)) {
        throw new IllegalArgumentException(directory.getPath() + " has already been registered");
      }

      Check.notNull(listener, "listener == null");
      Check.notNull(event, "event == null");

      EnumSet<Event> events;
      events = EnumSet.of(event);

      WatchDirectoryJava6 option;
      option = new WatchDirectoryJava6(directory, listener, events);

      option.register();

      directories.put(directory, option);
    }

  }

}