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
package objectos.docs.ui;

import static org.testng.Assert.assertEquals;

import objectos.docs.style.JavaCss;
import objectos.docs.style.SyntaxCss;
import objectos.docs.style.XmlCss;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MdTest {

  private Md md;

  @BeforeClass
  public void _beforeClass() {
    md = new Md();

    md.configure(new NoOpContext());
  }

  @Test
  public void heading() {
    test(
      """
      # heading{#id}
      ## heading _foo_{#foo}
      """,

      """
      <h1 id="id">heading</h1>
      <h2 id="foo">heading <em>foo</em></h2>
      """
    );
  }

  @Test
  public void java00() {
    test(
      """
      ```java
      import objectos.logging.*;
      ```
      """,

      """
      <pre class="f5y">\
      <code>\
      <span class="jps">import</span>\
      <span class="ebq"> </span>\
      <span class="pmb">objectos</span>\
      <span class="lma">.</span>\
      <span class="pmb">logging</span>\
      <span class="lma">.</span>\
      <span class="lma">*</span>\
      <span class="lma">;</span>\
      </code>\
      </pre>
      """
          .replace("f5y", SyntaxCss._PRE.className())
          .replace("jps", JavaCss._KEYWORD.className())
          .replace("ebq", JavaCss._WS.className())
          .replace("lma", JavaCss._TOKEN.className())
          .replace("pmb", JavaCss._IDENTIFIER.className())
    );
  }

  @Test
  public void java01() {
    test(
      """
      ```java
      module my.module {
        requires objectos.logging;
      }
      ```
      """,

      """
      <pre class="x4l">\
      <code>\
      <span class="q09">module</span>\
      <span class="rjs"> </span>\
      <span class="q09">my</span>\
      <span class="qki">.</span>\
      <span class="q09">module</span>\
      <span class="rjs"> </span>\
      <span class="qki">{</span>\
      <span class="rjs">
        </span>\
      <span class="q09">requires</span>\
      <span class="rjs"> </span>\
      <span class="q09">objectos</span>\
      <span class="qki">.</span>\
      <span class="q09">logging</span>\
      <span class="qki">;</span>\
      <span class="rjs">
      </span>\
      <span class="qki">}</span>\
      </code>\
      </pre>
            """
          .replace("x4l", SyntaxCss._PRE.className())
          .replace("rjs", JavaCss._WS.className())
          .replace("qki", JavaCss._TOKEN.className())
          .replace("q09", JavaCss._IDENTIFIER.className())
    );
  }

  @Test
  public void java02() {
    test(
      """
      ```java
      var s = "string literal";
      ```
      """,

      """
      <pre class="zqh">\
      <code>\
      <span class="kkl">var</span>\
      <span class="trr"> </span>\
      <span class="kkl">s</span>\
      <span class="trr"> </span>\
      <span class="a6g">=</span>\
      <span class="trr"> </span>\
      <span class="rlv">&quot;string literal&quot;</span>\
      <span class="a6g">;</span>\
      </code>\
      </pre>
            """
          .replace("zqh", SyntaxCss._PRE.className())
          .replace("trr", JavaCss._WS.className())
          .replace("a6g", JavaCss._TOKEN.className())
          .replace("kkl", JavaCss._IDENTIFIER.className())
          .replace("rlv", JavaCss._STRING.className())
    );
  }

  @Test
  public void java03() {
    test(
      """
      ```java
      @Override
      public void foo() {}
      ```
      """,

      """
      <pre class="tju">\
      <code>\
      <span class="vry">@Override</span>\
      <span class="xw2">
      </span>\
      <span class="vzu">public</span>\
      <span class="xw2"> </span>\
      <span class="vzu">void</span>\
      <span class="xw2"> </span>\
      <span class="nps">foo</span>\
      <span class="ah1">(</span>\
      <span class="ah1">)</span>\
      <span class="xw2"> </span>\
      <span class="ah1">{</span>\
      <span class="ah1">}</span>\
      </code>\
      </pre>
      """
          .replace("tju", SyntaxCss._PRE.className())
          .replace("vry", JavaCss._ANNOTATION.className())
          .replace("xw2", JavaCss._WS.className())
          .replace("vzu", JavaCss._KEYWORD.className())
          .replace("ah1", JavaCss._TOKEN.className())
          .replace("nps", JavaCss._IDENTIFIER.className())
    );
  }

  @Test
  public void java04() {
    test(
      """
      ```java
      // single line
      foo.bar();
      ```
      """,

      """
      <pre class="wbl">\
      <code>\
      <span class="vnq">// single line</span>\
      <span class="f30">
      </span>\
      <span class="my1">foo</span>\
      <span class="nq4">.</span>\
      <span class="my1">bar</span>\
      <span class="nq4">(</span>\
      <span class="nq4">)</span>\
      <span class="nq4">;</span>\
      </code>\
      </pre>
      """
          .replace("wbl", SyntaxCss._PRE.className())
          .replace("vnq", JavaCss._COMMENT.className())
          .replace("f30", JavaCss._WS.className())
          .replace("nq4", JavaCss._TOKEN.className())
          .replace("my1", JavaCss._IDENTIFIER.className())
    );
  }

  @Test
  public void java05() {
    test(
      """
      ```java
      var i = 123;
      ```
      """,

      """
      <pre class="zqh">\
      <code>\
      <span class="kkl">var</span>\
      <span class="trr"> </span>\
      <span class="kkl">i</span>\
      <span class="trr"> </span>\
      <span class="a6g">=</span>\
      <span class="trr"> </span>\
      <span class="rlv">123</span>\
      <span class="a6g">;</span>\
      </code>\
      </pre>
            """
          .replace("zqh", SyntaxCss._PRE.className())
          .replace("trr", JavaCss._WS.className())
          .replace("a6g", JavaCss._TOKEN.className())
          .replace("kkl", JavaCss._IDENTIFIER.className())
          .replace("rlv", JavaCss._DIGITS.className())
    );
  }

  @Test
  public void xml() {
    test(
      """
      ```xml
      <dependencies>
          <dependency>
              <groupId>br.com.objectos</groupId>
              <artifactId>logging</artifactId>
          </dependency>
      </dependencies>
      ```
      """,

      """
      <pre class="odr"><code><span class="low"></span><span class="ryi">&lt;</span><span class="bx4">dependencies</span><span class="ryi">&gt;</span><span class="low">
          </span><span class="ryi">&lt;</span><span class="bx4">dependency</span><span class="ryi">&gt;</span><span class="low">
              </span><span class="ryi">&lt;</span><span class="bx4">groupId</span><span class="ryi">&gt;</span><span class="low">br.com.objectos</span><span class="ryi">&lt;/</span><span class="bx4">groupId</span><span class="ryi">&gt;</span><span class="low">
              </span><span class="ryi">&lt;</span><span class="bx4">artifactId</span><span class="ryi">&gt;</span><span class="low">logging</span><span class="ryi">&lt;/</span><span class="bx4">artifactId</span><span class="ryi">&gt;</span><span class="low">
          </span><span class="ryi">&lt;/</span><span class="bx4">dependency</span><span class="ryi">&gt;</span><span class="low">
      </span><span class="ryi">&lt;/</span><span class="bx4">dependencies</span><span class="ryi">&gt;</span></code></pre>
      """
          .replace("odr", SyntaxCss._PRE.className())
          .replace("low", XmlCss._TEXT.className())
          .replace("ryi", XmlCss._SYMBOL.className())
          .replace("bx4", XmlCss._TAG_NAME.className())
    );
  }

  private void test(String source, String expected) {
    String html;
    html = md.render(source);

    assertEquals(html, expected);
  }

}