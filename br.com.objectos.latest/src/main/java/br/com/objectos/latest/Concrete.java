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
package br.com.objectos.latest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the concrete implementation of the annotated type should be
 * generated.
 *
 * <h2>Example</h2>
 *
 * <p>
 * The processing of the following class hierarchy:
 *
 * <pre><code>
 * package com.example;
 * &#64;Concrete(modifiers = "public final", simpleName = "Example")
 * abstract class ExampleJavaAny {
 *   public abstract void printSomething();
 * }
 *
 * &#64;Concrete.Bridge
 * abstract class ExampleJava6 extends ExampleJavaAny {
 *   private final int value;
 *   &#64;Constructor
 *   public ExampleJava6(int i) {
 *     this.value = i;
 *   }
 *
 *   public final void printSomething() {
 *     System.out.println(value);
 *   }
 * }
 *
 * &#64;Concrete.Bridge
 * abstract class ExampleJava11 extends ExampleJavaAny {
 *   private final String value;
 *   &#64;Constructor
 *   public ExampleJava11(int i) {
 *     this.value = Integer.toString(i);
 *   }
 *
 *   &#64;Constructor
 *   public ExampleJava11(String s) {
 *     this.value = s;
 *   }
 *
 *   public ExampleJava11(boolean ignoreMe) {}
 *
 *   public final void printSomething() {
 *     System.out.println(value);
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
 * &#64;Generated("br.com.objectos.latest.processor.ConcreteClassProcessor")
 * public final class Example extends ExampleJava11 {
 *   public Example(int i) {
 *     super(i);
 *   }
 *
 *   public Example(String s) {
 *     super(s);
 *   }
 * }
 * </code></pre>
 *
 * <p>
 * As the processor would choose, among the available bridges, the one that
 * represents the latest version.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Concrete {

  /**
   * The modifiers for the generated concrete type as a single string.
   *
   * @return the modifiers
   */
  String modifiers() default "";

  /**
   * The simple name of the generated concrete type.
   *
   * @return the simple name
   */
  String simpleName();

  /**
   * Indicates that the annotated type should be considered as one of the
   * possible bridges for the concrete implementation.
   */
  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.TYPE)
  public @interface Bridge {

    /**
     * Annotations to add to the generated type.
     *
     * <p>
     * The string values are written, as is, at the top of the generated type,
     * one string per line. Therefore, the full declaration of the annotation
     * invocation is expected.
     *
     * <p>
     * For example, a common use-case is to selective add the functional
     * interface annotation to the generated type
     *
     * <pre>
     * &#64;Bridge(annotations = {
     *     "&#64;java.lang.FunctionalInterface"
     * })
     * interface SomeInterfaceJava8 extends AbstractInterface {}</pre>
     *
     * <p>
     * while for Java 6 or Java 7 would be simply
     *
     * <pre>
     * &#64;Bridge
     * interface SomeInterfaceJava6 extends AbstractInterface {}</pre>
     *
     * @return the lines to be added at the top of the type declaration as
     *         annotation values.
     */
    String[] annotations() default {};

  }

  /**
   * Indicates that a constructor with the same access level and the same
   * signature as the annotated one should be generated for the concrete type.
   * Should be used on the same type annotated with {@link Bridge}.
   */
  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.CONSTRUCTOR)
  public @interface Constructor {}

}
