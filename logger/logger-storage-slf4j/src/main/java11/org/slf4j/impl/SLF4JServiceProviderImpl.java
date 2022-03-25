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

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public class SLF4JServiceProviderImpl implements SLF4JServiceProvider {

  private IMarkerFactory markerFactory;

  private BasicMDCAdapter mdcAdapter;

  @Override
  public final ILoggerFactory getLoggerFactory() {
    StaticLoggerBinder singleton;
    singleton = StaticLoggerBinder.getSingleton();

    return singleton.getLoggerFactory();
  }

  @Override
  public final IMarkerFactory getMarkerFactory() {
    return markerFactory;
  }

  @Override
  public final MDCAdapter getMDCAdapter() {
    return mdcAdapter;
  }

  @Override
  public final String getRequesteApiVersion() {
    return "1.8.0";
  }

  @Override
  public final void initialize() {
    markerFactory = new BasicMarkerFactory();

    mdcAdapter = new BasicMDCAdapter();
  }

}