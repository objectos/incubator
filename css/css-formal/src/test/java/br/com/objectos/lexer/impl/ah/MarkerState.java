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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

class MarkerState implements State {

  private static final AtomicInteger COUNTER = new AtomicInteger();

  private final int rank;
  private final String value;

  MarkerState(int rank, String value) {
    this.rank = rank;
    this.value = value;
  }

  public static MarkerState next(String value) {
    return new MarkerState(COUNTER.getAndIncrement(), value);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof MarkerState)) {
      return false;
    }
    MarkerState that = (MarkerState) obj;
    return rank == that.rank
        && value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(rank, value);
  }

  @Override
  public final StateWriter toString(StateWriter sb) {
    return sb.begin().write("<m:").write(value).write('>');
  }

  //

  @Override
  public final boolean hasNext() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final State next(StateSubject subject) {
    throw new UnsupportedOperationException();
  }

}