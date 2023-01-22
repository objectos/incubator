/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot.spec;

import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.html.boot.attribute.AttributeNames.StandardAttributeName;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.html.boot.attribute.AttributeNames;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import objectos.util.UnmodifiableSet;

public abstract class AttributeSpec {

  final Set<NamedClass> interfaceSet = new TreeSet<>();

  private final NamedClass className;

  private final Identifier constantName;

  private final Set<AttributeKind> kindSet = new TreeSet<>();

  private final String name;

  private final Set<String> nameSet = new LinkedHashSet<>();

  AttributeSpec(String name) {
    this.name = name;
    this.className = StandardAttributeName.nestedClass(JavaNames.toValidClassName(name));
    constantName = id(JavaNames.toIdentifier(name.toUpperCase()));
  }

  static AttributeSpec global(String name) {
    return new GlobalAttributeSpec(name);
  }

  public final void addKind(AttributeKind kind) {
    kindSet.add(kind);
  }

  public final void as(String... names) {
    for (String name : names) {
      nameSet.add(name);
    }
  }

  public final NamedClass className() {
    return className;
  }

  public final Identifier constantName() {
    return constantName;
  }

  public final Set<NamedClass> interfaceSet() {
    return interfaceSet;
  }

  public final AttributeKind kind() {
    Set<AttributeKind> s;
    s = kindSet();

    if (s.size() == 1) {
      Iterator<AttributeKind> it;
      it = s.iterator();

      return it.next();
    } else {
      return AttributeKind.STRING;
    }
  }

  public final Set<AttributeKind> kindSet() {
    return kindSet;
  }

  public final Iterable<String> methodNameStream() {
    return nameSet.isEmpty()
        ? UnmodifiableSet.of(methodName(name))
        : nameSet;
  }

  public final String name() {
    return name;
  }

  public final String simpleName() {
    return JavaNames.toValidClassName(name);
  }

  abstract ElementAttributeSpec toElementAttributeSpec(ElementSpec parent);

  private String methodName(String value) {
    return JavaNames.toValidMethodName(value);
  }

  private static class GlobalAttributeSpec extends AttributeSpec {

    GlobalAttributeSpec(String name) {
      super(name);
      interfaceSet.add(AttributeNames.GlobalAttributeName);
    }

    @Override
    final ElementAttributeSpec toElementAttributeSpec(ElementSpec parent) {
      throw new IllegalArgumentException(name() + " attribute was already defined as global!");
    }

  }

}