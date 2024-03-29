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

import objectos.lang.Note1;
import objectos.lang.Note2;

/**
 * Provides access to the library's logging events.
 *
 * @since 3
 */
final class GitEvents {

  private GitEvents() {}

  public static Note1<ObjectId> objectReaderNextObject() {
    return ObjectReader.ENEXT_OBJECT;
  }

  public static Note1<ObjectId> readTreeStart() {
    return ReadTree.ESTART;
  }

  public static Note1<ObjectId> readTreeTaskSetInput() {
    return ReadTreeTask.ESET_INPUT;
  }

  public static Note2<GitCommand<?>, MutableTree> writeTreeStart() {
    return WriteTree.ESTART;
  }

  public static Note2<GitCommand<?>, ObjectId> writeTreeSuccess() {
    return WriteTree.ESUCCESS;
  }

}