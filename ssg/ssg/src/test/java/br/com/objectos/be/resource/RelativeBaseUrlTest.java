/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.resource;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import java.io.IOException;
import org.testng.annotations.Test;

public class RelativeBaseUrlTest {

  RelativeBaseUrl root = RelativeBaseUrl.root();

  RelativeBaseUrl css = root.getChild("css");
  RelativeBaseUrl docs = root.getChild("docs");
  RelativeBaseUrl latest = docs.getChild("latest");
  RelativeBaseUrl v0 = docs.getChild("v0.x");

  @Test
  public void getChild() {
    RelativeBaseUrl empty;
    empty = root.getChild("");

    assertEquals(empty, root);
    test(empty, "");

    RelativeBaseUrl slash;
    slash = root.getChild("/");

    assertEquals(slash, root);
    assertEquals(slash, empty);
    test(slash, "");

    RelativeBaseUrl docsChild;
    docsChild = root.getChild("docs");

    assertEquals(docsChild, docs);
    test(docsChild, "docs");

    RelativeBaseUrl slashDocs;
    slashDocs = root.getChild("/docs");

    assertEquals(slashDocs, docs);
    assertEquals(slashDocs, docsChild);
    test(slashDocs, "docs");

    RelativeBaseUrl latestChild = root.getChild("docs/latest");

    assertEquals(latestChild, latest);
    test(latestChild, "docs/latest");

    RelativeBaseUrl slashLatest = root.getChild("/docs/latest");

    assertEquals(slashLatest, latest);
    assertEquals(slashLatest, latestChild);
    test(slashLatest, "docs/latest");
  }

  @Test
  public void getDirectory() throws IOException {
    Directory dir = TmpDir.create();

    dir = dir.createDirectory("root");

    try {
      assertTrue(root.getDirectory(dir).getPath().endsWith("/root"));
      assertTrue(css.getDirectory(dir).getPath().endsWith("/root/css"));
      assertTrue(docs.getDirectory(dir).getPath().endsWith("/root/docs"));
      assertTrue(latest.getDirectory(dir).getPath().endsWith("/root/docs/latest"));
      assertTrue(v0.getDirectory(dir).getPath().endsWith("/root/docs/v0.x"));
    } finally {
      dir.deleteContents();
      dir.delete();
    }
  }

  @Test
  public void resolve() {
    test(resolve(root, root), "x");
    test(resolve(root, css), "../x");
    test(resolve(root, docs), "../x");
    test(resolve(root, latest), "../../x");
    test(resolve(root, v0), "../../x");

    test(resolve(docs, root), "docs/x");
    test(resolve(docs, css), "../docs/x");
    test(resolve(docs, docs), "x");
    test(resolve(docs, latest), "../x");
    test(resolve(docs, v0), "../x");

    test(resolve(latest, root), "docs/latest/x");
    test(resolve(latest, css), "../docs/latest/x");
    test(resolve(latest, docs), "latest/x");
    test(resolve(latest, latest), "x");
    test(resolve(latest, v0), "../latest/x");
  }

  private String resolve(RelativeBaseUrl target, RelativeBaseUrl from) {
    return target.resolve(from).getPath("x");
  }

  private void test(Object o, String expected) {
    assertEquals(o.toString(), expected);
  }

}
