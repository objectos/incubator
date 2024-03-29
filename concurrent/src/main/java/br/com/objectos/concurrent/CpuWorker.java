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
package br.com.objectos.concurrent;

/**
 * Executes {@linkplain CpuTask CPU bound tasks} concurrently in a single
 * thread.
 *
 * <p>
 * Usually, the total number of of CPU workers in a program should be equal to
 * the total number of CPUs of the system the program is running on.
 *
 * @since 2
 */
public interface CpuWorker {

  /**
   * Offers the specified task to this worker, returns {@code true} if the task
   * is accepted.
   *
   * @param task
   *        the task instance to offer to this worker
   *
   * @return {@code true} if the task is accepted, {@code false} otherwise
   */
  boolean offer(CpuTask task);

}