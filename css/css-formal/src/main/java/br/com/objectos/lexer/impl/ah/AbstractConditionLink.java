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

abstract class AbstractConditionLink<SELF extends AbstractConditionLink<SELF>> implements Link, LinkMerger {

  Link failLink;
  Link matchLink;

  AbstractConditionLink() {
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    failLink = failLink == null ? null : failLink.acceptBrickMap(brickMap);
    matchLink = matchLink == null ? null : matchLink.acceptBrickMap(brickMap);
    return this;
  }

  @Override
  public final Link addLast(Link last) {
    failLink = failLink == null ? null : failLink.addLast(last);
    matchLink = matchLink == null ? null : matchLink.addLast(last);
    return this;
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  public final SELF onFail(Link link) {
    failLink = failLink == null ? link : failLink.merge(link);
    return self();
  }

  public final SELF onMatch(Link link) {
    matchLink = matchLink == null ? link : matchLink.merge(link);
    return self();
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    String prefix = w.removePrefix();

    w = matchLink.toString(toStringCondition(w.write(prefix)).begin().write('=')).writeNewLine();
    w = failLink.toString(toStringCondition(w.write(prefix)).begin().write('!'));

    return w;
  }

  final State failState() {
    return failLink != null ? failLink.toState() : null;
  }

  final State matchState() {
    return matchLink != null ? matchLink.toState() : null;
  }

  abstract SELF self();

  abstract StateWriter toStringCondition(StateWriter w);

}