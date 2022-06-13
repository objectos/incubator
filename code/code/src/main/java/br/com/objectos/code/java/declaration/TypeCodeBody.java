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
package br.com.objectos.code.java.declaration;

import br.com.objectos.code.java.element.CodeElement;
import br.com.objectos.code.java.element.Symbols;
import br.com.objectos.code.java.io.CodeWriter;
import br.com.objectos.code.java.io.Section;
import br.com.objectos.code.java.statement.Block;
import java.util.Iterator;
import objectos.util.ImmutableList;

class TypeCodeBody implements CodeElement {

  private final Iterable<? extends CodeElement> elements;

  private TypeCodeBody(Iterable<? extends CodeElement> elements) {
    this.elements = elements;
  }

  static CodeElement of(ImmutableList<? extends BodyElement> bodyElements) {
    return bodyElements.isEmpty()
        ? Block.empty()
        : new TypeCodeBody(bodyElements);
  }

  @Override
  public final CodeWriter acceptCodeWriter(CodeWriter w) {
    w.write('{');
    w.beginSection(Section.BLOCK);

    Iterator<? extends CodeElement> iterator = elements.iterator();
    if (iterator.hasNext()) {
      w.nextLine();
      w.writeCodeElement(iterator.next());
      w.nextLine();
      while (iterator.hasNext()) {
        w.writeCodeElement(iterator.next());
        w.nextLine();
      }
    }

    w.endSection();
    w.writeCodeElement(Symbols.closeBrace());
    return w;
  }

}
