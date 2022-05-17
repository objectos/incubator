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
package br.com.objectos.css.specgen.spec;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.map.ImmutableMap;
import br.com.objectos.core.map.MutableMap;
import br.com.objectos.core.map.MutableOrderedMap;
import objectos.lang.Checks;

public class Spec {

  private final ImmutableMap<String, Property> properties;

  private Spec(ImmutableMap<String, Property> properties) {
    this.properties = properties;
  }

  public static Builder builder() {
    return new Builder();
  }

  public final Property getProperty(String name) {
    Checks.checkArgument(properties.containsKey(name), name, " not found");

    return properties.get(name);
  }

  public final ImmutableList<Property> properties() {
    return ImmutableList.copyOf(properties.values());
  }

  public static class Builder {

    private final MutableMap<String, Property> properties = MutableOrderedMap.create();

    public final Builder addProperty(Property property) {
      properties.put(property.name(), property);
      return this;
    }

    public Spec build() {
      return new Spec(
          properties.toImmutableMap()
      );
    }

  }

}