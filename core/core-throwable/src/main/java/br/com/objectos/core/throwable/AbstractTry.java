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
package br.com.objectos.core.throwable;

import br.com.objectos.core.object.Checks;
import br.com.objectos.latest.Concrete;

/**
 * Provides a try-with-resources equivalent functionality for codebases that
 * must support Java 6. For example, the following Java 6 code:
 *
 * <pre>
 * public void consumeResource() throws IOException {
 *   Closeable resource;
 *   resource = openResource();
 *
 *   Throwable rethrow;
 *   rethrow = Try.begin();
 *
 *   try {
 *     // use resource
 *   } catch (Throwable e) {
 *     rethrow = e;
 *   } finally {
 *     rethrow = Try.close(rethrow, resource);
 *   }
 *
 *   Try.rethrowIfPossible(rethrow, IOException.class);
 * }</pre>
 *
 * <p>
 * Is equivalent to the following Java 7+ code:
 *
 * <pre>
 * public void consumeResource() throws IOException {
 *   try (AutoCloseable resource = openResource()) {
 *     // use resource
 *   }
 * }</pre>
 *
 * <p>
 * Please note that this class should only be used if you MUST
 * support Java 6. You should use the language provided try-with-resources
 * construct otherwise.
 *
 * @since 2
 */
@Concrete(modifiers = "public final", simpleName = "Try")
abstract class AbstractTry {

  AbstractTry() {}

  /**
   * Returns a {@code null} throwable reference.
   *
   * <p>
   * Intended to be called as the first statement of a try block.
   *
   * @return a {@code null} throwable
   */
  public static Throwable begin() {
    return null;
  }

  /**
   * Throws the specified {@code throwable} as the specified {@code type} if it
   * is possible to do so. More formally:
   *
   * <p>
   * If {@code throwable} is {@code null} then no action is taken.
   *
   * <p>
   * If {@code throwable} is an instance of {@code type}, i.e., if the
   * expression {@code type.isInstance(rethrow)} evaluates to {@code true}, then
   * {@code throwable} is cast to {@code type} and thrown as it is.
   *
   * <p>
   * In all other cases, a new {@link RethrowException} is thrown having the
   * specified {@code throwable} as its cause.
   *
   * @param <X> will be thrown as this {@code Throwable} type
   * @param throwable
   *        a possibly null {@code Throwable} instance to be rethrown if
   *        possible
   * @param type
   *        the type which to throw the {@code throwable} as
   *
   * @throws X
   *         if {@code throwable} is an instance of {@code type} and is
   *         non-null
   * @throws RethrowException
   *         if {@code throwable} is not an instance of {@code type} and is
   *         non-null
   */
  public static <X extends Throwable> void rethrowIfPossible(
      Throwable throwable, Class<X> type)
      throws X {
    Checks.checkNotNull(type, "type == null");

    if (throwable == null) {
      return;
    }

    if (type.isInstance(throwable)) {
      throw type.cast(throwable);
    }

    if (throwable instanceof RuntimeException) {
      throw (RuntimeException) throwable;
    }

    if (throwable instanceof Error) {
      throw (Error) throwable;
    }

    throw new RethrowException("Not of type " + type, throwable);
  }

}