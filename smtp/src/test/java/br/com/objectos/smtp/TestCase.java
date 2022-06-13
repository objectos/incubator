/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.smtp;

import java.nio.charset.Charset;
import objectos.util.ImmutableList;

public final class TestCase {

  public static final TestCase TC00 = testCase(
    body(
      "Date: Wed, 21 Oct 2020 02:36:23 -0300 (BRT)",
      "From: tc00-sender@example.com",
      "To: tc00-receiver@example.com",
      "Message-ID: <1176164144.0.1603258583357@localhost>",
      "Subject: Test case 00",
      "MIME-Version: 1.0",
      "Content-Type: text/plain; charset=us-ascii",
      "Content-Transfer-Encoding: 7bit",
      "",
      "1) no SSL / no TLS",
      "2) single recipient (TO:)",
      "3) text only",
      "4) ?",
      ".",
      ""
    )
  );

  private final String body;

  private TestCase(Builder builder) {
    body = builder.body;
  }

  private static Element body(final String... lines) {
    return new Element() {
      @Override
      public final void accept(Builder builder) {
        ImmutableList<String> list;
        list = ImmutableList.copyOf(lines);

        String body;
        body = list.join("\r\n");

        builder.setBody(body);
      }
    };
  }

  private static TestCase testCase(Element... elements) {
    Builder builder;
    builder = new Builder();

    for (Element e : elements) {
      e.accept(builder);
    }

    return new TestCase(builder);
  }

  public final String getBody() {
    return body;
  }

  public final byte[] getBodyBytes(Charset charset) {
    return body.getBytes(charset);
  }

  private static class Builder {

    private String body;

    final void setBody(String body) {
      this.body = body;
    }

  }

  private interface Element {

    void accept(Builder builder);

  }

}
