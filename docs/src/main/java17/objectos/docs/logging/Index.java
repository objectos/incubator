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

      public class HelloWorld {
        public static void main(String[] args) {
          var say = Events.info(HelloWorld.class, "SAY", String.class);

          var logger = new NoopLogger() {
            public <T> void log(Event1<T> event, T arg) {
              if (event == say) {
                // the cast to string is not necessary.
                // It is here just to show it is a safe cast
                // since `say` is parameterized Event1<String>
                System.out.println((String) arg);
              }
            }
          };

          logger.log(say, "Hello world!");
        }
      }""");

    p("""
      When run this Java program will output:""");

    codeShell("Hello world!");

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