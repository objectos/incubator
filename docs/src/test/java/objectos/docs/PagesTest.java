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

import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PagesTest {

  private Pages pages;

  @BeforeClass
  public void _beforeClass() {
    pages = new Pages();
  }

  @Test
  public void v0002() {
    pages.reset("next");

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

    var key = "index";

    assertEquals(pages.prevKey(key), null);
    assertEquals(pages.href(key), "/next/index.html");
    assertEquals(pages.nextKey(key), "intro/index");
    assertEquals(pages.trail(key), List.of("index"));

    key = "intro/index";

    assertEquals(pages.prevKey(key), "index");
    assertEquals(pages.href(key), "/next/intro/index.html");
    assertEquals(pages.nextKey(key), "intro/overview");
    assertEquals(pages.trail(key), List.of("index", "intro/index"));

    key = "intro/installation";

    assertEquals(pages.prevKey(key), "intro/overview");
    assertEquals(pages.href(key), "/next/intro/installation.html");
    assertEquals(pages.nextKey(key), "objectos-lang/index");
    assertEquals(pages.trail(key), List.of("index", "intro/index", "intro/installation"));

    key = "objectos-lang/Check";

    assertEquals(pages.prevKey(key), "objectos-lang/index");
    assertEquals(pages.href(key), "/next/objectos-lang/Check.html");
    assertEquals(pages.nextKey(key), "objectos-lang/Equals");
    assertEquals(pages.trail(key), List.of("index", "objectos-lang/index", "objectos-lang/Check"));
  }

  private void putAll(String... keys) {
    for (var key : keys) {
      pages.put(key);
    }
  }

}