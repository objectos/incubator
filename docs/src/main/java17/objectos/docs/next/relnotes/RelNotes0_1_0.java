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

# Objectos 0.1.0 release notes

_May 16, 2022_

Welcome to Objectos 0.1.0! This is the first public release of the
Objectos suite of Java libraries.

## JDK compatibility

Objectos 0.1.0 requires JDK 17 or later.

## What's new in Objectos 0.1.0

These are the new features included in this version.

### Objectos BOM POM

Objectos provides a Bill of Materials (BOM) POM. A BOM POM can help managing the versions
of the Objectos dependencies in a project.

We **highly recommend** the use of the Objectos BOM POM.

### Objectos Logging

[Objectos Logging](href:next.logging.Index) provides a logging API for Java applications.
You log events and not string messages. Events can have type arguments so that log
method invocations are type-safe. A hypothetical service that only logs "hello world!"
could be written as:

```java
import objectos.logging.*;

public class Service {
  static final Event1<String> SAY_HELLO = Event1.info();

  private final Logger logger;

  Service(Logger logger) { this.logger = logger; }

  public void sayHelloWorld() {
    logger.log(SAY_HELLO, "world!");
  }
}
```

*/
//@formatter:on
@Markdown
final class RelNotes0_1_0 extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Objectos 0.1.0 release notes";
  }
}
