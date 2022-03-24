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

import java.util.Map;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;

enum JdtSourceVersion {

  RELEASE_5("1.5"),

  RELEASE_6("1.6"),

  RELEASE_7("1.7"),

  RELEASE_8("1.8"),

  RELEASE_11("11"),

  RELEASE_15("15"),
  
  RELEASE_16("16"),
  
  RELEASE_17("17");

  private final String version;

  private JdtSourceVersion(String version) {
    this.version = version;
  }

  public void set(Map<String, String> optionMap) {
    optionMap.put(CompilerOptions.OPTION_TargetPlatform, version);
    optionMap.put(CompilerOptions.OPTION_Compliance, version);
    optionMap.put(CompilerOptions.OPTION_Source, version);
  }

}