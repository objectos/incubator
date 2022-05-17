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

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.set.ImmutableSet;
import br.com.objectos.core.set.MutableSet;
import objectos.lang.Checks;

/**
 * Recursively copies a tree object from one repository to another.
 *
 * <p>
 * Even though this is a command instance, it is a low-level (plumbing)
 * operation.
 *
 * @since 3
 */
final class CopyTreeCommand extends StageGitCommand<ObjectId> {

  private final MutableSet<ObjectId> objectsToCopy = MutableSet.create();

  private final Repository source;

  private final ObjectId sourceId;

  private final Repository target;

  CopyTreeCommand(Repository source, ObjectId tree, Repository destination) {
    this.source = Checks.checkNotNull(source, "source == null");

    this.sourceId = Checks.checkNotNull(tree, "tree == null");

    this.target = Checks.checkNotNull(destination, "destination == null");
  }

  @Override
  protected final void executeStage(int stage) throws Exception {
    switch (stage) {
      case 0:
        readTree(source, sourceId);

      case 1:
        if (isComputing()) {
          return;
        }

        final ImmutableList<Tree> trees;
        trees = getAll();

        for (Tree tree : trees) {
          visitTree(tree);
        }

        if (hasComputations()) {
          goTo(1);

          return;
        }

        final ImmutableSet<ObjectId> set;
        set = objectsToCopy.toImmutableSet();

        objectsToCopy.clear();

        copyObjects(source, set, target);

      case 2:
        if (isComputing()) {
          return;
        }

        setResult(sourceId);

        return;

      default:
        throw new AssertionError("Unexpected step=" + stage);
    }
  }

  private void visitTree(Tree tree) {
    ObjectId treeId;
    treeId = tree.getObjectId();

    objectsToCopy.add(treeId);

    ImmutableList<Entry> entries;
    entries = tree.getEntries();

    for (Entry entry : entries) {
      ObjectId entryId;
      entryId = entry.getObjectId();

      if (entry.isBlob()) {
        objectsToCopy.add(entryId);
      } else {
        readTree(source, entryId);
      }
    }
  }

}