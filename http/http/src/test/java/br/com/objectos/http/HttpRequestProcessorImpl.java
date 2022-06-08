/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.http;

import br.com.objectos.concurrent.CpuTask;
import br.com.objectos.concurrent.IoTask;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.PathNameVisitor;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Date;
import objectos.lang.Suppressed;
import objectos.lang.Throwables;

final class HttpRequestProcessorImpl
    implements
    CpuTask,
    IoTask,
    HttpProcessor,
    PathNameVisitor<Void, HttpRequestProcessorImpl> {

  static final byte _BODY = 1;

  static final byte _ENCODE = 2;

  static final byte _FINALLY = 3;

  static final byte _RESPONSE_HEADERS = 4;

  static final byte _STOP = 0;

  static final byte _WAIT_IO = 5;

  static final byte _WRITE_FILE = 6;

  static final byte IO_RESOLVE = 1;

  static final byte IO_WRITE = 2;

  static final byte IO_WRITE_FILE = 3;

  ByteBuffer byteBuffer;

  CharBuffer charBuffer;

  long contentLength;

  RegularFile file;

  FileChannel fileChannel;

  long fileChannelPosition;

  byte ioReady;

  byte ioTask;

  byte state;

  StringBuilder stringBuilder;

  int stringBuilderOffset;

  long writtenCount;

  private WritableByteChannel channel;

  private CharsetEncoder encoder;

  private boolean eof;

  private Throwable error;

  private HttpResponseHandle handle;

  private volatile boolean ioRunning;

  private IoWorker ioWorker;

  private long lastModifiedMillis;

  private final Directory siteDirectory;

  HttpRequestProcessorImpl(Directory siteDirectory) {
    this.siteDirectory = siteDirectory;
  }

  @Override
  public final void executeIo() {
    try {
      executeIo(ioTask);
    } catch (Throwable e) {
      error = e;
    } finally {
      ioRunning = false;
    }
  }

  @Override
  public final void executeOne() {
    state = execute(state);
  }

  @Override
  public final boolean isActive() {
    return state != _STOP;
  }

  @Override
  public final void requestHeader(String name, String value) {}

  @Override
  public final void requestLine(Method method, RequestTarget target, Version version) {
    if (method != Method.GET) {
      throw new UnsupportedOperationException("Implement me");
    }

    if (!target.pathEquals("/")) {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  @Override
  public final void requestStart(HttpResponseHandle handle) {
    this.handle = handle;

    state = _STOP;
  }

  @Override
  public final CpuTask responseTask() {
    byteBuffer = handle.getByteBuffer();

    channel = handle.getChannel();

    charBuffer = handle.getCharBuffer();

    encoder = handle.getEncoder(Charsets.isoLatin1());

    ioWorker = handle.getIoWorker();

    state = toIo(IO_RESOLVE, _RESPONSE_HEADERS);

    stringBuilder = handle.getStringBuilder();

    return this;
  }

  @Override
  public final Void visitDirectory(
      Directory directory, HttpRequestProcessorImpl p) throws IOException {
    return null;
  }

  @Override
  public final Void visitNotFound(
      ResolvedPath notFound, HttpRequestProcessorImpl p) throws IOException {
    return null;
  }

  @Override
  public final Void visitRegularFile(
      RegularFile file, HttpRequestProcessorImpl p) throws IOException {
    this.file = file;

    contentLength = file.size();

    lastModifiedMillis = file.getLastModifiedMillis();

    return null;
  }

  private byte execute(byte state) {
    switch (state) {
      case _BODY:
        return executeBody();
      case _ENCODE:
        return executeEncode();
      case _FINALLY:
        return executeFinally();
      case _RESPONSE_HEADERS:
        return executeResponseHeaders();
      case _WAIT_IO:
        return executeWaitIo();
      case _WRITE_FILE:
        return executeWriteFile();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
    }
  }

  private byte executeBody() {
    return toIo(IO_WRITE_FILE, _WRITE_FILE);
  }

  private byte executeEncode() {
    int sbRem;
    sbRem = stringBuilder.length() - stringBuilderOffset;

    if (sbRem == 0) {
      if (charBuffer.hasRemaining()) {
        throw new UnsupportedOperationException("Implement me");
      }

      return executeInline(_BODY);
    }

    if (charBuffer.position() > 0) {
      charBuffer.compact();
    }

    int cbRem;
    cbRem = charBuffer.remaining();

    int rem;
    rem = Math.min(sbRem, cbRem);

    int end;
    end = stringBuilderOffset + rem;

    charBuffer.append(stringBuilder, stringBuilderOffset, end);

    stringBuilderOffset = end;

    charBuffer.flip();

    boolean endOfInput;
    endOfInput = end == stringBuilder.length();

    if (byteBuffer.position() > 0) {
      byteBuffer.compact();
    }

    CoderResult result;
    result = encoder.encode(charBuffer, byteBuffer, endOfInput);

    if (result.isOverflow()) {
      throw new UnsupportedOperationException("Implement me");
    }

    else if (!result.isUnderflow()) {
      throw new UnsupportedOperationException("Implement me");
    }

    byteBuffer.flip();

    return toIo(IO_WRITE, _ENCODE);
  }

  private byte executeFinally() {
    channel = null;

    contentLength = 0;

    encoder = null;

    eof = false;

    error = null;

    file = null;

    fileChannel = null;

    fileChannelPosition = 0;

    handle = null;

    ioReady = 0;

    ioRunning = false;

    ioTask = 0;

    ioWorker = null;

    lastModifiedMillis = 0;

    stringBuilder = null;

    stringBuilderOffset = 0;

    writtenCount = 0;

    return _STOP;
  }

  private byte executeInline(byte target) {
    state = target;

    return execute(target);
  }

  private void executeIo(byte task) throws IOException {
    switch (task) {
      case IO_RESOLVE:
        ioResolve();
        break;
      case IO_WRITE:
        ioWrite();
        break;
      case IO_WRITE_FILE:
        ioWriteFile();
        break;
      default:
        throw new UnsupportedOperationException("Implement me: task=" + task);
    }
  }

  private byte executeResponseHeaders() {
    stringBuilder.append("HTTP/1.1 200 OK");

    stringBuilder.append(Http.CRLF);

    stringBuilder.append("Server: Objectos HTTP");

    stringBuilder.append(Http.CRLF);

    stringBuilder.append("Date: ");

    Date now;
    now = new Date();

    stringBuilder.append(handle.formatDate(now));

    stringBuilder.append(Http.CRLF);

    stringBuilder.append("Content-Type: text/html; charset=utf8");

    stringBuilder.append(Http.CRLF);

    stringBuilder.append("Content-Length: ");

    stringBuilder.append(contentLength);

    stringBuilder.append(Http.CRLF);

    stringBuilder.append("Last-Modified: ");

    stringBuilder.append(handle.formatDate(lastModifiedMillis));

    stringBuilder.append(Http.CRLF);

    stringBuilder.append(Http.CRLF);

    stringBuilderOffset = 0;

    return executeInline(_ENCODE);
  }

  private byte executeWaitIo() {
    if (ioRunning) {
      return _WAIT_IO;
    }

    else if (error != null) {
      throw new UnsupportedOperationException("Implement me");
    }

    else {
      return ioReady;
    }
  }

  private byte executeWriteFile() {
    if (eof) {
      return executeInline(_FINALLY);
    } else {
      return toIo(IO_WRITE_FILE, _WRITE_FILE);
    }
  }

  private void ioResolve() throws IOException {
    ResolvedPath maybe;
    maybe = siteDirectory.resolve("index.html");

    maybe.acceptPathNameVisitor(this, this);
  }

  private void ioWrite() throws IOException {
    writtenCount += channel.write(byteBuffer);
  }

  private void ioWriteFile() throws IOException {
    if (fileChannel == null) {
      fileChannel = file.openReadChannel();
    }

    long count;
    count = contentLength - fileChannelPosition;

    Throwable rethrow;
    rethrow = null;

    try {
      long written;
      written = fileChannel.transferTo(fileChannelPosition, count, channel);

      fileChannelPosition += written;
    } catch (Throwable e) {
      rethrow = e;
    }

    eof = fileChannelPosition == contentLength;

    if (rethrow != null || eof) {
      try {
        fileChannel.close();
      } catch (IOException e) {
        rethrow = Suppressed.addIfPossible(rethrow, e);
      }
    }

    Throwables.rethrowIfPossible(rethrow, IOException.class);
  }

  private byte toIo(byte task, byte onReady) {
    ioTask = task;

    ioReady = onReady;

    ioRunning = true;

    ioWorker.submit(this);

    return _WAIT_IO;
  }

}