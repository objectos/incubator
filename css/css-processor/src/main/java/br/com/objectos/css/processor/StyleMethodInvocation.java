/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.MethodInvocation;
import objectos.css.keyword.StandardKeyword;
import objectos.css.property.StandardPropertyName;
import objectos.css.select.AttributeValueOperator;
import objectos.css.select.Combinator;
import objectos.css.select.SimpleSelector;
import objectos.css.select.UniversalSelector;
import objectos.css.type.ColorName;
import objectos.css.type.LengthUnit;
import objectos.util.GrowableList;

class StyleMethodInvocation {

  private DeclarationMethodInvocation currentDeclaration;
  private final GrowableList<Argument> declarations = new GrowableList<>();

  private final GrowableList<ArgumentsElement> selector = new GrowableList<>();

  StyleMethodInvocation() {}

  public final MethodInvocation build() {
    buildCurrentDeclarationIfNecessary();

    GrowableList<ArgumentsElement> arguments;
    arguments = new GrowableList<>();

    arguments.add(nl());
    arguments.addAll(selector);
    arguments.add(nl());

    for (int i = 0; i < declarations.size(); i++) {
      Argument declaration;
      declaration = declarations.get(i);

      arguments.add(nl());
      arguments.add(declaration);
    }

    if (!declarations.isEmpty()) {
      arguments.add(nl());
    }

    return invoke("style", arguments);
  }

  final void addAttributeSelector(String attributeName) {
    selector.add(invoke("attr", l(attributeName)));
  }

  final void addAttributeValueSelector(
      String attributeName, AttributeValueOperator operator, String value) {
    selector.add(invoke("attr", l(attributeName), invoke(operator.getMethodName(), l(value))));
  }

  final void addClassSelector(String className) {
    selector.add(invoke("cn", l(className)));
  }

  final void addColor(ColorName value) {
    currentDeclaration.addColor(value);
  }

  final void addColor(String value) {
    currentDeclaration.addColor(value);
  }

  final void addCombinator(Combinator combinator) {
    String methodName;
    methodName = combinator.getJavaName();

    selector.add(invoke(methodName));

    if (combinator == Combinator.LIST) {
      selector.add(nl());
    }
  }

  final void addIdSelector(String id) {
    selector.add(invoke("id", l(id)));
  }

  final void addKeyword(StandardKeyword value) {
    currentDeclaration.addKeyword(value);
  }

  final void addKeyword(String keyword) {
    currentDeclaration.addKeyword(keyword);
  }

  final void addLength(LengthUnit unit, double value) {
    currentDeclaration.addLength(unit, value);
  }

  final void addLength(LengthUnit unit, int value) {
    currentDeclaration.addLength(unit, value);
  }

  final void addMultiDeclarationSeparator() {
    currentDeclaration = currentDeclaration.addMultiDeclarationSeparator();
  }

  final void addPercentage(double value) {
    currentDeclaration.addPercentage(value);
  }

  final void addPercentage(int value) {
    currentDeclaration.addPercentage(value);
  }

  final void addPropertyName(StandardPropertyName name) {
    buildCurrentDeclarationIfNecessary();
    currentDeclaration = DeclarationMethodInvocation.getSimple(name);
  }

  final void addRgb(double r, double g, double b) {
    currentDeclaration.addRgb(r, g, b);
  }

  final void addRgb(double r, double g, double b, double alpha) {
    currentDeclaration.addRgb(r, g, b, alpha);
  }

  final void addRgb(int r, int g, int b) {
    currentDeclaration.addRgb(r, g, b);
  }

  final void addRgb(int r, int g, int b, double alpha) {
    currentDeclaration.addRgb(r, g, b, alpha);
  }

  final void addRgba(double r, double g, double b, double alpha) {
    currentDeclaration.addRgba(r, g, b, alpha);
  }

  final void addRgba(int r, int g, int b, double alpha) {
    currentDeclaration.addRgba(r, g, b, alpha);
  }

  final void addSimpleSelector(SimpleSelector element) {
    Argument argument;
    argument = SelectorArguments.of(element);

    selector.add(argument);
  }

  final void addString(String value) {
    currentDeclaration.addString(value);
  }

  final void addUniversalSelector(UniversalSelector universal) {
    selector.add(invoke("any"));
  }

  final void addValue(double value) {
    currentDeclaration.addValue(value);
  }

  final void addValue(int value) {
    currentDeclaration.addValue(value);
  }

  private void buildCurrentDeclarationIfNecessary() {
    if (currentDeclaration == null) {
      return;
    }

    MethodInvocation declarationMethod;
    declarationMethod = currentDeclaration.build();

    declarations.add(declarationMethod);

    currentDeclaration = null;
  }

}
