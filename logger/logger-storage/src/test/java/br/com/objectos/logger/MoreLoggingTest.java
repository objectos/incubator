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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.concurrent.CpuWorkerService;
import br.com.objectos.concurrent.FixedCpuWorker;
import br.com.objectos.concurrent.IoWorkerService;
import br.com.objectos.concurrent.SingleThreadIoWorker;
import br.com.objectos.core.logging.testing.TestableLogger;
import br.com.objectos.core.service.Services;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.fs.watch.Watch;
import br.com.objectos.random.testing.Next;
import java.io.IOException;
import objectos.lang.Note0;
import objectos.lang.Note1;
import objectos.lang.Note2;
import objectos.lang.Note3;
import objectos.lang.NoteSink;
import objectos.util.GrowableList;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MoreLoggingTest implements LogListener {

  private static final Note0 DEBUG0 = Note0.debug();

  private static final Note1<Arg1> DEBUG1 = Note1.debug();

  private static final Note2<Arg1, Arg2> DEBUG2 = Note2.debug();

  private static final Note3<Arg1, Arg2, Arg3> DEBUG3 = Note3.debug();

  private static final Note1<Ex1> ERROR1 = Note1.error();

  private static final Note2<Ex1, Ex2> ERROR2 = Note2.error();

  private static final Note3<Ex1, Ex2, Ex3> ERROR3 = Note3.error();

  private static final Note0 INFO0 = Note0.info();

  private static final Note1<Arg1> INFO1 = Note1.info();

  private static final Note2<Arg1, Arg2> INFO2 = Note2.info();

  private static final Note3<Arg1, Arg2, Arg3> INFO3 = Note3.info();

  private static final Note0 TRACE0 = Note0.trace();

  private static final Note1<Arg1> TRACE1 = Note1.trace();

  private static final Note2<Arg1, Arg2> TRACE2 = Note2.trace();

  private static final Note3<Arg1, Arg2, Arg3> TRACE3 = Note3.trace();

  private static final Note0 WARN0 = Note0.warn();

  private static final Note1<Arg1> WARN1 = Note1.warn();

  private static final Note2<Arg1, Arg2> WARN2 = Note2.warn();

  private static final Note3<Arg1, Arg2, Arg3> WARN3 = Note3.warn();

  private final GrowableList<Log> logs = new GrowableList<>();

  private int notifySize;

  private Directory root;

  private StorageLogger storageLogger;

  @BeforeClass
  public void _beforeClass() throws Exception {
    root = TmpDir.create();

    TestableLogger testableLogger;
    testableLogger = new TestableLogger();

    testableLogger.setSysout(true);

    IoWorkerService ioWorker;
    ioWorker = new SingleThreadIoWorker(testableLogger);

    CpuWorkerService cpuWorker;
    cpuWorker = new FixedCpuWorker(10, 50, testableLogger);

    storageLogger = StorageLogger.create(
      root,

      ioWorker,

      cpuWorker
    );

    StorageWatcher storageWatcher;
    storageWatcher = StorageWatcher.create(
      root,

      ioWorker,

      cpuWorker,

      StorageWatcher.logListener(this)
    );

    Runtime.getRuntime().addShutdownHook(
      new Thread(
        () -> {
          try {
            storageLogger.close();
          } catch (IOException e) {
            e.printStackTrace();
          }

          try {
            storageWatcher.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      )
    );

    Watch.Service watchService;
    watchService = Watch.createService(storageWatcher);

    Services.start(
      ioWorker,

      cpuWorker,

      watchService
    );
  }

  @BeforeMethod
  public void _beforeMethod() {
    logs.clear();

    notifySize = -1;
  }

  @Override
  public final void onLog(Log log) {
    logs.add(log);

    if (logs.size() == notifySize) {
      synchronized (this) {
        notify();
      }
    }
  }

  @Test(timeOut = 5000)
  public void testCase01() throws InterruptedException {
    NoteSink logger;
    logger = storageLogger;

    Arg1 arg1;
    arg1 = new Arg1(111);

    Arg2 arg2;
    arg2 = new Arg2(222);

    Arg3 arg3;
    arg3 = new Arg3(333);

    logger.send(DEBUG0);

    logger.send(DEBUG1, arg1);

    logger.send(DEBUG2, arg1, arg2);

    logger.send(DEBUG3, arg1, arg2, arg3);

    waitLogs(4);

    logger.send(INFO0);

    logger.send(INFO1, arg1);

    logger.send(INFO2, arg1, arg2);

    logger.send(INFO3, arg1, arg2, arg3);

    waitLogs(8);

    testLog(logs.get(0), DEBUG0);

    testLog(logs.get(1), DEBUG1, arg1);

    testLog(logs.get(2), DEBUG2, arg1, arg2);

    testLog(logs.get(3), DEBUG3, arg1, arg2, arg3);

    testLog(logs.get(4), INFO0);

    testLog(logs.get(5), INFO1, arg1);

    testLog(logs.get(6), INFO2, arg1, arg2);

    testLog(logs.get(7), INFO3, arg1, arg2, arg3);
  }

  @Test(timeOut = 10000)
  public void testCase02() throws InterruptedException {
    NoteSink logger;
    logger = storageLogger;

    Ex1 ex1;
    ex1 = new Ex1("Simple, no cause, no suppressed");

    ex1.setStackTrace(randomStackTrace());

    Ex2 ex2;
    ex2 = new Ex2("With cause, no suppressed");

    ex2.setStackTrace(randomStackTrace());

    ex2.initCause(ex1);

    logger.send(ERROR1, ex1);

    logger.send(ERROR2, ex1, ex2);

    Ex3 ex3;
    ex3 = new Ex3("Suppressed (if supported)");

    ex3.setStackTrace(randomStackTrace());

    ex3.addSuppressed(ex1);

    logger.send(ERROR3, ex1, ex2, ex3);

    waitLogs(3);

    testLog(logs.get(0), ERROR1, ex1);

    testLog(logs.get(1), ERROR2, ex1, ex2);

    testLog(logs.get(2), ERROR3, ex1, ex2, ex3);
  }

  @Test
  public void testCase03() throws InterruptedException, IOException {
    BootstrapLogger bootstrapLogger;
    bootstrapLogger = new BootstrapLogger();

    NoteSink logger;
    logger = bootstrapLogger;

    Arg1 arg1;
    arg1 = new Arg1(111);

    Arg2 arg2;
    arg2 = new Arg2(222);

    Arg3 arg3;
    arg3 = new Arg3(333);

    logger.send(TRACE0);

    logger.send(WARN1, arg1);

    logger = bootstrapLogger.replace(storageLogger);

    bootstrapLogger.close();

    logger.send(WARN2, arg1, arg2);

    logger.send(WARN3, arg1, arg2, arg3);

    logger.send(WARN0);

    logger.send(TRACE1, arg1);

    logger.send(TRACE2, arg1, arg2);

    logger.send(TRACE3, arg1, arg2, arg3);

    waitLogs(8);

    testLog(logs.get(0), TRACE0);

    testLog(logs.get(1), WARN1, arg1);

    testLog(logs.get(2), WARN2, arg1, arg2);

    testLog(logs.get(3), WARN3, arg1, arg2, arg3);

    testLog(logs.get(4), WARN0);

    testLog(logs.get(5), TRACE1, arg1);

    testLog(logs.get(6), TRACE2, arg1, arg2);

    testLog(logs.get(7), TRACE3, arg1, arg2, arg3);
  }

  @Test
  public void toMediumByteArray() {
    int maxValue;
    maxValue = (1 << 24) - 1;

    byte[] bytes;
    bytes = MoreLogging.toMediumByteArray(maxValue);

    assertEquals(bytes[0], (byte) 0xFF);
    assertEquals(bytes[1], (byte) 0xFF);
    assertEquals(bytes[2], (byte) 0xFF);

    int value;
    value = MoreLogging.toMediumInt((byte) 0xFF, (byte) 0xFF, (byte) 0xFF);

    assertEquals(value, maxValue);
  }

  private StackTraceElement[] randomStackTrace() {
    int minimumLength;
    minimumLength = 3;

    int length;
    length = minimumLength + Next.intValue(256);

    StackTraceElement[] stackTrace;
    stackTrace = new StackTraceElement[length];

    for (int i = 0; i < stackTrace.length; i++) {
      String declaringClass;
      declaringClass = Next.string(20 + Next.intValue(20));

      String methodName;
      methodName = Next.string(10 + Next.intValue(10));

      String fileName;
      fileName = Next.string(10 + Next.intValue(10));

      int lineNumber;
      lineNumber = Next.intValue(128);

      StackTraceElement element;
      element = new StackTraceElement(declaringClass, methodName, fileName, lineNumber);

      stackTrace[i] = element;
    }

    return stackTrace;
  }

  private void testLog(Log log, Note0 event) {
    assertTrue(log instanceof ReadJobLog0);

    ReadJobLog0 read;
    read = (ReadJobLog0) log;

    assertTrue(read.matchesEvent(event));
    assertTrue(read.matchesThread(Thread.currentThread()));
  }

  private <T1> void testLog(Log log, Note1<T1> event, T1 value) {
    assertTrue(log instanceof ReadJobLog1);

    ReadJobLog1 read;
    read = (ReadJobLog1) log;

    assertTrue(read.matchesEvent(event));

    assertTrue(read.matchesThread(Thread.currentThread()));

    assertTrue(read.matchesValue(Logging.format(value)));

    testThrowable(read.throwable, value);
  }

  private <T1, T2> void testLog(Log log, Note2<T1, T2> event, T1 value1, T2 value2) {
    assertTrue(log instanceof ReadJobLog2);

    ReadJobLog2 read;
    read = (ReadJobLog2) log;

    assertTrue(read.matchesEvent(event));

    assertTrue(read.matchesThread(Thread.currentThread()));

    assertTrue(read.matchesValue1(Logging.format(value1)));

    assertTrue(read.matchesValue2(Logging.format(value2)));

    testThrowable(read.throwable1, value1);

    testThrowable(read.throwable2, value2);
  }

  private <T1, T2, T3> void testLog(
      Log log, Note3<T1, T2, T3> event, T1 value1, T2 value2, T3 value3) {
    assertTrue(log instanceof ReadJobLog3);

    ReadJobLog3 read;
    read = (ReadJobLog3) log;

    assertTrue(read.matchesEvent(event));

    assertTrue(read.matchesThread(Thread.currentThread()));

    assertTrue(read.matchesValue1(Logging.format(value1)));

    assertTrue(read.matchesValue2(Logging.format(value2)));

    assertTrue(read.matchesValue3(Logging.format(value3)));

    testThrowable(read.throwable1, value1);

    testThrowable(read.throwable2, value2);

    testThrowable(read.throwable3, value3);
  }

  private void testThrowable(ReadJobThrowable throwable, Object value) {
    if (throwable == null) {
      assertFalse(value instanceof Throwable);

      return;
    }

    assertTrue(value instanceof Throwable);

    Throwable that;
    that = (Throwable) value;

    Class<? extends Throwable> thatType;
    thatType = that.getClass();

    assertEquals(throwable.canonicalName, thatType.getCanonicalName());

    assertEquals(throwable.message, that.getMessage());

    assertEquals(throwable.stackTrace, that.getStackTrace());

    GrowableList<ReadJobThrowable> s;
    s = throwable.suppressed;

    if (s != null) {
      Throwable[] suppressed;
      suppressed = that.getSuppressed();

      assertEquals(s.size(), suppressed.length);

      for (int i = 0; i < s.size(); i++) {
        testThrowable(s.get(i), suppressed[i]);
      }
    }

    testThrowable(throwable.cause, that.getCause());
  }

  private void waitLogs(int expectedSize) throws InterruptedException {
    notifySize = expectedSize;

    synchronized (this) {
      wait();
    }
  }

}