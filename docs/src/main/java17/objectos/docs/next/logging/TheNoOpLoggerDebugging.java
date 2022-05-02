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

# During debugging

This section describes a method of using the `NoOpLogger` for helping the debugging of
Java applications.

## `Logger` is an event listener

The `Logger` interface acts as an event listener.

## The error condition

Suppose you get the following error in your logs:

## Subclassing the `NoOpLogger`

You can create a specialized `Logger` implementation.

```java
public class ServiceTest {

  class ThisLogger extends NoOpLogger {
    @Override
    public void <T> log(Event1<T> event, T value) {
      if (event == Service.EVENT
          && value instanceof String s
          && s.equals("FOO")) {

      }
    }
  }

}
```

 */
//@formatter:on
@Markdown
final class TheNoOpLoggerDebugging extends DocsPage {
  @Override
  protected void configure() {
    titleText = "During debugging";
  }
}