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

abstract class AbstractStringLink implements Link, LinkMerger {

  final String string;
  final Link link;

  AbstractStringLink(String string, Link link) {
    this.string = string;
    this.link = link;
  }

  public final String leading(int length) {
    return string.substring(0, length);
  }

  public final int length() {
    return string.length();
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return link.toString(w.begin()
        .writeQuote(string)
        .writeQuantifier(quantifier()));
  }

  final char firstChar() {
    return string.charAt(0);
  }

  abstract Quantifier quantifier();

}