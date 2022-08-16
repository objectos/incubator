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
import objectos.asciidoc.DocumentAttributes;
import objectos.asciidoc.InlineMacroAttributes;
import objectos.asciidoc.LinkText;

final class DocumentProcessor implements AsciiDoc.Processor {

  private final AsciiDoc asciiDoc = AsciiDoc.create();

  private DocumentAttributes attributes;

  private String doctitle;

  private final StringBuilder html = new StringBuilder();

  private final LanguageRenderer defaultRenderer = new DefaultRenderer();
  private final LanguageRenderer javaRenderer = new JavaRenderer();
  private final LanguageRenderer xmlRenderer = new XmlRenderer();

  private int headingLevel;

  private int headingStart;

  private int languageMark;

  private LanguageRenderer languageRenderer;

  private String slug;

  @Override
  public final void boldEnd() {
    html.append("</strong>");
  }

  @Override
  public final void boldStart() {
    html.append("<strong>");
  }

  @Override
  public final void documentEnd() {
    html.append("</article>");
  }

  @Override
  public final void documentStart(DocumentAttributes attributes) {
    this.attributes = attributes;

    html.append("<article>\n");
  }

  @Override
  public final void headingEnd() {
    if (headingLevel == 1) {
      doctitle = html.substring(headingStart, html.length());
    }

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

    if (headingLevel == 1) {
      headingStart = html.length();
    }
  }

  @Override
  public final void inlineMacro(
      String name, String target, InlineMacroAttributes attributes) {
    switch (name) {
      case "elink" -> {
        var first = target.indexOf('/');

        var versionKey = target.substring(0, first);

        var version = Version.parse(versionKey);

        var key = target.substring(first + 1);

        var href = "/" + version.slug() + "/" + key + ".html";

        html.append("<a href=\"");
        html.append(href);
        html.append("\">");
        attributes.render("1");
        html.append("</a>");
      }

      case "ilink" -> {
        var href = "/" + slug + "/" + target + ".html";

        html.append("<a href=\"");
        html.append(href);
        html.append("\">");
        attributes.render("1");
        html.append("</a>");
      }

      default -> throw new UnsupportedOperationException("Implement me :: name=" + name);
    }
  }

  @Override
  public final void italicEnd() {
    html.append("</em>");
  }

  @Override
  public final void italicStart() {
    html.append("<em>");
  }

  @Override
  public final void lineFeed() {
    if (languageRenderer != null) {
      html.append('\n');
    }
  }

  @Override
  public final void link(String href, LinkText text) {
    html.append("<a href=\"");
    html.append(href);
    html.append("\">");
    text.render();
    html.append("</a>");
  }

  @Override
  public final void listingBlockEnd() {
    sourceCodeBlockEnd();
  }

  @Override
  public final void listingBlockStart() {
    sourceCodeBlockStart("default");
  }

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
      attributes,

      doctitle,

      html.toString()
    );
  }

  @Override
  public final void monospaceEnd() {
    html.append("</code>");
  }

  @Override
  public final void monospaceStart() {
    html.append("<code>");
  }

  @Override
  public final void paragraphEnd() {
    html.append("</p>");
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
    //html.append("</section>\n");
  }

  @Override
  public final void sectionStart(int level) {
    //html.append("<section>\n");
  }

  @Override
  public final void sectionStart(int level, String style) {
    //html.append("<section>\n");
  }

  @Override
  public final void sourceCodeBlockEnd() {
    var source = html.substring(languageMark, html.length());

    html.setLength(languageMark);

    languageRenderer.render(this, source);

    languageRenderer = null;
  }

  @Override
  public final void sourceCodeBlockStart(String language) {
    languageMark = html.length();

    languageRenderer = switch (language) {
      case "default", "shell" -> defaultRenderer;

      case "java" -> javaRenderer;

      case "xml" -> xmlRenderer;

      default -> throw new UnsupportedOperationException("Implement me :: lang=" + language);
    };
  }

  @Override
  public final void text(String s) {
    if (languageRenderer != null) {
      raw(s);
    } else {
      escape(s);
    }
  }

  @Override
  public final void unorderedListEnd() {
    html.append("</ul>");
  }

  @Override
  public final void unorderedListStart() {
    html.append("\n<ul>");
  }

  final void escape(String s) {
    for (int i = 0, len = s.length(); i < len; i++) {
      var c = s.charAt(i);

      switch (c) {
        default:
          html.append(c);
          break;
        case '"':
          html.append("&quot;");
          break;
        case '&':
          html.append("&amp;");
          break;
        case '<':
          html.append("&lt;");
          break;
        case '>':
          html.append("&gt;");
          break;
        case '\u00A9':
          html.append("&copy;");
          break;
      }
    }
  }

  final void raw(String s) {
    html.append(s);
  }

  final void slug(String slug) {
    this.slug = slug;
  }

}