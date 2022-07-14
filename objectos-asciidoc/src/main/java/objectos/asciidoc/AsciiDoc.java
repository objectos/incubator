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

import objectos.lang.Check;

public class AsciiDoc extends Parser {

  public interface Processor {

    void endDocument();

    void endMonospace();

    void endParagraph();

    void endPreamble();

    void endTitle();

    void startDocument();

    void startMonospace();

    void startParagraph();

    void startPreamble();

    void startTitle(int level);

    void text(String s);

  }

  private AsciiDoc() {
  }

  public static AsciiDoc create() {
    return new AsciiDoc();
  }

  public final void process(String source, Processor processor) {
    Check.notNull(source, "source == null");
    Check.notNull(processor, "processor == null");

    tokenize(source);

    parse();

    process0(processor);
  }

  final void process0(Processor processor) {
    while (hasCode()) {
      int code = nextCode();

      switch (code) {
        case Code.END_DOCUMENT -> processor.endDocument();

        case Code.END_MONOSPACE -> processor.endMonospace();

        case Code.END_PARAGRAPH -> processor.endParagraph();

        case Code.END_PREAMBLE -> processor.endPreamble();

        case Code.END_TITLE -> processor.endTitle();

        case Code.START_DOCUMENT -> processor.startDocument();

        case Code.START_MONOSPACE -> processor.startMonospace();

        case Code.START_PARAGRAPH -> processor.startParagraph();

        case Code.START_PREAMBLE -> processor.startPreamble();

        case Code.START_TITLE -> {
          Check.state(hasCode(), "Could not find title level");

          var level = nextCode();

          processor.startTitle(level);
        }

        case Code.NOOP -> {}

        case Code.TEXT -> {
          Check.state(hasCode(), "Could not find string index");

          var index = nextCode();

          processor.text(string(index));
        }

        case Code.TEXT_EOF -> {
          Check.state(hasCode(), "Could not find source index");

          nextCode();
        }

        default -> throw new UnsupportedOperationException("Implement me :: code=" + code);
      }
    }
  }

}