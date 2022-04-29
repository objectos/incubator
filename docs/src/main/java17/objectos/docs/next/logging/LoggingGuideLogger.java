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

# Using the `Logger` interface

After declaring your event instances you can emit log messages
by using them with the `Logger` interface.

In this section we will give you instructions on how to use the
`Logger` interface.

## Obtaining `Logger` instances

As seen in the previous section events are bound to a particular class as, by default,
their source is set to the canonical name of the class in which the event was declared.

The first thing to know about logger instances is that, unlike events,
logger instances _are not bound_ to a particular class. Typically
logger instances **will not be** `static final` fields of a class.

Next we will discuss the recommended strategies for obtaining logger instances.

### Via constructor

This is the recommended way to obtain logger instances. In particular for
the long-lived objects of your Java application.

```java
import objectos.logging.*;

public class Service {
  private final Logger logger;

  Service(Logger logger) {
    this.logger = logger;
  }
}
```

### Via method parameter

If you are concerned about object size and garbage creation, storing the logger
in an instance field might be problematic. If the class or the method where the
logging takes place is not part of a public API, you can pass the logger via a method parameter.

```java
import objectos.logging.*;

public class ShortLived {
  void internalMethod(Logger logger) {
    // use logger here
  }
}
```

### Via setter method

The previous option might not work for you if the method where logging takes place
is part of the exported API. In this case you can use a setter method for the logger instance.

```java
import objectos.logging.*;

public class Setter {
  private Logger logger = NoOpLogger.getInstance();

  void setLogger(Logger logger) {
    if (logger == null) {
      throw new NullPointerException("logger == null");
    }

    this.logger = logger;
  }
}
```

You must be aware this strategy is error-prone:

- you must null-check before setting the logger field value; and
- you must set the logger to a default value to avoid null-pointer exceptions.

### Via static factory

As a last resort, you can create your own static factory. Since we consider it a last
resort, Objectos Logging does not provide one out-of-the-box. A minimal example is:

```java
import objectos.logging.*;

public final class StaticLogger {
  private static Logger LOGGER = NoOpLogger.getInstance();

  private StaticLogger() {}

  public static Logger get() {
    return LOGGER;
  }

  public static void set(Logger logger) {
    if (logger == null) {
      throw new NullPointerException("logger == null");
    }

    LOGGER = logger;
  }
}
```

During application initialization, set the static factory with a proper logger instance.
Then, in the class where logging is needed, you can obtain the logger from the factory.

```java
import objectos.logging.*;

public class Service {
  private static final Logger LOGGER = StaticLogger.get();
}
```

We consider this strategy as a last resort as it can make testing more complicated.

## Logging events

### `Event0` events

Events of the `Event0` types do not have type arguments. Therefore, you
log them directly like so:

```java
import
```

### Parameterized events

*/
//@formatter:on
@Markdown
final class LoggingGuideLogger extends DocsPage {
  @Override
  protected void configure() {
    titleText = "Using the Logger interface";
  }
}
