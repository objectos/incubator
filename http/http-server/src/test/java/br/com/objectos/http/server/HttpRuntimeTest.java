/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.server;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HttpRuntimeTest {

  @DataProvider
  public Object[][] toQualifiedNameProvider() {
    return new Object[][] {
        { HttpRuntime.of(80), "/", "http://localhost/" },
        { HttpRuntime.of(8080), "/", "http://localhost:8080/" },
        { HttpRuntime.of("host", 7000), "/hello.html", "http://host:7000/hello.html" },
        { HttpRuntime.of("host", 7000), "prepend.me", "http://host:7000/prepend.me" },
        { HttpRuntime.of(80), "", "http://localhost/" },
    };
  }

  @Test(dataProvider = "toQualifiedNameProvider")
  public void toQualifiedName(HttpRuntime runtime, String simplePath, String expected) {
    assertEquals(runtime.toQualifiedPath(simplePath), expected);
  }

}