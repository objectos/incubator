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
module br.com.objectos.more.logging.slf4j {
  exports br.com.objectos.more.logging.slf4j;
  exports org.slf4j.impl;

  requires transitive br.com.objectos.logger;
  requires transitive org.slf4j;

  requires objectos.lang;
  requires objectos.util;

  provides org.slf4j.spi.SLF4JServiceProvider with org.slf4j.impl.SLF4JServiceProviderImpl;
}