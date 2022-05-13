/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.next.logging;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Set;
import objectos.logging.Event0;
import objectos.logging.NoOpLogger;
import org.testng.annotations.Test;

public class HelloWriterTest {

  @Test(enabled = false)
  public void noSync() throws IOException {
    var logger = NoOpLogger.getInstance();
    var times = 100;

    var writer = new HelloWriter(logger, times);

    writer.start();

    var lines = writer.readLines();

    assertEquals(lines.size(), times);
    assertEquals(lines.get(0), "hello!");

    var distinct = Set.copyOf(lines);

    assertEquals(distinct.size(), 1);
  }

  @Test
  public void threadSleep() throws IOException, InterruptedException {
    var logger = NoOpLogger.getInstance();
    var times = 100;

    var writer = new HelloWriter(logger, times);

    writer.start();

    Thread.sleep(1000);

    var lines = writer.readLines();

    assertEquals(lines.size(), times);
    assertEquals(lines.get(0), "hello!");

    var distinct = Set.copyOf(lines);

    assertEquals(distinct.size(), 1);
  }

  @Test
  public void usingEventListener() throws IOException, InterruptedException {
    var logger = new ThisLogger();
    var times = 100;

    var writer = new HelloWriter(logger, times);

    writer.start();

    synchronized (this) {
      wait();
    }

    var lines = writer.readLines();

    assertEquals(lines.size(), times);
    assertEquals(lines.get(0), "hello!");

    var distinct = Set.copyOf(lines);

    assertEquals(distinct.size(), 1);
  }

  private class ThisLogger extends NoOpLogger {
    @Override
    public void log(Event0 event) {
      if (event == HelloWriter.DONE) {
        var mutex = HelloWriterTest.this;

        synchronized (mutex) {
          mutex.notify();
        }
      }
    }
  }

}