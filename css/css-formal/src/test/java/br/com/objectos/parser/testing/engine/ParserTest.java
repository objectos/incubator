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
package br.com.objectos.parser.testing.engine;

import static br.com.objectos.formal.testing.Digit.D0;
import static br.com.objectos.formal.testing.Digit.D1;
import static br.com.objectos.formal.testing.Letter.A;
import static br.com.objectos.formal.testing.Letter.B;
import static br.com.objectos.formal.testing.Letter.C;
import static br.com.objectos.formal.testing.Letter.D;

import br.com.objectos.formal.testing.ComplexDuo;
import br.com.objectos.formal.testing.ComplexSingle;
import br.com.objectos.formal.testing.ComplexTrio;
import br.com.objectos.formal.testing.Digit;
import br.com.objectos.formal.testing.HasDigits;
import br.com.objectos.formal.testing.HasLetters;
import br.com.objectos.formal.testing.driven.Description;
import br.com.objectos.formal.testing.driven.Keyword;
import br.com.objectos.formal.testing.driven.Scenario;
import br.com.objectos.formal.testing.driven.Step;
import br.com.objectos.formal.testing.driven.StepText;
import br.com.objectos.formal.testing.driven.Symbol;
import br.com.objectos.parser.testing.grammar.And;
import br.com.objectos.parser.testing.grammar.Formal0012ParserGrammar;
import br.com.objectos.parser.testing.grammar.Formal0013ParserGrammar;
import br.com.objectos.parser.testing.grammar.Formal0019ParserGrammar;
import br.com.objectos.parser.testing.grammar.Formal0020ParserGrammar;
import br.com.objectos.parser.testing.grammar.Goal;
import br.com.objectos.parser.testing.grammar.Grammar00;
import br.com.objectos.parser.testing.grammar.Grammar01;
import br.com.objectos.parser.testing.grammar.Grammar02;
import br.com.objectos.parser.testing.grammar.Grammar03;
import br.com.objectos.parser.testing.grammar.Grammar04;
import br.com.objectos.parser.testing.grammar.Grammar05;
import br.com.objectos.parser.testing.grammar.Grammar06;
import br.com.objectos.parser.testing.grammar.HasDigitAndLetter;
import br.com.objectos.parser.testing.grammar.Optional;
import br.com.objectos.parser.testing.grammar.Or;
import br.com.objectos.parser.testing.grammar.TrackbackKeyword;
import br.com.objectos.parser.testing.grammar.TrackbackSymbol;
import br.com.objectos.parser.testing.grammar.TrackbackTestingParserGrammar;
import br.com.objectos.parser.testing.grammar.TrackbackValueType;
import java.util.Arrays;
import org.testng.annotations.Test;

public abstract class ParserTest extends ParserDriver {

  @Test
  public void grammar0_HasLetters() {
    it().givenParserFromGrammar(new Grammar00())
        .whenParse(HasLetters.class, A)
        .thenResult(HasLetters.hasOne(A));
  }

  @Test
  public void grammar0_ComplexSingle() {
    it().givenParserFromGrammar(new Grammar00())
        .whenParse(ComplexSingle.class, A)
        .thenResult(HasLetters.hasOne(A).asComplexSingle());
  }

  @Test
  public void grammar1_HasLetters_A() {
    it().givenParserFromGrammar(new Grammar01())
        .whenParse(HasLetters.class, A)
        .thenResult(HasLetters.of(A));
  }

  @Test
  public void grammar1_HasLetters_AB() {
    it().givenParserFromGrammar(new Grammar01())
        .whenParse(HasLetters.class, A, B)
        .thenResult(HasLetters.of(A, B));
  }

  @Test
  public void grammar1_HasLetters_ABC() {
    it().givenParserFromGrammar(new Grammar01())
        .whenParse(HasLetters.class, A, B, C)
        .thenResult(HasLetters.of(A, B, C));
  }

  @Test
  public void grammar1_ComplexSingle() {
    it().givenParserFromGrammar(new Grammar01())
        .whenParse(ComplexSingle.class, A, B, C)
        .thenResult(HasLetters.of(A, B, C).asComplexSingle());
  }

  @Test
  public void grammar2_ComplexSingle() {
    it().givenParserFromGrammar(new Grammar02())
        .whenParse(ComplexSingle.class, A, B, C)
        .thenResult(HasLetters.of(A, B, C).asComplexSingle());
  }

  @Test
  public void grammar3_ComplexSingle() {
    it().givenParserFromGrammar(new Grammar03())
        .whenParse(ComplexDuo.class, A, B, D0, D1)
        .thenResult(new ComplexDuo(HasLetters.of(A, B), HasDigits.of(D0, D1)))
        .whenParse(ComplexDuo.class, A, D0)
        .thenResult(new ComplexDuo(HasLetters.of(A), HasDigits.of(D0)))
        .whenParse(ComplexDuo.class, D0, A)
        .thenResult(new ComplexDuo(HasDigits.of(D0), HasLetters.of(A)));
  }

  @Test
  public void grammar4_ComplexTrio() {
    it().givenParserFromGrammar(new Grammar04())
        .whenParse(ComplexTrio.class, C, A, D)
        .thenResult(new ComplexTrio(C, ComplexDuo.withOne(A), D));
  }

  @Test
  public void grammar5_ComplexDuo_present() {
    it().givenParserFromGrammar(new Grammar05())
        .whenParse(ComplexDuo.class, D0, A)
        .thenResult(ComplexDuo.withTwo(D0, A));
  }

  @Test
  public void grammar5_ComplexDuo_absent() {
    it().givenParserFromGrammar(new Grammar05())
        .whenParse(ComplexDuo.class, D0)
        .thenResult(ComplexDuo.withOne(D0));
  }

  @Test
  public void grammar6_ComplexSingle_digit() {
    it().givenParserFromGrammar(new Grammar06())
        .whenParse(ComplexSingle.class, D0)
        .thenResult(ComplexSingle.of(HasDigits.hasOne(Digit.D0)));
  }

  @Test
  public void grammar6_ComplexSingle_digitAndLetter() {
    it().givenParserFromGrammar(new Grammar06())
        .whenParse(ComplexSingle.class, D0, A)
        .thenResult(ComplexSingle.of(new HasDigitAndLetter(D0, A)));
  }

  @Test
  public void formal0012_01() {
    it().givenParserFromGrammar(new Formal0012ParserGrammar())
        .whenParse(Step.class, Keyword.GIVEN, new StepText(" a"))
        .thenResult(new Step(Keyword.GIVEN, Arrays.asList(new StepText(" a"))));
  }

  @Test
  public void formal0012_02() {
    it().givenParserFromGrammar(new Formal0012ParserGrammar())
        .whenParse(Step.class, Keyword.WHEN, new StepText(" x y z"))
        .thenResult(new Step(Keyword.WHEN, Arrays.asList(new StepText(" x y z"))));
  }

  @Test
  public void formal0013_aScenarioWithASingleStep() {
    it().givenParserFromGrammar(new Formal0013ParserGrammar())
        .whenParse(Scenario.class,
            Keyword.GIVEN, new StepText("a"))
        .thenResult(
            Scenario.of(
                Keyword.GIVEN.step(new StepText("a"))));
  }

  @Test
  public void formal0013_aScenarioWithManySteps() {
    it().givenParserFromGrammar(new Formal0013ParserGrammar())
        .whenParse(Scenario.class,
            Keyword.GIVEN, new StepText("a"),
            Keyword.WHEN, new StepText("x"),
            Keyword.THEN, new StepText("1"))
        .thenResult(
            Scenario.of(
                Keyword.GIVEN.step(new StepText("a")),
                Keyword.WHEN.step(new StepText("x")),
                Keyword.THEN.step(new StepText("1"))));
  }

  @Test
  public void formal0013_aScenarioWithManyStepsAndVaryingTokenLength() {
    it().givenParserFromGrammar(new Formal0013ParserGrammar())
        .whenParse(Scenario.class,
            Keyword.GIVEN, new StepText("a"),
            Keyword.WHEN, new StepText("x"), new StepText("y"), new StepText("z"),
            Keyword.THEN, new StepText("123"), new StepText("456"))
        .thenResult(
            Scenario.of(
                Keyword.GIVEN.step(new StepText("a")),
                Keyword.WHEN.step(new StepText("x"), new StepText("y"), new StepText("z")),
                Keyword.THEN.step(new StepText("123"), new StepText("456"))));
  }

  @Test
  public void formal0019_singleStepText() {
    it().givenParserFromGrammar(new Formal0019ParserGrammar())
        .whenParse(Step.class,
            Keyword.WHEN, new StepText("a"))
        .thenResult(
            Keyword.WHEN.step(new StepText("a")));
  }

  @Test
  public void formal0019_manyStepText() {
    it().givenParserFromGrammar(new Formal0019ParserGrammar())
        .whenParse(Step.class,
            Keyword.THEN, new StepText("hey"), new StepText("ho"))
        .thenResult(
            Keyword.THEN.step(new StepText("hey"), new StepText("ho")));
  }

  @Test
  public void formal0020_aScenarioWithASingleStep() {
    it().givenParserFromGrammar(new Formal0020ParserGrammar())
        .whenParse(Scenario.class,
            Symbol.H2, new StepText("first"), new StepText("scenario"),
            Keyword.GIVEN, new StepText("a"))
        .thenResult(new Scenario(
            Symbol.H2,
            Description.of(new StepText("first"), new StepText("scenario")),
            Arrays.asList(Keyword.GIVEN.step(new StepText("a")))));
  }

  @Test
  public void trackbackTesting() {
    TrackbackValueType length = TrackbackValueType.get("length");
    TrackbackKeyword inherit = TrackbackKeyword.get("inherit");

    Optional optional = new Optional(length);
    And and = new And(length, optional);
    Or or = new Or(and, inherit);
    Goal goal = new Goal(or);

    it().givenParserFromGrammar(TrackbackTestingParserGrammar.get())
        .whenParse(Goal.class, length, length, TrackbackSymbol.OPTIONAL, TrackbackSymbol.OR, inherit)
        .thenResult(goal);
  }

}
