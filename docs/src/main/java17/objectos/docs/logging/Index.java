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

//@formatter:off
/**

# Objectos Logging

Objectos Logging provides a logging API for Java applications.
You define logging events and you log them instead of string messages.
Events can be paramaterized making log method invocations type-safe.

*/
//@formatter:on
@Markdown
final class Index extends DocsPage {
  @Override
  protected void configure() {
    nextPage = GetStarted.class;

    titleText = "Overview";
  }
}