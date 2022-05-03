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

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.string.RandomString;
import br.com.objectos.core.throwable.Throwables;
import br.com.objectos.core.throwable.Try;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import objectos.logging.Event0;
import objectos.logging.Event1;
import objectos.logging.Events;
import objectos.logging.Logger;

final class IncrementalRestore extends AbstractClientJob<ImmutableList<String>> {

  private static final byte _CLOSE = 0;

  private static final byte _CONSUME_MYSQLBINLOG = 1;

  private static final byte _START_MYSQLBINLOG = 3;

  private static final byte COPY = 2;

  private static final Event0 EEMPTY_INPUT;

  private static final Event1<Exception> EFAILED;

  private static final Event1<Long> EFINISH;

  private static final Event0 ESTART;

  private static final byte IO_CLOSE = 0;

  private static final byte IO_COPY = 1;

  private static final byte IO_OPEN_NEXT_FILE = 2;

  private static final byte IO_START_PROCESS = 3;

  private static final RandomString RANDOM = new RandomString();

  static {
    Class<?> source;
    source = IncrementalRestore.class;

    EEMPTY_INPUT = Events.warn(source, "EMPTY_INPUT");

    EFAILED = Events.error(source, "FAILED", Exception.class);

    ESTART = Events.info(source, "START");

    EFINISH = Events.info(source, "FINISH", Long.class);
  }

  private final byte[] byteArray;

  private final ConfigurationFile configurationFile;

  private volatile boolean copyMore;

  private final ImmutableList<RegularFile> files;

  private int filesIndex;

  private InputStream inputStream;

  private final Logger logger;

  private final LoginPath loginPath;

  private final LoginPathFile loginPathFile;

  private final ResolvedPath mysqlbinlog;

  private OutputStream outputStream;

  private long startTime;

  private Directory tempDirectory;

  private final MutableList<RegularFile> uncompressedFiles = MutableList.create();

  private final Directory workDirectory;

  IncrementalRestore(Client worker,
                     LoginPath loginPath,
                     Directory workDirectory,
                     ImmutableList<RegularFile> files) {
    super(worker);

    byteArray = worker.getByteArray();

    configurationFile = worker.getConfigurationFile();

    this.files = files;

    logger = worker.getLogger();

    this.loginPath = loginPath;

    loginPathFile = worker.getLoginPathFile();

    mysqlbinlog = worker.getPath(Program.MYSQLBINLOG);

    this.workDirectory = workDirectory;
  }

  @Override
  final byte execute(byte state) {
    switch (state) {
      case _CLOSE:
        return executeClose();
      case _CONSUME_MYSQLBINLOG:
        return executeConsumeMysqlbinlog();
      case COPY:
        return executeCopy();
      case _START_MYSQLBINLOG:
        return executeStartMysqlbinlog();
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
      case IO_OPEN_NEXT_FILE:
        ioOpenNextFile();
        break;
      case IO_START_PROCESS:
        ioStartProcess();
        break;
      default:
        throw new UnsupportedOperationException("Implement me: task=" + task);
    }
  }

  @Override
  final byte executeStart() {
    if (files.isEmpty()) {
      logger.log(EEMPTY_INPUT);

      return toFinally();
    }

    startTime = System.currentTimeMillis();

    logger.log(ESTART);

    filesIndex = 0;

    uncompressedFiles.clear();

    return toIo(IO_OPEN_NEXT_FILE, COPY, _CLOSE);
  }

  @Override
  final ImmutableList<String> getResultImpl(
      IOException exception, ImmutableList<String> stderr, ImmutableList<String> stdout)
      throws IOException {
    if (exception == null) {
      long totalTime;
      totalTime = System.currentTimeMillis() - startTime;

      logger.log(EFINISH, totalTime);

      return stdout;
    }

    else {
      logger.log(EFAILED, exception);

      throw exception;
    }
  }

  private byte executeClose() {
    return toIo(IO_CLOSE, toFinally());
  }

  private byte executeConsumeMysqlbinlog() {
    InputStreamSource source;
    source = new InputStreamSource() {
      @Override
      public final InputStream openInputStream() throws IOException {
        return inputStream;
      }
    };

    return toSubTask(
        new ExecuteInputStreamSource(client, loginPath, source),

        _CLOSE
    );
  }

  private byte executeCopy() {
    if (copyMore) {
      return toIo(IO_COPY, COPY, _CLOSE);
    }

    else if (filesIndex < files.size()) {
      return toIo(IO_OPEN_NEXT_FILE, COPY, _CLOSE);
    }

    else {
      return _START_MYSQLBINLOG;
    }
  }

  private byte executeStartMysqlbinlog() {
    addCommand(mysqlbinlog.getPath());

    addCommand(configurationFile);

    addCommand(loginPathFile);

    addCommand(loginPath);

    for (int i = 0, size = uncompressedFiles.size(); i < size; i++) {
      RegularFile f;
      f = uncompressedFiles.get(i);

      addCommand(f.getPath());
    }

    return toIo(IO_START_PROCESS, _CONSUME_MYSQLBINLOG, _CLOSE);
  }

  private void ioClose() throws IOException {
    Throwable rethrow;
    rethrow = Try.begin();

    rethrow = Try.close(rethrow, inputStream);

    inputStream = null;

    rethrow = Try.close(rethrow, outputStream);

    outputStream = null;

    if (tempDirectory != null) {
      try {
        tempDirectory.deleteContents();

        tempDirectory.delete();
      } catch (IOException e) {
        rethrow = Throwables.addSuppressed(rethrow, e);
      }
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  private void ioCopy() throws IOException {
    int count;
    count = inputStream.read(byteArray);

    if (count > 0) {
      outputStream.write(byteArray, 0, count);

      copyMore = true;
    } else {
      Throwable rethrow;
      rethrow = Try.begin();

      rethrow = Try.close(rethrow, inputStream);

      rethrow = Try.close(rethrow, outputStream);

      copyMore = false;

      Try.rethrowIfPossible(rethrow, IOException.class);
    }
  }

  private void ioOpenNextFile() throws IOException {
    if (tempDirectory == null) {
      String random;
      random = RANDOM.nextString(12);

      tempDirectory = workDirectory.createDirectory(random);
    }

    RegularFile file;
    file = files.get(filesIndex);

    InputStream in;
    in = file.openInputStream();

    inputStream = new GZIPInputStream(in);

    RegularFile target;
    target = tempDirectory.createRegularFile("binlog." + filesIndex);

    uncompressedFiles.add(target);

    outputStream = target.openOutputStream();

    filesIndex++;

    ioCopy();
  }

  private void ioStartProcess() throws IOException {
    startProcess();

    inputStream = getProcessInputStream();
  }

}
