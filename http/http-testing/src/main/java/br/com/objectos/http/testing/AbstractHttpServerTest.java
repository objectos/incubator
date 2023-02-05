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
package br.com.objectos.http.testing;

import static org.testng.Assert.assertEquals;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Copy;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.http.media.TextType;
import br.com.objectos.http.path.Location;
import br.com.objectos.http.server.AbstractHttpModule;
import br.com.objectos.http.server.ActionInvocation;
import br.com.objectos.http.server.HtmlResponse;
import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpModuleDsl;
import br.com.objectos.http.server.MutableHttpServer;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.RequestHeaders;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.SimpleDirectoryAction;
import br.com.objectos.http.server.TextResponse;
import br.com.objectos.http.testing.HttpTesting.RequestBuilderDsl;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class AbstractHttpServerTest implements HttpModule {

  private MutableHttpServer server;
  private HttpTesting testing;
  private Directory textDir;

  @Override
  public final void acceptHttpModuleDsl(HttpModuleDsl dsl) {
    ExecuteModule execute = new ExecuteModule();
    dsl.install(new FilteringModule(execute));

    MainModule main = new MainModule();
    dsl.install(main);
  }

  @Test
  public final void directoryHelloWorld() throws IOException {
    String res = readResponse("/directory/hello-world.txt");
    assertEquals(res, readToString("htdocs/hello-world.txt"));
  }

  @Test
  public final void execute() throws IOException {
    String res = readResponse("/execute");
    assertEquals(res, "execute!");
  }

  @Test
  public final void executeCatchAll() throws IOException {
    String res = readResponse("/method/get");
    assertEquals(res, "method/get");
  }

  @Test
  public final void headerRequestContains() throws IOException {
    assertEquals(get("/header/request/contains/Single")
        .header("Single", "http://dummy")
        .execute().toString(), "contains(Single)=true");
    assertEquals(get("/header/request/contains/Single")
        .execute().toString(), "contains(Single)=false");
  }

  @Test
  public final void headerRequestGet() throws IOException {
    assertEquals(get("/header/request/get/Single")
        .header("Single", "http://dummy")
        .execute().toString(), "get(Single)=http://dummy");
    assertEquals(get("/header/request/get/Single")
        .execute().toString(), "NoSuchElementException(Single)");
  }

  @Test
  public final void helloWorld() throws IOException {
    assertEquals(get("/hello/World").execute().toString(), "Hello World!");
  }

  @Test
  public final void helloWorldWithFilter() throws IOException {
    assertEquals(get("/hello/World").header("X-Set", "Yo!").execute().toString(), "Yo!");
  }

  @Test
  public final void htmlText() throws IOException {
    String res = readResponse("/html/text");
    assertEquals(res, "<h1>Hello world!</h1>");
  }

  @Test
  public final void postSimple() throws IOException {
    String res = post("/post/simple").execute().toString();
    assertEquals(res, "simple ok");
  }

  @Test
  public final void postWithParam() throws IOException {
    String res = post("/post/params")
        .param("a", "3")
        .param("b", "2")
        .execute()
        .toString();
    assertEquals(res, "6");
  }

  @Test
  public final void reconfigure() throws IOException {
    assertEquals(readResponse("/reload/fixed"), "fixed");
    assertEquals(readResponse("/reload/test"), "test");
    server.reconfigure(new ReconfigureModule());
    assertEquals(readResponse("/reload/test"), "reloaded!");
    server.reconfigure(this);
    assertEquals(readResponse("/reload/test"), "test");
  }

  @Test
  public void requestUrl() throws IOException {
    String res = readResponse("/request/url");
    assertEquals(res, testing.httpLocalhost("/request/url"));
  }

  @BeforeClass
  public final void setUp() throws IOException {
    textDir = TmpDir.create();
    copy(textDir, "hello-world.txt");
    server = newServerWith(this);
    server.start();
    testing = HttpTesting.at(port());
  }

  @AfterClass(alwaysRun = true)
  public final void tearDown() {
    if (server != null) {
      server.stop();
    }
  }

  protected abstract MutableHttpServer newServerWith(HttpModule module);

  protected abstract int port();

  protected final String readResponse(String url) throws IOException {
    return testing.readResponse(url);
  }

  protected final void reload(HttpModule module) {
    server.reconfigure(module);
  }

  private void copy(Directory dir, String resourceName) {
    try {
      Resource source;
      source = Resource.getResource("htdocs/" + resourceName);

      RegularFile file;
      file = dir.createRegularFile(resourceName);

      Copy.sources(source, file);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  private RequestBuilderDsl get(String url) {
    return testing.get(url);
  }

  private RequestBuilderDsl post(String url) {
    return testing.post(url);
  }

  private String readToString(String filename) {
    try {
      Resource resource = Resource.getResource(filename);

      return Read.string(resource, Charsets.utf8());
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  private class ExecuteModule extends AbstractHttpModule {
    @Override
    protected final void configure() {
      route(
          Location.builder().fixed("execute").build(),
          get(this::execute)
      );

      route(
          Location.builder().fixed("hello").stringParam().build(),
          get(this::hello)
      );
    }

    private Response execute(Request request) {
      return new TextResponse("execute!");
    }

    private Response hello(Request request) {
      String name = request.getString(0);
      return new TextResponse("Hello " + name + "!");
    }
  }

  private class FilteringModule implements HttpModule {
    private final ExecuteModule module;

    FilteringModule(ExecuteModule module) {
      this.module = module;
    }

    @Override
    public final void acceptHttpModuleDsl(HttpModuleDsl dsl) {
      dsl.installAndIntercept(module, this::addHeaderIfRequired);
    }

    private Response addHeaderIfRequired(ActionInvocation invocation, Request request)
        throws HttpException {
      Response response = invocation.proceed(request);

      RequestHeaders headers = request.headers();
      return !headers.contains("X-Set")
          ? response
          : w -> w
              .sayOk()
              .contentType(TextType.PLAIN)
              .messageBody(headers.get("X-Set"))
              .write();
    }
  }

  private class MainModule extends AbstractHttpModule {
    @Override
    protected final void configure() {
      route(
          Location.builder()
              .fixed("directory").catchAll()
              .build(),
          get(new SimpleDirectoryAction(textDir))
      );

      route(
          Location.builder()
              .fixed("header").fixed("request").fixed("contains").stringParam()
              .build(),
          get(this::headerRequestContains)
      );

      route(
          Location.builder()
              .fixed("header").fixed("request").fixed("get").stringParam()
              .build(),
          get(this::headerRequestGet)
      );

      route(
          Location.builder().fixed("html").fixed("text").build(),
          get(this::htmlText)
      );

      route(
          Location.builder().fixed("post").fixed("simple").build(),
          post(this::postSimple)
      );

      route(
          Location.builder().fixed("post").fixed("params").build(),
          post(this::postParams)
      );

      route(
          Location.builder().fixed("reload").fixed("fixed").build(),
          get(new TextHttpAction("fixed"))
      );

      route(
          Location.builder().fixed("reload").fixed("test").build(),
          get(new TextHttpAction("test"))
      );

      route(
          Location.builder().fixed("request").fixed("url").build(),
          get(this::requestUrl)
      );

      route(
          Location.builder().catchAll().build(),
          get(this::catchAll)
      );
    }

    private Response catchAll(Request request) {
      return new TextResponse(request.getString(0));
    }

    private Response headerRequestContains(Request request) {
      RequestHeaders headers = request.headers();
      String name = request.getString(0);
      return new TextResponse(new StringBuilder()
          .append("contains(").append(name)
          .append(")=").append(headers.contains(name))
          .toString());
    }

    private Response headerRequestGet(Request request) {
      RequestHeaders headers = request.headers();
      String name = request.getString(0);
      try {
        return new TextResponse(new StringBuilder()
            .append("get(").append(name)
            .append(")=").append(headers.get(name))
            .toString());
      } catch (NoSuchElementException e) {
        return new TextResponse("NoSuchElementException(" + name + ")");
      }
    }

    private Response htmlText(Request request) {
      return new HtmlResponse("<h1>Hello world!</h1>");
    }

    private Response postParams(Request request) {
      System.out.println("postParams");
      int a = request.paramInt("a");
      int b = request.paramInt("b");
      return new TextResponse(Integer.toString(a * b));
    }

    private Response postSimple(Request request) {
      return new TextResponse("simple ok");
    }

    private Response requestUrl(Request request) {
      String url = request.requestUrl();
      return new TextResponse(url);
    }
  }

  private static class ReconfigureModule extends AbstractHttpModule {
    @Override
    protected final void configure() {
      route(
          Location.builder().fixed("reload").fixed("test").build(),
          get(new TextHttpAction("reloaded!"))
      );
    }
  }

  private static class TextHttpAction implements HttpAction {

    private final String value;

    public TextHttpAction(String value) {
      this.value = value;
    }

    @Override
    public final Response execute(Request request) throws HttpException {
      return new TextResponse(value);
    }

  }

}