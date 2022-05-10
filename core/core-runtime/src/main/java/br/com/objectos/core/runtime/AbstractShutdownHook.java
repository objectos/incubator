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
package br.com.objectos.core.runtime;

import br.com.objectos.core.object.Checks;
import br.com.objectos.latest.Concrete;
import java.io.Closeable;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import objectos.logging.Event0;
import objectos.logging.Event1;
import objectos.logging.Logger;
import objectos.logging.NoOpLogger;

/**
 * Provides a facility for running operations in a {@link Runtime} shutdown
 * hook.
 *
 * <p>
 * This class cannot be instantiated directly. Instead, it provides a number of
 * {@code static} methods for registering objects. For each object type it
 * accepts, it executes a specific operation when the {@link Runtime} shutdown
 * hook runs. To learn which method this facility invokes, see the specific
 * {@code register} method documentation.
 *
 * @since 2
 */
@Concrete(modifiers = "public final", simpleName = "ShutdownHook")
abstract class AbstractShutdownHook {

  private static final Event1<Throwable> CAUGHT_EXCEPTION = Event1.warn();

  private static final Event1<Long> FINISHED = Event1.info();

  private static final Event0 STARTED = Event0.info();

  private Job job;

  private Logger logger = NoOpLogger.getInstance();

  private List<ShutdownHookTask> tasks;

  AbstractShutdownHook() {}

  /**
   * Registers the specified {@link ShutdownHookTask} instance to the
   * facility provided by this class.
   *
   * <p>
   * During the JVM shutdown process, the facility will invoke the
   * {@link ShutdownHookTask#executeShutdownHookTask()} method on supplied
   * instance.
   *
   * @param task
   *        a {@link ShutdownHookTask} instance to be registered
   */
  public static void register(ShutdownHookTask task) {
    ShutdownHook hook;
    hook = getHook();

    hook.addShutdownHookTask(task);
  }

  /**
   * Sets the logger instance to be used by the shutdown hook.
   *
   * @param logger
   *        the logger instance to be used by the shutdown hook
   */
  public static void setLogger(Logger logger) {
    ShutdownHook hook;
    hook = getHook();

    hook.setLoggerImpl(logger);
  }

  static ShutdownHook getHook() {
    return ShutdownHookLazy.INSTANCE;
  }

  abstract void addCloseable(Closeable closeable);

  final void addShutdownHookTask(ShutdownHookTask task) {
    Checks.checkNotNull(task, "task == null");

    if (tasks == null) {
      synchronized (this) {
        if (tasks == null) {
          tasks = new ArrayList<ShutdownHookTask>();
        }
      }
    }

    synchronized (tasks) {
      tasks.add(task);
    }
  }

  final void log(Throwable e) {
    logger.log(CAUGHT_EXCEPTION, e);
  }

  final void register() {
    job = new Job();

    Runtime runtime;
    runtime = Runtime.getRuntime();

    runtime.addShutdownHook(job);
  }

  abstract void run0();

  final void setLoggerImpl(Logger logger) {
    Checks.checkNotNull(logger, "logger == null");

    synchronized (this) {
      State state;
      state = job.getState();

      if (state == State.NEW) {
        this.logger = logger;
      }
    }
  }

  final Thread startAndJoinThread() throws InterruptedException {
    job.start();

    job.join();

    return job;
  }

  private class Job extends Thread {
    Job() {
      super("Shutdown");
    }

    @Override
    public final void run() {
      long startTime;
      startTime = System.currentTimeMillis();

      logger.log(STARTED);

      if (tasks != null) {
        doTasks();
      }

      run0();

      long totalTime;
      totalTime = System.currentTimeMillis() - startTime;

      logger.log(FINISHED, totalTime);
    }

    private void doTasks() {
      for (int i = tasks.size() - 1; i >= 0; i--) {
        ShutdownHookTask l;
        l = tasks.get(i);

        try {
          l.executeShutdownHookTask();
        } catch (Exception e) {
          log(e);
        }
      }
    }
  }

  private static class ShutdownHookLazy {
    static ShutdownHook INSTANCE = create();

    private static ShutdownHook create() {
      ShutdownHook shutdownHook;
      shutdownHook = new ShutdownHook();

      shutdownHook.register();

      return shutdownHook;
    }
  }

}