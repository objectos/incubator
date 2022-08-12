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

import br.com.objectos.css.select.ClassSelector;
import java.util.Set;
import javax.lang.model.SourceVersion;
import objectos.docs.style.JavaCss;

final class JavaRenderer extends LanguageRenderer {

  private static final Set<String> CONTEXTUAL_KEYWORDS = Set.of(
    "record",
    "var"
  );

  private static final byte _ANNOTATION = 1;

  private static final byte _COMMENT = 2;

  private static final byte _COMMENT_BLOCK = 3;

  private static final byte _COMMENT_LINE = 4;

  private static final byte _IDENTIFIER = 5;

  private static final byte _INT_LITERAL = 6;

  private static final byte _START = 7;

  private static final byte _STOP = 0;

  private static final byte _STRING_LITERAL = 8;

  private static final byte _TEXT_BLOCK = 9;

  private static final byte _TOKEN = 10;

  private static final byte _WS = 11;

  private int index;

  private String java;

  private int length;

  private SourceVersion sourceVersion;

  private byte state;

  private final StringBuilder stringBuilder = new StringBuilder();

  @Override
  public final void renderImpl(String literal) {
    this.java = literal;

    index = 0;

    length = literal.length();

    sourceVersion = SourceVersion.latest();

    state = _START;

    for (;;) {
      state = execute0(state);

      if (state == _STOP) {
        break;
      }
    }
  }

  private byte execute0(byte state) {
    switch (state) {
      case _ANNOTATION:
        return executeAnnotation();
      case _COMMENT:
        return executeComment();
      case _COMMENT_BLOCK:
        return executeCommentBlock();
      case _COMMENT_LINE:
        return executeCommentLine();
      case _IDENTIFIER:
        return executeIdentifier();
      case _INT_LITERAL:
        return executeIntLiteral();
      case _START:
        return executeStart();
      case _STRING_LITERAL:
        return executeStringLiteral();
      case _TEXT_BLOCK:
        return executeTextBlock();
      case _TOKEN:
        return executeToken();
      case _WS:
        return executeWs();
      default:
        throw new UnsupportedOperationException("Implement me: state=" + state);
    }
  }

  private byte executeAnnotation() {
    while (hasNext()) {
      char c;
      c = peek();

      if (!Character.isJavaIdentifierPart(c)) {
        break;
      }

      stringBuilder.append(c);

      index++;
    }

    String a;
    a = makeString();

    span(JavaCss._ANNOTATION, a);

    return _START;
  }

  private byte executeComment() {
    if (!hasNext()) {
      // syntax error?
      return _TOKEN;
    }

    char c = next();

    stringBuilder.append(c);

    if (c == '/') {
      return _COMMENT_LINE;
    }

    else if (c == '*') {
      return _COMMENT_BLOCK;
    }

    else {
      // syntax error?
      return _TOKEN;
    }
  }

  private byte executeCommentBlock() {
    throw new UnsupportedOperationException("Implement me");
  }

  private byte executeCommentLine() {
    while (hasNext()) {
      char c;
      c = peek();

      if (c == '\n') {
        break;
      }

      stringBuilder.append(c);

      index++;
    }

    String c;
    c = makeString();

    span(JavaCss._COMMENT, c);

    return _START;
  }

  private byte executeIdentifier() {
    while (hasNext()) {
      char c;
      c = peek();

      if (!Character.isJavaIdentifierPart(c)) {
        break;
      }

      stringBuilder.append(c);

      index++;
    }

    String id;
    id = makeString();

    ClassSelector type;
    type = JavaCss._IDENTIFIER;

    if (SourceVersion.isKeyword(id, sourceVersion) || CONTEXTUAL_KEYWORDS.contains(id)) {
      type = JavaCss._KEYWORD;
    }

    span(type, id);

    if (!hasNext()) {
      return _STOP;
    }

    char c;
    c = peek();

    if (Character.isWhitespace(c)) {
      return _WS;
    }

    return _START;
  }

  private byte executeIntLiteral() {
    while (hasNext()) {
      char c;
      c = peek();

      if (!Character.isDigit(c)) {
        break;
      }

      stringBuilder.append(c);

      index++;
    }

    String lit;
    lit = makeString();

    span(JavaCss._DIGITS, lit);

    return _START;
  }

  private byte executeStart() {
    if (!hasNext()) {
      return _STOP;
    }

    char c;
    c = next();

    stringBuilder.append(c);

    if (Character.isJavaIdentifierStart(c)) {
      return _IDENTIFIER;
    }

    else if (isToken(c)) {
      return _TOKEN;
    }

    else if (Character.isWhitespace(c)) {
      return _WS;
    }

    else if (c == '"') {
      if (hasNext(0) && peek(0) == '"'
          && hasNext(1) && peek(1) == '"'
          && hasNext(2) && peek(2) == '\n') {
        return _TEXT_BLOCK;
      } else {
        return _STRING_LITERAL;
      }
    }

    else if (c == '@') {
      return _ANNOTATION;
    }

    else if (c == '/') {
      return _COMMENT;
    }

    else if (Character.isDigit(c)) {
      return _INT_LITERAL;
    }

    throw new UnsupportedOperationException("Implement me: c=" + c);
  }

  private byte executeStringLiteral() {
    boolean found = false;

    char previous = '\0';

    outer: while (hasNext()) {
      char c;
      c = next();

      stringBuilder.append(c);

      if (c == '"' && previous != '\\') {
        found = true;

        break outer;
      }

      previous = c;
    }

    String lit;
    lit = makeString();

    if (!found) {
      throw new UnsupportedOperationException(
        "Implement me: string missing closing quote: " + lit);
    }

    span(JavaCss._STRING, lit);

    return _START;
  }

  private byte executeTextBlock() {
    stringBuilder.append("\"\"\n");
    next();
    next();
    next();

    boolean found = false;

    outer: while (hasNext()) {
      char c;
      c = next();

      stringBuilder.append(c);

      if (c == '"') {
        int last = stringBuilder.length() - 2;

        if (last > 1
            && stringBuilder.charAt(last--) == '"'
            && stringBuilder.charAt(last--) == '"') {
          found = true;

          break outer;
        }
      }
    }

    String lit;
    lit = makeString();

    if (!found) {
      throw new UnsupportedOperationException(
        "Implement me: string missing closing quote: " + lit);
    }

    span(JavaCss._STRING, lit);

    return _START;
  }

  private byte executeToken() {
    String t;
    t = makeString();

    span(JavaCss._TOKEN, t);

    return _START;
  }

  private byte executeWs() {
    while (hasNext()) {
      char c;
      c = peek();

      if (!Character.isWhitespace(c)) {
        break;
      }

      stringBuilder.append(c);

      index++;
    }

    String ws;
    ws = makeString();

    span(JavaCss._WS, ws);

    return _START;
  }

  private boolean hasNext() {
    return index < length;
  }

  private boolean hasNext(int offset) {
    return index + offset < length;
  }

  private boolean isToken(char c) {
    switch (c) {
      case '.':
      case '*':
      case ';':
      case '{':
      case '}':
      case '<':
      case '>':
      case '(':
      case ')':
      case '[':
      case ']':
      case ',':
      case '=':
      case '+':
      case '-':
      case '&':
      case '!':
      case ':':
      case '|':
      case '?':
        return true;
      default:
        return false;
    }
  }

  private String makeString() {
    String s;
    s = stringBuilder.toString();

    stringBuilder.setLength(0);

    return s;
  }

  private char next() {
    return java.charAt(index++);
  }

  private char peek() {
    return java.charAt(index);
  }

  private char peek(int offset) {
    return java.charAt(index + offset);
  }

}