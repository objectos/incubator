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
import br.com.objectos.latest.Concrete.Bridge;
import br.com.objectos.latest.Concrete.Constructor;
import objectos.logging.Logger;

@Bridge
class ThrowablesJava7 extends AbstractThrowables {

  @Constructor
  ThrowablesJava7() {}

  /**
   * Adds to the first exception the second one as a suppressed
   * exception and returns the first exception. If the first exception is
   * {@code null} then the second exception is returned unchanged.
   *
   * @param exception
   *        the (maybe null) exception
   * @param suppressed
   *        the suppresed exception
   *
   * @return {@code exception} if it not null, {@code suppressed} otherwise
   */
  public static Throwable addSuppressed(Throwable exception, Throwable suppressed) {
    Checks.checkNotNull(suppressed, "suppressed == null");

    if (exception != null) {
      exception.addSuppressed(suppressed);

      return exception;
    } else {
      return suppressed;
    }
  }

  /**
   * Returns the result of evaluating {@code throwable.getSuppressed()}.
   *
   * @param throwable
   *        a throwable instance
   *
   * @return the result of evaluating {@code throwable.getSuppressed()}.
   */
  public static Throwable[] getSuppressed(Throwable throwable) {
    Checks.checkNotNull(throwable, "throwable == null");

    return throwable.getSuppressed();
  }

  /**
   * This is a no-op.
   *
   * <p>
   * For Java 6 builds, suppressed throwables are logged instead of added to
   * another throwable instance. That is not the case of this build. Hence the
   * no-op.
   *
   * @param logger
   *        a logger instance
   */
  public static void setLogger(Logger logger) {
    Checks.checkNotNull(logger, "logger == null");
  }

}