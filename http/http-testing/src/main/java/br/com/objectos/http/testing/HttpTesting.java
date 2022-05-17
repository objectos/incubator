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
package br.com.objectos.http.testing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import objectos.lang.Checks;
import java.util.Set;

public class HttpTesting {

  private final int port;

  private HttpTesting(int port) {
    this.port = port;
  }

  public static HttpTesting at(int port) {
    return new HttpTesting(port);
  }

  public RequestBuilderDsl get(String url) {
    return new GetRequest(url);
  }

  public String httpLocalhost(String url) {
    return "http://localhost:" + port + url;
  }

  public RequestBuilderDsl post(String url) {
    return new PostRequest(url);
  }

  public String readResponse(String url) {
    return new GetRequest(url).execute().toString();
  }

  public interface RequestBuilderDsl {
    Response execute();

    RequestBuilderDsl header(String key, String value);

    RequestBuilderDsl param(String key, Object value);
  }

  public interface Response {}

  private static class ByteArrayResponse implements Response {
    private final byte[] value;

    ByteArrayResponse(byte[] value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return new String(value);
    }
  }

  private static class ExceptionResponse implements Response {
    private final Exception e;

    ExceptionResponse(Exception e) {
      this.e = e;
    }

    @Override
    public String toString() {
      return e.toString();
    }
  }

  private class GetRequest extends Request {
    GetRequest(String url) {
      super(url);
    }

    @Override
    Response executeUrl(URL url, String params) {
      try {
        return readAndRespond(url.openConnection());
      } catch (IOException e) {
        return new ExceptionResponse(e);
      }
    }

    @Override
    String theUrl(String url, String params) {
      return params.isEmpty()
          ? url
          : url + "?" + params;
    }
  }

  private class PostRequest extends Request {
    PostRequest(String url) {
      super(url);
    }

    @Override
    Response executeUrl(URL url, String params) {
      HttpURLConnection conn = null;
      try {
        byte[] bytes = params.getBytes("UTF-8");
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(bytes.length));
        if (!params.isEmpty()) {
          try (OutputStream os = conn.getOutputStream()) {
            os.write(bytes);
          }
        }
        return readAndRespond(conn);
      } catch (IOException e) {
        return new ExceptionResponse(e);
      } finally {
        if (conn != null) {
          conn.disconnect();
        }
      }
    }
  }

  private abstract class Request implements RequestBuilderDsl {
    private final Map<String, List<String>> header = new LinkedHashMap<>();
    private final StringBuilder params = new StringBuilder();
    private final String url;

    Request(String url) {
      this.url = Checks.checkNotNull(url, "url == null");
    }

    @Override
    public final Response execute() {
      try {
        String paramString = params.toString();
        String theUrl = theUrl(url, paramString);
        return executeUrl(new URL(httpLocalhost(theUrl)), paramString);
      } catch (MalformedURLException e) {
        return new ExceptionResponse(e);
      }
    }

    @Override
    public final RequestBuilderDsl header(String key, String value) {
      List<String> list = header.computeIfAbsent(key, k -> new ArrayList<>());
      list.add(value);
      return this;
    }

    @Override
    public final RequestBuilderDsl param(String key, Object value) {
      try {
        onParam(URLEncoder.encode(key, "UTF-8"), URLEncoder.encode(value.toString(), "UTF-8"));
        return this;
      } catch (UnsupportedEncodingException e) {
        throw new AssertionError(e);
      }
    }

    abstract Response executeUrl(URL url, String params);

    Response readAndRespond(URLConnection conn) {
      Set<Entry<String, List<String>>> entrySet = header.entrySet();
      for (Entry<String, List<String>> entry : entrySet) {
        String key = entry.getKey();
        List<String> list = entry.getValue();
        for (String value : list) {
          conn.addRequestProperty(key, value);
        }
      }

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      try (InputStream in = conn.getInputStream()) {
        int c = 0;
        while ((c = in.read()) != -1) {
          out.write(c);
        }
        return new ByteArrayResponse(out.toByteArray());
      } catch (IOException e) {
        return new ExceptionResponse(e);
      }
    }

    String theUrl(String url, String params) {
      return url;
    }

    private void onParam(String key, String value) {
      if (params.length() != 0) {
        params.append('&');
      }
      params.append(key).append('=').append(value);
    }
  }

}
