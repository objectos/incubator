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

import br.com.objectos.logging.Event0;
import br.com.objectos.logging.Events;
import br.com.objectos.logging.NoopLogger;

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
}