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

import br.com.objectos.core.array.IntArrays;
import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.PseudoClassSelector;
import br.com.objectos.css.select.PseudoClassSelectors;
import br.com.objectos.css.select.PseudoElementSelector;
import br.com.objectos.css.select.PseudoElementSelectors;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.TypeSelector;
import br.com.objectos.css.select.TypeSelectors;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.Color;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;
import java.util.ArrayDeque;
import java.util.Deque;

final class Interpreter<E extends Exception> implements Context.Adapter<E> {

  private final Deque<Body> bodyStack = new ArrayDeque<>(3);

  private int[] call = new int[64];

  private int callIndex = -1;

  private Context<E> context;
  private int cursor;

  private boolean running;

  private final CompiledStyleSheet sheet;
  private final CompiledStyleSheetVisitor<E> visitor;

  Interpreter(CompiledStyleSheet sheet, CompiledStyleSheetVisitor<E> visitor) {
    this.sheet = sheet;
    this.visitor = visitor;
  }

  public final void execute() throws E {
    context = Context.getStart();

    running = true;

    while (running) {
      execute0();
    }

    context = context.visitStyleSheetEnd(this);
  }

  @Override
  public final Body peekBody() {
    return bodyStack.peek();
  }

  @Override
  public final Body popBody() {
    return bodyStack.pop();
  }

  @Override
  public final void pushBody(Body body) {
    bodyStack.push(body);
  }

  @Override
  public final void visitAfterLastDeclaration() throws E {
    visitor.visitAfterLastDeclaration();
  }

  @Override
  public final void visitAngle(AngleUnit unit, double value) throws E {
    visitor.visitAngle(unit, value);
  }

  @Override
  public final void visitAngle(AngleUnit unit, int value) throws E {
    visitor.visitAngle(unit, value);
  }

  @Override
  public final void visitAttributeSelector(String attributeName) throws E {
    visitor.visitAttributeSelector(attributeName);
  }

  @Override
  public final void visitAttributeValueSelector(
      String attributeName, AttributeValueOperator operator, String value) throws E {
    visitor.visitAttributeValueSelector(attributeName, operator, value);
  }

  @Override
  public final void visitBeforeNextDeclaration() throws E {
    visitor.visitBeforeNextDeclaration();
  }

  @Override
  public final void visitBeforeNextStatement() throws E {
    visitor.visitBeforeNextStatement();
  }

  @Override
  public final void visitBeforeNextValue() throws E {
    visitor.visitBeforeNextValue();
  }

  @Override
  public final void visitBlockEnd() throws E {
    visitor.visitBlockEnd();
  }

  @Override
  public final void visitBlockStart() throws E {
    visitor.visitBlockStart();
  }

  @Override
  public final void visitClassSelector(String className) throws E {
    visitor.visitClassSelector(className);
  }

  @Override
  public final void visitColor(ColorName value) throws E {
    visitor.visitColor(value);
  }

  @Override
  public final void visitColor(String value) throws E {
    visitor.visitColor(value);
  }

  @Override
  public final void visitCombinator(Combinator combinator) throws E {
    visitor.visitCombinator(combinator);
  }

  @Override
  public final void visitDeclarationStart(StandardPropertyName name) throws E {
    visitor.visitDeclarationStart(name);
  }

  @Override
  public final void visitDouble(double value) throws E {
    visitor.visitDouble(value);
  }

  @Override
  public final void visitEmptyBlock() throws E {
    visitor.visitEmptyBlock();
  }

  @Override
  public final void visitFunctionEnd() throws E {
    visitor.visitFunctionEnd();
  }

  @Override
  public final void visitFunctionStart(StandardFunctionName name) throws E {
    visitor.visitFunctionStart(name);
  }

  @Override
  public final void visitIdSelector(String id) throws E {
    visitor.visitIdSelector(id);
  }

  @Override
  public final void visitInt(int value) throws E {
    visitor.visitInt(value);
  }

  @Override
  public final void visitKeyword(StandardKeyword value) throws E {
    visitor.visitKeyword(value);
  }

  @Override
  public final void visitKeyword(String keyword) throws E {
    visitor.visitKeyword(keyword);
  }

  @Override
  public final void visitLength(LengthUnit unit, double value) throws E {
    visitor.visitLength(unit, value);
  }

  @Override
  public final void visitLength(LengthUnit unit, int value) throws E {
    visitor.visitLength(unit, value);
  }

  @Override
  public final void visitLogicalExpressionEnd() throws E {
    visitor.visitLogicalExpressionEnd();
  }

  @Override
  public final void visitLogicalExpressionStart(LogicalOperator operator) throws E {
    visitor.visitLogicalExpressionStart(operator);
  }

  @Override
  public final void visitMediaStart() throws E {
    visitor.visitMediaStart();
  }

  @Override
  public final void visitMediaType(MediaType type) throws E {
    visitor.visitMediaType(type);
  }

  @Override
  public final void visitMultiDeclarationSeparator() throws E {
    visitor.visitMultiDeclarationSeparator();
  }

  @Override
  public final void visitPercentage(double value) throws E {
    visitor.visitPercentage(value);
  }

  @Override
  public final void visitPercentage(int value) throws E {
    visitor.visitPercentage(value);
  }

  @Override
  public final void visitRgb(double r, double g, double b) throws E {
    visitor.visitRgb(r, g, b);
  }

  @Override
  public final void visitRgb(double r, double g, double b, double alpha) throws E {
    visitor.visitRgb(r, g, b, alpha);
  }

  @Override
  public final void visitRgb(int r, int g, int b) throws E {
    visitor.visitRgb(r, g, b);
  }

  @Override
  public final void visitRgb(int r, int g, int b, double alpha) throws E {
    visitor.visitRgb(r, g, b, alpha);
  }

  @Override
  public final void visitRgba(double r, double g, double b, double alpha) throws E {
    visitor.visitRgba(r, g, b, alpha);
  }

  @Override
  public final void visitRgba(int r, int g, int b, double alpha) throws E {
    visitor.visitRgba(r, g, b, alpha);
  }

  @Override
  public final void visitRuleStart() throws E {
    visitor.visitRuleStart();
  }

  @Override
  public final void visitSimpleSelector(SimpleSelector selector) throws E {
    visitor.visitSimpleSelector(selector);
  }

  @Override
  public final void visitString(String value) throws E {
    visitor.visitString(value);
  }

  @Override
  public final void visitUniversalSelector(UniversalSelector selector) throws E {
    visitor.visitUniversalSelector(selector);
  }

  @Override
  public final void visitUri(String value) throws E {
    visitor.visitUri(value);
  }

  private void doDeclarationStart() throws E {
    int code;
    code = getCode();

    StandardPropertyName name;
    name = StandardPropertyName.getByCode(code);

    context = context.visitDeclarationStart(this, name);
  }

  private void doFlowJmp() {
    int jumpTo = getCode();

    pushCall();

    cursor = jumpTo;
  }

  private void doFlowReturn() {
    if (callIndex >= 0) {
      cursor = popCall();
    } else {
      running = false;
    }
  }

  private void doFunctionEnd() throws E {
    context = context.visitFunctionEnd(this);
  }

  private void doFunctionStart() throws E {
    int code;
    code = getCode();

    StandardFunctionName name;
    name = StandardFunctionName.getByCode(code);

    context = context.visitFunctionStart(this, name);
  }

  private void doMediaEnd() throws E {
    context = context.visitMediaEnd(this);
  }

  private void doMediaStart() throws E {
    context = context.visitMediaStart(this);
  }

  private void doMediaType() throws E {
    int code;
    code = getCode();

    MediaType type;
    type = MediaType.getByCode(code);

    context = context.visitMediaType(this, type);
  }

  private void doMultiDeclarationSeparator() throws E {
    context = context.visitMultiDeclarationSeparator(this);
  }

  private void doRuleEnd() throws E {
    context = context.visitRuleEnd(this);
  }

  private void doRuleStart() throws E {
    context = context.visitRuleStart(this);
  }

  private void doSelectorAttribute() throws E {
    String attributeName;
    attributeName = getString();

    context = context.visitAttributeSelector(this, attributeName);
  }

  private void doSelectorAttributeValue() throws E {
    String attributeName = getString();

    int opCode;
    opCode = getCode();

    AttributeValueOperator operator;
    operator = AttributeValueOperator.getByCode(opCode);

    String value;
    value = getString();

    context = context.visitAttributeValueSelector(this, attributeName, operator, value);
  }

  private void doSelectorClass() throws E {
    String className;
    className = getString();

    context = context.visitClassSelector(this, className);
  }

  private void doSelectorCombinator() throws E {
    int code;
    code = getCode();

    Combinator combinator;
    combinator = Combinator.getByCode(code);

    context = context.visitCombinator(this, combinator);
  }

  private void doSelectorId() throws E {
    String id;
    id = getString();

    context = context.visitIdSelector(this, id);
  }

  private void doSelectorPseudoClass() throws E {
    int code;
    code = getCode();

    PseudoClassSelector selector;
    selector = PseudoClassSelectors.getByCode(code);

    context = context.visitSimpleSelector(this, selector);
  }

  private void doSelectorPseudoElement() throws E {
    int code;
    code = getCode();

    PseudoElementSelector selector;
    selector = PseudoElementSelectors.getByCode(code);

    context = context.visitSimpleSelector(this, selector);
  }

  private void doSelectorType() throws E {
    int code;
    code = getCode();

    TypeSelector selector;
    selector = TypeSelectors.getByCode(code);

    context = context.visitSimpleSelector(this, selector);
  }

  private void doSelectorUniversal() throws E {
    context = context.visitUniversalSelector(this, UniversalSelector.getInstance());
  }

  private void doValueAngleDouble() throws E {
    AngleUnit unit;
    unit = getAngleUnit();

    double value;
    value = getDouble();

    context = context.visitAngle(this, unit, value);
  }

  private void doValueAngleInt() throws E {
    AngleUnit unit;
    unit = getAngleUnit();

    int value;
    value = getCode();

    context = context.visitAngle(this, unit, value);
  }

  private void doValueColorHex() throws E {
    String hex;
    hex = getString();

    context = context.visitColor(this, hex);
  }

  private void doValueColorName() throws E {
    int code;
    code = getCode();

    ColorName color;
    color = Color.getByCode(code);

    context = context.visitColor(this, color);
  }

  private void doValueDouble() throws E {
    double value;
    value = getDouble();

    context = context.visitDouble(this, value);
  }

  private void doValueInt() throws E {
    int value;
    value = getCode();

    context = context.visitInt(this, value);
  }

  private void doValueKeyword() throws E {
    int code;
    code = getCode();

    StandardKeyword keyword;
    keyword = Keywords.getByCode(code);

    context = context.visitKeyword(this, keyword);
  }

  private void doValueKeywordCustom() throws E {
    String keyword = getString();

    context = context.visitKeyword(this, keyword);
  }

  private void doValueLengthDouble() throws E {
    LengthUnit unit;
    unit = getLengthUnit();

    double value;
    value = getDouble();

    context = context.visitLength(this, unit, value);
  }

  private void doValueLengthInt() throws E {
    LengthUnit unit;
    unit = getLengthUnit();

    int value;
    value = getCode();

    context = context.visitLength(this, unit, value);
  }

  private void doValuePercentageDouble() throws E {
    double value;
    value = getDouble();

    context = context.visitPercentage(this, value);
  }

  private void doValuePercentageInt() throws E {
    int value;
    value = getCode();

    context = context.visitPercentage(this, value);
  }

  private void doValueRgbaDouble() throws E {
    int doubleStartIndex;
    doubleStartIndex = getCode();

    double r;
    r = getDouble(doubleStartIndex++);

    double g;
    g = getDouble(doubleStartIndex++);

    double b;
    b = getDouble(doubleStartIndex++);

    double alpha;
    alpha = getDouble(doubleStartIndex);

    context = context.visitRgba(this, r, g, b, alpha);
  }

  private void doValueRgbaInt() throws E {
    int r;
    r = getCode();

    int g;
    g = getCode();

    int b;
    b = getCode();

    double alpha;
    alpha = getDouble();

    context = context.visitRgba(this, r, g, b, alpha);
  }

  private void doValueRgbDouble() throws E {
    int doubleStartIndex;
    doubleStartIndex = getCode();

    double r;
    r = getDouble(doubleStartIndex++);

    double g;
    g = getDouble(doubleStartIndex++);

    double b;
    b = getDouble(doubleStartIndex++);

    context = context.visitRgb(this, r, g, b);
  }

  private void doValueRgbDoubleAlpha() throws E {
    int doubleStartIndex;
    doubleStartIndex = getCode();

    double r;
    r = getDouble(doubleStartIndex++);

    double g;
    g = getDouble(doubleStartIndex++);

    double b;
    b = getDouble(doubleStartIndex++);

    double alpha;
    alpha = getDouble(doubleStartIndex);

    context = context.visitRgb(this, r, g, b, alpha);
  }

  private void doValueRgbInt() throws E {
    int r;
    r = getCode();

    int g;
    g = getCode();

    int b;
    b = getCode();

    context = context.visitRgb(this, r, g, b);
  }

  private void doValueRgbIntAlpha() throws E {
    int r;
    r = getCode();

    int g;
    g = getCode();

    int b;
    b = getCode();

    double alpha;
    alpha = getDouble();

    context = context.visitRgb(this, r, g, b, alpha);
  }

  private void doValueString() throws E {
    String value;
    value = getString();

    context = context.visitString(this, value);
  }

  private void doValueUri() throws E {
    String value;
    value = getString();

    context = context.visitUri(this, value);
  }

  private void execute0() throws E {
    int code;
    code = getCode();

    switch (code) {
      case ByteCode.DECLARATION_START:
        doDeclarationStart();
        break;

      case ByteCode.FLOW_JMP:
        doFlowJmp();
        break;
      case ByteCode.FLOW_RETURN:
        doFlowReturn();
        break;

      case ByteCode.FUNCTION_END:
        doFunctionEnd();
        break;
      case ByteCode.FUNCTION_START:
        doFunctionStart();
        break;

      case ByteCode.MEDIA_END:
        doMediaEnd();
        break;
      case ByteCode.MEDIA_START:
        doMediaStart();
        break;
      case ByteCode.MEDIA_TYPE:
        doMediaType();
        break;

      case ByteCode.MULTI_DECLARATION_SEPARATOR:
        doMultiDeclarationSeparator();
        break;

      case ByteCode.SELECTOR_ATTRIBUTE:
        doSelectorAttribute();
        break;
      case ByteCode.SELECTOR_ATTRIBUTE_VALUE:
        doSelectorAttributeValue();
        break;
      case ByteCode.SELECTOR_CLASS:
        doSelectorClass();
        break;
      case ByteCode.SELECTOR_COMBINATOR:
        doSelectorCombinator();
        break;
      case ByteCode.SELECTOR_ID:
        doSelectorId();
        break;
      case ByteCode.SELECTOR_PSEUDO_CLASS:
        doSelectorPseudoClass();
        break;
      case ByteCode.SELECTOR_PSEUDO_ELEMENT:
        doSelectorPseudoElement();
        break;
      case ByteCode.SELECTOR_TYPE:
        doSelectorType();
        break;
      case ByteCode.SELECTOR_UNIVERSAL:
        doSelectorUniversal();
        break;

      case ByteCode.ROOT:
        // noop
        break;
      case ByteCode.RULE_END:
        doRuleEnd();
        break;
      case ByteCode.RULE_START:
        doRuleStart();
        break;

      case ByteCode.VALUE_ANGLE_DOUBLE:
        doValueAngleDouble();
        break;
      case ByteCode.VALUE_ANGLE_INT:
        doValueAngleInt();
        break;
      case ByteCode.VALUE_COLOR_HEX:
        doValueColorHex();
        break;
      case ByteCode.VALUE_COLOR_NAME:
        doValueColorName();
        break;
      case ByteCode.VALUE_DOUBLE:
        doValueDouble();
        break;
      case ByteCode.VALUE_INT:
        doValueInt();
        break;
      case ByteCode.VALUE_LENGTH_DOUBLE:
        doValueLengthDouble();
        break;
      case ByteCode.VALUE_LENGTH_INT:
        doValueLengthInt();
        break;
      case ByteCode.VALUE_KEYWORD:
        doValueKeyword();
        break;
      case ByteCode.VALUE_KEYWORD_CUSTOM:
        doValueKeywordCustom();
        break;
      case ByteCode.VALUE_PERCENTAGE_DOUBLE:
        doValuePercentageDouble();
        break;
      case ByteCode.VALUE_PERCENTAGE_INT:
        doValuePercentageInt();
        break;
      case ByteCode.VALUE_RGB_DOUBLE:
        doValueRgbDouble();
        break;
      case ByteCode.VALUE_RGB_DOUBLE_ALPHA:
        doValueRgbDoubleAlpha();
        break;
      case ByteCode.VALUE_RGB_INT:
        doValueRgbInt();
        break;
      case ByteCode.VALUE_RGB_INT_ALPHA:
        doValueRgbIntAlpha();
        break;
      case ByteCode.VALUE_RGBA_DOUBLE:
        doValueRgbaDouble();
        break;
      case ByteCode.VALUE_RGBA_INT:
        doValueRgbaInt();
        break;
      case ByteCode.VALUE_STRING:
        doValueString();
        break;
      case ByteCode.VALUE_URI:
        doValueUri();
        break;
      default:
        throw new UnsupportedOperationException("Implement me: " + code);
    }
  }

  private AngleUnit getAngleUnit() {
    int unitCode;
    unitCode = getCode();

    return AngleUnit.getByCode(unitCode);
  }

  private int getCode() {
    return sheet.getCode(cursor++);
  }

  private double getDouble() {
    int index;
    index = getCode();

    return getDouble(index);
  }

  private double getDouble(int index) {
    return sheet.getDouble(index);
  }

  private LengthUnit getLengthUnit() {
    int unitCode;
    unitCode = getCode();

    return LengthUnit.getByCode(unitCode);
  }

  private String getString() {
    return getString(getCode(), getCode());
  }

  private String getString(int index, int length) {
    return sheet.getString(index, length);
  }

  private int popCall() {
    return call[callIndex--];
  }

  private void pushCall() {
    callIndex++;

    call = IntArrays.copyIfNecessary(call, callIndex);

    call[callIndex] = cursor;
  }

}
