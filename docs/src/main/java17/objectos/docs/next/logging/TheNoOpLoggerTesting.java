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

Please note that these are targeted to particular use-cases and should not
be considered for general practice.

## Collecting events

The `Logger` interface acts as an event listener. As such, it can be used
to collect events during a test run. Later, you can test if the collected events are what
you expected them to be.

This can be useful, for example, when testing state machines or, in some cases, multi-threaded
code.

 */
//@formatter:on
@Markdown
final class TheNoOpLoggerTesting extends DocsPage {
  @Override
  protected void configure() {
    titleText = "During testing";
  }
}