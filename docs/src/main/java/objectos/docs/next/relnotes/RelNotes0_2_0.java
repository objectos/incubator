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
package objectos.docs.next.relnotes;

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

This release introduces the [Objectos Lang](href:next.lang.LangIndex) library.
It provides:

- a set of utilities for objects whose type is from the `java.lang` package; and
- a note sink API

### The `Check` class

The [`Check`](href:next.lang.LangCheck) class
helps verify if a method or constructor has been invoked correctly:

```java
public void start(String name, int value) {
  Check.notNull(name, "name == null");
  Check.argument(value > 0, "value must be positive");
  Check.state(this.state == State.NEW, "cannot start: already started");
}
```

You can find [the full documentation here](href:next.lang.LangCheck).

### Utilities for overriding `java.lang.Object` methods

Objectos Lang provides three utilities each for overriding one of the following
`java.lang.Object` method:

- [`equals`](href:next.lang.LangEquals)
- [`hashCode`](href:next.lang.LangHashCode)
- [`toString`](href:next.lang.LangToString)

The following example show all of them in action:

```java
class Edition {
  Book book;
  Publisher publisher;
  LocalDate date;

  @Override
  public boolean equals(Object o) {
    return o == this || o instanceof Edition that
      && Equals.objects(
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
  public String toString() {
    return ToString.of(
      this,
      "book", book,
      "publisher", publisher,
      "date", date
    );
  }
}
```

You can find the full documentation for each of the classes in the links below:

- [`Equals`](href:next.lang.LangEquals)
- [`HashCode`](href:next.lang.LangHashCode)
- [`ToString`](href:next.lang.LangToString)

### The note sink API

## Changes from Objectos 0.1.0

### Objectos Logging moved to Objectos Lang

The classes from Objectos Logging were merged into Objectos Lang.

### Objectos BOM POM artifact name renamed

*/
//@formatter:on
@Markdown
final class RelNotes0_2_0 extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Objectos 0.2.0 release notes (2022-06-13)";
  }
}
