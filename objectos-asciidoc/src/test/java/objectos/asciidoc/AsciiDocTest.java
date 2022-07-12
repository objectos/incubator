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

import br.com.objectos.html.attribute.StandardAttributeName;
import br.com.objectos.html.element.StandardElementName;
import br.com.objectos.html.tmpl.AbstractTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AsciiDocTest {

  private class ThisProcessor extends AbstractTemplate implements AsciiDoc.Processor {
    private String source;

    private StandardElementName el;

    public final String convert(String source) {
      this.source = source;

      return toString();
    }

    @Override
    public final void startTitle() {
      id("header");

      el = StandardElementName.H1;
    }

    @Override
    public final void text(String s) {
      addStandardElement(el, s);

      div(StandardAttributeName.ID, el);
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

  @Test
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