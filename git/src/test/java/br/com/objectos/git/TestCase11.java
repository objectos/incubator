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

import br.com.objectos.core.io.Charsets;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objectos.util.UnmodifiableList;
import objectos.util.UnmodifiableSet;
import objectos.util.GrowableSet;

/**
 * Copy use-case. Verify that generated trees are sorted. In particular when
 * blobs are edited/filtered since these are executed last by the CopyJob.
 *
 * @since 1
 */
final class TestCase11 extends StageGitCommand<ObjectId> {

  static final String DESCRIPTION = "Copy use-case: sorted generated trees";

  private final Map<ObjectId, String> blobNames = new HashMap<>();

  private final GrowableSet<ObjectId> blobsToCopy = new GrowableSet<>();

  private final GrowableSet<ObjectId> blobsToRead = new GrowableSet<>();

  private Commit parentCommit;

  private ObjectId parentId;

  private final Map<ObjectId, MutableTree> parentMap = new HashMap<>();

  private MutableTree rootTree;

  private Commit sourceCommit;

  private Repository sourceRepository;

  private final Directory target;

  private Repository targetRepository;

  private ObjectId written;

  TestCase11(Directory target) {
    this.target = target;
  }

  public static void acceptGitServiceTest(GitServiceTest test) throws IOException {
    Directory target;
    target = test.testCase11Target = TmpDir.create();

    Directory repo01;
    repo01 = TestingGit.repo01();

    repo01.copyTo(target);

    TestCase11 impl;
    impl = new TestCase11(target);

    test.testCase11 = test.submit(impl);
  }

  public static ObjectId getCommit() throws InvalidObjectIdFormatException {
    return ObjectId.parse("717271f0f0ee528c0bb094e8b2f84ea6cef7b39d");
  }

  @Override
  protected final void executeStage(int step) throws Exception {
    switch (step) {
      case 0:
        openRepository(
          TestingGit.repo00()
        );

      case 1:
        if (isComputing()) {
          return;
        }

        sourceRepository = this.<Repository> get();

        openRepository(target);

      case 2:
        if (isComputing()) {
          return;
        }

        targetRepository = this.<Repository> get();

        readCommit(sourceRepository, getCommit());

      case 3:
        if (isComputing()) {
          return;
        }

        sourceCommit = this.<Commit> get();

        resolve(targetRepository, RefName.MASTER);

      case 4:
        if (isComputing()) {
          return;
        }

        MaybeObjectId maybeParentId;
        maybeParentId = this.<MaybeObjectId> get();

        if (!maybeParentId.isObjectId()) {
          submitReadSourceTree();

          goTo(6);

          return;
        }

        parentId = maybeParentId.getObjectId();

        readCommit(targetRepository, parentId);

      case 5:
        if (isComputing()) {
          return;
        }

        parentCommit = this.<Commit> get();

        submitReadSourceTree();

      case 6:
        if (isComputing()) {
          return;
        }

        rootTree = new MutableTree();

        final Tree sourceRoot;
        sourceRoot = get();

        visitTree(sourceRoot);

        if (!hasComputations()) {
          submitCopyBlobs();

          goTo(8);

          return;
        }

      case 7:
        if (isComputing()) {
          return;
        }

        final List<Tree> trees;
        trees = getAll();

        for (Tree tree : trees) {
          visitTree(tree);
        }

        if (hasComputations()) {
          goTo(7);

          return;
        }

        submitCopyBlobs();

      case 8:
        if (isComputing()) {
          return;
        }

        for (ObjectId id : blobsToRead) {
          readBlob(sourceRepository, id);
        }

        if (!hasComputations()) {
          writeTree(targetRepository, rootTree);

          goTo(10);

          return;
        }

      case 9:
        if (isComputing()) {
          return;
        }

        final List<Blob> blobs;
        blobs = getAll();

        for (Blob blob : blobs) {
          visitBlob(blob);
        }

        writeTree(targetRepository, rootTree);

      case 10:
        if (isComputing()) {
          return;
        }

        final ObjectId rootTreeId;
        rootTreeId = get();

        MaybeObjectId parentTree;
        parentTree = MaybeObjectId.empty();

        if (parentCommit != null) {
          parentTree = parentCommit.getTree();
        }

        if (parentTree.equals(rootTreeId)) {
          ObjectId r;
          r = sourceCommit.getObjectId();

          setResult(r);

          return;
        }

        MutableCommit mutable;
        mutable = new MutableCommit();

        if (parentId != null) {
          mutable.addParent(parentId);
        }

        mutable.setAuthor(sourceCommit.getAuthor());

        mutable.setCommitter(sourceCommit.getCommitter());

        mutable.setMessage(
          String.join(
            System.lineSeparator(),

            sourceCommit.getMessage(),
            "source=" + sourceCommit.getObjectId().getHexString(),
            ""
          )
        );

        mutable.setTree(rootTreeId);

        writeCommit(targetRepository, mutable);

      case 11:
        if (isComputing()) {
          return;
        }

        written = this.<ObjectId> get();

        updateRef(targetRepository, RefName.MASTER, written);

      case 12:
        if (isComputing()) {
          return;
        }

        get();

        setResult(written);

        return;

      default:
        throw new AssertionError("Unexpected step=" + step);
    }
  }

  private void submitCopyBlobs() {
    UnmodifiableSet<ObjectId> ids;
    ids = blobsToCopy.toUnmodifiableSet();

    blobsToCopy.clear();

    copyObjects(sourceRepository, ids, targetRepository);
  }

  private void submitReadSourceTree() {
    final ObjectId tid;
    tid = sourceCommit.getTree();

    readTree(sourceRepository, tid);
  }

  private void visitBlob(Blob blob) {
    ObjectId id;
    id = blob.getObjectId();

    String name;
    name = blobNames.remove(id);

    MutableBlob edit;
    edit = new MutableBlob(name);

    MutableTree tree;
    tree = parentMap.remove(id);

    if (edit.hasName("README.md")) {
      edit.setContents(
        String.join(
          System.lineSeparator(),

          "# ObjectosRepo",
          "",
          "test case 11",
          ""
        ).getBytes(Charsets.utf8())
      );

      tree.addEntry(edit);
    }

    else {
      throw new IllegalArgumentException(name);
    }
  }

  private void visitTree(Tree tree) {
    ObjectId treeId;
    treeId = tree.getObjectId();

    MutableTree currentTree;
    currentTree = parentMap.remove(treeId);

    if (currentTree == null) {
      currentTree = rootTree;
    }

    UnmodifiableList<Entry> entries;
    entries = tree.getEntries();

    for (Entry entry : entries) {
      visitTree0(currentTree, entry);
    }
  }

  private void visitTree0(MutableTree currentTree, Entry entry) {
    ObjectId id;
    id = entry.getObjectId();

    String name;
    name = entry.getName();

    if (entry.isBlob()) {
      if (name.equals("README.md")) {
        blobsToRead.add(id);

        blobNames.put(id, name);

        parentMap.put(id, currentTree);

        return;
      }

      blobsToCopy.add(id);

      currentTree.addEntry(entry);

      return;
    }

    // entry is tree

    if (currentTree.hasName("") && name.equals("bin")) {
      return;
    }

    if (currentTree.hasName("src") && name.equals("test")) {
      return;
    }

    readTree(sourceRepository, id);

    MutableTree mutableTree;
    mutableTree = new MutableTree(name);

    currentTree.addEntry(mutableTree);

    parentMap.put(id, mutableTree);
  }

}
