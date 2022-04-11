/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.logging;

import objectos.docs.ui.ArticlePage;

final class Index extends ArticlePage {

  @Override
  protected final void contents() {
    h1("Objectos Logging");

    p("""
      Objectos provides a type-safe logging API for Java applications.
      A "Hello world" would look like so:""");

    codeJava(
      """
      import objectos.logging.*;

      public class Example {
        private static final Event1<String> SAY
          = Events.info(Example.class, String.class);

        private final Logger logger;

        Example(Logger logger) {
          this.logger = logger;
        }

        public final void sayHello() {
          logger.log(SAY, "Hello world!");
        }
      }
      """);

    h2("Maven coordinates");

    p("""
      The following coordinates assume you have the Objectos BOM POM imported in your
      project:""");

    codeXml(
      """
      <dependencies>
          <dependency>
              <groupId>br.com.objectos</groupId>
              <artifactId>logging</artifactId>
          </dependency>
      </dependencies>""");
  }

}