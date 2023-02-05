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

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.UnaryOperator;

class CharArrayLink implements Link, LinkMerger {

  private final Map<Character, CharCell> charMap;
  private final Link failLink;

  public CharArrayLink() {
    this(Collections.emptyMap(), null);
  }

  private CharArrayLink(Map<Character, CharCell> cellMap, Link failLink) {
    this.charMap = cellMap;
    this.failLink = failLink;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharArrayLink(
        mapCharCell(cell -> cell.acceptBrickMap(brickMap)),
        failLink != null ? failLink.acceptBrickMap(brickMap) : null);
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharArrayLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharArrayLink(
        mapCharCell(cell -> cell.addLast(last)),
        failLink != null ? failLink.addLast(last) : null);
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  @Override
  public final Link mergeBrickStartLink(BrickStartLink that) {
    return new CharArrayLink(
        charMap,
        failLink != null ? failLink.merge(that) : that);
  }

  @Override
  public final Link mergeBrickValueOneLink(BrickValueOneLink that) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final Link mergeCharArrayLink(CharArrayLink that) {
    if (failLink != null || that.failLink != null) {
      throw new UnsupportedOperationException("Implement me");
    }

    TreeMap<Character, CharCell> thisMap = new TreeMap<>(charMap);

    Set<Entry<Character, CharCell>> thatEntrySet = that.charMap.entrySet();
    for (Entry<Character, CharCell> thatEntry : thatEntrySet) {
      Character thatKey = thatEntry.getKey();
      CharCell thatValue = thatEntry.getValue();
      thisMap.merge(thatKey, thatValue, (thisVal, thatVal) -> thisVal.merge(thatVal));
    }

    return new CharArrayLink(thisMap, failLink);
  }

  @Override
  public final Link mergeCharExpressionOneLink(CharExpressionOneLink that) {
    char[] charArray = that.toCharArray();
    return putAll(charArray, that.link);
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    char[] charArray = that.toCharArray();
    CharExpressionZeroOrMoreLink zeroOrMore = that.zeroOrMoreLink();
    return putAll(charArray, zeroOrMore);
  }

  @Override
  public final Link mergeCharExpressionZeroOrMoreLink(CharExpressionZeroOrMoreLink that) {
    char[] charArray = that.toCharArray();
    return putAll(charArray, that).merge(that.link);
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    return put(that.value, that.link);
  }

  @Override
  public final Link mergeCharSingleOneOrMoreLink(CharSingleOneOrMoreLink that) {
    CharSingleZeroOrMoreLink zeroOrMore = that.zeroOrMoreLink();
    return put(that.value, zeroOrMore);
  }

  @Override
  public final Link mergeCharSingleZeroOrMoreLink(CharSingleZeroOrMoreLink that) {
    return put(that.value, that).merge(that.link);
  }

  @Override
  public final Link mergeCharSingleOptionalLink(CharSingleOptionalLink that) {
    return put(that.value, that.link).merge(that.link);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return put(that.firstChar(), that.trailingLink(1));
  }

  @Override
  public final Link mergeStringValueLink(StringValueLink that) {
    // TODO fix
    return merge(that.link);
  }

  @Override
  public final Link mergeTrailingLink(TrailingLink that) {
    Link thisFail = failLink != null ? failLink.merge(that) : that;
    return new CharArrayLink(charMap, thisFail);
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final State toState() {
    int size = charMap.size();
    char[] charArray = new char[size];
    State[] charStateArray = new State[size];
    int index = 0;

    for (CharCell cell : charMap.values()) {
      charArray[index] = cell.charValue();
      charStateArray[index] = cell.state();
      index++;
    }

    return new CharArrayState(
        charArray,
        charStateArray,
        failLink == null ? null : failLink.toState());
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    String prefix = w.removePrefix();

    for (CharCell cell : charMap.values()) {
      w = cell.toString(w.write(prefix)).writeNewLine();
    }

    if (failLink != null) {
      return failLink.toString(w.write(prefix));
    } else {
      return w.deleteLastChar();
    }
  }

  private CharCell cell(TreeMap<Character, CharCell> thisMap, char key) {
    return thisMap.computeIfAbsent(key, CharCell::new);
  }

  private Map<Character, CharCell> mapCharCell(UnaryOperator<CharCell> f) {
    TreeMap<Character, CharCell> map = new TreeMap<>();
    for (Character key : charMap.keySet()) {
      CharCell cell = charMap.get(key);
      map.put(key, f.apply(cell));
    }
    return map;
  }

  private CharArrayLink put(char key, Link link) {
    if (failLink != null && !charMap.containsKey(key)) {
      throw new UnsupportedOperationException("Implement me");
    }
    
    TreeMap<Character, CharCell> thisMap = new TreeMap<>(charMap);
    cell(thisMap, key).addLink(link);
    return new CharArrayLink(thisMap, failLink);
  }

  private CharArrayLink putAll(char[] charArray, Link link) {
    if (failLink != null) {
      for (char c : charArray) {
        if (!charMap.containsKey(c)) {
          throw new UnsupportedOperationException("Implement me");
        }
      }
    }

    TreeMap<Character, CharCell> thisMap = new TreeMap<>(charMap);
    for (char key : charArray) {
      cell(thisMap, key).addLink(link);
    }
    return new CharArrayLink(thisMap, failLink);
  }

}