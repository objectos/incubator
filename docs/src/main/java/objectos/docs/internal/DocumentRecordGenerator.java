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
import objectos.asciidoc.pseudom.Document;
import objectos.asciidoc.pseudom.Node.Header;
import objectos.asciidoc.pseudom.Node.Title;

@SuppressWarnings("unused")
final class DocumentRecordGenerator {

  private final AsciiDoc asciiDoc = AsciiDoc.create();

  private final AsciiDoc2 asciiDoc2 = new AsciiDoc2();

  private final DocumentTitleProcessor documentTitleProcessor = new DocumentTitleProcessor();

  private final StringBuilder pageTitle = new StringBuilder();

  public final DocumentRecord generate(
      String key, Version version, String source)
      throws IOException {
    var oldDocument = asciiDoc.parse(source);

    oldDocument.process(documentTitleProcessor);

    var oldDocumentTitle = documentTitleProcessor.create();

    String templateName = "ArticleTemplate";

    outer: try (var document = asciiDoc2.open(source)) {
      var title = findTitle(document);
      var nodes = document.nodes();

      var iter = nodes.iterator();

      if (!iter.hasNext()) {
        break outer;
      }

      var first = iter.next();

      if (!(first instanceof Header header)) {
        break outer;
      }

      nodes = header.nodes();

      iter = nodes.iterator();

      if (!iter.hasNext()) {

      }
    }

    return new DocumentRecord(
      key,
      version,
      source,
      oldDocument,
      oldDocumentTitle,
      pageTitle.toString(),
      templateName
    );
  }

  private Title findTitle(Document document) {
    var nodes = document.nodes();

    var iter = nodes.iterator();

    if (!iter.hasNext()) {
      return null;
    }

    var first = iter.next();

    if (!(first instanceof Header header)) {
      return null;
    }

    for (var node : header.nodes()) {

    }

    return null;
  }

  private Title findTitle0(Header header) { return null; }

}