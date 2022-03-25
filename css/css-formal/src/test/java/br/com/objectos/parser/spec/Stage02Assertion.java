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
import java.util.stream.Collectors;

class Stage02Assertion {

  private final Stage02 subject;

  private Stage02Assertion(Stage02 subject) {
    this.subject = subject;
  }

  public static Stage02Assertion assertThat(Stage02 subject) {
    return new Stage02Assertion(subject);
  }

  public final Stage02Assertion hasOriginalList(String... expected) {
    assertEquals(
        subject.originalList
            .stream()
            .map(Object::toString)
            .collect(Collectors.toList()),
        Arrays.asList(expected)
    );
    return this;
  }

  public final Stage02Assertion hasPolymorphicList(String... expected) {
    assertEquals(
        subject.polymorphicList
            .stream()
            .map(Object::toString)
            .collect(Collectors.toList()),
        Arrays.asList(expected)
    );
    return this;
  }

}