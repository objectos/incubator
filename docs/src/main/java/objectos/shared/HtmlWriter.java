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
package objectos.shared;

import br.com.objectos.html.writer.SimpleTemplateWriter;
import objectos.html.tmpl.ElementName;
import objectos.html.tmpl.StandardElementName;

public class HtmlWriter extends SimpleTemplateWriter {

  private boolean newLine;

  public HtmlWriter() {
    super(new StringBuilder());
  }

  @Override
  public final void visitEndTag(ElementName element) {
    visitEndTag(element.getName());

    if (isBlock(element)) {
      append('\n');

      newLine = true;
    } else {
      newLine = false;
    }
  }

  @Override
  public final void visitStartTag(ElementName element) {
    if (!newLine && isBlock(element)) {
      append('\n');
    }

    newLine = false;

    visitStartTag(element.getName());
  }

  private boolean isBlock(ElementName element) {
    if (element instanceof StandardElementName name) {
      return switch (name) {
        case HTML, HEAD, TITLE, META, LINK, STYLE, SCRIPT, BODY,
             DIV, HEADER, NAV, MAIN, ARTICLE, SECTION, FOOTER,
             H1, H2, H3, H4, H5, H6,
             P, PRE,
             UL, OL, LI,
             BLOCKQUOTE -> true;

        default -> false;
      };
    }

    return false;
  }

}