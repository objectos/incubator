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
package br.com.objectos.css.parser.sheet;

import java.util.Iterator;
import java.util.List;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class Name {

  private final ImmutableList<String> partList;

  private Name(ImmutableList<String> partList) {
    this.partList = partList;
  }

  static Name copyOf(List<String> nameList) {
    return new Name(ImmutableList.copyOf(nameList));
  }

  static Name fromString(String name) {
    MutableList<String> list = new MutableList<>();
    StringBuilder part = new StringBuilder();
    char[] chars = name.toCharArray();
    for (char c : chars) {
      if (c == '.') {
        list.add(part.toString());
        part = new StringBuilder();
      } else {
        part.append(c);
      }
    }
    list.add(part.toString());
    return new Name(list.toImmutableList());
  }

  String interpolate() {
    StringBuilder s = new StringBuilder();

    Iterator<String> it = partList.iterator();
    if (it.hasNext()) {
      s.append(it.next());
      while (it.hasNext()) {
        s.append('.').append(it.next()).append("()");
      }
    }

    return s.toString();
  }

  String toString(String prefix) {
    StringBuilder sb = new StringBuilder();

    if (!partList.isEmpty()) {
      sb.append(prefix);

      sb.append(partList.get(0));

      for (int i = 1; i < partList.size(); i++) {
        sb.append(" . ");

        sb.append(prefix);

        sb.append(partList.get(i));
      }
    }

    return sb.toString();
  }

}