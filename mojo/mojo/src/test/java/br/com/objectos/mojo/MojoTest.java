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
import static br.com.objectos.mojo.Mojo.localRepository;
import static br.com.objectos.mojo.Mojo.mavenTestSkip;
import static br.com.objectos.mojo.Mojo.mirror;
import static br.com.objectos.mojo.Mojo.mirrorOf;
import static br.com.objectos.mojo.Mojo.plugin;
import static br.com.objectos.mojo.Mojo.runtime;
import static br.com.objectos.mojo.Mojo.url;
import static br.com.objectos.mojo.Mojo.verify;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Write;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.testing.TmpDir;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MojoTest {

  private String compilerSource;

  private String compilerTarget;

  private MojoRuntime runtime;

  @BeforeClass
  public void _setUp() throws MojoException {
    MojoTesting mojoTesting;
    mojoTesting = MojoTesting.get();

    compilerSource = mojoTesting.compilerSource;

    compilerTarget = mojoTesting.compilerTarget;

    runtime = runtime(
      localRepository(
        mojoTesting.repository
      ),

      mirror(
        id("mojo"),
        url(mojoTesting.mirrorUrl),
        mirrorOf("*")
      )
    );
  }

  @Test(description = ""
      + "simple project: "
      + "- standard locations "
      + "- no parent "
      + "- no plugins "
      + "- no resources "
      + "- single java file")
  public void testCase01() throws IOException, MojoException {
    Directory basedir;
    basedir = TmpDir.create();

    RegularFile pom;
    pom = basedir.createRegularFile("pom.xml");

    Write.string(
      pom,

      Charsets.utf8(),

      String.join(
        System.lineSeparator(),

        "<project>",
        "<modelVersion>4.0.0</modelVersion>",
        "<groupId>br.com.objectos.mojo</groupId>",
        "<artifactId>mojo-test-case-01</artifactId>",
        "<version>0.1.0</version>",
        "<properties>",
        compilerSource,
        compilerTarget,
        "</properties>",
        "</project>"
      )
    );

    ResolvedPath resolved;
    resolved = basedir.resolve("src", "main", "java");

    resolved.createParents();

    Directory srcMainJava;
    srcMainJava = resolved.createDirectory();

    Directory srcMainTesting;
    srcMainTesting = srcMainJava.createDirectory("testing");

    RegularFile mainJava;
    mainJava = srcMainTesting.createRegularFile("Main.java");

    Write.string(
      mainJava,

      Charsets.utf8(),

      String.join(
        System.lineSeparator(),

        "package testing;",
        "public class Main {}"
      )
    );

    // plugin goal
    Result result;
    result = runtime.mvn(basedir, plugin("compiler", "compile"));

    result.throwExceptionIfFailed();

    assertTrue(result.isSuccess());

    Log log;
    log = result.getLog();

    assertTrue(log.containsMessage("BUILD SUCCESS"));

    Directory outputDirectory;
    outputDirectory = result.getOutputDirectory();

    Directory testingPackage;
    testingPackage = outputDirectory.getDirectory("testing");

    RegularFile mainClassFile;
    mainClassFile = testingPackage.getRegularFile("Main.class");

    assertTrue(mainClassFile.exists());

    // system property
    result = runtime.mvn(basedir, verify(), mavenTestSkip());

    assertTrue(result.isSuccess());

    log = result.getLog();

    assertTrue(log.containsMessage("Tests are skipped."));
  }

}
