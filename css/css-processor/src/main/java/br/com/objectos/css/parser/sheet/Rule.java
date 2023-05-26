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
package br.com.objectos.css.parser.sheet;

import java.util.List;
import java.util.Objects;
import objectos.css.parser.IsNonTerminal;
import objectos.css.select.Selector;
import objectos.css.sheet.RuleElement;
import objectos.css.sheet.StyleEngine;
import objectos.util.GrowableList;

final class Rule implements IsNonTerminal {

  private final List<Declaration> declarations;
  private final Selector selector;

  Rule(Selector selector,
       List<Declaration> declarations) {
    this.selector = selector;
    this.declarations = declarations;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Rule)) {
      return false;
    }
    Rule that = (Rule) obj;
    return selector.equals(that.selector)
        && declarations.equals(that.declarations);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(selector, declarations);
  }

  final void acceptStyleEngine(StyleEngine engine) {
    GrowableList<RuleElement> elements;
    elements = new GrowableList<>();

    selector.acceptRuleElementList(elements);

    for (int i = 0; i < declarations.size(); i++) {
      Declaration declaration;
      declaration = declarations.get(i);

      declaration.create(engine);

      elements.add(declaration);
    }

    engine.addRule(elements.toUnmodifiableList());
  }

}