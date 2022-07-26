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

  private static final int START = 0;

  private static final int HEADER = 1 << 0;

  private static final int PREAMBLE = 1 << 1;

  private static final int CONTENT = 1 << 2;

  private int level;

  private int sectionCount;

  private int state;

  private String result;

  private final StringBuilder header = new StringBuilder();

  private final StringBuilder headingId = new StringBuilder();

  private int headingIdIndex;

  private final StringBuilder preamble = new StringBuilder();

  private final StringBuilder content = new StringBuilder();

  private StringBuilder sb;

  @Override
  public final void boldEnd() {
    sb.append("</strong>");
  }

  @Override
  public final void boldStart() {
    sb.append("<strong>");
  }

  @Override
  public final void documentEnd() {
    result = switch (state) {
      case HEADER -> """
        <div id="header">
        %s
        </div>

        <div id="content">
        </div>
        """.formatted(header);

      case PREAMBLE -> """
        <div id="header">
        </div>

        <div id="content">
        %s
        </div>
        """.formatted(preamble);

      case HEADER | PREAMBLE | CONTENT -> """
        <body>
        <div id="header">
        %s
        </div>
        <div id="content">
        <div id="preamble">
        <div class="sectionbody">
        %s
        </div>
        </div>
        %s
        </div>
        </body>
        """.formatted(header, preamble, content);

      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  @Override
  public final void documentStart() {
    level = 0;

    sectionCount = 0;

    state = START;

    result = "";

    header.setLength(0);

    headingId.setLength(0);

    headingIdIndex = 0;

    content.setLength(0);

    preamble.setLength(0);

    sb = null;
  }

  @Override
  public final void headingEnd() {
    sb.append("</h");
    sb.append(level);
    sb.append(">\n");

    switch (state) {
      case HEADER -> { /*noop*/ }
      case HEADER | PREAMBLE | CONTENT -> {
        sb.insert(headingIdIndex, headingId);

        content.append("<div class=\"sectionbody\">\n");
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }
  }

  @Override
  public final void headingStart(int level) {
    sb = switch (state) {
      case START -> {
        state = HEADER;

        header.append("<h");
        header.append(level);
        header.append(">");

        yield header;
      }

      default -> {
        headingId.setLength(0);
        headingId.append("_");

        content.append("<h");
        content.append(level);
        content.append(" id=\"");
        headingIdIndex = sb.length();
        content.append("\">");

        yield content;
      }
    };

    this.level = level;
  }

  @Override
  public final void italicEnd() {
    sb.append("</em>");
  }

  @Override
  public final void italicStart() {
    sb.append("<em>");
  }

  @Override
  public final void monospaceEnd() {
    sb.append("</code>");
  }

  @Override
  public final void monospaceStart() {
    sb.append("<code>");
  }

  @Override
  public final void newLine() {
    sb.append('\n');
  }

  @Override
  public final void paragraphEnd() {
    sb.append("</p>\n</div>\n");
  }

  @Override
  public final void paragraphStart() {
    sb.append("<div class=\"paragraph\">\n<p>");
  }

  @Override
  public final void preambleEnd() {
  }

  @Override
  public final void preambleStart() {
    state = switch (state) {
      case START -> PREAMBLE;
      case HEADER -> HEADER | PREAMBLE;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };

    sb = preamble;
  }

  @Override
  public final void sectionEnd() {
    sb.append("</div>\n"); // section body
    sb.append("</div>\n"); // section
  }

  @Override
  public final void sectionStart(int level) {
    state = switch (state) {
      case HEADER | PREAMBLE -> HEADER | PREAMBLE | CONTENT;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };

    sb = content;

    sb.append("<div class=\"sect");
    sb.append(++sectionCount);
    sb.append("\">\n");
  }

  @Override
  public final void text(String s) {
    sb.append(s);

    if (headingIdIndex > 0) {
      headingId.append(s.replace(' ', '_').toLowerCase());
    }
  }

  @Override
  public final String toString() {
    return result.toString();
  }
}