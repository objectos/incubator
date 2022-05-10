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
import objectos.logging.Event1;
import objectos.logging.NoOpLogger;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Quick start

This section presents a tutorial on how to get started quickly
with Objectos Logging.

## What you need

- JDK 17 or higher
- a text editor

## 1. Create a working directory

On Linux:

```
$ mkdir o7log
$ cd o7log
```

## 2. Install

Download the Objectos Logging JAR file from Maven central:

```
$ wget https://search.maven.org/remotecontent?filepath=br/com/objectos/logging/{{version}}/logging-{{version}}.jar
```

## 3. Create and run `HelloWorld.java`

Open your editor and create the `HelloWorld.java` file:

```java
public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}
```

Run the application:

```
$ java HelloWorld.java
```

It should print:

```
Hello world!
```

Let's refactor our example to use Objectos Logging.

## 4. Create a HELLO log event

Edit the `HelloWorld.java` and add the event:

```java
import objectos.logging.*;

public class HelloWorld {
  static final Event1<String> HELLO = Event1.info();

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}
```

To create the HELLO event we invoked the `info` method from the `Event1` class.
This way the event will be logged at the `INFO` level.

## 5. Create a logger implementation

As of the current release, Objectos logging only provides a simple no-operation `Logger`
implementation. You cannot do much with a no-operation logger. Let's extend it so that
it prints all events to `System.out`:

```java
import objectos.logging.*;

public class HelloWorld {
  static final Event1<String> HELLO = Event1.info();

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}

class ThisLogger extends NoOpLogger {
  public <T> void log(Event1<T> event, T value) {
    System.out.println(event + ":" + value);
  }
}
```

## 6. Log using the created logger

To log, invoke the `log` method from the logger instance as shown below:

```java
import objectos.logging.*;

public class HelloWorld {
  static final Event1<String> HELLO = Event1.info();

  public static void main(String[] args) {
    var logger = new ThisLogger();

    logger.log(HELLO, "world!");
  }
}

class ThisLogger extends NoopLogger {
  public <T> void log(Event1<T> event, T value) {
    System.out.println(event + ":" + value);
  }
}
```

## 7. Run the refactored program

To run the refactored program we will have to inform the `java` program where
to find the Objectos Logging JAR file. We will run the new version of our
example using the class path as it makes for shorter command:

```shell
$ java -cp logging-{{version}}.jar HelloWorld.java
```

When you run this Java program you will see the output:

```
Event1[HelloWorld,INFO,HelloWorld.java:4]:world!
```

The output is not quite "Hello world!" but it should be sufficient for now.

## 8. Verify type-safety

Log event instances can be parameterized. In our example, the `HELLO` event requires that
you pass, along with the event instance itself, a single `String` argument to the
`log` method. Edit the `HelloWorld.java` file. Change the implementation of the
`main` method to the following:

```java
public static void main(String[] args) {
  var logger = new ThisLogger();

  // notice the separate exclamation mark
  logger.log(HELLO, "world", "!");
}
```

Let's try to run this version of our program:

```shell
$ java -cp logging-{{version}}.jar HelloWorld.java
```

It should fail with a compilation error (the
ellipsis `(...)` below indicates suppressed output):

*```shell
*HelloWorld.java:10: error: no suitable method found for log(Event1<String>,String,String)
*    logger.log(HELLO, "world", "!");
*          ^
*(...)
*
*1 error
*error: compilation failed
*```

Edit the `HelloWorld.java` again. This time remove all arguments from the `log` method
invocation like so:

```java
public static void main(String[] args) {
  var logger = new ThisLogger();

  logger.log(HELLO);
}
```

Let's try to run this version of our program:

```shell
$ java -cp logging-{{version}}.jar HelloWorld.java
```

And it fails again with a compilation error:

*```shell
*HelloWorld.java:10: error: no suitable method found for log(Event1<String>)
*    logger.log(HELLO);
*          ^
*(...)
*
*1 error
*error: compilation failed
*```

## 9. Cleanup

Remove the working directory we created for this example:

```shell
$ cd ..
$ rm --recursive o7log/
```

## Summary

In this tutorial we introduced the main concepts you should know to get started
quickly with Objectos Logging.

*/
//@formatter:on
@Markdown
final class GettingStartedQuickStart extends DocsPage {
  static final Event1<String> HELLO = Event1.info();

  public static void main(String[] args) {
    var logger = new NoOpLogger() {
      @Override
      public <T> void log(Event1<T> event, T arg) {
        if (event == HELLO) {
          System.out.println("Hello " + arg);
        }
      }
    };

    logger.log(HELLO, "world!");
  }

  @Override
  protected final void configure() {
    titleText = "Quick start";
  }
}