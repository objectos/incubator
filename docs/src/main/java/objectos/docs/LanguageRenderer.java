/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs;

import br.com.objectos.css.select.ClassSelector;
import objectos.docs.style.SyntaxCss;

abstract class LanguageRenderer {

  private DocumentProcessor out;

  public final void render(DocumentProcessor out, String literal) {
    this.out = out;

    out.raw("\n<pre class=\"");
    out.raw(SyntaxCss._PRE.className());
    out.raw("\"><code>");

    renderImpl(literal);

    out.raw("</code></pre>\n");
  }

  abstract void renderImpl(String literal);

  final void span(ClassSelector clazz, char c) {
    span(clazz, Character.toString(c));
  }

  final void span(ClassSelector clazz, String s) {
    out.raw("<span class=\"");
    out.raw(clazz.className());
    out.raw("\">");
    out.escape(s);
    out.raw("</span>");
  }

  final void text(String s) {
    out.escape(s);
  }

}