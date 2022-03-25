/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class Stage01Assertion {

  private final Stage01 subject;

  private Stage01Assertion(Stage01 subject) {
    this.subject = subject;
  }

  public static Stage01Assertion assertThat(Stage01 subject) {
    return new Stage01Assertion(subject);
  }

  public final Stage01Assertion hasNonTerminalSet(NonTerminal... expected) {
    List<NonTerminal> list;
    list = Arrays.asList(expected);

    assertEquals(subject.nonTerminalSet, new HashSet<>(list));

    return this;
  }

  public final Stage01Assertion hasProductionList(String... expected) {
    assertEquals(
        subject.productionList
            .stream()
            .map(Object::toString)
            .collect(Collectors.toList()),
        Arrays.asList(expected)
    );
    return this;
  }

}