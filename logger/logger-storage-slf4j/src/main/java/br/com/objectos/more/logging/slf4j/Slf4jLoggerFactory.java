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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

final class Slf4jLoggerFactory implements ILoggerFactory {

  private final StorageLogger logger;

  private final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();

  public Slf4jLoggerFactory(StorageLogger logger) {
    this.logger = logger;
  }

  @Override
  public final Logger getLogger(String name) {
    Logger result;
    result = loggers.get(name);

    if (result == null) {
      Slf4jLogger newLogger;
      newLogger = new Slf4jLogger(name, logger);

      result = newLogger;

      Logger previous;
      previous = loggers.putIfAbsent(name, newLogger);

      if (previous != null) {
        result = previous;
      }
    }

    return result;
  }

}