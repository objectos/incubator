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
package br.com.objectos.git;

import objectos.lang.ToString;
import objectos.util.UnmodifiableSet;

final class CopyObjectsTask extends AbstractGitTask<UnmodifiableSet<ObjectId>> {

  private final Repository destination;

  private final UnmodifiableSet<ObjectId> objectsToCopy;

  private final Repository source;

  CopyObjectsTask(GitEngine engine,
                  Repository source,
                  UnmodifiableSet<ObjectId> objectsToCopy,
                  Repository destination) {
    super(engine);

    this.source = source;

    this.objectsToCopy = objectsToCopy;

    this.destination = destination;
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
        toString, level, this,
        "source", source,
        "objectsToCopy", objectsToCopy,
        "destination", destination
    );
  }

  @Override
  final AbstractGitEngineTask executeSetInputImpl() {
    CopyObjects task;
    task = engine.getCopyObjects();

    task.setInput(source, objectsToCopy, destination);

    return task;
  }

}