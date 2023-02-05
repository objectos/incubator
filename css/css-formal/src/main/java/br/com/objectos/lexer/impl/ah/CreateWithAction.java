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

class CreateWithAction implements SpecAction {

  private final int rank;
  final Class<?> type;
  final CreateWith createWith;

  CreateWithAction(int rank, Class<?> type, CreateWith createWith) {
    this.rank = rank;
    this.type = type;
    this.createWith = createWith;
  }

  @Override
  public final void execute(StateSubject subject) {
    ValueList valueList = subject.valueList();
    Object object = createWith.get(valueList);
    valueList.set(object);
  }

  @Override
  public final void onSpecActionBrick(BrickMap brickMap, Link link) {
    brickMap.putValue(type, link);
  }

  @Override
  public final int rank() {
    return rank;
  }

  @Override
  public final String toString() {
    return SpecAction.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return w.writeKind().write(':').write('<').write(type.getSimpleName()).write('>');
  }

}