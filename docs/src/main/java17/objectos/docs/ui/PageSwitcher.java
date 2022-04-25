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

import br.com.objectos.html.spi.type.DivValue;
import java.util.IdentityHashMap;
import java.util.Map;
import objectos.docs.style.PageSwitcherCss;
import objectos.ssg.SiteDirectory;
import objectos.ssg.SiteFragment;
import objectos.ssg.SitePage;
import objectos.ssg.SiteVisitor;

public class PageSwitcher extends SiteFragment implements SiteVisitor {

  private DocsPage current;

  private final Map<DocsPage, DocsPage> nextPages = new IdentityHashMap<>();

  private final Map<DocsPage, DocsPage> previousPages = new IdentityHashMap<>();

  public PageSwitcher() {}

  @Override
  public final void postVisitSiteDirectory(SiteDirectory directory) {
    // noop
  }

  public final void set(DocsPage page) {
    this.current = page;
  }

  @Override
  public final void visitSiteDirectory(SiteDirectory directory) {
    // noop
  }

  @Override
  public final void visitSitePage(SitePage page) {
    if (page instanceof DocsPage d) {
      visitDocsPage(d);
    }
  }

  @Override
  protected final void definition() {
    var back = backPage(current);

    var next = nextPages.get(current);

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

  final DocsPage backPage(DocsPage page) {
    return previousPages.get(page);
  }

  private DivValue renderNext(DocsPage next) {
    return a(
      href(next),

      div(
        PageSwitcherCss._TEXT,

        div("Next"),

        span(next.titleText)
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

  private DivValue renderPrev(DocsPage back) {
    return a(
      href(back),

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

        span(back.titleText)
      )
    );
  }

  private void visitDocsPage(DocsPage next) {
    if (current != null) {
      previousPages.put(next, current);

      nextPages.put(current, next);
    }

    current = next;
  }

}