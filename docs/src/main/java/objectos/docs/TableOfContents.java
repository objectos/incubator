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

import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.spi.type.UlValue;
import java.util.List;
import objectos.lang.ToString;
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;

final class TableOfContents extends ThisFragment {

  sealed abstract class Item implements ToString.Formattable permits Level, Simple {
    final String key;

    Item(String key) { this.key = key; }
  }

  final class Level extends Item {
    final GrowableList<Item> items = new GrowableList<>();
    final Level parent;

    Level(Level parent, String key) {
      super(key);
      this.parent = parent;
    }

    @Override
    public final void formatToString(StringBuilder sb, int level) {
      ToString.format(
        sb, level, this,
        "key", key,
        "items", items
      );
    }

    @Override
    public final String toString() {
      return ToString.of(this);
    }

    final Level addIndex(String index) {
      if (isChild(index)) {
        return addLevel(index);
      } else {
        if (parent != null) {
          return parent.addIndex(index);
        } else {
          var level = new Level(null, index);

          TableOfContents.this.items.add(level);

          return level;
        }
      }
    }

    final Level addLevel(String key) {
      var level = new Level(this, key);

      items.add(level);

      return level;
    }

    final void addSimple(String key) {
      items.add(
        new Simple(key)
      );
    }

    final boolean isChild(String childKey) {
      int index = key.lastIndexOf('/');

      var sub = key.substring(0, index);

      return childKey.startsWith(sub);
    }
  }

  final class Simple extends Item {
    Simple(String key) { super(key); }

    @Override
    public final void formatToString(StringBuilder sb, int level) {
      ToString.format(
        sb, level, this,
        "key", key
      );
    }

    @Override
    public final String toString() {
      return ToString.of(this);
    }
  }

  private final GrowableList<Item> items = new GrowableList<>();

  private Level level;

  public final void clear() {
    items.clear();

    level = null;
  }

  public final void put(String key) {
    var parts = key.split("/");

    if (parts.length == 1) {
      var item = new Simple(key);

      items.add(item);

      return;
    }

    if (level == null) {
      level = new Level(level, key);

      items.add(level);

      return;
    }

    var last = parts[parts.length - 1];

    if (last.equals("index")) {
      level = level.addIndex(key);
    } else {
      level.addSimple(key);
    }
  }

  @Override
  final void definitionImpl() {
    ul0(items);
  }

  final UnmodifiableList<Item> toUnmodifiableList() {
    return items.toUnmodifiableList();
  }

  private ElementName ul0(List<? extends Item> items) {
    var size = items.size();

    var values = new UlValue[size];

    for (int i = 0; i < size; i++) {
      var item = items.get(i);

      var href = pages.href(item.key);

      var title = trailTitle(item.key);

      values[i] = li(
        a(href(href), t(title)),

        item instanceof Level level ? ul0(level.items) : noop()
      );
    }

    return ul(values);
  }

}