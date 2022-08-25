/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.maven.plugin.framework;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkPropertyState;
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;

class Property extends PropertyClass.Builder {

  static class Builder {

    private NamedClass className;

    private final FrameworkGroup group;

    private UnmodifiableList<String> methodNames;

    private final PackageName packageName;

    private final GrowableList<NamedAtMedia> queries = new GrowableList<>();

    private final GrowableList<NamedValue> values = new GrowableList<>();

    final UnmodifiableList<FrameworkPropertyState> states;

    Builder(PackageName packageName,
            FrameworkGroup group,
            UnmodifiableList<FrameworkPropertyState> states) {
      this.packageName = packageName;

      this.group = group;

      this.states = states;
    }

    public final Property build() {
      return new Property(this);
    }

    final void addNamedQuery(NamedAtMedia query) {
      queries.add(query);
    }

    final void addNamedValue(NamedValue value) {
      values.add(value);
    }

    final void methodNames(UnmodifiableList<String> set) {
      methodNames = set;
    }

    final void simpleName(String name) {
      className = packageName.nestedPackage(group.name().toLowerCase()).nestedClass(name);
    }

    final UnmodifiableList<PropertyStyle> styles() {
      var builder = new GrowableList<PropertyStyle>();

      for (int i = 0; i < values.size(); i++) {
        var value = values.get(i);

        var style = toPropertyStyle(value);

        builder.add(style);
      }

      return builder.toUnmodifiableList();
    }

    private PropertyStyle toPropertyStyle(NamedValue value) {
      return new PropertyStyle(value, methodNames);
    }

  }

  private final NamedClass className;

  @SuppressWarnings("unused")
  private final FrameworkGroup group;

  private final UnmodifiableList<NamedAtMedia> queries;

  private final UnmodifiableList<FrameworkPropertyState> states;

  private final UnmodifiableList<PropertyStyle> styles;

  private Property(Builder builder) {
    group = builder.group;

    className = builder.className;

    states = builder.states;

    styles = builder.styles();

    queries = builder.queries.toUnmodifiableList();
  }

  public final PropertyClass toPropertyClass() { return build(); }

  @Override
  final NamedClass className() { return className; }

  @Override
  final UnmodifiableList<PropertyAtMedia> queries() {
    var medias = new GrowableList<PropertyAtMedia>();

    for (var query : queries) {
      var m = toPropertyMediaQuery(query);

      medias.add(m);
    }

    return medias.toUnmodifiableList();
  }

  @Override
  final UnmodifiableList<PropertyState> states() {
    var result = new GrowableList<PropertyState>();

    for (var state : states) {
      var s = new PropertyState(state, styles);

      result.add(s);
    }

    return result.toUnmodifiableList();
  }

  @Override
  final UnmodifiableList<PropertyStyle> styles() { return styles; }

  private PropertyAtMedia toPropertyMediaQuery(NamedAtMedia query) {
    return new PropertyAtMedia(query, styles);
  }

}
