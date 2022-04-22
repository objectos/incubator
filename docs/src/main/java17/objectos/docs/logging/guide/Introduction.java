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
package objectos.docs.logging.guide;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Introduction

Let's start our introduction to the Objectos Logging API by
building a "Hello world" Java program.

```java
import objectos.logging.*;

public class HelloWorld {
  private static final Event1<String> HELLO = Event1.info();

  public static void main(String[] args) {
    Logger logger = NoOpLogger.getInstance();

    logger.log(HELLO, "world");
  }
}
```

Running this program will result in no output. We are using the
no-operation logger (`NoOpLogger`). As the name of the class might imply
all logging methods of this logger do no operation.

## Logging API only

A first thing to notice from our "Hello world" example is that
Objectos Logging requires that you explicitly provide a logger instance.

```java
Logger logger = NoOpLogger.getInstance();
```

However, apart from the no-operation logger, Objectos Logging does not provide
Logger implementations. And, as of the current Objectos suite release, the
Objectos suite does not provide any implementation either.

Implementations will be provided in a upcoming release.

## Events, not string messages

A second thing to notice from our example is that the API requires you to
create a log event instance. As seen in the example, this event instance can be parameterized.

```java
private static final Event1<String> HELLO = Event1.info();
```

To log a message you invoke the `log` method from the `logger` object passing the
desired event instance. Since in our case the event is parameterized with a `String`
type argument, you have to pass a string object to the `log` method along with the
event instance, like so:

```java
logger.log(HELLO, "world");
```

*/
//@formatter:on
@Markdown
final class Introduction extends DocsPage {
  @Override
  protected final void configure() {
    nextPage = Events.class;

    titleText = "Introduction";
  }
}