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

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import java.io.IOException;

abstract class PomFile {

  private final RegularFile pom;

  PomFile(RegularFile pom) {
    this.pom = pom;
  }

  public final RegularFile pom() {
    return pom;
  }

  final Directory basedir() throws IOException {
    return pom.getParent();
  }

  final RegularFile file(String name) throws IOException {
    return basedir().getRegularFile(name);
  }

}
