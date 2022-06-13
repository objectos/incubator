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

import java.nio.ByteBuffer;
import objectos.lang.Check;
import objectos.util.ImmutableSet;
import objectos.util.MutableSet;

final class FilterNonExisting implements ObjectReaderAdapter {

  private ObjectReaderHandle handle;

  private final GitInjector injector;

  private ImmutableSet<ObjectId> objects;

  private GitRepository repository;

  private MutableSet<ObjectId> result;

  FilterNonExisting(GitInjector injector) {
    this.injector = injector;
  }

  @Override
  public final void executeFinally() {
    executeResult();

    handle = null;

    objects = null;

    repository = null;

    result = injector.putMutableSet(result);
  }

  @Override
  public final void executeObjectBodyFull(byte[] array, int length, ByteBuffer buffer) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void executeObjectBodyPart(ByteBuffer buffer) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void executeObjectFinish() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void executeObjectHeader(ObjectKind kind, long length) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void executeObjectNotFound(ObjectId objectId) {
    result.add(objectId);
  }

  @Override
  public final void executeObjectStart(ObjectId objectId) {
    // noop
  }

  @Override
  public final void executeStart(ObjectReaderHandle handle) {
    Check.state(this.handle == null, "already started");

    handle.setInputMany(ObjectReaderMode.EXISTS, objects);

    this.handle = handle;

    result = injector.getMutableSet();
  }

  @Override
  public final GitRepository getRepository() {
    return repository;
  }

  public final void set(GitRepository repository, ImmutableSet<ObjectId> objects) {
    this.repository = Check.notNull(repository, "repository == null");

    this.objects = Check.notNull(objects, "objects == null");
  }

  final void executeResult() {
    handle.setResult(
        result.toImmutableSet()
    );
  }

}