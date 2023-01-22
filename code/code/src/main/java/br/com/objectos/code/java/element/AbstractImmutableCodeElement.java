/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.element;

import br.com.objectos.code.java.io.CodeWriter;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

public abstract class AbstractImmutableCodeElement extends AbstractCodeElement {

  private final UnmodifiableList<CodeElement> elements;

  protected AbstractImmutableCodeElement(CodeElement... elements) {
    this.elements = UnmodifiableList.copyOf(elements);
  }

  protected AbstractImmutableCodeElement(UnmodifiableList<CodeElement> elements) {
    this.elements = elements;
  }

  @Override
  public final CodeWriter acceptCodeWriter(CodeWriter w) {
    w.writeCodeElements(elements);
    return w;
  }

  protected final UnmodifiableList<CodeElement> appendNextLine() {
    GrowableList<CodeElement> list;
    list = new GrowableList<>();

    list.addAll(elements);

    list.add(NewLine.nextLine());

    return list.toUnmodifiableList();
  }

}