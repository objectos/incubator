/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.latest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the implementation of the annotated type should be generated
 * as a singleton.
 *
 * <h2>Example</h2>
 *
 * <p>
 * The processing of the following class hierarchy:
 *
 * <pre><code>
 * package com.example;
 * public interface Example { void doImportantThing(); }
 *
 * &#64;Singleton
 * abstract class Factory {
 *   public abstract Example create();
 * }
 *
 * final class FactoryJava6 extends Factory {
 *   &#64;Singleton.Field
 *   static final FactoryJavaAny INSTANCE = new FactoryJava6();
 *
 *   public final Example create() {
 *     return new ThisExample();
 *   }
 *
 *   private static final class ThisExample implements Example {
 *     public final void doImportantThing() {
 *       // important thing... }
 *   }
 * }
 *
 * final class FactoryJava17 extends Factory {
 *   &#64;Singleton.Field
 *   static final FactoryJavaAny INSTANCE = new FactoryJava17();
 *
 *   public final Example create() {
 *     return new ThisExample();
 *   }
 *
 *   private static final class ThisExample implements Example {
 *     public final void doImportantThing() {
 *       // important thing but faster... }
 *   }
 * }
 * </code></pre>
 *
 * <p>
 * Would generate the following class:
 *
 * <pre><code>
 * package com.example;
 * import br.com.objectos.latest.Generated;
 * &#64;Generated("br.com.objectos.latest.processor.SingletonProcessor")
 * final class FactorySingleton {
 *   static final FactoryJavaAny INSTANCE = FactoryJava17.INSTANCE;
 *
 *   private FactorySingleton() {}
 * }
 * </code></pre>
 *
 * <p>
 * Which then could be used in a public class like so:
 *
 * <pre><code>
 * package com.example;
 * public final class Examples {
 *   private Examples() {}
 *
 *   public static Example create() {
 *     return FactorySingleton.INSTANCE.create();
 *   }
 * }
 * </code></pre>
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Singleton {

  /**
   * Indicates that the annotated field holds the singleton instance.
   */
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.FIELD)
  public @interface Field {}

}
