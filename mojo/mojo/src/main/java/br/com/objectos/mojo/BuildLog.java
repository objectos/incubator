/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import objectos.util.UnmodifiableList;
import org.slf4j.event.Level;

final class BuildLog implements Log {

  private final UnmodifiableList<Message> messages;

  BuildLog(BuildLogger builder) {
    messages = builder.messages();
  }

  @Override
  public final boolean containsMessage(String test) {
    for (int i = 0; i < messages.size(); i++) {
      Message message;
      message = messages.get(i);

      if (message.contains(test)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public final String toString() {
    return messages.join("\n");
  }

  static class Event extends Message {
    private final Level level;

    Event(Level level, String value) {
      super(value);
      this.level = level;
    }

    @Override
    public final String toString() {
      StringBuilder s = new StringBuilder();
      s.append('[');
      s.append(level);
      s.append(']');
      s.append(' ');
      s.append(value);
      return s.toString();
    }
  }

  static class Message {

    final String value;

    Message(String value) {
      this.value = value;
    }

    public final boolean contains(String test) {
      return value.contains(test);
    }

    @Override
    public String toString() {
      return value;
    }

  }

}
