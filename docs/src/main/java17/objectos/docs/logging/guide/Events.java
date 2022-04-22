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

# Events

All logging methods from the `Logger` class require that you pass an `Event` instance.
Therefore, before you can use a `logger` you need to define events.

## Description

An `Event` instance represents an event of a program execution that can be logged.
So for instance, if you want to log the start of a scheduled task, you create a
corresponding event like so:

```java
public class MyTask {
  private static final Event0 ESTART = Event0.info("START");
  private static final Event1<Long> ESUCCESS = Event1.info("SUCCESS");
  private static final Event1<IOException> EIO_EX = Event1.error("IO_EX");

  private final Logger logger;

  public MyTask(Logger logger) {
    this.logger = logger;
  }

  public void start() {
    logger.log(ESTART);

    long startTime = System.currentTimeMillis();

    try {
      doWork();

      long finishTime = System.currentTimeMillis();

      logger.log(ESUCCESS, finishTime - startTime);
    } catch (IOException e) {
      logger.log(EIO_EX, e);
    }
  }
}
```

 */
//@formatter:on
@Markdown
final class Events extends DocsPage {
  @Override
  protected final void configure() {
    nextPage = null;

    titleText = "Events";
  }
}