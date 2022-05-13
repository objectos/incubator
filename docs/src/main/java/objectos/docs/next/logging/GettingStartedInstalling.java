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

# Installing Objectos Logging

In order to use Objectos Logging in your Java project you need to have it
installed in your system.

As of the current release there is only one supported installation method:

- adding Objectos Logging to an existing Maven project.

## System requirements

Objectos requires JDK 17 or later.

## Maven

Here are the instructions to install Objectos Logging in an existing Maven project.

The first step is to import the Objectos BOM POM to your project.
You can do it by declaring it in the `dependencyManagement` section
of your project's POM file like so:

*```xml
*<dependencyManagement>
*    <dependencies>
*        <dependency>
*            <groupId>br.com.objectos</groupId>
*            <artifactId>bom</artifactId>
*            <version>{{version}}</version>
*            <type>pom</type>
*            <scope>import</scope>
*        </dependency>
*    </dependencies>
*</dependencyManagement>
*```

The second step is to add Objectos Logging to your project. Declare it
in the `dependencies` section of your projects's POM file like so:

*```xml
*<dependencies>
*    <dependency>
*        <groupId>br.com.objectos</groupId>
*        <artifactId>logging</artifactId>
*    </dependency>
*</dependencies>
*```

## `module-info.java`

If your project is modular you should add a `requires` directive
to your `module-info.java` file:

```java
module com.example.project {
  requires objectos.logging;
}
```

 */
//@formatter:on
@Markdown
final class GettingStartedInstalling extends DocsPage {
  @Override
  protected void configure() {
    titleText = "Installing Objectos Logging";
  }
}