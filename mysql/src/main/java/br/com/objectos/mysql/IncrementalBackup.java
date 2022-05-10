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
import br.com.objectos.core.throwable.Try;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import objectos.logging.Event1;
import objectos.logging.Event2;
import objectos.logging.Logger;

final class IncrementalBackup extends AbstractClientJob<ImmutableList<RegularFile>> {

  private static final byte _COPY = 0;

  private static final byte _FIRST_LOG = 1;

  private static final byte _NEXT_LOG = 2;

  private static final byte _PURGE = 3;

  private static final Event1<String> EBINARY_LOG = Event1.debug();

  private static final Event1<Exception> EFAILED = Event1.error();

  private static final Event1<String> ELOGBIN_BASENAME = Event1.debug();

  private static final Event2<LoginPath, String> ESTART = Event2.info();

  private static final Event1<Long> ESUCCESS = Event1.info();

  private static final byte IO_CLOSE = 0;

  private static final byte IO_COPY = 1;

  private static final byte IO_OPEN = 2;

  private String basename;

  private ImmutableList<String> binaryLogs;

  private int binaryLogsIndex;

  private String binlogPathName;

  private String binlogSimpleName;

  private final byte[] byteArray;

  private volatile boolean copyMore;

  private InputStream inputStream;

  private final Logger logger;

  private final LoginPath loginPath;

  private GZIPOutputStream outputStream;

  private String prefix;

  private final MutableList<RegularFile> result = MutableList.create();

  private long startTime;

  private final Directory targetDirectory;

  private RegularFile targetFile;

  private ResolvedPath targetPath;

  IncrementalBackup(Client worker,
                    LoginPath loginPath,
                    Directory targetDirectory) {
    super(worker);

    byteArray = worker.getByteArray();

    logger = worker.getLogger();

    this.loginPath = loginPath;

    this.targetDirectory = targetDirectory;
  }

  @Override
  final byte execute(byte state) {
    switch (state) {
      case _COPY:
        return executeCopy();
      case _FIRST_LOG:
        return executeFirstLog();
      case _NEXT_LOG:
        return executeNextLog();
      case _PURGE:
        return executePurge();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
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
        throw new UnsupportedOperationException("Implement me: task=" + task);
    }
  }

  @Override
  final byte executeStart() {
    startTime = System.currentTimeMillis();

    logger.log(ESTART, loginPath, targetDirectory.getPath());

    result.clear();

    return toExecuteStatement(
      _FIRST_LOG,

      "SELECT @@log_bin_basename;",

      "SHOW BINARY LOGS;",

      "FLUSH BINARY LOGS;"
    );
  }

  @Override
  final ImmutableList<RegularFile> getResultImpl(
      IOException exception, ImmutableList<String> stderr, ImmutableList<String> stdout)
      throws IOException {
    if (exception == null) {
      long totalTime;
      totalTime = System.currentTimeMillis() - startTime;

      logger.log(ESUCCESS, totalTime);

      return result.toImmutableList();
    }

    else {
      logger.log(EFAILED, exception);

      throw exception;
    }
  }

  private byte executeCopy() {
    if (copyMore) {
      return toIo(IO_COPY, _COPY);
    }

    else {
      result.add(targetFile);

      logger.log(EBINARY_LOG, binlogPathName);

      return toIo(IO_CLOSE, _NEXT_LOG);
    }
  }

  private byte executeFirstLog() {
    ImmutableList<String> executeStatementResult;
    executeStatementResult = getSubTaskResult();

    if (executeStatementResult.isEmpty()) {
      // log error

      return toFinally();
    }

    basename = executeStatementResult.get(0);

    logger.log(ELOGBIN_BASENAME, basename);

    binaryLogs = executeStatementResult;

    binaryLogsIndex = 1;

    prefix = createBackupPrefix();

    return executeNextLog();
  }

  private byte executeNextLog() {
    if (binaryLogsIndex < binaryLogs.size()) {
      String row;
      row = binaryLogs.get(binaryLogsIndex);

      binaryLogsIndex++;

      binlogSimpleName = getBinlogSimpleName(row);

      int dot;
      dot = binlogSimpleName.lastIndexOf('.');

      if (dot < 0) {
        throw new UnsupportedOperationException("Implement me");
      }

      String extension;
      extension = binlogSimpleName.substring(dot);

      binlogPathName = basename + extension;

      return toIo(IO_OPEN, _COPY);
    }

    else {
      return toExecuteStatement(
        _PURGE,

        "SHOW MASTER STATUS;"
      );
    }
  }

  private byte executePurge() {
    ImmutableList<String> masterStatus;
    masterStatus = getSubTaskResult();

    if (masterStatus.isEmpty()) {
      throw new UnsupportedOperationException("Implement me: masterStatus is empty");
    }

    String masterStatusRow0;
    masterStatusRow0 = masterStatus.get(0);

    String currentBinLog;
    currentBinLog = getBinlogSimpleName(masterStatusRow0);

    return toExecuteStatement(
      toFinally(),

      Mysql.sql("PURGE BINARY LOGS TO '", currentBinLog, "'")
    );
  }

  private String getBinlogSimpleName(String row) {
    StringBuilder currentBinLogBuilder;
    currentBinLogBuilder = new StringBuilder();

    outer: for (char c : row.toCharArray()) {
      switch (c) {
        case '\t':
          break outer;
        default:
          currentBinLogBuilder.append(c);
      }
    }

    return currentBinLogBuilder.toString();
  }

  private void ioClose() throws IOException {
    Throwable rethrow;
    rethrow = Try.begin();

    rethrow = Try.close(rethrow, inputStream);

    rethrow = Try.close(rethrow, outputStream);

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  private void ioCopy() throws IOException {
    int count;
    count = inputStream.read(byteArray);

    if (count > 0) {
      outputStream.write(byteArray, 0, count);

      copyMore = true;
    } else {
      targetFile = targetPath.toRegularFile();

      copyMore = false;
    }
  }

  private void ioOpen() throws IOException {
    targetPath = targetDirectory.resolve(prefix + '-' + binlogSimpleName + ".gz");

    OutputStream out;
    out = targetPath.openOutputStream();

    outputStream = new GZIPOutputStream(out);

    RegularFile regularFile;
    regularFile = LocalFs.getRegularFile(binlogPathName);

    inputStream = regularFile.openInputStream();

    copyMore = false;

    ioCopy();
  }

  private byte toExecuteStatement(byte onReady, String... statements) {
    return toSubTask(
      new ExecuteStatement(client, loginPath, statements, Mysql.skipColumnNames()),

      onReady
    );
  }

}
