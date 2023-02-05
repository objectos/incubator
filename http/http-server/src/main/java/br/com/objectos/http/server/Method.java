/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.server;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Method {

  GET(Get.class, "onGet"),

  HEAD(Head.class, "onHead"),

  POST(Post.class, "onPost");

  private static final Map<String, Method> MAP = Stream.of(Method.values())
      .collect(Collectors.toMap(Method::name, Function.identity()));

  private final Class<? extends Annotation> annotationType;
  private final String configurationMethodName;

  private Method(Class<? extends Annotation> annotationType, String configurationMethodName) {
    this.annotationType = annotationType;
    this.configurationMethodName = configurationMethodName;
  }

  public static boolean containsName(String name) {
    return MAP.containsKey(name);
  }

  public static Method get(String name) {
    return MAP.get(name);
  }

  public Class<? extends Annotation> annotationType() {
    return annotationType;
  }

  public String configurationMethodName() {
    return configurationMethodName;
  }

  String toString(String resourceName) {
    return name() + " " + resourceName;
  }

}