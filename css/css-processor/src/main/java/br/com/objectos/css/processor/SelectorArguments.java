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
package br.com.objectos.css.processor;

import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.css.select.AttributeSelector;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.AttributeValueSelector;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.select.PseudoClassSelector;
import br.com.objectos.css.select.PseudoElementSelector;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.SimpleSelectorVisitor;
import br.com.objectos.css.select.TypeSelector;

class SelectorArguments implements SimpleSelectorVisitor<Argument, Void> {

  private static final SelectorArguments INSTANCE = new SelectorArguments();

  private SelectorArguments() {}

  public static Argument of(SimpleSelector selector) {
    return selector.acceptSimpleSelectorVisitor(INSTANCE, null);
  }

  @Override
  public final Argument visitAttributeSelector(AttributeSelector selector,
      Void p) {
    return invoke("attr", l(selector.name()));
  }

  @Override
  public final Argument visitAttributeValueSelector(
      AttributeValueSelector selector, Void p) {
    AttributeSelector attr = selector.attribute();
    AttributeValueOperator op = selector.operator();
    return invoke("attr", l(attr.name()), invoke(op.getMethodName(), l(selector.value())));
  }

  @Override
  public final Argument visitClassSelector(ClassSelector selector, Void p) {
    return invoke("cn", l(selector.className()));
  }

  @Override
  public final Argument visitIdSelector(IdSelector selector, Void p) {
    return invoke("id", l(selector.id()));
  }

  @Override
  public final Argument visitPseudoClassSelector(
      PseudoClassSelector selector, Void p) {
    return id(toFieldName(selector.getName()));
  }

  @Override
  public final Argument visitPseudoElementSelector(
      PseudoElementSelector selector, Void p) {
    return id(toFieldName(selector.getName()));
  }

  @Override
  public final Argument visitTypeSelector(TypeSelector selector, Void p) {
    return id(selector.getName());
  }

  private String toFieldName(String name) {
    return JavaNames.toIdentifier(name.replace('-', '_').toUpperCase());
  }

}
