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
package br.com.objectos.core.map;

import br.com.objectos.core.array.ByteArrays;
import br.com.objectos.random.testing.Next;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import objectos.lang.ToString;

final class Thing implements ToString.Formattable {

  private final byte[] value;

  private Thing(byte[] value) {
    this.value = value;
  }

  public static Thing[] randomArray(int size) {
    Thing[] array;
    array = new Thing[size];

    for (int i = 0; i < array.length; i++) {
      array[i] = randomThing();
    }

    return array;
  }

  public static ArrayList<Thing> randomArrayList(int size) {
    ArrayList<Thing> list;
    list = new ArrayList<Thing>();

    Thing[] array;
    array = randomArray(size);

    for (Thing thing : array) {
      list.add(thing);
    }

    return list;
  }

  public static Thing randomThing() {
    byte[] value;
    value = Next.bytes(16);

    return new Thing(value);
  }

  @Override
  public final boolean equals(Object obj) {
    return obj == this || obj instanceof Thing && equals0((Thing) obj);
  }

  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.format(
      sb, depth, this,
      "value", ByteArrays.toHexString(value)
    );
  }

  @Override
  public final int hashCode() {
    return Arrays.hashCode(value);
  }

  public final String toDecimalString() {
    BigInteger bigInteger;
    bigInteger = new BigInteger(value);

    return bigInteger.toString();
  }

  public final String toHexString() {
    return ByteArrays.toHexString(value);
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

  private boolean equals0(Thing that) {
    return Arrays.equals(value, that.value);
  }

}