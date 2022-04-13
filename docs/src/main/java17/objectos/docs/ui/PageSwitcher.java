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

import br.com.objectos.be.site.SiteFragment;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.html.spi.type.DivValue;
import java.util.IdentityHashMap;
import java.util.Map;

public class PageSwitcher extends SiteFragment {

  static final IdSelector _ID = Css.randomHash(3);

  private final Map<ArticlePage, ArticlePage> backPages;

  private final Locator locator;

  private ArticlePage page;

  public PageSwitcher(Locator locator) {
    this.locator = locator;

    backPages = new IdentityHashMap<>();
  }

  public final void load(ArticlePage page) {
    ArticlePage current;
    current = page;

    for (;;) {
      Class<? extends ArticlePage> key;
      key = current.nextPage();

      if (key == null) {
        break;
      }

      ArticlePage next;
      next = locator.getInstance(key);

      if (next == null) {
        throw new NullPointerException("page not found: " + key);
      }

      backPages.put(next, current);

      current = next;
    }
  }

  public final void set(ArticlePage page) {
    this.page = page;
  }

  @Override
  protected final void definition() {
    ArticlePage back = backPage(page);

    ArticlePage next = null;

    Class<? extends ArticlePage> nextKey = page.nextPage();

    if (nextKey != null) {
      next = getInstance(nextKey);
    }

    div(
      _ID,

      back != null ? renderBack(back) : noop(),

      next != null ? renderNext(next) : noop()
    );
  }

  final ArticlePage backPage(ArticlePage page) {
    return backPages.get(page);
  }

  private DivValue renderBack(ArticlePage back) {
    return a(
      href(back),

      div(back.titleText)
    );
  }

  private DivValue renderNext(ArticlePage next) {
    return a(
      href(next),

      div(next.titleText)
    );
  }

}