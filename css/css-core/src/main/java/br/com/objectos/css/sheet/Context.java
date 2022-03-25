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
package br.com.objectos.css.sheet;

import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;

abstract class Context<E extends Exception> {

  Context() {}

  public static <E extends Exception> Context<E> getStart() {
    return ContextStart.get();
  }

  final Context<E> toDeclarationStart() {
    return ContextDeclarationStart.get();
  }

  final Context<E> toDeclarationValues() {
    return ContextDeclarationValues.get();
  }

  final Context<E> toFunctionStart() {
    return ContextFunctionStart.get();
  }

  final Context<E> toFunctionValues() {
    return ContextFunctionValues.get();
  }

  @SuppressWarnings("unchecked")
  final Context<E> toMediaOrSheetBody(Adapter<?> a) {
    Body body;
    body = a.peekBody();

    return (Context<E>) body.getContext();
  }

  final Context<E> toMediaQuery() {
    return ContextMediaQuery.get();
  }

  final Context<E> toMediaQueryDeclaration() {
    return ContextMediaQueryDeclaration.get();
  }

  final Context<E> toMediaStart() {
    return ContextAtMediaStart.get();
  }

  final Context<E> toRuleStart() {
    return ContextRuleStart.get();
  }

  final Context<E> toSelector() {
    return ContextSelector.get();
  }

  Context<E> visitAngle(Adapter<E> a, AngleUnit unit, double value) throws E {
    throw new UnsupportedOperationException("Implement visitAngle(double) @ " + name());
  }

  Context<E> visitAngle(Adapter<E> a, AngleUnit unit, int value) throws E {
    throw new UnsupportedOperationException("Implement visitAngle(int) @ " + name());
  }

  Context<E> visitAttributeSelector(Adapter<E> a, String attributeName) throws E {
    throw new UnsupportedOperationException("Implement visitAttributeSelector @ " + name());
  }

  Context<E> visitAttributeValueSelector(
      Adapter<E> a, String attributeName, AttributeValueOperator operator, String value)
      throws E {
    throw new UnsupportedOperationException("Implement visitAttributeValueSelector @ " + name());
  }

  Context<E> visitClassSelector(Adapter<E> a, String className) throws E {
    throw new UnsupportedOperationException("Implement visitClassSelector @ " + name());
  }

  Context<E> visitColor(Adapter<E> a, ColorName color) throws E {
    throw new UnsupportedOperationException("Implement visitValue(ColorName) @ " + name());
  }

  Context<E> visitColor(Adapter<E> a, String value) throws E {
    throw new UnsupportedOperationException("Implement visitValueColorHex @ " + name());
  }

  Context<E> visitCombinator(Adapter<E> a, Combinator combinator) throws E {
    throw new UnsupportedOperationException("Implement visitCombinator @ " + name());
  }

  Context<E> visitDeclarationStart(Adapter<E> a, StandardPropertyName name) throws E {
    throw new UnsupportedOperationException("Implement visitDeclarationStart @ " + name());
  }

  Context<E> visitDouble(Adapter<E> a, double value) throws E {
    throw new UnsupportedOperationException("Implement visitValue(double) @ " + name());
  }

  Context<E> visitFunctionEnd(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitFunctionEnd @ " + name());
  }

  Context<E> visitFunctionStart(Adapter<E> a, StandardFunctionName name) throws E {
    throw new UnsupportedOperationException("Implement visitFunctionStart @ " + name());
  }

  Context<E> visitIdSelector(Adapter<E> a, String id) throws E {
    throw new UnsupportedOperationException("Implement visitIdSelector @ " + name());
  }

  Context<E> visitInt(Adapter<E> a, int value) throws E {
    throw new UnsupportedOperationException("Implement visitValue(int) @ " + name());
  }

  Context<E> visitKeyword(Adapter<E> a, StandardKeyword keyword) throws E {
    throw new UnsupportedOperationException("Implement visitValue(StandardKeyword) @ " + name());
  }

  Context<E> visitKeyword(Adapter<E> a, String keyword) throws E {
    throw new UnsupportedOperationException("Implement visitKeyword(String) @ " + name());
  }

  Context<E> visitLength(Adapter<E> a, LengthUnit unit, double value) throws E {
    throw new UnsupportedOperationException("Implement visitValue(LenghtUnit, double) @ " + name());
  }

  Context<E> visitLength(Adapter<E> a, LengthUnit unit, int value) throws E {
    throw new UnsupportedOperationException("Implement visitValue(LenghtUnit, int) @ " + name());
  }

  Context<E> visitMediaEnd(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitMediaEnd @ " + name());
  }

  Context<E> visitMediaStart(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitMediaStart @ " + name());
  }

  Context<E> visitMediaType(Adapter<E> a, MediaType type) throws E {
    throw new UnsupportedOperationException("Implement visitMediaType @ " + name());
  }

  Context<E> visitMultiDeclarationSeparator(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitMultiDeclarationSeparator @ " + name());
  }

  Context<E> visitPercentage(Adapter<E> a, double value) throws E {
    throw new UnsupportedOperationException("Implement visitValuePercentage(double) @ " + name());
  }

  Context<E> visitPercentage(Adapter<E> a, int value) throws E {
    throw new UnsupportedOperationException("Implement visitValuePercentage(int) @ " + name());
  }

  Context<E> visitRgb(Adapter<E> a, double r, double g, double b) throws E {
    throw new UnsupportedOperationException("Implement visitRgb(double) @ " + name());
  }

  Context<E> visitRgb(Adapter<E> a, double r, double g, double b, double alpha) throws E {
    throw new UnsupportedOperationException("Implement visitRgb(double, alpha) @ " + name());
  }

  Context<E> visitRgb(Adapter<E> a, int r, int g, int b) throws E {
    throw new UnsupportedOperationException("Implement visitRgb(int) @ " + name());
  }

  Context<E> visitRgb(Adapter<E> a, int r, int g, int b, double alpha) throws E {
    throw new UnsupportedOperationException("Implement visitRgb(int, alpha) @ " + name());
  }

  Context<E> visitRgba(Adapter<E> a, double r, double g, double b, double alpha) throws E {
    throw new UnsupportedOperationException("Implement visitRgba(double) @ " + name());
  }

  Context<E> visitRgba(Adapter<E> a, int r, int g, int b, double alpha) throws E {
    throw new UnsupportedOperationException("Implement visitRgba(int) @ " + name());
  }

  Context<E> visitRuleEnd(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitRuleEnd @ " + name());
  }

  Context<E> visitRuleStart(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitRuleStart @ " + name());
  }

  Context<E> visitSimpleSelector(Adapter<E> a, SimpleSelector selector) throws E {
    throw new UnsupportedOperationException("Implement visitSimpleSelector @ " + name());
  }

  Context<E> visitString(Adapter<E> a, String value) throws E {
    throw new UnsupportedOperationException("Implement visitString @ " + name());
  }

  Context<E> visitStyleSheetEnd(Adapter<E> a) throws E {
    throw new UnsupportedOperationException("Implement visitStyleSheetEnd @ " + name());
  }

  Context<E> visitUniversalSelector(Adapter<E> a, UniversalSelector selector) throws E {
    throw new UnsupportedOperationException("Implement visitUniversalSelector @ " + name());
  }

  Context<E> visitUri(Adapter<E> a, String value) throws E {
    throw new UnsupportedOperationException("Implement visitUri @ " + name());
  }

  @SuppressWarnings("rawtypes")
  private String name() {
    Class<? extends Context> type;
    type = getClass();

    return type.getSimpleName();
  }

  interface Adapter<E extends Exception> extends CompiledStyleSheetVisitor<E> {

    Body peekBody();

    Body popBody();

    void pushBody(Body body);

  }

}