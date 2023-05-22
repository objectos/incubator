/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.internal;

import java.io.IOException;
import objectos.asciidoc.AsciiDoc;
import objectos.asciidoc.AsciiDoc2;
import objectos.asciidoc.pseudom.Node.ContainerNode;
import objectos.asciidoc.pseudom.Node.Monospaced;
import objectos.asciidoc.pseudom.Node.Text;
import objectos.asciidoc.pseudom.Node.Title;

final class DocumentRecordGenerator {

  private final AsciiDoc asciiDoc = AsciiDoc.create();

  private final AsciiDoc2 asciiDoc2 = new AsciiDoc2();

  private final DocumentTitleProcessor documentTitleProcessor = new DocumentTitleProcessor();

  private String templateName;

  private final StringBuilder titleHtml = new StringBuilder();

  private final StringBuilder titlePlain = new StringBuilder();

  public final DocumentRecord generate(
      String key, Version version, String source)
      throws IOException {
    var oldDocument = asciiDoc.parse(source);

    oldDocument.process(documentTitleProcessor);

    var oldDocumentTitle = documentTitleProcessor.create();

    templateName = "ArticleTemplate";

    titleHtml.setLength(0);

    titlePlain.setLength(0);

    outer: try (var document = asciiDoc2.open(source)) {
      var nodes = document.nodes();

      var iter = nodes.iterator();

      if (!iter.hasNext()) {
        break outer;
      }

      var first = iter.next();

      if (!(first instanceof Title title)) {
        break outer;
      }

      consumeTitle(title);

      if (!iter.hasNext()) {
        break outer;
      }

      iter.next();

      templateName = document.getNamed("template", "ArticleTemplate");
    }

    return new DocumentRecord(
      key,
      version,
      source,
      oldDocument,
      oldDocumentTitle,
      titleHtml.toString(),
      titlePlain.toString(),
      templateName
    );
  }

  private void consumeTitle(ContainerNode container) {
    for (var node : container.nodes()) {
      if (node instanceof Monospaced monospaced) {
        titleHtml.append("<code>");

        consumeTitle(monospaced);

        titleHtml.append("</code>");
      } else if (node instanceof Text text) {
        var value = text.value();

        titleHtml.append(value);

        titlePlain.append(value);
      } else {
        throw new UnsupportedOperationException(
          "Implement me :: type = " + node.getClass().getSimpleName()
        );
      }
    }
  }

}