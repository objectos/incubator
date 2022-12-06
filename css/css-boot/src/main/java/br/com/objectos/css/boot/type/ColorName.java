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
package br.com.objectos.css.boot.type;

import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.Callee;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import objectos.lang.Check;

public class ColorName implements Comparable<ColorName> {

  public final Identifier identifier;
  public final String name;

  private ColorName(String name, Identifier identifier) {
    this.name = name;
    this.identifier = identifier;
  }

  public static ColorName of(String name) {
    Check.notNull(name, "name == null");
    return new ColorName(
      name,
      id(JavaNames.toIdentifier(name))
    );
  }

  public final void assertIdentifierIsEqualToName() {
    String javaName;
    javaName = identifier.name();

    if (!name.equals(javaName)) {
      throw new AssertionError("Not equal: " + name + " != " + javaName);
    }
  }

  @Override
  public final int compareTo(ColorName o) {
    return name.compareTo(o.name);
  }

  final MethodInvocation invokePut(Callee callee) {
    return invoke(callee, "put", l(name), identifier);
  }

}
