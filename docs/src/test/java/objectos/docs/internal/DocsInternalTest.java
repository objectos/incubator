/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.internal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import objectos.docs.AbstractDocsTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DocsInternalTest extends AbstractDocsTest {

  private DocsInternal docs;

  private Path resultDir;

  private Path sourcedir;

  @BeforeClass
  public void _beforeClass() throws IOException, URISyntaxException {
    setRoot();

    docs = new DocsInternal();

    sourcedir = root.resolve("docs");

    docs.sourceDirectory(sourcedir);

    docs.executeVersions();

    resultDir = createTempDirectory();
  }

  @AfterClass
  public void _afterClass() throws IOException {
    rmdir(resultDir);
  }

  @Test(enabled = false, description = """
  Generation test of archive/0.6.4
  """)
  public void testCase01() throws IOException {
    test(
      resultDir.resolve("tc01"),
      "archive/0.6.4/intro/index.adoc",
      "archive/0.6.4/intro/install.adoc",
      "archive/0.6.4/intro/overview.adoc",
      "archive/0.6.4/objectos-asciidoc/index.adoc",
      "archive/0.6.4/objectos-html/index.adoc",
      "archive/0.6.4/objectos-lang/note-sink-api/creating-notes.adoc",
      "archive/0.6.4/objectos-lang/note-sink-api/index.adoc",
      "archive/0.6.4/objectos-lang/note-sink-api/the-no-op-note-sink.adoc",
      "archive/0.6.4/objectos-lang/note-sink-api/the-no-op-note-interface.adoc",
      "archive/0.6.4/objectos-lang/Check.adoc",
      "archive/0.6.4/objectos-lang/Equals.adoc",
      "archive/0.6.4/objectos-lang/HashCode.adoc",
      "archive/0.6.4/objectos-lang/index.adoc",
      "archive/0.6.4/objectos-lang/ToString.adoc",
      "archive/0.6.4/objectos-util/collections/builders.adoc",
      "archive/0.6.4/objectos-util/collections/index.adoc",
      "archive/0.6.4/objectos-util/collections/join-method.adoc",
      "archive/0.6.4/objectos-util/collections/limitations.adoc",
      "archive/0.6.4/objectos-util/collections/null-handling.adoc",
      "archive/0.6.4/objectos-util/collections/of.adoc",
      "archive/0.6.4/objectos-util/collections/to-string.adoc",
      "archive/0.6.4/objectos-util/array-utilities.adoc",
      "archive/0.6.4/objectos-util/index.adoc",
      "archive/0.6.4/index.adoc",

      "archive/0.6.4/objectos-code/class/annotations.adoc",
      "archive/0.6.4/objectos-code/class/body.adoc",
      "archive/0.6.4/objectos-code/class/extends.adoc",
      "archive/0.6.4/objectos-code/class/implements.adoc",
      "archive/0.6.4/objectos-code/class/index.adoc",
      "archive/0.6.4/objectos-code/class/modifiers.adoc",
      "archive/0.6.4/objectos-code/class/name.adoc",
      "archive/0.6.4/objectos-code/class/permits.adoc",
      "archive/0.6.4/objectos-code/class/type-parameters.adoc",
      "archive/0.6.4/objectos-code/constructor/annotations.adoc",
      "archive/0.6.4/objectos-code/constructor/body.adoc",
      "archive/0.6.4/objectos-code/constructor/index.adoc",
      "archive/0.6.4/objectos-code/constructor/modifiers.adoc",
      "archive/0.6.4/objectos-code/constructor/parameters.adoc",
      "archive/0.6.4/objectos-code/constructor/throws.adoc",
      "archive/0.6.4/objectos-code/expression/assignment-operator.adoc",
      "archive/0.6.4/objectos-code/expression/class-instance-creation.adoc",
      "archive/0.6.4/objectos-code/field/annotations.adoc",
      "archive/0.6.4/objectos-code/field/index.adoc",
      "archive/0.6.4/objectos-code/field/initializer.adoc",
      "archive/0.6.4/objectos-code/field/modifiers.adoc",
      "archive/0.6.4/objectos-code/field/name.adoc",
      "archive/0.6.4/objectos-code/field/type.adoc",
      "archive/0.6.4/objectos-code/interface/index.adoc",
      "archive/0.6.4/objectos-code/interface/name.adoc",
      "archive/0.6.4/objectos-code/method/annotations.adoc",
      "archive/0.6.4/objectos-code/method/body.adoc",
      "archive/0.6.4/objectos-code/method/index.adoc",
      "archive/0.6.4/objectos-code/method/modifiers.adoc",
      "archive/0.6.4/objectos-code/method/name.adoc",
      "archive/0.6.4/objectos-code/method/parameters.adoc",
      "archive/0.6.4/objectos-code/method/return-type.adoc",
      "archive/0.6.4/objectos-code/method/throws.adoc",
      "archive/0.6.4/objectos-code/method/type-parameters.adoc",
      "archive/0.6.4/objectos-code/index.adoc",
      "archive/0.6.4/objectos-code/tutorial.adoc",

      "archive/0.6.4/objectos-code/statement/if.adoc",
      "archive/0.6.4/objectos-code/statement/index.adoc",
      "archive/0.6.4/objectos-code/statement/return.adoc",
      "archive/0.6.4/objectos-code/template/auto-imports.adoc",
      "archive/0.6.4/objectos-code/template/include.adoc",
      "archive/0.6.4/objectos-code/template/index.adoc",
      "archive/0.6.4/objectos-code/template/recommended-usage.adoc",

      "archive/0.6.4/relnotes/0.1.0.adoc",
      "archive/0.6.4/relnotes/0.2.0.adoc",
      "archive/0.6.4/relnotes/0.3.0.adoc",
      "archive/0.6.4/relnotes/0.4.0.adoc",
      "archive/0.6.4/relnotes/0.4.1.adoc",
      "archive/0.6.4/relnotes/0.4.2.adoc",
      "archive/0.6.4/relnotes/0.4.3.1.adoc",
      "archive/0.6.4/relnotes/0.4.4.adoc",
      "archive/0.6.4/relnotes/0.5.0.adoc",
      "archive/0.6.4/relnotes/0.5.1.adoc",
      "archive/0.6.4/relnotes/0.5.2.adoc",
      "archive/0.6.4/relnotes/0.5.3.adoc",
      "archive/0.6.4/relnotes/0.6.0.adoc",
      "archive/0.6.4/relnotes/0.6.1.adoc",
      "archive/0.6.4/relnotes/0.6.2.adoc",
      "archive/0.6.4/relnotes/0.6.3.adoc",
      "archive/0.6.4/relnotes/0.6.4.adoc",
      "archive/0.6.4/relnotes/index.adoc",

      "main/versions.adoc"
    );
  }

  private void test(Path basedir, String... files) throws IOException {
  }

}