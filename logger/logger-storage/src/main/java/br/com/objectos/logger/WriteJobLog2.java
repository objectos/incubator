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

import objectos.lang.Note2;
import objectos.lang.NoteSink;

final class WriteJobLog2<T1, T2> extends WriteJobLog {

  private final Note2<T1, T2> event;

  private final T1 value1;

  private final T2 value2;

  WriteJobLog2(Note2<T1, T2> event, T1 value1, T2 value2) {
    super(event);

    this.event = event;

    this.value1 = value1;

    this.value2 = value2;
  }

  @Override
  final String formatValue(int index) {
    switch (index) {
      case 0:
        return Logging.format(value1);
      case 1:
        return Logging.format(value2);
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final byte getTypeV1() {
    return StorageV1.TYPE2;
  }

  @Override
  final Object getValue(int index) {
    switch (index) {
      case 0:
        return value1;
      case 1:
        return value2;
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final void replay(NoteSink logger) {
    logger.send(event, value1, value2);
  }

  @Override
  final int size() {
    return 2;
  }

}