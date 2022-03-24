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

import org.apache.maven.settings.Mirror;
import org.testng.annotations.Test;

public class MojoMirrorTest {

  @Test
  public void mirror() {
    MojoMirror mirror;
    mirror = MojoMirror.mirror(
        Mojo.id("mojo"),
        Mojo.url("http://server/path"),
        MojoMirror.mirrorOf("*")
    );

    Mirror result;
    result = mirror.get();

    assertEquals(result.getId(), "mojo");
    assertEquals(result.getUrl(), "http://server/path");
    assertEquals(result.getMirrorOf(), "*");
  }

}
