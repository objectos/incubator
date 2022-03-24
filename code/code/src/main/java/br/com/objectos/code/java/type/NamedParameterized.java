/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.type;

import br.com.objectos.code.java.expression.Expressions;
import br.com.objectos.code.java.expression.MethodReference;
import br.com.objectos.code.java.expression.TypeWitness;
import br.com.objectos.code.java.io.JavaFileImportSet;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.core.object.HashCode;

public final class NamedParameterized extends NamedClassOrParameterized {

  private final ImmutableList<? extends NamedType> arguments;
  private final NamedClass raw;

  NamedParameterized(NamedClass raw, ImmutableList<? extends NamedType> arguments) {
    this.raw = raw;
    this.arguments = arguments;

    Checks.checkArgument(!arguments.isEmpty(), "arguments is empty");
  }

  public static NamedParameterized of(
      NamedClass raw, Iterable<? extends NamedType> arguments) {
    Checks.checkNotNull(raw, "raw == null");
    Checks.checkNotNull(arguments, "arguments == null");

    ImmutableList<? extends NamedType> list;
    list = ImmutableList.copyOf(arguments);

    return new NamedParameterized(raw, list);
  }

  public static NamedParameterized of(NamedClass raw, NamedType arg) {
    Checks.checkNotNull(raw, "raw == null");

    ImmutableList<NamedType> list;
    list = ImmutableList.of(arg);

    return new NamedParameterized(raw, list);
  }

  public static NamedParameterized of(NamedClass raw, NamedType... arguments) {
    Checks.checkNotNull(raw, "raw == null");

    ImmutableList<NamedType> list;
    list = ImmutableList.copyOf(arguments);

    return new NamedParameterized(raw, list);
  }

  public static NamedParameterized of(
      NamedClass raw, NamedType arg1, NamedType arg2) {
    Checks.checkNotNull(raw, "raw == null");

    ImmutableList<NamedType> list;
    list = ImmutableList.of(arg1, arg2);

    return new NamedParameterized(raw, list);
  }

  public static NamedParameterized of(
      NamedClass raw, NamedType arg1, NamedType arg2, NamedType arg3) {
    Checks.checkNotNull(raw, "raw == null");

    ImmutableList<NamedType> list;
    list = ImmutableList.of(arg1, arg2, arg3);

    return new NamedParameterized(raw, list);
  }

  @Override
  public final String acceptJavaFileImportSet(JavaFileImportSet set) {
    StringBuilder s;
    s = new StringBuilder(raw.acceptJavaFileImportSet(set));

    s.append('<');

    NamedType first;
    first = arguments.get(0);

    s.append(first.acceptJavaFileImportSet(set));

    for (int i = 1; i < arguments.size(); i++) {
      s.append(", ");

      NamedType next;
      next = arguments.get(i);

      s.append(next.acceptJavaFileImportSet(set));
    }

    s.append('>');

    return s.toString();
  }

  @Override
  public final <R, P> R acceptTypeNameVisitor(NamedTypeVisitor<R, P> visitor, P p) {
    return visitor.visitNamedParameterized(this, p);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof NamedParameterized)) {
      return false;
    }
    NamedParameterized that = (NamedParameterized) obj;
    return raw.equals(that.raw)
        && arguments.equals(that.arguments);
  }

  @Override
  public final NamedType getArrayCreationExpressionName() {
    return raw;
  }

  @Override
  public final int hashCode() {
    return HashCode.hashCode(raw, arguments);
  }

  @Override
  public final MethodReference ref(String methodName) {
    return Expressions.ref(this, methodName);
  }

  @Override
  public final MethodReference ref(TypeWitness witness, String methodName) {
    return Expressions.ref(this, witness, methodName);
  }

  @Override
  public final String toString() {
    return raw + arguments.join(", ", "<", ">");
  }

}