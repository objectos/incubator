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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.css.parser.IsTerminal;

abstract class CssLexerDriverDef {

  final CssLexerDriverDef givenCss(String css) {
    givenCssImpl(css);
    return this;
  }

  final CssLexerDriverDef whenAnalyze() {
    whenAnalyzeImpl();
    return this;
  }

  final CssLexerDriverDef thenTokenList(IsTerminal... expected) {
    thenTokenListImpl(expected);
    return this;
  }

  CssLexerDriverDef it() {
    return this;
  }

  abstract void givenCssImpl(String css);

  abstract void whenAnalyzeImpl();

  abstract void thenTokenListImpl(IsTerminal[] expected);

}