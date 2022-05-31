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
package br.com.objectos.lexer.impl.ah;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import objectos.lang.Check;

class ValueList {

  private final Deque<List<Object>> stack = new ArrayDeque<>();

  ValueList() {}

  public final void add(Object value) {
    list().add(value);
  }

  public final Object get() {
    Check.state(stack.size() == 1, "stacks != 1");
    List<Object> list = stack.pop();
    Check.state(list.size() == 1, "list != 1");
    return list.get(0);
  }

  public final Iterator<Object> iterator() {
    return list().iterator();
  }

  public final Object peekLast() {
    Object last = null;

    List<Object> list = list();
    if (!list.isEmpty()) {
      last = list.get(list.size() - 1);
    }

    return last;
  }

  public final void pop() {
    stack.pop();
  }

  public final void push() {
    stack.push(new ArrayList<>());
  }

  public final void set(Object value) {
    stack.pop();
    list().add(value);
  }

  public final int size() {
    return list().size();
  }

  public final List<Object> toList() {
    return list();
  }

  private List<Object> list() {
    if (stack.isEmpty()) {
      push();
    }
    return stack.peek();
  }

}