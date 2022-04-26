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

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

// @formatter:off
/**

# About logging

A logging library let's you emit messages about the events that take place during
a program execution. These messages usually contain the time of the event,
the source of the event (in Java programs the name of class of the object from
which the message was emitted) and the severity of the event. Additional information
related to the event may be also added to the message at the developer's discretion.

These messages may be written to a log. The log may be persistent such as a log file. The
log may be ephemeral such as messages directed to a console. Messages
may be filtered out before being written to the log; for example, only messages
above a certain severity level may be sent to the log.

The act of emitting, filtering and storing messages in a program is called logging.

## Types of log messages

A log may contain purely informational messages such as:

- a SQL server started successfully (i.e., it is ready for connections);
- a HTTP server sent a 404 response; or
- a scheduled job completed successfully.

These informational messages can show developers, at development time, that the application
is functioning as expected. Similarly, these messages can be used by a system operator
to verify that the system is running correctly while in production.

A log may also contain messages indicating that a problem might occur to a running
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
final class GettingStartedAboutLogging extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "About logging";
  }
}