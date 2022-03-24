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

class FakeCompilationUnit {

  public static final ICompilationUnit HelloWorld = CompilationUnits.resourceBuilder()
      .resourceName("/code/HelloWorld.java.txt")
      .packageName("br.com.objectos.code.fake")
      .mainTypeName("HelloWorld")
      .build();

  public static final ICompilationUnit IsSubType = CompilationUnits.resourceBuilder()
      .resourceName("/code/IsSubType.java.txt")
      .packageName("br.com.objectos.code.fake")
      .mainTypeName("IsSubType")
      .build();

  public static final ICompilationUnit MethodOrder = CompilationUnits.resourceBuilder()
      .resourceName("/code/MethodOrder.java.txt")
      .packageName("br.com.objectos.code.fake")
      .mainTypeName("MethodOrder")
      .build();

  public static final ICompilationUnit TypeMarker = CompilationUnits.resourceBuilder()
      .resourceName("/code/TypeMarker.java.txt")
      .packageName("br.com.objectos.code.fake")
      .mainTypeName("TypeMarker")
      .build();

  private FakeCompilationUnit() {
  }

}