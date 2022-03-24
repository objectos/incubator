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

import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

class QualifiedName {

  private final PackageName packageName;
  private final String simpleName;
  private final String name;

  public QualifiedName(PackageName packageName, String simpleName) {
    this.packageName = packageName;
    this.simpleName = simpleName;
    name = packageName.name() + "." + simpleName;
  }

  public static QualifiedName of(char[][] compoundTypeName) {
    switch (compoundTypeName.length) {
    case 0:
      return new QualifiedName(PackageName.nullValue(), new String(compoundTypeName[0]));
    default:
      int packageLength = compoundTypeName.length - 1;
      char[][] packageName = new char[compoundTypeName.length - 1][];
      System.arraycopy(compoundTypeName, 0, packageName, 0, packageLength);
      return PackageName.of(packageName).toQualifiedName(compoundTypeName[packageLength]);
    }
  }

  @Override
  public String toString() {
    return name;
  }

  NameEnvironmentAnswer findType() {
    return packageName.findType(simpleName);
  }

}