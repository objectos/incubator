/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.internal;

import java.util.List;
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;

public class Navigation {

  sealed interface Element {}

  sealed interface NavigationOption extends Element {
    default void accept(Builder builder) {
      builder.elements.add(this);
    }
  }

  private static class Builder {
    final String name;

    final GrowableList<Element> elements = new GrowableList<>();

    public Builder(String name) {
      this.name = name;
    }

    public Navigation build() {
      return new Navigation(
        name, elements.toUnmodifiableList()
      );
    }
  }

  record Link(String iref) implements NavigationOption {}

  record LinkTitle(String iref, String title) implements NavigationOption {}

  record LinkList(String iref, List<Element> elements) implements NavigationOption {}

  record Section(String name, List<Element> elements) implements NavigationOption {}

  final String name;

  final UnmodifiableList<Element> elements;

  Navigation(String name, UnmodifiableList<Element> elements) {
    this.name = name;
    this.elements = elements;
  }

  public static Navigation create(String name, NavigationOption... options) {
    var builder = new Builder(name);

    for (var option : options) {
      option.accept(builder);
    }

    return builder.build();
  }

  public static NavigationOption link(String iref) {
    return new Link(iref);
  }

  public static NavigationOption link(String iref, NavigationOption... options) {
    var elements = toElements(options);

    return new LinkList(iref, elements);
  }

  public static NavigationOption link(String iref, String title) {
    return new LinkTitle(iref, title);
  }

  public static NavigationOption section(String name, NavigationOption... options) {
    var elements = toElements(options);

    return new Section(name, elements);
  }

  private static UnmodifiableList<Element> toElements(NavigationOption... options) {
    var list = new GrowableList<Element>();

    for (var option : options) {
      list.add(option);
    }

    return list.toUnmodifiableList();
  }

}