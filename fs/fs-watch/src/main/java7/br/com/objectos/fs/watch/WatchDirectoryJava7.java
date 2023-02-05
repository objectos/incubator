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
import br.com.objectos.fs.PathNameVisitor;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.watch.Watch.Event;
import br.com.objectos.fs.watch.Watch.Listener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Set;

final class WatchDirectoryJava7 implements PathNameVisitor<Void, WatchEvent.Kind<?>> {

  private final Directory directory;

  private final Set<Event> events;

  private final Listener listener;

  WatchDirectoryJava7(Directory directory, Listener listener, Set<Event> events) {
    this.directory = directory;

    this.listener = listener;

    this.events = events;
  }

  @Override
  public final Void visitDirectory(Directory directory, WatchEvent.Kind<?> p) {
    if (isCreate(p)) {
      listener.onDirectoryCreated(directory);

      return null;
    }

    else if (isModify(p)) {
      listener.onDirectoryModified(directory);

      return null;
    }

    else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  @Override
  public final Void visitNotFound(ResolvedPath notFound, WatchEvent.Kind<?> p) {
    if (isDelete(p)) {
      listener.onNotFoundDeleted(notFound);

      return null;
    }

    else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  @Override
  public final Void visitRegularFile(RegularFile file, WatchEvent.Kind<?> p) {
    if (isCreate(p)) {
      listener.onRegularFileCreated(file);

      return null;
    }

    else if (isModify(p)) {
      listener.onRegularFileModified(file);

      return null;
    }

    else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  final void consume(WatchKey currentKey) {
    List<WatchEvent<?>> events;
    events = currentKey.pollEvents();

    for (int i = 0, size = events.size(); i < size; i++) {
      WatchEvent<?> watchEvent;
      watchEvent = events.get(i);

      consume0(watchEvent);
    }
  }

  final WatchKey register(WatchService service) throws IOException {
    Kind<?>[] kinds;
    kinds = FactoryJava7.getEvents(events);

    return directory.register(service, kinds);
  }

  private void consume0(WatchEvent<?> watchEvent) {
    Path context;
    context = (Path) watchEvent.context();

    ResolvedPath resolved;
    resolved = directory.resolve(context);

    try {
      resolved.acceptPathNameVisitor(this, watchEvent.kind());
    } catch (IOException notThrown) {
    }
  }

  private boolean isCreate(WatchEvent.Kind<?> kind) {
    return kind == StandardWatchEventKinds.ENTRY_CREATE;
  }

  private boolean isDelete(WatchEvent.Kind<?> kind) {
    return kind == StandardWatchEventKinds.ENTRY_DELETE;
  }

  private boolean isModify(WatchEvent.Kind<?> kind) {
    return kind == StandardWatchEventKinds.ENTRY_MODIFY;
  }

}