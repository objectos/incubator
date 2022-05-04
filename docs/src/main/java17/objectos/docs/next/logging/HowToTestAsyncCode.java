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

# During testing

The `Logger` interface is an event listener. It can be used for logging and it can also be
used during testing.

This section describes uses of the `NoOpLogger` when you are writing tests for your
Java application.

Please note that these are targeted to particular use-cases and should not
be considered for general practice.

## Asynchronous code

When you start a Java thread in your test code you might not know exactly when
the thread will complete its work. If you don't know when the work is complete,
you can't know when it is possible to do meaninful assertions. Suppose the
class under test is the following `Runnable`:

```java
import objectos.logging.*;

class Incrementer implements Runnable {
  static final Event0 STARTED = Event0.info();

  private final Logger logger;
  private final int stopAt;

  int value;

  Incrementer(Logger logger, int stopAt) {
    this.logger = logger;
    this.stopAt = stopAt;
  }

  @Override
  public void run() {
    logger.log(STARTED);
  }
}
```



One solution is to put to sleep the thread
running the test itself. The following example illustrates the use of the
`Thread.sleep(int)` method:

```java
@Test
public void example() {
  var subject = new TaskBeingTested();

  var t = new Thread(subject);

  t.start();

  Thread.sleep(2000);

  // assert subject
}
```

## Collecting events

The `Logger` interface is as an event listener. As such, it can be used
to collect events during a test run. Later, you can test if the collected events are what
you expected them to be.

This can be useful for testing some kind of classes. For example, it can be used for testing
state machines, code in Java threads.

 */
//@formatter:on
@Markdown
final class HowToTestAsyncCode extends DocsPage {
  @Override
  protected void configure() {
    titleText = "How to Test asynchronous code";
  }
}