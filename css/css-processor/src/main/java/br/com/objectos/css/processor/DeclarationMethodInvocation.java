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
package br.com.objectos.css.processor;

import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;
import objectos.util.MutableList;

abstract class DeclarationMethodInvocation {

  final StandardPropertyName property;

  DeclarationMethodInvocation(StandardPropertyName property) {
    this.property = property;
  }

  public static DeclarationMethodInvocation getSimple(StandardPropertyName name) {
    return new Simple(name);
  }

  public abstract MethodInvocation build();

  abstract void add(Argument argument);

  final void addColor(ColorName value) {
    add(id(value.getJavaName()));
  }

  final void addColor(String value) {
    add(invoke("hex", l(value)));
  }

  final void addKeyword(StandardKeyword value) {
    add(id(value.getJavaName()));
  }

  final void addKeyword(String keyword) {
    add(invoke("keyword", l(keyword)));
  }

  final void addLength(LengthUnit unit, double value) {
    add(invoke(unit.getName(), l(value)));
  }

  final void addLength(LengthUnit unit, int value) {
    add(invoke(unit.getName(), l(value)));
  }

  abstract DeclarationMethodInvocation addMultiDeclarationSeparator();

  final void addPercentage(double value) {
    add(invoke("pct", l(value)));
  }

  final void addPercentage(int value) {
    add(invoke("pct", l(value)));
  }

  final void addRgb(double r, double g, double b) {
    add(invoke("rgb", l(r), l(g), l(b)));
  }

  final void addRgb(double r, double g, double b, double alpha) {
    add(invoke("rgb", l(r), l(g), l(b), l(alpha)));
  }

  final void addRgb(int r, int g, int b) {
    add(invoke("rgb", l(r), l(g), l(b)));
  }

  final void addRgb(int r, int g, int b, double alpha) {
    add(invoke("rgb", l(r), l(g), l(b), l(alpha)));
  }

  final void addRgba(double r, double g, double b, double alpha) {
    add(invoke("rgba", l(r), l(g), l(b), l(alpha)));
  }

  final void addRgba(int r, int g, int b, double alpha) {
    add(invoke("rgba", l(r), l(g), l(b), l(alpha)));
  }

  final void addString(String value) {
    add(invoke("l", l(value)));
  }

  final void addValue(double value) {
    add(l(value));
  }

  final void addValue(int value) {
    if (value == 0) {
      add(invoke("zero"));
    } else {
      add(l(value));
    }
  }

  final String getMethodName() {
    return property.getJavaName();
  }

  private static class Multi extends DeclarationMethodInvocation {

    private Simple current;

    private final MutableList<Simple> elements = MutableList.create();

    Multi(StandardPropertyName property, Simple first) {
      super(property);
      elements.add(first);
      setCurrent();
    }

    @Override
    public final MethodInvocation build() {
      elements.add(current);

      MutableList<ArgumentsElement> arguments;
      arguments = MutableList.create();

      for (int i = 0; i < elements.size(); i++) {
        arguments.add(nl());

        Simple element;
        element = elements.get(i);

        arguments.add(element.build());
      }

      arguments.add(nl());

      return invoke(
        getMethodName(),
        arguments
      );
    }

    @Override
    final void add(Argument argument) {
      current.add(argument);
    }

    @Override
    final DeclarationMethodInvocation addMultiDeclarationSeparator() {
      elements.add(current);
      setCurrent();
      return this;
    }

    private void setCurrent() {
      current = new Simple(property);
    }

  }

  private static class Simple extends DeclarationMethodInvocation {

    private final MutableList<ArgumentsElement> arguments = MutableList.create();

    Simple(StandardPropertyName property) {
      super(property);
    }

    @Override
    public final MethodInvocation build() {
      return invoke(getMethodName(), arguments);
    }

    @Override
    final void add(Argument argument) {
      arguments.add(argument);
    }

    @Override
    final DeclarationMethodInvocation addMultiDeclarationSeparator() {
      return new Multi(property, this);
    }

  }

}
