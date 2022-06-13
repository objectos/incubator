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

public class HasLetters implements HasSomething {

  private final ImmutableList<Letter> letterList;

  public HasLetters(Letter... letters) {
    letterList = ImmutableList.copyOf(letters);
  }

  private HasLetters(ImmutableList<Letter> letterList) {
    this.letterList = letterList;
  }

  public static HasLetters hasOne(Letter l01) {
    return new HasLetters(l01);
  }

  public static HasLetters hasTwo(Letter l01, Letter l02) {
    return new HasLetters(l01, l02);
  }

  public static HasLetters of(Letter... values) {
    return new HasLetters(values);
  }

  public static HasLetters ofIterable(Iterable<Letter> many) {
    return new HasLetters(ImmutableList.copyOf(many));
  }

  public final ComplexSingle asComplexSingle() {
    return new ComplexSingle(this);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HasLetters)) {
      return false;
    }
    HasLetters that = (HasLetters) obj;
    return letterList.equals(that.letterList);
  }

  @Override
  public final String toString() {
    return "HasLetters(" + letterList.join(",") + ")";
  }

}