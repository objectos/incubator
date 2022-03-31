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
package br.com.objectos.css;

import br.com.objectos.core.object.Checks;
import br.com.objectos.core.set.MutableSet;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.select.SelectorFactory;
import java.util.Locale;
import java.util.Set;

final class RandomIdSelectorGenerator {

  private static final int MAX_TRIES = 20;

  private static final Set<String> NAMES = MutableSet.create();

  private RandomIdSelectorGenerator() {}

  public static IdSelector randomHash(int length) {
    return randomIdSelector(length);
  }

  public static IdSelector randomIdSelector(int length) {
    Checks.checkArgument(length > 0, "length must be > 0");

    for (int i = 0; i < MAX_TRIES; i++) {
      String className;
      className = RandomStringImpl.next(length);

      char first;
      first = className.charAt(0);

      if (Character.isDigit(first)) {
        continue;
      }

      className = className.toLowerCase(Locale.US);

      if (NAMES.add(className)) {
        return SelectorFactory.id(className);
      }
    }

    throw new IllegalArgumentException(
        "Could not generate distinct IdSelector after " + MAX_TRIES + " tries");
  }

}