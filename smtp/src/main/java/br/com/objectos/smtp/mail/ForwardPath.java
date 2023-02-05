/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.smtp.mail;

public class ForwardPath {

  private final String value;

  public ForwardPath(String value) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof ForwardPath)) {
      return false;
    }

    ForwardPath that;
    that = (ForwardPath) obj;

    return value.equals(that.value);
  }

  public final String getLocalPart() {
    int index;
    index = value.indexOf('@');

    return value.substring(0, index);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

}
