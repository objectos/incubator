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
package br.com.objectos.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.LockSupport;
import objectos.lang.Checks;
import objectos.lang.ToString;
import objectos.logging.Event0;
import objectos.logging.Event1;
import objectos.logging.Logger;

/**
 * Runs {@linkplain CpuTask CPU bound tasks} concurrently (up to a fixed number)
 * in a single thread operating off a bounded queue.
 *
 * <p>
 * Instances of this worker will only accept tasks after it has been started.
 *
 * @since 2
 */
public final class FixedCpuWorker extends CpuWorkerService {

  static final Event1<CpuTask> INTERRUPTED = Event1.info();

  static final Event1<CpuTask> JOB_COMPLETED = Event1.debug();

  static final Event1<Throwable> JOB_EXCEPTION = Event1.error();

  static final Event1<CpuTask> JOB_STARTED = Event1.debug();

  static final Event0 PARKED = Event0.debug();

  static final Event1<String> STARTED = Event1.info();

  static final Event0 UNPARKED = Event0.trace();

  private final BlockingQueue<CpuTask> queue;

  private volatile boolean shutdown;

  private final CpuTask[] tasks;

  private FixedCpuWorkerThread thread;

  /**
   * Creates a new worker instance.
   *
   * @param activeCount
   *        the maximum number of tasks that can be run concurrently
   * @param queueCapacity
   *        the bounded queue capacity
   * @param logger
   *        the logger instance to be used for logging
   */
  public FixedCpuWorker(int activeCount, int queueCapacity, Logger logger) {
    Checks.checkArgument(activeCount > 0, "activeCount must be positive");
    Checks.checkArgument(queueCapacity > 0, "queueCapacity must be positive");
    Checks.checkNotNull(logger, "logger == null");

    tasks = new CpuTask[activeCount];

    this.logger = logger;

    queue = new ArrayBlockingQueue<CpuTask>(queueCapacity);
  }

  /**
   * Offers the specified task to be executed by this worker.
   *
   * <p>
   * If this worker has not been started, the task will be rejected. Otherwise
   * the task will be offered to this worker's queue.
   *
   * @param task
   *        the task instance to offer to this worker
   *
   * @return {@code true} if the task is accepted, {@code false} otherwise
   */
  @Override
  public final boolean offer(CpuTask task) {
    Checks.checkNotNull(task, "task == null");

    if (queue.offer(task) && thread != null) {
      LockSupport.unpark(thread);

      return true;
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void startService() {
    synchronized (this) {
      ThisAdapter adapter;
      adapter = new ThisAdapter();

      thread = new FixedCpuWorkerThread(adapter);

      thread.start();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void stopService() {
    synchronized (this) {
      if (thread != null && !shutdown) {
        shutdown = true;

        thread.interrupt();
      }
    }
  }

  @Override
  public final String toString() {
    return ToString.toString(
      this,
      "queue", queue
    );
  }

  @Override
  final boolean isStarted() {
    return thread != null && thread.isAlive();
  }

  private class ThisAdapter extends FixedCpuWorkerThreadAdapter {

    @Override
    final void assertNull(int index) {
      assert tasks[index] == null;
    }

    @Override
    final CpuTask get(int index) {
      return tasks[index];
    }

    @Override
    final boolean hasSlot(int index) {
      return index < tasks.length
          && tasks[index] == null;
    }

    @Override
    final boolean hasTask() {
      return !queue.isEmpty();
    }

    @Override
    final boolean interrupted() {
      return Thread.interrupted();
    }

    @Override
    final void log(Event0 event) {
      logger.log(event);
    }

    @Override
    final <T1> void log(Event1<T1> event, T1 t1) {
      logger.log(event, t1);
    }

    @Override
    final CpuTask poll() {
      return queue.poll();
    }

    @Override
    final void set(int index, CpuTask task) {
      tasks[index] = task;
    }

    @Override
    final boolean shutdown() {
      return shutdown;
    }

    @Override
    final CpuTask take() throws InterruptedException {
      return queue.take();
    }

  }

}