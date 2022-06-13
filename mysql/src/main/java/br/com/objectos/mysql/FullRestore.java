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

import br.com.objectos.fs.RegularFile;
import java.io.IOException;
import objectos.lang.Note1;
import objectos.lang.Note2;
import objectos.lang.NoteSink;
import objectos.util.ImmutableList;

final class FullRestore extends AbstractClientJob<ImmutableList<String>> {

  private static final byte _FLUSH_PRIVILEGES = 0;

  private static final Note1<Exception> EFAILED = Note1.error();

  private static final Note1<Long> EFINISH = Note1.info();

  private static final Note1<String> EINVALID_FULLNAME = Note1.info();

  private static final Note2<LoginPath, String> ESTART = Note2.info();

  private static final String[] FLUSH_PRIVILEGES_INPUT = new String[] {"FLUSH PRIVILEGES;"};

  private final RegularFile file;

  private final NoteSink logger;

  private final LoginPath loginPath;

  private long startTime;

  FullRestore(Client worker,
              LoginPath loginPath,
              RegularFile file) {
    super(worker);

    logger = worker.getLogger();

    this.loginPath = loginPath;

    this.file = file;
  }

  @Override
  final byte execute(byte state) {
    switch (state) {
      case _FLUSH_PRIVILEGES:
        return executeFlushPrivileges();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
    }
  }

  @Override
  final void executeIoTask(byte task) throws IOException {
    throw new UnsupportedOperationException("Implement me: task=" + task);
  }

  @Override
  final byte executeStart() {
    startTime = System.currentTimeMillis();

    String fileName;
    fileName = file.getName();

    logger.send(ESTART, loginPath, fileName);

    if (!fileName.startsWith("backup-")) {
      logger.send(EINVALID_FULLNAME, fileName);

      return toFinally();
    }

    if (!fileName.endsWith("full.sql.gz")) {
      logger.send(EINVALID_FULLNAME, fileName);

      return toFinally();
    }

    GzipInputStreamSource gzip;
    gzip = new GzipInputStreamSource(file);

    return toSubTask(
      new ExecuteInputStreamSource(client, loginPath, gzip, Mysql.skipColumnNames()),

      _FLUSH_PRIVILEGES
    );
  }

  @Override
  final ImmutableList<String> getResultImpl(
      IOException exception, ImmutableList<String> stderr, ImmutableList<String> stdout)
      throws IOException {
    if (exception == null) {
      long totalTime;
      totalTime = System.currentTimeMillis() - startTime;

      logger.send(EFINISH, totalTime);

      return stdout;
    }

    else {
      logger.send(EFAILED, exception);

      throw exception;
    }
  }

  private byte executeFlushPrivileges() {
    return toSubTask(
      new ExecuteStatement(
        client, loginPath, FLUSH_PRIVILEGES_INPUT, Mysql.skipColumnNames()
      ),

      toFinally()
    );
  }

}
