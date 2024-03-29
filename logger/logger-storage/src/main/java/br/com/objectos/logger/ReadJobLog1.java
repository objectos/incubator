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

import java.util.List;

final class ReadJobLog1 extends ReadJobLog {

  ReadJobThrowable throwable;

  private String value;

  @Override
  final void acceptValues(List<String> list) {
    value = list.get(0);
  }

  @Override
  final String formatValue(int index) {
    if (index != 0) {
      throw illegalIndexException(index);
    }

    return value;
  }

  final boolean matchesValue(String expected) {
    return value.equals(expected);
  }

  @Override
  final void printStackTrace(StringBuilder out) {
    if (throwable != null) {
      throwable.printStackTrace(out);
    }
  }

  @Override
  final void setThrowable(int index, ReadJobThrowable throwable) {
    this.throwable = throwable;
  }

  @Override
  final int size() {
    return 1;
  }

}