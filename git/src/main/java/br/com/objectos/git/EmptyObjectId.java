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

import java.util.NoSuchElementException;
import objectos.lang.ToString;

final class EmptyObjectId extends MaybeObjectId {

  static final EmptyObjectId INSTANCE = new EmptyObjectId();

  private EmptyObjectId() {}

  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.format(sb, depth, this);
  }

  @Override
  public final ObjectId getObjectId() {
    throw new NoSuchElementException();
  }

  @Override
  public final boolean isObjectId() {
    return false;
  }


}
