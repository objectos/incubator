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

import objectos.lang.Note1;
import objectos.lang.NoteSink;

final class WriteJobLog1<T1> extends WriteJobLog {

  private final Note1<T1> event;

  private final T1 value;

  WriteJobLog1(Note1<T1> event, T1 value) {
    super(event);

    this.event = event;

    this.value = value;
  }

  @Override
  final String formatValue(int index) {
    if (index != 0) {
      throw illegalIndexException(index);
    }

    return Logging.format(value);
  }

  @Override
  final byte getTypeV1() {
    return StorageV1.TYPE1;
  }

  @Override
  final Object getValue(int index) {
    if (index != 0) {
      throw illegalIndexException(index);
    }

    return value;
  }

  @Override
  final void replay(NoteSink logger) {
    logger.send(event, value);
  }

  @Override
  final int size() {
    return 1;
  }

}