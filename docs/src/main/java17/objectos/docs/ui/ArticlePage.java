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
package objectos.docs.ui;

import br.com.objectos.be.site.HasHref;
import br.com.objectos.html.element.ElementName;

public abstract class ArticlePage extends DocsPage {

  protected ArticlePage() {}

  @Override
  public String topBarTitle() {
    return null;
  }

  protected final ElementName a(Class<? extends HasHref> href, String t) {
    return a(
      href(href),

      t(t)
    );
  }

  protected final void codeXml(String xml) {
    pre(
      code(
        _class("language-xml"),
        t(xml)
      )
    );
  }

  protected abstract void contents();

  @Override
  protected final void uiMain() {
    article(
      f(this::contents)
    );
  }

}