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
import br.com.objectos.logging.Event1;
import br.com.objectos.logging.Events;
import br.com.objectos.logging.Logger;
import br.com.objectos.logging.NoopLogger;

@Bridge
class ThrowablesJava6 extends AbstractThrowables {

  static Logger LOGGER = NoopLogger.getInstance();

  private static final Throwable[] EMPTY = new Throwable[0];

  private static final Event1<Throwable> SUPPRESSED;

  static {
    Class<?> s;
    s = Throwables.class;

    SUPPRESSED = Events.warn(s, "SUPPRESSED", Throwable.class);
  }

  @Constructor
  ThrowablesJava6() {}

  /**
   * If the first exception is non-null logs the second one as a suppressed
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
      LOGGER.log(SUPPRESSED, suppressed);

      return exception;
    } else {
      return suppressed;
    }
  }

  /**
   * Returns a zero-length {@code Throwable} array.
   *
   * <p>
   * Note: suppressed exceptions were only introduced in Java 7.
   *
   * @param throwable
   *        a throwable instance
   *
   * @return a zero-length {@code Throwable} array.
   */
  public static Throwable[] getSuppressed(Throwable throwable) {
    Checks.checkNotNull(throwable, "throwable == null");

    return EMPTY;
  }

  /**
   * Sets the logger instance to use for logging suppressed exceptions.
   *
   * @param logger
   *        a logger instance
   */
  public static void setLogger(Logger logger) {
    LOGGER = LOGGER.replace(logger);
  }

}