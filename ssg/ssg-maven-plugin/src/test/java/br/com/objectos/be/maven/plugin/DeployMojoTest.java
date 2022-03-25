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
package br.com.objectos.be.maven.plugin;

import java.io.IOException;
import org.testng.annotations.Test;

public class DeployMojoTest {

  @Test
  public void test() throws IOException {
    // BuildResult res = mvn(
    // project("it/be-maven-plugin-it-01"),
    // compile(), plugin("be", "site"),
    // plugin("be", "deploy")
    // );
    // res.throwExceptionIfFailed();
    //
    // Directory deploy = res.directory().changeTo("deploy");
    // assertTrue(deploy.exists());
    //
    // RegularFile index = deploy.getRegularFile("index.html");
    // assertTrue(index.exists());
    //
    // String indexHtml = index.readToString();
    // assertTrue(indexHtml.contains("<!doctype html>"));
    // assertTrue(indexHtml.contains("Hello world!"));
  }

}
