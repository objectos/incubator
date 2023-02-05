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

import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;

class ArgumentValueVisitor extends SimpleValueVisitor<Argument> {

  static final ArgumentValueVisitor INSTANCE = new ArgumentValueVisitor();

  @Override
  public final void addColor(ColorName color) {
    String name;
    name = color.getName();

    set(FrameworkTypes._Color.id(name));
  }

  @Override
  public final void addKeyword(StandardKeyword keyword) {
    set(FrameworkTypes._Keywords.id(keyword.getJavaName()));
  }

  @Override
  public final void addZero() {
    set(invoke("zero"));
  }

  @Override
  public final void createColor(String value) {
    set(invoke("hex", l(value)));
  }

  @Override
  public final void createDouble(double value) {
    set(invoke("l", l(value)));
  }

  @Override
  public final void createInt(int value) {
    set(invoke("l", l(value)));
  }

  @Override
  public final void createLength(LengthUnit unit, double value) {
    set(invoke(unit.getName(), l(value)));
  }

  @Override
  public final void createLength(LengthUnit unit, int value) {
    set(invoke(unit.getName(), l(value)));
  }

  @Override
  public final void createPercentage(double value) {
    set(invoke("pct", l(value)));
  }

  @Override
  public final void createPercentage(int value) {
    set(invoke("pct", l(value)));
  }

  @Override
  public final void createRgba(int r, int g, int b, double alpha) {
    set(invoke("rgba", l(r), l(g), l(b), l(alpha)));
  }

  @Override
  public final void createString(String value) {
    set(invoke("l", l(value)));
  }

}