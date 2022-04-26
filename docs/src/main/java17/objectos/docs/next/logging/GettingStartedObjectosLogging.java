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

# Logging with Objectos Logging

In this section we will give you an overview of how Objectos Logging approaches
logging.

## Log events

As we have seen in the previous section, logging is about emitting messages
describing events taking place during a program execution. Therefore, a central
part of logging with Objectos Logging is defining the events you want to log.

## Debugging

## Monitoring

 */
//@formatter:on
@Markdown
final class GettingStartedObjectosLogging extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Logging with Objectos Logging";
  }
}
