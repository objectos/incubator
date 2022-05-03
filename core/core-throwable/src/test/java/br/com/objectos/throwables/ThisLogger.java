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
package br.com.objectos.throwables;

import objectos.logging.Event;
import objectos.logging.Event0;
import objectos.logging.Event1;
import objectos.logging.Event2;
import objectos.logging.Event3;
import objectos.logging.Logger;

final class ThisLogger implements Logger {

  private int count;

  public final boolean isEmpty() {
    return count == 0;
  }

  @Override
  public boolean isEnabled(Event event) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void log(Event0 event) {
    count++;
  }

  @Override
  public <T1> void log(Event1<T1> event, T1 v1) {
    count++;
  }

  @Override
  public <T1, T2> void log(Event2<T1, T2> event, T1 v1, T2 v2) {
    count++;
  }

  @Override
  public <T1, T2, T3> void log(Event3<T1, T2, T3> event, T1 v1, T2 v2, T3 v3) {
    count++;
  }

  @Override
  public Logger replace(Logger logger) {
    throw new UnsupportedOperationException();
  }

}