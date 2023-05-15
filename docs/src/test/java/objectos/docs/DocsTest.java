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
package objectos.docs;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DocsTest extends AbstractDocsTest {

  @BeforeClass
  public void _beforeClass() throws URISyntaxException {
    setRoot();
  }

  @Test(enabled = false, description = """
  Generation test of archive/0.6.4
  """)
  public void testCase01() throws IOException {
    Path result = null;

    try {
      var source = root.resolve("docs");

      result = createTempDirectory();

      var target = result.resolve("target");

      var validation = result.resolve("validation");

      Docs.main(new String[] {
          source.toString(),
          target.toString(),
          validation.toString(),
          "--main"
      });

      try (var walk = Files.walk(target)) {
        walk.filter(Files::isRegularFile)
            .forEach(file -> {
              try {
                validate(target, validation, file);
              } catch (IOException e) {
                throw new UncheckedIOException(e);
              }
            });
      }
    } finally {
      //rmdir(target);
      //rmdir(validation);
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