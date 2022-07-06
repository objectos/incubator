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

import br.com.objectos.html.spi.type.OlValue;
import objectos.docs.style.BreadcrumbCss;

final class Breadcrumbs extends ThisFragment {

  @Override
  final void definitionImpl() {
    var trail = pages.trail();

    var items = new OlValue[trail.size()];

    var key = trail.get(0);

    items[0] = li(
      a(href(pages.href(key)), t(title0(key)))
    );

    for (int i = 1, size = trail.size(); i < size; i++) {
      key = trail.get(i);

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

        a(href(pages.href(key)), t(title0(key)))
      );
    }

    nav(
      BreadcrumbCss.ID,

      ol(
        items
      )
    );
  }

  private String title0(String key) {
    var document = pages.document(key);

    var defaultValue = document.getDoctitle();

    return (String) document.getAttribute("trail-title", defaultValue);
  }

}