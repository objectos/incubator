/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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

class BrickActionLink implements Link {

  private final SpecAction action;
  private final Link link;

  public BrickActionLink(SpecAction action, Link link) {
    this.action = action;
    this.link = link;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new BrickActionLink(action, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link addLast(Link last) {
    if (link != null) {
      throw new UnsupportedOperationException("Implement me");
    }
    return new BrickActionLink(action, last);
  }

  public final Link reverse() {
    return link.reverse(new BrickActionLink(action, null));
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final State toState() {
    return new BrickActionState(action, link.toState());
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return link.toString(action.toString(w.begin().write('%').setBrick()).write('%'));
  }

}