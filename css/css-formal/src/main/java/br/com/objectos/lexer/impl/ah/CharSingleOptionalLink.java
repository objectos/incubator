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

class CharSingleOptionalLink extends AbstractCharSingleLink {

  CharSingleOptionalLink(char value, Link link) {
    super(value, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharSingleOptionalLink(value, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharSingleOptionalLink(this);
  }

  @Override
  public Link addLast(Link last) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeCharSingleOptionalLink(CharSingleOptionalLink that) {
    if (hasSameValue(that)) {
      return new CharSingleOptionalLink(
          value,
          link.merge(that.link));
    } else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return new CharArrayLink()
        .merge(new CharSingleOneLink(value, link))
        .merge(link)
        .merge(that);
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharSingleOptionalLink(value, next));
  }

  @Override
  public final State toState() {
    return new CharSingleOptionalState(value, link.toState());
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.OPTIONAL;
  }

}