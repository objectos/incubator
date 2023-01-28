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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated interface should be used as the template for a
 * generated interface. Unlike the case of the {@link Concrete} annotation, the
 * generated interface <em>will not</em> extend the annotated interface.
 *
 * <h2>Example</h2>
 *
 * <p>
 * The processing of the following hierarchy:
 *
 * <pre><code>
 * package com.example;
 * public interface PublicInterface {}
 *
 * &#64;Template(simpleName = "Example", extendsClause = "PublicInterface")
 * interface ExampleJavaAny {
 *   &#47;**
 *    * Method a
 *    *&#47;
 *   void a();
 * }
 *
 * &#64;Template.Bridge
 * interface ExampleJava6 extends ExampleJavaAny {}
 *
 * &#64;Template.Bridge
 * interface ExampleJava8 extends ExampleJavaAny {
 *   &#47;**
 *    * Method b
 *    *&#47;
 *   void b();
 * }
 * </code></pre>
 *
 * <p>
 * Would generate the following interface:
 *
 * <pre><code>
 * package com.example;
 * import br.com.objectos.latest.Generated;
 * &#64;Generated("br.com.objectos.latest.processor.TemplateProcessor")
 * public interface Example extends PublicInterface {
 *   &#47;**
 *    * Method a
 *    *&#47;
 *   void a();
 *
 *   &#47;**
 *    * Method b
 *    *&#47;
 *   void b();
 * }
 * </code></pre>
 *
 * <p>
 * As the processor would choose, among the available bridges, the one that
 * represents the latest version.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Template {

  /**
   * A string value to be added to the generated interface as the
   * {@code extends} clause or the empty string meaning the generated interface
   * will not extend any other interface.
   *
   * @return the {@code extends} clause
   */
  String extendsClause();

  /**
   * The simple name of the generated interface.
   *
   * @return the simple name
   */
  String simpleName();

  /**
   * Indicates that the annotated interface, a subinterface of the template
   * interface, should be considered as a candidate.
   */
  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.TYPE)
  public @interface Bridge {}

}