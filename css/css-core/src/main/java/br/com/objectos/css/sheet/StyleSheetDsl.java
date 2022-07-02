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
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.select.PseudoClassSelector;
import br.com.objectos.css.select.PseudoElementSelector;
import br.com.objectos.css.select.TypeSelector;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.Creator;
import br.com.objectos.css.type.Marker;
import br.com.objectos.css.type.Value;
import objectos.lang.Check;
import objectos.util.UnmodifiableList;

public interface StyleSheetDsl extends Creator, Marker {

  static CompiledStyleSheet compile(StyleSheet sheet) {
    Check.notNull(sheet, "sheet == null");

    var dsl = new StyleSheetDslImpl();

    sheet.acceptStyleSheetDsl(dsl);

    return dsl.compile();
  }

  void addAtMedia(AtMediaElement... elements);

  void addClassSelector(ClassSelector selector);

  void addCombinator(Combinator combinator);

  void addDeclaration(StandardPropertyName name, double value);

  void addDeclaration(StandardPropertyName name, int value);

  void addDeclaration(StandardPropertyName name, MultiDeclarationElement... elements);

  void addDeclaration(StandardPropertyName name, String value);

  void addDeclaration(StandardPropertyName name, Value v1);

  void addDeclaration(StandardPropertyName name, Value v1, Value v2);

  void addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3);

  void addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3, Value v4);

  void addDeclaration(StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5);

  void addDeclaration(
      StandardPropertyName name, Value v1, Value v2, Value v3, Value v4, Value v5, Value v6);

  void addFunction(StandardFunctionName name, Value v1);

  void addIdSelector(IdSelector selector);

  void addMediaType(MediaType type);

  void addPseudoClassSelector(PseudoClassSelector selector);

  void addPseudoElementSelector(PseudoElementSelector selector);

  void addRule(RuleElement... elements);

  void addRule(UnmodifiableList<RuleElement> elements);

  void addTypeSelector(TypeSelector selector);

  void addUniversalSelector(UniversalSelector selector);

  void clearRulePrefix();

  void createAttributeSelector(String name);

  void createAttributeValueElement(AttributeValueOperator operator, String value);

  void createAttributeValueSelector(String name);

  void createClassSelector(String className);

  void createIdSelector(String id);

  void markAttributeSelector();

  void markAttributeValueElement();

  void markAttributeValueSelector();

  void markClassSelector();

  void markDeclaration();

  void markIdSelector();

  void markMultiDeclarationElement();

  void markRule();

  void setRulePrefix(RuleElement... elements);

}
