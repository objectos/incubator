/*
 * Copyright (C) 2021 Objectos Software LTDA.
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
package br.com.objectos.more.logging.slf4j;

import br.com.objectos.logger.StorageLogger;
import objectos.lang.Check;
import org.slf4j.impl.StaticLoggerBinder;

public final class MoreLoggingAndSlf4j {

  private MoreLoggingAndSlf4j() {}

  public static void bootstrap(StorageLogger logger) {
    Check.notNull(logger, "logger == null");

    Slf4jLoggerFactory factory;
    factory = new Slf4jLoggerFactory(logger);

    StaticLoggerBinder.setLoggerFactory(factory);
  }

}
