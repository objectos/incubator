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

import br.com.objectos.html.spi.type.DivValue;
import objectos.docs.style.PageSwitcherCss;

final class PageSwitcher extends ThisFragment {

  @Override
  final void definitionImpl() {
    var back = pages.prevKey();

    var next = pages.nextKey();

    nav(
      PageSwitcherCss._NAV,

      div(
        PageSwitcherCss._PREV,

        back != null ? renderPrev(back) : noop()
      ),

      div(
        PageSwitcherCss._NEXT,

        next != null ? renderNext(next) : noop()
      )
    );
  }

  private DivValue renderNext(String nextKey) {
    return a(
      href(pages.href(nextKey)),

      div(
        PageSwitcherCss._TEXT,

        div("Next"),

        span(pages.title(nextKey))
      ),

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
          d("M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z")
        )
      )
    );
  }

  private DivValue renderPrev(String prevKey) {
    return a(
      href(pages.href(prevKey)),

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
          d("M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z")
        )
      ),

      div(
        PageSwitcherCss._TEXT,

        div("Previous"),

        span(pages.title(prevKey))
      )
    );
  }

}