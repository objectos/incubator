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

import br.com.objectos.concurrent.CpuWorker;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.watch.SimpleWatchListener;
import br.com.objectos.fs.watch.Watch;
import java.io.Closeable;
import java.io.IOException;
import objectos.lang.Check;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

/**
 * Reads log events from a storage by watching it for changes.
 *
 * @since 2
 */
public final class StorageWatcher implements Closeable, Watch.Option {

  private final ThisListener listener = new ThisListener();

  private final ReadJob readJob;

  private final Storage storage;

  private final CpuWorker worker;

  StorageWatcher(ReadJob readJob, Storage storage, CpuWorker worker) {
    this.readJob = readJob;

    this.storage = storage;

    this.worker = worker;
  }

  /**
   * Creates and returns a new instance of this watcher.
   *
   * @param directory
   *        the directory where the logging events are being store. The
   *        directory must already contain a logger formatted directory.
   * @param ioWorker
   *        the worker for I/O tasks
   * @param cpuWorker
   *        the worker for CPU bound tasks
   * @param options
   *        additional configuration options
   *
   * @return a new instance of this watcher
   *
   * @throws IOException
   *         if the directory is not empty and is not a valid storage directory,
   *         or if another I/O error occurs
   */
  public static StorageWatcher create(
      Directory directory, IoWorker ioWorker, CpuWorker cpuWorker,
      Option... options)
      throws IOException {
    Check.notNull(directory, "directory == null");
    Check.notNull(ioWorker, "ioWorker == null");
    Check.notNull(cpuWorker, "cpuWorker == null");
    Check.notNull(options, "options == null");

    Storage storage;
    storage = Storage.get(directory);

    StorageWatcher.Builder b;
    b = new StorageWatcher.Builder(ioWorker, storage, cpuWorker);

    for (int i = 0, length = options.length; i < length; i++) {
      Option option;
      option = options[i];

      Check.notNull(option, "options[", i, "] == null");

      option.acceptBuilder(b);
    }

    return b.build();
  }

  /**
   * Adds the specified log listener to a storage watcher.
   *
   * @param listener
   *        the listener instance to add
   *
   * @return a new storage watcher option that adds the specified log listener
   */
  public static Option logListener(final LogListener listener) {
    Check.notNull(listener, "listener == null");

    return new Option() {
      @Override
      final void acceptBuilder(Builder builder) {
        builder.addLogListener(listener);
      }
    };
  }

  /**
   * Adds this storage watcher to the specified watch service.
   *
   * @param builder
   *        the watch service builder
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  @Override
  public final void acceptWatchServiceBuilder(Watch.ServiceBuilder builder) throws IOException {
    storage.watchDirectory(builder, listener);
  }

  /**
   * Releases all resources used by this watcher.
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  @Override
  public final void close() throws IOException {
    readJob.close();
  }

  /**
   * Reads from the storage as if it was changed.
   */
  public final void touch() {
    listener.execute();
  }

  /**
   * Represents a configuration option for a storage watcher.
   *
   * @since 2
   */
  public static abstract class Option {

    Option() {}

    abstract void acceptBuilder(Builder builder);

  }

  static final class Builder {

    private final IoWorker ioWorker;

    private final MutableList<LogListener> listeners = new MutableList<>();

    private final Storage storage;

    private final CpuWorker worker;

    Builder(IoWorker ioWorker, Storage storage, CpuWorker worker) {
      this.ioWorker = ioWorker;
      this.storage = storage;
      this.worker = worker;
    }

    final void addLogListener(LogListener listener) {
      listeners.add(listener);
    }

    final StorageWatcher build() throws IOException {
      ImmutableList<LogListener> list;
      list = listeners.toImmutableList();

      ReadJob readJob;
      readJob = storage.createReadJob(ioWorker, list);

      return new StorageWatcher(readJob, storage, worker);
    }

  }

  private class ThisListener extends SimpleWatchListener {

    @Override
    public final void onRegularFileModified(RegularFile file) {
      String name;
      name = file.getName();

      if (!name.equals(StorageV1.LOG_NAME)) {
        return;
      }

      execute();
    }

    private void execute() {
      if (readJob.isActive() || readJob.isFailed()) {
        return;
      }

      synchronized (readJob) {
        if (!readJob.isActive() && !readJob.isFailed()) {
          readJob.reset();

          worker.offer(readJob);
        }
      }
    }

  }

}