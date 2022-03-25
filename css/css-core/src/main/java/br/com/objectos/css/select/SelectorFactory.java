/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.select;

import static br.com.objectos.css.select.Combinator.ADJACENT_SIBLING;
import static br.com.objectos.css.select.Combinator.CHILD;
import static br.com.objectos.css.select.Combinator.DESCENDANT;
import static br.com.objectos.css.select.Combinator.GENERAL_SIBLING;
import static br.com.objectos.css.select.Combinator.LIST;

import br.com.objectos.core.object.Checks;
import br.com.objectos.css.select.Selector.Builder;

public class SelectorFactory {

  private SelectorFactory() {}

  public static UniversalSelector any() {
    return UniversalSelector.getInstance();
  }

  public static AttributeSelector attr(String name) {
    Checks.checkNotNull(name, "name == null");
    return new AttributeSelector(name);
  }

  public static AttributeValueSelector attr(String name, AttributeValueElement element) {
    AttributeSelector previous = attr(name);
    Checks.checkNotNull(element, "element == null");
    return new AttributeValueSelector(previous, element);
  }

  public static AttributeValueSelector attr(String name, AttributeValueOperator op, String value) {
    AttributeSelector previous = attr(name);
    Checks.checkNotNull(op, "op == null");
    Checks.checkNotNull(value, "value == null");
    return new AttributeValueSelector(previous, op, value);
  }

  public static ClassSelector cn(String className) {
    Checks.checkNotNull(className, "className == null");
    return new ClassSelector(className);
  }

  public static AttributeValueElement contains(String value) {
    return AttributeValueOperator.CONTAINS.withValue(value);
  }

  public static ClassSelector dot(String className) {
    Checks.checkNotNull(className, "className == null");
    return new ClassSelector(className);
  }

  public static AttributeValueElement endsWith(String value) {
    return AttributeValueOperator.ENDS_WITH.withValue(value);
  }

  public static AttributeValueElement eq(String value) {
    return AttributeValueOperator.EQUALS.withValue(value);
  }

  public static Combinator gt() {
    return CHILD;
  }

  public static IdSelector id(String id) {
    Checks.checkNotNull(id, "id == null");
    return new IdSelector(id);
  }

  public static AttributeValueElement lang(String value) {
    return AttributeValueOperator.HYPHEN.withValue(value);
  }

  public static SelectorList list(Selector... selectors) {
    return new SelectorList(selectors);
  }

  public static Combinator or() {
    return LIST;
  }

  public static Combinator plus() {
    return ADJACENT_SIBLING;
  }

  public static Selector sel(Selector selector) {
    return Checks.checkNotNull(selector, "selector == null");
  }

  public static Selector sel(SelectorElement... elements) {
    Checks.checkNotNull(elements, "elements == null");
    Builder b = Selector.builder();

    for (int i = 0; i < elements.length; i++) {
      SelectorElement e = Checks.checkNotNull(elements[i], "elements[" + i + "] == null");
      e.acceptSelectorBuilderDsl(b);
    }

    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3,
      SelectorElement e4) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    e4.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3,
      SelectorElement e4,
      SelectorElement e5) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    e4.acceptSelectorBuilderDsl(b);
    e5.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3,
      SelectorElement e4,
      SelectorElement e5,
      SelectorElement e6) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    e4.acceptSelectorBuilderDsl(b);
    e5.acceptSelectorBuilderDsl(b);
    e6.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3,
      SelectorElement e4,
      SelectorElement e5,
      SelectorElement e6,
      SelectorElement e7) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    e4.acceptSelectorBuilderDsl(b);
    e5.acceptSelectorBuilderDsl(b);
    e6.acceptSelectorBuilderDsl(b);
    e7.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3,
      SelectorElement e4,
      SelectorElement e5,
      SelectorElement e6,
      SelectorElement e7,
      SelectorElement e8) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Checks.checkNotNull(e8, "e8 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    e4.acceptSelectorBuilderDsl(b);
    e5.acceptSelectorBuilderDsl(b);
    e6.acceptSelectorBuilderDsl(b);
    e7.acceptSelectorBuilderDsl(b);
    e8.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  // ClassSelector

  public static Selector sel(
      SelectorElement e1,
      SelectorElement e2,
      SelectorElement e3,
      SelectorElement e4,
      SelectorElement e5,
      SelectorElement e6,
      SelectorElement e7,
      SelectorElement e8,
      SelectorElement e9) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Checks.checkNotNull(e8, "e8 == null");
    Checks.checkNotNull(e9, "e9 == null");
    Builder b = Selector.builder();
    e1.acceptSelectorBuilderDsl(b);
    e2.acceptSelectorBuilderDsl(b);
    e3.acceptSelectorBuilderDsl(b);
    e4.acceptSelectorBuilderDsl(b);
    e5.acceptSelectorBuilderDsl(b);
    e6.acceptSelectorBuilderDsl(b);
    e7.acceptSelectorBuilderDsl(b);
    e8.acceptSelectorBuilderDsl(b);
    e9.acceptSelectorBuilderDsl(b);
    return b.build();
  }

  public static Combinator sp() {
    return DESCENDANT;
  }

  public static UniversalSelector star() {
    return UniversalSelector.getInstance();
  }

  public static AttributeValueElement startsWith(String value) {
    return AttributeValueOperator.STARTS_WITH.withValue(value);
  }

  public static Combinator tilde() {
    return GENERAL_SIBLING;
  }

  public static AttributeValueElement wsList(String value) {
    return AttributeValueOperator.WS_LIST.withValue(value);
  }

}
