/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

import objectos.css.type.Creator;
import objectos.css.type.LengthUnit;
import objectos.css.type.Marker;

final class IntLength extends Length {

  private final int value;

  IntLength(int value, LengthUnit unit) {
    super(unit);
    this.value = value;
  }

  @Override
  public final void acceptValueCreator(Creator creator) {
    creator.createLength(unit, value);
  }

  @Override
  public final void acceptValueMarker(Marker marker) {
    marker.markIntLength();
  }

  @Override
  public final String toString() {
    return Integer.toString(value) + unit.getName();
  }

}