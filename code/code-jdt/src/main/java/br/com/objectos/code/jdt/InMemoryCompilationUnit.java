/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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

class InMemoryCompilationUnit extends AbstractCompilationUnit {

  private final String fileName;
  private final String packageName;
  private final String mainTypeName;
  private final String contents;

  public InMemoryCompilationUnit(String fileName,
                                 String packageName,
                                 String mainTypeName,
                                 String contents) {
    this.fileName = fileName;
    this.packageName = packageName;
    this.mainTypeName = mainTypeName;
    this.contents = contents;
  }

  @Override
  public boolean ignoreOptionalProblems() {
    return false;
  }

  @Override
  String contents() {
    return contents;
  }

  @Override
  String fileName() {
    return fileName;
  }

  @Override
  String mainTypeName() {
    return mainTypeName;
  }

  @Override
  String packageName() {
    return packageName;
  }

}