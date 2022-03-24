/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import javax.lang.model.element.TypeElement;

final class LatestEntry implements Comparable<LatestEntry> {

  final TypeElement element;

  final String name;

  private final int ordinal;

  LatestEntry(String name, TypeElement element) {
    this.name = name;

    this.element = element;

    this.ordinal = getOrdinal();
  }

  @Override
  public final int compareTo(LatestEntry o) {
    int thisValue;
    thisValue = ordinal;

    int thatValue;
    thatValue = o.ordinal;

    if (thisValue < thatValue) {
      return -1;
    }

    if (thisValue == thatValue) {
      return 0;
    }

    return 1;
  }

  @Override
  public final String toString() {
    return "LatestEntry(" + name + ")";
  }

  private int getOrdinal() {
    if (name.isEmpty()) {
      return Integer.MIN_VALUE;
    }

    StringBuilder version;
    version = new StringBuilder();

    char[] array;
    array = name.toCharArray();

    int length;
    length = array.length;

    for (int i = length - 1; i >= 0; i--) {
      char last;
      last = array[i];

      if (!Character.isDigit(last)) {
        break;
      }

      version.append(last);
    }

    String ordinal;
    ordinal = version.toString();

    if (ordinal.isEmpty()) {
      return Integer.MIN_VALUE;
    } else {
      return Integer.parseInt(ordinal);
    }
  }

}