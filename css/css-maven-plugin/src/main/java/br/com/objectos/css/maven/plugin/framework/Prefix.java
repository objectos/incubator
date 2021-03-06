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
package br.com.objectos.css.maven.plugin.framework;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkPrefix;

abstract class Prefix extends AbstractFrameworkObject implements FrameworkPrefix {

  private static final Prefix NAMELESS = new Nameless();

  Prefix() {}

  public static Prefix named(String prefix) {
    return new Named(prefix);
  }

  public static Prefix nameless() {
    return NAMELESS;
  }

  public abstract String toClassName(String name);

  public abstract String toFieldName(String name);

  @Override
  final void acceptPropertyBuilder(Property.Builder builder) {
    builder.prefix(this);
  }

  private static class Named extends Prefix {

    private final String value;

    Named(String value) {
      this.value = value;
    }

    @Override
    public final String toClassName(String name) {
      return value + '-' + name;
    }

    @Override
    public final String toFieldName(String name) {
      return JavaNames.toValidMethodName(value + JavaNames.toValidClassName(name));
    }

  }

  private static class Nameless extends Prefix {

    @Override
    public final String toClassName(String name) {
      return name;
    }

    @Override
    public final String toFieldName(String name) {
      return JavaNames.toValidMethodName(name);
    }

  }

}
