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

class ThisProcessor implements AsciiDoc.Processor {

  private static final int _START = 0;

  private static final int _HEADER = 1;

  private static final int _CONTENT = 2;

  private int level;

  private int state;

  private final StringBuilder sb = new StringBuilder();

  @Override
  public final void endDocument() {
    switch (state) {
      case _HEADER -> sb.append("""
        </div>

        <div id="content">
        </div>
        """);
      case _CONTENT -> sb.append("</div>");
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }
  }

  @Override
  public final void endMonospace() {
    sb.append("</code>");
  }

  @Override
  public final void endParagraph() {
    sb.append("</p></div>");
  }

  @Override
  public final void endPreamble() {
  }

  @Override
  public final void endTitle() {
    sb.append("</h");
    sb.append(level);
    sb.append(">\n");
  }

  @Override
  public final void newLine() {
    sb.append('\n');
  }

  @Override
  public final void startDocument() {
    level = 0;

    state = _START;

    sb.setLength(0);
  }

  @Override
  public final void startMonospace() {
    sb.append("<code>");
  }

  @Override
  public final void startParagraph() {
    sb.append("<div class=\"paragraph\">\n<p>");
  }

  @Override
  public final void startPreamble() {
    switch (state) {
      case _START -> sb.append("""
        <div id="header">
        </div>

        <div id="content">
        """);
      case _HEADER -> sb.append("""
        </div>

        <div id="content">
        """);
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }

    state = _CONTENT;
  }

  @Override
  public final void startTitle(int level) {
    switch (state) {
      case _START -> sb.append("""
        <div id="header">
        """);
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }

    state = _HEADER;

    sb.append("<h");
    sb.append(level);
    sb.append(">");

    this.level = level;
  }

  @Override
  public final void text(String s) {
    sb.append(s);
  }

  @Override
  public final String toString() {
    return sb.toString();
  }
}