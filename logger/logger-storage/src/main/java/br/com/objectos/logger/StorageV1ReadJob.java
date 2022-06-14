/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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

import static br.com.objectos.logger.StorageV1.LOG;

import br.com.objectos.concurrent.IoTask;
import br.com.objectos.concurrent.IoWorker;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

final class StorageV1ReadJob implements IoTask, ReadJob {

  /*

  @startuml

  hide empty description
  skinparam shadowing false

  [*] --> START

  FINALLY --> [*]

  IO --> IO_WAIT

  IO_WAIT --> MAYBE_LOGS : ioReady

  MAYBE_LOGS --> FINALLY : logs == 0
  MAYBE_LOGS --> NEXT_LOG : logs > 0

  NEXT_LOG --> FINALLY : no more logs
  NEXT_LOG --> NEXT_PROCESSOR : has more logs

  NEXT_PROCESSOR --> NEXT_LOG : no more processors
  NEXT_PROCESSOR --> NEXT_PROCESSOR : has next processor

  START --> IO : ioTask = readLogs\lioReady = MAYBE_LOGS

  @enduml

  */

  private static final byte _FINALLY = 0;

  private static final byte _LISTENERS = 1;

  private static final byte _LOG_HEADER = 2;

  private static final byte _LOG_HEADER_STRING_LIST = 3;

  private static final byte _LOG_THROWABLE = 4;

  private static final byte _LOG_THROWABLES = 5;

  private static final byte _LOG_VALUES = 6;

  private static final byte _POP_THROWABLE = 7;

  private static final byte _READ_BYTE = 8;

  private static final byte _READ_SHORT = 9;

  private static final byte _READ_STRING = 10;

  private static final byte _READ_STRING_VALUE = 11;

  private static final byte _STACK_TRACE = 12;

  private static final byte _STACK_TRACE_ELEMENT = 13;

  private static final byte _STACK_TRACE_LOOP = 14;

  private static final byte _START = 15;

  private static final byte _STOP = 16;

  private static final byte _STRING_LIST = 17;

  private static final byte _STRING_LIST_VALUE = 18;

  private static final byte _THROWABLE = 19;

  private static final byte _THROWABLE_LOOP = 20;

  private static final byte _THROWABLE_SUPPRESSED = 21;

  private static final byte _THROWABLE_SWITCH = 22;

  private static final byte _WAIT_IO = 23;

  private final byte[] byteArray;

  private final ByteBuffer byteBuffer;

  private final ReadableByteChannel channel;

  private boolean eof;

  private boolean failed;

  private IOException ioException;

  private final IoWorker ioWorker;

  private byte ioReady;

  private volatile boolean ioRunning;

  private int iterIndex;

  private int iterLength;

  private final ImmutableList<LogListener> listeners;

  private int listenersIndex;

  private ReadJobLog log;

  private byte readByte;

  private byte readByteReady;

  private short readShort;

  private byte readShortReady;

  private String readString;

  private byte readStringReady;

  private Stack stack;

  private StackTraceElement[] stackTrace;

  private int stackTraceIndex;

  private byte startReady = _LOG_HEADER;

  private byte state = _STOP;

  private final StringBuilder stringBuilder = new StringBuilder();

  private int stringLength;

  private final MutableList<String> stringList = new MutableList<>();

  private int stringListLength;

  private byte stringListReady;

  StorageV1ReadJob(ReadableByteChannel channel,
                   IoWorker ioWorker,
                   ImmutableList<LogListener> listeners) {
    this.channel = channel;
    this.ioWorker = ioWorker;
    this.listeners = listeners;

    byteBuffer = ByteBuffer.allocate(1024);

    byteBuffer.limit(0);

    byteArray = byteBuffer.array();
  }

  @Override
  public final void close() throws IOException {
    channel.close();
  }

  @Override
  public final void executeIo() {
    try {
      if (byteBuffer.hasRemaining()) {
        byteBuffer.compact();
      } else {
        byteBuffer.clear();
      }

      int readCount;
      readCount = channel.read(byteBuffer);

      eof = readCount < 0;

      byteBuffer.flip();
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
  public final boolean isFailed() {
    return failed;
  }

  @Override
  public final void reset() {
    state = _START;
  }

  final byte execute(byte state) {
    switch (state) {
      case _FINALLY:
        return executeFinally();
      case _LISTENERS:
        return executeListeners();
      case _LOG_HEADER:
        return executeLogHeader();
      case _LOG_HEADER_STRING_LIST:
        return executeLogHeaderStringList();
      case _LOG_THROWABLE:
        return executeLogThrowable();
      case _LOG_THROWABLES:
        return executeLogThrowables();
      case _LOG_VALUES:
        return executeLogValues();
      case _POP_THROWABLE:
        return executePopThrowable();
      case _READ_BYTE:
        return executeReadByte();
      case _READ_SHORT:
        return executeReadShort();
      case _READ_STRING:
        return executeReadString();
      case _READ_STRING_VALUE:
        return executeReadStringValue();
      case _STACK_TRACE:
        return executeStackTrace();
      case _STACK_TRACE_ELEMENT:
        return executeStackTraceElement();
      case _STACK_TRACE_LOOP:
        return executeStackTraceLoop();
      case _START:
        return executeStart();
      case _STRING_LIST:
        return executeStringList();
      case _STRING_LIST_VALUE:
        return executeStringListValue();
      case _THROWABLE:
        return executeThrowable();
      case _THROWABLE_LOOP:
        return executeThrowableLoop();
      case _THROWABLE_SUPPRESSED:
        return executeThrowbleSuppressed();
      case _THROWABLE_SWITCH:
        return executeThrowableSwitch();
      case _WAIT_IO:
        return executeWaitIo();
      default:
        throw new UnsupportedOperationException("Implement me @ " + state);
    }
  }

  private ReadJobLog createLog(byte type) {
    switch (type) {
      case StorageV1.TYPE0:
        return new ReadJobLog0();
      case StorageV1.TYPE1:
        return new ReadJobLog1();
      case StorageV1.TYPE2:
        return new ReadJobLog2();
      case StorageV1.TYPE3:
        return new ReadJobLog3();
      default:
        throw new UnsupportedOperationException("Implement me: type=" + type);
    }
  }

  private String emptyToNull(String string) {
    if (string.isEmpty()) {
      return null;
    }

    return string;
  }

  private byte executeFinally() {
    if (ioException != null) {
      ioException.printStackTrace();

      ioException = null;

      failed = true;
    }

    ioRunning = false;

    log = null;

    readString = null;

    stack = null;

    stackTrace = null;

    stringBuilder.setLength(0);

    stringList.clear();

    return _STOP;
  }

  private byte executeIo(byte onReady) {
    ioReady = onReady;

    ioRunning = true;

    ioWorker.submit(this);

    return _WAIT_IO;
  }

  private byte executeIoIfPossible(byte onReady) {
    if (eof) {
      startReady = onReady;

      return _STOP;
    }

    else {
      return executeIo(onReady);
    }
  }

  private byte executeListeners() {
    if (listenersIndex < listeners.size()) {
      LogListener listener;
      listener = listeners.get(listenersIndex);

      listenersIndex++;

      listener.onLog(log);

      return _LISTENERS;
    }

    else {
      reset();

      startReady = _LOG_HEADER;

      return execute(state);
    }
  }

  private byte executeLogHeader() {
    if (!hasRemaining(StorageV1.HEADER_LENGTH)) {
      return executeIoIfPossible(_LOG_HEADER);
    }

    int position;
    position = byteBuffer.position();

    boolean headerFound;
    headerFound = true
        && byteArray[position++] == LOG[0]
        && byteArray[position++] == LOG[1]
        && byteArray[position++] == LOG[2]
        && byteArray[position++] == LOG[3];

    if (!headerFound) {
      byte[] copy;
      copy = new byte[4];

      byteBuffer.get(copy);

      return toIoException("Header not found: " + new String(copy));
    }

    byte type;
    type = byteArray[position++];

    log = createLog(type);

    iterLength = type;

    byte levelKey;
    levelKey = byteArray[position++];

    log.level = MoreLogging.parseLevel(levelKey);

    byteBuffer.position(position);

    log.timestamp = byteBuffer.getLong();

    return execute(
      toStringList(3, _LOG_HEADER_STRING_LIST)
    );
  }

  private byte executeLogHeaderStringList() {
    log.key = stringList.get(0);

    log.source = stringList.get(1);

    log.thread = stringList.get(2);

    return execute(
      toStringList(iterLength, _LOG_VALUES)
    );
  }

  private byte executeLogThrowable() {
    if (!hasRemaining()) {
      return executeIoIfPossible(_LOG_THROWABLE);
    }

    byte isThrowable;
    isThrowable = byteBuffer.get();

    switch (isThrowable) {
      case StorageV1.NOT_THROWABLE:
        return execute(_LOG_THROWABLES);
      case StorageV1.THROWABLE:
        byte state;
        state = toThrowable(_LOG_THROWABLES);

        log.setThrowable(iterIndex, stack.throwable);

        return execute(state);
      default:
        throw new UnsupportedOperationException("Implement me: marker=" + isThrowable);
    }
  }

  private byte executeLogThrowables() {
    if (iterIndex < iterLength) {
      iterIndex++;

      return execute(_LOG_THROWABLE);
    }

    else {
      listenersIndex = 0;

      return execute(_LISTENERS);
    }
  }

  private byte executeLogValues() {
    log.acceptValues(stringList);

    iterIndex = 0;

    return execute(_LOG_THROWABLES);
  }

  private byte executePopThrowable() {
    byte returnTo;
    returnTo = stack.ready;

    stack = stack.previous;

    return execute(returnTo);
  }

  private byte executeReadByte() {
    if (!hasRemaining()) {
      return executeIoIfPossible(_READ_BYTE);
    }

    readByte = byteBuffer.get();

    return execute(readByteReady);
  }

  private byte executeReadShort() {
    if (!hasRemaining(2)) {
      return executeIoIfPossible(_READ_SHORT);
    }

    readShort = byteBuffer.getShort();

    return execute(readShortReady);
  }

  private byte executeReadString() {
    if (!hasRemaining(2)) {
      return executeIoIfPossible(_READ_STRING);
    }

    stringBuilder.setLength(0);

    stringLength = byteBuffer.getShort();

    return execute(_READ_STRING_VALUE);
  }

  private byte executeReadStringValue() {
    int position;
    position = byteBuffer.position();

    int remaining;
    remaining = byteBuffer.remaining();

    int length;
    length = Math.min(remaining, stringLength);

    readString = new String(byteArray, position, length, StorageV1.CHARSET);

    int newPosition;
    newPosition = position + length;

    byteBuffer.position(newPosition);

    stringLength = stringLength - length;

    if (stringLength > 0) {
      stringBuilder.append(readString);

      readString = null;

      return executeIoIfPossible(_READ_STRING_VALUE);
    }

    else if (stringLength == 0) {
      stringBuilder.append(readString);

      readString = stringBuilder.toString();

      return readStringReady;
    }

    else {
      throw new AssertionError("stringLength < 0");
    }
  }

  private byte executeStackTrace() {
    ReadJobThrowable t;
    t = stack.throwable;

    stackTrace = t.stackTrace = new StackTraceElement[readShort];

    stackTraceIndex = 0;

    return executeStackTraceLoop();
  }

  private byte executeStackTraceElement() {
    String classLoader;
    classLoader = stringList.get(0);

    String moduleName;
    moduleName = stringList.get(1);

    String moduleVersion;
    moduleVersion = stringList.get(2);

    String className;
    className = stringList.get(3);

    String methodName;
    methodName = stringList.get(4);

    String fileName;
    fileName = stringList.get(5);

    int lineNumber;
    lineNumber = readShort;

    StackTraceElement element;
    element = new StackTraceElement(
      emptyToNull(
        classLoader
      ),

      emptyToNull(
        moduleName
      ),

      emptyToNull(
        moduleVersion
      ),

      className,

      methodName,

      emptyToNull(
        fileName
      ),

      lineNumber
    );

    stackTrace[stackTraceIndex] = element;

    stackTraceIndex++;

    return _STACK_TRACE_LOOP;
  }

  private byte executeStackTraceLoop() {
    if (stackTraceIndex < stackTrace.length) {
      return execute(
        toStringList(6, toReadShort(_STACK_TRACE_ELEMENT))
      );
    }

    else {
      return executeThrowableLoop();
    }
  }

  private byte executeStart() {
    return executeIo(startReady);
  }

  private byte executeStringList() {
    if (stringList.size() < stringListLength) {
      return execute(
        toReadString(_STRING_LIST_VALUE)
      );
    }

    else {
      return execute(stringListReady);
    }
  }

  private byte executeStringListValue() {
    String s;
    s = readString();

    stringList.add(s);

    return execute(_STRING_LIST);
  }

  private byte executeThrowable() {
    ReadJobThrowable t;
    t = stack.throwable;

    t.canonicalName = stringList.get(0);

    t.message = stringList.get(1);

    return execute(
      toReadShort(_STACK_TRACE)
    );
  }

  private byte executeThrowableLoop() {
    return execute(
      toReadyByte(_THROWABLE_SWITCH)
    );
  }

  private byte executeThrowableSwitch() {
    switch (readByte) {
      case StorageV1.THROWABLE_CAUSE:
        byte state;
        state = toThrowable(_THROWABLE_LOOP);

        stack.setCause();

        return execute(state);
      case StorageV1.THROWABLE_SUPPRESSED:
        state = toThrowable(_THROWABLE_LOOP);

        stack.setSuppressed();

        return execute(state);
      case StorageV1.POP_THROWABLE:
        return executePopThrowable();
      default:
        throw new UnsupportedOperationException("Implement me: value=" + readByte);
    }
  }

  private byte executeThrowbleSuppressed() {
    throw new UnsupportedOperationException("Implement me");
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

  private boolean hasRemaining() {
    return byteBuffer.hasRemaining();
  }

  private boolean hasRemaining(int length) {
    int remaining;
    remaining = byteBuffer.remaining();

    return remaining >= length;
  }

  private String readString() {
    String s;
    s = readString;

    readString = null;

    return s;
  }

  private byte toIoException(String message) {
    ioException = new IOException(message);

    return _FINALLY;
  }

  private byte toReadShort(byte onReady) {
    readShortReady = onReady;

    return _READ_SHORT;
  }

  private byte toReadString(byte onReady) {
    readStringReady = onReady;

    return _READ_STRING;
  }

  private byte toReadyByte(byte onReady) {
    readByteReady = onReady;

    return _READ_BYTE;
  }

  private byte toStringList(int requiredLength, byte onReady) {
    stringList.clear();

    stringListLength = requiredLength;

    stringListReady = onReady;

    return _STRING_LIST;
  }

  private byte toThrowable(byte onReady) {
    if (stack == null) {
      stack = new Stack(onReady);
    } else {
      Stack previous;
      previous = stack;

      stack = new Stack(previous, onReady);
    }

    return toStringList(2, _THROWABLE);
  }

  private static class Stack {

    final Stack previous;

    final byte ready;

    final ReadJobThrowable throwable = new ReadJobThrowable();

    Stack(byte ready) {
      this(null, ready);
    }

    Stack(Stack previous, byte ready) {
      this.previous = previous;

      this.ready = ready;
    }

    final void setCause() {
      ReadJobThrowable parent;
      parent = previous.throwable;

      parent.cause = throwable;
    }

    final void setSuppressed() {
      ReadJobThrowable parent;
      parent = previous.throwable;

      parent.addSuppressed(throwable);
    }

  }

}