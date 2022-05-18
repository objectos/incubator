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
package br.com.objectos.logger;

import objectos.lang.Level;

/**
 * A logged event.
 *
 * @since 2
 */
public abstract class Log {

  String key;

  Level level;

  String source;

  String thread;

  long timestamp;

  String formatValue(int index) {
    throw illegalIndexException(index);
  }

  Object getValue(int index) {
    throw illegalIndexException(index);
  }

  final IllegalArgumentException illegalIndexException(int index) {
    throw new IllegalArgumentException("Invalid index=" + index);
  }

  void printStackTrace(StringBuilder out) {}

  abstract int size();

}