/*
 * Copyright (C) 2021 Objectos Software LTDA.
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
package br.com.objectos.more.logging.slf4j;

import static org.testng.Assert.assertEquals;

import br.com.objectos.concurrent.CpuWorkerService;
import br.com.objectos.concurrent.FixedCpuWorker;
import br.com.objectos.concurrent.IoWorkerService;
import br.com.objectos.concurrent.SingleThreadIoWorker;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.logging.testing.TestableLogger;
import br.com.objectos.core.service.Services;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.logger.Log;
import br.com.objectos.logger.LogListener;
import br.com.objectos.logger.StorageLogger;
import java.io.IOException;
import objectos.lang.Level;
import objectos.lang.ToString;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MoreLoggingAndSlf4jTest implements LogListener {

  private final MutableList<Log> logs = MutableList.create();

  private int notifySize;

  private Directory root;

  private StorageLogger storageLogger;

  private TestableLogger testableLogger;

  @BeforeClass
  public void _setUp() throws Exception {
    root = TmpDir.create();

    testableLogger = new TestableLogger();

    testableLogger.setSysout(true);

    IoWorkerService ioWorker;
    ioWorker = new SingleThreadIoWorker(testableLogger);

    CpuWorkerService cpuWorker;
    cpuWorker = new FixedCpuWorker(4, 10, testableLogger);

    storageLogger = StorageLogger.create(
      root,

      ioWorker,

      cpuWorker,

      StorageLogger.logListener(this)
    );

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public final void run() {
        try {
          storageLogger.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    MoreLoggingAndSlf4j.bootstrap(storageLogger);

    Services.start(
      ioWorker,

      cpuWorker
    );
  }

  @BeforeMethod
  public void _setUpMethod() {
    logs.clear();
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

  @Test(timeOut = 3000)
  public void testCase01() throws InterruptedException, IOException {
    Class<? extends MoreLoggingAndSlf4jTest> s;
    s = getClass();

    String source;
    source = s.getCanonicalName();

    org.slf4j.Logger slf4j;
    slf4j = LoggerFactory.getLogger(source);

    slf4j.trace("trace test");

    slf4j.debug("debug test {}", 1);

    slf4j.info("info test {} {}", 2, 3);

    slf4j.warn("warn {} {} {}", "test", "A", "B");

    try {
      throwIoException(Level.ERROR);
    } catch (IOException e) {
      slf4j.error("error test", e);
    }

    waitLogs(5);

    assertEquals(logs.size(), 5, logs.toString());

    int index;
    index = 0;

    Log log;
    log = logs.get(index++);

    assertEquals(
      log.toString(),
      ToString.toString(
        "Slf4jLog1",
        "", "SLF4J",
        "", Level.TRACE,
        "", source,
        "", "trace test"
      )
    );

    log = logs.get(index++);

    assertEquals(
      log.toString(),
      ToString.toString(
        "Slf4jLog1",
        "", "SLF4J",
        "", Level.DEBUG,
        "", source,
        "", "debug test 1"
      )
    );

    log = logs.get(index++);

    assertEquals(
      log.toString(),
      ToString.toString(
        "Slf4jLog1",
        "", "SLF4J",
        "", Level.INFO,
        "", source,
        "", "info test 2 3"
      )
    );

    log = logs.get(index++);

    assertEquals(
      log.toString(),
      ToString.toString(
        "Slf4jLog1",
        "", "SLF4J",
        "", Level.WARN,
        "", source,
        "", "warn test A B"
      )
    );

    log = logs.get(index++);

    assertEquals(
      log.toString(),
      ToString.toString(
        "Slf4jLog2",
        "", "SLF4J",
        "", Level.ERROR,
        "", source,
        "", "error test",
        "", "java.io.IOException: ERROR"
      )
    );
  }

  private void throwIoException(Level l) throws IOException {
    throw new IOException(l.name());
  }

  private void waitLogs(int count) throws InterruptedException {
    notifySize = count;

    synchronized (this) {
      wait();
    }
  }

}
