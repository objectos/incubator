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
package br.com.objectos.git;

import br.com.objectos.core.object.ToString;
import br.com.objectos.fs.Directory;

final class OpenRepositoryTask extends AbstractGitTask<Repository> {

  private final Directory directory;

  OpenRepositoryTask(GitEngine engine, Directory directory) {
    super(engine);

    this.directory = directory;
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.formatToString(
        toString, level, this,
        "directory", directory
    );
  }

  @Override
  final OpenRepository executeSetInputImpl() {
    OpenRepository openRepository;
    openRepository = engine.getOpenRepository();

    openRepository.setInput(directory);

    return openRepository;
  }

}