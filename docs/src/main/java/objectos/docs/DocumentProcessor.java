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
package objectos.docs;

import static java.lang.System.err;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import objectos.asciidoc.AsciiDoc;
import objectos.util.UnmodifiableMap;

final class DocumentProcessor implements AsciiDoc.Processor {

  private final AsciiDoc asciiDoc = AsciiDoc.create();

  private final StringBuilder html = new StringBuilder();

  private int headingLevel;

  @Override
  public final void boldEnd() {}

  @Override
  public final void boldStart() {}

  @Override
  public final void documentEnd() {
    html.append("</article>");
  }

  @Override
  public final void documentStart() {
    html.append("<article>\n");
  }

  @Override
  public final void headingEnd() {
    html.append("</h");
    html.append(headingLevel);
    html.append(">\n");
  }

  @Override
  public final void headingStart(int level) {
    html.append("<h");
    html.append(level);
    html.append(">");

    headingLevel = level;
  }

  @Override
  public final void inlineMacro(
      String name, String target, UnmodifiableMap<String, String> attributes) {
    switch (name) {
      case "ilink" -> {
        //var href = pages.href(target);
        var href = target;

        var text = (String) attributes.get("1");

        html.append("<a href=\"");
        html.append(href);
        html.append("\">");
        html.append(text);
        html.append("</a>");
      }
      default -> throw new UnsupportedOperationException("Implement me :: name=" + name);
    }
  }

  @Override
  public final void italicEnd() {}

  @Override
  public final void italicStart() {}

  @Override
  public final void lineFeed() {}

  @Override
  public final void link(String href, String text) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void listingBlockEnd() {}

  @Override
  public final void listingBlockStart() {}

  @Override
  public final void listItemEnd() {
    html.append("</li>");
  }

  @Override
  public final void listItemStart() {
    html.append("<li>");
  }

  public final Document load(String resourceDirectory, String key) throws IOException {
    // load document
    var resourceName = resourceDirectory + "/" + key + ".adoc";

    var output = new ByteArrayOutputStream();

    var thisClass = getClass();

    try (var input = thisClass.getResourceAsStream(resourceName)) {
      input.transferTo(output);
    } catch (IOException e) {
      err.println("Failed to load a resource");

      throw e;
    }

    var bytes = output.toByteArray();

    var contents = new String(bytes, StandardCharsets.UTF_8);

    html.setLength(0);

    asciiDoc.process(contents, this);

    return new Document(
      html.toString()
    );
  }

  @Override
  public final void monospaceEnd() {}

  @Override
  public final void monospaceStart() {}

  @Override
  public final void paragraphEnd() {
    html.append("</p>\n");
  }

  @Override
  public final void paragraphStart() {
    html.append("<p>");
  }

  @Override
  public final void preambleEnd() {}

  @Override
  public final void preambleStart() {}

  @Override
  public final void sectionEnd() {
    html.append("</section>\n");
  }

  @Override
  public final void sectionStart(int level) {
    html.append("<section>\n");
  }

  @Override
  public final void sectionStart(int level, String style) {
    html.append("<section>\n");
  }

  @Override
  public final void sourceCodeBlockEnd() {}

  @Override
  public final void sourceCodeBlockStart(String language) {}

  @Override
  public final void text(String s) {
    // TODO escape?
    html.append(s);
  }

  @Override
  public final void unorderedListEnd() {
    html.append("</ul>\n");
  }

  @Override
  public final void unorderedListStart() {
    html.append("<ul>\n");
  }

}