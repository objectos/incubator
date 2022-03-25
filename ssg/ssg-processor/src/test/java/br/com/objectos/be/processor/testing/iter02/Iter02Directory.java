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
package br.com.objectos.be.processor.testing.iter02;

import br.com.objectos.be.site.AbstractDirectory;

public class Iter02Directory extends AbstractDirectory {

  private final Iter02Path iter02Path;

  public Iter02Directory(Iter02Path iter02Path) {
    this.iter02Path = iter02Path;
  }

  @Override
  protected final void configure() {
    addTemplate(new Index02Impl());
  }

  private class Index02Impl extends Index02 {

    @Override
    final Index02Html index() {
      return iter02Path.index02Html();
    }

  }

}
