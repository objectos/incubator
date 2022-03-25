/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor.site;

import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.list.ImmutableList;

class AtDirectoryFake extends AtDirectory.Builder {

  NamedClass directoryName;
  Identifier identifier;
  ImmutableList<PathParameter> parameters;
  String path;
  NamedClass pathName;

  @Override
  final NamedClass getDirectoryName() {
    return directoryName;
  }

  @Override
  final Identifier getIdentifier() {
    return identifier;
  }

  @Override
  final ImmutableList<PathParameter> getParameters() {
    return parameters;
  }

  @Override
  final String getPath() {
    return path;
  }

  @Override
  final NamedClass getPathName() {
    return pathName;
  }

}