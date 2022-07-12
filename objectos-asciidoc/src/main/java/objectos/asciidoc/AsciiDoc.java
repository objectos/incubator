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

    void startTitle();

    void text(String s);

  }

  private Processor processor;

  private AsciiDoc() {
  }

  public static AsciiDoc create() {
    return new AsciiDoc();
  }

  public final void process(String source, Processor processor) {
    Check.notNull(source, "source == null");
    this.processor = Check.notNull(processor, "processor == null");

    parse(source);

    while (hasCode()) {
      int code = nextCode();

      switch (code) {
        case TEXT -> processText();
        case TITLE -> processTitle();
        default -> throw new UnsupportedOperationException("Implement me :: code=" + code);
      }
    }
  }

  private void processText() {
    Check.state(hasCode(), "Could not find string index");

    var index = nextCode();

    processor.text(string(index));
  }

  private void processTitle() {
    processor.startTitle();
  }

}