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

# Getting started with Objectos Logging

Welcome to Objectos Logging.

This chapter introduces you to logging libraries in general and to
Objectos Logging in particular.

We begin by explaining why you may require a logging library in your Java application.
Next we give an overview on the Objectos Logging approach to logging. Finally we
give you instructions on how to install Objectos Logging and how to quickly get some
working Java code that makes use of Objectos Logging.

 */
//@formatter:on
@Markdown
final class GettingStartedIndex extends DocsPage {
  @Override
  protected void configure() {
    titleText = "Getting started";
  }
}