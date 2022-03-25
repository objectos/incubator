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

import br.com.objectos.core.object.ToString;
import br.com.objectos.logging.Level;

final class Slf4jLog2 extends WriteJobLog {

  private final String message;

  private final Throwable throwable;

  Slf4jLog2(Level level, String name, String message, Throwable throwable) {
    super("SLF4J", level, name);

    this.message = message;

    this.throwable = throwable;
  }

  @Override
  public final String toString() {
    return ToString.toString(
        this,
        "", key,
        "", level,
        "", source,
        "", message,
        "", throwable
    );
  }

  @Override
  final String formatValue(int index) {
    switch (index) {
      case 0:
        return message;
      case 1:
        return throwable.toString();
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
        return message;
      case 1:
        return throwable;
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final int size() {
    return 2;
  }

}