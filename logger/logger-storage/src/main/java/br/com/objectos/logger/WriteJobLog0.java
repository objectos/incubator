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

import objectos.lang.Note0;
import objectos.lang.NoteSink;

final class WriteJobLog0 extends WriteJobLog {

  private final Note0 event;

  WriteJobLog0(Note0 event) {
    super(event);

    this.event = event;
  }

  @Override
  final byte getTypeV1() {
    return StorageV1.TYPE0;
  }

  @Override
  final void replay(NoteSink logger) {
    logger.send(event);
  }

  @Override
  final int size() {
    return 0;
  }

}