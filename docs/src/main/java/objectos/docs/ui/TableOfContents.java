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

import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.spi.type.UlValue;
import java.util.HashMap;
import java.util.Map;
import objectos.lang.Check;
import objectos.ssg.SiteDirectory;
import objectos.ssg.SiteFragment;
import objectos.ssg.SitePage;
import objectos.ssg.SitePath;
import objectos.ssg.SiteVisitor;
import objectos.util.GrowableList;

public final class TableOfContents extends SiteFragment implements SiteVisitor {

  private Level currentLevel;

  private boolean increase;

  private final Map<String, RootLevel> roots = new HashMap<>();

  private RootLevel rootLevel;

  @Override
  public final void postVisitSiteDirectory(SiteDirectory directory) {
    currentLevel = currentLevel.decLevel();
  }

  public final void set(DocsPage page) {
    String slug;
    slug = VersionHolder.parse(page);

    set(slug);
  }

  @Override
  public final void visitSiteDirectory(SiteDirectory directory) {
    if (currentLevel == null) {
      String slug;
      slug = VersionHolder.parse(directory);

      RootLevel root;
      root = new RootLevel();

      roots.put(slug, root);

      currentLevel = root;
    } else {
      increase = true;
    }
  }

  @Override
  public final void visitSitePage(SitePage page) {
    if (currentLevel == null) {
      return;
    }

    if (page instanceof DocsPage d) {
      var title = d.titleText;

      var key = d.getClass();

      if (increase) {
        currentLevel = currentLevel.incLevel(title, key);

        increase = false;
      } else {
        currentLevel.add(title, key);
      }
    }
  }

  @Override
  protected final void definition() {
    rootLevel.render();
  }

  final void set(String slug) {
    rootLevel = roots.get(slug);
  }

  private class IncLevel extends RootLevel {

    private final Level parent;

    private final Simple top;

    IncLevel(Level parent, Simple top) {
      this.parent = parent;

      this.top = top;
    }

    @Override
    final Level decLevel() {
      return parent;
    }

    @Override
    final ElementName render() {
      return li(
        top.renderLink(),

        super.render()
      );
    }

  }

  private abstract class Item {

    abstract ElementName render();

  }

  private abstract class Level extends Item {

    abstract void add(String title, Class<? extends SitePath> key);

    abstract Level decLevel();

    abstract Level incLevel(String title, Class<? extends SitePath> key);

  }

  private class RootLevel extends Level {

    private final GrowableList<Item> items = new GrowableList<>();

    @Override
    final void add(String title, Class<? extends SitePath> key) {
      Item simple;
      simple = simple(title, key);

      items.add(simple);
    }

    @Override
    Level decLevel() {
      return null;
    }

    @Override
    final Level incLevel(String title, Class<? extends SitePath> key) {
      Simple top;
      top = simple(title, key);

      IncLevel level;
      level = new IncLevel(this, top);

      items.add(level);

      return level;
    }

    @Override
    ElementName render() {
      int size;
      size = items.size();

      UlValue[] values;
      values = new UlValue[size];

      for (int i = 0; i < size; i++) {
        Item item;
        item = items.get(i);

        values[i] = item.render();
      }

      return ul(values);
    }

    private Simple simple(String title, Class<? extends SitePath> key) {
      Check.notNull(title, "title == null");

      SitePath page;
      page = getObject(key);

      String href;
      href = page.path();

      return new Simple(title, href);
    }

  }

  private class Simple extends Item {

    private final String href;
    private final String title;

    public Simple(String title, String href) {
      this.title = title;

      this.href = href;
    }

    @Override
    final ElementName render() {
      return li(
        renderLink()
      );
    }

    final ElementName renderLink() {
      return a(href(href), t(title));
    }

  }

}