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

public class AsciiDoc {

  public interface Processor {

    void documentEnd();

    void documentStart();

    void endMonospace();

    void endParagraph();

    void endPreamble();

    void headingEnd();

    void headingStart(int level);

    void newLine();

    void startMonospace();

    void startParagraph();

    void startPreamble();

    void text(String s);

  }

  private final Pass0 pass0 = new Pass0();

  private final Pass1 pass1 = new Pass1();

  private final Pass2 pass2 = new Pass2();

  private String source;

  private Processor processor;

  private AsciiDoc() {
  }

  public static AsciiDoc create() {
    return new AsciiDoc();
  }

  public final void process(String source, Processor processor) {
    this.source = Check.notNull(source, "source == null");
    this.processor = Check.notNull(processor, "processor == null");

    pass0.execute(source);

    pass1.execute(pass0);

    process0();
  }

  final void process0() {
    while (hasCode()) {
      int code = nextCode();

      switch (code) {
        case Code.DOCUMENT_START -> processor.documentStart();

        case Code.DOCUMENT_END -> processor.documentEnd();

        case Code.HEADING_START -> processor.headingStart(nextCode());

        case Code.HEADING_END -> processor.headingEnd();

        case Code.TOKENS -> processTokens(nextCode(), nextCode());

        default -> throw new UnsupportedOperationException("Implement me :: code=" + code);
      }
    }
  }

  private boolean hasCode() { return pass1.hasCode(); }

  private int nextCode() {
    return pass1.nextCode();
  }

  private void processTokens(int first, int last) {
    pass2.execute(pass0, first, last);

    while (pass2.hasText()) {
      var text = pass2.nextText();

      switch (text) {
        case Text.REGULAR -> {
          var begin = pass2.nextText();
          var end = pass2.nextText();

          if (begin < end) {
            var s = source.substring(begin, end);

            processor.text(s);
          }
        }

        default -> throw new UnsupportedOperationException("Implement me :: text=" + text);
      }
    }
  }

}