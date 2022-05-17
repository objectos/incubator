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
import java.util.Arrays;
import objectos.lang.Checks;

final class Compiler {

  private int[] codes;

  private int codesIndex;
  private int cursor;

  private final StyleSheetDsl dsl;
  // multi stack
  private int[] multi;

  private int multiIndex = -1;
  // stack
  private int[] stack;

  private int stackIndex = -1;

  public Compiler(StyleSheetDsl dsl) {
    this.dsl = dsl;

    codes = new int[1024];
    stack = new int[64];
    multi = new int[8];
  }

  public final CompiledStyleSheet compile(int protoSize) {
    cursor = protoSize;
    return compile0();
  }

  void setJumpToSlot() {
    int returnTo = codesIndex;
    codesIndex = pop();
    addCode(returnTo);
    codesIndex = returnTo;
  }

  private void addCode(int code) {
    codes = IntArrays.copyIfNecessary(codes, codesIndex);

    codes[codesIndex++] = code;
  }

  private void addMarked(int p1) {
    int returnTo = popAndSetCode();

    addCode(p1);

    setCode(returnTo);
  }

  private void addMarked(int p1, int p2) {
    int returnTo = popAndSetCode();

    addCode(p1);
    addCode(p2);

    setCode(returnTo);
  }

  private void addMarked(int p1, int p2, int p3) {
    int returnTo = popAndSetCode();

    addCode(p1);
    addCode(p2);
    addCode(p3);

    setCode(returnTo);
  }

  private void addMarked(int p1, int p2, int p3, int p4) {
    int returnTo = popAndSetCode();

    addCode(p1);
    addCode(p2);
    addCode(p3);
    addCode(p4);

    setCode(returnTo);
  }

  private CompiledStyleSheet compile0() {
    while (cursor > 0) {
      int proto;
      proto = getProto();

      processByteProto(proto);
    }

    return new CompiledStyleSheet(
        Arrays.copyOf(codes, codesIndex),
        dsl.getChars(),
        dsl.getDoubles()
    );
  }

  private void doDeclarationEnd() {
    if (isMulti()) {
      popMulti();
    }
    addCode(ByteCode.FLOW_RETURN);
  }

  private void doDeclarationMark() {
    addCode(ByteCode.FLOW_JMP);
    pushCode();
    addCode(0); // jmp position
  }

  private void doDeclarationMultiElementMark() {
    addCode(ByteCode.FLOW_JMP);
    pushMulti();
    addCode(0); // jump to slot
  }

  private void doDeclarationMultiEnd() {
    addCode(ByteCode.FLOW_RETURN);
  }

  private void doDeclarationMultiStart() {
    int returnTo = popAndSetCode();
    addCode(returnTo);
    setCode(returnTo);

    addCode(ByteCode.DECLARATION_START);
    addCode(getProto());
  }

  private void doDeclarationStart() {
    if (!isMulti()) {
      int returnTo = popAndSetCode();
      addCode(returnTo);
      setCode(returnTo);

      addCode(ByteCode.DECLARATION_START);
      addCode(getProto());
    } else {
      int returnTo = codesIndex;
      codesIndex = peekMulti();
      addCode(returnTo);
      codesIndex = returnTo;

      addCode(ByteCode.MULTI_DECLARATION_SEPARATOR);
      // consume property name
      getProto();
    }
  }

  private void doFunctionEnd() {
    addCode(ByteCode.FUNCTION_END);
    addCode(ByteCode.FLOW_RETURN);
  }

  private void doFunctionStart() {
    int returnTo;
    returnTo = popAndSetCode();

    addCode(returnTo);

    setCode(returnTo);

    addCode(ByteCode.FUNCTION_START);

    addCode(getProto());
  }

  private void doMarkedString() {
    int startIndex;
    startIndex = getProto();

    int length;
    length = getProto();

    addMarked(startIndex, length);
  }

  private void doMediaEnd() {
    addCode(ByteCode.MEDIA_END);
    addCode(ByteCode.FLOW_RETURN);
  }

  private void doMediaMark() {
    addCode(ByteCode.FLOW_JMP);
    pushCode();
    addCode(0); // jump to slot
  }

  private void doMediaStart() {
    setJumpToSlot();

    addCode(ByteCode.MEDIA_START);
  }

  private void doMediaType() {
    addCode(ByteCode.MEDIA_TYPE);
    addCode(getProto());
  }

  private void doRootEnd() {
    addCode(ByteCode.FLOW_RETURN);
  }

  private void doRootStart() {
    addCode(ByteCode.ROOT);
  }

  private void doRuleEnd() {
    addCode(ByteCode.RULE_END);
    addCode(ByteCode.FLOW_RETURN);
  }

  private void doRuleMark() {
    addCode(ByteCode.FLOW_JMP);
    pushCode();
    addCode(0); // jump to slot
  }

  private void doRuleStart() {
    setJumpToSlot();

    addCode(ByteCode.RULE_START);
  }

  private void doSelectorAttribute() {
    doMarkedString();
  }

  private void doSelectorAttributeMark() {
    addCode(ByteCode.SELECTOR_ATTRIBUTE);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private void doSelectorAttributeValue() {
    int returnTo = popAndSetCode();

    // name
    addCode(getProto()); // start index
    addCode(getProto()); // length
    Checks.checkState(
        getProto() == ByteProto.SELECTOR_ATTRIBUTE_VALUE_ELEMENT,
        "expected ", ByteProto.SELECTOR_ATTRIBUTE_VALUE_ELEMENT
    );
    // operator
    addCode(getProto());
    // value
    addCode(getProto()); // start index
    addCode(getProto()); // length

    setCode(returnTo);
  }

  private void doSelectorAttributeValueMark() {
    addCode(ByteCode.SELECTOR_ATTRIBUTE_VALUE);
    pushCode();
    // name
    addCode(0); // start index
    addCode(0); // length
    // operator
    addCode(0);
    // value
    addCode(0); // start index
    addCode(0); // length
  }

  private void doSelectorClass() {
    doMarkedString();
  }

  private void doSelectorClassMark() {
    addCode(ByteCode.SELECTOR_CLASS);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private void doSelectorClassObj() {
    addCode(ByteCode.SELECTOR_CLASS);
    addCode(getProto()); // start index
    addCode(getProto()); // length
  }

  private void doSelectorCombinator() {
    addCode(ByteCode.SELECTOR_COMBINATOR);
    addCode(getProto());
  }

  private void doSelectorId() {
    doMarkedString();
  }

  private void doSelectorIdMark() {
    addCode(ByteCode.SELECTOR_ID);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private void doSelectorIdObj() {
    addCode(ByteCode.SELECTOR_ID);
    addCode(getProto()); // start index
    addCode(getProto()); // length
  }

  private void doSelectorPseudoClassObj() {
    addCode(ByteCode.SELECTOR_PSEUDO_CLASS);
    addCode(getProto());
  }

  private void doSelectorPseudoElementObj() {
    addCode(ByteCode.SELECTOR_PSEUDO_ELEMENT);
    addCode(getProto());
  }

  private void doSelectorTypeObj() {
    addCode(ByteCode.SELECTOR_TYPE);
    addCode(getProto());
  }

  private void doSelectorUniversalObj() {
    addCode(ByteCode.SELECTOR_UNIVERSAL);
  }

  private void doValueAngleDouble() {
    int unit;
    unit = getProto();

    int doubleIndex;
    doubleIndex = getProto();

    addMarked(unit, doubleIndex);
  }

  private void doValueAngleDoubleMark() {
    addCode(ByteCode.VALUE_ANGLE_DOUBLE);
    pushCode();
    addCode(0); // unit
    addCode(0); // double index
  }

  private void doValueAngleInt() {
    int unit;
    unit = getProto();

    int value;
    value = getProto();

    addMarked(unit, value);
  }

  private void doValueAngleIntMark() {
    addCode(ByteCode.VALUE_ANGLE_INT);
    pushCode();
    addCode(0); // unit
    addCode(0); // value
  }

  private void doValueColorHex() {
    doMarkedString();
  }

  private void doValueColorHexMark() {
    addCode(ByteCode.VALUE_COLOR_HEX);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private void doValueColorNamed() {
    addCode(ByteCode.VALUE_COLOR_NAME);
    addCode(getProto());
  }

  private void doValueDouble() {
    addCode(ByteCode.VALUE_DOUBLE);
    addCode(getProto());
  }

  private void doValueDoubleDsl() {
    int doubleIndex;
    doubleIndex = getProto();

    addMarked(doubleIndex);
  }

  private void doValueDoubleMark() {
    addCode(ByteCode.VALUE_DOUBLE);
    pushCode();
    addCode(0); // index
  }

  private void doValueFunctionMark() {
    addCode(ByteCode.FLOW_JMP);
    pushCode();
    addCode(0); // jmp position
  }

  private void doValueInt() {
    addCode(ByteCode.VALUE_INT);
    addCode(getProto());
  }

  private void doValueIntDsl() {
    int intValue;
    intValue = getProto();

    addMarked(intValue);
  }

  private void doValueIntMark() {
    addCode(ByteCode.VALUE_INT);
    pushCode();
    addCode(0); // intValue
  }

  private void doValueKeyword() {
    addCode(ByteCode.VALUE_KEYWORD);
    addCode(getProto());
  }

  private void doValueKeywordCustom() {
    doMarkedString();
  }

  private void doValueKeywordCustomMark() {
    addCode(ByteCode.VALUE_KEYWORD_CUSTOM);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private void doValueLengthDouble() {
    int unit;
    unit = getProto();

    int doubleIndex;
    doubleIndex = getProto();

    addMarked(unit, doubleIndex);
  }

  private void doValueLengthDoubleMark() {
    addCode(ByteCode.VALUE_LENGTH_DOUBLE);
    pushCode();
    addCode(0); // unit
    addCode(0); // double index
  }

  private void doValueLengthInt() {
    int unit;
    unit = getProto();

    int value;
    value = getProto();

    addMarked(unit, value);
  }

  private void doValueLengthIntMark() {
    addCode(ByteCode.VALUE_LENGTH_INT);
    pushCode();
    addCode(0); // unit
    addCode(0); // value
  }

  private void doValuePercentageDouble() {
    int doubleIndex;
    doubleIndex = getProto();

    addMarked(doubleIndex);
  }

  private void doValuePercentageDoubleMark() {
    addCode(ByteCode.VALUE_PERCENTAGE_DOUBLE);
    pushCode();
    addCode(0); // double index
  }

  private void doValuePercentageInt() {
    int value;
    value = getProto();

    addMarked(value);
  }

  private void doValuePercentageIntMark() {
    addCode(ByteCode.VALUE_PERCENTAGE_INT);
    pushCode();
    addCode(0); // value
  }

  private void doValueRgbaDouble() {
    int doubleStartIndex;
    doubleStartIndex = getProto();

    addMarked(doubleStartIndex);
  }

  private void doValueRgbaDoubleMark() {
    addCode(ByteCode.VALUE_RGBA_DOUBLE);
    pushCode();
    addCode(0); // double start index
  }

  private void doValueRgbaInt() {
    int r;
    r = getProto();

    int g;
    g = getProto();

    int b;
    b = getProto();

    int alphaDoubleIndexa;
    alphaDoubleIndexa = getProto();

    addMarked(r, g, b, alphaDoubleIndexa);
  }

  private void doValueRgbaIntMark() {
    addCode(ByteCode.VALUE_RGBA_INT);
    pushCode();
    addCode(0); // r
    addCode(0); // g
    addCode(0); // b
    addCode(0); // alpha double index
  }

  private void doValueRgbDouble() {
    int doubleStartIndex;
    doubleStartIndex = getProto();

    addMarked(doubleStartIndex);
  }

  private void doValueRgbDoubleAlpha() {
    int doubleStartIndex;
    doubleStartIndex = getProto();

    addMarked(doubleStartIndex);
  }

  private void doValueRgbDoubleAlphaMark() {
    addCode(ByteCode.VALUE_RGB_DOUBLE_ALPHA);
    pushCode();
    addCode(0); // double start index
  }

  private void doValueRgbDoubleMark() {
    addCode(ByteCode.VALUE_RGB_DOUBLE);
    pushCode();
    addCode(0); // double start index
  }

  private void doValueRgbInt() {
    int r;
    r = getProto();

    int g;
    g = getProto();

    int b;
    b = getProto();

    addMarked(r, g, b);
  }

  private void doValueRgbIntAlpha() {
    doValueRgbaInt();
  }

  private void doValueRgbIntAlphaMark() {
    addCode(ByteCode.VALUE_RGB_INT_ALPHA);
    pushCode();
    addCode(0); // r
    addCode(0); // g
    addCode(0); // b
    addCode(0); // alpha double index
  }

  private void doValueRgbIntMark() {
    addCode(ByteCode.VALUE_RGB_INT);
    pushCode();
    addCode(0); // r
    addCode(0); // g
    addCode(0); // b
  }

  private void doValueString() {
    addCode(ByteCode.VALUE_STRING);
    addCode(getProto()); // start index
    addCode(getProto()); // length
  }

  private void doValueStringDsl() {
    doMarkedString();
  }

  private void doValueStringMark() {
    addCode(ByteCode.VALUE_STRING);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private void doValueUri() {
    doMarkedString();
  }

  private void doValueUriMark() {
    addCode(ByteCode.VALUE_URI);
    pushCode();
    addCode(0); // start index
    addCode(0); // length
  }

  private int getProto() {
    return dsl.getProto(--cursor);
  }

  private boolean isMulti() {
    return multiIndex >= 0;
  }

  private int peekMulti() {
    return multi[multiIndex];
  }

  private int pop() {
    return stack[stackIndex--];
  }

  private int popAndSetCode() {
    int returnTo;
    returnTo = codesIndex;

    int top;
    top = pop();

    setCode(top);

    return returnTo;
  }

  private int popMulti() {
    return multi[multiIndex--];
  }

  private void processByteProto(int proto) {
    switch (proto) {
      case ByteProto.DECLARATION_END:
        doDeclarationEnd();
        break;
      case ByteProto.DECLARATION_MARK:
        doDeclarationMark();
        break;
      case ByteProto.DECLARATION_START:
        doDeclarationStart();
        break;
      case ByteProto.DECLARATION_MULTI_ELEMENT_MARK:
        doDeclarationMultiElementMark();
        break;
      case ByteProto.DECLARATION_MULTI_END:
        doDeclarationMultiEnd();
        break;
      case ByteProto.DECLARATION_MULTI_START:
        doDeclarationMultiStart();
        break;

      case ByteProto.AT_MEDIA_END:
        doMediaEnd();
        break;
      case ByteProto.AT_MEDIA_MARK:
        doMediaMark();
        break;
      case ByteProto.AT_MEDIA_START:
        doMediaStart();
        break;
      case ByteProto.MEDIA_TYPE:
        doMediaType();
        break;

      case ByteProto.FUNCTION_END:
        doFunctionEnd();
        break;
      case ByteProto.FUNCTION_START:
        doFunctionStart();
        break;

      case ByteProto.SELECTOR_ATTRIBUTE:
        doSelectorAttribute();
        break;
      case ByteProto.SELECTOR_ATTRIBUTE_MARK:
        doSelectorAttributeMark();
        break;
      case ByteProto.SELECTOR_ATTRIBUTE_VALUE:
        doSelectorAttributeValue();
        break;
      case ByteProto.SELECTOR_ATTRIBUTE_VALUE_MARK:
        doSelectorAttributeValueMark();
        break;
      case ByteProto.SELECTOR_CLASS:
        doSelectorClass();
        break;
      case ByteProto.SELECTOR_CLASS_OBJ:
        doSelectorClassObj();
        break;
      case ByteProto.SELECTOR_CLASS_MARK:
        doSelectorClassMark();
        break;
      case ByteProto.SELECTOR_COMBINATOR:
        doSelectorCombinator();
        break;
      case ByteProto.SELECTOR_ID:
        doSelectorId();
        break;
      case ByteProto.SELECTOR_ID_OBJ:
        doSelectorIdObj();
        break;
      case ByteProto.SELECTOR_ID_MARK:
        doSelectorIdMark();
        break;
      case ByteProto.SELECTOR_PSEUDO_CLASS_OBJ:
        doSelectorPseudoClassObj();
        break;
      case ByteProto.SELECTOR_PSEUDO_ELEMENT_OBJ:
        doSelectorPseudoElementObj();
        break;
      case ByteProto.SELECTOR_TYPE_OBJ:
        doSelectorTypeObj();
        break;
      case ByteProto.SELECTOR_UNIVERSAL_OBJ:
        doSelectorUniversalObj();
        break;

      case ByteProto.ROOT_END:
        doRootEnd();
        break;
      case ByteProto.ROOT_START:
        doRootStart();
        break;

      case ByteProto.RULE_END:
        doRuleEnd();
        break;
      case ByteProto.RULE_MARK:
        doRuleMark();
        break;
      case ByteProto.RULE_START:
        doRuleStart();
        break;

      case ByteProto.VALUE_ANGLE_DOUBLE:
        doValueAngleDouble();
        break;
      case ByteProto.VALUE_ANGLE_DOUBLE_MARK:
        doValueAngleDoubleMark();
        break;
      case ByteProto.VALUE_ANGLE_INT:
        doValueAngleInt();
        break;
      case ByteProto.VALUE_ANGLE_INT_MARK:
        doValueAngleIntMark();
        break;
      case ByteProto.VALUE_COLOR_HEX:
        doValueColorHex();
        break;
      case ByteProto.VALUE_COLOR_HEX_MARK:
        doValueColorHexMark();
        break;
      case ByteProto.VALUE_COLOR_NAME:
        doValueColorNamed();
        break;
      case ByteProto.VALUE_DOUBLE:
        doValueDouble();
        break;
      case ByteProto.VALUE_DOUBLE_DSL:
        doValueDoubleDsl();
        break;
      case ByteProto.VALUE_DOUBLE_MARK:
        doValueDoubleMark();
        break;
      case ByteProto.VALUE_FUNCTION_MARK:
        doValueFunctionMark();
        break;
      case ByteProto.VALUE_INT:
        doValueInt();
        break;
      case ByteProto.VALUE_INT_DSL:
        doValueIntDsl();
        break;
      case ByteProto.VALUE_INT_MARK:
        doValueIntMark();
        break;
      case ByteProto.VALUE_LENGTH_DOUBLE:
        doValueLengthDouble();
        break;
      case ByteProto.VALUE_LENGTH_DOUBLE_MARK:
        doValueLengthDoubleMark();
        break;
      case ByteProto.VALUE_LENGTH_INT:
        doValueLengthInt();
        break;
      case ByteProto.VALUE_LENGTH_INT_MARK:
        doValueLengthIntMark();
        break;
      case ByteProto.VALUE_KEYWORD:
        doValueKeyword();
        break;
      case ByteProto.VALUE_KEYWORD_CUSTOM:
        doValueKeywordCustom();
        break;
      case ByteProto.VALUE_KEYWORD_CUSTOM_MARK:
        doValueKeywordCustomMark();
        break;
      case ByteProto.VALUE_PERCENTAGE_DOUBLE:
        doValuePercentageDouble();
        break;
      case ByteProto.VALUE_PERCENTAGE_DOUBLE_MARK:
        doValuePercentageDoubleMark();
        break;
      case ByteProto.VALUE_PERCENTAGE_INT:
        doValuePercentageInt();
        break;
      case ByteProto.VALUE_PERCENTAGE_INT_MARK:
        doValuePercentageIntMark();
        break;
      case ByteProto.VALUE_RGB_DOUBLE:
        doValueRgbDouble();
        break;
      case ByteProto.VALUE_RGB_DOUBLE_MARK:
        doValueRgbDoubleMark();
        break;
      case ByteProto.VALUE_RGB_DOUBLE_ALPHA:
        doValueRgbDoubleAlpha();
        break;
      case ByteProto.VALUE_RGB_DOUBLE_ALPHA_MARK:
        doValueRgbDoubleAlphaMark();
        break;
      case ByteProto.VALUE_RGB_INT:
        doValueRgbInt();
        break;
      case ByteProto.VALUE_RGB_INT_MARK:
        doValueRgbIntMark();
        break;
      case ByteProto.VALUE_RGB_INT_ALPHA:
        doValueRgbIntAlpha();
        break;
      case ByteProto.VALUE_RGB_INT_ALPHA_MARK:
        doValueRgbIntAlphaMark();
        break;
      case ByteProto.VALUE_RGBA_INT:
        doValueRgbaInt();
        break;
      case ByteProto.VALUE_RGBA_INT_MARK:
        doValueRgbaIntMark();
        break;
      case ByteProto.VALUE_RGBA_DOUBLE:
        doValueRgbaDouble();
        break;
      case ByteProto.VALUE_RGBA_DOUBLE_MARK:
        doValueRgbaDoubleMark();
        break;
      case ByteProto.VALUE_STRING:
        doValueString();
        break;
      case ByteProto.VALUE_STRING_DSL:
        doValueStringDsl();
        break;
      case ByteProto.VALUE_STRING_MARK:
        doValueStringMark();
        break;
      case ByteProto.VALUE_URI:
        doValueUri();
        break;
      case ByteProto.VALUE_URI_MARK:
        doValueUriMark();
        break;
      default:
        throw new UnsupportedOperationException("Implement me: " + proto);
    }
  }

  private void pushCode() {
    stackIndex++;

    stack = IntArrays.copyIfNecessary(stack, stackIndex);

    stack[stackIndex] = codesIndex;
  }

  private void pushMulti() {
    multiIndex++;

    multi = IntArrays.copyIfNecessary(multi, multiIndex);

    multi[multiIndex] = codesIndex;
  }

  private void setCode(int returnTo) {
    codesIndex = returnTo;
  }

}
