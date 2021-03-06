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
package br.com.objectos.css.boot.sheet;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.function.FunctionName;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.css.boot.spec.Source;
import br.com.objectos.css.boot.type.JavaType;
import br.com.objectos.css.boot.type.Primitive;
import br.com.objectos.css.boot.type.PrimitiveType;
import br.com.objectos.css.boot.type.ValueType;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class GeneratedStyleSheetStepTest extends AbstractCssBootTest {

  @Test(description = ""
      + "it should generate: "
      + "(1) method for each sig "
      + "(2) field for each type selector "
      + "(3) field for each pseudo class selector "
      + "(4) field for each pseudo element selector "
      + "(5) field for each keyword "
      + "(6) length methods for each unit "
      + "(7) protected interface for each property "
      + "(8) AnyDeclaration interface")
  public void execute() {
    UnmodifiableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
        GeneratedStyleSheetStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            elementName("a");
            elementName("div");

            pseudoClasses("hover");
            pseudoElements("after");

            KeywordName auto = keyword("auto");
            KeywordName none = keyword("none");

            namedColors("transparent", "ButtonText");

            angleUnits("deg");

            lengthUnits("em");

            property(
                "clear",
                formal("", Source.MANUAL_ENTRY),
                sig(t("ClearValue", none), "value")
            );

            propertyHash(
                "font-family",
                formal("", Source.MANUAL_ENTRY),
                sig(JavaType.STRING, "name"),
                sigHash()
            );

            property(
                "min-width",
                formal("", Source.MANUAL_ENTRY),
                sigAbstract(primitive(Primitive.LENGTH), "length")
            );

            property(
                "top",
                formal("", Source.MANUAL_ENTRY),
                sig(t("TopValue", auto), "value")
            );

            PrimitiveType angle;
            angle = primitive(Primitive.ANGLE);

            FunctionName rotate;
            rotate = function("rotate", sig(angle, "angle"));

            ValueType transformValue;
            transformValue = t("TransformValue", rotate);

            property(
                "transform",
                formal("", Source.MANUAL_ENTRY),
                sig(transformValue, "function")
            );
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("GeneratedStyleSheet"),
        "package br.com.objectos.css.sheet;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.css.Css;",
        "import br.com.objectos.css.function.RotateFunction;",
        "import br.com.objectos.css.function.StandardFunctionName;",
        "import br.com.objectos.css.keyword.AutoKeyword;",
        "import br.com.objectos.css.keyword.Keywords;",
        "import br.com.objectos.css.keyword.NoneKeyword;",
        "import br.com.objectos.css.property.StandardPropertyName;",
        "import br.com.objectos.css.select.PseudoClassSelector;",
        "import br.com.objectos.css.select.PseudoElementSelector;",
        "import br.com.objectos.css.select.TypeSelector;",
        "import br.com.objectos.css.type.AngleType;",
        "import br.com.objectos.css.type.AngleUnit;",
        "import br.com.objectos.css.type.ClearValue;",
        "import br.com.objectos.css.type.Color;",
        "import br.com.objectos.css.type.LengthType;",
        "import br.com.objectos.css.type.LengthUnit;",
        "import br.com.objectos.css.type.TopValue;",
        "import br.com.objectos.css.type.TransformValue;",
        "import br.com.objectos.css.type.Value;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "abstract class GeneratedStyleSheet {",
        "",
        "  protected static final TypeSelector a = Css.a;",
        "",
        "  protected static final TypeSelector div = Css.div;",
        "",
        "  protected static final PseudoClassSelector HOVER = Css.HOVER;",
        "",
        "  protected static final PseudoElementSelector AFTER = Css.AFTER;",
        "",
        "  protected static final Color ButtonText = Color.ButtonText;",
        "",
        "  protected static final Color transparent = Color.transparent;",
        "",
        "  protected static final AutoKeyword auto = Keywords.auto;",
        "",
        "  protected static final NoneKeyword none = Keywords.none;",
        "",
        "  GeneratedStyleSheet() {}",
        "",
        "  protected final AngleType deg(double value) {",
        "    return getAngle(AngleUnit.DEG, value);",
        "  }",
        "",
        "  protected final AngleType deg(int value) {",
        "    return getAngle(AngleUnit.DEG, value);",
        "  }",
        "",
        "  protected final LengthType em(double value) {",
        "    return getLength(LengthUnit.EM, value);",
        "  }",
        "",
        "  protected final LengthType em(int value) {",
        "    return getLength(LengthUnit.EM, value);",
        "  }",
        "",
        "  protected final ClearDeclaration clear(ClearValue value) {",
        "    return addDeclaration(StandardPropertyName.CLEAR, value);",
        "  }",
        "",
        "  protected final FontFamilySingleDeclaration fontFamily(String name) {",
        "    return addDeclaration(StandardPropertyName.FONT_FAMILY, name);",
        "  }",
        "",
        "  protected final FontFamilyMultiDeclaration fontFamily(FontFamilySingleDeclaration... declarations) {",
        "    return addDeclaration(StandardPropertyName.FONT_FAMILY, declarations);",
        "  }",
        "",
        "  protected abstract MinWidthDeclaration minWidth(LengthType length);",
        "",
        "  protected final TopDeclaration top(TopValue value) {",
        "    return addDeclaration(StandardPropertyName.TOP, value);",
        "  }",
        "",
        "  protected final RotateFunction rotate(AngleType angle) {",
        "    return addFunction(StandardFunctionName.ROTATE, angle);",
        "  }",
        "",
        "  protected final TransformDeclaration transform(TransformValue function) {",
        "    return addDeclaration(StandardPropertyName.TRANSFORM, function);",
        "  }",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, int value);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, double value);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, MultiDeclarationElement... elements);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, String value);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, Value v1);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3, Value v4);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5);",
        "",
        "  abstract AnyDeclaration addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5, Value v6);",
        "",
        "  abstract AnyFunction addFunction(StandardFunctionName name, Value v1);",
        "",
        "  abstract AngleType getAngle(AngleUnit unit, double value);",
        "",
        "  abstract AngleType getAngle(AngleUnit unit, int value);",
        "",
        "  abstract LengthType getLength(LengthUnit unit, double value);",
        "",
        "  abstract LengthType getLength(LengthUnit unit, int value);",
        "",
        "  protected interface ClearDeclaration extends Declaration {}",
        "",
        "  protected interface FontFamilyMultiDeclaration extends Declaration {}",
        "",
        "  protected interface FontFamilySingleDeclaration extends MultiDeclarationElement {}",
        "",
        "  protected interface MinWidthDeclaration extends Declaration {}",
        "",
        "  protected interface TopDeclaration extends Declaration {}",
        "",
        "  protected interface TransformDeclaration extends Declaration {}",
        "",
        "  interface AnyDeclaration extends ClearDeclaration, FontFamilyMultiDeclaration, FontFamilySingleDeclaration, MinWidthDeclaration, TopDeclaration, TransformDeclaration {}",
        "",
        "  interface AnyFunction extends RotateFunction {}",
        "",
        "}"
    );
  }

}
