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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.parser.select.SelectorParser;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.Selector;
import br.com.objectos.css.type.Color;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.Creator;
import br.com.objectos.css.type.Marker;
import objectos.lang.Checks;

class Identifier extends ThisValue {

  private ColorName color;

  private StandardKeyword keyword;
  private final String name;

  public Identifier(String name) {
    this.name = Checks.checkNotNull(name, "name == null");

    if (Keywords.isKeyword(name)) {
      keyword = Keywords.getByName(name);
    }

    else if (Color.isColor(name)) {
      color = Color.getByName(name);
    }
  }

  @Override
  public final void acceptValueCreator(Creator creator) {
    if (keyword == null && color == null) {
      creator.createKeyword(name);
    }
  }

  @Override
  public final void acceptValueMarker(Marker marker) {
    if (keyword != null) {
      marker.addKeyword(keyword);
    }

    else if (color != null) {
      marker.addColor(color);
    }

    else {
      marker.markKeyword();
    }
  }

  public final Selector asSelector() {
    return SelectorParser.parse(name.trim());
  }

  public final ThisValue asValue() {
    return this;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Identifier)) {
      return false;
    }
    Identifier that = (Identifier) obj;
    return name.equals(that.name);
  }

  public final StandardPropertyName getPropertyName() {
    StandardPropertyName propertyName = StandardPropertyName.getByName(name);

    if (propertyName == null) {
      throw new UnsupportedOperationException("Implement me");
    }

    return propertyName;
  }

  @Override
  public final int hashCode() {
    return name.hashCode();
  }

  @Override
  public final String toString() {
    return name;
  }

}