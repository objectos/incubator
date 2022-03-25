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

class BrickValueOneLink extends AbstractBrickValueLink {

  private final boolean acceptBrickMapDone;

  BrickValueOneLink(Object value, Link link) {
    this(value, link, false);
  }

  private BrickValueOneLink(Object value, Link link, boolean acceptBrickMapDone) {
    super(value, link);
    this.acceptBrickMapDone = acceptBrickMapDone;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return acceptBrickMapDone ? acceptBrickMap1(brickMap, true) : acceptBrickMap0(brickMap);
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeBrickValueOneLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new BrickValueOneLink(value, link.addLast(last));
  }

  @Override
  public final Link mergeBrickValueOneLink(BrickValueOneLink that) {
    return new BrickListLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return new BrickListLink().merge(this).merge(that);
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new BrickValueOneLink(value, next));
  }

  @Override
  public final State toState() {
    return new BrickValueOneState(value, link.toState());
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ONE;
  }

  private Link acceptBrickMap0(BrickMap brickMap) {
    Link brickLink = brickMap.getByValue(value);

    if (brickLink != null) {
      return brickLink.addLast(acceptBrickMap1(brickMap, true));
    } else {
      return acceptBrickMap1(brickMap, false);
    }
  }

  private Link acceptBrickMap1(BrickMap brickMap, boolean done) {
    return new BrickValueOneLink(value, link.acceptBrickMap(brickMap), done);
  }

}