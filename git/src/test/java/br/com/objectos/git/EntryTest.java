/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.git;

import static org.testng.Assert.assertEquals;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import org.testng.annotations.Test;

public class EntryTest {

  @Test
  public void compareTo() {
    MutableList<MutableTreeEntry> entries;
    entries = MutableList.create();

    entries.add(TestingGit.tree("foo", "74a9c52015b363408e231ddea72ca57746615e77"));
    entries.add(TestingGit.tree("foo-bar", "74a9c52015b363408e231ddea72ca57746615e77"));

    entries.sort(MutableTreeEntry.ORDER);

    assertEquals(
        toNames(entries),

        ImmutableList.of(
            "foo-bar",
            "foo"
        )
    );
  }

  @Test
  public void print() throws InvalidObjectIdFormatException {
    assertEquals(
        new Entry(
            EntryMode.TREE,

            "objectos",

            ObjectId.parse("74a9c52015b363408e231ddea72ca57746615e77")
        ).print(),

        "040000 tree 74a9c52015b363408e231ddea72ca57746615e77    objectos"
    );

    assertEquals(
        new Entry(
            EntryMode.REGULAR_FILE,

            "pom.xml",

            ObjectId.parse("d72e03e0e09d1151dfd769e90bfe8f83f9fc5dfe")
        ).print(),

        "100644 blob d72e03e0e09d1151dfd769e90bfe8f83f9fc5dfe    pom.xml"
    );
  }

  private ImmutableList<String> toNames(MutableList<MutableTreeEntry> entries) {
    MutableList<String> result;
    result = MutableList.create();

    for (int i = 0, size = entries.size(); i < size; i++) {
      MutableTreeEntry e;
      e = entries.get(i);

      result.add(e.getName());
    }

    return result.toImmutableList();
  }

}