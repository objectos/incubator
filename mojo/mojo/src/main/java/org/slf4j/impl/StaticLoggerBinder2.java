/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package org.slf4j.impl;

import br.com.objectos.mojo.MojoLoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public final class StaticLoggerBinder2 implements LoggerFactoryBinder {

  private static final StaticLoggerBinder2 SINGLETON = new StaticLoggerBinder2();

  private static final String loggerFactoryClassStr = MojoLoggerFactory.class.getName();

  private StaticLoggerBinder2() {}

  public static final StaticLoggerBinder2 getSingleton() {
    return SINGLETON;
  }

  @Override
  public final ILoggerFactory getLoggerFactory() {
    return MojoLoggerFactory.getInstance();
  }

  @Override
  public final String getLoggerFactoryClassStr() {
    return loggerFactoryClassStr;
  }

}