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
package objectos.docs.v0001.logging;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# HOWTO: test asynchronous code

The `Logger` interface acts as an event listener.
It can be used for logging and it can also be used during testing.

In this article we will discuss a way of using Objectos Logging to help you
test asynchronous code.

## The challenge

When you start a Java thread in your test code you might not know exactly when
the thread will complete its work. If you don't know when the work is complete,
you can't know when it is possible to do meaningful assertions. Suppose the
class under test is the following `Thread`:

```java
class HelloWriter extends Thread {
  private final int quantity;

  volatile int value;

  Incrementer(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public void run() {
    Path p = Paths.of("/tmp/hello.txt");
    try () {
    }
    for (int i = 0; i < stopAt; i++) {
      value++;
    }
  }
}
```



And the following test:

```java
public class IncrementerTest {
  @Test
  public void test() {
    var inc = new Incrementer(1000);

    inc.start();

    assertEquals(inc.value, 1000);
  }
}
```

The problem is that the `Incrementer` code will be run

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
    titleText = "HOWTO: test asynchronous code";
  }
}