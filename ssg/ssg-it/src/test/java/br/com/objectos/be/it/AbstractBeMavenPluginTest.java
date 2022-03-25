/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.it;

import static br.com.objectos.mojo.Mojo.id;
import static br.com.objectos.mojo.Mojo.localRepository;
import static br.com.objectos.mojo.Mojo.mirror;
import static br.com.objectos.mojo.Mojo.mirrorOf;
import static br.com.objectos.mojo.Mojo.runtime;
import static br.com.objectos.mojo.Mojo.url;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Write;
import br.com.objectos.core.runtime.ObjectosBuild;
import br.com.objectos.core.system.LineSeparator;
import br.com.objectos.core.system.SystemProperty;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.mojo.Mojo;
import br.com.objectos.mojo.MojoException;
import br.com.objectos.mojo.MojoRuntime;
import java.io.IOException;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractBeMavenPluginTest {

  static Directory basedir;

  static String compilerSourceTag;

  static String compilerTargetTag;

  static MojoRuntime runtime;

  static String versionTag;

  @BeforeSuite
  public static void _setUp() throws IOException, MojoException {
    String basedirPath;
    basedirPath = SystemProperty.get("objectos.be.basedir");

    basedir = LocalFs.getDirectory(basedirPath);

    String compilerValue;

    ObjectosBuild build;
    build = ObjectosBuild.get();

    switch (build) {
      case JAVA6:
        compilerValue = "1.6";
        break;
      case JAVA7:
        compilerValue = "1.7";
        break;
      default:
        compilerValue = "1.8";
        break;
    }

    compilerSourceTag = Mojo.xml("maven.compiler.source", compilerValue);

    compilerTargetTag = Mojo.xml("maven.compiler.target", compilerValue);

    runtime = runtime(
        localRepository(
            LocalFs.resolve(
                SystemProperty.get("objectos.mojo.testing.repository")
            ).toDirectoryCreateIfNotFound()
        ),

        mirror(
            id("mojo"),
            url(
                SystemProperty.get("objectos.mojo.testing.mirrorUrl")
            ),
            mirrorOf("*")
        )
    );

    versionTag = Mojo.xml("version", SystemProperty.get("objectos.be.version"));
  }

  final void cloneTo(Directory target, String... pomLines) throws IOException {
    RegularFile pom;
    pom = target.createRegularFile("pom.xml");

    Write.string(pom, Charsets.utf8(), LineSeparator.join(pomLines));

    Directory baseSrcMain;
    baseSrcMain = basedir.resolve("src", "main").toDirectory();

    Directory targetSrcMain;
    targetSrcMain = target.createDirectory("src").createDirectory("main");

    baseSrcMain.copyTo(targetSrcMain);
  }

}
