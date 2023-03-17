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

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public abstract class AbstractDocsTest {

  private static final FileVisitor<Path> RMDIR = new SimpleFileVisitor<Path>() {
    @Override
    public final FileVisitResult postVisitDirectory(
        Path dir, IOException e) throws IOException {
      if (e == null) {
        Files.delete(dir);

        return FileVisitResult.CONTINUE;
      } else {
        throw e;
      }
    }

    @Override
    public final FileVisitResult visitFile(
        Path file, BasicFileAttributes attrs) throws IOException {
      Files.delete(file);

      return FileVisitResult.CONTINUE;
    }
  };

  protected Path root;

  protected final Path createTempDirectory() throws IOException {
    return Files.createTempDirectory("objectos-docs-temp-");
  }

  protected final void setRoot() throws URISyntaxException {
    if (root == null) {
      var markerClass = AbstractDocsTest.class;

      var markerUrl = markerClass.getResource("main-marker");

      var marker = Path.of(markerUrl.toURI());

      root = marker.getParent().getParent().getParent().getParent().getParent();
    }
  }

  protected final Path testResources() {
    return root.resolve("src/test/resources");
  }

  protected final void rmdir(Path target) throws IOException {
    if (target != null) {
      Files.walkFileTree(target, RMDIR);
    }
  }

}