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

import br.com.objectos.parser.testing.grammar.Grammar00;
import br.com.objectos.parser.testing.grammar.Grammar01;
import br.com.objectos.parser.testing.grammar.Grammar02;
import br.com.objectos.parser.testing.grammar.Grammar03;
import br.com.objectos.parser.testing.grammar.Grammar05;
import br.com.objectos.parser.testing.grammar.Grammar07;
import org.testng.annotations.Test;

public class SpecTest extends SpecDriver {

  @Test
  public void grammar0_RD() {
    it().givenRecursiveDescentParser()
        .whenGrammar(new Grammar00())
        .thenProductionCount(2)
        .thenToString(
            "<HasLetters> -> Letter",
            "<ComplexSingle> -> <HasLetters>");
  }

  @Test
  public void grammar1_RD() {
    it().givenRecursiveDescentParser()
        .whenGrammar(new Grammar01())
        .thenProductionCount(4)
        .thenToString(
            "Letter+ -> Letter Letter+",
            "Letter+ -> Letter",
            "<HasLetters> -> Letter+",
            "<ComplexSingle> -> <HasLetters>");
  }

  @Test
  public void grammar2_RD() {
    it().givenRecursiveDescentParser()
        .whenGrammar(new Grammar02())
        .thenProductionCount(5)
        .thenToString(
            "Letter+ -> Letter Letter+",
            "Letter+ -> Letter",
            "<HasLetters> -> Letter+",
            "<ComplexSingle> -> <HasSomething>",
            "<HasSomething> -> <HasLetters>");
  }

  @Test
  public void grammar3_RD() {
    it().givenRecursiveDescentParser()
        .whenGrammar(new Grammar03())
        .thenProductionCount(9)
        .thenToString(
            "Digit+ -> Digit Digit+",
            "Digit+ -> Digit",
            "<HasDigits> -> Digit+",
            "Letter+ -> Letter Letter+",
            "Letter+ -> Letter",
            "<HasLetters> -> Letter+",
            "<ComplexDuo> -> <HasSomething> <HasSomething>",
            "<HasSomething> -> <HasDigits>",
            "<HasSomething> -> <HasLetters>");
  }

  @Test
  public void grammar5_RD() {
    it().givenRecursiveDescentParser()
        .whenGrammar(new Grammar05())
        .thenProductionCount(3)
        .thenToString(
            "Letter? -> Letter",
            "Letter? -> ε",
            "<ComplexDuo> -> Digit Letter?");
  }

  @Test
  public void grammar7_RD() {
    it().givenRecursiveDescentParser()
        .whenGrammar(new Grammar07())
        .thenProductionCount(4)
        .thenToString(
            "Letter* -> Letter Letter*",
            "Letter* -> ε",
            "<HasLetters> -> Letter*",
            "<ComplexSingle> -> <HasLetters>");
  }

}