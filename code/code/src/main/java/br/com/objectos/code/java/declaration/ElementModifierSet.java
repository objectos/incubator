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

import br.com.objectos.code.java.element.AbstractCodeElement;
import br.com.objectos.code.java.element.Symbols;
import br.com.objectos.code.java.io.CodeWriter;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import java.util.Iterator;
import java.util.Locale;

abstract class ElementModifierSet extends AbstractCodeElement {

  private final ImmutableList<Modifier> values;

  ElementModifierSet(Builder<?> builder) {
    values = builder.values();
  }

  ElementModifierSet(ImmutableList<Modifier> values) {
    this.values = values;
  }

  @Override
  public final CodeWriter acceptCodeWriter(CodeWriter w) {
    Iterator<Modifier> it;
    it = values.iterator();

    if (it.hasNext()) {
      writeModifier(w, it.next());

      while (it.hasNext()) {
        w.writeCodeElement(Symbols.space());

        writeModifier(w, it.next());
      }
    }

    return w;
  }

  private void writeModifier(CodeWriter w, Modifier modifier) {
    w.writePreIndentation();
    w.write(modifier.toString().toLowerCase(Locale.US));
  }

  static abstract class Builder<E extends ElementModifierSet> {

    final MutableList<Modifier> values = MutableList.create();

    Builder() {}

    public abstract E build();

    final void addModifier(Modifier... modifiers) {
      Checks.checkNotNull(modifiers, "modifiers == null");

      for (int i = 0; i < modifiers.length; i++) {
        Modifier modifier;
        modifier = modifiers[i];

        values.add(modifier);
      }
    }

    final void addModifiers(Iterable<? extends Modifier> modifiers) {
      values.addAllIterable(modifiers);
    }

    final void addWithNullMessageImpl(Modifier modifier, String message) {
      values.addWithNullMessage(modifier, message);
    }

    final ImmutableList<Modifier> values() {
      return values.toImmutableList();
    }

    final Builder<E> withModifier(ElementModifierSet modifier) {
      Checks.checkNotNull(modifier, "modifier == null");

      values.addAll(modifier.values);

      return this;
    }

  }

}