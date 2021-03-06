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
package br.com.objectos.css.framework.util;

import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.html.spi.tmpl.Marker;
import br.com.objectos.html.spi.tmpl.Renderer;
import br.com.objectos.html.spi.type.AnyElementValue;

public class ClassSet implements AnyElementValue {

  private static final ClassSet EMPTY = new ClassSet("");
  
  private final String value;

  private ClassSet(String value) {
    this.value = value;
  }

  public static ClassSet of(ClassSelector... values) {
    switch (values.length) {
      case 0:
        return EMPTY;
      case 1:
        return new ClassSet(get(values, 0));
      default:
        StringBuilder value = new StringBuilder();
        value.append(get(values, 0));
        for (int i = 1; i < values.length; i++) {
          value.append(' ');
          value.append(get(values, i));
        }
        return new ClassSet(value.toString());
    }
  }
  
  private static String get(ClassSelector[] values, int index) {
    ClassSelector selector = values[index];
    if (selector == null) {
      throw new NullPointerException("values[" + index + "] == null");
    }
    return selector.className();
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ClassSet)) {
      return false;
    }
    ClassSet that = (ClassSet) obj;
    return value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final void mark(Marker marker) {
    if (!value.isEmpty()) {
      marker.markAttribute();
    }
  }

  @Override
  public final void render(Renderer renderer) {
    if (!value.isEmpty()) {
      renderer.addAttribute("class", value);
    }
  }

}
