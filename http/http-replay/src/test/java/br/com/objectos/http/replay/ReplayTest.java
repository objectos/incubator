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
package br.com.objectos.http.replay;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import br.com.objectos.core.io.Resource;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.system.LineSeparator;
import br.com.objectos.core.throwable.Try;
import br.com.objectos.http.Method;
import br.com.objectos.http.ProtocolException;
import br.com.objectos.http.Version;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ReplayTest extends AbstractReplayTest {

  @AfterMethod(alwaysRun = true)
  public void afterMethod() throws IOException {
    Throwable rethrow;
    rethrow = Try.begin();

    rethrow = Try.close(rethrow, socketChannel);

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  @Test
  public void testCase01() throws ProtocolException, IOException {
    Resource resource;
    resource = Resource.getResource("TEST-INF/test-case-01.zip");

    service.replay(resource);

    parseRequest();

    assertNull(parsedCookie);
    assertEquals(parsedMethod, Method.GET);
    assertEquals(parsedTarget, "/test/");
    assertEquals(parsedVersion, Version.V1_0);

    writeResponse(
        "HTTP/1.0 302 Found",
        "Location: /test/login",
        "",
        ""
    );

    sleep(100);

    assertEquals(
        log,
        ImmutableList.of(
            "000000000001=OK"
        )
    );
  }

  @Test
  public void testCase02() throws ProtocolException, IOException {
    Resource resource;
    resource = Resource.getResource("TEST-INF/test-case-02.zip");

    service.replay(resource);

    sleep(100);

    parseRequest();

    assertNull(parsedCookie);
    assertEquals(parsedMethod, Method.GET);
    assertEquals(parsedTarget, "/test/");
    assertEquals(parsedVersion, Version.V1_0);

    writeResponse(
        "HTTP/1.0 302 Found",
        "Location: /test/login",
        "",
        ""
    );

    sleep(100);

    assertEquals(
        log,
        ImmutableList.of(
            "000000000001=OK"
        )
    );

    parseRequest();

    assertNull(parsedCookie);
    assertEquals(parsedMethod, Method.GET);
    assertEquals(parsedTarget, "/test/login");
    assertEquals(parsedVersion, Version.V1_0);

    writeResponse(
        "HTTP/1.0 200 OK",
        "Content-Type: text/html; charset=utf8",
        "Content-Length: 82",
        "Set-Cookie: JSESSIONID=original",
        "",
        LineSeparator.join(
            "<!doctype html>",
            "<html>",
            "<head>",
            "<title>Login</title>",
            "</head>",
            "<body>",
            "</body>",
            "</html>",
            ""
        )
    );

    sleep(200);

    assertEquals(
        log,
        ImmutableList.of(
            "000000000001=OK",
            "000000000002=OK"
        )
    );
  }

  @Test
  public void testCase03() throws ProtocolException, IOException {
    Resource resource;
    resource = Resource.getResource("TEST-INF/test-case-03.zip");

    service.replay(resource);

    sleep(100);

    parseRequest();

    assertNull(parsedCookie);
    assertEquals(parsedMethod, Method.POST);
    assertEquals(parsedTarget, "/test/api/login");
    assertEquals(parsedVersion, Version.V1_0);

    writeResponse(
        "HTTP/1.0 200 OK",
        "Content-Type: text/json",
        "Content-Length: 35",
        "",
        "{\"prop1\":\"res001\",\"prop2\":\"res002\"}"
    );

    sleep(100);

    assertEquals(
        log,
        ImmutableList.of(
            "000000000001=OK"
        )
    );
  }

  @Test
  public void testCase04() throws ProtocolException, IOException {
    Resource resource;
    resource = Resource.getResource("TEST-INF/test-case-04.zip");

    service.replay(resource);

    sleep(100);

    parseRequest();

    assertNull(parsedCookie);
    assertEquals(parsedMethod, Method.GET);
    assertEquals(parsedTarget, "/test/login");
    assertEquals(parsedVersion, Version.V1_0);

    writeResponse(
        "HTTP/1.0 200 OK",
        "Content-Type: text/html; charset=utf8",
        "Content-Length: 82",
        "Set-Cookie: JSESSIONID=expected",
        "",
        LineSeparator.join(
            "<!doctype html>",
            "<html>",
            "<head>",
            "<title>Login</title>",
            "</head>",
            "<body>",
            "</body>",
            "</html>",
            ""
        )
    );

    sleep(100);

    assertEquals(
        log,
        ImmutableList.of(
            "000000000001=OK"
        )
    );

    parseRequest();

    assertNotNull(parsedCookie);
    assertEquals(parsedCookie.getCookieName(), "JSESSIONID");
    assertEquals(parsedCookie.getCookieValue(), "expected");
    assertEquals(parsedMethod, Method.POST);
    assertEquals(parsedTarget, "/test/api/login");
    assertEquals(parsedVersion, Version.V1_0);

    writeResponse(
        "HTTP/1.0 200 OK",
        "Content-Type: text/json",
        "Content-Length: 35",
        "",
        "{\"prop1\":\"res001\",\"prop2\":\"res002\"}"
    );

    sleep(100);

    assertEquals(
        log,
        ImmutableList.of(
            "000000000001=OK",
            "000000000002=OK"
        )
    );
  }

}