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

import br.com.objectos.be.annotations.Markdown;
import objectos.docs.ui.ArticlePage;

//@formatter:off
/**

# Objectos Logging

Objectos Logging provides a logging API for Java applications.
You define logging events and you log them instead of string messages.
Events can be paramaterized making log method invocations type-safe.

## Hello world

p("""
  """);

codeJava(
  """
  import objectos.logging.*;

  public class HelloWorld {
    public static void main(String[] args) {
      var helloWorld = Events.info(HelloWorld.class, "HELLO_WORLD");

      var logger = new NoopLogger() {
        public void log(Event0 event) {
          if (event == helloWorld) {
            System.out.println("Hello world!");
          }
        }
      };

      logger.log(helloWorld);
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

*/
//@formatter:on
@Markdown
final class Index extends ArticlePage {}