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

# Using the `NoOpLogger`

The `NoOpLogger` is a no-operation logger implementation provided with Objectos Logging.
In other words all of its logging methods do no operation when they are invoked.

It has uses during the development of a Java application
even though it performs no logging operation.
This chapters describes some of its uses.

 */
//@formatter:on
@Markdown
final class TheNoOpLoggerIndex extends DocsPage {
  @Override
  protected void configure() {
    titleText = "Using the NoOpLogger";
  }
}