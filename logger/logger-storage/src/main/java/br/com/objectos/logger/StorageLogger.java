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
package br.com.objectos.logger;

import br.com.objectos.concurrent.CpuWorker;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.fs.Directory;
import java.io.IOException;
import objectos.lang.Check;
import objectos.lang.Level;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

/**
 * A logger that writes log events to a file system storage.
 *
 * <p>
 * Instances of this class are thread-safe. However, writing to the same storage
 * from multiple logger instances is <em>not</em> supported. Therefore, it is
 * recommended that a single instance of this class to be used throughout an
 * application.
 *
 * @since 2
 */
public final class StorageLogger extends AbstractConfigurableLogger {

  private final CpuWorker worker;

  private final WriteJob writeJob;

  StorageLogger(CpuWorker worker,
                WriteJob writeJob) {
    this.worker = worker;

    this.writeJob = writeJob;
  }

  /**
   * Creates and returns a new instance of this logger.
   *
   * @param directory
   *        the directory where to store the logging events. The directory must
   *        be empty or it must already contain a logger formatted directory.
   * @param ioWorker
   *        the worker for I/O tasks
   * @param cpuWorker
   *        the worker for CPU bound tasks
   * @param options
   *        additional configuration options
   *
   * @return a new instance of this logger
   *
   * @throws IOException
   *         if the directory is not empty and is not a valid storage directory,
   *         or if another I/O error occurs
   */
  public static StorageLogger create(
      Directory directory, IoWorker ioWorker, CpuWorker cpuWorker,
      Option... options)
      throws IOException {
    Check.notNull(directory, "directory == null");
    Check.notNull(ioWorker, "ioWorker == null");
    Check.notNull(cpuWorker, "cpuWorker == null");
    Check.notNull(options, "options == null");

    Storage storage;
    storage = Storage.get(directory);

    StorageLogger.Builder b;
    b = new StorageLogger.Builder(storage, ioWorker, cpuWorker);

    for (int i = 0, length = options.length; i < length; i++) {
      Option option;
      option = options[i];

      Check.notNull(option, "options[", i, "] == null");

      option.acceptBuilder(b);
    }

    return b.build();
  }

  /**
   * Adds the specified log listener to a storage logger.
   *
   * @param listener
   *        the listener instance to add
   *
   * @return a new storage logger option that adds the specified log listener
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
   * Releases all resources used by this logger.
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  @Override
  public final void close() throws IOException {
    writeJob.close();
  }

  /**
   * Allows for interop with SLF4J: logs an event with the specified values.
   *
   * @param name
   *        the logger name
   * @param level
   *        the log event level
   * @param message
   *        a log message
   */
  public final void slf4j(String name, Level level, String message) {
    write(
        new Slf4jLog1(level, name, message)
    );
  }

  /**
   * Allows for interop with SLF4J: logs an event with the specified values.
   *
   * @param name
   *        the logger name
   * @param level
   *        the log event level
   * @param message
   *        a log message
   * @param t
   *        a throwable object
   */
  public final void slf4j(String name, Level level, String message, Throwable t) {
    write(
        new Slf4jLog2(level, name, message, t)
    );
  }

  @Override
  final void write(WriteJobLog log) {
    writeJob.offer(log);

    if (!writeJob.isActive()) {
      writeJob.start();

      worker.offer(writeJob);
    }
  }

  /**
   * Represents a configuration option for a storage logger.
   *
   * @since 2
   */
  public static abstract class Option {

    Option() {}

    abstract void acceptBuilder(Builder builder);

  }

  static final class Builder {

    private final IoWorker ioWorker;

    private final GrowableList<LogListener> listeners = new GrowableList<>();

    private final Storage storage;

    private final CpuWorker worker;

    Builder(Storage storage, IoWorker ioWorker, CpuWorker worker) {
      this.storage = storage;

      this.ioWorker = ioWorker;

      this.worker = worker;
    }

    final void addLogListener(LogListener value) {
      listeners.add(value);
    }

    final StorageLogger build() throws IOException {
      UnmodifiableList<LogListener> list;
      list = listeners.toUnmodifiableList();

      WriteJob writeJob;
      writeJob = storage.createWriteJob(ioWorker, list);

      return new StorageLogger(
          worker,

          writeJob
      );
    }

  }

}