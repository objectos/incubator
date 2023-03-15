/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
 *
 * Based on Tailwind CSS
 *
 * MIT License
 *
 * Copyright (c) Tailwind Labs, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * Footer
 */
package br.com.objectos.css.framework.util;

import br.com.objectos.css.select.ClassSelector;
import objectos.html.spi.Marker;
import objectos.html.spi.Renderer;
import objectos.html.tmpl.AnyElementValue;
import objectos.lang.Equals;
import objectos.util.UnmodifiableSet;

public class ClassSet implements AnyElementValue {

  private static final ClassSet EMPTY = new ClassSet(
    UnmodifiableSet.of()
  );

  private final UnmodifiableSet<ClassSelector> values;

  private ClassSet(UnmodifiableSet<ClassSelector> values) {
    this.values = values;
  }

  public static ClassSet of(ClassSelector... values) {
    return switch (values.length) {
      case 0 -> EMPTY;

      default -> new ClassSet(
        UnmodifiableSet.copyOf(values)
      );
    };
  }

  @Override
  public final boolean equals(Object obj) {
    return obj == this || obj instanceof ClassSet that
        && Equals.of(values, that.values);
  }

  @Override
  public final int hashCode() {
    return values.hashCode();
  }

  @Override
  public final void mark(Marker marker) {
    if (values.isEmpty()) {
      return;
    }

    for (var value : values) {
      value.mark(marker);
    }
  }

  @Override
  public final void render(Renderer renderer) {
    if (values.isEmpty()) {
      renderer.addNoOp();

      return;
    }

    for (var value : values) {
      value.render(renderer);
    }
  }

}
