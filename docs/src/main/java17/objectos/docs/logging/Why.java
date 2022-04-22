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
package objectos.docs.logging;

import objectos.docs.logging.guide.LoggingGuideDir;
import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

// @formatter:off
/**

# Introduction

If you are new to logging in Java development or if you are not sure why logging might
be required please read on. If you are already familiar with logging, you might skip
to next section.

Java developers need to output messages describing the state

Java developers need to keep a log of the events taking place during a program execution.

This can be specially useful for applications with no or little direct user interaction
such as a HTTP server, a database server or a mail server. Keep in mind, though, that
logging is not restricted to these kind of applications. For example, a text editor
might want to keep a log of user actions in order to understand how a feature is being used
(or perhaps if the feature is being used at all).

The log might contain purely informational messages such as:

- the application started successfully;
- a HTTP server received a request; or
- a scheduled job completed successfully.

These informational messages can show developers that the application is functioning
as expected both at development time and while running at production.

The log might also contain messages indicating that a problem might occur to a running
application if no action is taken. These can be health-related messages such as resource
exhaustion:

- a query to the database took more than a certain number of milliseconds;
- few free slots remain in the job scheduler; or
- too many invalid requests from a certain client.

Applications will eventually not work as expected. As pointed out before, it could be
from resource exhaustion. Other reasons might also include (but are in no way limited to):

- programming errors;
- invalid or insufficient permissions; or
- hardware failures.

Therefore, the log may also contain error messages such as:

- the application could not start as the configured network port is already in use;
- a HTTP server could not serve a file as its system's process does not have the
  required permissions to read it;
- a scheduled job fails as it tries to use `null` as if it were an object; or
- a scheduled job fails as the Java virtual machine cannot allocate an object as
  it ran out of memory.

While the log may contain the message that an error has occurred, the developer
might need additional information in order to reproduce the error in a development
environment. In this case, the log may also contain messages intended for debugging purposes.
Some examples are:

- a method has been invoked with such and such arguments;
- a method completed normally returning such value; or
- this code path has been executed.

Finally, the log may also contain messages that cover non-functional requirements.
In a way, these messages can be classified as being both informational
and for debugging purposes. For example, suppose you believe a library upgrade or
some code refactoring might improve numbers such as latency or throughput. How can
you confirm whether or not those changes improved those numbers?

be also be used for performance. For instance,
how can you know if your last upgrade improved or not numbers such
as latency or throughput.

One thing in common to all the examples is that it is the developer job to:

- decide what events to log;
- define the severity of the logged event; and
- what information should be logged with the event.

 */
// @formatter:on
@Markdown
final class Why extends DocsPage {
  @Override
  protected final void configure() {
    nextPage = LoggingGuideDir.INDEX;

    titleText = "Why Objectos Logging?";
  }
}