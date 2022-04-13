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

import br.com.objectos.be.annotations.Markdown;
import objectos.docs.ui.DocsPage;

// @formatter:off
/**

# Introduction

Developers need to keep a log of the events taking place during a program execution.
The log might contain purely informational messages such as:

- the application started successfully;
- a HTTP server received a request; or
- a scheduled job started.

These informational messages can show developers that their application
are functioning as expected. This can be specially useful for applications with
no or little direct user interaction such as a HTTP server, a database server
or a mail server.

 */
// @formatter:on
@Markdown
final class Introduction extends DocsPage {
  @Override
  protected final void register() {
    nextPage = null;

    titleText = "Introduction";
  }
}