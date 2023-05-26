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
package br.com.objectos.css.maven.plugin.framework;

import objectos.css.keyword.StandardKeyword;
import objectos.css.type.AngleUnit;
import objectos.css.type.ColorKind;
import objectos.css.type.ColorName;
import objectos.css.type.Creator;
import objectos.css.type.LengthUnit;
import objectos.css.type.Marker;
import objectos.css.type.Value;
import objectos.lang.Check;

class SimpleValueVisitor<E> implements Creator, Marker {

  private E result;

  public final E accept(Value value) {
    result = null;

    value.acceptValueCreator(this);

    if (result != null) {
      return result;
    }

    value.acceptValueMarker(this);

    if (result != null) {
      return result;
    }

    throw new AssertionError("null result when value is " + value);
  }

  @Override
  public void addColor(ColorName color) {}

  @Override
  public void addKeyword(StandardKeyword keyword) {}

  @Override
  public void addZero() {}

  @Override
  public void createAngle(AngleUnit unit, double value) {}

  @Override
  public void createAngle(AngleUnit unit, int value) {}

  @Override
  public void createColor(String value) {}

  @Override
  public void createDouble(double value) {}

  @Override
  public void createInt(int value) {}

  @Override
  public void createKeyword(String name) {}

  @Override
  public void createLength(LengthUnit unit, double value) {}

  @Override
  public void createLength(LengthUnit unit, int value) {}

  @Override
  public void createPercentage(double value) {}

  @Override
  public void createPercentage(int value) {}

  @Override
  public void createRgb(double r, double g, double b) {}

  @Override
  public void createRgb(double r, double g, double b, double alpha) {}

  @Override
  public void createRgb(int r, int g, int b) {}

  @Override
  public void createRgb(int r, int g, int b, double alpha) {}

  @Override
  public void createRgba(double r, double g, double b, double alpha) {}

  @Override
  public void createRgba(int r, int g, int b, double alpha) {}

  @Override
  public void createString(String value) {}

  @Override
  public void createUri(String value) {}

  @Override
  public void markColor(ColorKind kind) {}

  @Override
  public void markDouble() {}

  @Override
  public void markDoubleAngle() {}

  @Override
  public void markDoubleLength() {}

  @Override
  public void markDoublePercentage() {}

  @Override
  public void markFunction() {}

  @Override
  public void markInt() {}

  @Override
  public void markIntAngle() {}

  @Override
  public void markIntLength() {}

  @Override
  public void markIntPercentage() {}

  @Override
  public void markKeyword() {}

  @Override
  public void markString() {}

  @Override
  public void markUri() {}

  protected final void set(E newResult) {
    this.result = Check.notNull(newResult, "newResult == null");
  }

}