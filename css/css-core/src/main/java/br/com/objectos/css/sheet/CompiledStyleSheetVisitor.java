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

public interface CompiledStyleSheetVisitor<E extends Exception> {

  void visitAfterLastDeclaration() throws E;

  void visitAngle(AngleUnit unit, double value) throws E;

  void visitAngle(AngleUnit unit, int value) throws E;

  void visitAttributeSelector(String attributeName) throws E;

  void visitAttributeValueSelector(
      String attributeName, AttributeValueOperator operator, String value) throws E;

  void visitBeforeNextDeclaration() throws E;

  void visitBeforeNextStatement() throws E;

  void visitBeforeNextValue() throws E;

  void visitBlockEnd() throws E;

  void visitBlockStart() throws E;

  void visitClassSelector(String className) throws E;

  void visitColor(ColorName value) throws E;

  void visitColor(String value) throws E;

  void visitCombinator(Combinator combinator) throws E;

  void visitDeclarationStart(StandardPropertyName name) throws E;

  void visitDouble(double value) throws E;

  void visitEmptyBlock() throws E;

  void visitFunctionEnd() throws E;

  void visitFunctionStart(StandardFunctionName name) throws E;

  void visitIdSelector(String id) throws E;

  void visitInt(int value) throws E;

  void visitKeyword(StandardKeyword value) throws E;

  void visitKeyword(String keyword) throws E;

  void visitLength(LengthUnit unit, double value) throws E;

  void visitLength(LengthUnit unit, int value) throws E;

  void visitLogicalExpressionEnd() throws E;

  void visitLogicalExpressionStart(LogicalOperator operator) throws E;

  void visitMediaStart() throws E;

  void visitMediaType(MediaType type) throws E;

  void visitMultiDeclarationSeparator() throws E;

  void visitPercentage(double value) throws E;

  void visitPercentage(int value) throws E;

  void visitRgb(double r, double g, double b) throws E;

  void visitRgb(double r, double g, double b, double alpha) throws E;

  void visitRgb(int r, int g, int b) throws E;

  void visitRgb(int r, int g, int b, double alpha) throws E;

  void visitRgba(double r, double g, double b, double alpha) throws E;

  void visitRgba(int r, int g, int b, double alpha) throws E;

  void visitRuleStart() throws E;

  void visitSimpleSelector(SimpleSelector selector) throws E;

  void visitString(String value) throws E;

  void visitUniversalSelector(UniversalSelector selector) throws E;

  void visitUri(String value) throws E;

}
