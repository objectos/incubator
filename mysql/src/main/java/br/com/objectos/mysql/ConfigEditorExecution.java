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

import java.io.IOException;
import java.io.OutputStream;
import objectos.lang.Try;

final class ConfigEditorExecution extends Execution {

  private String password;

  public final void execute() throws ExecutionException, IOException {
    Process process;

    try {
      process = start();
    } catch (IOException e) {
      throw new IOException("Failed to start config editor process", e);
    }

    if (password != null) {
      OutputStream out;
      out = process.getOutputStream();

      byte[] bytes;
      bytes = password.getBytes();

      Throwable rethrow;
      rethrow = Try.begin();

      try {
        out.write(bytes);

        out.flush();
      } catch (Throwable e) {
        rethrow = e;
      } finally {
        rethrow = Try.close(rethrow, out);
      }

      Try.rethrowIfPossible(rethrow, IOException.class);
    }

    end(process);

    throwExecutionExceptionIfNecessary();
  }

  @Override
  public final void setPassword(String value) {
    addOption("--password");

    password = value;
  }

}