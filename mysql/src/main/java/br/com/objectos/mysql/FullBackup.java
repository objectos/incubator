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
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import objectos.lang.Event1;
import objectos.lang.Event2;
import objectos.lang.Logger;

final class FullBackup extends AbstractClientJob<RegularFile> {

  private static final byte _CLOSE = 0;

  private static final byte _COPY = 1;

  private static final Event1<Exception> EFAILED = Event1.error();

  private static final Event2<LoginPath, String> ESTART = Event2.info();

  private static final Event1<String> ESTART_PROCESS = Event1.debug();

  private static final Event2<Long, String> ESUCCESS = Event2.info();

  private static final byte IO_CLOSE = 0;

  private static final byte IO_COPY = 1;

  private static final byte IO_OPEN = 2;

  private final byte[] byteArray;

  private final ConfigurationFile configurationFile;

  private volatile boolean copyMore;

  private InputStream inputStream;

  private final Logger logger;

  private final LoginPath loginPath;

  private final LoginPathFile loginPathFile;

  private final ResolvedPath mysqldump;

  private OutputStream outputStream;

  private long startTime;

  private final Directory targetDirectory;

  private RegularFile targetFile;

  FullBackup(Client worker,
             LoginPath loginPath,
             Directory targetDirectory) {
    super(worker);

    byteArray = worker.getByteArray();

    configurationFile = worker.getConfigurationFile();

    logger = worker.getLogger();

    this.loginPath = loginPath;

    loginPathFile = worker.getLoginPathFile();

    mysqldump = worker.getPath(Program.MYSQLDUMP);

    this.targetDirectory = targetDirectory;
  }

  @Override
  final byte execute(byte state) {
    switch (state) {
      case _CLOSE:
        return executeClose();
      case _COPY:
        return executeCopy();
      default:
        throw new UnsupportedOperationException("Implement me. state=" + state);
    }
  }

  @Override
  final void executeIoTask(byte task) throws IOException {
    switch (task) {
      case IO_CLOSE:
        ioClose();
        break;
      case IO_COPY:
        ioCopy();
        break;
      case IO_OPEN:
        ioOpen();
        break;
      default:
        throw new UnsupportedOperationException("Implement me. task=" + task);
    }
  }

  @Override
  final byte executeStart() {
    startTime = System.currentTimeMillis();

    logger.log(ESTART, loginPath, targetDirectory.getPath());

    addCommand(mysqldump.getPath());

    addCommand(configurationFile);

    addCommand(loginPathFile);

    addCommand(loginPath);

    return toIo(IO_OPEN, _COPY, _CLOSE);
  }

  @Override
  final RegularFile getResultImpl(
      IOException exception, ImmutableList<String> stderr, ImmutableList<String> stdout)
      throws IOException {
    if (exception == null) {
      long totalTime;
      totalTime = System.currentTimeMillis() - startTime;

      String fileName;
      fileName = targetFile.getName();

      logger.log(ESUCCESS, totalTime, fileName);

      return targetFile;
    }

    else {
      logger.log(EFAILED, exception);

      throw exception;
    }
  }

  private byte executeClose() {
    return toIo(IO_CLOSE, toFinally());
  }

  private byte executeCopy() {
    if (copyMore) {
      return toIo(IO_COPY, _COPY, _CLOSE);
    }

    else {
      return executeClose();
    }
  }

  private void ioClose() throws IOException {
    closeTwo(inputStream, outputStream);
  }

  private void ioCopy() throws IOException {
    int count;
    count = inputStream.read(byteArray);

    if (count > 0) {
      outputStream.write(byteArray, 0, count);

      copyMore = true;
    } else {
      copyMore = false;
    }
  }

  private void ioOpen() throws IOException {
    String prefix;
    prefix = createBackupPrefix();

    targetFile = targetDirectory.createRegularFile(prefix + "-full.sql.gz");

    OutputStream out;
    out = targetFile.openOutputStream();

    outputStream = new GZIPOutputStream(out);

    startProcess();

    if (logger.isEnabled(ESTART_PROCESS)) {
      String command;
      command = getCommandString();

      logger.log(ESTART_PROCESS, command);
    }

    startStderrCollector();

    inputStream = getProcessInputStream();

    copyMore = false;

    ioCopy();
  }

}
