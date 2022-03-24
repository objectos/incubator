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

import br.com.objectos.core.list.ImmutableList;

class NullPackageName extends PackageName {

  @Override
  public PackageName append(char[] name) {
    String theName = new String(name);

    ImmutableList<String> list = ImmutableList.of(theName);

    return new StandardPackageName(list);
  }

  @Override
  public String name() {
    return "";
  }

  @Override
  public String toString() {
    return "";
  }

}