/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
 * Defines the Objectos Concurrent API.
 */
module br.com.objectos.concurrent {
  exports br.com.objectos.concurrent;

  requires transitive br.com.objectos.core.service;
  requires transitive br.com.objectos.logging;

  requires br.com.objectos.core.array;
  requires br.com.objectos.core.list;
  requires br.com.objectos.core.object;
  requires br.com.objectos.core.throwable;
}