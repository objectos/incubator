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

import objectos.util.UnmodifiableList;
import objectos.util.MutableList;

class BrickListLink implements Link, LinkMerger {

  private final Link link;
  private final UnmodifiableList<BrickLink> list;

  BrickListLink() {
    this(UnmodifiableList.of(), null);
  }

  private BrickListLink(UnmodifiableList<BrickLink> list, Link link) {
    this.list = list;
    this.link = link;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    Link result = link;

    for (BrickLink brickLink : list) {
      Link modified = brickLink.acceptBrickMap(brickMap);
      result = result.merge(modified);
    }

    return result;
  }

  @Override
  public final Link addLast(Link last) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  @Override
  public final Link mergeBrickValueOneLink(BrickValueOneLink that) {
    return add(that);
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    return linkMerge(that);
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    return linkMerge(that);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return linkMerge(that);
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

    for (BrickLink link : list) {
      w = link.toString(w.write(prefix)).writeNewLine();
    }

    if (link != null) {
      return link.toString(w.write(prefix));
    } else {
      return w.deleteLastChar();
    }
  }

  private Link add(BrickLink that) {
    MutableList<BrickLink> l;
    l = new MutableList<>();

    l.addAll(list);

    l.add(that);

    return new BrickListLink(l.toUnmodifiableList(), link);
  }

  private Link linkMerge(Link that) {
    return new BrickListLink(
        list,
        link == null ? that : link.merge(that));
  }

}