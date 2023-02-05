/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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

public enum Phase implements MvnOption {

  VALIDATE("validate"),

  INITIALIZE("initialize"),

  GENERATE_SOURCES("generate-sources"),

  PROCESS_SOURCES("process-sources"),

  GENERATE_RESOURCES("generate-resources"),

  PROCESS_RESOURCES("process-resources"),

  COMPILE("compile"),

  PROCESS_CLASSES("process-classes"),

  GENERATE_TEST_SOURCES("generate-test-sources"),

  PROCESS_TEST_SOURCES("process-test-sources"),

  GENERATE_TEST_RESOURCES("generate-test-resources"),

  PROCESS_TEST_RESOURCES("process-test-resources"),

  TEST_COMPILE("test-compile"),

  PROCESS_TEST_CLASSES("process-test-classes"),

  TEST("test"),

  PREPARE_PACKAGE("prepare-package"),

  PACKAGE("package"),

  PRE_INTEGRATION_TEST("pre-integration-test"),

  INTEGRATION_TEST("integration-test"),

  POST_INTEGRATION_TEST("post-integration-test"),

  VERIFY("verify"),

  INSTALL("install"),

  DEPLOY("deploy"),

  //

  PRE_CLEAN("pre-clean"),

  CLEAN("clean"),

  POST_CLEAN("post-clean"),

  //

  PRE_SITE("pre-site"),

  SITE("site"),

  POST_SITE("post-site"),

  SITE_DEPLOY("site-deploy"),

  //

  NONE("");

  private final String id;

  private Phase(String id) {
    this.id = id;
  }

  @Override
  public final void acceptMvnRequest(MvnRequest request) {
    request.addGoal(id);
  }

  public final String id() {
    return this.id;
  }

}
