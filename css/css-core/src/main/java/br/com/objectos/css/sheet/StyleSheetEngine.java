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
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.PseudoClassSelectors;
import br.com.objectos.css.select.PseudoElementSelectors;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.TypeSelectors;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.Color;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;
import objectos.lang.Check;
import objectos.util.IntArrays;

public abstract class StyleSheetEngine<E extends Exception> extends StyleSheetCompiler {

  @FunctionalInterface
  private interface Action<X extends Exception> {
    void execute() throws X;
  }

  private static final int _START = 1;

  private static final int _STOP = 2;

  static final int _AT_MEDIA_BODY = 3;

  private static final int _AT_MEDIA_START = 4;

  private static final int _DECLARATION_START = 5;

  private static final int _DECLARATION_VALUES = 6;

  private static final int _FUNCTION_START = 7;

  private static final int _FUNCTION_VALUES = 8;

  private static final int _IGNORE_IF_SINGLE_SELECTOR = 9;

  private static final int _IGNORE_RULE = 10;

  private static final int _MEDIA_QUERY = 11;

  private static final int _MEDIA_QUERY_DECLARATION = 12;

  private static final int _SELECTOR = 13;

  static final int _SHEET_BODY = 14;

  private static final Predicate<String> CLASS_SELECTORS_ALWAYS_TRUE = (s) -> true;

  private final Deque<Body> bodyStack = new ArrayDeque<>(3);

  private int[] call = new int[64];

  private int callIndex = -1;

  private int state;

  private Predicate<String> classSelectorsByName;

  private String currentClassName;

  protected StyleSheetEngine() {}

  public final void clear() {
    if (classSelectorsByName != null) {
      classSelectorsByName = null;
    }
  }

  public final void execute() throws E {
    if (classSelectorsByName == null) {
      classSelectorsByName = CLASS_SELECTORS_ALWAYS_TRUE;
    }

    cursor = 0;

    state = _START;

    while (state != _STOP) {
      execute0();
    }
  }

  public final void filterClassSelectorsByName(Predicate<String> names) {
    Check.notNull(names, "names == null");

    if (classSelectorsByName == null) {
      classSelectorsByName = names;
    } else {
      classSelectorsByName = classSelectorsByName.and(names);
    }
  }

  protected abstract void visitAfterLastDeclaration() throws E;

  protected abstract void visitAngle(AngleUnit unit, double value) throws E;

  protected abstract void visitAngle(AngleUnit unit, int value) throws E;

  protected abstract void visitAttributeSelector(String attributeName) throws E;

  protected abstract void visitAttributeValueSelector(
      String attributeName, AttributeValueOperator operator, String value) throws E;

  protected abstract void visitBeforeNextDeclaration() throws E;

  protected abstract void visitBeforeNextStatement() throws E;

  protected abstract void visitBeforeNextValue() throws E;

  protected abstract void visitBlockEnd() throws E;

  protected abstract void visitBlockStart() throws E;

  protected abstract void visitClassSelector(String className) throws E;

  protected abstract void visitColor(ColorName value) throws E;

  protected abstract void visitColor(String value) throws E;

  protected abstract void visitCombinator(Combinator combinator) throws E;

  protected abstract void visitDeclarationStart(StandardPropertyName name) throws E;

  protected abstract void visitDouble(double value) throws E;

  protected abstract void visitEmptyBlock() throws E;

  protected abstract void visitFunctionEnd() throws E;

  protected abstract void visitFunctionStart(StandardFunctionName name) throws E;

  protected abstract void visitIdSelector(String id) throws E;

  protected abstract void visitInt(int value) throws E;

  protected abstract void visitKeyword(StandardKeyword value) throws E;

  protected abstract void visitKeyword(String keyword) throws E;

  protected abstract void visitLength(LengthUnit unit, double value) throws E;

  protected abstract void visitLength(LengthUnit unit, int value) throws E;

  protected abstract void visitLogicalExpressionEnd() throws E;

  protected abstract void visitLogicalExpressionStart(LogicalOperator operator) throws E;

  protected abstract void visitMediaStart() throws E;

  protected abstract void visitMediaType(MediaType type) throws E;

  protected abstract void visitMultiDeclarationSeparator() throws E;

  protected abstract void visitPercentage(double value) throws E;

  protected abstract void visitPercentage(int value) throws E;

  protected abstract void visitRgb(double r, double g, double b) throws E;

  protected abstract void visitRgb(double r, double g, double b, double alpha) throws E;

  protected abstract void visitRgb(int r, int g, int b) throws E;

  protected abstract void visitRgb(int r, int g, int b, double alpha) throws E;

  protected abstract void visitRgba(double r, double g, double b, double alpha) throws E;

  protected abstract void visitRgba(int r, int g, int b, double alpha) throws E;

  protected abstract void visitRuleStart() throws E;

  protected abstract void visitSimpleSelector(SimpleSelector selector) throws E;

  protected abstract void visitString(String value) throws E;

  protected abstract void visitUniversalSelector(UniversalSelector selector) throws E;

  protected abstract void visitUri(String value) throws E;

  final Body peekBody() {
    return bodyStack.peek();
  }

  final Body popBody() {
    return bodyStack.pop();
  }

  final void pushBody(Body body) {
    bodyStack.push(body);
  }

  @Override
  void reset() {
    super.reset();

    bodyStack.clear();

    callIndex = -1;

    cursor = 0;

    state = 0;
  }

  private void doDeclarationStart() throws E {
    var code = getCode();

    var name = StandardPropertyName.getByCode(code);

    state = switch (state) {
      case _AT_MEDIA_BODY -> {
        visitBlockEnd();

        popBody();

        var body = peekBody();

        yield body.state;
      }
      case _DECLARATION_VALUES -> {
        visitBeforeNextDeclaration();

        visitDeclarationStart(name);

        yield _DECLARATION_START;
      }
      case _IGNORE_IF_SINGLE_SELECTOR -> _IGNORE_RULE;
      case _MEDIA_QUERY -> {
        visitLogicalExpressionStart(LogicalOperator.AND);

        visitDeclarationStart(name);

        yield _MEDIA_QUERY_DECLARATION;
      }
      case _SELECTOR -> {
        visitBlockStart();

        visitDeclarationStart(name);

        yield _DECLARATION_START;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
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
      state = _STOP;
    }
  }

  private void doFunctionEnd() throws E {
    state = switch (state) {
      case _FUNCTION_VALUES -> {
        visitFunctionEnd();

        yield _DECLARATION_VALUES;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doFunctionStart() throws E {
    var code = getCode();

    var name = StandardFunctionName.getByCode(code);

    state = switch (state) {
      case _DECLARATION_START -> {
        visitFunctionStart(name);

        yield _FUNCTION_START;
      }
      case _DECLARATION_VALUES -> {
        visitBeforeNextValue();

        visitFunctionStart(name);

        yield _FUNCTION_START;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doMediaEnd() throws E {
    state = switch (state) {
      case _AT_MEDIA_BODY -> {
        visitBlockEnd();

        popBody();

        var body = peekBody();

        yield body.state;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doMediaStart() throws E {
    state = switch (state) {
      case _SHEET_BODY -> {
        visitBeforeNextStatement();

        visitMediaStart();

        yield _AT_MEDIA_START;
      }
      case _START -> {
        pushBody(Body.SHEET);

        visitMediaStart();

        yield _AT_MEDIA_START;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doMediaType() throws E {
    state = switch (state) {
      case _AT_MEDIA_START -> {
        var code = getCode();

        var type = MediaType.getByCode(code);

        visitMediaType(type);

        yield _MEDIA_QUERY;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doMultiDeclarationSeparator() throws E {
    state = switch (state) {
      case _DECLARATION_START -> {
        yield _DECLARATION_START;
      }
      case _DECLARATION_VALUES -> {
        visitMultiDeclarationSeparator();

        yield _DECLARATION_START;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doRuleEnd() throws E {
    state = switch (state) {
      case _DECLARATION_VALUES -> {
        visitAfterLastDeclaration();

        visitBlockEnd();

        var body = peekBody();

        yield body.state;
      }
      case _IGNORE_RULE -> {
        var body = peekBody();

        yield body.state;
      }
      case _SELECTOR -> {
        visitEmptyBlock();

        var body = peekBody();

        yield body.state;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doRuleStart() throws E {
    state = switch (state) {
      case _AT_MEDIA_BODY, _SHEET_BODY -> state;
      case _MEDIA_QUERY -> {
        pushBody(Body.MEDIA);

        visitBlockStart();

        yield state;
      }
      case _START -> {
        pushBody(Body.SHEET);

        yield state;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelector0(Action<E> action) throws E {
    switch (state) {
      case _AT_MEDIA_BODY:
        visitBeforeNextStatement();

        visitRuleStart();

        action.execute();

        break;
      case _IGNORE_IF_SINGLE_SELECTOR:
        visitRuleStart();

        visitClassSelector(currentClassName);

        currentClassName = null;

        action.execute();

        break;
      case _MEDIA_QUERY:
        visitRuleStart();

        action.execute();

        break;
      case _SHEET_BODY:
        visitBeforeNextStatement();

        visitRuleStart();

        action.execute();

        break;
      case _SELECTOR:
        action.execute();

        break;
      case _START:
        visitRuleStart();

        action.execute();

        break;
      default:
        throw new IllegalArgumentException("Unexpected state: " + state);
    }

    state = _SELECTOR;
  }

  private void doSelectorAttribute() throws E {
    var attributeName = getString();

    doSelector0(() -> visitAttributeSelector(attributeName));
  }

  private void doSelectorAttributeValue() throws E {
    var attributeName = getString();

    var opCode = getCode();

    var operator = AttributeValueOperator.getByCode(opCode);

    var value = getString();

    doSelector0(() -> visitAttributeValueSelector(attributeName, operator, value));
  }

  private void doSelectorClass() throws E {
    var className = getString();

    state = switch (state) {
      case _AT_MEDIA_BODY, _MEDIA_QUERY, _SHEET_BODY, _START:
        if (!classSelectorsByName.test(className)) {
          currentClassName = className;

          yield _IGNORE_IF_SINGLE_SELECTOR;
        }

        // fall through
      default:
        doSelector0(() -> visitClassSelector(className));

        yield state;
    };
  }

  private void doSelectorCombinator() throws E {
    var code = getCode();

    var combinator = Combinator.getByCode(code);

    doSelector0(() -> visitCombinator(combinator));
  }

  private void doSelectorId() throws E {
    var id = getString();

    doSelector0(() -> visitIdSelector(id));
  }

  private void doSelectorPseudoClass() throws E {
    var code = getCode();

    var selector = PseudoClassSelectors.getByCode(code);

    doSelector0(() -> visitSimpleSelector(selector));
  }

  private void doSelectorPseudoElement() throws E {
    var code = getCode();

    var selector = PseudoElementSelectors.getByCode(code);

    doSelector0(() -> visitSimpleSelector(selector));
  }

  private void doSelectorType() throws E {
    var code = getCode();

    var selector = TypeSelectors.getByCode(code);

    doSelector0(() -> visitSimpleSelector(selector));
  }

  private void doSelectorUniversal() throws E {
    var selector = UniversalSelector.getInstance();

    doSelector0(() -> visitUniversalSelector(selector));
  }

  private <T> void doValue0(Action<E> action) throws E {
    state = switch (state) {
      case _DECLARATION_START -> {
        action.execute();

        yield _DECLARATION_VALUES;
      }
      case _DECLARATION_VALUES -> {
        visitBeforeNextValue();

        action.execute();

        yield _DECLARATION_VALUES;
      }
      case _FUNCTION_START -> {
        action.execute();

        yield _FUNCTION_VALUES;
      }
      case _FUNCTION_VALUES -> {
        visitBeforeNextValue();

        action.execute();

        yield _FUNCTION_VALUES;
      }
      case _IGNORE_RULE -> _IGNORE_RULE;
      case _MEDIA_QUERY_DECLARATION -> {
        action.execute();

        visitLogicalExpressionEnd();

        yield _MEDIA_QUERY;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doValueAngleDouble() throws E {
    var unit = getAngleUnit();

    var value = getDouble();

    doValue0(() -> visitAngle(unit, value));
  }

  private void doValueAngleInt() throws E {
    var unit = getAngleUnit();

    var value = getCode();

    doValue0(() -> visitAngle(unit, value));
  }

  private void doValueColorHex() throws E {
    var hex = getString();

    doValue0(() -> visitColor(hex));
  }

  private void doValueColorName() throws E {
    var code = getCode();

    var color = Color.getByCode(code);

    doValue0(() -> visitColor(color));
  }

  private void doValueDouble() throws E {
    var value = getDouble();

    doValue0(() -> visitDouble(value));
  }

  private void doValueInt() throws E {
    var value = getCode();

    doValue0(() -> visitInt(value));
  }

  private void doValueKeyword() throws E {
    var code = getCode();

    var keyword = Keywords.getByCode(code);

    doValue0(() -> visitKeyword(keyword));
  }

  private void doValueKeywordCustom() throws E {
    var keyword = getString();

    doValue0(() -> visitKeyword(keyword));
  }

  private void doValueLengthDouble() throws E {
    var unit = getLengthUnit();

    var value = getDouble();

    doValue0(() -> visitLength(unit, value));
  }

  private void doValueLengthInt() throws E {
    var unit = getLengthUnit();

    var value = getCode();

    doValue0(() -> visitLength(unit, value));
  }

  private void doValuePercentageDouble() throws E {
    var value = getDouble();

    doValue0(() -> visitPercentage(value));
  }

  private void doValuePercentageInt() throws E {
    var value = getCode();

    doValue0(() -> visitPercentage(value));
  }

  private void doValueRgbaDouble() throws E {
    var doubleStartIndex = getCode();

    var r = getDouble(doubleStartIndex++);
    var g = getDouble(doubleStartIndex++);
    var b = getDouble(doubleStartIndex++);
    var alpha = getDouble(doubleStartIndex);

    doValue0(() -> visitRgba(r, g, b, alpha));
  }

  private void doValueRgbaInt() throws E {
    var r = getCode();
    var g = getCode();
    var b = getCode();
    var alpha = getDouble();

    doValue0(() -> visitRgba(r, g, b, alpha));
  }

  private void doValueRgbDouble() throws E {
    var doubleStartIndex = getCode();

    var r = getDouble(doubleStartIndex++);
    var g = getDouble(doubleStartIndex++);
    var b = getDouble(doubleStartIndex++);

    doValue0(() -> visitRgb(r, g, b));
  }

  private void doValueRgbDoubleAlpha() throws E {
    var doubleStartIndex = getCode();

    var r = getDouble(doubleStartIndex++);
    var g = getDouble(doubleStartIndex++);
    var b = getDouble(doubleStartIndex++);
    var alpha = getDouble(doubleStartIndex);

    doValue0(() -> visitRgb(r, g, b, alpha));
  }

  private void doValueRgbInt() throws E {
    var r = getCode();
    var g = getCode();
    var b = getCode();

    doValue0(() -> visitRgb(r, g, b));
  }

  private void doValueRgbIntAlpha() throws E {
    var r = getCode();
    var g = getCode();
    var b = getCode();
    var alpha = getDouble();

    doValue0(() -> visitRgb(r, g, b, alpha));
  }

  private void doValueString() throws E {
    var value = getString();

    doValue0(() -> visitString(value));
  }

  private void doValueUri() throws E {
    var value = getString();

    doValue0(() -> visitUri(value));
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
    return codes[cursor++];
  }

  private double getDouble() {
    int index;
    index = getCode();

    return getDouble(index);
  }

  private double getDouble(int index) {
    return doubles[index];
  }

  private LengthUnit getLengthUnit() {
    int unitCode;
    unitCode = getCode();

    return LengthUnit.getByCode(unitCode);
  }

  private String getString() {
    return getString(getCode());
  }

  private String getString(int index) {
    return strings.get(index);
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
