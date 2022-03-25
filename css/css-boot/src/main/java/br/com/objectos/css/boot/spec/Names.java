/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.boot.spec;

import br.com.objectos.code.java.JavaNames;

public class Names {

  public static final String PKG = "br.com.objectos.css";
  public static final String SELECTORS = "br.com.objectos.css.select";

  private Names() {}

  public static String propertyToSimpleName(String propertyName) {
    return JavaNames.toValidClassName(propertyName);
  }

  public static String toJavaName(String name) {
    return JavaNames.toValidMethodName(name);
  }

}