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

import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

abstract class AbstractCompilationUnit
    extends AbstractCompilationUnitJava6
    implements ICompilationUnit {

  @Override
  public final char[] getContents() {
    return contents().toCharArray();
  }

  @Override
  public final char[] getFileName() {
    return fileName().toCharArray();
  }

  @Override
  public final char[] getMainTypeName() {
    return mainTypeName().toCharArray();
  }

  @Override
  public final char[][] getPackageName() {
    return PackageName.ofString(packageName()).toCharArray();
  }

  abstract String contents();

  abstract String fileName();

  abstract String mainTypeName();

  abstract String packageName();

}