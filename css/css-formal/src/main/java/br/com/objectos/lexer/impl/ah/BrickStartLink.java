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

class BrickStartLink implements Link, LinkMerger {

  private final Link link;

  BrickStartLink(Link link) {
    this.link = link;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new BrickStartLink(link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeBrickStartLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new BrickStartLink(link.addLast(last));
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  @Override
  public final Link mergeBrickStartLink(BrickStartLink that) {
    return new BrickStartLink(link.merge(that.link));
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final State toState() {
    return new BrickStartState(link.toState());
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return link.toString(w.begin().write("("));
  }

}