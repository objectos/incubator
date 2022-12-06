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
package br.com.objectos.css.boot.spec;

import br.com.objectos.css.boot.function.FunctionName;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.property.Property;
import br.com.objectos.css.boot.sheet.MethodSignature;
import br.com.objectos.css.boot.type.ColorName;
import br.com.objectos.css.boot.type.Primitive;
import br.com.objectos.css.boot.type.PrimitiveType;
import br.com.objectos.css.boot.type.Value;
import br.com.objectos.css.boot.type.ValueType;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import objectos.lang.Check;
import objectos.util.GrowableSet;

public class CssSpecDsl {

  private final Set<ColorName> colors = new TreeSet<>();

  private final GrowableSet<String> elementNames = new GrowableSet<>();

  private final Map<String, FunctionName> functions = new TreeMap<>();

  private final Map<String, KeywordName> keywords = new TreeMap<>();

  private final Set<String> lengthUnits = new TreeSet<>();

  private final Map<Primitive, PrimitiveType> primitives
      = new EnumMap<Primitive, PrimitiveType>(Primitive.class);

  private final GrowableSet<String> properties = new GrowableSet<>();

  private final Step step;

  private final Map<String, ValueType> valueTypes = new LinkedHashMap<>();

  public CssSpecDsl(Step step) {
    this.step = step;
  }

  public final void addAngleUnit(String unit) {
    step.addAngleUnit(unit);
  }

  public void addElementName(String elementName) {
    elementNames.add(elementName);

    step.addElementName(elementName);
  }

  public final FunctionName addFunction(String name, MethodSignature[] signatures) {
    Check.argument(!functions.containsKey(name), "function already defined: ", name);

    FunctionName function;
    function = FunctionName.of(name);

    functions.put(name, function);

    Arrays.sort(signatures);

    for (MethodSignature signature : signatures) {
      step.addMethodSignature(function, signature);
    }

    return function;
  }

  public final void addLengthUnit(String unit) {
    step.addLengthUnit(unit);

    lengthUnits.add(unit);
  }

  public final void addNamedColor(String name) {
    ColorName colorName;
    colorName = ColorName.of(name);

    colors.add(colorName);
  }

  public final void addProperty(Property property, MethodSignature[] signatures) {
    String name;
    name = property.getName();

    Check.argument(properties.add(name), "property already defined: ", name);

    step.addProperty(property);

    Arrays.sort(signatures);

    for (MethodSignature signature : signatures) {
      step.addMethodSignature(property, signature);
    }
  }

  public final void addPseudoClass(String name) {
    step.addPseudoClass(name);
  }

  public final void addPseudoElement(String name) {
    step.addPseudoElement(name);
  }

  public final void execute() {
    for (ColorName color : colors) {
      step.addColorName(color);
    }

    for (FunctionName function : functions.values()) {
      step.addFunction(function);
    }

    for (KeywordName keyword : keywords.values()) {
      step.addKeyword(keyword);
    }

    for (PrimitiveType type : primitives.values()) {
      step.addPrimitiveType(type);
    }

    for (ValueType type : valueTypes.values()) {
      step.addValueType(type);
    }

    step.execute();
  }

  public final KeywordName getKeyword(String value) {
    Check.notNull(value, "value == null");

    KeywordName keyword = keywords.get(value);

    if (keyword == null) {
      if (elementNames.contains(value)) {
        keyword = KeywordName.withKwSuffix(value);
      } else {
        keyword = KeywordName.of(value);
      }
      keywords.put(value, keyword);
    }

    return keyword;
  }

  public final PrimitiveType getPrimitive(Primitive kind) {
    Check.notNull(kind, "kind == null");

    PrimitiveType type = primitives.get(kind);

    if (type == null) {
      type = PrimitiveType.of(kind);
      primitives.put(kind, type);
    }

    return type;
  }

  public final ValueType getValueType(String name, Value[] values) {
    Check.notNull(name, "name == null");
    Check.notNull(values, "values == null");

    ValueType type = valueTypes.get(name);

    if (type == null) {
      type = ValueType.of(name);
      valueTypes.put(name, type);

      for (Value value : values) {
        value.acceptValueType(type);
      }
    }

    return type;
  }

  final Set<ColorName> colors() {
    return colors;
  }

  final Iterable<String> lengthUnits() {
    return lengthUnits;
  }

  final ValueType valueType(String name) {
    return valueTypes.get(name);
  }

}
