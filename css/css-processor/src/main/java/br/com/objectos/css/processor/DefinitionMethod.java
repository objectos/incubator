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

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java._void;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.statements;

import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.statement.BlockStatement;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.sheet.CompiledStyleSheet;
import br.com.objectos.css.sheet.CompiledStyleSheetVisitor;
import br.com.objectos.css.sheet.LogicalOperator;
import br.com.objectos.css.sheet.MediaType;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;

class DefinitionMethod implements CompiledStyleSheetVisitor<RuntimeException> {

  private final MutableList<BlockStatement> statements = MutableList.create();
  private StyleMethodInvocation style;

  private DefinitionMethod() {}

  public static MethodCode of(StyleSheet sheet) {
    DefinitionMethod method;
    method = new DefinitionMethod();

    CompiledStyleSheet compiled;
    compiled = sheet.compile();

    compiled.acceptCompiledStyleSheetVisitor(method);

    return method.build();
  }

  @Override
  public final void visitAfterLastDeclaration() {
    // noop
  }

  @Override
  public void visitAngle(AngleUnit unit, double value) throws RuntimeException {}

  @Override
  public void visitAngle(AngleUnit unit, int value) throws RuntimeException {}

  @Override
  public final void visitAttributeSelector(String attributeName) {
    style.addAttributeSelector(attributeName);
  }

  @Override
  public final void visitAttributeValueSelector(
      String attributeName, AttributeValueOperator operator, String value) {
    style.addAttributeValueSelector(attributeName, operator, value);
  }

  @Override
  public final void visitBeforeNextDeclaration() {
    // noop
  }

  @Override
  public final void visitBeforeNextStatement() {
    // noop
  }

  @Override
  public final void visitBeforeNextValue() {
    // noop
  }

  @Override
  public final void visitBlockEnd() {
    buildRule();
  }

  @Override
  public final void visitBlockStart() {
    // noop
  }

  @Override
  public final void visitClassSelector(String className) {
    style.addClassSelector(className);
  }

  @Override
  public final void visitColor(ColorName value) {
    style.addColor(value);
  }

  @Override
  public final void visitColor(String value) {
    style.addColor(value);
  }

  @Override
  public final void visitCombinator(Combinator combinator) {
    style.addCombinator(combinator);
  }

  @Override
  public final void visitDeclarationStart(StandardPropertyName name) {
    style.addPropertyName(name);
  }

  @Override
  public final void visitDouble(double value) {
    style.addValue(value);
  }

  @Override
  public final void visitEmptyBlock() {
    buildRule();
  }

  @Override
  public void visitFunctionEnd() throws RuntimeException {}

  @Override
  public void visitFunctionStart(StandardFunctionName name) throws RuntimeException {}

  @Override
  public final void visitIdSelector(String id) {
    style.addIdSelector(id);
  }

  @Override
  public final void visitInt(int value) {
    style.addValue(value);
  }

  @Override
  public final void visitKeyword(StandardKeyword value) {
    style.addKeyword(value);
  }

  @Override
  public final void visitKeyword(String keyword) {
    style.addKeyword(keyword);
  }

  @Override
  public final void visitLength(LengthUnit unit, double value) {
    style.addLength(unit, value);
  }

  @Override
  public final void visitLength(LengthUnit unit, int value) {
    style.addLength(unit, value);
  }

  @Override
  public void visitLogicalExpressionEnd() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void visitLogicalExpressionStart(LogicalOperator operator) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void visitMediaStart() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void visitMediaType(MediaType type) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public void visitMultiDeclarationSeparator() {
    style.addMultiDeclarationSeparator();
  }

  @Override
  public final void visitPercentage(double value) {
    style.addPercentage(value);
  }

  @Override
  public final void visitPercentage(int value) {
    style.addPercentage(value);
  }

  @Override
  public final void visitRgb(double r, double g, double b) {
    style.addRgb(r, g, b);
  }

  @Override
  public final void visitRgb(double r, double g, double b, double alpha) {
    style.addRgb(r, g, b, alpha);
  }

  @Override
  public final void visitRgb(int r, int g, int b) {
    style.addRgb(r, g, b);
  }

  @Override
  public final void visitRgb(int r, int g, int b, double alpha) {
    style.addRgb(r, g, b, alpha);
  }

  @Override
  public final void visitRgba(double r, double g, double b, double alpha) {
    style.addRgba(r, g, b, alpha);
  }

  @Override
  public final void visitRgba(int r, int g, int b, double alpha) {
    style.addRgba(r, g, b, alpha);
  }

  @Override
  public final void visitRuleStart() {
    style = new StyleMethodInvocation();
  }

  @Override
  public final void visitSimpleSelector(SimpleSelector selector) {
    style.addSimpleSelector(selector);
  }

  @Override
  public final void visitString(String value) {
    style.addString(value);
  }

  @Override
  public final void visitUniversalSelector(UniversalSelector selector) {
    style.addUniversalSelector(selector);
  }

  @Override
  public final void visitUri(String value) throws RuntimeException {
    throw new UnsupportedOperationException("Implement me");
  }

  private MethodCode build() {
    return method(
        annotation(Override.class),
        _protected(), _final(), _void(), id("definition"),
        statements(statements)
    );
  }

  private void buildRule() {
    MethodInvocation invocation;
    invocation = style.build();

    statements.add(invocation);

    style = null;
  }

}
