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

# Creating events

In Objectos Logging instances of the class `Event` represent any event that may occur
during a program execution that you want to log.

Defining and creating these event objects is a central part of logging
with Objectos Logging.

In this section we will discuss what are the properties of events
and on how you create them in your Java program.

## Event properties

Every event instance has the following properties:

- **source**: a name, possibly hierarchical, indicating the source of the event.
  This is typically the canonical name of the class where the event has been defined;
- **key**: an object that describes or identifies an event within the same source.
  This is typically a string; and
- **level**: indicates the severity of the event.

Events may _optionally_ have the following:

- **type parameters**: the types of the objects that will define the payload
  of the log message from the event.

## A first example

The example below creates a single `INFO` log event in the class `Service`:

```java
package com.example; import objectos.logging.*;

public class Service {
  static final Event0 STARTED = Event0.info();
}
```

Let's examine this declaration. We will explain the details as we go.

### The event is a `static final` field

The first thing to notice is that the event is defined as a field in our class.
It is highly recommended that you declare event fields as both `static` and `final`.
This is not a mandatory condition but, once again, it is highly recommended that you
do so. Reasons are:

- event instances are _immutable_. No reason allocating a new event object every time you
  log a message; and
- allows the use of the `==` operator when equality comparison is required.

### Logging level

The second thing to notice is that the event is created by invoking the `info()` method of
the event class.

The name of the method indicates the level of the created event. If we wanted the event
to have the `DEBUG` logging level we would have called the `debug()` method like so:

```java
static final Event0 STARTED = Event0.debug();
```

### Implicit source and key

Finally, the third thing to notice is that we did not specify
neither a source nor a key for our event. As a quick reminder:

- the **source** is a name used to indicate from where a log message originated; and
- the **key** is an object used to differentiate events of the same source.

When not explicitly defined both properties are inferred from the location where the event
was instantiated. Internally this is done by walking the stack frames of the executing thread.
The values will be:

- the **source** value will be the canonical name of the class; and
- the **key** value will be concatenation of the file name with line number.

Therefore, in our example the properties will have the following values:

- **source** = com.example.Service; and
- **key** = Service.java:4.

Or, in Java code, we would have:

```java
String source = STARTED.source();
source.equals("com.example.Service"); // evals to true

Object key = STARTED.key();
key.equals("Service.java:4") // evals to true;
```

## Standard logging levels

Objectos Logging defines 5 standard logging levels. They are listed below
ordered from the least severe to the most severe:

- TRACE;
- DEBUG;
- INFO;
- WARN; and
- ERROR.

In order to create an event having an specific logging level you invoke
the appropriate _static_ method from the event class. The example below
creates five different events each having a different event level:

```java
import objectos.logging.*;

public class Levels {
  static final Event0 TRACE = Event0.trace();
  static final Event0 DEBUG = Event0.debug();
  static final Event0 INFO = Event0.info();
  static final Event0 WARN = Event0.warn();
  static final Event0 ERROR = Event0.error();
}
```

## Creating parameterized events

Up to this point we have been using the `Event0` class in our examples.
This type of event does not allow you to pass any additional argument
to the log method. As an example the following code does not compile:

```java
static final Event0 IO_ERROR = Event0.error();

public void log(Logger logger, IOException e) {
  logger.log(IO_ERROR, e);
  //                 ^
  // compilation error here
}
```

Sometimes you will want to add some additional data to the log message.
In the example above we might want to pass the exception along. This
way a logger could, for example, add the stack trace of the exception
to the log file.

In order to do that, you need to declare a parameterized event instance.
The following code compiles without errors:

```java
static final Event1<IOException> IO_ERROR = Event1.error();

public void log(Logger logger, IOException e) {
  logger.log(IO_ERROR, e);
}
```

Notice that we have changed the event type from `Event0` to `Event1`.
The integer number at the end of the class name indicates the number
of type parameters that the event subclass declares:

- `Event0` does not allow any argument;
- `Event1` allows one argument;
- `Event2` allows two arguments; and
- `Event3` allows three arguments.

The next example illustrates the creation of each one of the available
event types:

```java
import java.io.*;
import objectos.logging.*;

public class TypeArgs {
  static final Event0 ZERO = Event0.trace();
  static final Event1<String> ONE = Event1.debug();
  static final Event2<String, Long> TWO = Event2.info();
  static final Event3<String, Long, IOException> THREE = Event3.error();
}
```

 */
//@formatter:on
@Markdown
final class LoggingGuideEvents extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Creating events";
  }
}