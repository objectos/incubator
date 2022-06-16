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

import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.PathNameVisitor;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.watch.Watch;
import java.io.IOException;
import objectos.lang.Check;
import objectos.util.UnmodifiableList;

abstract class Storage {

  private static final ThisVisitor VISITOR = new ThisVisitor();

  Storage() {}

  public static Storage get(Directory directory) throws IOException {
    Check.notNull(directory, "directory == null");

    if (directory.isEmpty()) {
      return StorageV1.create(directory);
    }

    ResolvedPath maybeVersionDirectory;
    maybeVersionDirectory = directory.resolve("version");

    return maybeVersionDirectory.acceptPathNameVisitor(VISITOR, directory);
  }

  public abstract ReadJob createReadJob(
      IoWorker ioWorker, UnmodifiableList<LogListener> listeners) throws IOException;

  public abstract WriteJob createWriteJob(
      IoWorker ioWorker, UnmodifiableList<LogListener> listeners) throws IOException;

  public abstract void watchDirectory(Watch.ServiceBuilder builder, Watch.Listener listener)
      throws IOException;

  private static class ThisVisitor implements PathNameVisitor<Storage, Directory> {
    @Override
    public final Storage visitDirectory(Directory directory, Directory p) throws IOException {
      Storage storage;
      storage = StorageV1.openIfPossible(p, directory);

      if (storage != null) {
        return storage;
      }

      throw new IOException("Invalid or corrupt storage directory");
    }

    @Override
    public final Storage visitNotFound(ResolvedPath notFound, Directory p) throws IOException {
      throw new IOException("Invalid or corrupt storage directory");
    }

    @Override
    public Storage visitRegularFile(RegularFile file, Directory p) throws IOException {
      throw new IOException("Invalid or corrupt storage directory");
    }
  }

}