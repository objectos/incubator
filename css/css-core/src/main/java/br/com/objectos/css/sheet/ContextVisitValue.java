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
package br.com.objectos.css.sheet;

import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;

abstract class ContextVisitValue<E extends Exception> extends Context<E> {

  ContextVisitValue() {}

  abstract Context<E> toNextContext(Adapter<E> a) throws E;

  @Override
  final Context<E> visitAngle(Adapter<E> a, AngleUnit unit, double value) throws E {
    visitBeforeValueImpl(a);
    a.visitAngle(unit, value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitAngle(Adapter<E> a, AngleUnit unit, int value) throws E {
    visitBeforeValueImpl(a);
    a.visitAngle(unit, value);
    return toNextContext(a);
  }

  abstract void visitBeforeValueImpl(Adapter<E> a) throws E;

  @Override
  final Context<E> visitColor(Adapter<E> a, ColorName value) throws E {
    visitBeforeValueImpl(a);
    a.visitColor(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitColor(Adapter<E> a, String value) throws E {
    visitBeforeValueImpl(a);
    a.visitColor(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitDouble(Adapter<E> a, double value) throws E {
    visitBeforeValueImpl(a);
    a.visitDouble(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitFunctionStart(Adapter<E> a, StandardFunctionName name) throws E {
    visitBeforeValueImpl(a);
    a.visitFunctionStart(name);
    return toFunctionStart();
  }

  @Override
  final Context<E> visitInt(Adapter<E> a, int value) throws E {
    visitBeforeValueImpl(a);
    a.visitInt(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitKeyword(Adapter<E> a, StandardKeyword value) throws E {
    visitBeforeValueImpl(a);
    a.visitKeyword(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitKeyword(Adapter<E> a, String keyword) throws E {
    visitBeforeValueImpl(a);
    a.visitKeyword(keyword);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitLength(Adapter<E> a, LengthUnit unit, double value) throws E {
    visitBeforeValueImpl(a);
    a.visitLength(unit, value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitLength(Adapter<E> a, LengthUnit unit, int value) throws E {
    visitBeforeValueImpl(a);
    a.visitLength(unit, value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitPercentage(Adapter<E> a, double value) throws E {
    visitBeforeValueImpl(a);
    a.visitPercentage(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitPercentage(Adapter<E> a, int value) throws E {
    visitBeforeValueImpl(a);
    a.visitPercentage(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitRgb(Adapter<E> a, double r, double g, double b) throws E {
    visitBeforeValueImpl(a);
    a.visitRgb(r, g, b);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitRgb(Adapter<E> a, double r, double g, double b, double alpha) throws E {
    visitBeforeValueImpl(a);
    a.visitRgb(r, g, b, alpha);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitRgb(Adapter<E> a, int r, int g, int b) throws E {
    visitBeforeValueImpl(a);
    a.visitRgb(r, g, b);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitRgb(Adapter<E> a, int r, int g, int b, double alpha) throws E {
    visitBeforeValueImpl(a);
    a.visitRgb(r, g, b, alpha);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitRgba(Adapter<E> a, double r, double g, double b, double alpha) throws E {
    visitBeforeValueImpl(a);
    a.visitRgba(r, g, b, alpha);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitRgba(Adapter<E> a, int r, int g, int b, double alpha) throws E {
    visitBeforeValueImpl(a);
    a.visitRgba(r, g, b, alpha);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitString(Adapter<E> a, String value) throws E {
    visitBeforeValueImpl(a);
    a.visitString(value);
    return toNextContext(a);
  }

  @Override
  final Context<E> visitUri(Adapter<E> a, String value) throws E {
    visitBeforeValueImpl(a);
    a.visitUri(value);
    return toNextContext(a);
  }

}
