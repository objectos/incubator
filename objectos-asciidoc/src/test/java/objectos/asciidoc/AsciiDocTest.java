/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package objectos.asciidoc;

import static org.testng.Assert.assertEquals;

import br.com.objectos.html.element.StandardElementName;
import br.com.objectos.html.spi.type.DivValue;
import br.com.objectos.html.spi.type.Value;
import br.com.objectos.html.tmpl.AbstractTemplate;
import java.util.List;
import objectos.util.GrowableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AsciiDocTest {

  private class ThisProcessor extends AbstractTemplate implements AsciiDoc.Processor {
    private String source;

    private StandardElementName el;

    private final List<Value> children = new GrowableList<>();

    public final String convert(String source) {
      this.source = source;

      return toString();
    }

    @Override
    public final void endDocument() {

    }

    @Override
    public final void endTitle() {
      var values = children.toArray(DivValue[]::new);

      div(values);
    }

    @Override
    public final void startDocument() {

    }

    @Override
    public final void startTitle(int level) {
      children.add(
        id("header")
      );

      el = switch (level) {
        case 1 -> StandardElementName.H1;
        default -> throw new UnsupportedOperationException("Implement me :: level=" + level);
      };
    }

    @Override
    public final void text(String s) {
      children.add(
        addStandardElement(el, s)
      );
    }

    @Override
    protected final void definition() {
      asciiDoc.process(source, this);

      div(id("content"), t("\n\n"));
    }
  }

  private AsciiDoc asciiDoc;

  private ThisProcessor processor;

  @BeforeClass
  public void _beforeClass() {
    asciiDoc = AsciiDoc.create();

    processor = new ThisProcessor();
  }

  @Test(description = //
  """
  = Document title

  - happy path
  - title ends @ eof
  """)
  public final void testCase01() {
    test(
      """
      = The doctitle""",

      """
      <div id="header">
      <h1>The doctitle</h1>
      </div>
      <div id="content">

      </div>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  = Document title

  - happy path
  - title ends @ NL
  - with preamble
  """)
  public final void testCase02() {
    test(
      """
      = Test document

      Some preamble
      """,

      """
      <div id="header">
      <h1>Test document</h1>
      </div>
      <div id="content">
      <div class="paragraph">
      <p>Some preamble</p>
      </div>
      </div>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  = Document title

  - title has inline element (code)
  - title ends @ NL
  """)
  public final void testCase03() {
    test(
      """
      = The `Foo` class
      """,

      """
      <div id="header">
      <h1>The <code>Foo</code> class</h1>
      </div>
      <div id="content">

      </div>
      </div>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  = Document title

  - not a title (no space after symbol '=')
  """)
  public final void testCase04() {
    test(
      """
      =Not Title
      """,

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p>=Not Title</p>
      </div>
      </div>
      </body>
      """
    );
  }

  String convert(String source) {
    var html = processor.convert(source);

    return normalize(html);
  }

  final String normalize(String html) {
    Document fragment = Jsoup.parseBodyFragment(html);

    Element body = fragment.body();

    return body.toString();
  }

  private void test(String source, String expectedHtml) {
    assertEquals(
      convert(source),

      normalize(expectedHtml)
    );
  }

}