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

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import objectos.docs.AbstractDocsTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfigTest extends AbstractDocsTest {

  private final Step0Config config = new Step0Config();

  @BeforeClass
  public void _beforeClass() throws URISyntaxException {
    setRoot();
  }

  @BeforeMethod
  public void _beforeMethod() {
    config.clearConfig();
  }

  @Test
  public void parseArgs01() throws IOException {
    Path target = null;

    try {
      target = createTempDirectory();

      var source = testResources().resolve("parse-args");

      config.parseArgs(
        source.toString(),
        target.toString()
      );

      var sourcePaths = config.sourceDirectories;

      assertEquals(sourcePaths.size(), 2);
      assertEquals(sourcePaths.get(0), source.resolve("main"));
      assertEquals(sourcePaths.get(1), source.resolve("archive"));
    } finally {
      rmdir(target);
    }
  }

  @Test
  public void parseArgs02() throws IOException {
    Path target = null;

    try {
      target = createTempDirectory();

      var source = testResources().resolve("parse-args");

      config.parseArgs(
        source.toString(),
        target.toString(),
        "--main"
      );

      var sourcePaths = config.sourceDirectories;

      assertEquals(sourcePaths.size(), 1);
      assertEquals(sourcePaths.get(0), source.resolve("main"));
    } finally {
      rmdir(target);
    }
  }

}