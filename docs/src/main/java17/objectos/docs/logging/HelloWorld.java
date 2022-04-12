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
import br.com.objectos.logging.Event0;
import br.com.objectos.logging.Events;
import br.com.objectos.logging.NoopLogger;
import objectos.docs.ui.ArticlePage;

//@formatter:off
/**

# Hello world

The following example shows most the library's exported API:

*```java
*import objectos.logging.*;
*
*public class HelloWorld {
*  public static void main(String[] args) {
*    var helloWorld = Events.info(HelloWorld.class, "HELLO_WORLD");
*
*    var logger = new NoopLogger() {
*      public void log(Event0 event) {
*        if (event == helloWorld) {
*          System.out.println("Hello world!");
*        }
*      }
*    };
*
*    logger.log(helloWorld);
*  }
*}
*```

This Java program will output:

```shell
Hello world!
```

The first statement in the main method creates an event instance by
invoking the static `info` method of the `Events` class.

*/
//@formatter:on
@Markdown
final class HelloWorld extends ArticlePage {
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