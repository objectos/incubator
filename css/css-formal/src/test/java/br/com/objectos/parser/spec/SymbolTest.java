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

import static org.testng.Assert.assertEquals;

import br.com.objectos.formal.testing.HasDigits;
import br.com.objectos.formal.testing.HasLetters;
import br.com.objectos.formal.testing.HasSomething;
import br.com.objectos.formal.testing.Letter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SymbolTest {

  @DataProvider
  public Object[][] acceptStage02Provider() {
    return new Object[][] {
        { NonTerminal.get(HasLetters.class),
            NonTerminal.get(HasSomething.class),
            "<HasSomething> -> <HasLetters>" },
        { NonTerminal.get(HasDigits.class),
            NonTerminal.get(HasSomething.class),
            "<HasSomething> -> <HasDigits>" },
        { NonTerminal.get(HasDigits.class).oneOrMore(CollectionKind.LIST),
            NonTerminal.get(HasSomething.class),
            "<HasSomething> -> <HasDigits>" },
        { Terminal.get(HasDigits.class),
            NonTerminal.get(HasSomething.class),
            "" },
    };
  }

  @DataProvider
  public Object[][] toStringProvider() {
    return new Object[][] {
        { NonTerminal.get(HasLetters.class), "<HasLetters>" },
        { Terminal.get(Letter.class), "Letter" },
        { Terminal.get(Letter.C), "Letter'C'" },
        { Symbol.eof(), "<eof>" },
        { Repetition.oneOrMore(Terminal.get(Letter.class), CollectionKind.LIST), "Letter+" },
        { Optional.get(Terminal.get(Letter.class)), "Letter?" }
    };
  }

  @Test(dataProvider = "acceptStage02Provider")
  public void acceptStage02(Symbol nonTerminal, NonTerminal superType, String expected) {
    Stage02Builder stage02 = new ThisStage02();
    nonTerminal.acceptStage02Builder(stage02, superType);
    assertEquals(stage02.toString(), expected);
  }

  @Test(dataProvider = "toStringProvider")
  public void toString_test(Symbol symbol, String expected) {
    assertEquals(symbol.toString(), expected);
  }

  private class ThisStage02 implements Stage02Builder {

    private final List<Production> list = new ArrayList<>();

    @Override
    public void addProduction(Production newProduction) {
      list.add(newProduction);
    }

    @Override
    public final String toString() {
      return list.stream()
          .map(Object::toString)
          .collect(Collectors.joining(","));
    }

    @Override
    public List<Production> originalList() {
      throw new UnsupportedOperationException();
    }

    @Override
    public List<Production> polymorphicList() {
      throw new UnsupportedOperationException();
    }

  }

}