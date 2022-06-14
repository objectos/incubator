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
package br.com.objectos.css.specgen;

import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.MethodCode.Builder;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.Literal;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.css.specgen.spec.KeywordSet;
import br.com.objectos.css.specgen.spec.Property;
import br.com.objectos.css.specgen.spec.Step;
import br.com.objectos.css.specgen.spec.StepAdapter;
import br.com.objectos.css.specgen.spec.ValueType;
import java.util.Iterator;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class PropertyModuleStep extends Step {

  PropertyModuleStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addProperty(Property property) {
    addProperty(property, ImmutableList.of());
  }

  @Override
  public final void addProperty(Property property, ImmutableList<Property> group) {
    MethodCode.Builder def = MethodCode.builder();
    def.addAnnotation(Override.class);
    def.addModifier(Modifiers.FINAL);
    def.name("propertyDefinition");

    doKeywords(property, group, def);
    doMainProperty(property, def);
    doGroupProperties(group, def);

    writeJavaFile(
        Types.BOOT,
        _class(
            SpecgenBoot.GENERATED,
            _final(), className(property), _extends(Types.AbstractPropertyModule),

            def.build()
        )
    );
  }

  private Identifier className(Property property) {
    return id(
        JavaNames.toValidClassName(
            property.name() + "PropertyModule"
        )
    );
  }

  private void doGroupProperties(ImmutableList<Property> group, Builder def) {
    if (group.isEmpty()) {
      return;
    }

    MutableList<Literal> literals;
    literals = new MutableList<>();

    for (Property property : group) {
      String name = property.name();

      literals.add(l(name));
    }

    MethodInvocation namesInvocation = invoke("names", literals);

    Property first = group.get(0);

    doProperty(first, def, namesInvocation);
  }

  private void doKeywords(
      Property property, ImmutableList<Property> group, MethodCode.Builder def) {
    KeywordSet.Builder kwSetBuilder = KeywordSet.builder();
    property.acceptKeywordSetBuilder(kwSetBuilder);

    for (Property groupProperty : group) {
      groupProperty.acceptKeywordSetBuilder(kwSetBuilder);
    }

    KeywordSet keywordSet = kwSetBuilder.build();
    keywordSet.acceptPropertyDefinitionMethod(def);
  }

  private void doMainProperty(Property property, MethodCode.Builder def) {
    doProperty(property, def, l(property.name()));
  }

  private void doProperty(Property property, MethodCode.Builder def, ArgumentsElement name) {
    def.addStatement(
        invoke(
            "property", nl(),
            name, nl(),
            nl(),
            formalExpression(property), nl(),
            nl(),
            Ids.globalSig, nl()
        )
    );
  }

  private ArgumentsElement formalExpression(Property property) {
    MutableList<ArgumentsElement> args = new MutableList<>();
    args.add(nl());
    args.add(Types.Source.id(Ids.MDN));
    args.add(nl());
    args.add(l(property.formal()));
    args.add(nl());

    Iterator<ValueType> iterator = property.valueTypes().iterator();
    if (iterator.hasNext()) {
      args.add(nl());
      args.add(iterator.next().formalLiteral());
      args.add(nl());
      while (iterator.hasNext()) {
        args.add(iterator.next().formalLiteral());
        args.add(nl());
      }
    }

    return invoke("formal", args);
  }

}
