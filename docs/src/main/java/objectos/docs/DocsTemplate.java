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

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.flexbox.FlexDirection;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextDecoration;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.html.element.StandardElementName;
import br.com.objectos.html.spi.type.Value;
import br.com.objectos.html.tmpl.AbstractTemplate;
import java.util.Arrays;
import objectos.asciidoc.AsciiDoc;
import objectos.asciidoc.DocumentAttributes;
import objectos.asciidoc.InlineMacroAttributes;
import objectos.asciidoc.LinkText;
import objectos.util.IntArrays;
import objectos.util.ObjectArrays;

abstract class DocsTemplate extends AbstractTemplate implements AsciiDoc.Processor {

  static final IdSelector BODY = Css.randomHash(3);

  final DocsInjector injector;

  private String rawStyle;

  private Value[] valueList = new Value[64];

  private int valueListIndex = 0;

  private int[] valueStack = new int[32];

  private int valueStackIndex = -1;

  DocsTemplate(DocsInjector injector) { this.injector = injector; }

  @Override
  public final void boldEnd() {
    tagEnd(StandardElementName.STRONG);
  }

  @Override
  public final void boldStart() {
    tagStart();
  }

  @Override
  public void documentEnd() {
  }

  @Override
  public void documentStart(DocumentAttributes attributes) {
    valueListIndex = 0;

    valueStackIndex = -1;
  }

  @Override
  public void headingEnd(int level) {
    var tagName = switch (level) {
      case 1 -> StandardElementName.H1;

      case 2 -> StandardElementName.H2;

      case 3 -> StandardElementName.H3;

      case 4 -> StandardElementName.H4;

      case 5 -> StandardElementName.H5;

      case 6 -> StandardElementName.H6;

      default -> throw new UnsupportedOperationException(
        "Implement me :: headingLevel=" + level);
    };

    tagEnd(tagName);
  }

  @Override
  public void headingStart(int level) {
    tagStart();
  }

  @Override
  public final void inlineMacro(
      String name, String target, InlineMacroAttributes attributes) {
    switch (name) {
      case "elink" -> {
        var href = injector.$elink(target);

        tagStart();
        linkValues(href);
        attributes.render("1");
        tagEnd(StandardElementName.A);
      }

      case "ilink" -> {
        var href = injector.$ilink(target);

        tagStart();
        linkValues(href);
        attributes.render("1");
        tagEnd(StandardElementName.A);
      }

      default -> throw new UnsupportedOperationException("Implement me :: name=" + name);
    }
  }

  @Override
  public final void italicEnd() {
    tagEnd(StandardElementName.EM);
  }

  @Override
  public final void italicStart() {
    tagStart();
  }

  @Override
  public void lineFeed() {}

  @Override
  public final void link(String href, LinkText text) {
    tagStart();
    linkValues(href);
    text.render();
    tagEnd(StandardElementName.A);
  }

  @Override
  public void listingBlockEnd() {}

  @Override
  public void listingBlockStart() {}

  @Override
  public final void listItemEnd() {
    tagEnd(StandardElementName.LI);
  }

  @Override
  public void listItemStart() {
    tagStart();
  }

  @Override
  public final void monospaceEnd() {
    tagEnd(StandardElementName.CODE);
  }

  @Override
  public void monospaceStart() {
    tagStart();
  }

  @Override
  public final void paragraphEnd() {
    tagEnd(StandardElementName.P);
  }

  @Override
  public void paragraphStart() {
    tagStart();
  }

  @Override
  public void preambleEnd() {}

  @Override
  public void preambleStart() {}

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
    addValue0(t(s));
  }

  @Override
  public final void unorderedListEnd() {
    tagEnd(StandardElementName.UL);
  }

  @Override
  public void unorderedListStart() {
    tagStart();
  }

  @Override
  protected final void definition() {
    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        BODY,
        Display.flex,
        FlexDirection.column,

        f(injector.$topBar()),

        f(this::main0),

        f(injector.$bottomBar())
      )
    );
  }

  final void addValue0(Value value) {
    valueList = ObjectArrays.copyIfNecessary(valueList, valueListIndex);

    valueList[valueListIndex++] = value;
  }

  final void addValue0(Value... values) {
    valueList = ObjectArrays.copyIfNecessary(valueList, valueListIndex + values.length - 1);

    for (var value : values) {
      valueList[valueListIndex++] = value;
    }
  }

  final void addValue0(Value v0, Value v1, Value v2) {
    valueList = ObjectArrays.copyIfNecessary(valueList, valueListIndex + 2);

    valueList[valueListIndex++] = v0;
    valueList[valueListIndex++] = v1;
    valueList[valueListIndex++] = v2;
  }

  void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type("vnd.microsoft.icon"), href("/favicon.ico"));

    var title = injector.$title();

    title(title.plain());

    if (rawStyle != null) {
      style(raw(rawStyle));
    }
  }

  void linkValues(String href) {
    addValue0(
      TextColor.blue600,
      TextColor.hover.blue900,
      TextDecoration.underline,
      TextDecoration.hover.none,

      href(href)
    );
  }

  abstract void main0();

  final void rawStyle(String rawStyle) {
    this.rawStyle = rawStyle;
  }

  final void renderDocument() {
    var document = injector.$document();

    document.process(this);
  }

  final void tagEnd(StandardElementName name) {
    var values = popValues();

    var value = addStandardElement(name, values);

    addValue0(value);
  }

  final void tagStart() {
    valueStackIndex++;

    valueStack = IntArrays.copyIfNecessary(valueStack, valueStackIndex);

    valueStack[valueStackIndex] = valueListIndex;
  }

  private Value[] popValues() {
    var start = valueStack[valueStackIndex--];

    var values = Arrays.copyOfRange(valueList, start, valueListIndex);

    valueListIndex = start;

    return values;
  }

}