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

import java.util.List;

final class ReadJobLog0 extends ReadJobLog {

  @Override
  final void acceptValues(List<String> list) {
    // noop
  }

  @Override
  final void setThrowable(int index, ReadJobThrowable throwable) {
    // noop
  }

  @Override
  final int size() {
    return 0;
  }

}
