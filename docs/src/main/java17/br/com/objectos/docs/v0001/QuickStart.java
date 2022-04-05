/*
 * Copyright (C) 2022-2022 Objectos Software LTDA.
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
package br.com.objectos.docs.v0001;

import br.com.objectos.be.annotations.Be;
import br.com.objectos.be.annotations.Markdown;
import br.com.objectos.docs.DocsPage;

@Be
abstract class QuickStart extends DocsPage {

  @Override
  protected final ThisStyleSheet thisStyleSheet() {
    return new ThisStyleSheet() {
      @Override
      protected final void definition() {
        super.definition();

        articleCode();

        articleTable();

        articleUl();
      }
    };
  }

  @Override
  protected final String topBarTitle() {
    return null;
  }

  @Override
  protected final void uiMain() {
    article(
      header(
        h1("Objectos Quick Start")
      ),

      p("""
        Get started with Objectos. Learn how to add the Objectos dependencies to an existing
        Maven project."""),

      f(this::article0),

      f(this::article1),

      f(this::article2)
    );
  }

  //@formatter:off
  /**

## Choose the Objectos version

Objectos version 0.1.0 is compatible with following JDK versions:

- JDK 17+
- JDK 11
- JDK 8
- JDK 7
- JDK 6

Objectos provides different binaries for each of the supported JDK versions.
Therefore, prior to adding Objectos to your project, you must choose
the proper Objectos version. The choice depends on the JDK version
your application will be running on.

Use the table below and choose the Objectos version based on the JDK version
that better represents your environment. Please note that for new projects
we highly recommend that you use the latest available JDK.

```self
article0Table
```

   */
  //@formatter:on
  @Markdown
  abstract void article0();

  final void article0Table() {
    table(
      thead(
        tr(
          th("Your JDK version"),
          th("Use this Objectos version")
        )
      ),

      tbody(
        tr(
          td("JDK 18"),
          td(code("0.1.0-jdk17"))
        ),
        tr(
          td("JDK 17"),
          td(code("0.1.0-jdk17"))
        ),
        tr(
          td("JDK 11"),
          td(code("0.1.0-jdk11"))
        ),
        tr(
          td("JDK 8"),
          td(code("0.1.0-jdk8"))
        ),
        tr(
          td("JDK 7"),
          td(code("0.1.0-jdk7"))
        ),
        tr(
          td("JDK 6"),
          td(code("0.1.0-jdk6"))
        )
      )
    );
  }

 //@formatter:off
/**

## Import the Objectos BOM POM

Now that you have chosen the Objectos version, the next step is to import
the Objectos BOM POM.

You do so by declaring it in the `dependencyManagement` section of your
project's POM file.

*```xml
*<properties>
*    <objectos.version>version chosen in the previous section</objectos.version>
*</properties>
*
*<dependencyManagement>
*    <dependencies>
*        <dependency>
*            <groupId>br.com.objectos</groupId>
*            <artifactId>bom</artifactId>
*            <version>${objectos.version}</version>
*            <type>pom</type>
*            <scope>import</scope>
*        </dependency>
*    </dependencies>
*</dependencyManagement>
*```
 */
//@formatter:on
  @Markdown
  abstract void article1();

  //@formatter:off
  /**

## Use Objectos components

The next step is to add Objectos components to your project.
Since you have the Objectos POM imported, you do not need to specify the version
for each of the added component.

For instance, to add Objectos Logging to your project, you would declare it
in the `dependencies` section of your project's POM file, like so:

*```xml
*<dependencies>
*    <dependency>
*        <groupId>br.com.objectos</groupId>
*        <artifactId>logging</artifactId>
*    </dependency>
*</dependencies>
*```

   */
  //@formatter:on
  @Markdown
  abstract void article2();

}
