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

final class ReadJobLog3 extends ReadJobLog {

  ReadJobThrowable throwable1;

  ReadJobThrowable throwable2;

  ReadJobThrowable throwable3;

  private String value1;

  private String value2;

  private String value3;

  @Override
  final void acceptValues(List<String> list) {
    value1 = list.get(0);

    value2 = list.get(1);

    value3 = list.get(2);
  }

  @Override
  final String formatValue(int index) {
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

  final boolean matchesValue1(String expected) {
    return value1.equals(expected);
  }

  final boolean matchesValue2(String expected) {
    return value2.equals(expected);
  }

  final boolean matchesValue3(String expected) {
    return value3.equals(expected);
  }

  @Override
  final void printStackTrace(StringBuilder out) {
    if (throwable1 != null) {
      throwable1.printStackTrace(out);
    }

    if (throwable2 != null) {
      throwable2.printStackTrace(out);
    }

    if (throwable3 != null) {
      throwable3.printStackTrace(out);
    }
  }

  @Override
  final void setThrowable(int index, ReadJobThrowable throwable) {
    switch (index) {
      case 1:
        throwable1 = throwable;
        break;
      case 2:
        throwable2 = throwable;
        break;
      case 3:
        throwable3 = throwable;
        break;
      default:
        throw illegalIndexException(index);
    }
  }

  @Override
  final int size() {
    return 3;
  }

}