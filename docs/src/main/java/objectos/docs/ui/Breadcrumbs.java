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

import br.com.objectos.html.spi.type.OlValue;
import objectos.docs.style.BreadcrumbCss;
import objectos.ssg.SiteFragment;
import objectos.util.UnmodifiableList;

public final class Breadcrumbs extends SiteFragment {

  private UnmodifiableList<DocsPage> trail;

  @Override
  protected final void definition() {
    OlValue[] items;
    items = new OlValue[trail.size()];

    DocsPage page;
    page = trail.get(0);

    items[0] = li(
      a(href(page), t(page.titleText))
    );

    for (int i = 1, size = trail.size(); i < size; i++) {
      page = trail.get(i);

      items[i] = li(
        svg(
          xmlns("http://www.w3.org/2000/svg"),
          fill("currentColor"),
          viewBox("0 0 24 24"),
          height("16"),
          width("16"),
          path(
            fill("none"),
            d("M0 0h24v24H0z")
          ),
          path(
            d("M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z")
          )
        ),

        a(href(page), t(page.titleText))
      );
    }

    nav(
      BreadcrumbCss.ID,

      ol(
        items
      )
    );
  }

  final void set(DocsPage docsPage) {
    Pages pages;
    pages = getObject(Pages.class);

    trail = pages.trail(docsPage);
  }

  final boolean shouldRender() {
    return trail != null && !trail.isEmpty();
  }

}