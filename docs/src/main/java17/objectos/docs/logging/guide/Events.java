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

# Creating events

As we have seen in the previous section, Objectos Logging let's you log events
instead of string messages.

```java
// log events...
logger.log(HELLO, "world!");

// instead of string messages
// note: the statement below does not compile
logger.log("Hello world!");
```

In this section you will learn how to create log event instances.

## A `Service` example

Let's create a hypothetical `Service` class. We will use it as an illustration on log
event creation.

Suppose our service is started without direct user interaction. How we will
know if the service has started? We can log a message for this purpose.
This message is of informational nature: "the service has started successfully".
Let's define an event for it:

```java
package com.example; import objectos.logging.*;

public class Service {
  static final Event0 STARTED = Event0.info();
}
```

There are important information in this statement, let's break them down:

### Logging level

Suppose our service needs to read some configuration information before it
starts. Therefore our service start might fail if the configuration cannot be
read. To indicate that the service started successfully  We will start by defining an event we will use to signal that our service
has successfully started. Let's define that this is an informational message.
In other words we want it to signal


The logging level of an event is used to indicate its severity.

The logging level is embedded in the event instance, so it must specified when you
create an event instance.

## Properties

Every log event instance must define a number of required properties. It may define
additional optional properties but, for now, we will focus on the required ones.
To illustrate the required properties, we will create some events for a hypothetical
`Service` class.

We will start by defining an event we will use to signal that our service
has successfully started:

```java
package com.example; import objectos.logging.*;

public class Service {
  static final Event0 STARTED = Event0.info();
}
```

Things to notice:

- the field is both `static` and `final`;
- the field is initialized from the static method `info()`; and
- the type of the field is `Event0`;

### Events are both `static` and `final`

The first thing to notice is that the event is defined as a field in our class.
It is highly recommended that events be declared as both `static` and `final`.

Event instances are _immutable_.

### Logging level

### Source

### Keys

### Creating parameterized event instances

### Properties summary

Every event instance must define the following properties:

- **source**: a possibly hierarchical name indicating the source of the event.
  This is typically the canonical name of the class where the event has been defined; and
- **level**: indicates the severity of the event.
  This is typically used by logger implementations to define whether a log message
  should be created or not. In other words, whether a log method invocation
  should produce a logging statement or not.

Events may _optionally_ define the following:

- **key**: an object that describes or identifies an event within the same source.
  This is typically a string literal
- **type parameters**:

 */
//@formatter:on
@Markdown
final class Events extends DocsPage {
  @Override
  protected final void configure() {
    nextPage = null;

    titleText = "Creating events";
  }
}