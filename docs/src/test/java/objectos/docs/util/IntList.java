/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.util;

import objectos.util.IntArrays;

public class IntList {
  private int[] data = IntArrays.empty();

  private int cursor = 0;

  public boolean add(int value) {
    data = IntArrays.growIfNecessary(data, cursor);
    data[cursor++] = value;
    return true;
  }

  public int get(int index) {
    return data[index];
  }
}