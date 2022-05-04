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
package objectos.docs.next.logging;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Logging with Objectos Logging

In this section we will give you an overview of how Objectos Logging approaches
logging.

## Log events

As we have seen in the previous section, logging is about emitting messages
describing events taking place during a program execution. Put simply,
Objectos Logging acts as an event listener. Therefore, the central
part of logging with Objectos Logging is defining these events.

In Objectos Logging events are _immutable_ objects that you instantiate.
A "Hello world!" example:

```java
package com.example; import objectos.logging.*;

public class Greeter {
  static final Event0 HELLO_WORLD = Event0.info();

  // for brevity, assume injected
  Logger logger;

  public void helloWorld() {
    logger.log(HELLO_WORLD);
  }
}
```

If one were to provide a logger implementation the message could be printed as:

(Please note that, as of the current release, Objectos Logging does not provide
logger implementations)

```
ts=1651061090268 | level=INFO | thread=main | source=com.example.Greeter | name=Greeter.java:4
```

Or, using a more human readable format, could be printed as:

```
2022-04-27 09:04:50.268 INFO --- [main] com.example.Greeter(Greeter.java:4)
```

## Events can be parameterized

In the previous example the event is "Hello world!". We could alter the event
to mean "Hello something" by adding a type parameter:

```java
package com.example; import objectos.logging.*;

public class Greeter {
  static final Event1<String> HELLO = Event1.info();

  // for brevity, assume injected
  Logger logger;

  public void hello(String something) {
    logger.log(HELLO, something);
  }
}
```

So if we were to invoke our greeter with the string "universe!" the log message would be:

```
2022-04-27 09:04:50.268 INFO --- [main] com.example.Greeter(Greeter.java:4) universe!
```

This way you can add arbitrary payloads to the logging message.

## Debugging

When using Objectos Logging logger instances are usually not `static final`. This means
that you can use a specific implementation for your test cases. And since events are
objects you can selectively intercept logging method invocations like so:

```java
public class MyTestingLogger extends NoOpLogger {
  @Override
  public void <T> log(Event1<T> event, T value) {
    if (event == Greeter.HELLO && "world!".equals(value) {
      // add a debugger breakpoint here.
    }
  }
}
```

## Summary

In this section we discussed what makes Objectos Logging particular regarding Java
logging libraries.

 */
//@formatter:on
@Markdown
final class GettingStartedObjectosLogging extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Logging with Objectos Logging";
  }
}
