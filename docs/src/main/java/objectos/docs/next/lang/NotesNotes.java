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

# Creating notes

`Note` objects represent an instant of a program execution for which you may
wish to keep a note of.

Defining and creating these objects is a central part of using the note sink API.

In this section we will discuss what are the properties of notes
and on how you create them in your Java program.

## Note properties

Every note instance has the following properties:

- **source**: a name, possibly hierarchical, indicating the source of the note.
  This is typically the qualified name of the class where the note has been defined;
- **key**: an object that describes or identifies a note within the same source.
  This is typically a string; and
- **level**: indicates the severity of the note.

Notes may _optionally_ have the following:

- **type parameters**: the types of the objects that will define the payload
  of the note.

## A first example

The example below creates a single `INFO` note in the class `Service`:

```java
package com.example; import objectos.lang.*;

public class Service {
  static final Note0 STARTED = Note0.info();
}
```

Let's examine this declaration. We will explain the details as we go.

### The note is a `static final` field

The first thing to notice is that the note is defined as a field in our class.
It is highly recommended that you declare note fields as both `static` and `final`.
This is not a mandatory condition but, once again, it is highly recommended that you
do so. Reasons are:

- note instances are _immutable_. No reason allocating a new note object every time
  you wish to send a note; and
- allows the use of the `==` operator when equality comparison is required.

### Note level

The second thing to notice is that the note is created by invoking the `info()` method of
the note class.

The name of the method indicates the level of the created note. If we wanted the note
to have the `DEBUG` level we would have called the `debug()` method like so:

```java
static final Note0 STARTED = Note0.debug();
```

### Implicit source and key

Finally, the third thing to notice is that we did not specify
neither a source nor a key for our note. As a quick reminder:

- the **source** is a name used to indicate from where a note was sent; and
- the **key** is an object used to differentiate notes of the same source.

When not explicitly defined both properties are inferred from the location where the note
was instantiated. Internally this is done by walking the stack frames of the executing thread.
The values will be:

- the **source** value will be the fully qualified name of the class; and
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

## Standard note levels

Objectos Lang defines 5 standard note levels. They are listed below
ordered from the least severe to the most severe:

- TRACE;
- DEBUG;
- INFO;
- WARN; and
- ERROR.

In order to create an note having an specific level you invoke
the appropriate _static_ method from the note class. The example below
creates five different notes each having a different note level:

```java
import objectos.lang.*;

public class Levels {
  static final Note0 TRACE = Note0.trace();
  static final Note0 DEBUG = Note0.debug();
  static final Note0 INFO = Note0.info();
  static final Note0 WARN = Note0.warn();
  static final Note0 ERROR = Note0.error();
}
```

## Creating parameterized notes

Up to this point we have been using the `Note0` class in our examples.
This type of note does not allow you to pass any additional argument
to the `send` method. As an example the following code does not compile:

```java
static final Note0 IO_ERROR = Note0.error();

public void log(NoteSink sink, IOException e) {
  sink.send(IO_ERROR, e);
  //                 ^
  // compilation error here
}
```

Sometimes you will want to add some additional data to the note.
In the example above we might want to pass the exception along. This
way a `NoteSink` acting as a logger could, for example, add the stack trace
of the exception to the log file.

In order to do that, you need to declare a parameterized note instance.
The following code compiles without errors:

```java
static final Note1<IOException> IO_ERROR = Note1.error();

public void log(NoteSink sink, IOException e) {
  sink.send(IO_ERROR, e);
}
```

Notice that we have changed the note type from `Note0` to `Note1`.
The integer number at the end of the class name indicates the number
of type parameters that the note subclass declares:

- `Note0` does not allow any argument;
- `Note1` allows one argument;
- `Note2` allows two arguments; and
- `Note3` allows three arguments.

The next example illustrates the creation of each one of the available
note types:

```java
import java.io.*;
import objectos.lang.*;

public class TypeArgs {
  static final Note0 ZERO = Note0.trace();
  static final Note1<String> ONE = Note1.debug();
  static final Note2<String, Long> TWO = Note2.info();
  static final Note3<String, Long, IOException> THREE = Note3.error();
}
```

 */
//@formatter:on
@Markdown
final class NotesNotes extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Creating notes";
  }
}