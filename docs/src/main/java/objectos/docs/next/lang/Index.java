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
package objectos.docs.next.lang;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Objectos Lang

Objectos Lang provides:

- utilities for instances whose type is from the `java.lang` package; and
- a type-safe note sink API.

Objectos itself is built on top of Objectos Lang.

## Installation

To use Objectos Lang in your project add the following to your POM:

*```xml
*<dependencies>
*    <dependency>
*        <groupId>br.com.objectos</groupId>
*        <artifactId>objectos-lang</artifactId>
*    </dependency>
*</dependencies>
*```

This assumes you are using the [Objectos BOM POM](href:next.intro.IntroInstallation).

*/
//@formatter:on
@Markdown
final class Index extends DocsPage {
  @Override
  protected void configure() {
    titleText = "Objectos Lang";
  }
}