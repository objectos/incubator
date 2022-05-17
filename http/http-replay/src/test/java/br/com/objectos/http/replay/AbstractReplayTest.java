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

import br.com.objectos.concurrent.Concurrent;
import br.com.objectos.concurrent.DirectIoWorker;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.map.Maps;
import br.com.objectos.core.net.InetAddresses;
import br.com.objectos.http.Body;
import br.com.objectos.http.Body.Text;
import br.com.objectos.http.Header;
import br.com.objectos.http.Header.ContentType;
import br.com.objectos.http.Header.Cookie;
import br.com.objectos.http.Header.SetCookie;
import br.com.objectos.http.Http;
import br.com.objectos.http.Method;
import br.com.objectos.http.ProtocolException;
import br.com.objectos.http.Request;
import br.com.objectos.http.RequestHeader;
import br.com.objectos.http.RequestParser;
import br.com.objectos.http.Response;
import br.com.objectos.http.SimpleRequestVisitor;
import br.com.objectos.http.Status;
import br.com.objectos.http.Version;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import objectos.lang.Try;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractReplayTest {

  final MutableList<String> log = MutableList.create();

  Header.Cookie parsedCookie;

  Method parsedMethod;

  String parsedTarget;

  Version parsedVersion;

  ReplayService service;

  SocketChannel socketChannel;

  private Request parsedRequest;

  private RequestParser requestParser;

  private ThisRequestVisitor requestVisitor;

  private ServerSocketChannel serverSocketChannel;

  private final Map<String, String> sessionMap = Maps.newHashMap();

  private InetSocketAddress socketAddress;

  @BeforeClass
  public final void _setUp() throws IOException {
    ByteBuffer byteBuffer;
    byteBuffer = ByteBuffer.allocate(1024);

    CharBuffer charBuffer;
    charBuffer = CharBuffer.allocate(1024);

    IoWorker ioWorker;
    ioWorker = DirectIoWorker.get();

    requestParser = Http.createRequestParser(byteBuffer, charBuffer, ioWorker);

    requestVisitor = new ThisRequestVisitor();

    serverSocketChannel = ServerSocketChannel.open();

    InetAddress loopback;
    loopback = InetAddresses.getLoopbackAddress();

    socketAddress = new InetSocketAddress(loopback, 7124);

    serverSocketChannel.bind(socketAddress);

    serverSocketChannel.configureBlocking(false);

    ThisReplayAdapter adapter;
    adapter = new ThisReplayAdapter();

    service = Replay.createService(adapter, ioWorker, socketAddress);

    service.start();
  }

  @BeforeMethod
  public final void _setUpTest() {
    log.clear();

    parsedCookie = null;

    sessionMap.clear();

    socketChannel = null;
  }

  @AfterClass(alwaysRun = true)
  public final void _tearDown() throws IOException {
    Throwable rethrow;
    rethrow = Try.begin();

    rethrow = Try.close(rethrow, service);

    rethrow = Try.close(rethrow, serverSocketChannel);

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

  final void parseRequest() throws IOException, ProtocolException {
    parsedMethod = null;

    parsedRequest = null;

    parsedTarget = null;

    parsedVersion = null;

    if (socketChannel == null) {
      socketChannel = serverSocketChannel.accept();

      while (socketChannel == null) {
        socketChannel = serverSocketChannel.accept();
      }

      socketChannel.configureBlocking(false);
    }

    requestParser.setInput(socketChannel);

    Concurrent.exhaust(requestParser);

    parsedRequest = requestParser.getResult();

    parsedRequest.acceptRequestVisitor(requestVisitor);
  }

  final void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      AssertionError error;
      error = new AssertionError();

      error.initCause(e);

      throw error;
    }
  }

  final void writeResponse(String... lines) throws IOException {
    ImmutableList<String> list;
    list = ImmutableList.copyOf(lines);

    String string;
    string = list.join("\r\n");

    byte[] bytes;
    bytes = string.getBytes(Charsets.usAscii());

    ByteBuffer buffer;
    buffer = ByteBuffer.wrap(bytes);

    while (buffer.hasRemaining()) {
      socketChannel.write(buffer);
    }
  }

  private class ThisReplayAdapter extends SimpleRequestVisitor implements ReplayAdapter {

    boolean cookieJar;

    String cookieKey;

    private ThisResponseVisitor currentResponseVisitor;

    private String key;

    private final ThisResponseRedirectVisitor redirect = new ThisResponseRedirectVisitor(this);

    private ReplayRequestWriter requestWriter;

    private final ThisResponseTextVisitor text = new ThisResponseTextVisitor(this);

    @Override
    public final void begin(String prefix) {
      cookieJar = false;

      cookieKey = null;

      currentResponseVisitor = null;

      key = prefix;

      requestWriter = null;
    }

    @Override
    public final void expectRedirect(String location) {
      currentResponseVisitor = redirect.set(location);
    }

    @Override
    public final void expectTextResponse(ContentType contentType, String body) {
      currentResponseVisitor = text.set(contentType, body);
    }

    @Override
    public final void test(Response actualResponse) {
      actualResponse.acceptResponseVisitor(currentResponseVisitor);

      ReplayResult result;
      result = currentResponseVisitor.getResult();

      switch (result) {
        case SUCCESS:
          log.add(key + "=OK");
          break;
        default:
          throw new UnsupportedOperationException("Implement me @ " + result);
      }
    }

    @Override
    public final void visitRequestHeader(Cookie cookie) {
      String cookieName;
      cookieName = cookie.getCookieName();

      if (!cookieName.equals("JSESSIONID")) {
        requestWriter.writeHeader(cookie);

        return;
      }

      cookieKey = cookie.getCookieValue();

      if (cookieJar) {
        return;
      }

      String mappedValue;
      mappedValue = sessionMap.get(cookieKey);

      if (mappedValue != null) {
        Cookie remapped;
        remapped = cookie.withCookieValue(mappedValue);

        requestWriter.writeHeader(remapped);

        return;
      }

      // do not send cookie
    }

    @Override
    public final void visitRequestLine(Method method, String target, Version version) {
      requestWriter.writeRequestLine(method, target, version);

      if (target.equals("/test/login")) {
        cookieJar = true;
      } else {
        cookieJar = false;
      }
    }

    @Override
    public final void writeRequest(ReplayRequestWriter writer, Request request) {
      requestWriter = writer;

      request.acceptRequestVisitor(this);
    }

    @Override
    protected final void defaultRequestBodyAction(Body body) {
      requestWriter.writeBody(body);
    }

    @Override
    protected final void defaultRequestHeaderAction(RequestHeader header) {
      requestWriter.writeHeader(header);
    }

  }

  private class ThisRequestVisitor extends SimpleRequestVisitor {
    @Override
    public final void visitRequestHeader(Cookie cookie) {
      parsedCookie = cookie;
    }

    @Override
    public final void visitRequestLine(Method method, String target, Version version) {
      parsedMethod = method;

      parsedTarget = target;

      parsedVersion = version;
    }
  }

  private class ThisResponseRedirectVisitor extends ThisResponseVisitor {

    private String location;

    ThisResponseRedirectVisitor(ThisReplayAdapter adapter) {
      super(adapter);
    }

    @Override
    public final void visitResponseHeader(Header.Location field) {
      String value;
      value = field.getHeaderValue();

      if (!location.equals(value)) {
        fail();
      } else {
        success();
      }
    }

    @Override
    protected final void visitResponseStatusLineImpl(
        Version version, Status status, String reason) {
      if (status != Status.FOUND) {
        fail();
      }
    }

    final ThisResponseRedirectVisitor set(String location) {
      this.location = location;

      return this;
    }

  }

  private class ThisResponseTextVisitor extends ThisResponseVisitor {

    private String body;

    private ContentType contentType;

    ThisResponseTextVisitor(ThisReplayAdapter adapter) {
      super(adapter);
    }

    @Override
    public final void visitResponseBody(Text text) {
      String contents;
      contents = text.getContents();

      if (!body.equals(contents)) {
        fail();
      } else {
        success();
      }
    }

    @Override
    public final void visitResponseHeader(ContentType thatContentType) {
      if (!contentType.equals(thatContentType)) {
        fail();
      }
    }

    final ThisResponseTextVisitor set(ContentType contentType, String body) {
      this.body = body;

      this.contentType = contentType;

      return this;
    }

  }

  private abstract class ThisResponseVisitor extends AbstractActualResponseVisitor {

    final ThisReplayAdapter adapter;

    ThisResponseVisitor(ThisReplayAdapter adapter) {
      this.adapter = adapter;
    }

    @Override
    public final void visitResponseHeader(SetCookie cookie) {
      super.visitResponseHeader(cookie);

      if (!adapter.cookieJar) {
        return;
      }

      String cookieName;
      cookieName = cookie.getCookieName();

      if (!cookieName.equals("JSESSIONID")) {
        return;
      }

      String cookieValue;
      cookieValue = cookie.getCookieValue();

      sessionMap.put(adapter.cookieKey, cookieValue);
    }

  }

}
