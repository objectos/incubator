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

# The note sink API

_The note sink API is a work in progress. The API might change substantially
before its final delivery._

Objectos Lang provides a note sink API for Java applications.

It allows you define `Note` objects representing events taking place
during an application execution. Listeners for these notes are abstracted
away by the `NoteSink` interface. Notes may be parameterized; this
feature allows note producers to attach application-specific
arguments to the emitted note.

```java
package com.example; import objectos.lang.*;

public class Greeter {
  static final Event1<String> HELLO = Event1.info();

  final NoteSink sink;

  Greeter(NoteSink sink) { this.sink = sink; }

  public void hello(String something) {
    sink.emit(HELLO, something);
  }
}
```

## It's an event listener

The note sink API acts exactly as an event listener. It is meant primarily for:

- logging
- testing
- debugging

The reason for using the term _note_ instead of _event_ is that the latter is
a common and overloaded word. Therefore, in order to reduce the chance
of name clashes, we use the names:

- `Note` instead of `Event`
- `NoteSink` instead of `EventListener`

*/
//@formatter:on
@Markdown
final class NotesIndex extends DocsPage {
  @Override
  protected void configure() {
    titleText = "The note sink API";
  }
}