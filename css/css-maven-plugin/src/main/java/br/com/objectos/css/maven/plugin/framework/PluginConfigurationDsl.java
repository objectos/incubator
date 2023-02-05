/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
import br.com.objectos.css.config.framework.ConfigurationDsl;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.type.ColorKind;
import br.com.objectos.css.type.Creator;
import br.com.objectos.css.type.LengthUnit;
import br.com.objectos.css.type.Marker;
import br.com.objectos.css.type.Value;
import java.util.EnumMap;
import java.util.Map;
import objectos.lang.Check;
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;

class PluginConfigurationDsl implements ConfigurationDsl {

  private final Map<FrameworkGroup, GrowableList<PropertyClass>> groupMap
      = new EnumMap<>(FrameworkGroup.class);

  private PackageName packageName = PackageName.unnamed();

  @Override
  public final void defineProperty(
      FrameworkGroup group,
      FrameworkSimpleName simpleName,
      FrameworkMethodSet methods,
      FrameworkNamedValueSet values,
      FrameworkAtMediaSet queries,
      FrameworkPropertyState... states) {

    var statesCopy = UnmodifiableList.copyOf(states);

    var builder = new Property.Builder(packageName, group, statesCopy);

    simpleName.acceptFrameworkObjectVisitor(builder);

    methods.acceptFrameworkObjectVisitor(builder);

    values.acceptFrameworkObjectVisitor(builder);

    queries.acceptFrameworkObjectVisitor(builder);

    var property = builder.build();

    var propertyClass = property.toPropertyClass();

    groupMap.computeIfAbsent(group, k -> new GrowableList<>()).add(propertyClass);

  }

  @Override
  public final FrameworkAtMedia getAtMedia(String name, FrameworkAtMediaElement... elements) {
    Check.notNull(name, "name == null");
    Check.notNull(elements, "elements == null");
    UnmodifiableList<FrameworkAtMediaElement> set = UnmodifiableList.copyOf(elements);
    return new NamedAtMedia(name, set);
  }

  @Override
  public final FrameworkAtMediaSet getAtMediaSet(FrameworkAtMedia... queries) {
    switch (queries.length) {
      case 0:
        return AtMediaSet.EMPTY;
      default:
        UnmodifiableList<FrameworkAtMedia> set = UnmodifiableList.copyOf(queries);
        return new AtMediaSet(set);
    }
  }

  @Override
  public final FrameworkAtMediaDeclaration getDeclaration(
      StandardPropertyName propertyName, Value value) {
    Check.notNull(propertyName, "propertyName == null");
    Check.notNull(value, "value == null");
    return new AtMediaDeclaration(propertyName, value);
  }

  @Override
  public final Value getInt(int value) {
    return new Value() {
      @Override
      public final void acceptValueCreator(Creator creator) {
        creator.createInt(value);
      }

      @Override
      public final void acceptValueMarker(Marker marker) {
        marker.markInt();
      }
    };
  }

  @Override
  public final Value getLength(LengthUnit unit, double value) {
    return new Value() {
      @Override
      public final void acceptValueCreator(Creator creator) {
        creator.createLength(unit, value);
      }

      @Override
      public final void acceptValueMarker(Marker marker) {
        marker.markDoubleLength();
      }
    };
  }

  @Override
  public final Value getLength(LengthUnit unit, int value) {
    return new Value() {
      @Override
      public final void acceptValueCreator(Creator creator) {
        creator.createLength(unit, value);
      }

      @Override
      public final void acceptValueMarker(Marker marker) {
        marker.markIntLength();
      }
    };
  }

  @Override
  public final FrameworkMethodSet getMethodSet(String... names) {
    UnmodifiableList<String> set;
    set = UnmodifiableList.copyOf(names);

    return new MethodSet(set);
  }

  @Override
  public final FrameworMultiElement getMultiElement(Value... values) {
    UnmodifiableList<Value> arguments;
    arguments = UnmodifiableList.copyOf(values);

    return new MultiElement(arguments);
  }

  @Override
  public final FrameworkNamedValue getNamedValue(String name, double value) {
    Check.notNull(name, "name == null");

    return new NamedDouble(name, value);
  }

  @Override
  public final FrameworkNamedValue getNamedValue(String name, FrameworkAtMediaSet set) {
    Check.notNull(name, "name == null");
    Check.notNull(set, "set == null");

    return new NamedAtMediaSet(name, set);
  }

  @Override
  public final FrameworkNamedValue getNamedValue(String name, FrameworMultiElement... elements) {
    Check.notNull(name, "name == null");

    UnmodifiableList<FrameworMultiElement> list = UnmodifiableList.copyOf(elements);

    return new NamedMulti(name, list);
  }

  @Override
  public final FrameworkNamedValue getNamedValue(String name, int value) {
    Check.notNull(name, "name == null");

    return new NamedInt(name, value);
  }

  @Override
  public final FrameworkNamedValue getNamedValue(String name, Value... values) {
    Check.notNull(name, "name == null");

    var arguments = UnmodifiableList.copyOf(values);

    return new NamedArguments(name, arguments);
  }

  @Override
  public final FrameworkNamedValueSet getNamedValueSet(FrameworkNamedValue... values) {
    UnmodifiableList<FrameworkNamedValue> set;
    set = UnmodifiableList.copyOf(values);

    return new NamedValueSet(set);
  }

  @Override
  public final Value getPercentage(double value) {
    return new Value() {
      @Override
      public final void acceptValueCreator(Creator creator) {
        creator.createPercentage(value);
      }

      @Override
      public final void acceptValueMarker(Marker marker) {
        marker.markDoublePercentage();
      }
    };
  }

  @Override
  public final Value getPercentage(int value) {
    return new Value() {
      @Override
      public final void acceptValueCreator(Creator creator) {
        creator.createPercentage(value);
      }

      @Override
      public final void acceptValueMarker(Marker marker) {
        marker.markIntPercentage();
      }
    };
  }

  @Override
  public final Value getRgba(int r, int g, int b, double alpha) {
    return new Value() {
      @Override
      public final void acceptValueCreator(Creator creator) {
        creator.createRgba(r, g, b, alpha);
      }

      @Override
      public final void acceptValueMarker(Marker marker) {
        marker.markColor(ColorKind.RGBA_INT);
      }
    };
  }

  @Override
  public final FrameworkSimpleName getSimpleName(String name) {
    Check.notNull(name, "name == null");
    return new SimpleName(name);
  }

  @Override
  public final void setPackageName(String name) {
    this.packageName = PackageName.named(name);
  }

  final void execute(ConfigurationAdapter adapter) {
    executeFramework(adapter);
    executeProperties(adapter);
  }

  final void executeFramework(ConfigurationAdapter adapter) {
    FrameworkClass framework = new FrameworkClass(packageName);

    for (FrameworkGroup group : groupMap.keySet()) {
      framework.addGroup(group, groupMap.get(group));
    }

    framework.execute(adapter);
  }

  final void executeProperties(ConfigurationAdapter adapter) {
    for (Iterable<PropertyClass> properties : groupMap.values()) {
      for (PropertyClass property : properties) {
        property.execute(adapter);
      }
    }
  }

}
