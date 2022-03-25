/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.io;

import java.io.IOException;

class Indentation {

  private final char c;
  private final int n;

  private int level = 0;

  private Indentation(char c, int n) {
    this.c = c;
    this.n = n;
  }

  public static Indentation standard() {
    return new Indentation(' ', 2);
  }

  public void decrement() {
    level--;
  }

  public void increment() {
    level++;
  }

  public void write(CssWriter writer) throws IOException {
    int count = level * n;
    for (int i = 0; i < count; i++) {
      writer.write(c);
    }
  }

}