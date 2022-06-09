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
package objectos.docs.next.lang;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# The `NoOpNoteSink`

The `NoOpNoteSink` is a logger implementation provided with Objectos Logging.
It is a no-operation logger. All of its logging methods do no operation when invoked.

Even though it performs no logging operation, the `NoOpNoteSink` can be useful
during the development of a Java application.

This section describes some of its uses.

## Obtaining the singleton instance

The `NoOpNoteSink` is a no-operation logger implementation. As it does nothing, it has no state
either. It provides a singleton instance since it can be safely reused. You access it by
invoking the static `getInstance()` method:

```java
var logger = NoOpNoteSink.getInstance();
```

## Use it as a _default_ value

In classes where the `Logger` is defined as a mutable field, it is recommended you provide
the `NoOpNoteSink` instance as a _default_ logger implementation.

We saw two examples in the [obtaining the `Logger` interface](href:next.lang.NotesNoteSink)
section:

- via the setter method; and
- via static factory.

As a quick reminder, let's see the _setter method_ strategy:

```java
import objectos.logging.*;

public class Setter {
  private Logger logger = NoOpNoteSink.getInstance();

  void setLogger(Logger logger) {
    if (logger == null) {
      throw new NullPointerException("logger == null");
    }

    this.logger = logger;
  }
}
```

Notice how the `logger` field is initialized with the `NoOpNoteSink` singleton instance.
It can be altered via the `setLogger()` method.

## Use it by subclassing

The `NoOpNoteSink` is also designed so it can be used by subclassing it.

For example, suppose you wish to provide a default `Logger` implementation that does a little
work, say print to the standard output. You can do it by extending the `NoOpNoteSink`.

```java
import objectos.logging.*;

public class Service {
  static final Event0 START = Event0.info();

  Logger logger = new NoOpNoteSink() {
    @Override
    public void log(Event0 event) {
      if (event == START) {
        System.out.println(event);
      }
    }
  };

  public void start() {
    logger.log(START);
  }

  void setLogger(Logger logger) {
    if (logger == null) {
      throw new NullPointerException("logger == null");
    }

    this.logger = logger;
  }
}
```

 */
//@formatter:on
@Markdown
final class NotesNoOpNoteSink extends DocsPage {
  @Override
  protected void configure() {
    titleText = "The NoOpNoteSink";
  }
}