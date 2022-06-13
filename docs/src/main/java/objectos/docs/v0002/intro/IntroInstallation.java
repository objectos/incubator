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
package objectos.docs.v0002.intro;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Installation

In order to get started with Objectos you need to have it installed in your system.

As of the current release there is only one supported installation method:

- add Objectos to an existing Maven project.

## System requirements

Objectos requires JDK 17 or later.

## Import the Objectos BOM POM

Using the Objectos BOM POM is the recommended way of adding Objectos to your
project. You can import it by declaring it in the `dependencyManagement`
section of your project's POM file like so:

*```xml
*<dependencyManagement>
*    <dependencies>
*        <dependency>
*            <groupId>br.com.objectos</groupId>
*            <artifactId>objectos-bom</artifactId>
*            <version>{{version}}</version>
*            <type>pom</type>
*            <scope>import</scope>
*        </dependency>
*    </dependencies>
*</dependencyManagement>
*```

## Use Objectos components

The next step is to add Objectos components to your project.
Since you have the Objectos BOM imported you do not need to specify the version
for each of the added components.

For instance, to add Objectos Lang to your project, you would declare it
in the `dependencies` section of your projects's POM file like so:

*```xml
*<dependencies>
*    <dependency>
*        <groupId>br.com.objectos</groupId>
*        <artifactId>objectos-lang</artifactId>
*    </dependency>
*</dependencies>
*```

If your application is modular, you should also add the `requires`
directive to your `module-info.java` file like so:

```java
module my.module {
  requires objectos.lang;
}
```

*/
//@formatter:on
@Markdown
final class IntroInstallation extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Installation";
  }
}
