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

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import objectos.lang.Try;

abstract class Execution {

  private final ProcessBuilder builder;

  private final List<String> command;

  private final Map<String, String> environment;

  private final MutableList<String> error = MutableList.create();

  private int exitValue = -1;

  private final MutableList<String> output = MutableList.create();

  Execution() {
    builder = new ProcessBuilder();

    command = builder.command();

    environment = builder.environment();
  }

  public final void addOption(String option) {
    command.add(option);
  }

  public final void putEnvironment(String key, String value) {
    environment.put(key, value);
  }

  public void setPassword(String value) {
    throw new UnsupportedOperationException();
  }

  final void end(Process process) throws IOException {
    InputStream in;
    in = process.getInputStream();

    consume(in, output);

    InputStream err;
    err = process.getErrorStream();

    consume(err, error);

    try {
      exitValue = process.waitFor();
    } catch (InterruptedException e) {
      exitValue = -1;
      Thread.interrupted();
      throw new IOException("Got interrupted while waiting for process to finish", e);
    }
  }

  final Process start() throws IOException {
    return builder.start();
  }

  final ImmutableList<String> sysout() throws ExecutionException {
    ImmutableList<String> sysout;
    sysout = output.toImmutableList();

    if (exitValue == 0) {
      return sysout;
    }

    ImmutableList<String> syserr;
    syserr = error.toImmutableList();

    throw new ExecutionException(exitValue, syserr, sysout);
  }

  final void throwExecutionExceptionIfNecessary() throws ExecutionException {
    if (exitValue != 0) {
      ImmutableList<String> sysout;
      sysout = output.toImmutableList();

      ImmutableList<String> syserr;
      syserr = error.toImmutableList();

      throw new ExecutionException(exitValue, syserr, sysout);
    }
  }

  private void consume(InputStream in, MutableList<String> result) throws IOException {
    InputStreamReader streamReader;
    streamReader = new InputStreamReader(in);

    BufferedReader reader;
    reader = new BufferedReader(streamReader);

    Throwable rethrow;
    rethrow = Try.begin();

    try {
      String line;
      line = reader.readLine();

      while (line != null) {
        result.add(line);

        line = reader.readLine();
      }
    } catch (Throwable e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, reader);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

}