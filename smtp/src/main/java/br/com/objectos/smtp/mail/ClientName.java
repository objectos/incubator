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

import objectos.lang.Check;

public class ClientName {

  private final String value;

  private ClientName(String value) {
    this.value = value;
  }

  public static ClientName of(String argument) {
    Check.notNull(argument, "argument == null");

    return new ClientName(argument);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ClientName)) {
      return false;
    }
    ClientName that = (ClientName) obj;
    return value.endsWith(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

}
