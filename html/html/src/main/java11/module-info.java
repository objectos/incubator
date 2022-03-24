/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
 * Provides the Objectos HTML API.
 */
module br.com.objectos.html {
  exports br.com.objectos.html;
  exports br.com.objectos.html.attribute;
  exports br.com.objectos.html.element;
  exports br.com.objectos.html.tmpl;
  exports br.com.objectos.html.writer;

  requires transitive br.com.objectos.html.spi;

  requires br.com.objectos.code.annotations;
  requires br.com.objectos.core.array;
  requires br.com.objectos.core.collection;
  requires br.com.objectos.core.list;
  requires br.com.objectos.core.map;
  requires br.com.objectos.core.object;
}