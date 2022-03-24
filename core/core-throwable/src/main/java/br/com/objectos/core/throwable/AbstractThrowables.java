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

import br.com.objectos.latest.Concrete;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Provides {@code static} utility methods for working with {@link Throwable}
 * instances. In particular, provides a single API for working with
 * {@link Throwable} instances in a Java multi-release manner.
 *
 * @since 2
 */
@Concrete(modifiers = "public final", simpleName = "Throwables")
abstract class AbstractThrowables {

  AbstractThrowables() {}

  /**
   * Returns a new string whose contents is what would be sent to the standard
   * error stream as a result of invoking {@link Throwable#printStackTrace()}.
   *
   * @param throwable
   *        a throwable instance to analyze
   *
   * @return a string containing the throwable and its backtrace
   */
  public static String printStackTraceToString(Throwable throwable) {
    StringWriter output;
    output = new StringWriter();

    PrintWriter printWriter;
    printWriter = new PrintWriter(output);

    throwable.printStackTrace(printWriter);

    return output.toString();
  }

}