/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor;

import org.commonmark.node.Node;
import org.commonmark.node.Nodes;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser.Builder;
import org.commonmark.parser.Parser.ParserExtension;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

final class FootnoteExtension implements ParserExtension {

  static final ParserExtension INSTANCE = new FootnoteExtension();

  private static final DelimiterProcessor DELIMITER_PROCESSOR = new ThisDelimiterProcessor();

  @Override
  public final void extend(Builder parserBuilder) {
    parserBuilder.customDelimiterProcessor(DELIMITER_PROCESSOR);
  }

  private static class ThisDelimiterProcessor implements DelimiterProcessor {

    @Override
    public final char getClosingCharacter() {
      return '^';
    }

    @Override
    public final int getMinLength() {
      return 2;
    }

    @Override
    public final char getOpeningCharacter() {
      return '^';
    }

    @Override
    public final int process(DelimiterRun openingRun, DelimiterRun closingRun) {
      if (openingRun.length() == 2 && closingRun.length() == 2) {
        Text opener;
        opener = openingRun.getOpener();

        Text closer;
        closer = closingRun.getCloser();

        Iterable<Node> nodes;
        nodes = Nodes.between(opener, closer);

        Footnote footnote;
        footnote = new Footnote();

        for (Node node : nodes) {
          footnote.appendChild(node);
        }

        opener.insertAfter(footnote);

        return 2;
      } else {
        return 0;
      }
    }

  }

}