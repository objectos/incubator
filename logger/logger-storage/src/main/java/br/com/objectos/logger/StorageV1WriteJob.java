/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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
package br.com.objectos.logger;

import br.com.objectos.concurrent.Concurrent;
import br.com.objectos.concurrent.CpuTask;
import br.com.objectos.concurrent.IoTask;
import br.com.objectos.concurrent.IoWorker;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import objectos.lang.Level;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

final class StorageV1WriteJob implements IoTask, CpuTask, WriteJob {

  private static final byte _FINALLY = 0;

  private static final byte _FLUSH = 1;

  private static final byte _LISTENERS = 2;

  private static final byte _LOG_HEADER = 3;

  private static final byte _LOG_THROWABLE = 4;

  private static final byte _LOG_THROWABLES = 5;

  private static final byte _LOG_VALUE = 6;

  private static final byte _LOG_VALUES = 7;

  private static final byte _POP_THROWABLE = 8;

  private static final byte _STACK_TRACE = 9;

  private static final byte _STACK_TRACE_LENGTH = 10;

  private static final byte _STACK_TRACE_LINE_NUMBER = 11;

  private static final byte _START = 12;

  private static final byte _STOP = 13;

  private static final byte _SUPRESSED = 14;

  private static final byte _THROWABLE = 15;

  private static final byte _WAIT_IO = 16;

  private static final byte _WRITE_BYTE = 17;

  private static final byte _WRITE_BYTE_ARRAY = 18;

  private static final byte _WRITE_SHORT = 19;

  private static final byte _WRITE_STRING = 20;

  private static final byte _WRITE_STRING_LIST = 21;

  private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

  private final FileChannel channel;

  private IOException ioException;

  private final IoWorker ioWorker;

  private byte ioReady;

  private volatile boolean ioRunning;

  private int iterIndex;

  private int iterLength;

  private final UnmodifiableList<LogListener> listeners;

  private WriteJobLog log;

  private final Queue<WriteJobLog> queue = new LinkedBlockingQueue<WriteJobLog>();

  private Stack stack;

  private StackTraceElement[] stackTrace;

  private int stackTraceIndex;

  private int stackTraceLineNumber;

  private byte state = _STOP;

  private final StringBuilder stringBuilder = new StringBuilder();

  private byte writeByte;

  private byte[] writeByteArray;

  private int writeByteArrayOffset;

  private byte writeByteArrayReady;

  private byte writeByteReady;

  private int writeShort;

  private byte writeShortReady;

  private String writeString;

  private final GrowableList<String> writeStringList = new GrowableList<>();

  private int writeStringListIndex;

  private byte writeStringListReady;

  private byte writeStringReady;

  StorageV1WriteJob(FileChannel channel,
                    IoWorker ioWorker,
                    UnmodifiableList<LogListener> listeners) {
    this.channel = channel;

    this.ioWorker = ioWorker;

    this.listeners = listeners;
  }

  @Override
  public final void close() throws IOException {
    try {
      Concurrent.exhaust(this);
    } finally {
      channel.close();
    }
  }

  @Override
  public final void executeIo() {
    try {
      byteBuffer.flip();

      channel.write(byteBuffer);

      channel.force(false);

      byteBuffer.compact();
    } catch (IOException e) {
      ioException = e;
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
  public final void offer(WriteJobLog newLog) {
    queue.offer(newLog);
  }

  @Override
  public final void start() {
    state = _START;
  }

  private void addWriteStringList(String s) {
    var value = s;

    if (value == null) {
      value = "";
    }

    writeStringList.add(value);
  }

  private byte execute(byte state) {
    switch (state) {
      case _FINALLY:
        return executeFinally();
      case _FLUSH:
        return executeFlush();
      case _LISTENERS:
        return executeListeners();
      case _LOG_HEADER:
        return executeLogHeader();
      case _LOG_THROWABLE:
        return executeLogThrowable();
      case _LOG_THROWABLES:
        return executeLogThrowables();
      case _LOG_VALUE:
        return executeLogValue();
      case _LOG_VALUES:
        return executeLogValues();
      case _POP_THROWABLE:
        return executePopThrowable();
      case _STACK_TRACE:
        return executeStackTrace();
      case _STACK_TRACE_LENGTH:
        return executeStackTraceLength();
      case _STACK_TRACE_LINE_NUMBER:
        return executeStackTraceLineNumber();
      case _START:
        return executeStart();
      case _SUPRESSED:
        return executeSuppressed();
      case _THROWABLE:
        return executeThrowable();
      case _WAIT_IO:
        return executeWaitIo();
      case _WRITE_BYTE:
        return executeWriteByte();
      case _WRITE_BYTE_ARRAY:
        return executeWriteByteArray();
      case _WRITE_SHORT:
        return executeWriteShort();
      case _WRITE_STRING:
        return executeWriteString();
      case _WRITE_STRING_LIST:
        return executeWriteStringList();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
    }
  }

  private byte executeFinally() {
    if (ioException != null) {
      // job failed to write a log
      // probably it will fail again if we try to log this exception
      // can't do much but print the stack trace
      ioException.printStackTrace();

      ioException = null;
    }

    log = null;

    stackTrace = null;

    stringBuilder.setLength(0);

    writeByteArray = null;

    writeString = null;

    writeStringList.clear();

    return _STOP;
  }

  private byte executeFlush() {
    iterIndex = 0;

    return executeIo(_LISTENERS);
  }

  private byte executeIo(byte onReady) {
    ioReady = onReady;

    ioRunning = true;

    ioWorker.submit(this);

    return _WAIT_IO;
  }

  private byte executeListeners() {
    if (iterIndex < listeners.size()) {
      LogListener listener;
      listener = listeners.get(iterIndex);

      iterIndex++;

      listener.onLog(log);

      return _LISTENERS;
    } else {
      return _START;
    }
  }

  private byte executeLogHeader() {
    byteBuffer.put(StorageV1.LOG);

    byteBuffer.put(log.getTypeV1());

    Level level;
    level = log.level;

    byte levelKey;
    levelKey = level.getKey();

    byteBuffer.put(levelKey);

    byteBuffer.putLong(log.timestamp);

    writeStringList.clear();

    writeStringList.add(log.key);

    writeStringList.add(log.source);

    writeStringList.add(log.thread);

    return execute(
      toWriteStringList(_LOG_VALUES)
    );
  }

  private byte executeLogThrowable() {
    if (iterIndex < iterLength) {
      Object maybeThrowable;
      maybeThrowable = log.getValue(iterIndex);

      iterIndex++;

      if (maybeThrowable instanceof Throwable) {
        Throwable throwable;
        throwable = (Throwable) maybeThrowable;

        return execute(
          toWriteByte(
            StorageV1.THROWABLE,

            toThrowable(throwable, _LOG_THROWABLE)
          )
        );
      }

      return execute(
        toWriteByte(StorageV1.NOT_THROWABLE, _LOG_THROWABLE)
      );
    }

    else {
      return execute(_FLUSH);
    }
  }

  private byte executeLogThrowables() {
    iterIndex = 0;

    return execute(_LOG_THROWABLE);
  }

  private byte executeLogValue() {
    if (iterIndex < iterLength) {
      String value;
      value = log.formatValue(iterIndex);

      iterIndex++;

      return execute(
        toWriteString(value, _LOG_VALUE)
      );
    }

    else {
      return execute(_LOG_THROWABLES);
    }
  }

  private byte executeLogValues() {
    iterIndex = 0;

    return execute(_LOG_VALUE);
  }

  private byte executePopThrowable() {
    byte returnTo;
    returnTo = stack.ready;

    stack = stack.previous;

    return execute(
      toWriteByte(StorageV1.POP_THROWABLE, returnTo)
    );
  }

  private byte executeStackTrace() {
    if (stackTraceIndex < stackTrace.length) {
      StackTraceElement element;
      element = stackTrace[stackTraceIndex];

      stackTraceIndex++;

      return execute(
        toStackTraceElement(element)
      );
    }

    else {
      return execute(_SUPRESSED);
    }
  }

  private byte executeStackTraceLength() {
    Throwable t;
    t = stack.value;

    stackTrace = t.getStackTrace();

    stackTraceIndex = 0;

    return execute(
      toWriteShort(stackTrace.length, _STACK_TRACE)
    );
  }

  private byte executeStackTraceLineNumber() {
    return execute(
      toWriteShort(stackTraceLineNumber, _STACK_TRACE)
    );
  }

  private byte executeStart() {
    if (queue.isEmpty()) {
      return executeFinally();
    }

    log = queue.remove();

    byteBuffer.clear();

    ioRunning = false;

    iterIndex = 0;

    iterLength = log.getTypeV1();

    return execute(_LOG_HEADER);
  }

  private byte executeSuppressed() {
    if (stack.hasNextSuppressed()) {
      Throwable t;
      t = stack.nextSuppressed();

      return execute(
        toWriteByte(StorageV1.THROWABLE_SUPPRESSED, toThrowable(t, _SUPRESSED))
      );
    }

    Throwable cause;
    cause = stack.cause;

    if (cause != null) {
      return execute(
        toWriteByte(StorageV1.THROWABLE_CAUSE, toThrowable(cause, _POP_THROWABLE))
      );
    }

    else {
      return execute(_POP_THROWABLE);
    }
  }

  private byte executeThrowable() {
    writeStringList.clear();

    writeStringList.add(stack.className);

    writeStringList.add(stack.message);

    return execute(
      toWriteStringList(_STACK_TRACE_LENGTH)
    );
  }

  private byte executeWaitIo() {
    if (ioRunning) {
      return _WAIT_IO;
    }

    else if (ioException != null) {
      return _FINALLY;
    }

    else {
      return ioReady;
    }
  }

  private byte executeWriteByte() {
    if (!byteBuffer.hasRemaining()) {
      return executeIo(_WRITE_BYTE);
    }

    byteBuffer.put(writeByte);

    return writeByteReady;
  }

  private byte executeWriteByteArray() {
    if (!byteBuffer.hasRemaining()) {
      return executeIo(_WRITE_BYTE_ARRAY);
    }

    int bufferLength;
    bufferLength = byteBuffer.remaining();

    int writeByteArrayLength;
    writeByteArrayLength = writeByteArray.length - writeByteArrayOffset;

    int thisLength;
    thisLength = Math.min(bufferLength, writeByteArrayLength);

    byteBuffer.put(writeByteArray, writeByteArrayOffset, thisLength);

    writeByteArrayOffset += thisLength;

    if (writeByteArrayOffset < writeByteArray.length) {
      return executeIo(_WRITE_BYTE_ARRAY);
    }

    else {
      return execute(writeByteArrayReady);
    }
  }

  private byte executeWriteShort() {
    if (writeShort > Short.MAX_VALUE) {
      return toIoException("Exceeds short value limit: " + writeShort + " > Short.MAX_VALUE");
    }

    int remaining;
    remaining = byteBuffer.remaining();

    if (remaining < 2) {
      return executeIo(_WRITE_SHORT);
    }

    byteBuffer.putShort((short) writeShort);

    return execute(writeShortReady);
  }

  private byte executeWriteString() {
    byte[] bytes;
    bytes = writeString.getBytes(StorageV1.CHARSET);

    int length;
    length = bytes.length;

    return execute(
      toWriteShort(
        length,

        toWriteByteArray(bytes, writeStringReady)
      )
    );
  }

  private byte executeWriteStringList() {
    if (writeStringListIndex < writeStringList.size()) {
      String value = writeStringList.get(writeStringListIndex);

      writeStringListIndex++;

      return execute(
        toWriteString(value, _WRITE_STRING_LIST)
      );
    }

    else {
      writeStringListIndex = 0;

      writeStringList.clear();

      return writeStringListReady;
    }
  }

  private byte toIoException(String message) {
    ioException = new IOException(message);

    return _FINALLY;
  }

  private byte toStackTraceElement(StackTraceElement element) {
    writeStringList.clear();

    addWriteStringList(element.getClassLoaderName());

    addWriteStringList(element.getModuleName());

    addWriteStringList(element.getModuleVersion());

    addWriteStringList(element.getClassName());

    addWriteStringList(element.getMethodName());

    addWriteStringList(element.getFileName());

    stackTraceLineNumber = element.getLineNumber();

    return toWriteStringList(_STACK_TRACE_LINE_NUMBER);
  }

  private byte toThrowable(Throwable value, byte onReady) {
    if (stack == null) {
      stack = new Stack(value, onReady);
    } else {
      Stack previous;
      previous = stack;

      stack = new Stack(previous, value, onReady);
    }

    return _THROWABLE;
  }

  private byte toWriteByte(byte value, byte onReady) {
    writeByte = value;

    writeByteReady = onReady;

    return _WRITE_BYTE;
  }

  private byte toWriteByteArray(byte[] value, byte onReady) {
    writeByteArray = value;

    writeByteArrayOffset = 0;

    writeByteArrayReady = onReady;

    return _WRITE_BYTE_ARRAY;
  }

  private byte toWriteShort(int value, byte onReady) {
    writeShort = value;

    writeShortReady = onReady;

    return _WRITE_SHORT;
  }

  private byte toWriteString(String value, byte onReady) {
    writeString = value;

    writeStringReady = onReady;

    return _WRITE_STRING;
  }

  private byte toWriteStringList(byte onReady) {
    writeStringListIndex = 0;

    writeStringListReady = onReady;

    return _WRITE_STRING_LIST;
  }

  private static class Stack {

    final Throwable cause;

    final String className;

    final String message;

    final Stack previous;

    final byte ready;

    final Throwable[] suppressed;

    int suppressedIndex = 0;

    final Throwable value;

    Stack(Stack previous, Throwable value, byte onReady) {
      Class<? extends Throwable> type;
      type = value.getClass();

      cause = value.getCause();

      className = type.getCanonicalName();

      message = value.getLocalizedMessage();

      this.previous = previous;

      ready = onReady;

      suppressed = value.getSuppressed();

      this.value = value;
    }

    Stack(Throwable value, byte onReady) {
      this(null, value, onReady);
    }

    final boolean hasNextSuppressed() {
      return suppressedIndex < suppressed.length;
    }

    final Throwable nextSuppressed() {
      return suppressed[suppressedIndex++];
    }

  }

}
