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
import static br.com.objectos.mojo.Mojo.mirror;
import static br.com.objectos.mojo.Mojo.mirrorOf;
import static br.com.objectos.mojo.Mojo.url;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Write;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.testing.TmpDir;
import java.io.IOException;
import objectos.lang.Try;
import org.testng.annotations.Test;

public class MojoRuntimeTest {

  @Test
  public void testCase01() throws IOException {
    MojoTesting mojoTesting;
    mojoTesting = MojoTesting.get();

    Directory root;
    root = TmpDir.create();

    MojoRuntime runtime;
    runtime = null;

    Throwable rethrow;
    rethrow = Try.begin();

    try {
      runtime = MojoRuntime.runtime(
        localRepository(
          mojoTesting.repository
        ),

        mirror(
          id("mojo"),
          url(mojoTesting.mirrorUrl),
          mirrorOf("*")
        )
      );

      BuildRequest request;
      request = new BuildRequest();

      request.setGoals(ImmutableList.of("verify"));

      Directory basedir;
      basedir = root.createDirectory("basedir");

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
          "<artifactId>mojo-runtime-it-01</artifactId>",
          "<version>0.1.0</version>",
          "<properties>",
          "</properties>",
          "</project>"
        )
      );

      request.setPom(pom);

      Result result;
      result = runtime.execute(request);

      assertTrue(result.isSuccess());

      Directory outputDirectory;
      outputDirectory = basedir.getDirectory("target");

      assertTrue(outputDirectory.exists());

      Log log;
      log = result.getLog();

      assertTrue(log.containsMessage("BUILD SUCCESS"));
    } catch (IOException e) {
      rethrow = e;
    } catch (MojoException e) {
      rethrow = e;
    } finally {
      rethrow = Try.close(rethrow, runtime);
    }

    Try.rethrowIfPossible(rethrow, IOException.class);
  }

}
