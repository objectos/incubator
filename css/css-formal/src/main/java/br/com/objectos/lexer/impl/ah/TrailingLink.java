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

class TrailingLink implements Link, LinkMerger {

  private final SpecAction action;
  private final Link link;

  TrailingLink(SpecAction action, Link link) {
    this.action = action;
    this.link = link;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return this;
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeTrailingLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    throw new UnsupportedOperationException("This method should not be called for TrailingLink.");
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  @Override
  public final Link mergeTrailingLink(TrailingLink that) {
    // TODO moe @ 28/01/2019: este teste não é suficiente. Estou (erroneamente) assumindo que
    // uma das actions vem de uma string de tamanho fixo, ie 'keyword' e outra de tamanho variável,
    // ie 'identifier'
    SpecAction finalAction = action.rank() < that.action.rank() ? action : that.action;
    return new TrailingLink(finalAction, null);
  }

  public final Link reverse() {
    return link.reverse(this);
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final State toState() {
    return new TrailingState(action);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return action.toString(w.begin().setToken()).begin().write(')');
  }

}