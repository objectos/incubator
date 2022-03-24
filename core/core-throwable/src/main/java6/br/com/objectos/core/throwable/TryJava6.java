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

import br.com.objectos.latest.Concrete.Bridge;
import br.com.objectos.latest.Concrete.Constructor;
import java.io.Closeable;
import java.util.zip.ZipFile;

@Bridge
class TryJava6 extends AbstractTry {

  @Constructor
  TryJava6() {}

  /**
   * Closes the specified {@code closeable} (if possible) and logs any
   * suppressed exception. More formally:
   *
   * <p>
   * If {@code closeable} is null, then no action is performed.
   *
   * <p>
   * If the close operation completes normally, then {@code primary} value is
   * returned.
   *
   * <p>
   * If the close operation completes abruptly because of a Throwable {@code e},
   * then the result of invoking {@code Throwables.addSuppressed(primary, e)} is
   * returned.
   *
   * @param primary
   *        a possibly null primary throwable
   * @param closeable
   *        a possible null closeable that needs to be closed
   *
   * @return the {@code primary} throwable if it is not null, the caught
   *         throwable (if any) or {@code null} otherwise
   */
  public static Throwable close(Throwable primary, Closeable closeable) {
    Throwable result;
    result = primary;

    if (closeable != null) {
      try {
        closeable.close();
      } catch (Throwable e) {
        result = ThrowablesJava6.addSuppressed(result, e);
      }
    }

    return result;
  }

  /**
   * Closes the specified {@code file} (if possible) and logs any
   * suppressed exception. More formally:
   *
   * <p>
   * If {@code file} is null, then no action is performed.
   *
   * <p>
   * If the close operation completes normally, then {@code primary} value is
   * returned.
   *
   * <p>
   * If the close operation completes abruptly because of a Throwable {@code e},
   * then the result of invoking {@code Throwables.addSuppressed(primary, e)} is
   * returned.
   *
   * @param primary
   *        a possibly null primary throwable
   * @param file
   *        a possible null zip file that needs to be closed
   *
   * @return the {@code primary} throwable if it is not null, the caught
   *         throwable (if any) or {@code null} otherwise
   */
  public static Throwable close(Throwable primary, ZipFile file) {
    Throwable result;
    result = primary;

    if (file != null) {
      try {
        file.close();
      } catch (Throwable e) {
        result = ThrowablesJava6.addSuppressed(result, e);
      }
    }

    return result;
  }

}