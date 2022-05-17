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
package br.com.objectos.mysql;

import objectos.lang.Checks;

final class PasswordOption extends AbstractOption implements ClientOrConfigEditorOption {

  private PasswordOption(String key, String value) {
    super(key, value);
  }

  public static ClientOrConfigEditorOption password(String password) {
    Checks.checkNotNull(password, "password == null");

    return new PasswordOption("password", password);
  }

  @Override
  public final void acceptClientJob(AbstractClientJob<?> job) {
    job.setPassword(value);
  }

  @Override
  public final void acceptExecution(Execution execution) {
    execution.setPassword(value);
  }

}
