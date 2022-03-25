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

import static br.com.objectos.mojo.Mojo.plugin;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.system.SystemProperty;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.mojo.Log;
import br.com.objectos.mojo.Mojo;
import br.com.objectos.mojo.MojoException;
import br.com.objectos.mojo.Result;
import java.io.IOException;
import org.testng.annotations.Test;

public class BeMavenPluginTest extends AbstractBeMavenPluginTest {

  @Test(enabled = false, description = "test be:serve")
  public void testCase01() throws IOException, MojoException {
    Directory basedir;
    basedir = TmpDir.create();

    cloneTo(
        basedir,
        "<project>",

        "<modelVersion>4.0.0</modelVersion>",
        "<groupId>br.com.objectos</groupId>",
        "<artifactId>be-test-case-01</artifactId>",
        versionTag,

        "<properties>",
        compilerSourceTag,
        compilerTargetTag,
        "</properties>",

        "<build>",
        "<plugins>",

        "<plugin>",
        "<artifactId>maven-compiler-plugin</artifactId>",
        "<version>3.8.1</version>",
        "<configuration>",
        "<annotationProcessorPaths>",
        "<annotationProcessorPath>",
        "<groupId>${project.groupId}</groupId>",
        "<artifactId>objectos-be-processor</artifactId>",
        "<version>${project.version}</version>",
        "</annotationProcessorPath>",
        "</annotationProcessorPaths>",
        "</configuration>",
        "</plugin>",

        "<plugin>",
        "<groupId>${project.groupId}</groupId>",
        "<artifactId>objectos-be-maven-plugin</artifactId>",
        "<version>${project.version}</version>",
        "<configuration>",
        "<joinExecution>false</joinExecution>",
        "<site>",
        "<id>site-file</id>",
        "<url>file://${project.build.directory}/deploy</url>",
        "</site>",
        "<sitePackage>br.com.objectos.be.it</sitePackage>",
        "</configuration>",
        "</plugin>",

        "</plugins>",
        "</build>",

        "<dependencies>",
        "<dependency>",
        "<groupId>${project.groupId}</groupId>",
        "<artifactId>objectos-be</artifactId>",
        "<version>${project.version}</version>",
        "</dependency>",
        "</dependencies>",

        "</project>"
    );

    String assertKey;
    assertKey = "objectos.be.it.tc01";

    System.setProperty(assertKey, "");

    Result result;
    result = runtime.mvn(basedir, Mojo.clean(), Mojo.compile(), plugin("objectos-be", "serve"));

    result.throwExceptionIfFailed();

    assertTrue(result.isSuccess());

    Log log;
    log = result.getLog();

    assertTrue(log.containsMessage("BUILD SUCCESS"));

    assertEquals(SystemProperty.get(assertKey), "Hello!");
  }

}
