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

import objectos.lang.Note0;
import objectos.lang.Note1;

abstract class FixedCpuWorkerThreadAdapter {

  abstract void assertNull(int index);

  abstract CpuTask get(int index);

  abstract boolean hasSlot(int index);

  abstract boolean hasTask();

  abstract boolean interrupted();

  abstract void log(Note0 event);

  abstract <T1> void log(Note1<T1> event, T1 t1);

  abstract CpuTask poll();

  abstract void set(int index, CpuTask task);

  abstract boolean shutdown();

  abstract CpuTask take() throws InterruptedException;

}