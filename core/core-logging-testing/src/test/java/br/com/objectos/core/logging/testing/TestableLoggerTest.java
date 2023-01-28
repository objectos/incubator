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
package br.com.objectos.core.logging.testing;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import objectos.lang.Note0;
import objectos.lang.Note1;
import objectos.lang.Note2;
import objectos.lang.Note3;
import org.testng.annotations.Test;

public class TestableLoggerTest {

  @Test
  public void createTestableLogger() {
    TestableLogger logger;
    logger = new TestableLogger();

    Note0 e0;
    e0 = Note0.info();

    assertFalse(logger.contains(e0));

    logger.send(e0);

    assertTrue(logger.contains(e0));

    Note1<IOException> e1;
    e1 = Note1.info();

    assertFalse(logger.contains(e1));

    IOException ioException;
    ioException = new IOException();

    logger.send(e1, ioException);

    assertTrue(logger.contains(e1));

    Event1Log<IOException> log1;
    log1 = logger.getFirst(e1);

    assertEquals(log1.getEvent(), e1);

    assertSame(log1.getValue(), ioException);

    Note2<Integer, Double> e2;
    e2 = Note2.info();

    assertFalse(logger.contains(e2));

    logger.send(e2, Integer.valueOf(123), Double.valueOf(2.34));

    assertTrue(logger.contains(e2));

    Note3<String, String, String> e3;
    e3 = Note3.info();

    assertFalse(logger.contains(e3));

    logger.send(e3, "A", "B", "C");

    assertTrue(logger.contains(e3));
  }

}