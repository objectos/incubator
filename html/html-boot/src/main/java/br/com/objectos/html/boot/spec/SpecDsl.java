/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot.spec;

import java.util.Map;
import java.util.TreeMap;

public class SpecDsl {

  private final Map<String, AttributeSpec> attributeMap = new TreeMap<>();
  private final Map<String, CategorySpec> categoryMap = new TreeMap<>();
  private final Map<String, ElementSpec> elementMap = new TreeMap<>();

  private final TemplateSpec template;
  private final TextSpec text;

  public SpecDsl() {
    template = new TemplateSpec(this);
    text = new TextSpec(this);
  }

  public final AttributeSpec attributeSpec(String name) {
    return attributeMap.get(name);
  }

  public final CategorySpec category(String name) {
    return categoryMap.computeIfAbsent(name, this::category0);
  }

  public final ElementSpec element(String name) {
    return elementMap.computeIfAbsent(name, this::element0);
  }

  public final ElementAttributeSpec elementAttribute(ElementSpec parent, String name) {
    AttributeSpec attr = attributeMap.computeIfAbsent(name, this::elementAttribute0);
    return attr.toElementAttributeSpec(parent);
  }

  public final void execute(Step step) {
    step.templateSpec(template);
    step.textSpec(text);

    for (ElementSpec elementSpec : elementMap.values()) {
      step.elementSpec(elementSpec);
    }

    for (AttributeSpec attributeSpec : attributeMap.values()) {
      step.attributeSpec(attributeSpec);
    }

    step.execute();
  }

  public final AttributeSpec globalAttribute(String name) {
    if (attributeMap.containsKey(name)) {
      throw new IllegalArgumentException(name + " global attribute already defined!");
    }
    AttributeSpec attr = AttributeSpec.global(name);
    attributeMap.put(name, attr);
    return attr;
  }

  public final void prepare() {
    for (ElementSpec element : elementMap.values()) {
      element.prepare();
    }
  }

  public final RootElementSpec rootElement() {
    return new RootElementSpec(this);
  }

  public final TemplateSpec template() {
    return template;
  }

  public final TextSpec text() {
    return text;
  }

  public final SpecDsl with(Spec spec) {
    spec.acceptSpecDsl(this);
    return this;
  }

  private CategorySpec category0(String name) {
    return new CategorySpec(name);
  }

  private ElementSpec element0(String name) {
    return new ElementSpec(this, name);
  }

  private ElementAttributeSpec elementAttribute0(String name) {
    return new ElementAttributeSpec(name);
  }

}