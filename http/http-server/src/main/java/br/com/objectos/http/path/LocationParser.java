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
package br.com.objectos.http.path;

import objectos.util.GrowableList;

class LocationParser {

  private final StringBuilder slug = new StringBuilder();

  private final GrowableList<Slug> slugs = new GrowableList<>();
  private State state = State.START;
  private final String string;

  LocationParser(String string) {
    this.string = string;
  }

  public final Location parse() {
    for (char c : string.toCharArray()) {
      state = state.consume(this, c);
    }

    state.onEof(this);

    return slugs.isEmpty() ? Location.root() : new Location(slugs.toUnmodifiableList());
  }

  protected Location buildStandard() {
    return null;
  }

  final void add(char c) {
    slug.append(c);
  }

  final IllegalArgumentException newException() {
    return new IllegalArgumentException(string + " is not a valid location");
  }

  final void slugEnd() {
    slugs.add(Slug.fixed(slug.toString()));
    slug.setLength(0);
  }

  private enum State {

    SLASH {
      @Override
      final State consume(LocationParser outer, char c) {
        if (isInvalid(c)) {
          throw outer.newException();
        }

        outer.add(c);
        return SLUG;
      }

      @Override
      final void onEof(LocationParser outer) {

      }
    },

    SLUG {
      @Override
      final State consume(LocationParser outer, char c) {
        if (isForwardSlash(c)) {
          outer.slugEnd();
          return SLASH;
        }

        if (isInvalid(c)) {
          throw outer.newException();
        }

        outer.add(c);
        return this;
      }

      @Override
      final void onEof(LocationParser outer) {
        outer.slugEnd();
      }
    },

    START {
      @Override
      final State consume(LocationParser outer, char c) {
        if (Character.isWhitespace(c)) {
          return this;
        }

        if (isForwardSlash(c)) {
          return SLASH;
        }

        throw outer.newException();
      }

      @Override
      final void onEof(LocationParser outer) {
        throw outer.newException();
      }
    };

    abstract State consume(LocationParser outer, char c);

    final boolean isForwardSlash(char c) {
      return c == '/';
    }

    final boolean isInvalid(char c) {
      return Character.isISOControl(c);
    }

    abstract void onEof(LocationParser outer);

  }

}
