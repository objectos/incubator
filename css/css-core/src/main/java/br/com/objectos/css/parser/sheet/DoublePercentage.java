/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.css.type.Creator;
import br.com.objectos.css.type.Marker;

final class DoublePercentage extends Percentage {

  private final double value;

  DoublePercentage(double value) {
    this.value = value;
  }

  @Override
  public final void acceptValueCreator(Creator creator) {
    creator.createPercentage(value);
  }

  @Override
  public final void acceptValueMarker(Marker marker) {
    marker.markDoublePercentage();
  }

  @Override
  public final String toString() {
    return Double.toString(value) + "%";
  }

}