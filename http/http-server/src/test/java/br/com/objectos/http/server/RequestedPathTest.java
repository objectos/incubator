/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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

import objectos.util.UnmodifiableList;
import org.testng.annotations.Test;

public class RequestedPathTest {

  @Test
  public void emptyShouldOnlyHaveEndPart() {
    RequestedPath path = new RequestedPath("/");
    assertEquals(list(path), "[]");
  }

  @Test
  public void intPart() {
    RequestedPath path = new RequestedPath("/123");
    assertEquals(list(path), "[int{123}]");
  }

  @Test
  public void singleStringPart() {
    RequestedPath path = new RequestedPath("/abc");
    assertEquals(list(path), "[string{abc}]");
  }

  @Test
  public void twoStringParts() {
    RequestedPath path = new RequestedPath("/abc/xyz");
    assertEquals(list(path), "[string{abc},string{xyz}]");
  }

  private String list(RequestedPath path) {
    return UnmodifiableList.copyOf(path).join(",", "[", "]");
  }

}