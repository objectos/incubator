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

import br.com.objectos.logging.Event0;
import br.com.objectos.logging.Logger;

final class WriteJobLog0 extends WriteJobLog {

  private final Event0 event;

  WriteJobLog0(Event0 event) {
    super(event);

    this.event = event;
  }

  @Override
  final byte getTypeV1() {
    return StorageV1.TYPE0;
  }

  @Override
  final void replay(Logger logger) {
    logger.log(event);
  }

  @Override
  final int size() {
    return 0;
  }

}