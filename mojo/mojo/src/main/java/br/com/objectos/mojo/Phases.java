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

final class Phases {

  private Phases() {}
  
  public static Phase validate() {
    return Phase.VALIDATE;
  }

  public static Phase generateSources() {
    return Phase.GENERATE_SOURCES;
  }

  public static Phase generateResources() {
    return Phase.GENERATE_RESOURCES;
  }

  public static Phase compile() {
    return Phase.COMPILE;
  }

  public static Phase generateTestSources() {
    return Phase.GENERATE_TEST_SOURCES;
  }

  public static Phase generateTestResources() {
    return Phase.GENERATE_TEST_RESOURCES;
  }

  public static Phase testCompile() {
    return Phase.TEST_COMPILE;
  }

  public static Phase test() {
    return Phase.TEST;
  }

  public static Phase packagePhase() {
    return _package();
  }

  public static Phase _package() {
    return Phase.PACKAGE;
  }

  public static Phase integrationTest() {
    return Phase.INTEGRATION_TEST;
  }

  public static Phase verify() {
    return Phase.VERIFY;
  }

  public static Phase install() {
    return Phase.INSTALL;
  }

  public static Phase deploy() {
    return Phase.DEPLOY;
  }

  public static Phase clean() {
    return Phase.CLEAN;
  }

  public static Phase site() {
    return Phase.SITE;
  }

  public static Phase siteDeploy() {
    return Phase.SITE_DEPLOY;
  }

}
