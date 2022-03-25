/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.parser.impl.rd;

class StateWriter {

  private final StringBuilder sb = new StringBuilder();

  @Override
  public final String toString() {
    return sb.toString();
  }

  public final StateWriter writeChar(char c) {
    sb.append(c);
    return this;
  }

  public final StateWriter writeInt(int i) {
    sb.append(i);
    return this;
  }

  public final StateWriter writeNewLine() {
    sb.append('\n');
    return this;
  }

  public final StateWriter writeObject(Object o) {
    sb.append(o);
    return this;
  }

  public final StateWriter writeString(String s) {
    sb.append(s);
    return this;
  }

}