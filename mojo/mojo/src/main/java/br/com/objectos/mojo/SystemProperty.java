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
package br.com.objectos.mojo;

import java.util.Properties;
import objectos.lang.Check;

public class SystemProperty implements MvnOption {

  private static final SystemProperty MAVEN_TEST_SKIP
      = new SystemProperty("maven.test.skip", "true");

  private final String key;

  private final String value;

  private SystemProperty(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public static SystemProperty mavenTestSkip() {
    return MAVEN_TEST_SKIP;
  }

  public static SystemProperty prop(String key, boolean value) {
    Check.notNull(key, "key == null");

    return new SystemProperty(key, Boolean.toString(value));
  }

  public static SystemProperty prop(String key, String value) {
    Check.notNull(key, "key == null");
    Check.notNull(value, "value == null");

    return new SystemProperty(key, value);
  }

  @Override
  public final void acceptMvnRequest(MvnRequest request) {
    Properties properties;
    properties = request.systemProperties;

    properties.put(key, value);
  }

  public final void set(Properties properties) {
    properties.put(key, value);
  }

}
