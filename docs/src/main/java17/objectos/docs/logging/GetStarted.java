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
import br.com.objectos.logging.Event1;
import br.com.objectos.logging.Events;
import br.com.objectos.logging.NoopLogger;
import objectos.docs.ui.ArticlePage;

//@formatter:off
/**

# Get started with Objectos Logging

This section shows how to get started quickly with Objectos Logging.

## 1. Install

Import the Objectos BOM POM by following [this document](href:intro.Installation).

Next, add the Objectos Logging dependency to your project's `pom.xml`:

*```xml
*<dependencies>
*    <dependency>
*        <groupId>br.com.objectos</groupId>
*        <artifactId>logging</artifactId>
*    </dependency>
*</dependencies>
*```

## 2. Create a log event

You create a log event by invoking the appropriate method in the `Events`
class:

```java
import objectos.logging.*;

public class Application {
  static final Event1<String> HELLO
      = Events.info(Application.class, "HELLO", String.class);
}
```

We created a log event having the `INFO` level. The event source is the
`Application` class, its key is `HELLO` and it accepts one `String`
parameter.

## 3. Get a logger instance

Objectos Logging focuses on the Logging API leaving the implementation open.
It does provide a simple no-operation `Logger` implementation. Let's
extend it so that it logs the `HELLO` event to `System.out`:

```java
var logger = new NoopLogger() {
  @Override
  public <T> void log(Event1<T> event, T arg) {
    if (event == HELLO) {
      System.out.println("Hello " + arg);
    }
  }
};
```

## 4. Log

To log, invoke the `log` method from the logger instance. The full listing of
our example is shown below:

```java
import objectos.logging.*;

public class Application {
  static final Event1<String> HELLO
      = Events.info(Application.class, "HELLO", String.class);

  public static void main(String[] args) {
    var logger = new NoopLogger() {
      @Override
      public <T> void log(Event1<T> event, T arg) {
        if (event == HELLO) {
          System.out.println("Hello " + arg);
        }
      }
    };

    logger.log(HELLO, "world!");
  }
}
```

When you run this Java program you will see the output:

```shell
Hello world!
```

## 5. Verify type-safety

Log event instances are parameterized. In our example, the `HELLO` event requires that
you pass a `String` argument to the `log` method. The following examples will
fail to compile:

```java
// compilation error: arg required
logger.log(HELLO);

// compilation error: arg is not a String
logger.log(HELLO, Boolean.TRUE);

// compilation error: too many args
logger.log(HELLO, "world", "!");
```

## Where to go from here



*/
//@formatter:on
@Markdown
final class GetStarted extends ArticlePage {
  static final Event1<String> HELLO
      = Events.info(GetStarted.class, "HELLO", String.class);

  public static void main(String[] args) {
    var logger = new NoopLogger() {
      @Override
      public <T> void log(Event1<T> event, T arg) {
        if (event == HELLO) {
          System.out.println("Hello " + arg);
        }
      }
    };

    logger.log(HELLO, "world!");
  }
}