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

import objectos.logging.Event3;
import objectos.logging.Logger;

final class WriteJobLog3<T1, T2, T3> extends WriteJobLog {

  private final Event3<T1, T2, T3> event;

  private final T1 value1;

  private final T2 value2;

  private final T3 value3;

  WriteJobLog3(Event3<T1, T2, T3> event,
               T1 value1,
               T2 value2,
               T3 value3) {
    super(event);

    this.event = event;

    this.value1 = value1;

    this.value2 = value2;

    this.value3 = value3;
  }

  @Override
  final String formatValue(int index) {
    switch (index) {
      case 0:
        return Logging.format(value1);
      case 1:
        return Logging.format(value2);
      case 2:
        return Logging.format(value3);
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final byte getTypeV1() {
    return StorageV1.TYPE3;
  }

  @Override
  final Object getValue(int index) {
    switch (index) {
      case 0:
        return value1;
      case 1:
        return value2;
      case 2:
        return value3;
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final void replay(Logger logger) {
    logger.log(event, value1, value2, value3);
  }

  @Override
  final int size() {
    return 3;
  }

}