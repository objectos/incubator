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
package objectos.docs.intro;

import br.com.objectos.be.annotations.Markdown;
import objectos.docs.logging.LoggingDir;
import objectos.docs.ui.DocsPage;

//@formatter:off
/**

# Installation

In order to get started with Objectos you need to have it installed in your system.
As of the current release adding Objectos to an existing Maven project is the
only supported installation method.

## System requirements

Objectos requires JDK 17 or later.

## Import the Objectos BOM POM"

Using the Objectos BOM POM is the recommended way of managing the Objectos'
dependencies. You can import it in your existing Maven project by declaring it
in the `dependencyManagement` section of your project's POM file like so:

*```xml
*<dependencyManagement>
*    <dependencies>
*        <dependency>
*            <groupId>br.com.objectos</groupId>
*            <artifactId>bom</artifactId>
*            <version>0.1.0</version>
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

For instance, to add Objectos Logging to your project, you would declare it
in the `dependencies` section of your projects's POM file like so:

*```xml
*<dependencies>
*    <dependency>
*        <groupId>br.com.objectos</groupId>
*        <artifactId>logging</artifactId>
*    </dependency>
*</dependencies>
*```

If your application is modular, you should also add the `requires`
directive to your `module-info.java` file like so:

```java
module my.module {
  requires objectos.logging;
}
```

*/
//@formatter:on
@Markdown
final class Installation extends DocsPage {
  @Override
  protected final void register() {
    nextPage = LoggingDir.INDEX;

    titleText = "Installation";
  }
}
