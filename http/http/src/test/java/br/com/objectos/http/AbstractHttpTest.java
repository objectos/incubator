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

import br.com.objectos.concurrent.DirectIoWorker;
import br.com.objectos.concurrent.FixedCpuArray;
import br.com.objectos.concurrent.SingleThreadIoWorker;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Copy;
import br.com.objectos.core.net.InetAddresses;
import br.com.objectos.core.service.Services;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TestInf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicInteger;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractHttpTest {

  protected static HttpEngine engine;

  protected static HttpTestingLogger logger;

  protected static HttpService service;

  protected static HashMapStringDeduplicator stringDeduplicator;

  private static AtomicInteger portGenerator = new AtomicInteger(5678);

  @BeforeSuite
  public static void beforeSuiteGitService() throws Exception {
    Directory testInf;
    testInf = TestInf.get();

    Directory siteDirectory;
    siteDirectory = testInf.getDirectory("site");

    HttpRequestProcessorProvider provider;
    provider = new HttpRequestProcessorProvider(siteDirectory);

    stringDeduplicator = new HashMapStringDeduplicator();

    HttpEngineBuilder engineBuilder;
    engineBuilder = new HttpEngineBuilder(
      DirectIoWorker.get(),

      provider.create(),

      stringDeduplicator
    );

    engineBuilder.setBufferSize(64);

    logger = new HttpTestingLogger();

    engineBuilder.setLogger(logger);

    engine = engineBuilder.build();

    InetSocketAddress address;
    address = nextLoopbackSocketAddress();

    FixedCpuArray cpuArray;
    cpuArray = new FixedCpuArray(10, 50, logger);

    SingleThreadIoWorker ioWorker;
    ioWorker = new SingleThreadIoWorker(logger);

    service = HttpService.create(
      address,

      cpuArray,

      ioWorker,

      provider,

      HttpService.bufferSize(64),

      HttpService.enginesPerWorker(2),

      HttpService.logger(logger)
    );

    Services.start(
      cpuArray,

      ioWorker,

      service
    );
  }

  protected static InetSocketAddress nextLoopbackSocketAddress() {
    InetAddress loopback;
    loopback = InetAddresses.getLoopbackAddress();

    int port;
    port = portGenerator.getAndIncrement();

    return new InetSocketAddress(loopback, port);
  }

  protected final URLConnection GET(String path) {
    try {
      URL url;
      url = new URL("http", "127.0.0.1", 5678, path);

      return url.openConnection();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected final String readString(URLConnection c) throws IOException {
    try (var in = c.getInputStream(); var out = new ByteArrayOutputStream()) {
      Copy.streams(in, out, new byte[1024]);

      byte[] bytes;
      bytes = out.toByteArray();

      return new String(bytes, Charsets.utf8());
    }
  }

}