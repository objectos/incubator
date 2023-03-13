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

  private static final byte _MAYBE_ATTR = 3;

  private static final byte _ATTR_NAME = 4;

  private static final byte _MAYBE_ATTR_VALUE = 5;

  private static final byte _ATTR_VALUE = 6;

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
    literal = literal.trim();

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
      case _MAYBE_ATTR:
        return executeMaybeAttr();
      case _ATTR_NAME:
        return executeAttrName();
      case _MAYBE_ATTR_VALUE:
        return executeMaybeAttrValue();
      case _ATTR_VALUE:
        return executeAttrValue();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
    }
  }

  private byte executeAttrValue() {
    if (c == '"') {
      stringBuilder.append(c);

      var value = makeString();

      span(XmlStyles._ATTR_VALUE, value);

      return _MAYBE_ATTR;
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeAttrName() {
    if (c == '=') {
      var name = makeString();

      span(XmlStyles._ATTR_NAME, name);

      span(XmlStyles._SYMBOL, c);

      return _MAYBE_ATTR_VALUE;
    }

    if (c == '>') {
      var name = makeString();

      span(XmlStyles._ATTR_NAME, name);

      span(XmlStyles._SYMBOL, c);

      return _ELEMENT_CONTENT;
    }

    if (Character.isWhitespace(c)) {
      throw new UnsupportedOperationException("Implement me :: " + stringBuilder);
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeElementContent() {
    if (c == '<') {
      var t = makeString();

      if (!t.isEmpty()) {
        span(XmlStyles._TEXT, t);
      }

      stringBuilder.append(c);

      return _TAG_SYMBOL;
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeMaybeAttr() {
    if (Character.isLetter(c)) {
      var t = makeString();

      span(XmlStyles._TEXT, t);

      stringBuilder.append(c);

      return _ATTR_NAME;
    }

    if (c == '>') {
      span(XmlStyles._SYMBOL, c);

      return _ELEMENT_CONTENT;
    }

    stringBuilder.append(c);

    return state;
  }

  private byte executeMaybeAttrValue() {
    if (c == '"') {
      var t = makeString();

      if (!t.isEmpty()) {
        span(XmlStyles._TEXT, t);
      }

      stringBuilder.append(c);

      return _ATTR_VALUE;
    }

    throw new UnsupportedOperationException("Implement me");
  }

  private byte executeTagName() {
    if (c == '>') {
      var tagName = makeString();

      span(XmlStyles._TAG_NAME, tagName);

      span(XmlStyles._SYMBOL, c);

      return _ELEMENT_CONTENT;
    }

    if (Character.isWhitespace(c)) {
      var tagName = makeString();

      span(XmlStyles._TAG_NAME, tagName);

      stringBuilder.append(c);

      return _MAYBE_ATTR;
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