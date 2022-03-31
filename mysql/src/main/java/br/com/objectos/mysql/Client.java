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

import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.logging.Logger;
import java.nio.charset.Charset;

public final class Client {

  private byte[] byteArray;

  private final ConfigurationFile configurationFile;

  private final Directory directory;

  private final IoWorker ioWorker;

  private AbstractClientJob<?> job;

  private final Logger logger;

  private final LoginPathFile loginPathFile;

  private Client(Directory directory,
                 ConfigurationFile configurationFile,
                 LoginPathFile loginPathFile,
                 IoWorker ioWorker,
                 Logger logger) {
    this.directory = directory;
    this.configurationFile = configurationFile;
    this.loginPathFile = loginPathFile;
    this.ioWorker = ioWorker;
    this.logger = logger;
  }

  public static Client createClient(
      Directory directory,
      ConfigurationFile configurationFile, LoginPathFile loginPathFile,
      IoWorker ioWorker, Logger logger) {
    Checks.checkNotNull(directory, "directory == null");
    Checks.checkNotNull(configurationFile, "configurationFile == null");
    Checks.checkNotNull(loginPathFile, "loginPathFile == null");
    Checks.checkNotNull(ioWorker, "ioWorker == null");
    Checks.checkNotNull(logger, "logger == null");

    return new Client(
        directory,
        configurationFile, loginPathFile,
        ioWorker, logger
    );
  }

  public static String sql(String... parts) {
    Checks.checkNotNull(parts, "parts == null");

    StringBuilder sql;
    sql = new StringBuilder();

    for (int i = 0; i < parts.length; i++) {
      String part;
      part = parts[i];

      if (part == null) {
        throw new NullPointerException("parts[" + i + "] == null");
      }

      sql.append(part);
    }

    return sql.toString();
  }

  public static String tuple(Object... values) {
    Checks.checkNotNull(values, "values == null");

    StringBuilder result;
    result = new StringBuilder();

    result.append('(');

    if (values.length > 0) {
      tuple0(result, values, 0);
    }

    for (int i = 1; i < values.length; i++) {
      result.append(',');

      tuple0(result, values, i);
    }

    result.append(')');

    return result.toString();
  }

  private static void tuple0(StringBuilder out, Object[] values, int index) {
    Object value;
    value = values[index];

    if (value == null) {
      throw new NullPointerException("values[" + index + "] == null");
    }

    String s;
    s = value.toString();

    if (s == null) {
      throw new NullPointerException("values[" + index + "].toString() == null");
    }

    out.append('\'');

    out.append(s);

    out.append('\'');
  }

  public final ClientJob<ImmutableList<String>> executeInputStreamSource(
      LoginPath loginPath, InputStreamSource source) {
    Checks.checkNotNull(loginPath, "loginPath == null");
    Checks.checkNotNull(source, "source == null");

    return new ExecuteInputStreamSource(this, loginPath, source);
  }

  public final ClientJob<ImmutableList<String>> executeStatement(
      LoginPath loginPath, String... statements) {
    Checks.checkNotNull(loginPath, "loginPath == null");
    Checks.checkNotNull(statements, "statements == null");

    return new ExecuteStatement(this, loginPath, statements);
  }

  public final ClientJob<RegularFile> fullBackup(
      LoginPath loginPath, Directory targetDirectory) {
    Checks.checkNotNull(loginPath, "loginPath == null");
    Checks.checkNotNull(targetDirectory, "targetDirectory == null");

    return new FullBackup(this, loginPath, targetDirectory);
  }

  public final ClientJob<ImmutableList<String>> fullRestore(
      LoginPath loginPath, RegularFile file) {
    Checks.checkNotNull(loginPath, "loginPath == null");
    Checks.checkNotNull(file, "file == null");

    return new FullRestore(this, loginPath, file);
  }

  public final ClientJob<ImmutableList<RegularFile>> incrementalBackup(
      LoginPath loginPath, Directory targetDirectory) {
    Checks.checkNotNull(loginPath, "loginPath == null");
    Checks.checkNotNull(targetDirectory, "targetDirectory == null");

    return new IncrementalBackup(this, loginPath, targetDirectory);
  }

  public final ClientJob<ImmutableList<String>> incrementalRestore(
      LoginPath loginPath, Directory workDirectory, ImmutableList<RegularFile> files) {
    Checks.checkNotNull(loginPath, "loginPath == null");
    Checks.checkNotNull(workDirectory, "workDirectory == null");
    Checks.checkNotNull(files, "files == null");

    return new IncrementalRestore(this, loginPath, workDirectory, files);
  }

  public final ClientJob<ImmutableList<String>> setLoginPath(
      ConfigEditorOption... options) {
    Checks.checkNotNull(options, "options == null");

    return new SetLoginPath(this, options);
  }

  final byte[] getByteArray() {
    if (byteArray == null) {
      byteArray = new byte[8192];
    }

    return byteArray;
  }

  final Charset getCharset() {
    return configurationFile.charset;
  }

  final ConfigurationFile getConfigurationFile() {
    return configurationFile;
  }

  final IoWorker getIoExecutor() {
    return ioWorker;
  }

  final Logger getLogger() {
    return logger;
  }

  final LoginPathFile getLoginPathFile() {
    return loginPathFile;
  }

  final ResolvedPath getPath(Program program) {
    return program.resolve(directory);
  }

  final boolean isSet() {
    return job != null;
  }

  final void set(AbstractClientJob<?> newJob) {
    Checks.checkState(job == null, "A previous job is already running");

    job = newJob;
  }

  final void unset(AbstractClientJob<?> clientJob) {
    if (job == clientJob) {
      job = null;
    }
  }

}