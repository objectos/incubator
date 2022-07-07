/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TableOfContentsTest {

  private TableOfContents toc;

  @BeforeClass
  public void _beforeClass() {
    toc = new TableOfContents(null);
  }

  @Test
  public void v0002() {
    toc.clear();

    putAll(
      "index",

      "intro/index",
      "intro/overview",
      "intro/installation",

      "objectos-lang/index",
      "objectos-lang/Check",
      "objectos-lang/Equals",
      "objectos-lang/HashCode",
      "objectos-lang/ToString",
      "objectos-lang/note-sink-api/index",
      "objectos-lang/note-sink-api/creating-notes",
      "objectos-lang/note-sink-api/the-note-sink-interface",
      "objectos-lang/note-sink-api/the-no-op-note-sink",

      "relnotes/index",
      "relnotes/0.2.0",
      "relnotes/0.1.0"
    );

    var items = toc.toUnmodifiableList();

    assertEquals(items.size(), 4, items.toString());

    assertEquals(items.get(0).key, "index");
    assertEquals(items.get(1).key, "intro/index");
    assertEquals(items.get(2).key, "objectos-lang/index");
    assertEquals(items.get(3).key, "relnotes/index");
  }

  private void putAll(String... keys) {
    for (var key : keys) {
      toc.put(key);
    }
  }

}