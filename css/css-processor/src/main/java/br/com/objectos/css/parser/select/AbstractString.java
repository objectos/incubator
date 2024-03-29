/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.parser.select;

abstract class AbstractString implements HasStringValue {

  private final String value;

  AbstractString(String value) {
    this.value = value;
  }

  AbstractString(String open, String value, String close) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof AbstractString)) {
      return false;
    }
    AbstractString that = (AbstractString) obj;
    return quote() == that.quote()
        && value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String stringValue() {
    return value;
  }

  abstract char quote();

}