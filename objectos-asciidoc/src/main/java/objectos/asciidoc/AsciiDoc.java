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

import java.util.Map;
import objectos.lang.Check;
import objectos.util.GrowableMap;

public class AsciiDoc {

  public interface InlineMacroAttributes {

    void render(String name);

  }

  public interface LinkText { void render(); }

  public interface Processor {

    void boldEnd();

    void boldStart();

    void documentEnd();

    void documentStart();

    void headingEnd();

    void headingStart(int level);

    void inlineMacro(String name, String target, InlineMacroAttributes attributes);

    void italicEnd();

    void italicStart();

    void lineFeed();

    void link(String href, LinkText text);

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
    static final int SOURCE_CODE = 1 << 0;
  }

  private final GrowableMap<String, AttrValue> attrMap = new GrowableMap<>();

  private final Pass0 pass0 = new Pass0();

  private final Pass1 pass1 = new Pass1();

  private final Pass2 pass2 = new Pass2();

  private String source;

  private Processor processor;

  private int state;

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

    pass1.running = false;
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

        case Code.SECTION_END -> { processor.sectionEnd(); attrMap.clear(); }

        case Code.PARAGRAPH_START -> processor.paragraphStart();

        case Code.PARAGRAPH_END -> { processor.paragraphEnd(); attrMap.clear(); }

        case Code.TOKENS -> processTokens(nextCode(), nextCode());

        case Code.ATTR_POSITIONAL -> processAttrPositional(nextCode(), nextCode(), nextCode());

        case Code.ATTR_NAMED -> processAttrNamed(nextCode(), nextCode(), nextCode(), nextCode());

        case Code.LISTING_BLOCK_START -> processListingBlockStart();

        case Code.LISTING_BLOCK_END -> { processListingBlockEnd(); attrMap.clear(); }

        case Code.VERBATIM -> processVerbatim(nextCode(), nextCode());

        case Code.ULIST_START -> processor.unorderedListStart();

        case Code.ULIST_END -> { processor.unorderedListEnd(); attrMap.clear(); }

        case Code.LI_START -> processor.listItemStart();

        case Code.LI_END -> processor.listItemEnd();

        case Code.INLINE_MACRO -> { processMacroInline(); attrMap.clear(); }

        case Code.URL_MACRO -> { processMacroUrl(); attrMap.clear(); }

        default -> throw new UnsupportedOperationException("Implement me :: code=" + code);
      }
    }
  }

  private boolean hasCode() { return pass1.hasCode(); }

  private int nextCode() { return pass1.nextCode(); }

  private void processAttrNamed(int nameStart, int nameEnd, int first, int last) {
    var name = source.substring(nameStart, nameEnd);

    processAttrPositionalOrNamed(name, first, last);
  }

  private void processAttrPositional(int position, int first, int last) {
    var name = Integer.toString(position);

    processAttrPositionalOrNamed(name, first, last);
  }

  private void processAttrPositionalOrNamed(String name, int first, int last) {
    pass2.execute(pass0, first, last);

    AttrValue value = pass2.toAttrValue(source);

    attrMap.put(name, value);

    pass2.reset();
  }

  private void processListingBlockEnd() {
    switch (state) {
      case ListingBlock.SOURCE_CODE -> processor.sourceCodeBlockEnd();

      default -> processor.listingBlockEnd();
    }
  }

  private void processListingBlockStart() {
    if (attrMap.isEmpty()) {
      processor.listingBlockStart();

      return;
    }

    var style = stringAttr(AttrName.STYLE, "1");

    switch (style) {
      case "" -> processListingBlockStartMaybeSource();

      case "source" -> processSourceCodeBlock();

      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }
  }

  private void processListingBlockStartMaybeSource() {
    var lang = stringAttr(AttrName.LANGUAGE, "2");

    if (lang.equals("")) {
      throw new UnsupportedOperationException("Implement me :: no lang found");
    }

    processSourceCodeBlock(lang);
  }

  private void processMacroInline() {
    var name = source.substring(nextCode(), nextCode());

    var maybeTarget = nextCode();

    if (maybeTarget != Code.MACRO_TARGET) {
      throw new UnsupportedOperationException("Implement me");
    }

    var target = source.substring(nextCode(), nextCode());

    processMacroInlineAttrMap();

    processMacroInlineAny(name, target);
  }

  private void processMacroInlineAny(String name, String target) {
    class Impl implements InlineMacroAttributes {
      final Map<String, AttrValue> copy = attrMap.toUnmodifiableMap();

      @Override
      public final void render(String name) {
        Check.notNull(name, "name == null");

        var attr = copy.getOrDefault(name, AttrValue.EMPTY);

        attr.render(processor);
      }
    }

    processor.inlineMacro(name, target, new Impl());
  }

  private void processMacroInlineAttrMap() {
    attrMap.clear();

    loop: while (hasCode()) {
      var cursor = pass1.codeCursor();

      var code = pass1.codeAt(cursor);

      switch (code) {
        case Code.ATTR_POSITIONAL -> {
          nextCode();

          processAttrPositional(nextCode(), nextCode(), nextCode());
        }

        default -> { break loop; }
      }
    }
  }

  private void processMacroUrl() {
    var href = source.substring(nextCode(), nextCode());

    var start = nextCode();

    if (start != Code.URL_TARGET_START) {
      throw new UnsupportedOperationException(
        "Implement me :: expected Code.URL_TARGET_START but found=" + start);
    }

    var codeStart = pass1.codeCursor();

    var end = Integer.MAX_VALUE;

    do {
      end = nextCode();
    } while (hasCode() && end != Code.URL_TARGET_END);

    if (end == Integer.MAX_VALUE) {
      throw new UnsupportedOperationException("Implement me");
    }

    var codeEnd = pass1.codeCursor() - 1;

    processor.link(href, () -> {
      for (int index = codeStart; index < codeEnd;) {
        var code = pass1.codeAt(index++);

        switch (code) {
          case Code.TOKENS -> {
            var first = pass1.codeAt(index++);
            var last = pass1.codeAt(index++);

            processTokens(first, last);
          }

          default -> throw new UnsupportedOperationException("Implement me :: code=" + code);
        }
      }
    });
  }

  private void processSectionStart(int level) {
    if (attrMap.isEmpty()) {
      processor.sectionStart(level);
    } else {
      processor.sectionStart(level);
    }
  }

  private void processSourceCodeBlock() {
    var lang = stringAttr(AttrName.LANGUAGE, "2");

    if (lang.equals("")) {
      throw new UnsupportedOperationException("Implement me :: no lang found");
    }

    processSourceCodeBlock(lang);
  }

  private void processSourceCodeBlock(String lang) {
    state = ListingBlock.SOURCE_CODE;

    processor.sourceCodeBlockStart(lang);
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

    pass2.reset();
  }

  private void processVerbatim(int first, int last) {
    for (int index = first; index < last;) {
      int token = pass0.token(index++);

      switch (token) {
        case Token.BLOB, Token.LITERALI -> {
          var begin = pass0.token(index++);
          var end = pass0.token(index++);

          text(begin, end);
        }

        case Token.BOLD_END -> {
          var pos = pass0.token(index++);

          text(pos, pos + 1);
        }

        case Token.LF -> processor.lineFeed();

        default -> uoe(token);
      }
    }
  }

  private String stringAttr(String name, String alt) {
    var attr = attrMap.get(name);

    if (attr == null) {
      attr = attrMap.getOrDefault(alt, AttrValue.EMPTY);
    }

    return attr.stringValue();
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