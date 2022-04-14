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

import objectos.docs.style.SyntaxCss;
import objectos.docs.style.SyntaxXmlCss;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MdTest {

  private Md md;

  @BeforeClass
  public void _beforeClass() {
    Locator l;
    l = this::locator;

    md = new Md(l);
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
      ```""",

      """
      <pre class="odr"><code><span class="low"></span><span class="ryi">&lt;</span><span class="bx4">dependencies</span><span class="ryi">&gt;</span><span class="low">
          </span><span class="ryi">&lt;</span><span class="bx4">dependency</span><span class="ryi">&gt;</span><span class="low">
              </span><span class="ryi">&lt;</span><span class="bx4">groupId</span><span class="ryi">&gt;</span><span class="low">br.com.objectos</span><span class="ryi">&lt;/</span><span class="bx4">groupId</span><span class="ryi">&gt;</span><span class="low">
              </span><span class="ryi">&lt;</span><span class="bx4">artifactId</span><span class="ryi">&gt;</span><span class="low">logging</span><span class="ryi">&lt;/</span><span class="bx4">artifactId</span><span class="ryi">&gt;</span><span class="low">
          </span><span class="ryi">&lt;/</span><span class="bx4">dependency</span><span class="ryi">&gt;</span><span class="low">
      </span><span class="ryi">&lt;/</span><span class="bx4">dependencies</span><span class="ryi">&gt;</span></code></pre>
      """
          .replace("odr", SyntaxCss._PRE.className())
          .replace("low", SyntaxXmlCss._TEXT.className())
          .replace("ryi", SyntaxXmlCss._SYMBOL.className())
          .replace("bx4", SyntaxXmlCss._TAG_NAME.className())
    );
  }

  private <T> T locator(Class<? extends T> key) {
    return null;
  }

  private void test(String source, String expected) {
    String html;
    html = md.render(source);

    assertEquals(html, expected);
  }

}