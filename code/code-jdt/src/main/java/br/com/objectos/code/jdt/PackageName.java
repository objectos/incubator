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
package br.com.objectos.code.jdt;

import objectos.util.UnmodifiableList;
import objectos.util.MutableList;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

abstract class PackageName {

  private static final PackageName NULL = new NullPackageName();

  PackageName() {}

  public static PackageName nullValue() {
    return NULL;
  }

  public static PackageName of(char[][] packageName) {
    if (packageName == null) {
      return NULL;
    }

    MutableList<String> nameList = new MutableList<>();

    for (char[] cs : packageName) {
      nameList.add(new String(cs));
    }

    return new StandardPackageName(nameList.toUnmodifiableList());
  }

  public static PackageName ofString(String packageName) {
    String[] parts = packageName.split("\\.");
    return new StandardPackageName(UnmodifiableList.copyOf(parts));
  }

  public abstract PackageName append(char[] name);

  public boolean isPackage() {
    return false;
  }

  public abstract String name();

  public char[][] toCharArray() {
    return new char[][] {};
  }

  public QualifiedName toQualifiedName(char[] name) {
    return new QualifiedName(this, new String(name));
  }

  @Override
  public abstract String toString();

  NameEnvironmentAnswer findType(String simpleName) {
    return null;
  }

}