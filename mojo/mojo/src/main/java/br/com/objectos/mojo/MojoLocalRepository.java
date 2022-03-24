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

import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;

public class MojoLocalRepository implements MojoRuntimeElement {

  private final Directory directory;

  private MojoLocalRepository(Directory directory) {
    this.directory = directory;
  }

  public static MojoLocalRepository localRepository(Directory directory) {
    Checks.checkNotNull(directory, "directory == null");

    return new MojoLocalRepository(directory);
  }

  @Override
  public final void acceptMojoRuntimeBuilder(MojoRuntime.Builder builder) {
    builder.setLocalRepository(directory);
  }

}
