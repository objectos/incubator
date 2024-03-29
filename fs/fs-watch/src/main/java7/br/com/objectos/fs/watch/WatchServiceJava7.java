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

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.watch.Watch.Event;
import br.com.objectos.fs.watch.Watch.Listener;
import br.com.objectos.fs.watch.Watch.ServiceBuilder;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.WatchKey;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import objectos.lang.Check;
import objectos.util.UnmodifiableMap;
import objectos.util.UnmodifiableSet;
import objectos.util.GrowableMap;

final class WatchServiceJava7 implements Watch.Service {

  private final Set<WatchDirectoryJava7> directories;

  private Worker worker;

  WatchServiceJava7(Set<WatchDirectoryJava7> directories) {
    this.directories = directories;
  }

  @Override
  public final synchronized void startService() throws IOException {
    FileSystem fs;
    fs = FileSystems.getDefault();

    java.nio.file.WatchService delegate;

    try {
      delegate = fs.newWatchService();
    } catch (IOException e) {
      throw e;
    }

    GrowableMap<WatchKey, WatchDirectoryJava7> keys;
    keys = new GrowableMap<>();

    for (WatchDirectoryJava7 option : directories) {
      try {
        WatchKey watchKey;
        watchKey = option.register(delegate);

        keys.put(watchKey, option);
      } catch (IOException e) {
        try {
          delegate.close();
        } catch (IOException sup) {
          e.addSuppressed(sup);
        }

        throw e;
      }
    }

    worker = new Worker(delegate, keys.toUnmodifiableMap());

    worker.start();
  }

  @Override
  public final void stopService() throws IOException {
    worker.shutdown();
  }

  static class Builder implements ServiceBuilder {

    private final GrowableMap<Directory, WatchDirectoryJava7> directories = new GrowableMap<>();

    public final Watch.Service build() {
      Collection<WatchDirectoryJava7> values;
      values = directories.values();

      UnmodifiableSet<WatchDirectoryJava7> set;
      set = UnmodifiableSet.copyOf(values);

      return new WatchServiceJava7(set);
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

      WatchDirectoryJava7 option;
      option = new WatchDirectoryJava7(directory, listener, events);

      directories.put(directory, option);
    }

  }

  private static class Worker extends Thread {

    private final java.nio.file.WatchService delegate;

    private final UnmodifiableMap<WatchKey, WatchDirectoryJava7> keys;

    Worker(java.nio.file.WatchService delegate, UnmodifiableMap<WatchKey, WatchDirectoryJava7> keys) {
      super("WatchService");

      this.delegate = delegate;
      this.keys = keys;
    }

    @Override
    public final void run() {
      while (!Thread.interrupted()) {
        try {
          WatchKey currentKey;
          currentKey = delegate.take();

          WatchDirectoryJava7 watchDirectory;
          watchDirectory = keys.get(currentKey);

          if (watchDirectory != null) {
            watchDirectory.consume(currentKey);
          }

          currentKey.reset();
        } catch (InterruptedException e) {
          return;
        }
      }
    }

    public final void shutdown() throws IOException {
      try {
        interrupt();
      } finally {
        delegate.close();
      }
    }

  }

}