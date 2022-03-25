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

import br.com.objectos.core.array.CharArrays;
import br.com.objectos.core.array.DoubleArrays;
import br.com.objectos.core.array.IntArrays;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.select.PseudoClassSelector;
import br.com.objectos.css.select.PseudoElementSelector;
import br.com.objectos.css.select.TypeSelector;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.ColorHex;
import br.com.objectos.css.type.ColorKind;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.Creator;
import br.com.objectos.css.type.LengthUnit;
import br.com.objectos.css.type.Marker;
import br.com.objectos.css.type.Value;
import java.util.Arrays;

public class StyleSheetDsl implements Creator, Marker {

  private char[] chars;
  private int charsLength;

  private double[] doubles;
  private int doublesLength;

  private int[] objects;
  private int objectsLength;

  private int[] protos;
  private int protosLength;

  private MutableList<RuleElement> rulePrefix;

  StyleSheetDsl() {
    chars = new char[16 * 128];

    doubles = new double[10];

    objects = new int[128];

    protos = new int[128];
  }

  public static CompiledStyleSheet compile(StyleSheet sheet) {
    Checks.checkNotNull(sheet, "sheet == null");

    StyleSheetDsl dsl;
    dsl = new StyleSheetDsl();

    sheet.acceptStyleSheetDsl(dsl);

    return dsl.compile();
  }

  public final void addAtMedia(AtMediaElement[] elements) {
    Checks.checkNotNull(elements, "elements == null");

    addProto(ByteProto.AT_MEDIA_END);

    for (int i = elements.length - 1; i >= 0; i--) {
      AtMediaElement element;
      element = elements[i];

      if (element == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }

      element.acceptMediaQueryElementVisitor(this);
    }

    addProto(ByteProto.AT_MEDIA_START);

    addObject(ByteProto.AT_MEDIA_MARK);
  }

  public final void addClassSelector(ClassSelector selector) {
    addProtoString(
        ByteProto.SELECTOR_CLASS_OBJ,
        selector.className()
    );
  }

  @Override
  public final void addColor(ColorName color) {
    addProto(color.getCode());
    addProto(ByteProto.VALUE_COLOR_NAME);
  }

  public final void addCombinator(Combinator combinator) {
    addProto(combinator.getCode());
    addProto(ByteProto.SELECTOR_COMBINATOR);
  }

  public final void addDeclaration(StandardPropertyName name, double value) {
    Checks.checkNotNull(name, "name == null");

    addDeclarationEnd();
    addValueDouble(value);
    addDeclarationStart(name);
  }

  public final void addDeclaration(StandardPropertyName name, int value) {
    Checks.checkNotNull(name, "name == null");

    addDeclarationEnd();
    addValueInt(value);
    addDeclarationStart(name);
  }

  public final void addDeclaration(StandardPropertyName name, MultiDeclarationElement[] elements) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(elements, "elements == null");

    addProto(ByteProto.DECLARATION_MULTI_END);

    // yes, reverse
    for (int i = elements.length - 1; i >= 0; i--) {
      MultiDeclarationElement element = elements[i];

      if (element == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }

      element.markMultiDeclarationElement(this);
    }

    addProto(name.getCode());
    addProto(ByteProto.DECLARATION_MULTI_START);
  }

  public final void addDeclaration(StandardPropertyName name, String value) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(value, "value == null");

    addDeclarationEnd();
    addProtoString(ByteProto.VALUE_STRING, value);
    addDeclarationStart(name);
  }

  public final void addDeclaration(StandardPropertyName name, Value v1) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");

    v1.acceptValueCreator(this);

    addDeclarationEnd();
    v1.acceptValueMarker(this);
    addDeclarationStart(name);
  }

  public final void addDeclaration(StandardPropertyName name, Value v1, Value v2) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");

    v1.acceptValueCreator(this);
    v2.acceptValueCreator(this);

    addDeclarationEnd();
    // yes, reverse
    v2.acceptValueMarker(this);
    v1.acceptValueMarker(this);
    addDeclarationStart(name);
  }

  public final void addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");

    v1.acceptValueCreator(this);
    v2.acceptValueCreator(this);
    v3.acceptValueCreator(this);

    addDeclarationEnd();
    // yes, reverse
    v3.acceptValueMarker(this);
    v2.acceptValueMarker(this);
    v1.acceptValueMarker(this);
    addDeclarationStart(name);
  }

  public final void addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");

    v1.acceptValueCreator(this);
    v2.acceptValueCreator(this);
    v3.acceptValueCreator(this);
    v4.acceptValueCreator(this);

    addDeclarationEnd();
    // yes, reverse
    v4.acceptValueMarker(this);
    v3.acceptValueMarker(this);
    v2.acceptValueMarker(this);
    v1.acceptValueMarker(this);
    addDeclarationStart(name);
  }

  public final void addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");

    v1.acceptValueCreator(this);
    v2.acceptValueCreator(this);
    v3.acceptValueCreator(this);
    v4.acceptValueCreator(this);
    v5.acceptValueCreator(this);

    addDeclarationEnd();
    // yes, reverse
    v5.acceptValueMarker(this);
    v4.acceptValueMarker(this);
    v3.acceptValueMarker(this);
    v2.acceptValueMarker(this);
    v1.acceptValueMarker(this);
    addDeclarationStart(name);
  }

  public final void addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5, Value v6) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");
    Checks.checkNotNull(v6, "v6 == null");

    v1.acceptValueCreator(this);
    v2.acceptValueCreator(this);
    v3.acceptValueCreator(this);
    v4.acceptValueCreator(this);
    v5.acceptValueCreator(this);
    v6.acceptValueCreator(this);

    addDeclarationEnd();
    // yes, reverse
    v6.acceptValueMarker(this);
    v5.acceptValueMarker(this);
    v4.acceptValueMarker(this);
    v3.acceptValueMarker(this);
    v2.acceptValueMarker(this);
    v1.acceptValueMarker(this);
    addDeclarationStart(name);
  }

  public final void addFunction(StandardFunctionName name, Value v1) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(v1, "v1 == null");

    v1.acceptValueCreator(this);

    addFunctionEnd();

    v1.acceptValueMarker(this);

    addFunctionStart(name);
  }

  public final void addIdSelector(IdSelector selector) {
    addProtoString(
        ByteProto.SELECTOR_ID_OBJ,
        selector.id()
    );
  }

  @Override
  public final void addKeyword(StandardKeyword keyword) {
    addProto(keyword.getCode());
    addProto(ByteProto.VALUE_KEYWORD);
  }

  public final void addMediaType(MediaType type) {
    addProto(type.getCode());
    addProto(ByteProto.MEDIA_TYPE);
  }

  public final void addPseudoClassSelector(PseudoClassSelector selector) {
    addProto(selector.getCode());
    addProto(ByteProto.SELECTOR_PSEUDO_CLASS_OBJ);
  }

  public final void addPseudoElementSelector(PseudoElementSelector selector) {
    addProto(selector.getCode());
    addProto(ByteProto.SELECTOR_PSEUDO_ELEMENT_OBJ);
  }

  public void addRule(ImmutableList<RuleElement> elements) {
    Checks.checkNotNull(elements, "elements == null");

    addProto(ByteProto.RULE_END);

    for (int i = elements.size() - 1; i >= 0; i--) {
      RuleElement element;
      element = elements.get(i);

      if (element == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }

      element.acceptRuleElementVisitor(this);
    }

    addProto(ByteProto.RULE_START);
    addObject(ByteProto.RULE_MARK);
  }

  public void addRule(RuleElement[] elements) {
    Checks.checkNotNull(elements, "elements == null");

    addProto(ByteProto.RULE_END);

    for (int i = elements.length - 1; i >= 0; i--) {
      RuleElement element;
      element = elements[i];

      if (element == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }

      element.acceptRuleElementVisitor(this);
    }

    if (rulePrefix != null) {
      for (int i = rulePrefix.size() - 1; i >= 0; i--) {
        RuleElement element;
        element = rulePrefix.get(i);

        element.acceptRuleElementVisitor(this);
      }
    }

    addProto(ByteProto.RULE_START);
    addObject(ByteProto.RULE_MARK);
  }

  public final void addTypeSelector(TypeSelector selector) {
    addProto(selector.getCode());
    addProto(ByteProto.SELECTOR_TYPE_OBJ);
  }

  public final void addUniversalSelector(UniversalSelector selector) {
    addProto(ByteProto.SELECTOR_UNIVERSAL_OBJ);
  }

  @Override
  public final void addZero() {
    addValueInt(0);
  }

  public final void clearRulePrefix() {
    if (rulePrefix != null) {
      rulePrefix.clear();
    }
  }

  public final CompiledStyleSheet compile() {
    onCompile();

    Compiler compiler;
    compiler = new Compiler(this);

    return compiler.compile(protosLength);
  }

  @Override
  public final void createAngle(AngleUnit unit, double value) {
    addDoubleProto(value);
    addProto(unit.getCode());
    addProto(ByteProto.VALUE_ANGLE_DOUBLE);
  }

  @Override
  public final void createAngle(AngleUnit unit, int value) {
    addProto(value);
    addProto(unit.getCode());
    addProto(ByteProto.VALUE_ANGLE_INT);
  }

  public final void createAttributeSelector(String name) {
    addProtoString(
        ByteProto.SELECTOR_ATTRIBUTE,
        name
    );
  }

  public final void createAttributeValueElement(AttributeValueOperator operator, String value) {
    addProto(value.length());
    addProto(charsLength);
    addProto(operator.getCode());
    addProto(ByteProto.SELECTOR_ATTRIBUTE_VALUE_ELEMENT);

    addString(value);
  }

  public final void createAttributeValueSelector(String name) {
    addProtoString(
        ByteProto.SELECTOR_ATTRIBUTE_VALUE,
        name
    );
  }

  public final void createClassSelector(String className) {
    addProtoString(
        ByteProto.SELECTOR_CLASS,
        className
    );
  }

  @Override
  public final void createColor(String hex) {
    ColorHex.checkValidHexNotation(hex);

    addProtoString(ByteProto.VALUE_COLOR_HEX, hex);
  }

  @Override
  public final void createDouble(double value) {
    addDoubleProto(value);
    addProto(ByteProto.VALUE_DOUBLE_DSL);
  }

  public final void createIdSelector(String id) {
    addProtoString(
        ByteProto.SELECTOR_ID,
        id
    );
  }

  @Override
  public final void createInt(int value) {
    addProto(value);
    addProto(ByteProto.VALUE_INT_DSL);
  }

  @Override
  public final void createKeyword(String name) {
    Checks.checkNotNull(name, "name == null");

    for (char c : name.toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        continue;
      }
      if (c == '-') {
        continue;
      }
      throw new IllegalArgumentException("Invalid keyword name: " + name);
    }

    addProtoString(ByteProto.VALUE_KEYWORD_CUSTOM, name);
  }

  @Override
  public final void createLength(LengthUnit unit, double value) {
    addDoubleProto(value);
    addProto(unit.getCode());
    addProto(ByteProto.VALUE_LENGTH_DOUBLE);
  }

  @Override
  public final void createLength(LengthUnit unit, int value) {
    addProto(value);
    addProto(unit.getCode());
    addProto(ByteProto.VALUE_LENGTH_INT);
  }

  @Override
  public final void createPercentage(double value) {
    addDoubleProto(value);
    addProto(ByteProto.VALUE_PERCENTAGE_DOUBLE);
  }

  @Override
  public final void createPercentage(int value) {
    addProto(value);
    addProto(ByteProto.VALUE_PERCENTAGE_INT);
  }

  @Override
  public final void createRgb(double r, double g, double b) {
    addProto(doublesLength);

    addDouble(r);
    addDouble(g);
    addDouble(b);

    addProto(ByteProto.VALUE_RGB_DOUBLE);
  }

  @Override
  public final void createRgb(double r, double g, double b, double alpha) {
    addProto(doublesLength);

    addDouble(r);
    addDouble(g);
    addDouble(b);
    addDouble(alpha);

    addProto(ByteProto.VALUE_RGB_DOUBLE_ALPHA);
  }

  @Override
  public final void createRgb(int r, int g, int b) {
    addProto(b);
    addProto(g);
    addProto(r);
    addProto(ByteProto.VALUE_RGB_INT);
  }

  @Override
  public final void createRgb(int r, int g, int b, double alpha) {
    addDoubleProto(alpha);
    addProto(b);
    addProto(g);
    addProto(r);
    addProto(ByteProto.VALUE_RGB_INT_ALPHA);
  }

  @Override
  public final void createRgba(double r, double g, double b, double alpha) {
    addProto(doublesLength);

    addDouble(r);
    addDouble(g);
    addDouble(b);
    addDouble(alpha);

    addProto(ByteProto.VALUE_RGBA_DOUBLE);
  }

  @Override
  public final void createRgba(int r, int g, int b, double alpha) {
    addDoubleProto(alpha);
    addProto(b);
    addProto(g);
    addProto(r);
    addProto(ByteProto.VALUE_RGBA_INT);
  }

  @Override
  public final void createString(String value) {
    addProtoString(ByteProto.VALUE_STRING_DSL, value);
  }

  @Override
  public final void createUri(String value) {
    Checks.checkNotNull(value, "value == null");

    addProtoString(
        ByteProto.VALUE_URI,

        value
    );
  }

  public final void markAttributeSelector() {
    addProto(ByteProto.SELECTOR_ATTRIBUTE_MARK);
  }

  public final void markAttributeValueElement() {
    int proto = getLastProto();
    Checks.checkState(
        proto == ByteProto.SELECTOR_ATTRIBUTE_VALUE_ELEMENT,
        "not ", ByteProto.SELECTOR_ATTRIBUTE_VALUE_ELEMENT
    );
  }

  public final void markAttributeValueSelector() {
    addProto(ByteProto.SELECTOR_ATTRIBUTE_VALUE_MARK);
  }

  public final void markClassSelector() {
    addProto(ByteProto.SELECTOR_CLASS_MARK);
  }

  @Override
  public final void markColor(ColorKind kind) {
    Checks.checkNotNull(kind, "kind == null");

    switch (kind) {
      case HEX:
        addProto(ByteProto.VALUE_COLOR_HEX_MARK);
        break;
      case RGB_DOUBLE:
        addProto(ByteProto.VALUE_RGB_DOUBLE_MARK);
        break;
      case RGB_DOUBLE_ALPHA:
        addProto(ByteProto.VALUE_RGB_DOUBLE_ALPHA_MARK);
        break;
      case RGB_INT:
        addProto(ByteProto.VALUE_RGB_INT_MARK);
        break;
      case RGB_INT_ALPHA:
        addProto(ByteProto.VALUE_RGB_INT_ALPHA_MARK);
        break;
      case RGBA_DOUBLE:
        addProto(ByteProto.VALUE_RGBA_DOUBLE_MARK);
        break;
      case RGBA_INT:
        addProto(ByteProto.VALUE_RGBA_INT_MARK);
        break;
      default:
        throw new AssertionError("Unexpected kind " + kind);
    }
  }

  public final void markDeclaration() {
    addProto(ByteProto.DECLARATION_MARK);
  }

  @Override
  public final void markDouble() {
    addProto(ByteProto.VALUE_DOUBLE_MARK);
  }

  @Override
  public final void markDoubleAngle() {
    addProto(ByteProto.VALUE_ANGLE_DOUBLE_MARK);
  }

  @Override
  public final void markDoubleLength() {
    addProto(ByteProto.VALUE_LENGTH_DOUBLE_MARK);
  }

  @Override
  public final void markDoublePercentage() {
    addProto(ByteProto.VALUE_PERCENTAGE_DOUBLE_MARK);
  }

  @Override
  public final void markFunction() {
    addProto(ByteProto.VALUE_FUNCTION_MARK);
  }

  public final void markIdSelector() {
    addProto(ByteProto.SELECTOR_ID_MARK);
  }

  @Override
  public final void markInt() {
    addProto(ByteProto.VALUE_INT_MARK);
  }

  @Override
  public final void markIntAngle() {
    addProto(ByteProto.VALUE_ANGLE_INT_MARK);
  }

  @Override
  public final void markIntLength() {
    addProto(ByteProto.VALUE_LENGTH_INT_MARK);
  }

  @Override
  public final void markIntPercentage() {
    addProto(ByteProto.VALUE_PERCENTAGE_INT_MARK);
  }

  @Override
  public final void markKeyword() {
    addProto(ByteProto.VALUE_KEYWORD_CUSTOM_MARK);
  }

  public final void markMultiDeclarationElement() {
    addProto(ByteProto.DECLARATION_MULTI_ELEMENT_MARK);
  }

  public final void markRule() {
    int code;
    code = popObject();

    addProto(code);
  }

  @Override
  public final void markString() {
    addProto(ByteProto.VALUE_STRING_MARK);
  }

  @Override
  public final void markUri() {
    addProto(ByteProto.VALUE_URI_MARK);
  }

  public final void setRulePrefix(RuleElement[] elements) {
    if (rulePrefix == null) {
      rulePrefix = MutableList.create();
    }

    for (int i = 0; i < elements.length; i++) {
      RuleElement element;
      element = elements[i];

      rulePrefix.addWithNullMessage(element, "elements[", i, "] == null");
    }
  }

  void addDouble(double value) {
    doubles = DoubleArrays.copyIfNecessary(doubles, doublesLength);

    doubles[doublesLength++] = value;
  }

  final String charsToString() {
    return new String(chars, 0, charsLength);
  }

  final char[] getChars() {
    return Arrays.copyOf(chars, charsLength);
  }

  final double[] getDoubles() {
    return Arrays.copyOf(doubles, doublesLength);
  }

  final int getProto(int index) {
    return protos[index];
  }

  final int[] getProtos() {
    return Arrays.copyOf(protos, protosLength);
  }

  private void addDeclarationEnd() {
    addProto(ByteProto.DECLARATION_END);
  }

  private void addDeclarationStart(StandardPropertyName name) {
    addProto(name.getCode());
    addProto(ByteProto.DECLARATION_START);
  }

  private void addDoubleProto(double value) {
    addProto(doublesLength);
    addDouble(value);
  }

  private void addFunctionEnd() {
    addProto(ByteProto.FUNCTION_END);
  }

  private void addFunctionStart(StandardFunctionName name) {
    addProto(name.getCode());
    addProto(ByteProto.FUNCTION_START);
  }

  private void addObject(int code) {
    objects = IntArrays.copyIfNecessary(objects, objectsLength);

    objects[objectsLength++] = code;
  }

  private void addProto(int code) {
    protos = IntArrays.copyIfNecessary(protos, protosLength);

    protos[protosLength++] = code;
  }

  private void addProtoString(int code, String value) {
    addProto(value.length());
    addProto(charsLength);
    addProto(code);

    addString(value);
  }

  private void addString(String value) {
    chars = CharArrays.append(chars, charsLength, value);

    charsLength += value.length();
  }

  private void addValueDouble(double value) {
    addDoubleProto(value);
    addProto(ByteProto.VALUE_DOUBLE);
  }

  private void addValueInt(int value) {
    addProto(value);
    addProto(ByteProto.VALUE_INT);
  }

  private int getLastProto() {
    return protos[protosLength - 1];
  }

  private void onCompile() {
    addProto(ByteProto.ROOT_END);

    for (int i = objectsLength - 1; i >= 0; i--) {
      addProto(objects[i]);
    }

    addProto(ByteProto.ROOT_START);
  }

  private int popObject() {
    return objects[--objectsLength];
  }

}
