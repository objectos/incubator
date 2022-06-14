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
package br.com.objectos.code.java.io;

import br.com.objectos.code.java.declaration.BodyElement;
import java.util.List;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

abstract class FormattingAction {

  private final MutableList<BodyElement> elements = new MutableList<>();

  private final FormattingAction nextAction;

  FormattingAction(FormattingAction nextAction) {
    this.nextAction = nextAction;
  }

  public abstract void consume(FormattingSource source);

  public abstract void consumeElement(BodyElement element);

  public final void propagateElement(BodyElement element) {
    nextAction.consumeElement(element);
  }

  public List<BodyElement> stream() {
    MutableList<BodyElement> result;
    result = elements();

    List<BodyElement> fromNext;
    fromNext = nextAction.stream();

    for (int i = 0; i < fromNext.size(); i++) {
      BodyElement e;
      e = fromNext.get(i);

      result.add(e);
    }

    return result;
  }

  public final <E extends BodyElement> ImmutableList<E> toImmutableList(Class<E> type) {
    MutableList<E> result;
    result = new MutableList<>();

    List<BodyElement> stream;
    stream = stream();

    for (int i = 0; i < stream.size(); i++) {
      BodyElement source;
      source = stream.get(i);

      E cast;
      cast = type.cast(source);

      result.add(cast);
    }

    return result.toImmutableList();
  }

  MutableList<BodyElement> elements() {
    return elements;
  }

  final void nextAction(FormattingSource source) {
    nextAction.consume(source);
  }

  final void storeElement(BodyElement element) {
    elements.add(element);
  }

}