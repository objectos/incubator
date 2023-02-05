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
package br.com.objectos.parser.impl.rd;

class ProductionHead implements State {

  private final ProductionState productionState;

  ProductionHead(ProductionState productionState) {
    this.productionState = productionState;
  }

  @Override
  public final int depth() {
    return productionState.depth();
  }

  @Override
  public final int offset() {
    return -1;
  }

  @Override
  public final int tokenCount() {
    return 0;
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return w;
  }

  @Override
  public final State trackback() {
    return productionState.trackback();
  }

  //

  @Override
  public final Object getResult() {
    throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support this op.");
  }

  @Override
  public final boolean hasNext() {
    throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support this op.");
  }

  @Override
  public final State next() {
    throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support this op.");
  }

}