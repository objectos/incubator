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
package org.slf4j.impl;

import objectos.lang.Check;
import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public final class StaticLoggerBinder
    implements
    LoggerFactoryBinder {

  private static final StaticLoggerBinder INSTANCE = new StaticLoggerBinder();

  private static ILoggerFactory loggerFactory = new NOPLoggerFactory();

  public StaticLoggerBinder() {}

  public static StaticLoggerBinder getSingleton() {
    return INSTANCE;
  }

  public static void setLoggerFactory(ILoggerFactory factory) {
    Check.notNull(factory, "factory == null");

    Class<? extends ILoggerFactory> factoryClass;
    factoryClass = loggerFactory.getClass();

    Check.state(factoryClass.equals(NOPLoggerFactory.class), "factory was already set");

    loggerFactory = factory;
  }

  @Override
  public final ILoggerFactory getLoggerFactory() {
    return loggerFactory;
  }

  @Override
  public final String getLoggerFactoryClassStr() {
    return StaticLoggerBinder.class.getName();
  }

}