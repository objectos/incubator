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
package br.com.objectos.core.array;

import br.com.objectos.latest.Singleton;
import objectos.lang.Checks;

final class MismatchJava6 extends Mismatch {

  @Singleton.Field
  static final MismatchJava6 INSTANCE = new MismatchJava6();

  @Override
  final int mismatch(char[] a, char[] b) {
    Checks.checkNotNull(a, "a == null");
    Checks.checkNotNull(b, "b == null");

    int len;
    len = Math.min(a.length, b.length);

    for (int i = 0; i < len; i++) {
      if (a[i] != b[i]) {
        return i;
      }
    }

    if (a.length == b.length) {
      return -1;
    } else {
      return len;
    }
  }

  @Override
  final int mismatch(
      char[] a, int aFromIndex, int aToIndex,
      char[] b, int bFromIndex, int bToIndex) {
    checkIndex(a.length, aFromIndex, aToIndex);
    checkIndex(b.length, bFromIndex, bToIndex);

    int aLen;
    aLen = aToIndex - aFromIndex;

    int bLen;
    bLen = bToIndex - bFromIndex;

    int len;
    len = Math.min(aLen, bLen);

    for (int i = 0; i < len; i++) {
      if (a[aFromIndex + i] != b[bFromIndex + i]) {
        return i;
      }
    }

    if (aLen == bLen) {
      return -1;
    } else {
      return len;
    }
  }

}
