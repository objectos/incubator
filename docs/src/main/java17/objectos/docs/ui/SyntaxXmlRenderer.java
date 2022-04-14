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

import objectos.docs.style.SyntaxXmlCss;
import org.commonmark.renderer.html.HtmlNodeRendererContext;

final class SyntaxXmlRenderer extends AbstractSyntaxRenderer {

  private static final byte _ELEMENT_CONTENT = 0;

  private static final byte _TAG_NAME = 1;

  private static final byte _TAG_SYMBOL = 2;

  private char c;

  private int i;

  private int length;

  private byte state;

  private final StringBuilder stringBuilder = new StringBuilder();

  SyntaxXmlRenderer(HtmlNodeRendererContext context) {
    super(context);
  }

  @Override
  final void render(String literal) {
    length = literal.length();

    state = _ELEMENT_CONTENT;

    for (i = 0; i < length; i++) {
      c = literal.charAt(i);

      state = execute0();
    }
  }

  @Override
  final boolean shouldRender(String info) {
    return info.equals("xml");
  }

  private byte execute0() {
    switch (state) {
      case _ELEMENT_CONTENT:
        return executeElementContent();
      case _TAG_NAME:
        return executeTagName();
      case _TAG_SYMBOL:
        return executeTagSymbol();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
    }
  }

  private byte executeElementContent() {
    if (c == '<') {
      String t;
      t = makeString();

      span(SyntaxXmlCss._TEXT, t);

      stringBuilder.append(c);

      return _TAG_SYMBOL;
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeTagName() {
    if (c == '>') {
      String tagName;
      tagName = makeString();

      span(SyntaxXmlCss._TAG_NAME, tagName);

      span(SyntaxXmlCss._SYMBOL, c);

      return _ELEMENT_CONTENT;
    }

    if (Character.isWhitespace(c)) {
      // maybe attributes
      throw new UnsupportedOperationException("Implement me");
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeTagSymbol() {
    switch (c) {
      case '/':
      case '?':
      case '!':
        stringBuilder.append(c);

        return _TAG_SYMBOL;
      default:
        String symbol;
        symbol = makeString();

        span(SyntaxXmlCss._SYMBOL, symbol);

        stringBuilder.append(c);

        return _TAG_NAME;
    }
  }

  private String makeString() {
    String s;
    s = stringBuilder.toString();

    stringBuilder.setLength(0);

    return s;
  }

}