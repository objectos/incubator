/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

enum UndefinedTokenLink implements Link {

  INSTANCE;

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final Link addLast(Link last) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final Link merge(Link nextLink) {
    return new BeginLink(nextLink);
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    throw new UnsupportedOperationException("Implement me");
  }

}