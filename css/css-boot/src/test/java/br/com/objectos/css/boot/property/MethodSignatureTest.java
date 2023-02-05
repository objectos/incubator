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
package br.com.objectos.css.boot.property;

import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.sheet.MethodSignature;
import br.com.objectos.css.boot.type.JavaType;
import br.com.objectos.css.boot.type.ValueType;
import org.testng.annotations.Test;

public class MethodSignatureTest extends AbstractCssBootTest {

  @Test
  public void fontFamily() {
    Property prop;
    prop = PropertyKind.HASH.get("font-family");

    MethodSignature sig;
    sig = MethodSignature.sigHash();

    testLines(
        sig.writeMethodCode(prop),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.FontFamilyMultiDeclaration fontFamily(br.com.objectos.css.sheet.GeneratedStyleSheet.FontFamilySingleDeclaration... declarations) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.FONT_FAMILY, declarations);",
        "}"
    );
  }

  @Test
  public void margin() {
    Property margin = PropertyKind.STANDARD.get("margin");

    ValueType marginWidth = ValueType.of("MarginWidth");

    MethodSignature sig0 = MethodSignature.of(
        marginWidth, "all"
    );
    MethodSignature sig1 = MethodSignature.of(
        marginWidth, "vertical",
        marginWidth, "horizontal"
    );
    MethodSignature sig2 = MethodSignature.of(
        marginWidth, "top",
        marginWidth, "horizontal",
        marginWidth, "bottom"
    );
    MethodSignature sig3 = MethodSignature.of(
        marginWidth, "top",
        marginWidth, "right",
        marginWidth, "bottom",
        marginWidth, "left"
    );

    testLines(
        sig0.writeMethodCode(margin),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.MarginDeclaration margin(br.com.objectos.css.type.MarginWidth all) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.MARGIN, all);",
        "}"
    );
    testLines(
        sig1.writeMethodCode(margin),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.MarginDeclaration margin(br.com.objectos.css.type.MarginWidth vertical, br.com.objectos.css.type.MarginWidth horizontal) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.MARGIN, vertical, horizontal);",
        "}"
    );
    testLines(
        sig2.writeMethodCode(margin),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.MarginDeclaration margin(br.com.objectos.css.type.MarginWidth top, br.com.objectos.css.type.MarginWidth horizontal, br.com.objectos.css.type.MarginWidth bottom) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.MARGIN, top, horizontal, bottom);",
        "}"
    );
    testLines(
        sig3.writeMethodCode(margin),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.MarginDeclaration margin(br.com.objectos.css.type.MarginWidth top, br.com.objectos.css.type.MarginWidth right, br.com.objectos.css.type.MarginWidth bottom, br.com.objectos.css.type.MarginWidth left) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.MARGIN, top, right, bottom, left);",
        "}"
    );
  }

  @Test
  public void minWidth() {
    Property property;
    property = PropertyKind.STANDARD.get("min-width");

    MethodSignature sigZero;
    sigZero = MethodSignature.sigZero();

    testLines(
        sigZero.writeMethodCode(property),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.MinWidthDeclaration minWidth(br.com.objectos.css.type.Zero zero) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.MIN_WIDTH, 0);",
        "}"
    );
  }

  @Test
  public void zIndex() {
    Property prop = PropertyKind.STANDARD.get("z-index");

    MethodSignature sig = MethodSignature.of(
        JavaType.INT, "value"
    );

    testLines(
        sig.writeMethodCode(prop),
        "protected final br.com.objectos.css.sheet.GeneratedStyleSheet.ZIndexDeclaration zIndex(int value) {",
        "  return addDeclaration(br.com.objectos.css.property.StandardPropertyName.Z_INDEX, value);",
        "}"
    );
  }

}
