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

import java.util.HashMap;
import java.util.Map;
import objectos.lang.Check;

public class AsciiDoc {

  public interface Processor {

    void boldEnd();

    void boldStart();

    void documentEnd();

    void documentStart();

    void headingEnd();

    void headingStart(int level);

    void italicEnd();

    void italicStart();

    void listingBlockEnd();

    void listingBlockStart();

    void monospaceEnd();

    void monospaceStart();

    void paragraphEnd();

    void paragraphStart();

    void preambleEnd();

    void preambleStart();

    void sectionEnd();

    void sectionStart(int level);

    void sectionStart(int level, String style);

    void sourceCodeBlockEnd();

    void sourceCodeBlockStart(String language);

    void text(String s);

  }

  private static class ListingBlock {
    static final int START = 0;

    static final int SOURCE_CODE = 1 << 0;
  }

  private static class Section {
    static final int START = 0;

    static final int STYLE_SOURCE = 1 << 0;
  }

  private final Pass0 pass0 = new Pass0();

  private final Pass1 pass1 = new Pass1();

  private final Pass2 pass2 = new Pass2();

  private String source;

  private Processor processor;

  private int state;

  private int index;

  private int attrStart;

  private int attrEnd;

  private final Map<String, String> attrMap = new HashMap<>();

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

        case Code.PREAMBLE_START -> processor.preambleStart();

        case Code.PREAMBLE_END -> processor.preambleEnd();

        case Code.SECTION_START -> processSectionStart(nextCode());

        case Code.SECTION_END -> { processor.sectionEnd(); attrClear(); }

        case Code.PARAGRAPH_START -> processor.paragraphStart();

        case Code.PARAGRAPH_END -> { processor.paragraphEnd(); attrClear(); }

        case Code.TOKENS -> processTokens(nextCode(), nextCode());

        case Code.ATTR_POSITIONAL -> {
          attrSet();

          nextCode(); // position
          nextCode(); // start
          nextCode(); // end

          attrEnd = pass1.codeCursor();
        }

        case Code.LISTING_BLOCK_START -> processListingBlockStart();

        case Code.LISTING_BLOCK_END -> { processListingBlockEnd(); attrClear(); }

        case Code.BLOB -> processor.text(source.substring(nextCode(), nextCode()));

        default -> throw new UnsupportedOperationException("Implement me :: code=" + code);
      }
    }
  }

  private boolean attr() {
    return attrStart != Integer.MIN_VALUE;
  }

  private void attrClear() {
    attrStart = Integer.MIN_VALUE;
  }

  private void attrSet() {
    if (attrStart == Integer.MIN_VALUE) {
      attrStart = pass1.codeCursor() - 1;
    }
  }

  private int codeAt(int index) {
    return pass1.codeAt(index);
  }

  private boolean hasCode() { return pass1.hasCode(); }

  private int nextCode() { return pass1.nextCode(); }

  private void processListingBlockEnd() {
    var style = attrMap.getOrDefault(AttrName.STYLE, "");

    if (style.equals("source")) {
      processor.sourceCodeBlockEnd();
    } else {
      processor.listingBlockEnd();
    }
  }

  private void processListingBlockStart() {
    if (!attr()) {
      processor.listingBlockStart();

      return;
    }

    attrMap.clear();

    state = ListingBlock.START;

    for (index = attrStart; index < attrEnd;) {
      var code = codeAt(index++);

      switch (code) {
        case Code.ATTR_POSITIONAL -> processListingBlockStartAttrPositional();

        default -> uoe(code);
      }
    }

    switch (state) {
      case ListingBlock.START -> processor.listingBlockStart();

      case ListingBlock.SOURCE_CODE -> {
        var language = attrMap.getOrDefault(AttrName.LANGUAGE, "");

        processor.sourceCodeBlockStart(language);
      }

      default -> uoe(state);
    }
  }

  private void processListingBlockStartAttrPositional() {
    var position = codeAt(index++);
    var start = codeAt(index++);
    var end = codeAt(index++);
    var value = source.substring(start, end);

    switch (position) {
      case 1 -> {
        if (value.equals("") || value.equals("source")) {
          state = state | ListingBlock.SOURCE_CODE;

          attrMap.put(AttrName.STYLE, "source");
        } else {
          throw new UnsupportedOperationException("Implement me");
        }
      }

      case 2 -> {
        attrMap.put(AttrName.LANGUAGE, value);
      }

      default -> uoe(position);
    }
  }

  private void processSectionStart(int level) {
    if (!attr()) {
      processor.sectionStart(level);

      return;
    }

    attrMap.clear();

    state = Section.START;

    for (index = attrStart; index < attrEnd;) {
      var code = codeAt(index++);

      switch (code) {
        case Code.ATTR_POSITIONAL -> processSectionStartAttrPositional();

        default -> uoe(code);
      }
    }

    switch (state) {
      case Section.START -> {
        var style = attrMap.getOrDefault(AttrName.STYLE, "");

        processor.sectionStart(level, style);
      }

      default -> uoe(state);
    }
  }

  private void processSectionStartAttrPositional() {
    var position = codeAt(index++);
    var start = codeAt(index++);
    var end = codeAt(index++);
    var value = source.substring(start, end);

    switch (position) {
      case 1 -> {
        if (value.equals("source")) {
          state = state | Section.STYLE_SOURCE;
        } else {
          attrMap.put(AttrName.STYLE, value);
        }
      }

      default -> uoe(position);
    }
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

        case Text.MONOSPACE_START -> processor.monospaceStart();

        case Text.MONOSPACE_END -> processor.monospaceEnd();

        case Text.BOLD_START -> processor.boldStart();

        case Text.BOLD_END -> processor.boldEnd();

        case Text.ITALIC_START -> processor.italicStart();

        case Text.ITALIC_END -> processor.italicEnd();

        default -> throw new UnsupportedOperationException("Implement me :: text=" + text);
      }
    }
  }

  private int uoe(int value) {
    throw new UnsupportedOperationException("Implement me :: value=" + value);
  }

}