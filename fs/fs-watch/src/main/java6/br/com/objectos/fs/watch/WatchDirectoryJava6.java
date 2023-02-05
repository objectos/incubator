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
import br.com.objectos.fs.DirectoryContentsVisitor;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.watch.Watch.Event;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import objectos.util.GrowableMap;

final class WatchDirectoryJava6 implements DirectoryContentsVisitor {

  private final Checker checker = new Checker();

  private final Map<String, Directory> directories = new GrowableMap<>();

  private final Directory directory;

  private final Set<Event> events;

  private final Map<String, RegularFileWrapper> files = new GrowableMap<>();

  private final Watch.Listener listener;

  public WatchDirectoryJava6(Directory directory,
                             Watch.Listener listener,
                             Set<Event> events) {
    this.directory = directory;

    this.listener = listener;

    this.events = events;
  }

  @Override
  public final void visitDirectory(Directory child) throws IOException {
    String key;
    key = child.getName();

    directories.put(key, child);
  }

  @Override
  public final void visitRegularFile(RegularFile file) throws IOException {
    String key;
    key = file.getName();

    RegularFileWrapper wrapper;
    wrapper = new RegularFileWrapper(file);

    files.put(key, wrapper);
  }

  final void checkForEvents() {
    try {
      checker.clear();

      directory.visitContents(checker);

      checker.doDeleteEvents();
    } catch (IOException e) {
      // this visitor does not throw IOException
    }
  }

  final void register() {
    try {
      directory.visitContents(this);
    } catch (IOException e) {
      // this visitor does not throw IOException
    }
  }

  private class Checker implements DirectoryContentsVisitor {

    @Override
    public final void visitDirectory(Directory child) throws IOException {
      String key;
      key = child.getName();

      if (directories.containsKey(key)) {
        return;
      }

      directories.put(key, child);

      if (isCreatedEnabled()) {
        listener.onDirectoryCreated(child);
      }
    }

    @Override
    public final void visitRegularFile(RegularFile file) throws IOException {
      String key;
      key = file.getName();

      RegularFileWrapper thisFile;
      thisFile = files.get(key);

      if (thisFile == null) {
        RegularFileWrapper wrapper;
        wrapper = new RegularFileWrapper(file);

        files.put(key, wrapper);

        if (isCreatedEnabled()) {
          listener.onRegularFileCreated(file);
        }
      }

      else if (isModifiedEnabled() && thisFile.modified(file)) {
        listener.onRegularFileModified(file);
      }
    }

    final void clear() {}

    final void doDeleteEvents() {
      if (events.contains(Event.DELETED)) {
        throw new UnsupportedOperationException("Implement me");
      }
    }

    private boolean isCreatedEnabled() {
      return events.contains(Event.CREATED);
    }

    private boolean isModifiedEnabled() {
      return events.contains(Event.MODIFIED);
    }

  }

}
