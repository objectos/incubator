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
package br.com.objectos.core.runtime;

/**
 * Indicates the Objectos build being used by the current runtime.
 *
 * @since 2
 */
public enum ObjectosBuild {

  /**
   * The Objectos build for Java 11.
   */
  JAVA11,

  /**
   * The Objectos build for Java 16.
   *
   * @deprecated
   */
  @Deprecated
  JAVA16,

  /**
   * The Objectos build for Java 17.
   */
  JAVA17,

  /**
   * The Objectos build for Java 6.
   */
  JAVA6,

  /**
   * The Objectos build for Java 7.
   */
  JAVA7,

  /**
   * The Objectos build for Java 8.
   */
  JAVA8;

  /**
   * Returns the Objectos build value.
   *
   * @return the Objectos build value
   */
  public static ObjectosBuild get() {
    return ObjectosBuildProviderSingleton.INSTANCE.get();
  }

}