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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import objectos.asciidoc.AsciiDoc;
import objectos.util.GrowableMap;

abstract class Step2Scan extends Step1Versions {

  final GrowableMap<String, DocumentRecord> documents = new GrowableMap<>();

  private final AsciiDoc asciiDoc = AsciiDoc.create();

  private final DocumentTitleProcessor documentTitleProcessor = new DocumentTitleProcessor();

  private IOException rethrow;

  private Path source;

  public final void executeScan() throws IOException {
    long startTime = System.currentTimeMillis();

    for (var sourcePath : sourceDirectories) {
      System.out.println("Resource source path: " + sourcePath);

      source = sourcePath;

      scan();
    }

    long totalTime = System.currentTimeMillis() - startTime;

    System.out.println("Step2: " + totalTime + " ms");
  }

  private void catchIO(IOException e) {
    if (rethrow == null) {
      rethrow = e;
    } else {
      rethrow.addSuppressed(e);
    }
  }

  private void scan() throws IOException {
    rethrow = null;

    try (Stream<Path> walk = Files.walk(source)) {
      walk.filter(Files::isRegularFile)
          .forEach(this::scanFileUnchecked);
    }

    if (rethrow != null) {
      throw rethrow;
    }
  }

  private void scanFile(Path file) throws IOException {
    var fileName = file.getFileName().toString();

    int lastDot = fileName.lastIndexOf('.');

    var extension = fileName.substring(lastDot);

    if (!extension.equals(".adoc")) {
      throw new UnsupportedOperationException(
        "Implement me :: extension=" + extension
      );
    }

    scanFileAsciiDoc(file);
  }

  private void scanFileAsciiDoc(Path file) throws IOException {
    var relativePath = source.relativize(file);

    var key = relativePathToKey(relativePath);

    var version = relativePathToVersion(relativePath);

    var source = Files.readString(file, StandardCharsets.UTF_8);

    var document = asciiDoc.parse(source);

    document.process(documentTitleProcessor);

    var documentTitle = documentTitleProcessor.create();

    var value = new DocumentRecord(file, key, version, document, documentTitle);

    documents.put(key, value);
  }

  private String relativePathToKey(Path relativePath) {
    var key = relativePath.toString();

    return "/" + key.substring(0, key.length() - 5) + ".html";
  }

  private Version relativePathToVersion(Path relativePath) {
    int nameCount = relativePath.getNameCount();

    if (nameCount > 1) {
      var versionPath = relativePath.getName(0);

      var versionName = versionPath.toString();

      return versions.get(versionName);
    } else {
      return null;
    }
  }

  private void scanFileUnchecked(Path file) {
    try {
      scanFile(file);
    } catch (IOException e) {
      catchIO(e);
    }
  }

}