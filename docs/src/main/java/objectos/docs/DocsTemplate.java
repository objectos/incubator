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

import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.AbstractTemplate;
import objectos.asciidoc.AsciiDoc;
import objectos.asciidoc.DocumentAttributes;
import objectos.asciidoc.InlineMacroAttributes;
import objectos.asciidoc.LinkText;

abstract class DocsTemplate extends AbstractTemplate implements AsciiDoc.Processor {

  final DocsInjector injector;

  private int headingLevel;

  DocsTemplate(DocsInjector injector) { this.injector = injector; }

  @Override
  public final void boldEnd() {
    raw("</strong>");
  }

  @Override
  public final void boldStart() {
    raw("<strong>");
  }

  @Override
  public final void documentEnd() {
    raw("\n</article>");
  }

  @Override
  public final void documentStart(DocumentAttributes attributes) {
    raw("\n<article>\n");
  }

  @Override
  public final void headingEnd() {
    raw("</h");
    raw(Integer.toString(headingLevel));
    raw(">");
  }

  @Override
  public final void headingStart(int level) {
    raw("\n<h");
    raw(Integer.toString(level));
    raw(">");

    headingLevel = level;
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

        raw("<a href=\"");
        raw(href);
        raw("\">");
        attributes.render("1");
        raw("</a>");
      }

      case "ilink" -> {
        var href = injector.$ilink(target);

        raw("<a href=\"");
        raw(href);
        raw("\">");
        attributes.render("1");
        raw("</a>");
      }

      default -> throw new UnsupportedOperationException("Implement me :: name=" + name);
    }
  }

  @Override
  public final void italicEnd() {
    raw("</em>");
  }

  @Override
  public final void italicStart() {
    raw("<em>");
  }

  @Override
  public void lineFeed() {}

  @Override
  public final void link(String href, LinkText text) {
    raw("<a href=\"");
    raw(href);
    raw("\">");
    text.render();
    raw("</a>");
  }

  @Override
  public void listingBlockEnd() {}

  @Override
  public void listingBlockStart() {}

  @Override
  public final void listItemEnd() {
    raw("</li>");
  }

  @Override
  public final void listItemStart() {
    raw("\n<li>");
  }

  @Override
  public final void monospaceEnd() {
    raw("</code>");
  }

  @Override
  public final void monospaceStart() {
    raw("<code>");
  }

  @Override
  public final void paragraphEnd() {
    raw("\n</p>\n");
  }

  @Override
  public final void paragraphStart() {
    raw("<p>");
  }

  @Override
  public final void preambleEnd() {}

  @Override
  public final void preambleStart() {}

  @Override
  public final void sectionEnd() {}

  @Override
  public final void sectionStart(int level) {}

  @Override
  public final void sectionStart(int level, String style) {}

  @Override
  public void sourceCodeBlockEnd() {}

  @Override
  public void sourceCodeBlockStart(String language) {}

  @Override
  public void text(String s) {
    t(s);
  }

  @Override
  public final void unorderedListEnd() {
    raw("\n</ul>");
  }

  @Override
  public final void unorderedListStart() {
    raw("\n<ul>");
  }

  @Override
  protected final void definition() {
    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      f(this::body0)
    );
  }

  abstract void body0();

  final String generate() {
    return toString();
  }

  final void renderDocument() {
    var document = injector.$document();

    document.process(this);
  }

  abstract StyleSheet styleSheet();

  private void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type("vnd.microsoft.icon"), href("/favicon.ico"));

    var title = injector.$title();

    title(title.plain());

    style(
      raw(styleSheet().toString())
    );
  }

}