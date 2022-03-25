/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.core.object.Checks;

class CharCell {

  final Character key;
  private Link link;

  CharCell(Character key) {
    this(key, StartingLink.INSTANCE);
  }

  private CharCell(Character key, Link link) {
    this.key = key;
    this.link = link;
  }

  public final CharCell acceptBrickMap(BrickMap brickMap) {
    link = link.acceptBrickMap(brickMap);
    return this;
  }

  public final CharCell addLast(Link last) {
    link = link.addLast(last);
    return this;
  }

  public final void addLink(Link newLink) {
    link = link.merge(newLink);
  }

  public final char charValue() {
    return key.charValue();
  }

  public final CharCell merge(CharCell thatValue) {
    Checks.checkArgument(key.equals(thatValue.key), "keys are not equal");
    return new CharCell(key, link.merge(thatValue.link));
  }

  public final State state() {
    return link.toState();
  }

  @Override
  public final String toString() {
    return toString(new StateWriter()).toString();
  }

  public final StateWriter toString(StateWriter w) {
    return link.toString(w.begin().writeQuote(key.charValue()));
  }

}
