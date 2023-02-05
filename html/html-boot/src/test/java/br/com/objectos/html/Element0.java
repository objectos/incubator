/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
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

public abstract class Element0 {

  final HtmlDsl dsl;

  Element0(HtmlDsl dsl) {
    this.dsl = dsl;
  }

  @Override
  public final String toString() {
    return dsl.toString();
  }

  final void closeTag() {
    dsl.closeTag();
  }

  final void openTag(String name) {
    dsl.openTag(name);
  }

  final void render(ChildDsl<?> child) {
    child.render(dsl);
  }

  final void text0(String text) {
    dsl.text0(text);
  }

}