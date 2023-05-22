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

import br.com.objectos.css.sheet.StyleEngine;
import br.com.objectos.css.sheet.StyleSheet;
import java.util.List;

class ParsedStyleSheet implements StyleSheet {

  private final List<Rule> rules;

  ParsedStyleSheet(List<Rule> rules) {
    this.rules = rules;
  }

  @Override
  public final void eval(StyleEngine engine) {
    for (int i = 0; i < rules.size(); i++) {
      Rule rule;
      rule = rules.get(i);

      rule.acceptStyleEngine(engine);
    }
  }

  @Override
  public final String printMinified() {
    throw new UnsupportedOperationException("Implement me");
  }

}