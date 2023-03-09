/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.shared;

public final class XmlRenderer extends LanguageRenderer {

  private static final byte _ELEMENT_CONTENT = 0;

  private static final byte _TAG_NAME = 1;

  private static final byte _TAG_SYMBOL = 2;

  private char c;

  private int i;

  private int length;

  private byte state;

  private final StringBuilder stringBuilder = new StringBuilder();

  public static void init() {
    XmlStyles.init();
  }

  @Override
  public final void renderImpl(String literal) {
    length = literal.length();

    state = _ELEMENT_CONTENT;

    for (i = 0; i < length; i++) {
      c = literal.charAt(i);

      state = execute0();
    }
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
      var t = makeString();

      span(XmlStyles._TEXT, t);

      stringBuilder.append(c);

      return _TAG_SYMBOL;
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeTagName() {
    if (c == '>') {
      var tagName = makeString();

      span(XmlStyles._TAG_NAME, tagName);

      span(XmlStyles._SYMBOL, c);

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
        var symbol = makeString();

        span(XmlStyles._SYMBOL, symbol);

        stringBuilder.append(c);

        return _TAG_NAME;
    }
  }

  private String makeString() {
    var s = stringBuilder.toString();

    stringBuilder.setLength(0);

    return s;
  }

}