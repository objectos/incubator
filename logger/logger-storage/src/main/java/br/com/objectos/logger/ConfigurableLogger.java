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
package br.com.objectos.logger;

import java.io.Closeable;
import objectos.logging.Level;
import objectos.logging.Logger;

/**
 * A {@link Logger} that allows for setting its level.
 *
 * @since 2
 */
public interface ConfigurableLogger
    extends
    Closeable,
    Logger {

  /**
   * Sets the logging level of this logger so it will only log events having the
   * specified level or a greater level.
   *
   * @param level
   *        the logging level to set this logger to
   */
  void setLevel(Level level);

}