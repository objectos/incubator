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

import br.com.objectos.css.Css;
import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.io.MinifiedCssWriter;
import br.com.objectos.css.io.PrettyCssWriter;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.PseudoElementSelector;
import br.com.objectos.css.select.Selector;
import br.com.objectos.css.select.SelectorList;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.AngleType;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.ColorKind;
import br.com.objectos.css.type.ColorType;
import br.com.objectos.css.type.Creator;
import br.com.objectos.css.type.DoubleType;
import br.com.objectos.css.type.FontFamilyValue;
import br.com.objectos.css.type.IntType;
import br.com.objectos.css.type.LengthType;
import br.com.objectos.css.type.LengthUnit;
import br.com.objectos.css.type.Marker;
import br.com.objectos.css.type.OutlineValue;
import br.com.objectos.css.type.PercentageType;
import br.com.objectos.css.type.StringType;
import br.com.objectos.css.type.UriType;
import br.com.objectos.css.type.Value;
import br.com.objectos.css.type.Zero;
import objectos.lang.Checks;

public abstract class AbstractStyleSheet extends GeneratedStyleSheet
    implements
    StyleSheet {

  protected static final MediaType all = MediaType.ALL;

  protected static final MediaType print = MediaType.PRINT;

  protected static final MediaType screen = MediaType.SCREEN;

  private StyleSheetDsl dsl;

  protected AbstractStyleSheet() {}

  @Override
  public final void acceptStyleSheetDsl(StyleSheetDsl dsl) {
    if (this.dsl != null) {
      throw new IllegalStateException("Only one Dsl instance per time.");
    }

    this.dsl = Checks.checkNotNull(dsl, "dsl == null");

    try {
      this.dsl.clearRulePrefix();

      definition();
    } finally {
      this.dsl = null;
    }
  }

  @Override
  public final CompiledStyleSheet compile() {
    return StyleSheetDsl.compile(this);
  }

  @Override
  public final String printMinified() {
    CompiledStyleSheet compiled;
    compiled = compile();

    return MinifiedCssWriter.toString(compiled);
  }

  @Override
  public final String toString() {
    CompiledStyleSheet compiled;
    compiled = compile();

    return PrettyCssWriter.toString(compiled);
  }

  protected final RuleElement _webkitTextSizeAdjust(PercentageType pct) {
    // return Property.getStandard("-webkit-text-size-adjust", pct);
    throw new UnsupportedOperationException("Implement me");
  }

  protected final MediaType all() {
    return MediaType.ALL;
  }

  protected final AtMediaElement and(MediaExpression expression) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final UniversalSelector any() {
    return UniversalSelector.getInstance();
  }

  protected final RuleElement attr(String name) {
    dsl.createAttributeSelector(name);

    return ThisAttributeSelector.INSTANCE;
  }

  protected final ThisAttributeValueSelector attr(String name, ThisAttributeValueElement element) {
    element.acceptStyleSheetDsl(dsl);

    dsl.createAttributeValueSelector(name);

    return ThisAttributeValueSelector.INSTANCE;
  }

  protected final void clearRulePrefix() {
    dsl.clearRulePrefix();
  }

  protected final RuleElement cn(String className) {
    dsl.createClassSelector(className);

    return ThisClassSelector.INSTANCE;
  }

  protected abstract void definition();

  protected final ThisAttributeValueElement eq(String value) {
    dsl.createAttributeValueElement(AttributeValueOperator.EQUALS, value);

    return ThisAttributeValueElement.INSTANCE;
  }

  protected final Combinator gt() {
    return Combinator.CHILD;
  }

  protected final ColorType hex(String hex) {
    dsl.createColor(hex);

    return ThisColorType.HEX;
  }

  protected final RuleElement id(String id) {
    dsl.createIdSelector(id);

    return ThisIdSelector.INSTANCE;
  }

  protected final void install(StyleSheet sheet) {
    Checks.checkNotNull(sheet, "sheet == null");

    sheet.acceptStyleSheetDsl(dsl);
  }

  protected final CustomKeyword keyword(String name) {
    dsl.createKeyword(name);

    return CustomKeyword.INSTANCE;
  }

  protected final DoubleType l(double value) {
    dsl.createDouble(value);

    return ThisDoubleType.INSTANCE;
  }

  protected final IntType l(int value) {
    dsl.createInt(value);

    return ThisIntType.INSTANCE;
  }

  protected final StringType l(String value) {
    dsl.createString(value);

    return ThisStringType.INSTANCE;
  }

  protected final SelectorList list(Selector... selectors) {
    return Css.list(selectors);
  }

  @Override
  protected final ThisMaxHeightDeclaration maxHeight(LengthType length) {
    dsl.addDeclaration(StandardPropertyName.MAX_HEIGHT, length);

    return ThisMaxHeightDeclaration.INSTANCE;
  }

  @Override
  protected final ThisMaxWidthDeclaration maxWidth(LengthType length) {
    dsl.addDeclaration(StandardPropertyName.MAX_WIDTH, length);

    return ThisMaxWidthDeclaration.INSTANCE;
  }

  protected final void media(AtMediaElement... elements) {
    dsl.addAtMedia(elements);
  }

  @Override
  protected final ThisMinHeightDeclaration minHeight(LengthType length) {
    dsl.addDeclaration(StandardPropertyName.MIN_HEIGHT, length);

    return ThisMinHeightDeclaration.INSTANCE;
  }

  @Override
  protected final ThisMinWidthDeclaration minWidth(LengthType length) {
    dsl.addDeclaration(StandardPropertyName.MIN_WIDTH, length);

    return ThisMinWidthDeclaration.INSTANCE;
  }

  protected final Combinator or() {
    return Combinator.LIST;
  }

  protected final PercentageType pct(double value) {
    dsl.createPercentage(value);

    return ThisPercentageTypeDouble.INSTANCE;
  }

  protected final PercentageType pct(int value) {
    dsl.createPercentage(value);

    return ThisPercentageTypeInt.INSTANCE;
  }

  protected final Combinator plus() {
    return Combinator.ADJACENT_SIBLING;
  }

  protected final ColorType rgb(double r, double g, double b) {
    dsl.createRgb(r, g, b);

    return ThisColorType.RGB_DOUBLE;
  }

  protected final ColorType rgb(double r, double g, double b, double alpha) {
    dsl.createRgb(r, g, b, alpha);

    return ThisColorType.RGB_DOUBLE_ALPHA;
  }

  protected final ColorType rgb(int r, int g, int b) {
    dsl.createRgb(r, g, b);

    return ThisColorType.RGB_INT;
  }

  protected final ColorType rgb(int r, int g, int b, double alpha) {
    dsl.createRgb(r, g, b, alpha);

    return ThisColorType.RGB_INT_ALPHA;
  }

  protected final ColorType rgba(double r, double g, double b, double alpha) {
    dsl.createRgba(r, g, b, alpha);

    return ThisColorType.RGBA_DOUBLE;
  }

  protected final ColorType rgba(int r, int g, int b, double alpha) {
    dsl.createRgba(r, g, b, alpha);

    return ThisColorType.RGBA_INT;
  }

  protected final void setRulePrefix(RuleElement... elements) {
    dsl.setRulePrefix(elements);
  }

  protected final Combinator sp() {
    return Combinator.DESCENDANT;
  }

  protected final ThisAttributeValueElement startsWith(String value) {
    dsl.createAttributeValueElement(AttributeValueOperator.STARTS_WITH, value);

    return ThisAttributeValueElement.INSTANCE;
  }

  protected final StyleType style(RuleElement... elements) {
    addRule(elements);

    return StyleType.INSTANCE;
  }

  protected final Combinator tilde() {
    return Combinator.GENERAL_SIBLING;
  }

  protected final UriType url(String value) {
    dsl.createUri(value);

    return ThisUri.INSTANCE;
  }

  protected final PseudoElementSelector webkitFileUploadButton() {
    return Css._WEBKIT_FILE_UPLOAD_BUTTON;
  }

  protected final Zero zero() {
    return Zero.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(StandardPropertyName name, double value) {
    dsl.addDeclaration(name, value);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(StandardPropertyName name, int value) {
    dsl.addDeclaration(name, value);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(
      StandardPropertyName name, MultiDeclarationElement... elements) {
    dsl.addDeclaration(name, elements);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(StandardPropertyName name, String value) {
    dsl.addDeclaration(name, value);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(StandardPropertyName name, Value v1) {
    dsl.addDeclaration(name, v1);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2) {
    dsl.addDeclaration(name, v1, v2);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3) {
    dsl.addDeclaration(name, v1, v2, v3);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4) {
    dsl.addDeclaration(name, v1, v2, v3, v4);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5) {
    dsl.addDeclaration(name, v1, v2, v3, v4, v5);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyDeclaration addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5, Value v6) {
    dsl.addDeclaration(name, v1, v2, v3, v4, v5, v6);

    return ThisAnyDeclaration.INSTANCE;
  }

  @Override
  final AnyFunction addFunction(StandardFunctionName name, Value v1) {
    dsl.addFunction(name, v1);

    return ThisAnyFunction.INSTANCE;
  }

  @Override
  final AngleType getAngle(AngleUnit unit, double value) {
    dsl.createAngle(unit, value);

    return ThisAngleTypeDouble.INSTANCE;
  }

  @Override
  final AngleType getAngle(AngleUnit unit, int value) {
    dsl.createAngle(unit, value);

    return ThisAngleTypeInt.INSTANCE;
  }

  @Override
  final LengthType getLength(LengthUnit unit, double value) {
    dsl.createLength(unit, value);

    return ThisLengthTypeDouble.INSTANCE;
  }

  @Override
  final LengthType getLength(LengthUnit unit, int value) {
    dsl.createLength(unit, value);

    return ThisLengthTypeInt.INSTANCE;
  }

  private void addRule(RuleElement... elements) {
    dsl.addRule(elements);
  }

  protected final static class CustomKeyword implements FontFamilyValue, OutlineValue {

    static final CustomKeyword INSTANCE = new CustomKeyword();

    private CustomKeyword() {}

    @Override
    public final void acceptValueCreator(Creator creator) {
      // noop
    }

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markKeyword();
    }

  }

  protected final static class StyleType implements AtMediaElement {

    static final StyleType INSTANCE = new StyleType();

    private StyleType() {}

    @Override
    public final void acceptMediaQueryElementVisitor(StyleSheetDsl dsl) {
      dsl.markRule();
    }

  }

  protected final static class ThisAttributeValueElement {

    static final ThisAttributeValueElement INSTANCE = new ThisAttributeValueElement();

    private ThisAttributeValueElement() {}

    final void acceptStyleSheetDsl(StyleSheetDsl dsl) {
      dsl.markAttributeValueElement();
    }

  }

  protected final static class ThisMaxHeightDeclaration
      extends AbstractMediaExpressionOrRuleElement implements MaxHeightDeclaration {

    static final ThisMaxHeightDeclaration INSTANCE = new ThisMaxHeightDeclaration();

    private ThisMaxHeightDeclaration() {}

  }

  protected final static class ThisMaxWidthDeclaration
      extends AbstractMediaExpressionOrRuleElement implements MaxWidthDeclaration {

    static final ThisMaxWidthDeclaration INSTANCE = new ThisMaxWidthDeclaration();

    private ThisMaxWidthDeclaration() {}

  }

  protected final static class ThisMinHeightDeclaration
      extends AbstractMediaExpressionOrRuleElement implements MinHeightDeclaration {

    static final ThisMinHeightDeclaration INSTANCE = new ThisMinHeightDeclaration();

    private ThisMinHeightDeclaration() {}

  }

  protected final static class ThisMinWidthDeclaration
      extends AbstractMediaExpressionOrRuleElement implements MinWidthDeclaration {

    static final ThisMinWidthDeclaration INSTANCE = new ThisMinWidthDeclaration();

    private ThisMinWidthDeclaration() {}

  }

  private abstract static class AbstractMediaExpressionOrRuleElement
      implements
      MediaExpression,
      AtMediaElement,
      RuleElement {

    @Override
    public final void acceptMediaQueryElementVisitor(StyleSheetDsl dsl) {
      dsl.markDeclaration();
    }

    @Override
    public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
      dsl.markDeclaration();
    }

  }

  private static class ThisAngleTypeDouble extends ThisValue implements AngleType {

    static final AngleType INSTANCE = new ThisAngleTypeDouble();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markDoubleAngle();
    }

  }

  private static class ThisAngleTypeInt extends ThisValue implements AngleType {

    static final AngleType INSTANCE = new ThisAngleTypeInt();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markIntAngle();
    }

  }

  private static class ThisAnyDeclaration implements AnyDeclaration {

    static final AnyDeclaration INSTANCE = new ThisAnyDeclaration();

    @Override
    public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
      dsl.markDeclaration();
    }

    @Override
    public final void markMultiDeclarationElement(StyleSheetDsl dsl) {
      dsl.markMultiDeclarationElement();
    }

  }

  private static class ThisAnyFunction extends ThisValue implements AnyFunction {

    static final AnyFunction INSTANCE = new ThisAnyFunction();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markFunction();
    }

  }

  private static class ThisAttributeSelector implements RuleElement {

    static final ThisAttributeSelector INSTANCE = new ThisAttributeSelector();

    @Override
    public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
      dsl.markAttributeSelector();
    }

  }

  private static class ThisAttributeValueSelector implements RuleElement {

    static final ThisAttributeValueSelector INSTANCE = new ThisAttributeValueSelector();

    @Override
    public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
      dsl.markAttributeValueSelector();
    }

  }

  private static class ThisClassSelector implements RuleElement {

    static final ThisClassSelector INSTANCE = new ThisClassSelector();

    @Override
    public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
      dsl.markClassSelector();
    }

  }

  private enum ThisColorType implements ColorType {

    HEX,

    RGB_DOUBLE,

    RGB_DOUBLE_ALPHA,

    RGB_INT,

    RGB_INT_ALPHA,

    RGBA_DOUBLE,

    RGBA_INT;

    private final ColorKind kind;

    private ThisColorType() {
      this.kind = ColorKind.valueOf(name());
    }

    @Override
    public final void acceptValueCreator(Creator creator) {
      // noop
    }

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markColor(kind);
    }

  }

  private static class ThisDoubleType extends ThisValue implements DoubleType {

    static final DoubleType INSTANCE = new ThisDoubleType();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markDouble();
    }

  }

  private static class ThisIdSelector implements RuleElement {

    static final ThisIdSelector INSTANCE = new ThisIdSelector();

    @Override
    public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
      dsl.markIdSelector();
    }

  }

  private static class ThisIntType extends ThisValue implements IntType {

    static final IntType INSTANCE = new ThisIntType();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markInt();
    }

  }

  private static class ThisLengthTypeDouble extends ThisValue implements LengthType {

    static final LengthType INSTANCE = new ThisLengthTypeDouble();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markDoubleLength();
    }

  }

  private static class ThisLengthTypeInt extends ThisValue implements LengthType {

    static final LengthType INSTANCE = new ThisLengthTypeInt();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markIntLength();
    }

  }

  private static class ThisPercentageTypeDouble extends ThisValue implements PercentageType {

    static final PercentageType INSTANCE = new ThisPercentageTypeDouble();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markDoublePercentage();
    }

  }

  private static class ThisPercentageTypeInt extends ThisValue implements PercentageType {

    static final PercentageType INSTANCE = new ThisPercentageTypeInt();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markIntPercentage();
    }

  }

  private static class ThisStringType extends ThisValue implements StringType {

    static final StringType INSTANCE = new ThisStringType();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markString();
    }

  }

  private static class ThisUri extends ThisValue implements UriType {

    static final UriType INSTANCE = new ThisUri();

    @Override
    public final void acceptValueMarker(Marker marker) {
      marker.markUri();
    }

  }

  private abstract static class ThisValue implements Value {

    @Override
    public final void acceptValueCreator(Creator creator) {
      // noop
    }

  }

}