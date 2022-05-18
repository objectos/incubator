/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
/**
 * Provides the Objectos Git implementation.
 */
module br.com.objectos.git {
  exports br.com.objectos.git;

  requires transitive br.com.objectos.concurrent;
  requires transitive br.com.objectos.core.list;
  requires transitive objectos.lang;
  requires transitive br.com.objectos.fs;

  requires br.com.objectos.core.array;
  requires br.com.objectos.core.map;
  requires br.com.objectos.core.set;
  requires br.com.objectos.core.system;
}