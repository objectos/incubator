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
package br.com.objectos.css.select;

import br.com.objectos.html.spi.tmpl.Marker;
import br.com.objectos.html.spi.tmpl.Renderer;
import br.com.objectos.html.spi.type.AnyElementValue;

public class ClassSelector extends SimpleSelector implements AnyElementValue {

  private final String className;

  ClassSelector(String className) {
    this.className = className;
  }

  @Override
  public final <R, P> R acceptSimpleSelectorVisitor(SimpleSelectorVisitor<R, P> visitor, P p) {
    return visitor.visitClassSelector(this, p);
  }

  public final String className() {
    return className;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof ClassSelector)) {
      return false;
    }
    return toString().equals(obj.toString());
  }

  @Override
  public final int hashCode() {
    return className.hashCode();
  }

  @Override
  public final void mark(Marker marker) {
    marker.markAttribute();
  }

  @Override
  public final boolean matches(Selectable element) {
    return element.hasAttributeValue("class", className);
  }

  @Override
  public final void render(Renderer renderer) {
    renderer.addAttribute("class", className);
  }

  @Override
  public final String toString() {
    return "." + className;
  }

}