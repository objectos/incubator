/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.replay;

import br.com.objectos.concurrent.IoTask;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.InputStreamSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import objectos.http.Body;
import objectos.http.BodyVisitor;
import objectos.http.Http;
import objectos.http.Method;
import objectos.http.ProtocolException;
import objectos.http.Request;
import objectos.http.RequestHeader;
import objectos.http.RequestParser;
import objectos.http.Response;
import objectos.http.ResponseParser;
import objectos.http.Version;
import objectos.http.Body.Ignored;
import objectos.http.Body.Text;

final class ByteSourceJob implements BodyVisitor, IoTask, Runnable {

  private static final byte IO_CLOSE = 0;

  private static final byte IO_OPEN = 1;

  private static final byte IO_REPLAY_REQUEST_WRITE = 2;

  private final ReplayAdapter adapter;

  private final ByteBuffer byteBuffer;

  private final CharBuffer charBuffer;

  private final Map<Charset, CharsetEncoder> charEncoderMap = new HashMap<>(4);

  private State closeState;

  @SuppressWarnings("unused")
  private String errorMessage;

  private Exception exception;

  private GZIPInputStream gzip;

  private Response httpResponse;

  private final ResponseParser httpResponseParser;

  private int id = 1;

  private State ioReadyState;

  private volatile boolean ioRunning;

  private long ioStartTime;

  private byte ioTask;

  private final IoWorker ioWorker;

  private String prefix;

  @SuppressWarnings("unused")
  private ProtocolException protocolException;

  // private final ReplayRequestProcessor replayRequestProcessor;

  private final SocketAddress remoteAddress;

  private Request replayRequest;

  private Body replayRequestBody;

  private final ReplayRequestChannel replayRequestChannel;

  private final RequestParser replayRequestParser;

  private final ThisReplayRequestWriter replayRequestWriter = new ThisReplayRequestWriter();

  private final ReplayResponseParser replayResponseParser;

  private IOException rethrow;

  private SocketChannel socketChannel;

  private InputStreamSource source;

  private State state;

  @SuppressWarnings("unused")
  private Throwable suppressed;

  private final long timeout = 60 * 1000;

  private ZipInputStream zip;

  private ZipEntry zipEntry;

  private State zipEntryAction;

  public ByteSourceJob(ReplayAdapter adapter,
                       IoWorker ioWorker,
                       SocketAddress remoteAddress) {
    this.adapter = adapter;

    int bufferSize;
    bufferSize = 1024;

    byteBuffer = ByteBuffer.allocate(bufferSize);

    charBuffer = CharBuffer.allocate(bufferSize);

    httpResponseParser = new ResponseParser(byteBuffer, charBuffer);

    this.ioWorker = ioWorker;

    this.remoteAddress = remoteAddress;

    replayRequestChannel = new ReplayRequestChannel(bufferSize);

    replayRequestParser = Http.createRequestParser(byteBuffer, charBuffer);

    replayResponseParser = new ReplayResponseParser(adapter, byteBuffer, charBuffer, ioWorker);
  }

  @Override
  public final void executeIo() {
    try {
      switch (ioTask) {
        case IO_CLOSE:
          ioClose();
          break;
        case IO_OPEN:
          ioOpen();
          break;
        case IO_REPLAY_REQUEST_WRITE:
          ioReplayRequestWrite();
          break;
        default:
          throw new UnsupportedOperationException("Implement me: task=" + ioTask);
      }
    } catch (IOException e) {
      exception = e;
    } finally {
      ioRunning = false;
    }
  }

  @Override
  public final void run() {
    while (state != null) {
      state = execute();
    }
  }

  public final void setInput(InputStreamSource source) {
    this.source = source;

    state = State.RESET;
  }

  @Override
  public final String toString() {
    try {
      return state.toString();
    } catch (NullPointerException e) {
      return "null";
    }
  }

  @Override
  public final void visitBody(Ignored ignored) {
    // noop
  }

  @Override
  public final void visitBody(Text text) {
    try {
      writeBody0(text);
    } catch (IOException e) {
      rethrow = e;
    }
  }

  final void close0(Closeable closeable) {
    if (closeable == null) {
      return;
    }

    try {
      closeable.close();
    } catch (Throwable e) {
      suppressed = e;
    }
  }

  final CharsetEncoder getCharEncoder(Charset charset) {
    CharsetEncoder encoder;
    encoder = charEncoderMap.get(charset);

    if (encoder == null) {
      encoder = charset.newEncoder();

      charEncoderMap.put(charset, encoder);
    } else {
      encoder.reset();
    }

    return encoder;
  }

  private State execute() {
    switch (state) {
      case CLOSE:
        return executeClose();
      case END:
        return executeEnd();
      case ERROR:
      case ERROR_IO:
      case ERROR_PROTOCOL:
        throw new UnsupportedOperationException("Implement me @ " + state.name());
      case IO_TIMEOUT:
        return executeIoTimeout();
      case IO_WAIT:
        return executeIoWait();
      case OPEN:
        return executeOpen();
      case REPORT:
        return executeReport();
      case REQUEST:
        return executeRequest();
      case REQUEST_PARSE:
        return executeRequestParse();
      case REQUEST_PROCESS:
        return executeRequestProcess();
      case RESET:
        return executeReset();
      case RESPONSE:
        return executeResponse();
      case RESPONSE_IO:
        return executeResponseIo();
      case RESPONSE_REPLAY:
        return executeResponseReplay();
      case RESPONSE_REPLAY_IO:
        return executeResponseReplayIo();
      case ZIP_ENTRY:
        return executeZipEntry();
      default:
        throw new UnsupportedOperationException("Implement me @ " + state.name());
    }
  }

  private State executeClose() {
    return toIo(IO_CLOSE, closeState);
  }

  private State executeEnd() {
    return null;
  }

  private State executeIoTimeout() {
    ioWorker.cancelOrInterrupt(this);

    return toError("I/O timeout");
  }

  private State executeIoWait() {
    if (ioRunning) {
      return State.IO_WAIT;
    }

    else if (closeState == null && exception != null) {
      return toError(exception);
    }

    else if (ioTimedOut()) {
      return State.IO_TIMEOUT;
    }

    else {
      return ioReadyState;
    }
  }

  private State executeOpen() {
    zipEntryAction = State.REQUEST;

    return toIo(IO_OPEN, State.ZIP_ENTRY);
  }

  private State executeReport() {
    adapter.test(httpResponse);

    zipEntryAction = State.REQUEST;

    return State.ZIP_ENTRY;
  }

  private State executeRequest() {
    String entryName;
    entryName = zipEntry.getName();

    if (entryName.endsWith("archive")) {
      return State.ZIP_ENTRY;
    }

    prefix = String.format("%012d", id++);

    if (!entryName.endsWith(prefix + ".request.gz")) {
      return toError("Not the request: expected " + prefix + " found " + entryName);
    }

    adapter.begin(prefix);

    try {
      replayRequestChannel.setInput();

      replayRequestParser.setInput(replayRequestChannel);

      return State.REQUEST_PARSE;
    } catch (IOException e) {
      return toError(e);
    }
  }

  private State executeRequestParse() {
    if (replayRequestParser.isActive()) {
      replayRequestParser.executeOne();

      return State.REQUEST_PARSE;
    }

    try {
      replayRequest = replayRequestParser.getResult();

      return State.REQUEST_PROCESS;
    } catch (ProtocolException e) {
      return toError(e);
    }
  }

  private State executeRequestProcess() {
    charBuffer.clear();

    replayRequestWriter.reset();

    adapter.writeRequest(replayRequestWriter, replayRequest);

    zipEntryAction = State.RESPONSE;

    return toIo(IO_REPLAY_REQUEST_WRITE, State.ZIP_ENTRY);
  }

  private State executeReset() {
    byteBuffer.clear();

    charBuffer.clear();

    closeState = null;

    errorMessage = null;

    gzip = null;

    httpResponse = null;

    id = 1;

    exception = null;

    protocolException = null;

    replayRequest = null;

    replayRequestBody = null;

    socketChannel = null;

    suppressed = null;

    zip = null;

    zipEntry = null;

    zipEntryAction = null;

    return State.OPEN;
  }

  private State executeResponse() {
    String entryName;
    entryName = zipEntry.getName();

    if (!entryName.endsWith(prefix + ".response.gz")) {
      return toError("Not the response: expected " + prefix + " found " + entryName);
    }

    httpResponseParser.setInput(socketChannel);

    return State.RESPONSE_IO;
  }

  private State executeResponseIo() {
    if (httpResponseParser.isActive()) {
      httpResponseParser.executeOne();

      return State.RESPONSE_IO;
    }

    try {
      httpResponse = httpResponseParser.getResult();

      return State.RESPONSE_REPLAY;
    } catch (ProtocolException e) {
      return toError(e);
    }
  }

  private State executeResponseReplay() {
    try {
      gzip = new GZIPInputStream(zip);
    } catch (IOException e) {
      return toError(e);
    }

    replayResponseParser.setInput(gzip);

    return State.RESPONSE_REPLAY_IO;
  }

  private State executeResponseReplayIo() {
    if (replayResponseParser.shouldExecute()) {
      replayResponseParser.executeOne();

      return State.RESPONSE_REPLAY_IO;
    }

    return State.REPORT;
  }

  private State executeZipEntry() {
    try {
      zipEntry = zip.getNextEntry();
    } catch (IOException e) {
      return toError(e);
    }

    if (zipEntry == null) {
      return toClose(State.END);
    }

    return zipEntryAction;
  }

  private void ioClose() {
    close0(socketChannel);

    close0(gzip);

    close0(zip);
  }

  private void ioOpen() throws IOException {
    socketChannel = SocketChannel.open(remoteAddress);

    socketChannel.configureBlocking(false);

    InputStream inputStream;
    inputStream = source.openInputStream();

    zip = new ZipInputStream(inputStream);
  }

  private void ioReplayRequestWrite() throws IOException {
    writeHeader();

    rethrow = null;

    replayRequestBody.acceptBodyVisitor(this);

    if (rethrow != null) {
      throw rethrow;
    }
  }

  private boolean ioTimedOut() {
    long elapsed;
    elapsed = System.currentTimeMillis() - ioStartTime;

    return elapsed > timeout;
  }

  private State toClose(State afterClose) {
    closeState = afterClose;

    return State.CLOSE;
  }

  private State toError(Exception e) {
    exception = e;

    return toClose(State.ERROR_IO);
  }

  private State toError(ProtocolException e) {
    protocolException = e;

    return toClose(State.ERROR_PROTOCOL);
  }

  private State toError(String message) {
    errorMessage = message;

    return toClose(State.ERROR);
  }

  private State toIo(byte task, State onSuccess) {
    ioReadyState = onSuccess;

    ioStartTime = System.currentTimeMillis();

    ioTask = task;

    ioRunning = true;

    ioWorker.submit(this);

    return State.IO_WAIT;
  }

  private void writeBody0(Text text) throws IOException {
    Charset charset;
    charset = text.getCharset();

    CharsetEncoder encoder;
    encoder = getCharEncoder(charset);

    String contents;
    contents = text.getContents();

    char[] charArray;
    charArray = contents.toCharArray();

    int contentLength;
    contentLength = charArray.length;

    int sent;
    sent = 0;

    while (sent < contentLength) {
      charBuffer.clear();

      int remainingToSend;
      remainingToSend = contentLength - sent;

      int charsToSendInThisIteration;
      charsToSendInThisIteration = Math.min(remainingToSend, charBuffer.limit());

      charBuffer.put(charArray, sent, charsToSendInThisIteration);

      sent += charsToSendInThisIteration;

      writeCharBuffer(encoder);
    }
  }

  private void writeCharBuffer(CharsetEncoder encoder) throws IOException {
    charBuffer.flip();

    byteBuffer.clear();

    CoderResult coderResult;
    coderResult = encoder.encode(charBuffer, byteBuffer, false);

    if (!coderResult.isUnderflow()) {
      throw new UnsupportedOperationException("Implement me");
    }

    byteBuffer.flip();

    while (byteBuffer.hasRemaining()) {
      socketChannel.write(byteBuffer);
    }
  }

  private void writeHeader() throws IOException {
    Charset charset;
    charset = Charsets.isoLatin1();

    CharsetEncoder encoder;
    encoder = getCharEncoder(charset);

    writeCharBuffer(encoder);
  }

  private class ReplayRequestChannel implements ReadableByteChannel {

    private final byte[] byteArray;

    ReplayRequestChannel(int bufferSize) {
      byteArray = new byte[bufferSize];
    }

    @Override
    public final void close() throws IOException {
      // noop
    }

    @Override
    public final boolean isOpen() {
      return true;
    }

    @Override
    public final int read(ByteBuffer dst) throws IOException {
      int remaining;
      remaining = dst.remaining();

      if (remaining == 0) {
        return 0;
      }

      int bytesRead;
      bytesRead = gzip.read(byteArray, 0, remaining);

      if (bytesRead > 0) {
        dst.put(byteArray, 0, bytesRead);
      }

      return bytesRead;
    }

    public void setInput() throws IOException {
      gzip = new GZIPInputStream(zip);
    }

  }

  private enum State {

    CLOSE,

    END,

    ERROR,

    ERROR_IO,

    ERROR_PROTOCOL,

    IO_RETRY,

    IO_TIMEOUT,

    IO_WAIT,

    OPEN,

    REPORT,

    REQUEST,

    REQUEST_PARSE,

    REQUEST_PROCESS,

    RESET,

    RESPONSE,

    RESPONSE_IO,

    RESPONSE_REPLAY,

    RESPONSE_REPLAY_IO,

    ZIP_ENTRY;

  }

  private class ThisReplayRequestWriter implements ReplayRequestWriter {

    private boolean overflow;

    @Override
    public final void writeBody(Body body) {
      buffer(Http.CRLF);

      replayRequestBody = body;
    }

    @Override
    public final void writeHeader(RequestHeader header) {
      buffer(header.toString());

      buffer(Http.CRLF);
    }

    @Override
    public final void writeRequestLine(Method method, String target, Version version) {
      buffer(method.name());

      buffer(' ');

      buffer(target);

      buffer(' ');

      buffer(version.signature);

      buffer(Http.CRLF);
    }

    final void reset() {
      overflow = false;
    }

    private void buffer(char c) {
      if (overflow) {
        overflow(c);

        return;
      }

      if (!charBuffer.hasRemaining()) {
        overflow(c);

        return;
      }

      charBuffer.put(c);
    }

    private void buffer(String value) {
      if (overflow) {
        overflow(value);

        return;
      }

      int length;
      length = value.length();

      if (length > charBuffer.remaining()) {
        overflow(value);

        return;
      }

      charBuffer.put(value);
    }

    private void overflow(char c) {
      throw new UnsupportedOperationException("Implement me");
    }

    private void overflow(String value) {
      throw new UnsupportedOperationException("Implement me");
    }

  }

}
