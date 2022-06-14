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
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class Property extends PropertyClass.Builder {

  private final NamedClass className;
  @SuppressWarnings("unused")
  private final FrameworkGroup group;

  private final ImmutableList<NamedAtMedia> queries;

  private final ImmutableList<PropertyStyle> styles;

  private Property(Builder builder) {
    group = builder.group;
    className = builder.className;
    styles = builder.styles();
    queries = builder.queries.toImmutableList();
  }

  public final PropertyClass toPropertyClass() {
    return build();
  }

  @Override
  final NamedClass className() {
    return className;
  }

  @Override
  final ImmutableList<PropertyAtMedia> queries() {
    MutableList<PropertyAtMedia> medias;
    medias = new MutableList<>();

    for (NamedAtMedia query : queries) {
      PropertyAtMedia m = toPropertyMediaQuery(query);

      medias.add(m);
    }

    return medias.toImmutableList();
  }

  @Override
  final ImmutableList<PropertyStyle> styles() {
    return styles;
  }

  private PropertyAtMedia toPropertyMediaQuery(NamedAtMedia query) {
    return new PropertyAtMedia(query, styles);
  }

  static class Builder {

    private NamedClass className;
    private final FrameworkGroup group;
    private ImmutableList<String> methodNames;
    private final PackageName packageName;

    private Prefix prefix;
    private final MutableList<NamedAtMedia> queries = new MutableList<>();
    private final MutableList<NamedValue> values = new MutableList<>();

    Builder(PackageName packageName, FrameworkGroup group) {
      this.packageName = packageName;
      this.group = group;
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

    final void methodNames(ImmutableList<String> set) {
      methodNames = set;
    }

    final void prefix(Prefix prefix) {
      this.prefix = prefix;
    }

    final void simpleName(String name) {
      className = packageName.nestedPackage(group.name().toLowerCase()).nestedClass(name);
    }

    final ImmutableList<PropertyStyle> styles() {
      MutableList<PropertyStyle> builder;
      builder = new MutableList<>();

      for (int i = 0; i < values.size(); i++) {
        NamedValue value;
        value = values.get(i);

        PropertyStyle style;
        style = toPropertyStyle(value);

        builder.add(style);
      }

      return builder.toImmutableList();
    }

    private PropertyStyle toPropertyStyle(NamedValue value) {
      return new PropertyStyle(value, prefix, methodNames);
    }

  }

}
