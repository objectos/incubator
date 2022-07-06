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

import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.AbstractTemplate;
import br.com.objectos.http.media.ImageType;
import org.asciidoctor.ast.Document;

abstract class ThisTemplate extends AbstractTemplate {

  Pages pages;

  Document document;

  public void set(Pages pages) {
    this.pages = pages;

    document = pages.document();
  }

  @Override
  protected final void definition() {
    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      f(this::body0)
    );

    pages.unset();

    pages = null;
  }

  abstract void body0();

  abstract StyleSheet styleSheet();

  private void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));

    var title = document.getDoctitle();

    title(pages.stripTags(title));

    style(
      raw(styleSheet().toString())
    );
  }

}