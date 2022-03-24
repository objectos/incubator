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

import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

class NameEnvironment implements INameEnvironment {

  @Override
  public NameEnvironmentAnswer findType(char[][] compoundTypeName) {
    QualifiedName qname = QualifiedName.of(compoundTypeName);
    return qname.findType();
  }

  @Override
  public NameEnvironmentAnswer findType(char[] typeName, char[][] packageName) {
    return findType0(typeName, packageName);
  }

  @Override
  public boolean isPackage(char[][] parentPackageName, char[] packageName) {
    PackageName name = PackageName.of(parentPackageName).append(packageName);
    return name.isPackage();
  }

  @Override
  public void cleanup() {
  }

  private NameEnvironmentAnswer findType0(char[] typeName, char[][] packageName) {
    QualifiedName qname = PackageName.of(packageName).toQualifiedName(typeName);
    return qname.findType();
  }

}