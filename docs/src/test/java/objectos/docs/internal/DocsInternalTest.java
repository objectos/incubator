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

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import objectos.docs.AbstractDocsTest;
import org.testng.Assert;
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

  @Test(description = """
  Generation test of archive/0.6.4
  """)
  public void testCase01() throws IOException {
    test(
      resultDir.resolve("tc01"),
      "archive/0.6.4/index.adoc",
      "archive/0.6.4/objectos-asciidoc/index.adoc",
      "archive/0.6.4/objectos-code/tutorial.adoc"
    );
  }

  private void test(Path basedir, String... files) throws IOException {
    docs.clearScan();

    var refdir = basedir.resolve("ref");

    var valdir = basedir.resolve("val");

    var fileNames = Set.of(files);

    docs.fileFilter = (path) -> {
      var relative = sourcedir.relativize(path);

      return fileNames.contains(relative.toString());
    };

    docs.targetDirectory(refdir);

    docs.validationDirectory(valdir);

    docs.executeScan();

    docs.executeGenerate();

    validate(refdir, valdir);
  }

  private void validate(Path targetDirectory, Path validationDirectory) {
    try (var walk = Files.walk(targetDirectory)) {
      walk.filter(Files::isRegularFile)
          .forEach(file -> {
            try {
              validate(targetDirectory, validationDirectory, file);
            } catch (IOException e) {
              throw new UncheckedIOException(e);
            }
          });
    } catch (IOException e1) {
      throw new UncheckedIOException(e1);
    }
  }

  private void validate(Path target, Path validation, Path file) throws IOException {
    var refLines = Files.readAllLines(file);

    Path relativize = target.relativize(file);

    Path validationFile = validation.resolve(relativize);

    var valLines = Files.readAllLines(validationFile);

    Patch<String> diff = DiffUtils.diff(refLines, valLines);

    List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff("original-file.txt",
      "new-file.txt", refLines, diff, 0);

    if (unifiedDiff.size() > 0) {
      unifiedDiff.forEach(System.out::println);

      Assert.fail(relativize.toString());
    }
  }

}