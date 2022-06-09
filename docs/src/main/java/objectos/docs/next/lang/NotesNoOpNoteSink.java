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

The `NoOpNoteSink` is a no-operation `NoteSink` implementation provided with Objectos Lang.
All of its note sending methods do no operation when invoked.

Even though it performs no note sending operation, the `NoOpNoteSink` can be useful
during the development of a Java application.

This section describes some of its uses.

## Obtaining the singleton instance

The `NoOpNoteSink` is a no-operation implementation. As it does nothing, it has no state
either. Therefore, the provided singleton instance can be safely reused. You access it by
invoking the static `getInstance()` method:

```java
var sink = NoOpNoteSink.getInstance();
```

## Use it as a _default_ value

In classes where the `NoteSink` is defined as a mutable field, it is recommended you provide
the `NoOpNoteSink` instance as a _default_ sink implementation.

We saw two examples in the [obtaining the `NoteSink` interface](href:next.lang.NotesNoteSink)
section:

- via the setter method; and
- via static factory.

As a quick reminder, let's see the _setter method_ strategy:

```java
import objectos.lang.*;

public class Setter {
  private NoteSink sink = NoOpNoteSink.getInstance();

  void setNoteSink(NoteSink sink) {
    if (sink == null) {
      throw new NullPointerException("sink == null");
    }

    this.sink = sink;
  }
}
```

Notice how the `sink` field is initialized with the `NoOpNoteSink` singleton instance.
It can be altered via the `setNoteSink` method.

## Use it by subclassing

The `NoOpNoteSink` is also designed so it can be used by subclassing it.

For example, suppose you wish to provide a default `NoteSink` implementation that does a little
work, say print to the standard output. You can do it by extending the `NoOpNoteSink`.

```java
import objectos.lang.*;

public class Service {
  static final Note0 START = Note0.info();

  NoteSink sink = new NoOpNoteSink() {
    @Override
    public void log(Note0 note) {
      if (note == START) {
        System.out.println(note);
      }
    }
  };

  public void start() {
    sink.send(START);
  }

  void setNoteSink(NoteSink sink) {
    if (sink == null) {
      throw new NullPointerException("sink == null");
    }

    this.sink = sink;
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