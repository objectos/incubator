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
 * {@link FixedCpuWorker}
 *
 * - start empty (waiting for tasks);
 * - take single task;
 * - offer task during run cycle;
 * - complete both tasks;
 * - back to waiting.
 */
final class TestCase005 {

  public static final String DESCRIPTION = "FixedCpuWorker take single; poll single";

  public static FixedExecutionCount pollTask() {
    return new FixedExecutionCount(2);
  }

  public static FixedExecutionCount takeTask() {
    return new FixedExecutionCount(2);
  }

}