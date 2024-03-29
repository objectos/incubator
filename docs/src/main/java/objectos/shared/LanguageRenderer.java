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
package objectos.shared;

import objectos.css.select.ClassSelector;

public abstract class LanguageRenderer {

  public interface Output {
    void languageSpan(ClassSelector clazz, String contents);

    void languageText(String text);
  }

  private Output out;

  public final void render(Output output, String literal) {
    this.out = output;

    renderImpl(literal);
  }

  abstract void renderImpl(String literal);

  final void span(ClassSelector clazz, char c) {
    span(clazz, Character.toString(c));
  }

  final void span(ClassSelector clazz, String s) {
    out.languageSpan(clazz, s);
  }

  final void text(String s) {
    out.languageText(s);
  }

}