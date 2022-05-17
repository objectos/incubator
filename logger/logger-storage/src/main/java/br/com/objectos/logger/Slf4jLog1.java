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

import objectos.lang.ToString;
import objectos.logging.Level;

final class Slf4jLog1 extends WriteJobLog {

  private final String message;

  Slf4jLog1(Level level, String name, String message) {
    super("SLF4J", level, name);

    this.message = message;
  }

  @Override
  public final String toString() {
    return ToString.toString(
        this,
        "", key,
        "", level,
        "", source,
        "", message
    );
  }

  @Override
  final String formatValue(int index) {
    switch (index) {
      case 0:
        return message;
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final byte getTypeV1() {
    return StorageV1.TYPE1;
  }

  @Override
  final Object getValue(int index) {
    switch (index) {
      case 0:
        return message;
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final int size() {
    return 1;
  }

}