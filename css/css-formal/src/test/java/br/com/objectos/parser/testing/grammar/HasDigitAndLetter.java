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
package br.com.objectos.parser.testing.grammar;

import br.com.objectos.formal.testing.Digit;
import br.com.objectos.formal.testing.HasSomething;
import br.com.objectos.formal.testing.Letter;
import java.util.Objects;

public class HasDigitAndLetter implements HasSomething {

  private final Digit digit;
  private final Letter letter;

  public HasDigitAndLetter(Digit digit, Letter letter) {
    this.digit = digit;
    this.letter = letter;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HasDigitAndLetter)) {
      return false;
    }
    HasDigitAndLetter that = (HasDigitAndLetter) obj;
    return digit.equals(that.digit)
        && letter.equals(that.letter);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(digit, letter);
  }

  @Override
  public final String toString() {
    return digit.toString() + letter.toString();
  }

}