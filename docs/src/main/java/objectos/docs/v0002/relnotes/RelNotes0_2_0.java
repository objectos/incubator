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
package objectos.docs.v0002.relnotes;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Objectos 0.2.0 release notes

_June 13, 2022_

Welcome to Objectos 0.2.0, the second public release of the
Objectos suite of Java libraries.

## JDK compatibility

Objectos 0.2.0 requires JDK 17 or later.

## What's new in Objectos 0.2.0

This release introduces the [Objectos Lang](href:v0002.lang.LangIndex) library.
It provides:

- a set of utilities for objects whose type is from the `java.lang` package; and
- a note sink API

### The `Check` class

The [`Check`](href:v0002.lang.LangCheck) class
helps verify if a method or constructor has been invoked correctly:

```java
public void start(String name, int value) {
  Check.notNull(name, "name == null");
  Check.argument(value > 0, "value must be positive");
  Check.state(this.state == State.NEW, "cannot start: already started");
}
```

If any of the check conditions
does not evaluate to `true` then the check fails with a specific `RuntimeException` sub-type.

You can find [the full documentation here](href:v0002.lang.LangCheck).

### Utilities for overriding `java.lang.Object` methods

Objectos Lang provides three utilities each for overriding one of the following
`java.lang.Object` method:

- [`equals`](href:v0002.lang.LangEquals)
- [`hashCode`](href:v0002.lang.LangHashCode)
- [`toString`](href:v0002.lang.LangToString)

The following example show all of them in action:

```java
class Edition implements ToString.Formattable {
  Book book;
  Publisher publisher;
  LocalDate date;

  @Override
  public boolean equals(Object o) {
    return o == this || o instanceof Edition that
      && Equals.of(
        book, that.book,
        publisher, that.publisher,
        date, that.date
      );
  }

  @Override
  public int hashCode() {
    return HashCode.of(book, publisher, date);
  }

  @Override
  public void formatToString(StringBuilder sb, int level) {
    ToString.format(
      sb, level, this,
      "book", book,
      "publisher", publisher,
      "date", date
    );
  }

  @Override
  public String toString() {
    return ToString.of(this);
  }
}
```

You can find the full documentation for each of the classes in the links below:

- [`Equals`](href:v0002.lang.LangEquals)
- [`HashCode`](href:v0002.lang.LangHashCode)
- [`ToString`](href:v0002.lang.LangToString)

### The note sink API

Objectos Lang provides an API for defining and sending notes about events taking
place during an application execution. Use it for logging or debugging.

```java
import objectos.lang.*;

public class Service {
  static final Note1<String> SAY_HELLO = Note1.info();

  private final NoteSink sink;

  Service(NoteSink sink) { this.sink = sink; }

  public void sayHelloWorld() {
    sink.send(SAY_HELLO, "world!");
  }
}
```

## Changes from Objectos 0.1.0

Notable changes from the previous release are listed in this section.

### Objectos Logging was moved to Objectos Lang

Classes from the Objectos Logging artifact were moved to Objectos Lang.
Additionally the classes were renamed:

- `Event` was renamed to `Note`
- `Logger` was renamed to `NoteSink`

### Objectos BOM POM artifact name renamed

The Maven artifact for the Objectos BOM POM was renamed from:

- `bom`

To:

- `objectos-bom`

*/
//@formatter:on
@Markdown
final class RelNotes0_2_0 extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Objectos 0.2.0 release notes (2022-06-13)";
  }
}
