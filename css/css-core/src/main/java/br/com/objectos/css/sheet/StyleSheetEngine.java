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
import br.com.objectos.css.type.LengthUnit;
import java.util.ArrayDeque;
import java.util.Deque;
import objectos.util.IntArrays;

public abstract class StyleSheetEngine<E extends Exception> extends StyleSheetCompiler
    implements Context.Adapter<E> {

  @FunctionalInterface
  private interface DoValue<X extends Exception> {
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

  private static final int _MEDIA_QUERY = 9;

  private static final int _MEDIA_QUERY_DECLARATION = 10;

  private static final int _RULE_START = 11;

  private static final int _SELECTOR = 12;

  static final int _SHEET_BODY = 13;

  private final Deque<Body> bodyStack = new ArrayDeque<>(3);

  private int[] call = new int[64];

  private int callIndex = -1;

  private int state;

  protected StyleSheetEngine() {}

  public final void execute() throws E {
    state = _START;

    cursor = 0;

    while (state != _STOP) {
      execute0();
    }

    //context = context.visitStyleSheetEnd(this);
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
      case _AT_MEDIA_BODY -> {

        visitBeforeNextStatement();

        visitRuleStart();

        yield _RULE_START;
      }
      case _MEDIA_QUERY -> {
        pushBody(Body.MEDIA);

        visitBlockStart();

        visitRuleStart();

        yield _RULE_START;
      }
      case _SHEET_BODY -> {
        visitBeforeNextStatement();

        visitRuleStart();

        yield _RULE_START;
      }
      case _START -> {
        pushBody(Body.SHEET);

        visitRuleStart();

        yield _RULE_START;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelectorAttribute() throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        var attributeName = getString();

        visitAttributeSelector(attributeName);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelectorAttributeValue() throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        var attributeName = getString();

        var opCode = getCode();

        var operator = AttributeValueOperator.getByCode(opCode);

        var value = getString();

        visitAttributeValueSelector(attributeName, operator, value);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelectorClass() throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        var className = getString();

        visitClassSelector(className);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelectorCombinator() throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        var code = getCode();

        var combinator = Combinator.getByCode(code);

        visitCombinator(combinator);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelectorId() throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        var id = getString();

        visitIdSelector(id);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSelectorPseudoClass() throws E {
    var code = getCode();

    var selector = PseudoClassSelectors.getByCode(code);

    doSimpleSelector(selector);
  }

  private void doSelectorPseudoElement() throws E {
    var code = getCode();

    var selector = PseudoElementSelectors.getByCode(code);

    doSimpleSelector(selector);
  }

  private void doSelectorType() throws E {
    var code = getCode();

    var selector = TypeSelectors.getByCode(code);

    doSimpleSelector(selector);
  }

  private void doSelectorUniversal() throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        var selector = UniversalSelector.getInstance();

        visitUniversalSelector(selector);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private void doSimpleSelector(SimpleSelector selector) throws E {
    state = switch (state) {
      case _RULE_START, _SELECTOR -> {
        visitSimpleSelector(selector);

        yield _SELECTOR;
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    };
  }

  private <T> void doValue0(DoValue<E> action) throws E {
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
