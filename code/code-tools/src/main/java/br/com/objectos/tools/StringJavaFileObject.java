/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.tools;

import br.com.objectos.core.set.ImmutableSet;
import br.com.objectos.core.string.Strings;
import java.io.IOException;
import java.net.URI;

class StringJavaFileObject extends AbstractJavaFileObject {

  private static final ImmutableSet<String> TYPES_KEYWORDS = ImmutableSet.of(
      "@interface",
      "class",
      "enum",
      "interface",
      "record"
  );

  private CanonicalName canonicalName;

  private final String source;
  private URI uri;

  StringJavaFileObject(String source) {
    this.source = source;
  }

  @Override
  public final CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    return source;
  }

  @Override
  public final Kind getKind() {
    return Kind.SOURCE;
  }

  @Override
  public final String getName() {
    URI uri;
    uri = getUri();

    return uri.getPath();
  }

  @Override
  public final boolean isNameCompatible(String simpleName, Kind kind) {
    return getKind().equals(kind)
        && getSimpleName().endsWith(simpleName);
  }

  @Override
  public final URI toUri() {
    return getUri();
  }

  private CanonicalName getCanonicalName() {
    if (canonicalName == null) {
      canonicalName = getCanonicalName0();
    }

    return canonicalName;
  }

  private CanonicalName getCanonicalName0() {
    Lexer lexer;
    lexer = new Lexer(source);

    boolean simpleNameFound;
    simpleNameFound = false;

    String packageName = "";
    String simpleName = "";

    while (lexer.hasNext() && !simpleNameFound) {
      String word;
      word = lexer.next();

      if (word.equals("package") && lexer.hasNext()) {
        packageName = lexer.next();
        continue;
      }

      if (word.equals("module")) {
        simpleName = "module-info";
        break;
      }

      if (TYPES_KEYWORDS.contains(word) && lexer.hasNext()) {
        simpleName = lexer.next();
        simpleNameFound = true;
        continue;
      }
    }

    if (simpleName.equals("") && !packageName.isEmpty()) {
      simpleName = "package-info";
    }

    return new CanonicalName(packageName, simpleName);
  }

  private String getPackageName() {
    CanonicalName canonicalName;
    canonicalName = getCanonicalName();

    return canonicalName.packageName;
  }

  private String getSimpleName() {
    CanonicalName canonicalName;
    canonicalName = getCanonicalName();

    return canonicalName.simpleName;
  }

  private URI getUri() {
    if (uri == null) {
      uri = getUri0();
    }

    return uri;
  }

  private URI getUri0() {
    StringBuilder uri;
    uri = new StringBuilder();

    String packageName;
    packageName = getPackageName();

    String packagePath;
    packagePath = packageName.replace('.', '/');

    if (!packagePath.isEmpty()) {
      uri.append(packagePath);

      uri.append('.');
    }

    String simpleName;
    simpleName = getSimpleName();

    uri.append(simpleName);

    uri.append(".java");

    return URI.create(uri.toString());
  }

  private static class CanonicalName {

    final String packageName;
    final String simpleName;

    CanonicalName(String packageName, String simpleName) {
      this.packageName = packageName;
      this.simpleName = simpleName;
    }

  }

  private static class Lexer {

    private final int[] codePoints;
    private int index;

    private String nextWord;
    private boolean nextWordComputed;

    Lexer(String source) {
      codePoints = Strings.toCodePoints(source);
    }

    final boolean hasNext() {
      if (!nextWordComputed) {
        hasNextWord0();

        nextWordComputed = true;
      }

      return nextWord != null;
    }

    final String next() {
      String result = nextWord;

      nextWord = null;
      nextWordComputed = false;

      return result;
    }

    private void hasNextWord0() {
      if (index < codePoints.length) {
        hasNextWord1();
      }
    }

    private void hasNextWord1() {
      trim();

      StringBuilder word;
      word = new StringBuilder();

      for (; index < codePoints.length; index++) {
        int codePoint;
        codePoint = codePoints[index];

        if (Character.isWhitespace(codePoint)) {
          break;
        }

        if (codePoint == '<') {
          break;
        }

        if (codePoint != ';') {
          word.appendCodePoint(codePoint);
        }
      }

      if (word.length() > 0) {
        nextWord = word.toString();
      }
    }

    private void trim() {
      for (; index < codePoints.length; index++) {
        int codePoint;
        codePoint = codePoints[index];

        if (!Character.isWhitespace(codePoint)) {
          break;
        }
      }
    }

  }

}