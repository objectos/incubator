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
package br.com.objectos.formal.testing.driven;

import br.com.objectos.formal.testing.Token;
import java.util.Objects;

public class StepText implements Token {

  public final String value;

  public StepText(String value) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof StepText)) {
      return false;
    }
    StepText that = (StepText) obj;
    return Objects.equals(value, that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return "StepText(" + value + ")";
  }

}