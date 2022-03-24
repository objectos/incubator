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

import static org.testng.Assert.assertEquals;

import java.util.Properties;
import org.testng.annotations.Test;

public class SystemPropertyTest {

  @Test
  public void acceptMvnRequest() {
    SystemProperty goal;
    goal = SystemProperty.prop("maven.test.skip", true);

    MvnRequest request;
    request = MojoTesting.getMvnRequest(goal);

    Properties properties;
    properties = request.systemProperties;

    assertEquals(properties.size(), 1);
    assertEquals(properties.get("maven.test.skip"), "true");
  }

}