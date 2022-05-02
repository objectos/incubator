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

# During testing

This section describes uses of the `NoOpLogger` when you are writing tests for your
Java application.

## Supplying a Logger

Depending on the test case you might not be interested in the logging at all.
In this situation you can supply the `NoOpLogger` to the tested class.
For instance suppose the tested class is:

```java
import objectos.logging.*;

public class Service {
  private final Logger logger;

  Service(Logger logger) { this.logger = logger; }

  // methods
}
```

Then, during testing:

```java
import objectos.logging.*;

public class ServiceTest {

  private Service service = new Service(NoOpLogger.getInstance());

}
```

## Collecting events

 */
//@formatter:on
@Markdown
final class TheNoOpLoggerTesting extends DocsPage {
  @Override
  protected void configure() {
    titleText = "During testing";
  }
}