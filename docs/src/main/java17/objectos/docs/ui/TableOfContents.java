/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.ui;

import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.spi.type.UlValue;
import objectos.ssg.SiteFragment;
import objectos.ssg.SitePath;

public final class TableOfContents extends SiteFragment {

  private Level currentLevel;

  private Level rootLevel;

  public final void add(String title, Class<? extends SitePath> key) {
    currentLevel.add(title, key);
  }

  public final void decLevel() {
    currentLevel = currentLevel.decLevel();
  }

  public final void incLevel(String title, Class<? extends SitePath> key) {
    currentLevel = currentLevel.incLevel(title, key);
  }

  public final void set(DocsPage page) {
    configure();
  }

  @Override
  protected void configure() {
    currentLevel = rootLevel = new Level();
  }

  @Override
  protected final void definition() {
    rootLevel.render();
  }

  private class IncLevel extends Level {

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

  private class Level extends Item {

    private final MutableList<Item> items = new MutableList<>();

    final void add(String title, Class<? extends SitePath> key) {
      Item simple;
      simple = simple(title, key);

      items.add(simple);
    }

    Level decLevel() {
      throw new UnsupportedOperationException("root level");
    }

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
      Checks.checkNotNull(title, "title == null");

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