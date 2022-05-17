/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import static br.com.objectos.mojo.Mojo.id;
import static br.com.objectos.mojo.Mojo.mirror;
import static br.com.objectos.mojo.Mojo.mirrorOf;
import static br.com.objectos.mojo.Mojo.url;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import java.io.IOException;
import objectos.lang.Checks;

final class MojoTesting {

  private static final MojoTesting INSTANCE = create();

  final String compilerSource;

  final String compilerTarget;

  final String mirrorUrl;

  final Directory repository;

  MojoTesting(String compilerSource,
              String compilerTarget,
              String mirrorUrl,
              Directory repository) {
    this.compilerSource = compilerSource;
    this.compilerTarget = compilerTarget;
    this.mirrorUrl = mirrorUrl;
    this.repository = repository;
  }

  public static MojoTesting get() {
    return INSTANCE;
  }

  public static MvnRequest getMvnRequest(MvnOption option) {
    MvnRequest request;
    request = new MvnRequest();

    option.acceptMvnRequest(request);

    return request;
  }

  private static MojoTesting create() {
    String javaVersionString;
    javaVersionString = System.getProperty("java.version");

    String javaVersion;

    if (javaVersionString.startsWith("1.6")) {
      javaVersion = "1.6";
    }

    else if (javaVersionString.startsWith("1.7")) {
      javaVersion = "1.7";
    }

    else {
      javaVersion = "1.8";
    }

    String mirrorUrl;
    mirrorUrl = System.getProperty("objectos.mojo.testing.mirrorUrl");

    Checks.checkNotNull(mirrorUrl, "objectos.mojo.testing.mirrorUrl was not set");

    String repositoryPath;
    repositoryPath = System.getProperty("objectos.mojo.testing.repository");

    Checks.checkNotNull(repositoryPath, "objectos.mojo.testing.repository was not set");

    Directory repository;

    try {
      repository = LocalFs.resolve(repositoryPath).toDirectoryCreateIfNotFound();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new MojoTesting(
        xml("maven.compiler.source", javaVersion),
        xml("maven.compiler.target", javaVersion),
        mirrorUrl,
        repository
    );
  }

  private static String xml(String tagName, String value) {
    StringBuilder s;
    s = new StringBuilder();

    s.append('<');
    s.append(tagName);
    s.append('>');

    s.append(value);

    s.append('<');
    s.append('/');
    s.append(tagName);
    s.append('>');

    return s.toString();
  }

  public final MojoRuntime createRuntime() throws MojoException {
    return MojoRuntime.runtime(
        mirror(
            id("mojo"),
            url(mirrorUrl),
            mirrorOf("*")
        )
    );
  }

}
