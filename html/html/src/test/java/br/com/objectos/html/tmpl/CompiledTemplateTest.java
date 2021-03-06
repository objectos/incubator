/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.tmpl;

import static org.testng.Assert.assertEquals;

import br.com.objectos.html.ex.TestCase00;
import br.com.objectos.html.ex.TestCase01;
import br.com.objectos.html.ex.TestCase02;
import br.com.objectos.html.ex.TestCase03;
import br.com.objectos.html.ex.TestCase04;
import br.com.objectos.html.ex.TestCase05;
import br.com.objectos.html.ex.TestCase06;
import br.com.objectos.html.ex.TestCase07;
import br.com.objectos.html.ex.TestCase08;
import br.com.objectos.html.ex.TestCase09;
import br.com.objectos.html.ex.TestCase10;
import br.com.objectos.html.ex.TestCase11;
import br.com.objectos.html.ex.TestCase12;
import br.com.objectos.html.ex.TestCase13;
import br.com.objectos.html.ex.TestCase14;
import br.com.objectos.html.ex.TestCase15;
import br.com.objectos.html.ex.TestCase16;
import br.com.objectos.html.ex.TestCase17;
import br.com.objectos.html.ex.TestCase18;
import br.com.objectos.html.ex.TestCase19;
import br.com.objectos.html.ex.TestCase20;
import br.com.objectos.html.ex.TestCase21;
import br.com.objectos.html.ex.TestCase22;
import br.com.objectos.html.ex.TestCase23;
import br.com.objectos.html.ex.TestCase24;
import br.com.objectos.html.ex.TestCase25;
import br.com.objectos.html.ex.TestCase26;
import br.com.objectos.html.ex.TestCase27;
import br.com.objectos.html.ex.TestCase28;
import br.com.objectos.html.ex.TestCase29;
import br.com.objectos.html.ex.TestCase30;
import org.testng.annotations.Test;

public class CompiledTemplateTest {

  @Test
  public void testCase00() {
    testSimpleWriter(
      new TestCase00(),
      "<html></html>"
    );
  }

  @Test
  public void testCase01() {
    testSimpleWriter(
      new TestCase01(),
      "<html lang=\"pt-BR\"></html>"
    );
  }

  @Test
  public void testCase02() {
    testSimpleWriter(
      new TestCase02(),
      "<html class=\"no-js\" lang=\"pt-BR\"></html>"
    );
  }

  @Test
  public void testCase03() {
    testSimpleWriter(
      new TestCase03(),
      "<html><head></head></html>"
    );
  }

  @Test
  public void testCase04() {
    testSimpleWriter(
      new TestCase04(),
      "<html lang=\"pt-BR\"><head></head></html>"
    );
  }

  @Test
  public void testCase05() {
    testSimpleWriter(
      new TestCase05(),
      "<html><head><meta></head></html>"
    );
  }

  @Test
  public void testCase06() {
    testSimpleWriter(
      new TestCase06(),
      "<html lang=\"pt-BR\"><head><meta charset=\"utf-8\"></head></html>"
    );
  }

  @Test
  public void testCase07() {
    testSimpleWriter(
      new TestCase07(),
      "<html><head></head><body></body></html>"
    );
  }

  @Test
  public void testCase08() {
    testSimpleWriter(
      new TestCase08(),
      "<html lang=\"pt-BR\"><head></head><body></body></html>"
    );
  }

  @Test
  public void testCase09() {
    testSimpleWriter(
      new TestCase09(),
      "<!doctype html><html></html>"
    );
  }

  @Test
  public void testCase10() {
    testSimpleWriter(
      new TestCase10(),
      "<html><head><meta charset=\"utf-8\"></head></html>"
    );
  }

  @Test
  public void testCase11() {
    testSimpleWriter(
      new TestCase11(),
      "<html><body><header><nav></nav></header></body></html>"
    );
  }

  @Test
  public void testCase12() {
    testSimpleWriter(
      new TestCase12(),
      "<html><head><meta></head><body><header></header></body></html>"
    );
  }

  @Test
  public void testCase13() {
    testSimpleWriter(
      new TestCase13(),
      "<html><head><link rel=\"stylesheet\" href=\"https://example.com/css/styles.css\"></head></html>"
    );
  }

  @Test
  public void testCase14() {
    testSimpleWriter(
      new TestCase14(),
      "<html><body><p>o7html</p></body></html>"
    );
  }

  @Test
  public void testCase15() {
    testSimpleWriter(
      new TestCase15(),
      "<div>a<p>b</p>c</div>"
    );
  }

  @Test
  public void testCase16() {
    testSimpleWriter(
      new TestCase16(),
      "<html><head><title>element</title></head><body title=\"attribute\"></body></html>"
    );
  }

  @Test
  public void testCase17() {
    testSimpleWriter(
      new TestCase17(),
      "<html>",
      "<head>",
      "<meta charset=\"utf8\">",
      "<title>test case 17</title>",
      "</head>",
      "<body id=\"id\" title=\"t1 t2\">",
      "<p>tc17</p>",
      "</body>",
      "</html>"
    );
  }

  @Test
  public void testCase18() {
    testSimpleWriter(
      new TestCase18(),
      "<body id=\"id\" title=\"t1 t2\">",
      "<p>tc18</p>",
      "</body>"
    );
  }

  @Test
  public void testCase19() {
    testSimpleWriter(
      new TestCase19(),
      "<body>",
      "<option label=\"attribute\"></option>",
      "<fieldset><label>element</label></fieldset>",
      "</body>"
    );
  }

  @Test
  public void testCase20() {
    testSimpleWriter(
      new TestCase20(),
      "<body>",
      "<nav>",
      "<a>o7html</a>",
      "</nav>",
      "<section>",
      "<p>is cool</p>",
      "</section>",
      "</body>"
    );
  }

  @Test
  public void testCase21() {
    testSimpleWriter(
      new TestCase21(),
      "<div class=\"first second\">",
      "</div>"
    );
  }

  @Test
  public void testCase22() {
    testSimpleWriter(
      new TestCase22(),
      "<div class=\"c1 c2 c3 c4 c5\">",
      "</div>"
    );
  }

  @Test
  public void testCase23() {
    int count = 1024;
    StringBuilder ul = new StringBuilder()
      .append("<ul>");
    for (int i = 0; i < count; i++) {
      ul.append("<li>");
      ul.append(i);
      ul.append("</li>");
    }
    ul.append("</ul>");
    testSimpleWriter(
      new TestCase23(1024),
      ul.toString()
    );
  }

  @Test

  public void testCase24() {
    testSimpleWriter(
      new TestCase24(),
      "<pre><code>&lt;xml&gt;&lt;/xml&gt;&copy;</code></pre>"
    );
  }

  @Test

  public void testCase25() {
    testSimpleWriter(
      new TestCase25(),
      "<style>@font-face {font-family: 'Foo';}</style>"
    );
  }

  @Test

  public void testCase26() {
    testSimpleWriter(
      new TestCase26(),
      "<div>",
      "<div id=\"f0\"></div>",
      "<main><article><div id=\"f1\"></div></article></main>",
      "<div id=\"f2\"></div>",
      "</div>"
    );
  }

  @Test
  public void testCase27() {
    testSimpleWriter(
      new TestCase27(),
      "<style>ul > li { margin: 0; }</style>"
    );
  }

  @Test
  public void testCase28() {
    testSimpleWriter(
      new TestCase28(),
      "<div>",
      "<svg xmlns=\"http://www.w3.org/2000/svg\" class=\"icon icon-tabler icon-tabler-brand-github\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"currentColor\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">",
      "<path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\"></path>",
      "<path d=\"M9 19c-4.3 1.4 -4.3 -2.5 -6 -3m12 5v-3.5c0 -1 .1 -1.4 -.5 -2c2.8 -.3 5.5 -1.4 5.5 -6a4.6 4.6 0 0 0 -1.3 -3.2a4.2 4.2 0 0 0 -.1 -3.2s-1.1 -.3 -3.5 1.3a12.3 12.3 0 0 0 -6.2 0c-2.4 -1.6 -3.5 -1.3 -3.5 -1.3a4.2 4.2 0 0 0 -.1 3.2a4.6 4.6 0 0 0 -1.3 3.2c0 4.6 2.7 5.7 5.5 6c-.6 .6 -.6 1.2 -.5 2v3.5\"></path>",
      "</svg>",
      "</div>"
    );
  }

  @Test
  public void testCase29() {
    testSimpleWriter(
      new TestCase29(),
      "<div>",
      "<svg width=\"301.6\" height=\"66.16\" viewBox=\"0 0 301.6 66.16\" xmlns=\"http://www.w3.org/2000/svg\">",
      "<defs>",
      "<clipPath id=\"a\"><path d=\"m22.6 145.9h83.26v83.25h-83.26z\"></path></clipPath>",
      "</defs>",
      "<g transform=\"translate(-34.75 -154.6)\" clip-path=\"url(#a)\">",
      "<path transform=\"matrix(3.469 0 0 3.469 22.61 145.9)\" d=\"m12 12-8-4.5m8 4.5v9m0-9 8-4.5m-8-4.5 8 4.5v9l-8 4.5-8-4.5v-9l8-4.5\" fill=\"none\" stroke=\"#4484cb\" stroke-linecap=\"round\"></path>",
      "</g>",
      "<g transform=\"translate(-34.75 -154.6)\" fill=\"#545454\">",
      "<path transform=\"translate(309.9 203.1)\" d=\"m14.86 0.3281c-2.086 0-4.117-0.3672-6.093-1.109-1.969-0.7382-3.602-1.859-4.891-3.359-0.25-0.2891-0.375-0.6133-0.375-0.9688 0-0.5 0.2344-0.9101 0.7031-1.234 0.2071-0.1875 0.5078-0.2813 0.9063-0.2813 0.5 0 0.9101 0.1992 1.234 0.5938 1.801 2.188 4.691 3.281 8.672 3.281 2.832 0 4.921-0.5 6.265-1.5 1.344-1.008 2.016-2.301 2.016-3.875 0-2.832-2.746-4.645-8.234-5.438-3.481-0.5-6.11-1.445-7.891-2.843-1.774-1.406-2.656-3.094-2.656-5.063 0-2.625 0.9571-4.66 2.875-6.109 1.926-1.457 4.429-2.188 7.515-2.188 4.52 0 7.946 1.618 10.28 4.844 0.208 0.262 0.313 0.57 0.313 0.922 0 0.5-0.289 0.914-0.859 1.234-0.356 0.149-0.621 0.219-0.797 0.219-0.543 0-0.992-0.234-1.344-0.703-1.836-2.332-4.402-3.5-7.703-3.5-2.188 0-3.93 0.48-5.219 1.438-1.293 0.949-1.938 2.121-1.938 3.515 0 1.438 0.6172 2.59 1.859 3.453 1.238 0.856 3.313 1.5 6.219 1.938 3.801 0.531 6.535 1.543 8.203 3.031 1.664 1.492 2.5 3.293 2.5 5.406 0 1.656-0.531 3.117-1.594 4.375-1.055 1.25-2.461 2.219-4.219 2.906-1.761 0.6797-3.679 1.016-5.75 1.016zm-31.05 0c-2.836 0-5.402-0.6562-7.703-1.969-2.293-1.312-4.094-3.113-5.406-5.406-1.305-2.301-1.954-4.848-1.954-7.641 0-2.832 0.649-5.394 1.954-7.687 1.312-2.301 3.113-4.109 5.406-5.422 2.301-1.312 4.867-1.969 7.703-1.969 2.801 0 5.348 0.657 7.64 1.969 2.289 1.313 4.094 3.121 5.406 5.422 1.312 2.293 1.969 4.855 1.969 7.687 0 2.793-0.6562 5.34-1.969 7.641-1.312 2.293-3.117 4.094-5.406 5.406-2.292 1.312-4.84 1.969-7.64 1.969zm0-3.016c2.219 0 4.223-0.5195 6.016-1.562 1.8-1.039 3.21-2.473 4.234-4.297 1.02-1.832 1.531-3.879 1.531-6.141 0-2.257-0.5117-4.3-1.531-6.124-1.024-1.833-2.434-3.282-4.234-4.344-1.793-1.063-3.797-1.594-6.016-1.594-2.231 0-4.242 0.531-6.031 1.594-1.793 1.062-3.211 2.511-4.25 4.344-1.043 1.824-1.563 3.867-1.563 6.124 0 2.262 0.52 4.309 1.563 6.141 1.039 1.824 2.457 3.258 4.25 4.297 1.789 1.043 3.8 1.562 6.031 1.562zm-19.8-0.5937c0.5 0 0.898 0.1523 1.203 0.4531 0.301 0.3047 0.453 0.7109 0.453 1.219 0 0.461-0.152 0.8438-0.453 1.156-0.305 0.3047-0.703 0.4531-1.203 0.4531h-1.25c-1.969 0-3.727-0.4453-5.266-1.344-1.543-0.8945-2.746-2.125-3.609-3.687-0.856-1.562-1.282-3.328-1.282-5.297v-15.12h-4.203c-0.429 0-0.777-0.133-1.047-0.406-0.273-0.27-0.406-0.618-0.406-1.047 0-0.426 0.133-0.774 0.406-1.047 0.27-0.27 0.618-0.406 1.047-0.406h4.203v-8.766c0-0.508 0.149-0.914 0.454-1.219 0.3-0.301 0.687-0.453 1.156-0.453 0.5 0 0.898 0.152 1.203 0.453 0.312 0.305 0.469 0.711 0.469 1.219v8.766h7.093c0.438 0 0.786 0.136 1.047 0.406 0.27 0.273 0.407 0.621 0.407 1.047 0 0.429-0.137 0.777-0.407 1.047-0.261 0.273-0.609 0.406-1.047 0.406h-7.093v15.12c0 2.086 0.644 3.781 1.937 5.094 1.289 1.305 2.957 1.953 5 1.953zm-30.14 3.609c-2.761 0-5.257-0.6562-7.484-1.969-2.219-1.312-3.965-3.113-5.234-5.406-1.274-2.301-1.907-4.848-1.907-7.641 0-2.832 0.586-5.39 1.766-7.671 1.187-2.282 2.836-4.071 4.953-5.375 2.113-1.313 4.484-1.969 7.109-1.969 2.29 0 4.383 0.449 6.282 1.344 1.906 0.898 3.562 2.226 4.968 3.984 0.25 0.355 0.375 0.656 0.375 0.906 0 0.512-0.257 0.907-0.765 1.188-0.25 0.219-0.539 0.328-0.86 0.328-0.5 0-0.914-0.211-1.234-0.641-2.293-2.726-5.215-4.093-8.766-4.093-2.085 0-3.933 0.511-5.546 1.531-1.618 1.023-2.868 2.449-3.75 4.281-0.875 1.824-1.313 3.887-1.313 6.187 0 2.262 0.484 4.309 1.453 6.141 0.969 1.824 2.313 3.258 4.031 4.297 1.727 1.043 3.704 1.562 5.922 1.562 3.157 0 5.688-0.8789 7.594-2.641 0.32-0.3203 0.695-0.4844 1.125-0.4844 0.395 0 0.719 0.125 0.969 0.375 0.363 0.3242 0.547 0.7188 0.547 1.188 0 0.3984-0.149 0.7188-0.438 0.9688-1.25 1.148-2.719 2.039-4.406 2.672-1.688 0.625-3.484 0.9375-5.391 0.9375zm-31.52-30.03c2.614 0 4.954 0.621 7.016 1.859 2.063 1.242 3.664 2.953 4.813 5.141 1.156 2.187 1.734 4.68 1.734 7.469 0 0.468-0.148 0.839-0.438 1.109-0.281 0.273-0.656 0.406-1.125 0.406h-22.55c0.1 2.117 0.66 4.008 1.66 5.672 1 1.668 2.36 2.969 4.04 3.906 1.69 0.9297 3.569 1.391 5.643 1.391 1.407 0 2.817-0.2383 4.235-0.7187 1.414-0.4883 2.535-1.125 3.359-1.906 0.352-0.2891 0.727-0.4375 1.125-0.4375 0.395 0 0.719 0.1094 0.969 0.3281 0.394 0.3242 0.594 0.6992 0.594 1.125 0 0.3555-0.164 0.6953-0.485 1.016-1.148 1.043-2.648 1.906-4.5 2.594-1.844 0.6797-3.609 1.016-5.297 1.016-2.836 0-5.353-0.6328-7.563-1.906-2.2-1.27-3.93-3.047-5.19-5.328-1.25-2.281-1.87-4.851-1.87-7.719 0-2.906 0.58-5.499 1.75-7.781 1.16-2.281 2.8-4.054 4.89-5.328 2.09-1.269 4.49-1.906 7.186-1.906zm0 3.016c-2.806 0-5.166 0.933-7.076 2.796-1.92 1.868-3.06 4.325-3.42 7.375h20.93c-0.25-3.05-1.328-5.507-3.234-7.375-1.899-1.863-4.297-2.796-7.204-2.796zm-33.47 39.28c-0.5 0-0.9-0.156-1.21-0.469-0.31-0.305-0.47-0.684-0.47-1.141 0-0.468 0.16-0.867 0.47-1.187 0.31-0.3242 0.71-0.4844 1.21-0.4844 2.73 0 4.95-0.8672 6.67-2.594 1.72-1.719 2.58-3.945 2.58-6.672v-27.87c0-0.5 0.14-0.891 0.45-1.172 0.31-0.289 0.69-0.437 1.16-0.437 0.5 0 0.91 0.156 1.21 0.468 0.31 0.305 0.46 0.684 0.46 1.141v27.87c0 2.406-0.54 4.555-1.61 6.453-1.08 1.906-2.56 3.394-4.47 4.469-1.9 1.082-4.05 1.625-6.45 1.625zm10.81-48.22c-0.71 0-1.3-0.238-1.77-0.719-0.47-0.488-0.7-1.094-0.7-1.812 0-0.789 0.25-1.399 0.75-1.828 0.5-0.426 1.09-0.641 1.78-0.641 0.68 0 1.26 0.215 1.74 0.641 0.48 0.429 0.73 1.039 0.73 1.828 0 0.718-0.25 1.324-0.73 1.812-0.48 0.481-1.08 0.719-1.8 0.719zm-23.89 5.859c2.83 0 5.4 0.657 7.69 1.969 2.3 1.313 4.1 3.121 5.41 5.422 1.31 2.293 1.96 4.836 1.96 7.625 0 2.836-0.65 5.402-1.96 7.703-1.31 2.293-3.11 4.094-5.41 5.406-2.29 1.312-4.86 1.969-7.69 1.969-2.76 0-5.28-0.6328-7.56-1.906-2.28-1.281-4.09-3.051-5.42-5.312-1.33-2.258-2-4.769-2.03-7.531v-25.98c0-0.508 0.15-0.907 0.45-1.188 0.31-0.289 0.7-0.437 1.16-0.437 0.5 0 0.89 0.148 1.18 0.437 0.29 0.281 0.44 0.68 0.44 1.188v16.78c1.18-1.895 2.8-3.391 4.86-4.484 2.06-1.102 4.37-1.657 6.92-1.657zm0 27.08c2.22 0 4.23-0.5234 6.02-1.578 1.8-1.062 3.22-2.508 4.26-4.344 1.04-1.832 1.57-3.879 1.57-6.141 0-2.258-0.53-4.301-1.57-6.125-1.04-1.832-2.46-3.27-4.26-4.312-1.79-1.04-3.8-1.563-6.02-1.563-2.23 0-4.24 0.523-6.03 1.563-1.79 1.042-3.2 2.48-4.22 4.312-1.02 1.824-1.53 3.867-1.53 6.125 0 2.262 0.51 4.309 1.53 6.141 1.02 1.836 2.43 3.281 4.22 4.344 1.79 1.055 3.8 1.578 6.03 1.578zm-36.05 3.016c-2.84 0-5.4-0.6562-7.7-1.969-2.3-1.312-4.1-3.113-5.41-5.406-1.3-2.301-1.95-4.848-1.95-7.641 0-2.832 0.65-5.394 1.95-7.687 1.31-2.301 3.11-4.109 5.41-5.422 2.3-1.312 4.86-1.969 7.7-1.969 2.8 0 5.35 0.657 7.64 1.969 2.29 1.313 4.09 3.121 5.41 5.422 1.31 2.293 1.97 4.855 1.97 7.687 0 2.793-0.66 5.34-1.97 7.641-1.32 2.293-3.12 4.094-5.41 5.406-2.29 1.312-4.84 1.969-7.64 1.969zm0-3.016c2.22 0 4.22-0.5195 6.02-1.562 1.8-1.039 3.21-2.473 4.23-4.297 1.02-1.832 1.53-3.879 1.53-6.141 0-2.257-0.51-4.3-1.53-6.124-1.02-1.833-2.43-3.282-4.23-4.344-1.8-1.063-3.8-1.594-6.02-1.594-2.23 0-4.24 0.531-6.03 1.594-1.79 1.062-3.21 2.511-4.25 4.344-1.04 1.824-1.56 3.867-1.56 6.124 0 2.262 0.52 4.309 1.56 6.141 1.04 1.824 2.46 3.258 4.25 4.297 1.79 1.043 3.8 1.562 6.03 1.562z\">",
      "</path>",
      "</g></svg></div>"
    );
  }

  @Test
  public void testCase30() {
    testSimpleWriter(
      new TestCase30(),
      "<script>alert(\"hello world!\");</script>"
    );
  }

  private void testSimpleWriter(Template template, String... expected) {
    assertEquals(template.printMinified(), String.join("", expected));
  }

}
