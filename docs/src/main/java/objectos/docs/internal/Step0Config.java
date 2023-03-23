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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import objectos.util.GrowableList;

class Step0Config {

  String baseHref;

  boolean production;

  final List<Path> sourceDirectories = new GrowableList<>();

  Path targetDirectory;

  public final void clearConfig() {
    sourceDirectories.clear();

    targetDirectory = null;
  }

  public final void parseArgs(String... args) {
    int length = args.length;

    if (length < 2) {
      printHelp();

      return;
    }

    var sourcePathName = args[0];

    var targetPathName = args[1];

    var mainOnly = false;

    if (length > 2) {
      for (int i = 2; i < length;) {
        var arg = args[i++];

        switch (arg) {
          case "--main" -> mainOnly = true;

          default -> {
            throw new IllegalArgumentException("""
            Invalid option: %s
            """.formatted(arg)
            );
          }
        }
      }
    }

    sourcePath(sourcePathName, mainOnly);

    targetDirectory(targetPathName);
  }

  public final void production() {
    baseHref = "/docs";

    production = true;
  }

  public final void sourceDirectory(Path path) {
    sourcePath(path, false);
  }

  public final void targetDirectory(Path path) {
    if (Files.exists(path) && !Files.isDirectory(path)) {
      throw new IllegalArgumentException("""
      Invalid <target-path>: %s is not a directory
      """.formatted(path));
    }

    targetDirectory = path;

    development();
  }

  private void development() {
    baseHref = targetDirectory.toString();

    production = false;
  }

  private void printHelp() {
    System.out.print(
      """
      Usage: java objectos.docs.DocsSite <source-path> <target-path> [--main]
      """
    );
  }

  private void sourcePath(Path sourcePath, boolean mainOnly) {
    if (!Files.isDirectory(sourcePath)) {
      throw new IllegalArgumentException(
        """
        Invalid <source-path>: %s is not a directory
        """.formatted(sourcePath)
      );
    }

    sourcePathAdd(sourcePath, "main");

    if (mainOnly) {
      return;
    }

    sourcePathAdd(sourcePath, "archive");
  }

  private void sourcePath(String sourcePathName, boolean mainOnly) {
    var sourcePath = Path.of(sourcePathName).toAbsolutePath();

    sourcePath(sourcePath, mainOnly);
  }

  private void sourcePathAdd(Path sourcePath, String name) {
    var path = sourcePath.resolve(name);

    sourceDirectories.add(path);
  }

  private void targetDirectory(String targetPathName) {
    var maybe = Path.of(targetPathName).toAbsolutePath();

    targetDirectory(maybe);
  }

}