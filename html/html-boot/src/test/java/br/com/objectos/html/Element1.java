/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html;

public abstract class Element1<E0> {

  private final Element0 parent;

  Element1(Element0 parent) {
    this.parent = parent;
  }

  @Override
  public final String toString() {
    return parent.toString();
  }

  @SuppressWarnings("unchecked")
  final E0 closeTag() {
    parent.closeTag();
    return (E0) parent;
  }

  final void openTag(String name) {
    parent.openTag(name);
  }

  final void render(ChildDsl<?> child) {
    parent.render(child);
  }

  final void text0(String text) {
    parent.text0(text);
  }

}