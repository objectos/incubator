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
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

public class AsciiDoc {

  public interface Processor {

    void boldEnd();

    void boldStart();

    void documentEnd();

    void documentStart();

    void headingEnd();

    void headingStart(int level);

    void inlineMacro(String name, String target, UnmodifiableMap<String, String> attributes);

    void italicEnd();

    void italicStart();

    void lineFeed();

    void link(String href, String text);

    void listingBlockEnd();

    void listingBlockStart();

    void listItemEnd();

    void listItemStart();

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

    void unorderedListEnd();

    void unorderedListStart();

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

  private final GrowableMap<String, String> attrMap = new GrowableMap<>();

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

        case Code.VERBATIM -> processVerbatim(nextCode(), nextCode());

        case Code.ULIST_START -> processor.unorderedListStart();

        case Code.ULIST_END -> processor.unorderedListEnd();

        case Code.LI_START -> processor.listItemStart();

        case Code.LI_END -> processor.listItemEnd();

        case Code.INLINE_MACRO -> processInlineMacro();

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

  private void processInlineMacro() {
    var name = source.substring(nextCode(), nextCode());

    var maybeTarget = nextCode();

    if (maybeTarget != Code.MACRO_TARGET) {
      throw new UnsupportedOperationException("Implement me");
    }

    var target = source.substring(nextCode(), nextCode());

    processInlineMacroAttrMap();

    switch (name) {
      case "https" -> processInlineMacroUrl(name, target);

      default -> processInlineMacroAny(name, target);
    }
  }

  private void processInlineMacroAny(String name, String target) {
    var copy = attrMap.toUnmodifiableMap();

    processor.inlineMacro(name, target, copy);
  }

  private void processInlineMacroAttrMap() {
    attrMap.clear();

    loop: while (hasCode()) {
      int cursor = pass1.codeCursor();

      int peek = pass1.codeAt(cursor);

      switch (peek) {
        case Code.ATTR_POSITIONAL -> {
          nextCode();

          var index = nextCode();
          var value = source.substring(nextCode(), nextCode());

          attrMap.put(Integer.toString(index), value);
        }
        default -> { break loop; }
      }
    }
  }

  private void processInlineMacroUrl(String protocol, String target) {
    var size = attrMap.size();

    switch (size) {
      case 0 -> uoe(size);

      case 1 -> {
        var text = attrMap.get("1");

        processor.link(protocol + ":" + target, text);
      }

      default -> uoe(size);
    }
  }

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

          text(begin, end);
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

  private void processVerbatim(int first, int last) {
    for (int index = first; index < last;) {
      int token = pass0.token(index++);

      switch (token) {
        case Token.BLOB -> {
          var begin = pass0.token(index++);
          var end = pass0.token(index++);

          text(begin, end);
        }

        case Token.LF -> processor.lineFeed();

        default -> uoe(token);
      }
    }
  }

  private void text(int begin, int end) {
    if (begin < end) {
      var s = source.substring(begin, end);

      processor.text(s);
    }
  }

  private int uoe(int value) {
    throw new UnsupportedOperationException("Implement me :: value=" + value);
  }

}