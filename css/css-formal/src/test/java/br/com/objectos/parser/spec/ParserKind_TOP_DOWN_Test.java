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

import br.com.objectos.formal.testing.Letter;
import objectos.util.GrowableList;
import org.testng.annotations.Test;

public class ParserKind_TOP_DOWN_Test {

  @Test
  public void oneOrMore() {
    Terminal letter = Terminal.get(Letter.class);
    Repetition it = Repetition.oneOrMore(letter, CollectionKind.LIST);
    GrowableList<Production> list = new GrowableList<>();

    it.forTopDown(list);

    assertEquals(list.size(), 2);
    assertEquals(list.get(0).toString(), "Letter+ -> Letter Letter+");
    assertEquals(list.get(1).toString(), "Letter+ -> Letter");
  }

}