/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.parser.spec;

import static br.com.objectos.parser.spec.Stage01Assertion.assertThat;

import br.com.objectos.formal.testing.HasSomething;
import br.com.objectos.parser.testing.grammar.Grammar02;
import org.testng.annotations.Test;

public class Stage01Test extends AbstractParserSpecTest {

  @Test
  public void grammar2_RD() {
    NonTerminal hasSomething = NonTerminal.get(HasSomething.class);

    Stage01 it = ofRecursiveDescentParser(new Grammar02());
    assertThat(it)
        .hasProductionList(
            "Letter+ -> Letter Letter+",
            "Letter+ -> Letter",
            "<HasLetters> -> Letter+",
            "<ComplexSingle> -> <HasSomething>")
        .hasNonTerminalSet(hasSomething);
  }

  @Test
  public void grammar2_RD_toStage02() {
    Stage02 res = toStage02(ofRecursiveDescentParser(new Grammar02()));
    Stage02Assertion.assertThat(res)
        .hasOriginalList(
            "Letter+ -> Letter Letter+",
            "Letter+ -> Letter",
            "<HasLetters> -> Letter+",
            "<ComplexSingle> -> <HasSomething>")
        .hasPolymorphicList(
            "<HasSomething> -> <HasLetters>");
  }

}