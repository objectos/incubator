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
package br.com.objectos.mysql;

import objectos.lang.Check;

final class ClientOrConfigEditorOptionImpl
    extends AbstractOption
    implements ClientOrConfigEditorOption {

  private ClientOrConfigEditorOptionImpl(String key) {
    super(key);
  }

  private ClientOrConfigEditorOptionImpl(String key, String value) {
    super(key, value);
  }

  public static ClientOrConfigEditorOption user(String user) {
    Check.notNull(user, "user == null");

    return new ClientOrConfigEditorOptionImpl("user", user);
  }

}
