/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.formal.testing;

import objectos.util.ImmutableList;

public class HasDigits implements HasSomething {

  private final ImmutableList<Digit> digitList;

  public HasDigits(Digit... digits) {
    digitList = ImmutableList.copyOf(digits);
  }

  private HasDigits(ImmutableList<Digit> digitList) {
    this.digitList = digitList;
  }

  public static HasDigits hasOne(Digit l01) {
    return new HasDigits(l01);
  }

  public static HasDigits hasTwo(Digit l01, Digit l02) {
    return new HasDigits(l01, l02);
  }

  public static HasDigits of(Digit... values) {
    return new HasDigits(values);
  }

  public static HasDigits ofIterable(Iterable<Digit> many) {
    return new HasDigits(ImmutableList.copyOf(many));
  }

  public final ComplexSingle asComplexSingle() {
    return new ComplexSingle(this);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HasDigits)) {
      return false;
    }
    HasDigits that = (HasDigits) obj;
    return digitList.equals(that.digitList);
  }

  @Override
  public final String toString() {
    return "HasDigits(" + digitList.join(",") + ")";
  }

}